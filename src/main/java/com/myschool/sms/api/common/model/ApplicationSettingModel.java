package com.myschool.sms.api.common.model;

import com.myschool.sms.api.common.response.Payload;

/**
 * created by lokesh on 27/02/17.
 */
public class ApplicationSettingModel implements Payload {
    private String key;
    private String applicationData;

    public ApplicationSettingModel() {
    }

    public ApplicationSettingModel(String key, String applicationData) {
        this.key = key;
        this.applicationData = applicationData;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getApplicationData() {
        return applicationData;
    }

    public void setApplicationData(String applicationData) {
        this.applicationData = applicationData;
    }
}
