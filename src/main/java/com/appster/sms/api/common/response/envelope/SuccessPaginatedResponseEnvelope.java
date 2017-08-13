package com.appster.sms.api.common.response.envelope;

import com.appster.sms.api.common.response.Payload;
import com.appster.sms.api.common.response.meta.SuccessMeta;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * created by atul on 18/11/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessPaginatedResponseEnvelope<T extends Payload> {

    private T data;
    private SuccessMeta meta;
    private Pagination pagination;

    //build success response
    public SuccessPaginatedResponseEnvelope(T data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
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

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
