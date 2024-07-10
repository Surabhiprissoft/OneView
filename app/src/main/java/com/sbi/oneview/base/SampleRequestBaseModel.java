package com.sbi.oneview.base;

import com.google.gson.Gson;

public class SampleRequestBaseModel<T> {


    private HeaderRequestModel headerRequestModel;
    private String request;

    public HeaderRequestModel getHeader() {
        return headerRequestModel;
    }

    public void setHeader(HeaderRequestModel headerRequestModel) {
        this.headerRequestModel = headerRequestModel;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
    /*@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }*/

}
