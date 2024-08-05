package com.sbi.oneview.ui.transitScreen;

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
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.Transit.TransitStatementRequestModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.network.ResponseModel.TransitStatement.TransitStatementResponseModel;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.adapters.TransactionStatementAdapter;
import com.sbi.oneview.ui.adapters.Transit.TransitStatementAdapter;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;


public class CardStatementFragment extends BaseFragment implements MyFragmentCallback {


    TextView tvMyCards,tvCardDetails,tvTransaction,tvCardStatement,tvCurrentDate;

    int currentYear,currentDay,preYear,preDay;
    RecyclerView rvTransactionStatement;
    String currentMonth,preMonth;
    MaterialCardView cardStartDate,cardEndDate;
    TextView tvStartDate,tvEndDate;
    LinearLayout layoutNavButton;
    ImageView imgSearchIcon,imgNext,imgPrev;

    Data loginResponse;
    String currentCardStatus;

    String CardProxyNumber,productCode;
    int cardPosition;
    TextView tvSpendLimit;
    LinearLayout layoutCardStatus,layoutSpendLimitController;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate,tvCardBal,tvChipBal;
    String currentTransactionLength;
    int currentTransactionPageLength,currentPage=1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_statement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialization(view);
        clickListner();


        //--------------- default start and end-----------------
        getPreviousDateInFormat(1);
        //------------------------------------------------------



        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getTransit().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);


        /*TransactionStatementAdapter transactionStatementAdapter = new TransactionStatementAdapter();
        rvTransactionStatement.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvTransactionStatement.setAdapter(transactionStatementAdapter);*/
    }


    public void initialization(View view) {

        tvTransaction = view.findViewById(R.id.tvTransaction);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        CommonUtils.setGradientColor(tvTransaction);
        CommonUtils.setGradientColor(tvCardDetails);

        rvTransactionStatement = view.findViewById(R.id.rvTransactionStatement);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());

        tvCardStatement = view.findViewById(R.id.tvHeader);
        tvCardStatement.setText("Card Statement");
        CommonUtils.setGradientColor(tvCardStatement);

        tvMyCards = view.findViewById(R.id.tvMyCards);
        CommonUtils.setGradientColor(tvMyCards);


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

        cardStartDate = view.findViewById(R.id.cardStartDate);
        cardEndDate = view.findViewById(R.id.cardEndDate);
        tvStartDate = view.findViewById(R.id.tvStartDate);
        tvEndDate = view.findViewById(R.id.tvEndDate);
        imgSearchIcon = view.findViewById(R.id.imgSearchIcon);
        imgNext = view.findViewById(R.id.imgNext);
        imgPrev = view.findViewById(R.id.imgPrev);
        rvTransactionStatement = view.findViewById(R.id.rvTransactionStatement);
        layoutNavButton = view.findViewById(R.id.layoutNavButton);


    }


    public void clickListner() {

        LocalDate currentDate = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
            currentYear = currentDate.getYear();
            currentMonth = currentDate.getMonth().getDisplayName(java.time.format.TextStyle.SHORT, Locale.ENGLISH);
            currentDay = currentDate.getDayOfMonth();
        }


        cardStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.showDatePickerDialogOnTextView(getActivity(),tvStartDate);
            }
        });


        cardEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.showDatePickerDialogOnTextView(getActivity(), tvEndDate);
            }
        });


        imgSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentCardStatus.equals("ACTIVE") || currentCardStatus.equals("A")){
                    if (!tvStartDate.getText().toString().isEmpty()){

                        if (!tvEndDate.getText().toString().isEmpty()) {

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate date1 = LocalDate.parse(tvStartDate.getText(), formatter);
                            LocalDate date2 = LocalDate.parse(tvEndDate.getText(), formatter);

                            boolean isDifferenceExceeds = isDateDifferenceExceeds(date1, date2, 180);

                            if (isDifferenceExceeds){
                                CommonUtils.showInputValidationMsgDialogue(getActivity(),"You can get only 180 days(6 months) of transaction statement at a time, please select date range between only 180 days.");
                            }else {
                                getCardStatement(loginResponse.getToken(), "01", "M");
                            }
                        }else{
                            CommonUtils.showInputValidationMsgDialogue(getActivity(),"Please enter end date to proceed further");
                        }
                    }else{
                        CommonUtils.showInputValidationMsgDialogue(getActivity(),"Please enter start date to proceed further");
                    }
                }else{
                    Toast.makeText(getActivity(), "your current selected card is "+currentCardStatus, Toast.LENGTH_SHORT).show();
                }

            }
        });


        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("CP",""+currentPage);
                if (currentPage<=currentTransactionPageLength)
                {

                    getCardStatement(loginResponse.getToken(),"0"+String.valueOf(currentPage+1),"N");

                }else{
                    Toast.makeText(getActivity(), "No more transaction ahead", Toast.LENGTH_SHORT).show();
                }

            }
        });


        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("CP",""+currentPage);

                if(currentPage>1)
                {
                    getCardStatement(loginResponse.getToken(),"0"+String.valueOf(currentPage-1),"P");

                }else{
                    Toast.makeText(getActivity(), "No transaction", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public static boolean isDateDifferenceExceeds(LocalDate date1, LocalDate date2, int days) {
        long difference = ChronoUnit.DAYS.between(date1, date2);
        return Math.abs(difference) > days;
    }

    public void getCardStatement(String token,String pageNumber,String action)
    {
        showLoading();

        Log.d("PAGE",pageNumber);
        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);  // 24-hour format
        int minute = calendar.get(Calendar.MINUTE);

        // Format the time to always be two digits
        String formattedHour = String.format("%02d", hour);
        String formattedMinute = String.format("%02d", minute);

        TransitStatementRequestModel transitStatementRequestModel = new TransitStatementRequestModel();
        transitStatementRequestModel.setCardRefNumber(CardProxyNumber);
        transitStatementRequestModel.setSId("");
        transitStatementRequestModel.setPageNo(pageNumber);
        transitStatementRequestModel.setProductCode(productCode);
        transitStatementRequestModel.setFromDate(tvStartDate.getText().toString()+"T00:01:22.044");
        transitStatementRequestModel.setToDate(tvEndDate.getText().toString()+"T"+formattedHour+":"+formattedMinute+":00.044");

        Log.d("START",""+tvStartDate.getText().toString()+"T00:01:22.044");
        Log.d("END",""+tvEndDate.getText().toString()+"T"+formattedHour+":"+formattedMinute+":00.044");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(transitStatementRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);

        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.transitStatement(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
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

                                Log.d("MESG",""+responseBaseModel.toString());
                                TransitStatementResponseModel transitStatementResponseModel = null;

                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    transitStatementResponseModel = om1.readValue(jsonString, TransitStatementResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (transitStatementResponseModel!=null){

                                    String totalRecords = transitStatementResponseModel.getData().getTotalRecords();

                                    if (totalRecords != null && !totalRecords.isEmpty() && totalRecords.matches("\\d+")) {
                                        Log.d("LENGTH", totalRecords);
                                        currentTransactionLength = totalRecords;
                                        currentTransactionPageLength = Integer.parseInt(currentTransactionLength) / 20;
                                    } else {
                                        // Handle the case when totalRecords is null, empty, or not a valid number
                                        Log.d("LENGTH", "Total records is null, empty, or not a valid number.");
                                    }

                                    TransitStatementAdapter transactionStatementAdapter = new TransitStatementAdapter(getActivity(),transitStatementResponseModel.getData());
                                    rvTransactionStatement.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                    rvTransactionStatement.setAdapter(transactionStatementAdapter);

                                    if (currentTransactionPageLength>0)
                                    {
                                        layoutNavButton.setVisibility(View.VISIBLE);
                                    }

                                    if (action.equals("N")){
                                        currentPage = currentPage+1;
                                    }else if(action.equals("P")){
                                        currentPage = currentPage-1;
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

        }else {
            Toast.makeText(getActivity(), getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }



    public void getPreviousDateInFormat(int numberOfMonths) {
        // Get the current date
        LocalDate currentDate = null;
        String formattedPreviousDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
            LocalDate previousDate = currentDate.minusMonths(numberOfMonths);
            // Format the previous date
            formatDate(previousDate);
        }

        // return formattedPreviousDate;
    }

    // Method to format the date in the required format
    public void formatDate(LocalDate date) {
        // Get year, day, and month in 3 letters

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            preYear = date.getYear();
            preDay = date.getDayOfMonth();
            preMonth = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
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
            tvCardBal.setText(getResources().getString(R.string.Rs)+loginResponse.getTransit().getCardDetails().get(position).getWallBalPersonal());
            tvChipBal.setText(getResources().getString(R.string.Rs)+"0");


            CardProxyNumber = loginResponse.getTransit().getCardDetails().get(position).getCardRefNumber();
            productCode = loginResponse.getTransit().getCardDetails().get(position).getProductCode();
            cardPosition = position;

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