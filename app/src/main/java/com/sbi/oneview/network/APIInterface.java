package com.sbi.oneview.network;


import com.sbi.oneview.network.RequestModel.CardBlockUnblockRequestModel;
import com.sbi.oneview.network.RequestModel.CardHotlistRequestModel;
import com.sbi.oneview.network.RequestModel.CardMiniStatementRequestModel;
import com.sbi.oneview.network.RequestModel.InrCardStatementRequestModel;
import com.sbi.oneview.network.RequestModel.LimitEnquiryRequestModel;
import com.sbi.oneview.network.RequestModel.LoginRequestModel;
import com.sbi.oneview.network.RequestModel.LoginWithOtpRequestModel;
import com.sbi.oneview.network.RequestModel.SetPinRequestModel;
import com.sbi.oneview.network.RequestModel.TransitMiniStatementRequestModel;
import com.sbi.oneview.network.RequestModel.ValidateCaptchaRequestModel;
import com.sbi.oneview.network.ResponseModel.BlockUnblockCard.CardBlockUnblockResponseModel;
import com.sbi.oneview.network.ResponseModel.GetCaptcha.GetCaptchaResponseModel;
import com.sbi.oneview.network.ResponseModel.HotlistCard.CardHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.InrCardStatement.InrCardStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.InrLimitEnquiry.InrLimitEnquiryResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.LoginWithOtpResponseModel;
import com.sbi.oneview.network.ResponseModel.MiniStatement.CardMiniStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.SetPin.SetPinResponseModel;
import com.sbi.oneview.network.ResponseModel.TransitMiniStatement.TransitMiniStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.ValidateCaptcha.ValidateCaptchaResponseModel;
import com.sbi.oneview.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIInterface {


    @POST(Constants.AUTHENTICATEUSER)
    Call<LoginResponseModel> authenticateUser(@Body LoginRequestModel loginRequest);

    @GET(Constants.GETCAPTCHA)
    Call<GetCaptchaResponseModel> getCaptcha();

    @POST(Constants.VALIDATECAPTCHA)
    Call<String> ValidateCaptcha(@Body String validateCaptchaRequestModel, @Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.LOGINWITHOTP)
    Call<String> loginUserWithOtp(@Body String loginWithOtpRequestModel,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.CARDMINISTATEMENT)
    Call<String> getCardMiniStatement(@Body String cardMiniStatementRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.CARDSTATEMENT)
    Call<String> getCardStatement(@Body String inrCardStatementRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.INRCARDTOPUP)
    Call<String> getInrTopup(@Body String inrTopupRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.CARDBLOCK)
    Call<String> getCardBlock(@Body String cardBlockUnblockRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.CARDUNBLOCK)
    Call<String> getCardUnBlock(@Body String cardBlockUnblockRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.CARDHOTLIST)
    Call<String> getCardHotlist(@Body String cardHotlistRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.LIMITENQUIRY)
    Call<String> getInrLimitEnquiry(@Body String limitEnquiryRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.LIMITUPDATE)
    Call<String> getInrLimitUpdate(@Body String limitUpdateRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.SETPIN)
    Call<String> getSetPin(@Body String setPinRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);


    // ------------------------------------------ TRANSIT -----------------------------------------------------------------------
    @POST(Constants.VALIDATEEFORM)
    Call<String> getValidateEform(@Body String validateEformRequestModel,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.PROCESSEFORM)
    Call<String> getProcessEform(@Body String processEfromRequestModel,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.TRANSITSTATEMENT)
    Call<String> getTransitStatement(@Body String transitStatementRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.TRANSITMINISTATEMENT)
    Call<String> getTransitMiniStatement(@Body String transitMiniStatementRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.TRANSITREQUESTHOTLIST)
    Call<String> getTransitHotlistRequest(@Body String transitRequestHotlistRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.TRANSITPROCESSHOTLIST)
    Call<String> getTransitHotlistProcess(@Body String transitProcessHotlistRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.TRANSITTOPUP)
    Call<String> getInitiateTopUP(@Body String initiateTopupRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);


    @POST(Constants.RESENDOTP)
    Call<String> getResendOtp(@Body String resendOtpRequestModel,@Header("Access-Key") String accessKey,@Header("Host") String host);


    @POST(Constants.LOGOUT)
    Call<String> getLogout(@Body String logoutRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.LOGINRESENDOTP)
    Call<String> getLoginResendOtp  (@Body String validateCaptchaRequestModel, @Header("Access-Key") String accessKey,@Header("Host") String host);

}
