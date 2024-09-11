package com.sbi.oneview.ui.FtcScreen;

import android.content.Intent;
import android.graphics.Color;
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
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.SetPinRequestModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.network.ResponseModel.SetPin.SetPinResponseModel;
import com.sbi.oneview.ui.WebView.ResetPinWebViewActivity;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class FtcResetPinFragment extends BaseFragment implements MyFragmentCallback {

    TextView tvBlockHeading,tvCurrentDate,tvResetPin,tvMyCards,tvCardDetails;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate;
    LinearLayout layoutCardStatus;
    MaterialButton btnResetPin;
    TextView tvCardStatusNote;
    FtcHomeActivity ftcHomeActivity;
    String currentCardStatus,token;
    String CardProxyNumber;
    int cardPosition;
    private static final String RESET_URL = "reset_url";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ftc_reset_pin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidget(view);
        clickListener();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getFtc().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView,null);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }

    public void initWidget(View view){
        tvBlockHeading = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        tvResetPin = view.findViewById(R.id.tvResetPin);

        tvCardNumber = view.findViewById(R.id.tvCardNumber);
        tvCRN = view.findViewById(R.id.tvCRNNumber);
        tvCardStatus = view.findViewById(R.id.tvCardStatus);
        layoutCardStatus = view.findViewById(R.id.layoutCardStatus);
        tvProductName = view.findViewById(R.id.tvCardProductName);
        tvActDate = view.findViewById(R.id.tvCardActiveDate);
        tvExpDate = view.findViewById(R.id.tvCardExpDate);

        btnResetPin = view.findViewById(R.id.btnResetPin);
        tvCardStatusNote = view.findViewById(R.id.tvCardStatusNote);

        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        tvBlockHeading.setText("Reset PIN");

        CommonUtils.setGradientColor(tvBlockHeading);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvResetPin);

        ftcHomeActivity = (FtcHomeActivity) getActivity();
    }

    public void clickListener(){

        btnResetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentCardStatus.equals("ACTIVE"))
                {
                    resetPin();
                }


            }



        });
    }


    public void resetPin(){
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

                        ObjectMapper om = new ObjectMapper();
                        ResponseBaseModel responseBaseModel = null;
                        JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                        try {
                            responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                        }catch (Exception e)
                        {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }


                        if (responseBaseModel!=null) {

                            if (responseBaseModel.getStatusCode() == 200) {

                                SetPinResponseModel setPinResponseModel = null;
                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    setPinResponseModel = om1.readValue(jsonString, SetPinResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (setPinResponseModel!=null){
                                    if (setPinResponseModel.getStatusCode()==200){

                                        Intent webViewIntent = new Intent(getActivity(), ResetPinWebViewActivity.class);
                                        webViewIntent.putExtra(RESET_URL,setPinResponseModel.getData().getTargetUrl());
                                        startActivity(webViewIntent);

                                    }
                                }

                            }
                        }

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

                }

                @Override
                public void onResponseBodyNull(Call<String> call, Response<String> response) {

                }

                @Override
                public void onResponseUnsuccessful(Call<String> call, Response<String> response) {

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }

                @Override
                public void onInternalServerError() {

                }
            });


        }else{
            Toast.makeText(getActivity(), ""+getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onPositionChange(int position) {
        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());
        if (loginResponse!=null){

            tvCRN.setText(loginResponse.getFtc().getCardDetails().get(position).getProxyNumber());
            tvCardNumber.setText(loginResponse.getFtc().getCardDetails().get(position).getCardNumber());
            tvCardStatus.setText(loginResponse.getFtc().getCardDetails().get(position).getCardStatus());
            tvProductName.setText(loginResponse.getFtc().getCardDetails().get(position).getProductName());
            tvActDate.setText(loginResponse.getFtc().getCardDetails().get(position).getCardActivDate().substring(3,5) +" / "+ loginResponse.getFtc().getCardDetails().get(position).getCardActivDate().substring(6));
            tvExpDate.setText(loginResponse.getFtc().getCardDetails().get(position).getCardExpiryDate().substring(3,5)+" / "+loginResponse.getFtc().getCardDetails().get(position).getCardExpiryDate().substring(6));

            CardProxyNumber = loginResponse.getFtc().getCardDetails().get(position).getProxyNumber();
            cardPosition = position;
            token = loginResponse.getToken();

            currentCardStatus = loginResponse.getFtc().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("ACTIVE") || currentCardStatus.equals("A")){
                tvCardStatus.setText("ACTIVE");
                tvCardStatusNote.setVisibility(View.GONE);
                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));

            }else if(currentCardStatus.equals("BLOCKED")){
                tvCardStatus.setText("BLOCKED");
                tvCardStatusNote.setText("Your card has been temporary block, Please unblock it first to procced with Reset pin.");
                tvCardStatusNote.setVisibility(View.VISIBLE);
                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }else{
                tvCardStatus.setText("INACTIVE");
                tvCardStatusNote.setText("Your card has been permanently block.");
                tvCardStatusNote.setVisibility(View.VISIBLE);
                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }


        }
    }


}