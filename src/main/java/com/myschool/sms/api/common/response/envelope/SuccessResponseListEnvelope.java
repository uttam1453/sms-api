package com.myschool.sms.api.common.response.envelope;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myschool.sms.api.common.model.PaginatedResponseModelWrapper;
import com.myschool.sms.api.common.response.Payload;
import com.myschool.sms.api.common.response.meta.SuccessMeta;

/**
 * created by lokesh on 18/11/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponseListEnvelope<T extends Payload> {

    private List<T> data;
    private SuccessMeta meta;
    private Pagination pagination;

    //build success response
    public SuccessResponseListEnvelope(List<T> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
        this.meta = new SuccessMeta(200);
    }

    public SuccessResponseListEnvelope(List<T> data) {
        this.data = data;
        this.meta = new SuccessMeta(200);
    }

    public SuccessResponseListEnvelope(PaginatedResponseModelWrapper paginatedResponseModelWrapper) {
        this.data = paginatedResponseModelWrapper.getData();
        this.pagination = paginatedResponseModelWrapper.getPagination();
        this.meta = new SuccessMeta(200);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
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
