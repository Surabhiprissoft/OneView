package com.sbi.oneview.utils;

import android.content.Context;

public class Constants {
    public static final String RESPONSECODE_SUCESS = "00";
    public static final String AUTHENTICATEUSER ="/login";
    static Context context;

    //CONSTANTS FIELDS FOR HEADER
    public  static final String TARGETAPPID="PCMS";
    public  static final String ORGANISATIONID="SC0001";

  // public static final String BASE_URL ="https://192.168.49.99/APIW/outletPortalMMRDA/" ;     // Production URL
   public static final String BASE_URL ="https://192.168.49.101/apiw/outletPortalMMRDA/" ;  // UAT URL

   //public static final String BASE_URL_HOSTNAME ="192.168.49.99" ;   // Production
   public static final String BASE_URL_HOSTNAME ="192.168.49.101" ;   // UAT


 // End Points
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String TAG = "APIClient";

}
