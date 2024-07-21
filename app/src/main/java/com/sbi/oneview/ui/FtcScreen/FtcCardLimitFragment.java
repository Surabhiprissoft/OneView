package com.sbi.oneview.ui.FtcScreen;

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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbi.oneview.R;
import com.sbi.oneview.base.BaseFragment;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.LimitEnquiryRequestModel;
import com.sbi.oneview.network.RequestModel.LimitUpdateRequestModel;
import com.sbi.oneview.network.ResponseModel.InrLimitEnquiry.InrLimitEnquiryResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
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

public class FtcCardLimitFragment extends BaseFragment implements MyFragmentCallback {

    TextView tvLimitHeading,tvCurrentDate,tvCardLimit,tvMyCards,tvCardDetails;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate;
    TextView tvCardStatusNote;
    Button btnLimitUpdate;
    private SeekBar seekBarAtm,seekBarPos,seekBarEcomm,seekBarContactless;
    private EditText etAtmValue,etPosValue,etEcommValue,etContactlessValue;
    private Switch switchAtm,switchPos,switchEComm,switchContactless;
    LinearLayout layoutCardStatus,layoutSpendLimitController;
    FtcHomeActivity ftcHomeActivity;
    String currentCardStatus;
    String CardProxyNumber,token;
    int cardPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ftc_card_limit, container, false);
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
        tvLimitHeading = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        tvCardLimit = view.findViewById(R.id.tvCardLimit);

        tvCardNumber = view.findViewById(R.id.tvCardNumber);
        tvCRN = view.findViewById(R.id.tvCRNNumber);
        tvCardStatus = view.findViewById(R.id.tvCardStatus);
        layoutCardStatus = view.findViewById(R.id.layoutCardStatus);
        tvProductName = view.findViewById(R.id.tvCardProductName);
        tvActDate = view.findViewById(R.id.tvCardActiveDate);
        tvExpDate = view.findViewById(R.id.tvCardExpDate);

        tvCardStatusNote = view.findViewById(R.id.tvCardStatusNote);
        layoutSpendLimitController = view.findViewById(R.id.layoutSpendLimitController);

        etAtmValue = view.findViewById(R.id.etAtmValue);
        etPosValue = view.findViewById(R.id.etPosValue);
        etEcommValue = view.findViewById(R.id.etEcommValue);
        etContactlessValue = view.findViewById(R.id.etContactlessValue);

        seekBarAtm = view.findViewById(R.id.seekBarAtm);
        seekBarPos = view.findViewById(R.id.seekBarPos);
        seekBarEcomm = view.findViewById(R.id.seekBarEcomm);
        seekBarContactless = view.findViewById(R.id.seekBarContactless);

        switchAtm = view.findViewById(R.id.switchAtm);
        switchPos = view.findViewById(R.id.switchPos);
        switchEComm = view.findViewById(R.id.switchEComm);
        switchContactless = view.findViewById(R.id.switchContactless);

        btnLimitUpdate = view.findViewById(R.id.btnLimitUpdate);

        seekBarAtm.setMax(40000);
        seekBarPos.setMax(200000);
        seekBarEcomm.setMax(200000);
        seekBarContactless.setMax(5000);

        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        tvLimitHeading.setText("Card Limit");

        CommonUtils.setGradientColor(tvLimitHeading);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvCardLimit);

        ftcHomeActivity = (FtcHomeActivity) getActivity();

    }

    public void clickListener(){
        seekBarAtm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etAtmValue.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarPos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etPosValue.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarEcomm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etEcommValue.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarContactless.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etContactlessValue.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        switchAtm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    etAtmValue.setFocusable(true);
                    seekBarAtm.setEnabled(true);
                }else{
                    etAtmValue.setFocusable(false);
                    seekBarAtm.setEnabled(false);
                }
            }
        });

        switchPos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etPosValue.setFocusable(true);
                    seekBarPos.setEnabled(true);

                }else{
                    etPosValue.setFocusable(false);
                    seekBarPos.setEnabled(false);

                }
            }
        });

        switchEComm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etEcommValue.setFocusable(true);
                    seekBarEcomm.setEnabled(true);
                }else{
                    etEcommValue.setFocusable(false);
                    seekBarEcomm.setEnabled(false);
                }
            }
        });

        switchContactless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etContactlessValue.setFocusable(true);
                    seekBarContactless.setEnabled(true);
                }else{
                    etContactlessValue.setFocusable(false);
                    seekBarContactless.setEnabled(false);
                }
            }
        });

        btnLimitUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardLimitUpdate(CardProxyNumber);
            }
        });
    }

    @Override
    public void onPositionChange(int position) {

        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());
        if (loginResponse!=null){

            tvCRN.setText(loginResponse.getFtc().getCardDetails().get(position).getProxyNumber());
            tvCardNumber.setText(loginResponse.getFtc().getCardDetails().get(position).getCardNumber());
           // tvCardStatus.setText(loginResponse.getFtc().getCardDetails().get(position).getCardStatus());
            tvProductName.setText(loginResponse.getFtc().getCardDetails().get(position).getProductName());
            tvActDate.setText(loginResponse.getFtc().getCardDetails().get(position).getCardActivDate().substring(3,5) +" / "+ loginResponse.getFtc().getCardDetails().get(position).getCardActivDate().substring(6));
            tvExpDate.setText(loginResponse.getFtc().getCardDetails().get(position).getCardExpiryDate().substring(3,5)+" / "+loginResponse.getFtc().getCardDetails().get(position).getCardExpiryDate().substring(6));

            CardProxyNumber = loginResponse.getFtc().getCardDetails().get(position).getProxyNumber();
            cardPosition = position;
            token = loginResponse.getToken();

            currentCardStatus = loginResponse.getFtc().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("ACTIVE") || currentCardStatus.equals("A")){
                tvCardStatus.setText("ACTIVE");
                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));
                tvCardStatusNote.setVisibility(View.GONE);
                layoutSpendLimitController.setVisibility(View.VISIBLE);

            }else if(currentCardStatus.equals("BLOCKED")){
                tvCardStatus.setText("BLOCKED");
                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));
                tvCardStatusNote.setVisibility(View.VISIBLE);
                tvCardStatusNote.setText("Your Card has been blocked, please un-block it to use spend limit features.");
                layoutSpendLimitController.setVisibility(View.GONE);

            }else {
                tvCardStatus.setText("INACTIVE");
                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));
                tvCardStatusNote.setVisibility(View.VISIBLE);
                tvCardStatusNote.setText("Your Card has been permanantly blocked, please visit nearest branch for issuance of new card.");
                layoutSpendLimitController.setVisibility(View.GONE);

            }


            cardLimitEnquiry(loginResponse.getFtc().getCardDetails().get(position).getProxyNumber());



        }
    }


    public void cardLimitEnquiry(String proxyNumber){

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        LimitEnquiryRequestModel limitEnquiryRequestModel = new LimitEnquiryRequestModel();
        limitEnquiryRequestModel.setProxyNumber(proxyNumber);
        limitEnquiryRequestModel.setSId("");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(limitEnquiryRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);


        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.CardLimitEnquiry(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
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

                        if (responseBaseModel!=null)
                        {
                            if (responseBaseModel.getStatusCode()==200)
                            {
                                Log.d("BASE-RESPOSNE",""+responseBaseModel.toString());

                                InrLimitEnquiryResponseModel inrLimitEnquiryResponseModel = null;

                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    inrLimitEnquiryResponseModel = om1.readValue(jsonString, InrLimitEnquiryResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (inrLimitEnquiryResponseModel!=null)
                                {
                                    if (inrLimitEnquiryResponseModel.getStatusCode()==200)
                                    {
                                        Log.d("RESPOSNE",""+inrLimitEnquiryResponseModel.getData().toString());


                                        if (inrLimitEnquiryResponseModel.getData().getAtmTxnFlag()!=null) {
                                            etAtmValue.setText(inrLimitEnquiryResponseModel.getData().getAtmTxnAmount().isEmpty() || inrLimitEnquiryResponseModel.getData().getAtmTxnAmount().equals("null") ? "0" : inrLimitEnquiryResponseModel.getData().getAtmTxnAmount());
                                            seekBarAtm.setProgress(inrLimitEnquiryResponseModel.getData().getAtmTxnAmount().equals("") || inrLimitEnquiryResponseModel.getData().getAtmTxnAmount().equals("null") ? 0 : Integer.parseInt(inrLimitEnquiryResponseModel.getData().getAtmTxnAmount()), true);
                                            switchAtm.setChecked(inrLimitEnquiryResponseModel.getData().getAtmTxnFlag().equals("1") ? true : false);

                                            if (inrLimitEnquiryResponseModel.getData().getAtmTxnFlag().equals("1")){
                                                etAtmValue.setFocusable(true);
                                                seekBarAtm.setEnabled(true);
                                            }else{
                                                etAtmValue.setFocusable(false);
                                                seekBarAtm.setEnabled(false);
                                            }
                                        }
                                        else{
                                            etAtmValue.setText(""+0);
                                            seekBarAtm.setProgress(0);
                                        }


                                        Log.d("POS","its :"+inrLimitEnquiryResponseModel.getData().getPosTxnFlag());
                                        if (inrLimitEnquiryResponseModel.getData().getPosTxnFlag()!=null) {
                                            etPosValue.setText(inrLimitEnquiryResponseModel.getData().getPosTxnAmount().isEmpty() || inrLimitEnquiryResponseModel.getData().getPosTxnAmount() == null ? "0" : inrLimitEnquiryResponseModel.getData().getPosTxnAmount());
                                            seekBarPos.setProgress(inrLimitEnquiryResponseModel.getData().getPosTxnAmount().equals("") || inrLimitEnquiryResponseModel.getData().getPosTxnAmount().equals("null") ? 0 : Integer.parseInt(inrLimitEnquiryResponseModel.getData().getPosTxnAmount()), true);
                                            switchPos.setChecked(inrLimitEnquiryResponseModel.getData().getPosTxnFlag().equals("1") ? true:false);

                                            if (inrLimitEnquiryResponseModel.getData().getPosTxnFlag().equals("1")){
                                                etPosValue.setFocusable(true);
                                                seekBarPos.setEnabled(true);
                                            }else{
                                                etPosValue.setFocusable(false);
                                                seekBarPos.setEnabled(false);
                                            }
                                        }
                                        else{
                                            etPosValue.setText(""+0);
                                            seekBarPos.setProgress(0);
                                        }


                                        if (inrLimitEnquiryResponseModel.getData().getEcomTxnFlag()!=null) {
                                            etEcommValue.setText(inrLimitEnquiryResponseModel.getData().getEcomTxnAmount().equals("") || inrLimitEnquiryResponseModel.getData().getEcomTxnAmount().equals("null") ? "0" : inrLimitEnquiryResponseModel.getData().getEcomTxnAmount());
                                            seekBarEcomm.setProgress(inrLimitEnquiryResponseModel.getData().getEcomTxnAmount().equals("") || inrLimitEnquiryResponseModel.getData().getEcomTxnAmount().equals("null") ? 0 : Integer.parseInt(inrLimitEnquiryResponseModel.getData().getEcomTxnAmount()), true);
                                            switchEComm.setChecked(inrLimitEnquiryResponseModel.getData().getEcomTxnFlag().equals("1") ? true:false);

                                            if (inrLimitEnquiryResponseModel.getData().getEcomTxnFlag().equals("1")){
                                                etEcommValue.setFocusable(true);
                                                seekBarEcomm.setEnabled(true);
                                            }else{
                                                etEcommValue.setFocusable(false);
                                                seekBarEcomm.setEnabled(false);
                                            }
                                        }
                                        else{
                                            etEcommValue.setText(""+0);
                                            seekBarEcomm.setProgress(0);
                                        }


                                        //handle user interaction on editText and seekbar in accordance with response flag






                                        /*if (inrLimitEnquiryResponseModel.getData().getClTxnFlag().equals("1")){
                                            etContactlessValue.setFocusable(true);
                                            seekBarContactless.setEnabled(true);
                                        }else{
                                            etContactlessValue.setFocusable(false);
                                            seekBarContactless.setEnabled(false);
                                        }*/
                                    }
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
            Toast.makeText(getActivity(), ""+getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }



    public void cardLimitUpdate(String cardProxyNumber){

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        LimitUpdateRequestModel limitUpdateRequestModel = new LimitUpdateRequestModel();
        limitUpdateRequestModel.setProxyNumber(cardProxyNumber);
        limitUpdateRequestModel.setRemarks("API INSETION UPDATE");

        limitUpdateRequestModel.setAtmTxnAmount(etAtmValue.getText().toString());
        limitUpdateRequestModel.setAtmTxnFlag(switchAtm.isChecked() ? "1":"0");
        limitUpdateRequestModel.setAtmTxnCount(switchAtm.isChecked() ? "1":"");

        limitUpdateRequestModel.setPosTxnAmount(etPosValue.getText().toString());
        limitUpdateRequestModel.setPosTxnFlag(switchPos.isChecked() ? "1":"0");
        limitUpdateRequestModel.setPosTxnCount(switchPos.isChecked() ? "1":"");

        limitUpdateRequestModel.setEcomTxnAmount(etEcommValue.getText().toString());
        limitUpdateRequestModel.setEcomTxnFlag(switchEComm.isChecked() ? "1":"0");
        limitUpdateRequestModel.setEcomTxnCount(switchEComm.isChecked() ? "1":"");

        limitUpdateRequestModel.setClTxnAmount(etContactlessValue.getText().toString());
        limitUpdateRequestModel.setClTxnFlag(switchContactless.isChecked() ? "1":"0");
        limitUpdateRequestModel.setClTxnCount(switchContactless.isChecked() ? "1":"");


        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(limitUpdateRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);

        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.CardLimitUpdate(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
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

                        if (responseBaseModel!=null)
                        {
                            if (responseBaseModel.getStatusCode()==200)
                            {
                                Toast.makeText(getActivity(), "Card limit updated successfully", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getActivity(), ""+getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }



}