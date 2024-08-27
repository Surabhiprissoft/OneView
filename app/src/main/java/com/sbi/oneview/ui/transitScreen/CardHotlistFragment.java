package com.sbi.oneview.ui.transitScreen;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.base.BaseFragment;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.Transit.TransitProcessHotlistRequestModel;
import com.sbi.oneview.network.RequestModel.Transit.TransitRequestHotlistRequestModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.network.ResponseModel.TransitMiniStatement.TransitMiniStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.TransitRequestHotlist.TransitRequestHotlistResponseModel;
import com.sbi.oneview.ui.CallBackListner.OtpDialogueCallBack;
import com.sbi.oneview.ui.WebView.TopupWebViewActivity;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.ui.paymentStatus.PaymentStatusActivity;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.OTPVerificationDialog;
import com.sbi.oneview.utils.SharedConfig;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class CardHotlistFragment extends BaseFragment implements MyFragmentCallback, OtpDialogueCallBack {

    TextView tvHotlistHeading,tvCurrentDate,tvHotlist,tvMyCards,tvCardDetails;
    MaterialButton btnHotlist;
    OTPVerificationDialog otpVerificationDialog;

    Spinner spinnerHotlistReason;
    String selectedHotlistReason ="";

    Data loginResponse;
    String currentCardStatus,token;
    String requestTxnID="";
    String CardProxyNumber,mobileNumber;
    int cardPosition;
    TextView tvSpendLimit,tvHotlistNote;
    LinearLayout layoutCardStatus,layoutSpendLimitController,layoutHotlistOption;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate,tvCardBal,tvChipBal,tvCardBalanceSync,tvChipBalanceSync;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_hotlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidget(view);
        clickListener();

    }


    public void initWidget(View view){

        btnHotlist = view.findViewById(R.id.btnHotlist);
        tvHotlistHeading = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvHotlist = view.findViewById(R.id.tvCardHotlist);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        spinnerHotlistReason = view.findViewById(R.id.spinnerHotlistReason);

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
        tvCardBalanceSync = view.findViewById(R.id.tvCardBalanceSync);
        tvChipBalanceSync = view.findViewById(R.id.tvChipBalanceSync);

        tvHotlistNote = view.findViewById(R.id.tvHotlistNote);
        layoutHotlistOption = view.findViewById(R.id.layoutHotlistOption);


        CommonUtils.setGradientColor(tvHotlist);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);

        tvHotlistHeading.setText("Card Hotlist");
        CommonUtils.setGradientColor(tvHotlistHeading);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());

        String[]cards_array  = getResources().getStringArray(R.array.hot_list_reason_array);
        ArrayAdapter<String> cardsAdapter =new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, cards_array);
        spinnerHotlistReason.setAdapter(cardsAdapter);



        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getTransit().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }

    public void clickListener(){

        spinnerHotlistReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(i==0)
                {
                    selectedHotlistReason = "";
                    //((TextView) view.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.transperant_white));
                }
                else
                {
                    selectedHotlistReason = parent.getSelectedItem().toString();
                    //((TextView) view.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.hinttext_transperant_white));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnHotlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedHotlistReason.equalsIgnoreCase("")) {
                    Toast.makeText(App.getAppContext(), "Select reason to proceed", Toast.LENGTH_SHORT).show();
                }else {
                    showConfirmationDialogue();
                }
            }
        });


    }


    public void showConfirmationDialogue(){

        Dialog dialog = new Dialog(getActivity());

        dialog.setContentView(R.layout.dialog_hotlist_confirmation);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        ImageView closeImg = dialog.findViewById(R.id.imgClose);
        MaterialButton btnYes = dialog.findViewById(R.id.btnYes);
        MaterialButton btnNo = dialog.findViewById(R.id.btnNo);

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                requestHotlist("Lost");

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onPositionChange(int position) {
        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());
        if (loginResponse!=null){

            tvCRN.setText(loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber());
            tvCardNumber.setText("XXXX XXXX XXXX "+loginResponse.getTransit().getCardDetails().get(position).getCardNumber());
            tvCardStatus.setText(loginResponse.getTransit().getCardDetails().get(position).getCardStatus().equals("A") ? "ACTIVE":"INACTIVE");
            tvProductName.setText(loginResponse.getTransit().getCardDetails().get(position).getProductName());
            tvCardBalanceSync.setText("[As on "+loginResponse.getTransit().getCardDetails().get(position).getLastSyncPersonal() +"]");
            tvChipBalanceSync.setText("[As on "+loginResponse.getTransit().getCardDetails().get(position).getLastSyncTransit()+"]");
            tvActDate.setText(loginResponse.getTransit().getCardDetails().get(position).getActivityDate().substring(0,2) +" / "+ loginResponse.getTransit().getCardDetails().get(position).getActivityDate().substring(2));
            tvExpDate.setText(loginResponse.getTransit().getCardDetails().get(position).getExpDate().substring(0,2)+" / "+loginResponse.getTransit().getCardDetails().get(position).getExpDate().substring(2));

            tvCardBal.setText(getResources().getString(R.string.Rs)+loginResponse.getTransit().getCardDetails().get(position).getWallBalPersonal());
            tvChipBal.setText(getResources().getString(R.string.Rs)+"0");


            CardProxyNumber = loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber();
            cardPosition = position;
            token = loginResponse.getToken();
            mobileNumber = SharedConfig.getInstance(getActivity()).getMobileNumber();

            currentCardStatus = loginResponse.getTransit().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("A") || currentCardStatus.equals("ACTIVE")){

                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));
                layoutHotlistOption.setVisibility(View.VISIBLE);
                tvHotlistNote.setVisibility(View.GONE);

            }else{

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));
                layoutHotlistOption.setVisibility(View.GONE);
                tvHotlistNote.setVisibility(View.VISIBLE);

            }


        }
    }


    public void requestHotlist(String hotlistReason){

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        TransitRequestHotlistRequestModel transitRequestHotlistRequestModel = new TransitRequestHotlistRequestModel();
        transitRequestHotlistRequestModel.setAction("");
        transitRequestHotlistRequestModel.setSId("");
        transitRequestHotlistRequestModel.setCardRefNumber(CardProxyNumber);
        transitRequestHotlistRequestModel.setHotlistResaon(hotlistReason);

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(transitRequestHotlistRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);


        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.transitRequestHotlist(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
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

                        if (responseBaseModel!=null)
                        {
                            if (responseBaseModel.getStatusCode()==200){

                                TransitRequestHotlistResponseModel transitRequestHotlistResponseModel = null;

                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    transitRequestHotlistResponseModel = om1.readValue(jsonString, TransitRequestHotlistResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (transitRequestHotlistResponseModel!=null)
                                {
                                    requestTxnID = transitRequestHotlistResponseModel.getData().getTxnId();
                                    Toast.makeText(getActivity(), ""+transitRequestHotlistResponseModel.getData().getMessage(), Toast.LENGTH_SHORT).show();

                                    if (otpVerificationDialog != null && otpVerificationDialog.isShowing()) {
                                        otpVerificationDialog.dismiss();
                                    }
                                    otpVerificationDialog = new OTPVerificationDialog(CardHotlistFragment.this::onVerifyClick,getActivity(),mobileNumber,CardProxyNumber,"CPRQSTHOTLIST");
                                    //otpVerificationDialog.setCancelable(false);
                                    otpVerificationDialog.show();

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
                            Log.d("MSEF",responseBaseModel.getMessage());
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
            Toast.makeText(getActivity(), getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onVerifyClick(String otp) {

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        TransitProcessHotlistRequestModel transitProcessHotlistRequestModel = new TransitProcessHotlistRequestModel();
        transitProcessHotlistRequestModel.setCardRefNumber(CardProxyNumber);
        transitProcessHotlistRequestModel.setOtp(otp);
        transitProcessHotlistRequestModel.setReason("Lost");
        transitProcessHotlistRequestModel.setSId("");
        transitProcessHotlistRequestModel.setRefTxnId(requestTxnID);

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(transitProcessHotlistRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);


        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.transitProcessHotlist(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
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

                        if (responseBaseModel!=null)
                        {
                            if (responseBaseModel.getStatusCode()==200)
                            {

                                TransitRequestHotlistResponseModel transitRequestHotlistResponseModel = new TransitRequestHotlistResponseModel();
                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    transitRequestHotlistResponseModel = om1.readValue(jsonString, TransitRequestHotlistResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (transitRequestHotlistResponseModel!=null)
                                {

                                    //CommonUtils.showSuccessDialogue(getActivity(),"Your Card has been permanently hotlisted.");
                                    //Toast.makeText(getActivity(), ""+transitRequestHotlistResponseModel.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                    loginResponse.transit.cardDetails.get(cardPosition).setCardStatus("INACTIVE");
                                    currentCardStatus="INACTIVE";
                                    SharedConfig.getInstance(getActivity()).saveLoginResponse(getActivity(),loginResponse);
                                    onPositionChange(cardPosition);
                                    otpVerificationDialog.dismiss();

                                    Intent i = new Intent(getActivity(), PaymentStatusActivity.class);
                                    i.putExtra("status","h");
                                    startActivity(i);
                                }

                            }
                        }


                    }else{
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
            Toast.makeText(getActivity(), getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }





}