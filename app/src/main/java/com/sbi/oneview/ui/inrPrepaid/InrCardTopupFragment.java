package com.sbi.oneview.ui.inrPrepaid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.base.BaseFragment;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.InrTopupRequestModel;
import com.sbi.oneview.network.ResponseModel.InrTopup.InrTopupResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.ui.WebView.TopupWebViewActivity;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class InrCardTopupFragment extends BaseFragment implements MyFragmentCallback{

    TextView tvContactUs,tvCurrentDate,tvTopUp,tvMyCards,tvCardDetails;
    private LinearLayout container;
    MaterialCardView card5000,card10000,card20000,card50000;
    EditText etMoney;
    LinearLayout layoutChooseMoney;
    MaterialButton btnProceedWithMoney;

    Data loginResponse;
    String currentCardStatus,token;

    String CardProxyNumber;
    int cardPosition;
    TextView tvSpendLimit;
    LinearLayout layoutCardStatus,layoutSpendLimitController;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate,tvCardBal,tvChipBal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inr_card_topup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initWidget(view);
        clickListener();
    }

    public void initWidget(View view){
        tvContactUs = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvTopUp = view.findViewById(R.id.tvTopUp);

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

        card5000 = view.findViewById(R.id.card500);
        card10000 = view.findViewById(R.id.card1000);
        card20000 = view.findViewById(R.id.card2000);
        card50000 = view.findViewById(R.id.card5000);
        etMoney = view.findViewById(R.id.etMoney);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        layoutChooseMoney = view.findViewById(R.id.layoutChooseMoney);
        btnProceedWithMoney = view.findViewById(R.id.btnProceedWithMoney);


        CommonUtils.setGradientColor(tvTopUp);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);

        tvContactUs.setText("TopUp");
        CommonUtils.setGradientColor(tvContactUs);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getPrepaid().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }


    public void clickListener(){
        card5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText(getResources().getString(R.string._5000));
            }
        });

        card10000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText(getResources().getString(R.string._10000));
            }
        });

        card20000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText(getResources().getString(R.string._20000));
            }
        });

        card50000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText(getResources().getString(R.string._50000));
            }
        });



        btnProceedWithMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etMoney.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please amount to proceed with top up", Toast.LENGTH_SHORT).show();
                }else{
                    initiateTopup("100");
                    Toast.makeText(getActivity(), "Work in Progress, please try again later", Toast.LENGTH_SHORT).show();
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
               /* tvCardStatusNote.setVisibility(View.GONE);
                layoutSpendLimitController.setVisibility(View.VISIBLE);*/

            }else if(currentCardStatus.equals("BLOCKED")){

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));
                /*tvCardStatusNote.setVisibility(View.VISIBLE);
                tvCardStatusNote.setText("Your Card has been blocked, please un-block it to use spend limit features.");
                layoutSpendLimitController.setVisibility(View.GONE);*/

            }else if (currentCardStatus.equals("INACTIVE")){

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));
                /*tvCardStatusNote.setVisibility(View.VISIBLE);
                tvCardStatusNote.setText("Your Card has been permanantly blocked, please visit nearest branch for issuance of new card.");
                layoutSpendLimitController.setVisibility(View.GONE);*/

            }


        }
    }



    public void initiateTopup(String amount)
    {
        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        InrTopupRequestModel inrTopupRequestModel = new InrTopupRequestModel();
        inrTopupRequestModel.setProxyNumber(CardProxyNumber);
        inrTopupRequestModel.setTopUpAmount(amount);
        inrTopupRequestModel.setRemark("Epaytxn");
        inrTopupRequestModel.setSId("");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(inrTopupRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);

        if (NetworkUtils.isNetworkConnected(getActivity()))
        {

            APIRequests.inrCardTopup(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
                @Override
                public void onSuccess(Call<String> call, Response<String> response) {

                    if (response.isSuccessful())
                    {
                        String encryptedResponse = response.body();
                        encryptedResponse = encryptedResponse.replaceAll("[\\\\\"]", "");

                        ObjectMapper om = new ObjectMapper();
                        ResponseBaseModel responseBaseModel = null;
                        try {
                            JsonNode node = (JsonNode) CipherEncryption.decryptMessage2(encryptedResponse, randomKey);

                            responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                        }catch (Exception e)
                        {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }

                        if (responseBaseModel!=null) {
                            if (responseBaseModel.getStatusCode() == 200) {

                                Log.d("RESPONSE",""+responseBaseModel.getData().toString());

                                InrTopupResponseModel inrTopupResponseModel = null;
                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    inrTopupResponseModel = om1.readValue(jsonString, InrTopupResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (inrTopupResponseModel!=null)
                                {

                                    Log.d("MSFG",""+inrTopupResponseModel.getData().getData());
                                    Log.d("MSFG",""+inrTopupResponseModel.getData().getValue());
                                    Log.d("URL",""+inrTopupResponseModel.getData().getUrl());

                                    String epayUrl = inrTopupResponseModel.getData().getUrl();
                                    String encryptTrans = inrTopupResponseModel.getData().getData();
                                    String merchIdVal = inrTopupResponseModel.getData().getValue();

                                    //encryptTrans = encryptTrans.substring(0, encryptTrans.length() - 1);
                                    // Start WebViewActivity with the URL and POST data
                                    Intent intent = new Intent(getActivity(), TopupWebViewActivity.class);
                                    intent.putExtra("epayUrl", epayUrl);
                                    intent.putExtra("encryptTrans", encryptTrans);
                                    intent.putExtra("merchIdVal", merchIdVal);
                                    startActivity(intent);
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
            hideLoading();
            Toast.makeText(getActivity(), getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }


    }
}