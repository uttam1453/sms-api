package com.myschool.sms.api.common.response.envelope;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myschool.sms.api.common.response.Payload;
import com.myschool.sms.api.common.response.meta.SuccessMeta;

/**
 * created by lokesh on 18/11/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponseEnvelope<T extends Payload> {

    private T data;
    private SuccessMeta meta;

    //build success response
    public SuccessResponseEnvelope(T data) {
        this.data = data;
        this.meta = new SuccessMeta(200);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SuccessMeta getMeta() {
        return meta;
    }

    public void setMeta(SuccessMeta meta) {
        this.meta = meta;
    }
}
