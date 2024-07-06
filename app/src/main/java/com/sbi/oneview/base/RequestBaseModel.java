package com.sbi.oneview.base;

import com.google.gson.Gson;

public class RequestBaseModel<T> {

    private HeaderRequestModel headerRequestModel;
    private T request;

    public HeaderRequestModel getHeader() {
        return headerRequestModel;
    }

    public void setHeader(HeaderRequestModel headerRequestModel) {
        this.headerRequestModel = headerRequestModel;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
