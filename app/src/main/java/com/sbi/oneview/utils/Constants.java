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
  // public static final String BASE_URL ="http://192.168.14.138:8080/" ;  // Local
   public static final String BASE_URL ="http://192.168.9.136:8080/" ;  // LAPTOP Local
  // public static final String BASE_URL ="https://10.176.14.138:8502/oneview/" ;  // Bank Env

   //public static final String BASE_URL_HOSTNAME ="192.168.49.99" ;   // Production
   public static final String BASE_URL_HOSTNAME ="192.168.0.130" ;   // UAT


 // End Points
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String TAG = "APIClient";

    // Api End points
    public static final String LOGINWITHOTP = "service/login";
    public static final String CARDMINISTATEMENT = "s2/cardMiniStatement";
    public static final String CARDBLOCK = "s2/cardBlock";
    public static final String CARDUNBLOCK = "s2/cardUnblock";
    public static final String CARDHOTLIST = "s2/hotlist";

}
