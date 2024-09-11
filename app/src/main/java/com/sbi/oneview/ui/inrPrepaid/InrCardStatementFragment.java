package com.sbi.oneview.ui.inrPrepaid;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.sbi.oneview.network.RequestModel.CardHotlistRequestModel;
import com.sbi.oneview.network.RequestModel.InrCardStatementRequestModel;
import com.sbi.oneview.network.ResponseModel.InrCardStatement.InrCardStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.network.ResponseModel.MiniStatement.CardMiniStatementResponseModel;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.adapters.TransactionStatementAdapter;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class InrCardStatementFragment extends BaseFragment implements MyFragmentCallback{


    TextView tvCardStatement;
    Data loginResponse;
    String currentCardStatus;
    RecyclerView rvTransactionStatement;
    String CardProxyNumber;
    int cardPosition;
    TextView tvCurrentDate,tvMyCards,tvCardDetails,tvTransaction;
    LinearLayout layoutCardStatus,layoutSpendLimitController;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate,tvCardBal,tvChipBal;

    MaterialCardView cardStartDate,cardEndDate;
    TextView tvStartDate,tvEndDate;
    ImageView imgSearchIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inr_card_statement, container, false);
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
        tvTransaction = view.findViewById(R.id.tvTransaction);

        cardStartDate = view.findViewById(R.id.cardStartDate);
        cardEndDate = view.findViewById(R.id.cardEndDate);
        tvStartDate = view.findViewById(R.id.tvStartDate);
        tvEndDate = view.findViewById(R.id.tvEndDate);
        imgSearchIcon = view.findViewById(R.id.imgSearchIcon);
        rvTransactionStatement = view.findViewById(R.id.rvTransactionStatement);

        tvCardStatement = view.findViewById(R.id.tvHeader);
        tvCardStatement.setText("Card Statement");
        CommonUtils.setGradientColor(tvCardStatement);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());

        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvTransaction);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getPrepaid().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView,null);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

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
                        getCardStatement(loginResponse.getToken());
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

            currentCardStatus = loginResponse.getPrepaid().getCardDetails().get(position).getCardStatus();
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

    public void getCardStatement(String token){

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        InrCardStatementRequestModel inrCardStatementRequestModel = new InrCardStatementRequestModel();
        inrCardStatementRequestModel.setFromDate(tvStartDate.getText().toString()+"T15:35:22.044");
        inrCardStatementRequestModel.setToDate(tvEndDate.getText().toString()+"T15:35:22.044");
        inrCardStatementRequestModel.setProxyNumber(CardProxyNumber);
        inrCardStatementRequestModel.setType("");
        inrCardStatementRequestModel.setSId("");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(inrCardStatementRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);



        if(NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.cardStatement(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
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

                            if (responseBaseModel.getStatusCode()==200) {

                                InrCardStatementResponseModel inrCardStatementResponseModel = null;
                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    inrCardStatementResponseModel = om1.readValue(jsonString, InrCardStatementResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (inrCardStatementResponseModel!=null){

                                    if (inrCardStatementResponseModel.getStatusCode()==200) {

                                        TransactionStatementAdapter transactionStatementAdapter = new TransactionStatementAdapter(getActivity(),inrCardStatementResponseModel.getData());
                                        rvTransactionStatement.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                        rvTransactionStatement.setAdapter(transactionStatementAdapter);

                                    }

                                }

                            }

                        }else{
                            Toast.makeText(getActivity(), "responseBaseModel"+getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
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
        }else{
            Toast.makeText(getActivity(), ""+getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }
    }
}