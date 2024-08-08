package com.sbi.oneview.ui.transitScreen;

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

        tvContactUs = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        rvContactUs = view.findViewById(R.id.rvContactMethods);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvContactUs.setLayoutManager(layoutManager);

        tvContactUs.setText("Contact US");
        CommonUtils.setGradientColor(tvContactUs);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());

        // Initialize the list
        mContactUsMethodList = new ArrayList<>();
        mContactUsMethodList.add(new ContactUsMethod(getString(R.string.transit_contact_1),"Nation First Transit Card"));
        mContactUsMethodList.add(new ContactUsMethod(getString(R.string.transit_contact_2),"Mumbai One Card"));
        mContactUsMethodList.add(new ContactUsMethod(getString(R.string.transit_contact_3),"All Metro"));
        mContactUsMethodList.add(new ContactUsMethod(getString(R.string.transit_contact_4),"STD/ISD No."));
        // Add more items as needed

        // Initialize the adapter
        ContactUsMethodAdapter adapter = new ContactUsMethodAdapter(getActivity(), mContactUsMethodList);

        // Set the adapter to your RecyclerView
        rvContactUs.setAdapter(adapter);

    }
}