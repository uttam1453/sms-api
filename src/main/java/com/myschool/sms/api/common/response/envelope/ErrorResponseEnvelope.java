package com.myschool.sms.api.common.response.envelope;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myschool.sms.api.common.response.meta.ErrorMeta;

/**
 * created by lokesh on 18/11/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseEnvelope {
    private ErrorMeta meta;


    public ErrorResponseEnvelope(ErrorMeta meta) {
        this.meta = meta;
    }

    public ErrorMeta getMeta() {
        return meta;
    }

    public void setMeta(ErrorMeta meta) {
        this.meta = meta;
    }
}
