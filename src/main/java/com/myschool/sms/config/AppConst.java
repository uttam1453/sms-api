package com.myschool.sms.config;

/**
 * created by lokesh on 05/01/17.
 */
public interface AppConst {
    String TOKEN_HEADER = "Authorization";
    String SECRET = "Q@R'DuAzNzH*y5Z";
    int EXPIRATION = 604800;
    String EMPTY_STRING = "";
    int MAX_SEARCH_THREAD = 10;
    //default search distance around any campus in miles
    double DEFAULT_SEARCH_DISTANCE = 50;
    int AUTO_COMPLETE_MAX_RESULT = 10;
    int MAX_GOOGLE_BOOK_SEARCH_RESULT_BY_QUERY_FOR_AUTO_COMP = 30;
    int ZERO = 0;
    int ONE = 1;
    int BOOK_TITLE_MAX_LENGTH = 255;
    int BOOK_AUTHOR_NAME_MAX_LENGTH = 255;
    String FILE_SEPARATOR = "/";
    
    enum UPLOAD_TYPE { // NOSONAR
        PROFILE_IMAGE,ROOMS_IMAGE
     }
    
    enum GENDER {
        MALE, FEMALE
    }
}
