package com.sbi.oneview.network;


import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.network.RequestModel.CardBlockUnblockRequestModel;
import com.sbi.oneview.network.RequestModel.CardHotlistRequestModel;
import com.sbi.oneview.network.RequestModel.CardMiniStatementRequestModel;
import com.sbi.oneview.network.RequestModel.LoginRequestModel;
import com.sbi.oneview.network.RequestModel.LoginWithOtpRequestModel;
import com.sbi.oneview.network.ResponseModel.BlockUnblockCard.CardBlockUnblockResponseModel;
import com.sbi.oneview.network.ResponseModel.HotlistCard.CardHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.LoginWithOtpResponseModel;
import com.sbi.oneview.network.ResponseModel.MiniStatement.CardMiniStatementResponseModel;
import com.sbi.oneview.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {


    @POST(Constants.AUTHENTICATEUSER)
    Call<LoginResponseModel> authenticateUser(@Body LoginRequestModel loginRequest);

    @POST(Constants.LOGINWITHOTP)
    Call<LoginWithOtpResponseModel> loginUserWithOtp(@Body LoginWithOtpRequestModel loginWithOtpRequestModel);

    @POST(Constants.CARDMINISTATEMENT)
    Call<CardMiniStatementResponseModel> getCardMiniStatement(@Body CardMiniStatementRequestModel cardMiniStatementRequestModel);

    @POST(Constants.CARDBLOCK)
    Call<CardBlockUnblockResponseModel> getCardBlock(@Body CardBlockUnblockRequestModel cardBlockUnblockRequestModel);

    @POST(Constants.CARDUNBLOCK)
    Call<CardBlockUnblockResponseModel> getCardUnBlock(@Body CardBlockUnblockRequestModel cardBlockUnblockRequestModel);

    @POST(Constants.CARDHOTLIST)
    Call<CardHotlistResponseModel> getCardHotlist(@Body CardHotlistRequestModel cardHotlistRequestModel);

 /*   @POST(Constants.AUTHENTICATEUSER)
    Call<String> authenticateUser(@Query("channelId") String channelID, @Body String loginRequest);

    @POST(Constants.VALIDATECARDLINKING)
    Call<String> validatecardLinking(@Query("channelId") String channelID, @Body String validateCardLinkingRequest);

    @POST(Constants.PROCESSCARDLINKING)
    Call<String> processcardLinking(@Query("channelId") String channelID, @Body String processCard);

    @POST(Constants.GETCUSTOMERDATA)
    Call<String> getCustomerData(@Query("channelId") String channelID, @Body String customercard);


    @POST(Constants.CARDLINKINGEFORM)
    Call<String> processEcardLink(@Query("channelId") String channelID, @Body String customercard);


    @POST(Constants.CUSTOMERCARDDATA)
    Call<String> getCustomerCardData(@Query("channelId") String channelID, @Body String customerCardData);


    @POST(Constants.REQUESTCARDBLOCK)
    Call<String> requestCardBlock(@Query("channelId") String channelID, @Body String requestCardBlock);

    @POST(Constants.PROCESSCARDBLOCK)
    Call<String> processCardBlock(@Query("channelId") String channelID, @Body String processCardBlock);

    @POST(Constants.CHECKELIGIBILITY)
    Call<String> checkReplacementEligibility(@Query("channelId") String channelID, @Body String CheckEligibility);

    @POST(Constants.RESENDOTP)
    Call<String> resendOtp(@Query("channelId") String channelID, @Body String resendOtp);

    @POST(Constants.REPLACECARD)
    Call<String> replaceCard(@Query("channelId") String channelID, @Body String resendOtp);

    @POST(Constants.LOGOUT)
    Call<String> logoutUser(@Query("channelId") String channelID, @Body String resendOtp);*/

}
