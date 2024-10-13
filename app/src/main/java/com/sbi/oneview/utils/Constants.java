package com.sbi.oneview.utils;

import android.content.Context;

public class Constants {
    public static final String RESPONSECODE_SUCESS = "00";
    public static final String AUTHENTICATEUSER ="/login";
    static Context context;



  public static final String BASE_URL ="https://oneview.prepaid.sbi/oneview/" ;     // Production URL
 //  public static final String BASE_URL ="http://192.168.155.136:8080/" ;  // LAPTOP Local
 //  public static final String BASE_URL ="https://corpuat.prepaid.sbi:444/oneview/" ;  // UAT

   public static final String BASE_URL_HOSTNAME ="oneview.prepaid.sbi" ;   // Production
  // public static final String BASE_URL_HOSTNAME ="corpuat.prepaid.sbi" ;   // UAT


  //  public static final String TOP_UP_SUCCESS_URL = "https://oneview.prepaid.sbi/oneview/epay/topup-success";    //PROD
    public static final String TOP_UP_SUCCESS_URL = "https://corpuat.prepaid.sbi:444/oneview/epay/topup-success";  //UAT
    //public static final String TOP_UP_FAIL_URL = "https://oneview.prepaid.sbi/oneview/epay/topup-fail";    //PROD
    public static final String TOP_UP_FAIL_URL = "https://corpuat.prepaid.sbi:444/oneview/epay/topup-fail";   //UAT




 // End Points
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String TAG = "APIClient";

    // Api End points
    public static final String GETCAPTCHA = "captcha/getCaptcha";
    public static final String VALIDATECAPTCHA = "service/getOtp";
    public static final String LOGINWITHOTP = "service/login";
    public static final String CARDSTATEMENT = "s2/cardStatement";
    public static final String CARDMINISTATEMENT = "s2/cardMiniStatement";
    public static final String INRCARDTOPUP = "s2/topUpNotification";
    public static final String CARDBLOCK = "s2/cardBlock";
    public static final String CARDUNBLOCK = "s2/cardUnblock";
    public static final String CARDHOTLIST = "s2/hotlist";
    public static final String LIMITENQUIRY = "s2/limitEnquiry";
    public static final String LIMITUPDATE = "s2/limitUpdate";
    public static final String SETPIN = "s2/setPin";

    public static final String TRANSITSTATEMENT = "s1/statement";
    public static final String TRANSITMINISTATEMENT = "s1/miniStatement";
    public static final String TRANSITREQUESTHOTLIST = "s1/requestHotlist";
    public static final String TRANSITPROCESSHOTLIST = "s1/processHotlist";
    public static final String TRANSITTOPUP = "s1/initiateTopup";
    public static final String VALIDATEEFORM = "s1/applyForCard";
    public static final String PROCESSEFORM = "s1/processEform";
    public static final String RESENDOTP = "s1/resendOtp";


    public static final String LOGOUT = "service/logout";
    public static final String LOGINRESENDOTP = "service/resendOtp";




}
