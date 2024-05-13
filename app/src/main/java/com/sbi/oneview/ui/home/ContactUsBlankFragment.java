package com.sbi.oneview.ui.home;

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
import com.sbi.oneview.ui.adapters.ContactUsMethodAdapter;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.ContactUsMethod;

import java.util.ArrayList;
import java.util.List;


public class ContactUsBlankFragment extends Fragment {



    private RecyclerView rvContactUs;
    private List<ContactUsMethod> mContactUsMethodList;
    TextView tvContactUs,tvCurrentDate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_us_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvContactUs = view.findViewById(R.id.tvDashboard);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        rvContactUs = view.findViewById(R.id.rvContactMethods);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvContactUs.setLayoutManager(layoutManager);

        tvContactUs.setText("Contact US");
        CommonUtils.setGradientColor(tvContactUs);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());

        // Initialize the list
        mContactUsMethodList = new ArrayList<>();
        mContactUsMethodList.add(new ContactUsMethod("Contact", "18001234","", R.drawable.img_phone));
        mContactUsMethodList.add(new ContactUsMethod("Email", "contactcentre@sbi.co.in","", R.drawable.img_email));
        mContactUsMethodList.add(new ContactUsMethod("Website", "https://transit.sbi","Please visit our Website", R.drawable.img_web));
        mContactUsMethodList.add(new ContactUsMethod("Complaints", "https://crcf.sbi.co.in/ccf","Please register your complaint here.", R.drawable.img_complaint));
        // Add more items as needed

        // Initialize the adapter
        ContactUsMethodAdapter adapter = new ContactUsMethodAdapter(getActivity(), mContactUsMethodList);

        // Set the adapter to your RecyclerView
        rvContactUs.setAdapter(adapter);

    }
}