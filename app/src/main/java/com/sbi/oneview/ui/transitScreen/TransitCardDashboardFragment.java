package com.sbi.oneview.ui.transitScreen;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.TransitMiniStatementRequestModel;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class TransitCardDashboardFragment extends Fragment implements MyFragmentCallback {


    TextView tvDashboard,tvCurrentDate,tvRecentTransaction,tvQuickAccess,tvMyCards,tvCardDetails;
    //TextView tvTopUpRupee,tvSpendRupee,tvSinceLastTopUp,tvSuccessTxns,tvSinceLastLogin,tvLastStatementGenerated,tvAnalytics;
    MaterialCardView cardCardTopUp,cardResetPin,cardHotList,cardStatement;
    RecyclerView rvRecentTransaction;
    TransitHomeActivity transitHomeActivity;

    Data loginResponse;
    String currentCardStatus;

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
        tvRecentTransaction = view.findViewById(R.id.tvRecentTransaction);
        cardCardTopUp = view.findViewById(R.id.cardCardTopup);
        cardResetPin = view.findViewById(R.id.cardResetPin);
        cardHotList = view.findViewById(R.id.cardHotlist);
        cardStatement = view.findViewById(R.id.cardStatement);
        rvRecentTransaction = view.findViewById(R.id.rvRecentTransaction);

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

        cardResetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transitHomeActivity!=null){
                    transitHomeActivity.drawerItemClick("cardManagement");
                    transitHomeActivity.subMenuClicked(transitHomeActivity.resetpinCard,true);
                    transitHomeActivity.replaceFragment(new ResetPinFragment());
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
            tvCardNumber.setText(loginResponse.getTransit().getCardDetails().get(position).getCardNumber());
            tvCardStatus.setText(loginResponse.getTransit().getCardDetails().get(position).getCardStatus().equals("A") ? "ACTIVE":"BLOCKED");
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


            CardProxyNumber = loginResponse.getTransit().getCardDetails().get(position).getProxyNumber();
            cardPosition = position;

            currentCardStatus = loginResponse.getTransit().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("A")){

                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));

            }else if(currentCardStatus.equals("PHL")){

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }

            //LoadTransitCardMiniStatement(loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber(),loginResponse.getTransit().getCardDetails().get(position).getProductCode());


        }


    }


/*
    public void LoadTransitCardMiniStatement(String proxyNumber,String productCode){
        RequestBaseModel<TransitMiniStatementRequestModel> data = new RequestBaseModel<>();
        TransitMiniStatementRequestModel transitMiniStatementRequestModel = new TransitMiniStatementRequestModel();

        transitMiniStatementRequestModel.setCardRefNumber(proxyNumber);
        transitMiniStatementRequestModel.setSId("");
        transitMiniStatementRequestModel.setProductCode(productCode);

        data.setRequest(transitMiniStatementRequestModel);

        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.transitMiniStatement(getActivity(), transitMiniStatementRequestModel, new NetworkResponseCallback<TransitMiniStatementResponseModel>() {
                @Override
                public void onSuccess(Call<TransitMiniStatementResponseModel> call, Response<TransitMiniStatementResponseModel> response) {

                    if (response.body().getStatusCode()==200){

                        // Create an instance of the adapter
                        TransitRecentTransactionAdapter transitRecentTransactionAdapter = new TransitRecentTransactionAdapter(getActivity(),response.body());
                        // Set the adapter to the RecyclerView
                        rvRecentTransaction.setAdapter(transitRecentTransactionAdapter);
                        // Set layout manager to position the items
                        rvRecentTransaction.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                }

                @Override
                public void onResponseBodyNull(Call<TransitMiniStatementResponseModel> call, Response<TransitMiniStatementResponseModel> response) {

                }

                @Override
                public void onResponseUnsuccessful(Call<TransitMiniStatementResponseModel> call, Response<TransitMiniStatementResponseModel> response) {

                }

                @Override
                public void onFailure(Call<TransitMiniStatementResponseModel> call, Throwable t) {

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
*/
}