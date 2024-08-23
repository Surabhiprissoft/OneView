package com.sbi.oneview.ui.inrPrepaid;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.base.BaseFragment;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIClient;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.CardHotlistRequestModel;
import com.sbi.oneview.network.RequestModel.SetPinRequestModel;
import com.sbi.oneview.network.ResponseModel.HotlistCard.CardHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.InrResetPinResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.network.ResponseModel.ResetPin.FSSResetPinResponseModel;
import com.sbi.oneview.network.ResponseModel.SetPin.SetPinResponseModel;
import com.sbi.oneview.ui.CallBackListner.OtpDialogueCallBack;
import com.sbi.oneview.ui.WebView.ResetPinWebViewActivity;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.OTPVerificationDialog;
import com.sbi.oneview.utils.SharedConfig;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class InrResetPinFragment extends BaseFragment implements MyFragmentCallback {


    TextView tvResetPin,tvCardStatusNote;
    OTPVerificationDialog otpVerificationDialog;


    Data loginResponse;
    String currentCardStatus;

    String CardProxyNumber,token;
    int cardPosition;
    TextView tvCurrentDate,tvMyCards,tvCardDetails,tvResetPinSubHeading;
    LinearLayout layoutCardStatus,layoutSpendLimitController;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate,tvCardBal,tvChipBal;
    MaterialButton btnResetPin;

    private static final String RESET_URL = "reset_url";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inr_reset_pin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidget(view);
        clickListener();


    }

    public void initWidget(View view){

        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);

        tvCardNumber = view.findViewById(R.id.tvCardNumber);
        tvCRN = view.findViewById(R.id.tvCRNNumber);
        tvCardStatus = view.findViewById(R.id.tvCardStatus);
        //cardStatusCard = view.findViewById(R.id.cardStatusCard);
        layoutCardStatus = view.findViewById(R.id.layoutCardStatus);
        tvProductName = view.findViewById(R.id.tvCardProductName);
        tvActDate = view.findViewById(R.id.tvCardActiveDate);
        tvExpDate = view.findViewById(R.id.tvCardExpDate);
        tvCardBal = view.findViewById(R.id.tvCardBalance);
        tvChipBal = view.findViewById(R.id.tvChipBalance);
        tvResetPinSubHeading = view.findViewById(R.id.tvResetPinSubHeading);
        tvCardStatusNote = view.findViewById(R.id.tvCardStatusNote);
        btnResetPin = view.findViewById(R.id.btnResetPin);

        tvResetPin = view.findViewById(R.id.tvHeader);
        tvResetPin.setText("Reset Pin");
        CommonUtils.setGradientColor(tvResetPin);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());


        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvResetPinSubHeading);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getPrepaid().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void clickListener(){

        btnResetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                /*if (otpVerificationDialog != null && otpVerificationDialog.isShowing()) {
                    otpVerificationDialog.dismiss();
                }
                otpVerificationDialog = new OTPVerificationDialog(InrResetPinFragment.this::onVerifyClick,getActivity());
                //otpVerificationDialog.setCancelable(false);
                otpVerificationDialog.show();*/


                showLoading();

                String randomKey = CommonUtils.generateRandomString();
                System.out.println("Random Key: " + randomKey);

                SetPinRequestModel setPinRequestModel = new SetPinRequestModel();
                setPinRequestModel.setProxyNumber(CardProxyNumber);
                setPinRequestModel.setSid("");

                ObjectMapper om = new ObjectMapper();
                String req = null;
                try {
                    req = om.writeValueAsString(setPinRequestModel);
                } catch (JsonProcessingException e) {
                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                }
                String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
                System.out.println("Message : " + encryptedMsg);


                if(NetworkUtils.isNetworkConnected(getActivity())){

                    APIRequests.SetPin(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
                        @Override
                        public void onSuccess(Call<String> call, Response<String> response) {

                            if (response.isSuccessful()){

                                String encryptedResponse = response.body();
                                encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                                String res = (String) CipherEncryption.decryptMessage(encryptedResponse,randomKey);

                                String strResponse = convertToJson(res);
                                Log.d("RESPONSE",""+strResponse);

                                String jsonResponse = "{\"statusCode\":200,\"data\":{\"message\":\"Success\",\"targetUrl\":\"http://10.176.6.136:8504/SBICMS/cms/tl_cfp/u_14.cms?es=%22OmXTrvINHbOSAqWfaM/FIc1m7YzSysG/&id=t255YAlyMp8%22\",\"id\":\"t255YAlyMp8\"},\"message\":\"Pin set successfully\"}";

                                try {
                                    // Create a JSONObject from the response string
                                    JSONObject jsonObject = new JSONObject(jsonResponse);

                                    // Get the "data" object
                                    JSONObject dataObject = jsonObject.getJSONObject("data");

                                    // Extract the "targetUrl" value
                                    String targetUrl = dataObject.getString("targetUrl");

                                    // Print or use the targetUrl as needed
                                    System.out.println("Full Target URL: " + targetUrl);

                                    /*Intent intent = new Intent(getActivity(), ResetPinWebViewActivity.class);
                                    intent.putExtra("resetUrl", targetUrl);
                                    startActivity(intent);*/

                                    String url = targetUrl; // Replace with your desired URL
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                    startActivity(intent);



                                } catch (JSONException e) {
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

/*
                                ObjectMapper objectMapper = new ObjectMapper();
                                try {
                                    // Deserialize JSON string to SampleResponseModel
                                    FSSResetPinResponseModel sampleResponseModel = objectMapper.readValue(strResponse, FSSResetPinResponseModel.class);

                                    // Use the deserialized object
                                    System.out.println("Status Code: " + sampleResponseModel.getStatusCode());
                                    System.out.println("Message: " + sampleResponseModel.getMessage());
                                    System.out.println("URL: " + sampleResponseModel.getData().getTargetUrl());
                                    System.out.println("URL MSG: " + sampleResponseModel.getData().getMessage());

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }*/




                                /*ObjectMapper om = new ObjectMapper();
                                String decryptedResponse = (String) CipherEncryption.decryptMessage(encryptedResponse,randomKey);

                                if (decryptedResponse!=null) {

                                    JsonNode node = null;
                                    try {
                                        node = om.readTree(decryptedResponse);
                                    } catch (JsonProcessingException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println(node.toPrettyString());
                                    JsonNode dataNode = node.get("data");

                                    if (dataNode != null) {
                                        String message = dataNode.get("message").asText();
                                        String targetUrl = dataNode.get("targetUrl").asText();
                                        System.out.println("Data Message: " + message);
                                        System.out.println("Target URL: " + targetUrl);
                                    }

                                }*/
                                /*ObjectMapper om = new ObjectMapper();
                                FSSResetPinResponseModel responseBaseModel = null;
                                Log.d("MSEG",encryptedResponse);
                                String  node = (String) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                                try {
                                    responseBaseModel = om.treeToValue(node, FSSResetPinResponseModel.class);
                                }catch (Exception e)
                                {
                                    Log.d("EXCEPTION",e.getLocalizedMessage());
                                }


                                if (responseBaseModel!=null) {

                                    if (responseBaseModel.getStatusCode() == 200) {

                                        Log.d("URL",""+responseBaseModel.getData().getTargetUrl());
                                        *//*InrResetPinResponseModel setPinResponseModel = null;
                                        try{
                                            Object data = responseBaseModel;

                                            // Convert LinkedHashMap to JSON string
                                            ObjectMapper om1 = new ObjectMapper();
                                            String jsonString = om1.writeValueAsString(data);
                                            setPinResponseModel = om1.readValue(jsonString, InrResetPinResponseModel.class);

                                        }catch (Exception e){
                                            Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                        }

                                        if (setPinResponseModel!=null){

                                            Intent webViewIntent = new Intent(getActivity(), ResetPinWebViewActivity.class);
                                            webViewIntent.putExtra(RESET_URL,setPinResponseModel.getTargetUrl());
                                            startActivity(webViewIntent);

                                        }*//*

                                    }
                                }
*/
                            }
                            else{

                                String encryptedResponse ="";
                                try {
                                    encryptedResponse = response.errorBody().string();
                                } catch (IOException e) {
                                    Log.d("EXCEPTION",e.getLocalizedMessage());
                                }
                                encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                                ObjectMapper om = new ObjectMapper();
                                ResponseBaseModel responseBaseModel = null;
                                JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                                try {
                                    responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                                }catch (Exception e)
                                {
                                    Log.d("EXCEPTION",e.getLocalizedMessage());
                                }

                                if (responseBaseModel!=null)
                                {
                                    Toast.makeText(getActivity(), ""+responseBaseModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }

                            hideLoading();

                        }

                        @Override
                        public void onResponseBodyNull(Call<String> call, Response<String> response) {
                            hideLoading();

                        }

                        @Override
                        public void onResponseUnsuccessful(Call<String> call, Response<String> response) {
                            hideLoading();

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            hideLoading();

                        }

                        @Override
                        public void onInternalServerError() {
                            hideLoading();

                        }
                    });
                }else{
                    Toast.makeText(getActivity(), ""+getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onPositionChange(int position) {
        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());
        if (loginResponse!=null){

            tvCRN.setText(loginResponse.getPrepaid().getCardDetails().get(position).getProxyNumber());
            tvCardNumber.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardNumber());
            tvCardStatus.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardStatus());
            tvProductName.setText(loginResponse.getPrepaid().getCardDetails().get(position).getProductName());
            tvActDate.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardActivDate().substring(3,5) +" / "+ loginResponse.getPrepaid().getCardDetails().get(position).getCardActivDate().substring(6));
            tvExpDate.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardExpiryDate().substring(3,5)+" / "+loginResponse.getPrepaid().getCardDetails().get(position).getCardExpiryDate().substring(6));


            for(int i=0;i<loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().size();i++){
                if (loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().get(i).getAccountType().equals("Online")){
                    tvCardBal.setText(getResources().getString(R.string.Rs)+""+loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().get(i).getAccountBalance());
                }
                else{
                    tvChipBal.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().get(i).getAccountBalance());
                }
            }

            CardProxyNumber = loginResponse.getPrepaid().getCardDetails().get(position).getProxyNumber();
            cardPosition = position;
            token = loginResponse.getToken();

            currentCardStatus = loginResponse.getPrepaid().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("ACTIVE")){

                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));
                tvCardStatusNote.setVisibility(View.GONE);
                btnResetPin.setBackgroundColor(getResources().getColor(R.color.background_one));

            }else if(currentCardStatus.equals("BLOCKED")){

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));
                tvCardStatusNote.setVisibility(View.VISIBLE);
                tvCardStatusNote.setText("Your Card has been blocked, please un-block first to proceed.");
                btnResetPin.setBackgroundColor(getResources().getColor(R.color.background_dim));


            }else if (currentCardStatus.equals("INACTIVE")){

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));
                tvCardStatusNote.setVisibility(View.VISIBLE);
                tvCardStatusNote.setText("Your Card has been permanantly blocked, please visit nearest branch for issuance of new card.");
                btnResetPin.setBackgroundColor(getResources().getColor(R.color.background_dim));

            }


        }
    }

   /* @Override
    public void onVerifyClick(String otp) {
        otpVerificationDialog.dismiss();
        Toast.makeText(getActivity(), "Reached to fragment : "+otp, Toast.LENGTH_SHORT).show();

    }*/

    public static String convertToJson(String input) {

        String json = input.replaceAll("\\b(\\w+)\\s*=\\s*", "\"$1\": ");
        json = json.replaceAll("(?<=\": )(?!\\[|\\{|(?:(?:null)[,}\n]))([^,}\n]*)", "\"$1\"");
        return json;
    }


}