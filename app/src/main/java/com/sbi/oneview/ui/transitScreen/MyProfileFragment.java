package com.sbi.oneview.ui.transitScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.SharedConfig;


public class MyProfileFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_my_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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


        tvHeader.setText("My profile");

        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        CommonUtils.setGradientColor(tvHeader);
        CommonUtils.setGradientColor(tvcustomerdetails);

        setUserProfileData();
        mobileNumberTextView.setText(SharedConfig.getInstance(getActivity()).getMobileNumber());

    }


    public void setUserProfileData()
    {
        Data loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());
        if (loginResponse!=null)
        {
            firstNameTextView.setText(loginResponse.getTransit().getFirstName());
            lastNameTextView.setText(loginResponse.getTransit().getLastName());
            middleNameTextView.setText(loginResponse.getTransit().getMiddleName());
            dobTextView.setText(loginResponse.getTransit().getDob());
            ovdTypeTextView.setText(loginResponse.getTransit().getOvdType());
            ovdValueTextView.setText(loginResponse.getTransit().getOvdValue());
            kycTypeTextView.setText(loginResponse.getTransit().getKyc().equals("00")? "Min KYC":"Full KYC");
        }
        else{
            Toast.makeText(getActivity(), "No data Found", Toast.LENGTH_SHORT).show();
        }
    }
}