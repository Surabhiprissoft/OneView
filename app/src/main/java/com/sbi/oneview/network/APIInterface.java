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

    @POST(Constants.TRANSITMINISTATEMENT)
    Call<TransitMiniStatementResponseModel> getTransitMiniStatement(@Body TransitMiniStatementRequestModel transitMiniStatementRequestModel);

    @POST(Constants.CARDMINISTATEMENT)
    Call<String> getCardMiniStatement(@Body String cardMiniStatementRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.CARDSTATEMENT)
    Call<String> getCardStatement(@Body String inrCardStatementRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.CARDBLOCK)
    Call<String> getCardBlock(@Body String cardBlockUnblockRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.CARDUNBLOCK)
    Call<String> getCardUnBlock(@Body String cardBlockUnblockRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.CARDHOTLIST)
    Call<String> getCardHotlist(@Body String cardHotlistRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);

    @POST(Constants.LIMITENQUIRY)
    Call<InrLimitEnquiryResponseModel> getInrLimitEnquiry(@Body LimitEnquiryRequestModel limitEnquiryRequestModel);

    @POST(Constants.SETPIN)
    Call<String> getSetPin(@Body String setPinRequestModel,@Header("Authorization") String token,@Header("Access-Key") String accessKey,@Header("Host") String host);



}
