package com.sbi.oneview.utils;

public class ContactUsMethod {
    private String contactMethod;
    private String contactInfo;
    private String contactExplain;
    private int imageResource;

    public ContactUsMethod(String contactMethod, String contactInfo,String contactExplain, int imageResource) {
        this.contactMethod = contactMethod;
        this.contactInfo = contactInfo;
        this.contactExplain = contactExplain;
        this.imageResource = imageResource;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getContactExplain(){
        return  contactExplain;
    }

    public int getImageResource() {
        return imageResource;
    }

}
