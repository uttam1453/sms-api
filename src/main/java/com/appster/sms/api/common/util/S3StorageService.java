package com.appster.sms.api.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.appster.sms.config.AppConst;

/**
 * created by atul on 22/01/16.
 */
@Service
@PropertySource({"classpath:application.properties"})
public class S3StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3StorageService.class);
    private static String bucketName;
    private static String accessKey;
    private static String secretKey;
    private static String profileImagePath;
    private static String roomImagePath;
    
    @Autowired
    Environment env;

    @PostConstruct
    public void init() {
        bucketName = env.getProperty("bucket_name");
        accessKey = env.getProperty("awsAccessKey");
        secretKey = env.getProperty("awsSecretKey");
        profileImagePath = env.getProperty("profileImagePath");
        roomImagePath = env.getProperty("roomImagePath");
    }

    public String saveImage(byte[] file, int userId, String fileName, String type) throws IOException {

        AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider("bookie"));
        String keyName = "user/" + userId + "/" + fileName;
        try {
            InputStream stream = new ByteArrayInputStream(file);
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(file.length);
            meta.setContentType("image/" + type);
            s3client.putObject(bucketName, keyName, stream, meta);
            s3client.setObjectAcl(bucketName, keyName, CannedAccessControlList.PublicRead);
        } catch (AmazonServiceException ase) {
            LOGGER.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            LOGGER.error("Error Message:    " + ase.getMessage());
            LOGGER.error("HTTP Status Code: " + ase.getStatusCode());
            LOGGER.error("AWS Error Code:   " + ase.getErrorCode());
            LOGGER.error("Error Type:       " + ase.getErrorType());
            LOGGER.error("Request ID:       " + ase.getRequestId());
            throw ase;
        } catch (AmazonClientException ace) {
            LOGGER.error("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            LOGGER.error("Error Message: " + ace.getMessage());
            throw ace;
        }
        return keyName;
    }
    
    public String saveImages(MultipartFile picFile, long id, AppConst.UPLOAD_TYPE uploadType) {
        try 
        {
            if (picFile == null || picFile.getBytes().length == 0) {
                return AppConst.EMPTY_STRING;
            }
            String originalFilename = picFile.getOriginalFilename();
            String extension = AppConst.EMPTY_STRING;
            if (originalFilename.contains(".")) {
                String[] split = picFile.getOriginalFilename().split("\\.");
                extension = split[split.length - 1];
            }
            
            String destinationDir = "";
            if(AppConst.UPLOAD_TYPE.PROFILE_IMAGE.equals(uploadType)){
            	destinationDir = profileImagePath+id;
            }
            else if(AppConst.UPLOAD_TYPE.ROOMS_IMAGE.equals(uploadType)){
            	destinationDir = roomImagePath+id;
            }
            
            LOGGER.info("uploadType : "+uploadType+" - extension.toLowerCase() : "+extension.toLowerCase()+" - destinationDir : "+destinationDir);
            String newName = UUID.randomUUID().toString().replaceAll("-", AppConst.EMPTY_STRING);
            String picURI = destinationDir + AppConst.FILE_SEPARATOR +newName + "." + extension.replace("\"", "");
	        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
	        
	        AmazonS3 s3client = new AmazonS3Client(credentials);
			ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(picFile.getContentType());
            meta.setContentLength(picFile.getSize());
            meta.setHeader("filename", picFile.getOriginalFilename());
	        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, picURI, picFile.getInputStream(), meta);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            s3client.putObject(putObjectRequest);
            IOUtils.closeQuietly(picFile.getInputStream());
            return picURI;
        }catch (AmazonServiceException ase) {
            LOGGER.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            LOGGER.error("Error Message:    " + ase.getMessage());
            LOGGER.error("HTTP Status Code: " + ase.getStatusCode());
            LOGGER.error("AWS Error Code:   " + ase.getErrorCode());
            LOGGER.error("Error Type:       " + ase.getErrorType());
            LOGGER.error("Request ID:       " + ase.getRequestId());
            throw ase;
        } catch (AmazonClientException ace) {
            LOGGER.error("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            LOGGER.error("Error Message: " + ace.getMessage());
            throw ace;
        } catch (IOException ex) {
        	LOGGER.error("IOException - "+ex);
        } catch (IllegalStateException ex) {
        	LOGGER.error("IllegalStateException - "+ex);
        }
        return AppConst.EMPTY_STRING;
    }
    
}
