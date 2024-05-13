package com.sbi.oneview.ui;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.sbi.oneview.R;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;

import java.util.ArrayList;

public class TopUpFragment extends Fragment {

    TextView tvContactUs,tvCurrentDate,tvTopUp,tvMyCards,tvCardDetails;
    private LinearLayout container;
    MaterialCardView card500,card1000,card2000,card5000,card10000;
    EditText etMoney;
    LinearLayout layoutChooseMoney,layoutChoosePaymentMode;
    MaterialButton btnProceedWithMoney;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvContactUs = view.findViewById(R.id.tvDashboard);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvTopUp = view.findViewById(R.id.tvTopUp);
        card500 = view.findViewById(R.id.card500);
        card1000 = view.findViewById(R.id.card1000);
        card2000 = view.findViewById(R.id.card2000);
        card5000 = view.findViewById(R.id.card5000);
        card10000 = view.findViewById(R.id.card10000);
        etMoney = view.findViewById(R.id.etMoney);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        layoutChooseMoney = view.findViewById(R.id.layoutChooseMoney);
        layoutChoosePaymentMode = view.findViewById(R.id.layoutChoosePaymentMode);
        btnProceedWithMoney = view.findViewById(R.id.btnProceedWithMoney);

        /*container = view.findViewById(R.id.container);
        addTextViews();*/

        CommonUtils.setGradientColor(tvTopUp);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);

        tvContactUs.setText("TopUp");
        CommonUtils.setGradientColor(tvContactUs);
        tvCurrentDate.setText(CommonUtils.setCurrentDate());

        card500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("500");
            }
        });

        card1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("1000");
            }
        });

        card2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("2000");
            }
        });

        card5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("5000");
            }
        });

        card10000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMoney.setText("10000");
            }
        });


        btnProceedWithMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutChooseMoney.setVisibility(View.GONE);
                layoutChoosePaymentMode.setVisibility(View.VISIBLE);
            }
        });



        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        ArrayList<String> arrayList = new ArrayList<>();
        //motionLayout.transitionToEnd();
        //Add multiple images to arraylist.

        arrayList.add("https://images.unsplash.com/photo-1692528131755-d4e366b2adf0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzNXx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692862582645-3b6fd47b7513?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw0MXx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692584927805-d4096552a5ba?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw0Nnx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692854236272-cc49076a2629?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1MXx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1681207751526-a091f2c6a538?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyODF8fHxlbnwwfHx8fHw%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692610365998-c628604f5d9f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyODZ8fHxlbnwwfHx8fHw%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692854236272-cc49076a2629?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1MXx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1681207751526-a091f2c6a538?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyODF8fHxlbnwwfHx8fHw%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692610365998-c628604f5d9f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyODZ8fHxlbnwwfHx8fHw%3D&auto=format&fit=crop&w=500&q=60");


        CourouselAdapter adapter = new CourouselAdapter(getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);


    }

    private void addTextViews() {
        String[] texts = {"Text 1", "Text 2", "Text 3", "Text 4", "Text 5","Text 1", "Text 2", "Text 3", "Text 4", "Text 5","Text 1", "Text 2", "Text 3", "Text 4", "Text 5"};

        for (String text : texts) {
            TextView textView = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(8, 8, 8, 8);
            textView.setLayoutParams(layoutParams);
            textView.setText(text);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.BLUE);

            container.addView(textView);
        }
    }

}