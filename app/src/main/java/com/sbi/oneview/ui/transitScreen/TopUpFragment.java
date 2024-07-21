package com.sbi.oneview.ui.transitScreen;

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
import com.sbi.oneview.network.RequestModel.Transit.TransitInitiateTopupRequestModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.network.ResponseModel.TransitRequestHotlist.TransitRequestHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.TransitTopup.TransitInitiateTopupResponseModel;
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

public class TopUpFragment extends BaseFragment implements MyFragmentCallback {

    TextView tvContactUs,tvCurrentDate,tvTopUp,tvMyCards,tvCardDetails;
    private LinearLayout container;
    MaterialCardView card500,card1000,card2000,card5000,card10000;
    EditText etMoney;
    LinearLayout layoutChooseMoney;
    MaterialButton btnProceedWithMoney;

    Data loginResponse;
    String currentCardStatus;

    String CardProxyNumber,token;
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
        return inflater.inflate(R.layout.fragment_top_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initWidget(view);
        clickListener();


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getTransit().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);


    }

    public void initWidget(View view){
        tvContactUs = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvTopUp = view.findViewById(R.id.tvTopUp);
        card500 = view.findViewById(R.id.card500);
        card1000 = view.findViewById(R.id.card1000);
        card2000 = view.findViewById(R.id.card2000);
        card5000 = view.findViewById(R.id.card5000);
        card10000 = view.findViewById(R.id.card10000);
        etMoney = view.findViewById(R.id.etMoney);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        layoutChooseMoney = view.findViewById(R.id.layoutChooseMoney);
        btnProceedWithMoney = view.findViewById(R.id.btnProceedWithMoney);

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

        /*container = view.findViewById(R.id.container);
        addTextViews();*/

        CommonUtils.setGradientColor(tvTopUp);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);

        tvContactUs.setText("TopUp");
        CommonUtils.setGradientColor(tvContactUs);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());

    }

    public void clickListener(){
        card500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("500");
            }
        });

        card1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("1000");
            }
        });

        card2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("2000");
            }
        });

        card5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("5000");
            }
        });

        card10000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("10000");
            }
        });


        btnProceedWithMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMoney.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please amount to proceed with top up", Toast.LENGTH_SHORT).show();
                }else{
                    initiatTopUP();
                    Toast.makeText(getActivity(), "Work in Progress, please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addTextViews() {
        String[] texts = {"Text 1", "Text 2", "Text 3", "Text 4", "Text 5","Text 1", "Text 2", "Text 3", "Text 4", "Text 5","Text 1", "Text 2", "Text 3", "Text 4", "Text 5"};

        for (String text : texts) {
            TextView textView = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(8, 8, 8, 8);
            textView.setLayoutParams(layoutParams);
            textView.setText(text);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.BLUE);

            container.addView(textView);
        }
    }

    public void initiatTopUP()
    {
        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        TransitInitiateTopupRequestModel transitInitiateTopupRequestModel = new TransitInitiateTopupRequestModel();
        transitInitiateTopupRequestModel.setAmount(100);
        transitInitiateTopupRequestModel.setCardRefNumber(CardProxyNumber);
        transitInitiateTopupRequestModel.setSId("1");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(transitInitiateTopupRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);

        if (NetworkUtils.isNetworkConnected(getActivity()))
        {
            APIRequests.transitInitiateTopUp(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
                @Override
                public void onSuccess(Call<String> call, Response<String> response) {
                    if (response.isSuccessful())
                    {
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

                                TransitInitiateTopupResponseModel transitInitiateTopupResponseModel = null;

                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    transitInitiateTopupResponseModel = om1.readValue(jsonString, TransitInitiateTopupResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (transitInitiateTopupResponseModel!=null)
                                {
                                    Log.d("MSFG",""+transitInitiateTopupResponseModel.getData().getTxndata());
                                    Log.d("MSFG",""+transitInitiateTopupResponseModel.getData().getMerchantCode());

                                    String url = transitInitiateTopupResponseModel.getData().getUrl();
                                    String merchantCode = transitInitiateTopupResponseModel.getData().getMerchantCode();
                                    String data = transitInitiateTopupResponseModel.getData().getTxndata();
                                    String postData = "txndata=" + data + "&merchantCode=" + merchantCode;

                                    // Start WebViewActivity with the URL and POST data
                                    Intent intent = new Intent(getActivity(), ResetPinWebViewActivity.class);
                                    intent.putExtra("url", url);
                                    intent.putExtra("postData", postData);
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
        }

    }

    @Override
    public void onPositionChange(int position) {
        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());
        if (loginResponse!=null){

            tvCRN.setText(loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber());
            tvCardNumber.setText(loginResponse.getTransit().getCardDetails().get(position).getCardNumber());
            tvCardStatus.setText(loginResponse.getTransit().getCardDetails().get(position).getCardStatus().equals("A") ? "ACTIVE":"INACTIVE");
            tvProductName.setText(loginResponse.getTransit().getCardDetails().get(position).getProductName());
            tvActDate.setText(loginResponse.getTransit().getCardDetails().get(position).getActivityDate().substring(0,2) +" / "+ loginResponse.getTransit().getCardDetails().get(position).getActivityDate().substring(2));
            tvExpDate.setText(loginResponse.getTransit().getCardDetails().get(position).getExpDate().substring(0,2)+" / "+loginResponse.getTransit().getCardDetails().get(position).getExpDate().substring(2));


           /* for(int i=0;i<loginResponse.getTransit().getCardDetails().get(position).getCardAccountDetails().size();i++){
                if (loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().get(i).getAccountType().equals("Online")){
                    tvCardBal.setText(getResources().getString(R.string.Rs)+""+loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().get(i).getAccountBalance());
                }
                else{
                    tvChipBal.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().get(i).getAccountBalance());
                }
            }*/
            tvCardBal.setText(getResources().getString(R.string.Rs)+"0");
            tvChipBal.setText(getResources().getString(R.string.Rs)+"0");


            CardProxyNumber = loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber();
            cardPosition = position;
            token = loginResponse.getToken();

            currentCardStatus = loginResponse.getTransit().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("A")){

                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));

            }else if(currentCardStatus.equals("PHL")){

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }


        }
    }
}