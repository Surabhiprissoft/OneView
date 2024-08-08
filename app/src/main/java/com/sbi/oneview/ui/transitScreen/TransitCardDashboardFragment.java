package com.sbi.oneview.ui.transitScreen;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.base.BaseFragment;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.TransitMiniStatementRequestModel;
import com.sbi.oneview.network.ResponseModel.HotlistCard.CardHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.network.ResponseModel.TransitMiniStatement.TransitMiniStatementResponseModel;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.adapters.Transit.TransitRecentTransactionAdapter;
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


public class TransitCardDashboardFragment extends BaseFragment implements MyFragmentCallback {


    TextView tvDashboard,tvCurrentDate,tvRecentTransaction,tvQuickAccess,tvMyCards,tvCardDetails,tvViewAll,tvNoData;
    //TextView tvTopUpRupee,tvSpendRupee,tvSinceLastTopUp,tvSuccessTxns,tvSinceLastLogin,tvLastStatementGenerated,tvAnalytics;
    MaterialCardView cardCardTopUp,myProfileCard,cardHotList,cardStatement;
    RecyclerView rvRecentTransaction;
    TransitHomeActivity transitHomeActivity;
    ProgressBar pbMiniStatement;

    Data loginResponse;
    String currentCardStatus,token;

    String CardProxyNumber;
    int cardPosition;
    TextView tvSpendLimit;
    LinearLayout layoutCardStatus,layoutSpendLimitController;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate,tvCardBal,tvChipBal,tvCardBalanceSync,tvChipBalanceSync;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transit_card_dashboard, container, false);



    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidget(view);
        clickListener();
    }

    public void initWidget(View view){
        tvDashboard = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvQuickAccess = view.findViewById(R.id.tvQuickAccess);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        tvViewAll = view.findViewById(R.id.tvViewAll);
        tvRecentTransaction = view.findViewById(R.id.tvRecentTransaction);
        cardCardTopUp = view.findViewById(R.id.cardCardTopup);
        myProfileCard = view.findViewById(R.id.myProfileCard);
        cardHotList = view.findViewById(R.id.cardHotlist);
        cardStatement = view.findViewById(R.id.cardStatement);
        rvRecentTransaction = view.findViewById(R.id.rvRecentTransaction);

        pbMiniStatement = view.findViewById(R.id.pbMiniStatement);

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
        tvNoData = view.findViewById(R.id.tvNoData);
        tvCardBalanceSync = view.findViewById(R.id.tvCardBalanceSync);
        tvChipBalanceSync = view.findViewById(R.id.tvChipBalanceSync);


        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        tvDashboard.setText("Dashboard");
        transitHomeActivity = (TransitHomeActivity) getActivity();

        CommonUtils.setGradientColor(tvDashboard);
        CommonUtils.setGradientColor(tvQuickAccess);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvRecentTransaction);



        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getTransit().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);




    }

    public void clickListener(){

        tvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transitHomeActivity!=null){
                    transitHomeActivity.drawerItemClick("cardManagement");
                    transitHomeActivity.subMenuClicked(transitHomeActivity.cardStatementCard,true);
                    transitHomeActivity.replaceFragment(new CardStatementFragment());
                }
            }
        });


        cardCardTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transitHomeActivity!=null){
                    transitHomeActivity.drawerItemClick("cardManagement");
                    transitHomeActivity.subMenuClicked(transitHomeActivity.cardTopUpCard,true);
                    transitHomeActivity.replaceFragment(new TopUpFragment());
                }
            }
        });

        myProfileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transitHomeActivity!=null){
                    transitHomeActivity.drawerItemClick("cardManagement");
                    transitHomeActivity.subMenuClicked(transitHomeActivity.myProfileCardView,true);
                    transitHomeActivity.replaceFragment(new MyProfileFragment());
                }
            }
        });


        cardHotList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transitHomeActivity!=null){
                    transitHomeActivity.drawerItemClick("cardManagement");
                    transitHomeActivity.subMenuClicked(transitHomeActivity.cardhotlistCard,true);
                    transitHomeActivity.replaceFragment(new CardHotlistFragment());
                }
            }
        });


        cardStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transitHomeActivity!=null){
                    transitHomeActivity.drawerItemClick("cardManagement");
                    transitHomeActivity.subMenuClicked(transitHomeActivity.cardStatementCard,true);
                    transitHomeActivity.replaceFragment(new CardStatementFragment());
                }
            }
        });
    }


    @Override
    public void onPositionChange(int position) {

        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());
        if (loginResponse!=null){

            tvCRN.setText(loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber());
            tvCardNumber.setText("XXXX XXXX XXXX "+loginResponse.getTransit().getCardDetails().get(position).getCardNumber());
            tvCardStatus.setText(loginResponse.getTransit().getCardDetails().get(position).getCardStatus().equals("A") ? "ACTIVE":"INACTIVE");
            tvProductName.setText(loginResponse.getTransit().getCardDetails().get(position).getProductName());
            tvActDate.setText(loginResponse.getTransit().getCardDetails().get(position).getActivityDate().substring(0,2) +" / "+ loginResponse.getTransit().getCardDetails().get(position).getActivityDate().substring(2));
            tvExpDate.setText(loginResponse.getTransit().getCardDetails().get(position).getExpDate().substring(0,2)+" / "+loginResponse.getTransit().getCardDetails().get(position).getExpDate().substring(2));
            tvCardBalanceSync.setText("[As on "+loginResponse.getTransit().getCardDetails().get(position).getLastSyncPersonal() +"]");
            tvChipBalanceSync.setText("[As on "+loginResponse.getTransit().getCardDetails().get(position).getLastSyncTransit()+"]");
            tvCardBal.setText(getResources().getString(R.string.Rs)+loginResponse.getTransit().getCardDetails().get(position).getWallBalPersonal());
            tvChipBal.setText(getResources().getString(R.string.Rs)+"0");


            CardProxyNumber = loginResponse.getTransit().getCardDetails().get(position).getProxyNumber();
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

            LoadTransitCardMiniStatement(loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber(),loginResponse.getTransit().getCardDetails().get(position).getProductCode(),token);


        }


    }


    public void LoadTransitCardMiniStatement(String proxyNumber,String productCode,String token){

        //showLoading();

        tvNoData.setVisibility(View.GONE);
        pbMiniStatement.setVisibility(View.VISIBLE);
        // Create an instance of the adapter
        TransitRecentTransactionAdapter transitRecentTransactionAdapter1 = null;
        // Set the adapter to the RecyclerView
        rvRecentTransaction.setAdapter(transitRecentTransactionAdapter1);
        // Set layout manager to position the items
        rvRecentTransaction.setLayoutManager(new LinearLayoutManager(getActivity()));


        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        TransitMiniStatementRequestModel transitMiniStatementRequestModel = new TransitMiniStatementRequestModel();
        transitMiniStatementRequestModel.setCardRefNumber(proxyNumber);
        transitMiniStatementRequestModel.setSId("");
        transitMiniStatementRequestModel.setProductCode(productCode);

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(transitMiniStatementRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);


        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.transitMiniStatement(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
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

                        if (responseBaseModel!=null){
                            if (responseBaseModel.getStatusCode()==200){

                                TransitMiniStatementResponseModel transitMiniStatementResponseModel= null;
                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    transitMiniStatementResponseModel = om1.readValue(jsonString, TransitMiniStatementResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if(transitMiniStatementResponseModel!=null){
                                    if (transitMiniStatementResponseModel.getStatusCode()==200)
                                    {
                                        if (transitMiniStatementResponseModel.getData().isEmpty()){
                                            tvNoData.setVisibility(View.VISIBLE);
                                            pbMiniStatement.setVisibility(View.GONE);
                                        }else{
                                            tvNoData.setVisibility(View.GONE);
                                            pbMiniStatement.setVisibility(View.GONE);
                                            // Create an instance of the adapter
                                            TransitRecentTransactionAdapter transitRecentTransactionAdapter = new TransitRecentTransactionAdapter(getActivity(),transitMiniStatementResponseModel);
                                            // Set the adapter to the RecyclerView
                                            rvRecentTransaction.setAdapter(transitRecentTransactionAdapter);
                                            // Set layout manager to position the items
                                            rvRecentTransaction.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        }

                                    }
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

                    //hideLoading();
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
        }
        else{
            Toast.makeText(getActivity(), getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }
    }
}