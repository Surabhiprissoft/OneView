package com.sbi.oneview.ui.inrPrepaid;

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
import com.sbi.oneview.network.RequestModel.CardMiniStatementRequestModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.LoginWithOtpResponseModel;
import com.sbi.oneview.network.ResponseModel.MiniStatement.CardMiniStatementResponseModel;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.adapters.RecentTransactionAdapter;
import com.sbi.oneview.ui.registration.EnterOtp;
import com.sbi.oneview.ui.registration.LoginActivity;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class InrDashboardFragment extends BaseFragment implements MyFragmentCallback{

    TextView tvDashboard,tvCurrentDate,tvRecentTransaction,tvQuickAccess,tvMyCards,tvCardDetails;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate,tvCardBal,tvChipBal,tvViewAll;
    MaterialCardView cardStatusCard,cardTopUp,cardResetPin,cardLimit,cardStatement;
    RecyclerView rvRecentTransaction;
    InrPrepaidHomeActivity inrPrepaidHomeActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inr_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        initWidget(view);
        clickListener();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getPrepaid().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);


        rvRecentTransaction = view.findViewById(R.id.rvRecentTransaction);


    }



    public void initWidget(View view){
        tvDashboard = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvQuickAccess = view.findViewById(R.id.tvQuickAccess);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        tvRecentTransaction = view.findViewById(R.id.tvRecentTransaction);

        tvCardNumber = view.findViewById(R.id.tvCardNumber);
        tvCRN = view.findViewById(R.id.tvCRNNumber);
        tvCardStatus = view.findViewById(R.id.tvCardStatus);
        tvProductName = view.findViewById(R.id.tvCardProductName);
        tvActDate = view.findViewById(R.id.tvCardActiveDate);
        tvExpDate = view.findViewById(R.id.tvCardExpDate);
        tvCardBal = view.findViewById(R.id.tvCardBalance);
        tvChipBal = view.findViewById(R.id.tvChipBalance);

        tvViewAll = view.findViewById(R.id.tvViewAll);
        cardTopUp = view.findViewById(R.id.cardTopUP);
        cardResetPin = view.findViewById(R.id.cardResetPin);
        cardLimit = view.findViewById(R.id.cardLimit);
        cardStatement = view.findViewById(R.id.cardStatement);

        cardStatusCard = view.findViewById(R.id.cardStatusCard);

        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        tvDashboard.setText("Dashboard");

        CommonUtils.setGradientColor(tvDashboard);
        CommonUtils.setGradientColor(tvQuickAccess);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvRecentTransaction);

        inrPrepaidHomeActivity = (InrPrepaidHomeActivity) getActivity();


    }

    private void clickListener() {

        tvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inrPrepaidHomeActivity!=null){
                    inrPrepaidHomeActivity.drawerItemClick("cardManagement");
                    inrPrepaidHomeActivity.subMenuClicked(inrPrepaidHomeActivity.cardStatementCard,true);
                    inrPrepaidHomeActivity.replaceFragment(new InrCardStatementFragment());
                }
            }
        });
        cardTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (inrPrepaidHomeActivity!=null){
                    inrPrepaidHomeActivity.drawerItemClick("cardManagement");
                    inrPrepaidHomeActivity.subMenuClicked(inrPrepaidHomeActivity.cardTopUpCard,true);
                    inrPrepaidHomeActivity.replaceFragment(new TopUpFragment());
                }*/
            }
        });

        cardResetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inrPrepaidHomeActivity!=null){
                    inrPrepaidHomeActivity.drawerItemClick("cardManagement");
                    inrPrepaidHomeActivity.subMenuClicked(inrPrepaidHomeActivity.resetpinCard,true);
                    inrPrepaidHomeActivity.replaceFragment(new InrResetPinFragment());
                }
            }
        });


        cardStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inrPrepaidHomeActivity!=null){
                    inrPrepaidHomeActivity.drawerItemClick("cardManagement");
                    inrPrepaidHomeActivity.subMenuClicked(inrPrepaidHomeActivity.cardStatementCard,true);
                    inrPrepaidHomeActivity.replaceFragment(new InrCardStatementFragment());
                }
            }
        });

        cardLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inrPrepaidHomeActivity!=null){
                    inrPrepaidHomeActivity.drawerItemClick("cardManagement");
                    inrPrepaidHomeActivity.subMenuClicked(inrPrepaidHomeActivity.cardLimitCard,true);
                    inrPrepaidHomeActivity.replaceFragment(new InrCardLimitFragment());
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

           loadCardMiniStatement(loginResponse.getPrepaid().getCardDetails().get(position).getProxyNumber(),loginResponse.getToken());
        }


    }


    public void loadCardMiniStatement(String proxyNumber,String token){

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        CardMiniStatementRequestModel cardMiniStatementRequestModel = new CardMiniStatementRequestModel();
        cardMiniStatementRequestModel.setProxyNumber(proxyNumber);
        cardMiniStatementRequestModel.setSId("");
        cardMiniStatementRequestModel.setType("");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(cardMiniStatementRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);


        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.cardMiniStatement(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
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

                            if (responseBaseModel.getStatusCode()==200) {

                                CardMiniStatementResponseModel cardMiniStatementResponseModel = null;
                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    cardMiniStatementResponseModel = om1.readValue(jsonString, CardMiniStatementResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (cardMiniStatementResponseModel!=null){
                                    if (cardMiniStatementResponseModel.getStatusCode()==200){
                                        // Create an instance of the adapter
                                        RecentTransactionAdapter adapterRecentTransaction = new RecentTransactionAdapter(getActivity(),cardMiniStatementResponseModel.getData());
                                        // Set the adapter to the RecyclerView
                                        rvRecentTransaction.setAdapter(adapterRecentTransaction);
                                        // Set layout manager to position the items
                                        rvRecentTransaction.setLayoutManager(new LinearLayoutManager(getActivity()));

                                    }
                                }

                            }

                        }else{
                            Toast.makeText(getActivity(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
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
        else{
            Toast.makeText(getActivity(), getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }
}




