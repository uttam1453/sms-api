package com.appster.sms.api.user.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.appster.sms.api.common.AppException;
import com.appster.sms.api.common.constants.UserRoleEnum;
import com.appster.sms.api.master.service.MasterService;
import com.appster.sms.entity.MasterRoleEntity;
import com.appster.sms.entity.MasterSignupTypeEntity;
import com.appster.sms.entity.UserEntity;
import com.appster.sms.entity.UserRoleEntity;
import com.appster.sms.entity.UserSignupTypeEntity;
import com.appster.sms.entity.embeddedId.UserRolePK;
import com.appster.sms.entity.embeddedId.UserSignupTypePK;
import com.appster.sms.repo.MasterRoleRepo;
import com.appster.sms.repo.UserRepo;
import com.appster.sms.repo.UserSignUpTypeRepo;

/**
 * Created by atul on 26/01/17.
 */
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MasterService masterService;
    @Autowired
    private MasterRoleRepo masterRoleRepo;
    @Autowired
    private UserSignUpTypeRepo userSignUpTypeRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity createNewUser(String email, String password, String role) {
        if (userRepo.checkEmailExistence(email) != null)
            throw new AppException("AUTH.EMAIL_ALREADY_EXIST");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setProfileStatus(UserEntity.ACCOUNT_STATUS.ACTIVE);

        // MasterRoleEntity userMasterRole = masterService.getMasterRoleByName(role);
        MasterRoleEntity userMasterRole = masterRoleRepo.getActiveRole(UserRoleEnum.STUDENT.name());

        UserRoleEntity userRoleEntity = new UserRoleEntity(new UserRolePK(userEntity, userMasterRole));

        userEntity.setUserRoles(new ArrayList<>(Arrays.asList(userRoleEntity)));
        return userRepo.save(userEntity);
    }

    public UserEntity assignSignUpSource(UserEntity userEntity, String source, String sourceId) {
        MasterSignupTypeEntity masterSignupTypeEntity = masterService.getMasterSignupTypeByName(source);
        UserSignupTypeEntity userSignupTypeEntity = new UserSignupTypeEntity(new UserSignupTypePK(userEntity, masterSignupTypeEntity));
        userSignupTypeEntity = userSignUpTypeRepo.save(userSignupTypeEntity);
        userEntity.setUserSignupTypes(new ArrayList<>(Arrays.asList(userSignupTypeEntity)));
        return userEntity;
    }

    public boolean hasAlreadySignedUp(UserEntity userEntity, String source, String sourceId) {
        MasterSignupTypeEntity masterSignupTypeEntity = masterService.getMasterSignupTypeByName(source);
        UserSignupTypeEntity checkSignupTypeEntity = new UserSignupTypeEntity(new UserSignupTypePK(userEntity, masterSignupTypeEntity));
        for (UserSignupTypeEntity userSignupTypeEntity : userEntity.getUserSignupTypes()) {
            if (userSignupTypeEntity.equals(checkSignupTypeEntity))
                return true;
        }
        return false;
    }
/*
    public boolean hasCardRegistered(int userId) {
        CardEntity cardEntity = cardRepo.findByUserId(userId);
        if (null == cardEntity || null == cardEntity.getCardId() || cardEntity.getCardId().equals(AppConst.EMPTY_STRING))
            return false;
        return true;
    }

    public boolean hasBankAccountAdded(int userId) {
        return accountDetailRepo.findByUserId(userId) != null;

    }
    public UserRatingDetailModel getUserRatingDetails(int userId){
        List<Object[]> records=bookTransactionRatingRepo.getUserRatingsDetail(userId);
        Map<String,Integer> ratingsMap=new HashMap<>();
        ratingsMap.put("A",0);
        ratingsMap.put("B",0);
        ratingsMap.put("C",0);
        ratingsMap.put("D",0);
        ratingsMap.put("F",0);

        for (Object[] result : records) {
           ratingsMap.put((String)result[1], ((BigInteger)result[0]).intValue());
        }
        //create the rating detail model and return
        UserRatingDetailModel userRatingDetailModel=new UserRatingDetailModel();
        userRatingDetailModel.setA(ratingsMap.get("A"));
        userRatingDetailModel.setB(ratingsMap.get("B"));
        userRatingDetailModel.setC(ratingsMap.get("C"));
        userRatingDetailModel.setD(ratingsMap.get("D"));
        userRatingDetailModel.setF(ratingsMap.get("F"));
        return userRatingDetailModel;

    }
    public UserRatingModel getUserRating(int userId) {
      Double rating=bookTransactionRatingRepo.getUserRating(userId);
        if (rating==null)
            return new UserRatingModel("");
        return new UserRatingModel(getRatingIdentifier(rating));
    }
    private String getRatingIdentifier(double rating){
        if(rating>=4.5)
            return "A";
        else if(rating<=4.4 && rating>=3.5)
            return "B";
        else if(rating<=3.4 && rating>=2.5)
            return "C";
        else if(rating<=2.4 && rating>=1.5)
            return "D";
        else if(rating<=1.4 && rating>0)
            return "F";
        else
            return "";
    }
*/
}
