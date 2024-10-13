package com.sbi.oneview.ui.transitScreen;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.base.BaseFragment;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.TopupWebPageRequestModel;
import com.sbi.oneview.network.RequestModel.Transit.TransitInitiateTopupRequestModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.network.ResponseModel.TransitRequestHotlist.TransitRequestHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.TransitTopup.TransitInitiateTopupResponseModel;
import com.sbi.oneview.ui.WebView.ResetPinWebViewActivity;
import com.sbi.oneview.ui.WebView.TopupWebViewActivity;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    Double eligibleBalance;
    String CardProxyNumber,token;
    int cardPosition;
    TextView tvSpendLimit,tvEligibleBal,tvTopupNote;
    LinearLayout layoutCardStatus,layoutSpendLimitController,layoutTopupOption;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate,tvCardBal,tvChipBal,tvCardBalanceSync,tvChipBalanceSync;

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




        CourouselAdapter adapter;

        if (getArguments()!=null)
        {
            int pos = getArguments().getInt("pos");
            adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView,pos);
        }
        else{
            adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView,null);
        }

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
        tvEligibleBal = view.findViewById(R.id.tvEligibleBal);
        tvCardBalanceSync = view.findViewById(R.id.tvCardBalanceSync);
        tvChipBalanceSync = view.findViewById(R.id.tvChipBalanceSync);

        layoutTopupOption = view.findViewById(R.id.layoutTopupOption);
        tvTopupNote  =view.findViewById(R.id.tvTopupNote);

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
                if (eligibleBalance>=500.0) {
                    etMoney.setText("500");
                }else{
                    etMoney.setText("");
                    Toast.makeText(getActivity(), "You are exceeding your eligible balance", Toast.LENGTH_SHORT).show();
                }
            }
        });

        card1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eligibleBalance>=1000.0) {
                    etMoney.setText("1000");
                }else{
                    etMoney.setText("");
                    Toast.makeText(getActivity(), "You are exceeding your eligible balance", Toast.LENGTH_SHORT).show();
                }

            }
        });

        card2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eligibleBalance>=2000.0) {
                    etMoney.setText("2000");
                }else{
                    etMoney.setText("");
                    Toast.makeText(getActivity(), "You are exceeding your eligible balance", Toast.LENGTH_SHORT).show();
                }
            }
        });

        card5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eligibleBalance>=5000.0) {
                    etMoney.setText("5000");
                }else{
                    etMoney.setText("");
                    Toast.makeText(getActivity(), "You are exceeding your eligible balance", Toast.LENGTH_SHORT).show();
                }
            }
        });

        card10000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eligibleBalance>=10000.0) {
                    etMoney.setText("10000");
                }else{
                    etMoney.setText("");
                    Toast.makeText(getActivity(), "You are exceeding your eligible balance", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnProceedWithMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMoney.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter amount to proceed with top up", Toast.LENGTH_SHORT).show();
                }else{
                    int topUpAmount = Integer.parseInt(etMoney.getText().toString());

                    if (topUpAmount>=100) {
                        initiatTopUP(topUpAmount);
                    }else{
                        Toast.makeText(getActivity(), "Minimum top up amount is 100", Toast.LENGTH_SHORT).show();
                    }
                   // Toast.makeText(getActivity(), "Work in Progress, please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    public void initiatTopUP(int amount)
    {
        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        TransitInitiateTopupRequestModel transitInitiateTopupRequestModel = new TransitInitiateTopupRequestModel();
        transitInitiateTopupRequestModel.setAmount(amount);
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
                        encryptedResponse = encryptedResponse.replaceAll("[\\\\\"]", "");

                        String res = (String) CipherEncryption.decryptMessage(encryptedResponse,randomKey);


                        String strResponse = convertToJson(res);
                        Log.d("DECRYPTED RESPONSE",""+strResponse);

                        /*String res = (String) CipherEncryption.decryptMessage(encryptedResponse,randomKey);


                        String strResponse = convertToJson(res);
                        Log.d("DECRYPTED RESPONSE",""+res);
                        Log.d("RESPONSE",""+strResponse);

                        String jsonString = "{"
                                + "\"statusCode\": 200,"
                                + "\"data\": {"
                                + "\"url\": \"https://test.sbiepay.sbi/secure/AggregatorHostedListener\","
                                + "\"txndata\": \"zm3x1mTU=\","
                                + "\"merchantCode\": \"1000001\","
                                + "\"status\": \"A\","
                                + "\"message\": \"The Card is eligible for TopUp ..!!\""
                                + "},"
                                + "\"message\": \"Success\""
                                + "}";


                        try {
                            String cleanedJsonString = String.valueOf(cleanJSON(strResponse));
                            String resString = cleanedJsonString.replace("\\", "");
                            Log.d("CLEAN STRING",""+resString);


                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode rootNode = objectMapper.readTree(resString);

                            // Access the fields
                            JsonNode dataNode = rootNode.path("data");
                            String url = dataNode.path("url").asText();
                            String txndata = dataNode.path("txndata").asText();
                            String merchantCode = dataNode.path("merchantCode").asText();


                            Intent intent = new Intent(getActivity(), TopupWebViewActivity.class);
                            intent.putExtra("epayUrl", url);
                            intent.putExtra("encryptTrans", txndata);
                            intent.putExtra("merchIdVal", merchantCode);
                            startActivity(intent);




                        } catch (JsonMappingException e) {
                            Log.d("EXCEPTION MSG",""+e.getLocalizedMessage());

                        } catch (JsonProcessingException e) {
                            Log.d("EXCEPTION MSG",""+e.getLocalizedMessage());

                        }
*/




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
                                    Log.d("URL",""+transitInitiateTopupResponseModel.getData().getUrl());

                                    /*String url = transitInitiateTopupResponseModel.getData().getUrl();
                                    String merchantCode = transitInitiateTopupResponseModel.getData().getMerchantCode();
                                    String data = transitInitiateTopupResponseModel.getData().getTxndata();
                                    String postData = "txndata=" + data + "&merchantCode=" + merchantCode;

                                    // Start WebViewActivity with the URL and POST data
                                    Intent intent = new Intent(getActivity(), ResetPinWebViewActivity.class);
                                    intent.putExtra("url", url);
                                    intent.putExtra("postData", postData);
                                    startActivity(intent);*/

                                    String epayUrl = transitInitiateTopupResponseModel.getData().getUrl();
                                    String encryptTrans = transitInitiateTopupResponseModel.getData().getTxndata();
                                    String merchIdVal = transitInitiateTopupResponseModel.getData().getMerchantCode();

                                    //encryptTrans = encryptTrans.substring(0, encryptTrans.length() - 1);
                                    // Start WebViewActivity with the URL and POST data
                                    /*Intent intent = new Intent(getActivity(), TopupWebViewActivity.class);
                                    intent.putExtra("epayUrl", epayUrl);
                                    intent.putExtra("encryptTrans", encryptTrans);
                                    intent.putExtra("merchIdVal", merchIdVal);
                                    startActivity(intent);*/

                                    callingTopupWebpage(encryptTrans,merchIdVal,amount);

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


    public void callingTopupWebpage(String txnData,String merchndId,int amount){


        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        TopupWebPageRequestModel topupWebPageRequestModel = new TopupWebPageRequestModel();
        topupWebPageRequestModel.setEncData(txnData);
        topupWebPageRequestModel.setMerchIdVal(merchndId);

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(topupWebPageRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);

        hideLoading();

        if (encryptedMsg!=null)
        {

            Intent intent = new Intent(getActivity(), TopupWebViewActivity.class);
            intent.putExtra("txnData", encryptedMsg);
            intent.putExtra("accessKey", randomKey);
            intent.putExtra("amount", amount);
            startActivity(intent);

        }


    }

    @Override
    public void onPositionChange(int position) {
        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());
        if (loginResponse!=null){

            tvCRN.setText(loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber());
            tvCardNumber.setText("XXXX XXXX XXXX "+loginResponse.getTransit().getCardDetails().get(position).getCardNumber());
            tvCardStatus.setText(loginResponse.getTransit().getCardDetails().get(position).getCardStatus().equals("A") ? "ACTIVE":"INACTIVE");
            tvProductName.setText(loginResponse.getTransit().getCardDetails().get(position).getProductName());
          //  tvCardBalanceSync.setText("[As on "+loginResponse.getTransit().getCardDetails().get(position).getLastSyncPersonal() +"]");
          //  tvChipBalanceSync.setText("[As on "+loginResponse.getTransit().getCardDetails().get(position).getLastSyncTransit()+"]");
            tvActDate.setText(loginResponse.getTransit().getCardDetails().get(position).getActivityDate().substring(0,2) +" / "+ loginResponse.getTransit().getCardDetails().get(position).getActivityDate().substring(2));
            tvExpDate.setText(loginResponse.getTransit().getCardDetails().get(position).getExpDate().substring(0,2)+" / "+loginResponse.getTransit().getCardDetails().get(position).getExpDate().substring(2));

            tvCardBal.setText(getResources().getString(R.string.Rs)+loginResponse.getTransit().getCardDetails().get(position).getWallBalPersonal());
            tvChipBal.setText(getResources().getString(R.string.Rs)+loginResponse.getTransit().getCardDetails().get(position).getWallBalTransit());
            eligibleBalance = 10000-(Double.parseDouble(loginResponse.getTransit().getCardDetails().get(position).getWallBalPersonal()));
            tvEligibleBal.setText(""+eligibleBalance.toString());

            String cardBalanceSync = loginResponse.getTransit().getCardDetails().get(position).getLastSyncPersonal();
            String chipBalanceSync = loginResponse.getTransit().getCardDetails().get(position).getLastSyncTransit();

            if (cardBalanceSync!=null) {
                if (cardBalanceSync.length() != 14) {
                    tvCardBalanceSync.setText("[As on " + cardBalanceSync + "]");
                } else {
                    String day = cardBalanceSync.substring(0, 2);
                    String month = cardBalanceSync.substring(2, 4);
                    String year = cardBalanceSync.substring(4, 8);
                    String hour = cardBalanceSync.substring(8, 10);
                    String minute = cardBalanceSync.substring(10, 12);
                    String second = cardBalanceSync.substring(12, 14);

                    tvCardBalanceSync.setText("[As on " + day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second + "]");
                }
            }

            if (chipBalanceSync!=null) {
                if (chipBalanceSync.length() != 14) {
                    tvChipBalanceSync.setText("[As on " + chipBalanceSync + "]");
                } else {
                    String day = chipBalanceSync.substring(0, 2);
                    String month = chipBalanceSync.substring(2, 4);
                    String year = chipBalanceSync.substring(4, 8);
                    String hour = chipBalanceSync.substring(8, 10);
                    String minute = chipBalanceSync.substring(10, 12);
                    String second = chipBalanceSync.substring(12, 14);

                    tvChipBalanceSync.setText("[As on " + day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second + "]");
                }
            }

            CardProxyNumber = loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber();
            cardPosition = position;
            token = loginResponse.getToken();

            currentCardStatus = loginResponse.getTransit().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("A") || currentCardStatus.equals("ACTIVE")){

                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));
                tvTopupNote.setVisibility(View.GONE);
                layoutTopupOption.setVisibility(View.VISIBLE);

            }else{
                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));
                tvTopupNote.setVisibility(View.VISIBLE);
                layoutTopupOption.setVisibility(View.GONE);
            }

            //setTopUpCardColor();


        }
    }

    public void setTopUpCardColor()
    {
        if (eligibleBalance>=500.0)
        {
            card500.setCardBackgroundColor(getActivity().getColor(R.color.background_card));
        }else{
            card500.setCardBackgroundColor(getActivity().getColor(R.color.background_dim));
        }

        if (eligibleBalance>=1000.0)
        {
            card1000.setCardBackgroundColor(getActivity().getColor(R.color.background_card));
        }else{
            card1000.setCardBackgroundColor(getActivity().getColor(R.color.background_dim));
        }

        if (eligibleBalance>=2000.0)
        {
            card2000.setCardBackgroundColor(getActivity().getColor(R.color.background_card));
        }else{
            card2000.setCardBackgroundColor(getActivity().getColor(R.color.background_dim));
        }

        if (eligibleBalance>=5000.0)
        {
            card5000.setCardBackgroundColor(getActivity().getColor(R.color.background_card));
        }else{
            card5000.setCardBackgroundColor(getActivity().getColor(R.color.background_dim));
        }

        if (eligibleBalance>=10000.0)
        {
            card10000.setCardBackgroundColor(getActivity().getColor(R.color.background_card));
        }else{
            card10000.setCardBackgroundColor(getActivity().getColor(R.color.background_dim));
        }
    }

    public static String convertToJson(String input) {

        String json = input.replaceAll("\\b(\\w+)\\s*=\\s*", "\"$1\": ");
        json = json.replaceAll("(?<=\": )(?!\\[|\\{|(?:(?:null)[,}\n]))([^,}\n]*)", "\"$1\"");
        return json;
    }

    public static String cleanJSON(String jsonString) {
        // Regular expression to match the pattern: any text followed by ": ""=""
        Pattern pattern = Pattern.compile(":[\\s\"]*\\\"\\\"\\s*=\\\"\\\"$");
        Matcher matcher = pattern.matcher(jsonString);
        return matcher.replaceAll("");
    }
}