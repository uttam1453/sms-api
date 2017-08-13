package com.appster.sms.api.master.model;

import com.appster.sms.api.common.model.BaseId;

/**
 * Created by atul on 02/06/17.
 */
public class SegmentModel extends BaseId {
    private String name;
    private String description;
    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
