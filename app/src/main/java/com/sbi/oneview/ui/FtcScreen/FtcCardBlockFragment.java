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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.SharedConfig;

import java.util.List;


public class FtcCardBlockFragment extends Fragment implements MyFragmentCallback {


    TextView tvBlockHeading,tvCurrentDate,tvCardBlock,tvMyCards,tvCardDetails;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate;
    TextView tvCardBlockUnblock,tvCardHotlist,tvTempBlockNote,tvPerBlockNote,tvPerBlockNote1;
    LinearLayout cardBlockUnblock,cardHotlist,layoutBlockUnblock,layoutHotlist,layoutCardStatus;
    MaterialButton btnTempBlockUnblock,btnHotlist;

    FtcHomeActivity ftcHomeActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ftc_card_block, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidget(view);
        clickListener();
        changeTab("block");


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
        tvCardBlock = view.findViewById(R.id.tvCardBlock);

        tvCardNumber = view.findViewById(R.id.tvCardNumber);
        tvCRN = view.findViewById(R.id.tvCRNNumber);
        tvCardStatus = view.findViewById(R.id.tvCardStatus);
        tvProductName = view.findViewById(R.id.tvCardProductName);
        tvActDate = view.findViewById(R.id.tvCardActiveDate);
        tvExpDate = view.findViewById(R.id.tvCardExpDate);

        btnTempBlockUnblock = view.findViewById(R.id.btnTempBlockUnblock);
        btnHotlist = view.findViewById(R.id.btnHotlist);
        tvTempBlockNote = view.findViewById(R.id.tvTempBlockNote);
        tvPerBlockNote = view.findViewById(R.id.tvPerBlockNote);
        tvPerBlockNote1 = view.findViewById(R.id.tvPerBlockNote1);

        tvCardBlockUnblock = view.findViewById(R.id.tvCardBlockUnblock);
        tvCardHotlist = view.findViewById(R.id.tvHotlist);

        cardHotlist = view.findViewById(R.id.cardHotlist);
        cardBlockUnblock = view.findViewById(R.id.cardBlocUnblock);

        layoutBlockUnblock = view.findViewById(R.id.layoutBlockUnblock);
        layoutHotlist = view.findViewById(R.id.layoutHotlist);

        layoutHotlist.setVisibility(View.GONE);

        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        tvBlockHeading.setText("Card Block");

        CommonUtils.setGradientColor(tvBlockHeading);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvCardBlock);

        ftcHomeActivity = (FtcHomeActivity) getActivity();
    }

    public void clickListener(){
        cardBlockUnblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab("block");
            }
        });

        cardHotlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab("hotlist");
            }
        });
    }

    public void changeTab(String tab){
        if (tab.equals("hotlist")){
            layoutHotlist.setVisibility(View.VISIBLE);
            layoutBlockUnblock.setVisibility(View.GONE);

            tvCardHotlist.setTextColor(Color.WHITE);
            cardHotlist.setBackgroundColor(getResources().getColor(R.color.background_one));

            tvCardBlockUnblock.setTextColor(Color.RED);
            cardBlockUnblock.setBackgroundColor(getResources().getColor(R.color.header_white_color));
        }else{
            layoutHotlist.setVisibility(View.GONE);
            layoutBlockUnblock.setVisibility(View.VISIBLE);

            tvCardHotlist.setTextColor(Color.RED);
            cardHotlist.setBackgroundColor(getResources().getColor(R.color.header_white_color));

            tvCardBlockUnblock.setTextColor(Color.WHITE);
            cardBlockUnblock.setBackgroundColor(getResources().getColor(R.color.background_one));
        }
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