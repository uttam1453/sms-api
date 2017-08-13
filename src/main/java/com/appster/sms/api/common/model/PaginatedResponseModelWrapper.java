package com.appster.sms.api.common.model;

import com.appster.sms.api.common.response.Payload;
import com.appster.sms.api.common.response.envelope.Pagination;

import java.util.List;

/**
 * Created by atul on 24/02/17.
 */
public class PaginatedResponseModelWrapper<T extends Payload> {
    List<T> data;
    private Pagination pagination;

    public PaginatedResponseModelWrapper(List<T> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
