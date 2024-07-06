package com.sbi.oneview.ui.FtcScreen;

import android.animation.ObjectAnimator;
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
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.base.BaseFragment;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.InrCardStatementRequestModel;
import com.sbi.oneview.network.ResponseModel.InrCardStatement.InrCardStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.adapters.TransactionStatementAdapter;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class FtcCardStatementFragment extends BaseFragment implements MyFragmentCallback {


    TextView tvCardStatement,tvCurrentDate,tvTransaction,tvMyCards,tvCardDetails;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate;
    FtcHomeActivity ftcHomeActivity;
    LinearLayout layoutCardStatus,layoutSpendLimitController;
    MaterialCardView cardStartDate,cardEndDate;
    TextView tvStartDate,tvEndDate;
    ImageView imgSearchIcon;
    RecyclerView rvTransactionStatement;
    String CardProxyNumber;
    String currentCardStatus;
    int cardPosition;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ftc_card_statement, container, false);
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



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }

    public void initWidget(View view){
        tvCardStatement = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        tvTransaction = view.findViewById(R.id.tvTransaction);
        layoutCardStatus = view.findViewById(R.id.layoutCardStatus);


        tvCardNumber = view.findViewById(R.id.tvCardNumber);
        tvCRN = view.findViewById(R.id.tvCRNNumber);
        tvCardStatus = view.findViewById(R.id.tvCardStatus);
        tvProductName = view.findViewById(R.id.tvCardProductName);
        tvActDate = view.findViewById(R.id.tvCardActiveDate);
        tvExpDate = view.findViewById(R.id.tvCardExpDate);

        cardStartDate = view.findViewById(R.id.cardStartDate);
        cardEndDate = view.findViewById(R.id.cardEndDate);
        tvStartDate = view.findViewById(R.id.tvStartDate);
        tvEndDate = view.findViewById(R.id.tvEndDate);
        imgSearchIcon = view.findViewById(R.id.imgSearchIcon);
        rvTransactionStatement = view.findViewById(R.id.rvTransactionStatement);

        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        tvCardStatement.setText("Card Statement");

        CommonUtils.setGradientColor(tvCardStatement);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvTransaction);

        ftcHomeActivity = (FtcHomeActivity) getActivity();



    }

    public void clickListener(){

        cardStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.showDatePickerDialogOnTextView(getActivity(),tvStartDate);
            }
        });

        cardEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.showDatePickerDialogOnTextView(getActivity(),tvEndDate);
            }
        });

        imgSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentCardStatus.equals("ACTIVE")){

                    if (!tvStartDate.getText().toString().isEmpty() && !tvEndDate.getText().toString().isEmpty()){
                        getCardStatement();
                    }else{
                        Toast.makeText(getActivity(), "Please enter valid date in both field", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getActivity(), "your current selected card is "+currentCardStatus, Toast.LENGTH_SHORT).show();
                }
            }
        });

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

            currentCardStatus = loginResponse.getFtc().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("ACTIVE")){

                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));
                /*tvCardStatusNote.setVisibility(View.GONE);
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

    public void getCardStatement(){

        showLoading();
        RequestBaseModel<InrCardStatementRequestModel> data = new RequestBaseModel<>();
        InrCardStatementRequestModel inrCardStatementRequestModel = new InrCardStatementRequestModel();

        inrCardStatementRequestModel.setFromDate(tvStartDate.getText().toString()+"T15:35:22.044");
        inrCardStatementRequestModel.setToDate(tvEndDate.getText().toString()+"T15:35:22.044");
        inrCardStatementRequestModel.setProxyNumber(CardProxyNumber);
        inrCardStatementRequestModel.setType("");
        inrCardStatementRequestModel.setSId("");

        data.setRequest(inrCardStatementRequestModel);

        if(NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.cardStatement(getActivity(), inrCardStatementRequestModel, new NetworkResponseCallback<InrCardStatementResponseModel>() {
                @Override
                public void onSuccess(Call<InrCardStatementResponseModel> call, Response<InrCardStatementResponseModel> response) {

                    hideLoading();
                    if (response.body().getStatusCode()==200){


                        TransactionStatementAdapter transactionStatementAdapter = new TransactionStatementAdapter(getActivity(),response.body().getData());
                        rvTransactionStatement.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        rvTransactionStatement.setAdapter(transactionStatementAdapter);

                    }else{
                        Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onResponseBodyNull(Call<InrCardStatementResponseModel> call, Response<InrCardStatementResponseModel> response) {
                    hideLoading();
                }

                @Override
                public void onResponseUnsuccessful(Call<InrCardStatementResponseModel> call, Response<InrCardStatementResponseModel> response) {
                    hideLoading();
                }

                @Override
                public void onFailure(Call<InrCardStatementResponseModel> call, Throwable t) {
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
}