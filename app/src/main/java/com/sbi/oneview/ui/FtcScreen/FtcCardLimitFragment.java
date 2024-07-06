package com.sbi.oneview.ui.FtcScreen;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.sbi.oneview.R;
import com.sbi.oneview.base.BaseFragment;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.LimitEnquiryRequestModel;
import com.sbi.oneview.network.ResponseModel.InrLimitEnquiry.InrLimitEnquiryResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FtcCardLimitFragment extends BaseFragment implements MyFragmentCallback {

    TextView tvLimitHeading,tvCurrentDate,tvCardLimit,tvMyCards,tvCardDetails;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate;
    TextView tvCardStatusNote;
    private SeekBar seekBarAtm,seekBarPos,seekBarEcomm,seekBarContactless;
    private EditText etAtmValue,etPosValue,etEcommValue,etContactlessValue;
    private Switch switchAtm,switchPos,switchEComm,switchContactless;
    LinearLayout layoutCardStatus,layoutSpendLimitController;
    FtcHomeActivity ftcHomeActivity;
    String currentCardStatus;
    String CardProxyNumber;
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

        seekBarAtm.setMax(40000);
        seekBarPos.setMax(200000);
        seekBarEcomm.setMax(200000);
        seekBarContactless.setMax(5000);

        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        tvLimitHeading.setText("Reset PIN");

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
                tvCardStatusNote.setVisibility(View.GONE);
                layoutSpendLimitController.setVisibility(View.VISIBLE);

            }else if(currentCardStatus.equals("BLOCKED")){

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));
                tvCardStatusNote.setVisibility(View.VISIBLE);
                tvCardStatusNote.setText("Your Card has been blocked, please un-block it to use spend limit features.");
                layoutSpendLimitController.setVisibility(View.GONE);

            }else if (currentCardStatus.equals("INACTIVE")){

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

        Toast.makeText(getActivity(), ""+proxyNumber, Toast.LENGTH_SHORT).show();
        showLoading();

        RequestBaseModel<LimitEnquiryRequestModel> data = new RequestBaseModel<>();
        LimitEnquiryRequestModel limitEnquiryRequestModel = new LimitEnquiryRequestModel();

        limitEnquiryRequestModel.setProxyNumber(proxyNumber);
        limitEnquiryRequestModel.setSId("");

        data.setRequest(limitEnquiryRequestModel);


        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.CardLimitEnquiry(getActivity(), limitEnquiryRequestModel, new NetworkResponseCallback<InrLimitEnquiryResponseModel>() {
                @Override
                public void onSuccess(Call<InrLimitEnquiryResponseModel> call, Response<InrLimitEnquiryResponseModel> response) {
                    hideLoading();

                    if (response.body().getStatusCode()==200){

                        etAtmValue.setText(response.body().getData().getAtmTxnAmount());
                        seekBarAtm.setProgress(Integer.parseInt(response.body().getData().getAtmTxnAmount()),true);

                        etPosValue.setText(response.body().getData().getPosTxnAmount());
                        seekBarPos.setProgress(Integer.parseInt(response.body().getData().getPosTxnAmount()),true);

                        etEcommValue.setText(response.body().getData().getEcomTxnAmount());
                        seekBarEcomm.setProgress(Integer.parseInt(response.body().getData().getEcomTxnAmount()),true);

                        etContactlessValue.setText(response.body().getData().getClTxnAmount());
                        seekBarContactless.setProgress(Integer.parseInt(response.body().getData().getClTxnAmount()),true);

                        switchAtm.setChecked(response.body().getData().getAtmTxnFlag().equals("1") ? true:false);
                        switchPos.setChecked(response.body().getData().getPosTxnFlag().equals("1") ? true:false);
                        switchEComm.setChecked(response.body().getData().getEcomTxnFlag().equals("1") ? true:false);
                        switchContactless.setChecked(response.body().getData().getClTxnFlag().equals("1") ? true:false);

                        //handle user interaction on editText and seekbar in accordance with response flag
                        if (response.body().getData().getAtmTxnFlag().equals("1")){
                            etAtmValue.setFocusable(true);
                            seekBarAtm.setEnabled(true);
                        }else{
                            etAtmValue.setFocusable(false);
                            seekBarAtm.setEnabled(false);
                        }

                        if (response.body().getData().getPosTxnFlag().equals("1")){
                            etPosValue.setFocusable(true);
                            seekBarPos.setEnabled(true);
                        }else{
                            etPosValue.setFocusable(false);
                            seekBarPos.setEnabled(false);
                        }

                        if (response.body().getData().getEcomTxnFlag().equals("1")){
                            etEcommValue.setFocusable(true);
                            seekBarEcomm.setEnabled(true);
                        }else{
                            etEcommValue.setFocusable(false);
                            seekBarEcomm.setEnabled(false);
                        }

                        if (response.body().getData().getClTxnFlag().equals("1")){
                            etContactlessValue.setFocusable(true);
                            seekBarContactless.setEnabled(true);
                        }else{
                            etContactlessValue.setFocusable(false);
                            seekBarContactless.setEnabled(false);
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onResponseBodyNull(Call<InrLimitEnquiryResponseModel> call, Response<InrLimitEnquiryResponseModel> response) {
                    hideLoading();

                }

                @Override
                public void onResponseUnsuccessful(Call<InrLimitEnquiryResponseModel> call, Response<InrLimitEnquiryResponseModel> response) {
                    hideLoading();

                }

                @Override
                public void onFailure(Call<InrLimitEnquiryResponseModel> call, Throwable t) {
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