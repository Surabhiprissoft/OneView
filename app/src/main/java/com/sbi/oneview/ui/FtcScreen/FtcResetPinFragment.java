package com.sbi.oneview.ui.FtcScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.SharedConfig;

import java.util.List;


public class FtcResetPinFragment extends Fragment implements MyFragmentCallback {

    TextView tvBlockHeading,tvCurrentDate,tvResetPin,tvMyCards,tvCardDetails;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate;
    FtcHomeActivity ftcHomeActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ftc_reset_pin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidget(view);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getFtc().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }

    public void initWidget(View view){
        tvBlockHeading = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        tvResetPin = view.findViewById(R.id.tvResetPin);

        tvCardNumber = view.findViewById(R.id.tvCardNumber);
        tvCRN = view.findViewById(R.id.tvCRNNumber);
        tvCardStatus = view.findViewById(R.id.tvCardStatus);
        tvProductName = view.findViewById(R.id.tvCardProductName);
        tvActDate = view.findViewById(R.id.tvCardActiveDate);
        tvExpDate = view.findViewById(R.id.tvCardExpDate);

        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        tvBlockHeading.setText("Reset PIN");

        CommonUtils.setGradientColor(tvBlockHeading);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvResetPin);

        ftcHomeActivity = (FtcHomeActivity) getActivity();
    }

    public void clickListener(){

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

        }
    }
}