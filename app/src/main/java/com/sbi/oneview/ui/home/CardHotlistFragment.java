package com.sbi.oneview.ui.home;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.OTPVerificationDialog;

import java.util.ArrayList;


public class CardHotlistFragment extends Fragment {

    TextView tvHotlistHeading,tvCurrentDate,tvHotlist,tvMyCards,tvCardDetails;
    MaterialButton btnHotlist;
    OTPVerificationDialog otpVerificationDialog;

    Spinner spinnerHotlistReason;
    String selectedHotlistReason ="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_hotlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnHotlist = view.findViewById(R.id.btnHotlist);
        tvHotlistHeading = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvHotlist = view.findViewById(R.id.tvCardHotlist);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        spinnerHotlistReason = view.findViewById(R.id.spinnerHotlistReason);

        CommonUtils.setGradientColor(tvHotlist);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);

        tvHotlistHeading.setText("Card Hotlist");
        CommonUtils.setGradientColor(tvHotlistHeading);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());

        String[]cards_array  = getResources().getStringArray(R.array.hot_list_reason_array);
        ArrayAdapter<String> cardsAdapter =new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, cards_array);
        spinnerHotlistReason.setAdapter(cardsAdapter);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(R.drawable.city_mumbai);
        arrayList.add(R.drawable.city_noida);
        arrayList.add(R.drawable.city_nagpur);
        arrayList.add(R.drawable.city_chennai);
        arrayList.add(R.drawable.city_kanpur);

        CourouselAdapter adapter = new CourouselAdapter(getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        btnHotlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialogue();
            }
        });


    }

    public void showConfirmationDialogue(){

        Dialog dialog = new Dialog(getActivity());

        dialog.setContentView(R.layout.dialog_hotlist_confirmation);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        ImageView closeImg = dialog.findViewById(R.id.imgClose);
        MaterialButton btnYes = dialog.findViewById(R.id.btnYes);
        MaterialButton btnNo = dialog.findViewById(R.id.btnNo);

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (otpVerificationDialog != null && otpVerificationDialog.isShowing()) {
                    otpVerificationDialog.dismiss();
                }
                otpVerificationDialog = new OTPVerificationDialog(getActivity());
                //otpVerificationDialog.setCancelable(false);
                otpVerificationDialog.show();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }








}