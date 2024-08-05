package com.sbi.oneview.ui.FtcScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.SharedConfig;


public class FtcMyProfileFragment extends Fragment {


    TextView tvHeader,tvCurrentDate;
    TextView tvcustomerdetails;
    private TextView firstNameTextView,middleNameTextView, lastNameTextView,dobTextView, ovdTypeTextView, ovdValueTextView, kycTypeTextView, mobileNumberTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ftc_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidget(view);

    }


    public void initWidget(View view){

        tvHeader = view.findViewById(R.id.tvHeader);
        tvcustomerdetails = view.findViewById(R.id.tvCustomerdetails);
        tvCurrentDate = view.findViewById(R.id.tvDate);

        firstNameTextView = view.findViewById(R.id.first_name_value);
        middleNameTextView = view.findViewById(R.id.middle_name_value);
        lastNameTextView = view.findViewById(R.id.last_name_value);
        dobTextView = view.findViewById(R.id.DOB_value);
        ovdTypeTextView = view.findViewById(R.id.ovd_type_value);
        ovdValueTextView = view.findViewById(R.id.ovd_value_value);
        kycTypeTextView = view.findViewById(R.id.kyc_type_value);
        mobileNumberTextView = view.findViewById(R.id.mobile_number_value);


        tvHeader.setText("My Profile");

        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        CommonUtils.setGradientColor(tvHeader);
        CommonUtils.setGradientColor(tvcustomerdetails);


        setFtcUserProfileData();
        mobileNumberTextView.setText(SharedConfig.getInstance(getActivity()).getMobileNumber());


    }

    public void setFtcUserProfileData(){

        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());
        if (loginResponse!=null){

            firstNameTextView.setText(loginResponse.getFtc().getFirstName());
            lastNameTextView.setText(loginResponse.getFtc().getLastName());
            middleNameTextView.setText(loginResponse.getFtc().getMiddleName());
            dobTextView.setText(loginResponse.getFtc().getDob());
            ovdTypeTextView.setText(loginResponse.getFtc().getOvdType());
            ovdValueTextView.setText(loginResponse.getFtc().getOvdValue());
            kycTypeTextView.setText(loginResponse.getFtc().getKyc().equals("00")? "Min KYC":"Full KYC");

        }

    }
}