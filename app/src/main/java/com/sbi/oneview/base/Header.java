package com.sbi.oneview.base;

import com.google.gson.annotations.SerializedName;

public class Header {

    @SerializedName("dateTime")
    private String dateTime;

    @SerializedName("srvcName")
    private String srvcName;

    @SerializedName("channelId")
    private String channelId;

    @SerializedName("orgId")
    private String orgId;

    @SerializedName("tgtAppId")
    private String tgtAppId;

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setSrvcName(String srvcName) {
        this.srvcName = srvcName;
    }

    public String getSrvcName() {
        return srvcName;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setTgtAppId(String tgtAppId) {
        this.tgtAppId = tgtAppId;
    }

    public String getTgtAppId() {
        return tgtAppId;
    }

    @Override
    public String toString() {
        return
                "Header{" +
                        "dateTime = '" + dateTime + '\'' +
                        ",srvcName = '" + srvcName + '\'' +
                        ",channelId = '" + channelId + '\'' +
                        ",orgId = '" + orgId + '\'' +
                        ",tgtAppId = '" + tgtAppId + '\'' +
                        "}";
    }
}