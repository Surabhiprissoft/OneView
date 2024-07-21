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

import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.adapters.FtcBalanceAdapter;
import com.sbi.oneview.ui.inrPrepaid.InrCardStatementFragment;
import com.sbi.oneview.ui.inrPrepaid.InrPrepaidHomeActivity;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.SharedConfig;

import java.util.List;


public class FtcDashboardFragment extends Fragment implements MyFragmentCallback {

    TextView tvDashboard,tvCurrentDate,tvBalance,tvQuickAccess,tvMyCards,tvCardDetails;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate;
    MaterialCardView cardStatusCard,cardBlock,cardResetPin,cardLimit,cardStatement;
    LinearLayout layoutCardStatus;
    RecyclerView rvBalance;
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
        return inflater.inflate(R.layout.fragment_ftc_dashboard, container, false);
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


        rvBalance = view.findViewById(R.id.rvBalance);
    }

    public void initWidget(View view){

        tvDashboard = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvQuickAccess = view.findViewById(R.id.tvQuickAccess);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        tvBalance = view.findViewById(R.id.tvBalance);

        tvCardNumber = view.findViewById(R.id.tvCardNumber);
        tvCRN = view.findViewById(R.id.tvCRNNumber);
        tvCardStatus = view.findViewById(R.id.tvCardStatus);
        layoutCardStatus = view.findViewById(R.id.layoutCardStatus);
        tvProductName = view.findViewById(R.id.tvCardProductName);
        tvActDate = view.findViewById(R.id.tvCardActiveDate);
        tvExpDate = view.findViewById(R.id.tvCardExpDate);

        cardBlock = view.findViewById(R.id.cardBlock);
        cardResetPin = view.findViewById(R.id.cardResetPin);
        cardLimit = view.findViewById(R.id.cardLimit);
        cardStatement = view.findViewById(R.id.cardStatement);

        cardStatusCard = view.findViewById(R.id.cardStatusCard);


        tvCurrentDate.setText(CommonUtils.setCurrentDate());
        tvDashboard.setText("Dashboard");

        CommonUtils.setGradientColor(tvDashboard);
        CommonUtils.setGradientColor(tvQuickAccess);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvBalance);

        ftcHomeActivity = (FtcHomeActivity) getActivity();

    }


    public void clickListener(){

        cardBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ftcHomeActivity!=null){
                    ftcHomeActivity.drawerItemClick("cardManagement");
                    ftcHomeActivity.subMenuClicked(ftcHomeActivity.cardhotlistCard,true);
                    ftcHomeActivity.replaceFragment(new FtcCardBlockFragment());
                }
            }
        });

        cardResetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ftcHomeActivity!=null){
                    ftcHomeActivity.drawerItemClick("cardManagement");
                    ftcHomeActivity.subMenuClicked(ftcHomeActivity.resetpinCard,true);
                    ftcHomeActivity.replaceFragment(new FtcResetPinFragment());
                }
            }
        });

        cardLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ftcHomeActivity!=null){
                    ftcHomeActivity.drawerItemClick("cardManagement");
                    ftcHomeActivity.subMenuClicked(ftcHomeActivity.cardLimitCard,true);
                    ftcHomeActivity.replaceFragment(new FtcCardLimitFragment());
                }
            }
        });

        cardStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ftcHomeActivity!=null){
                    ftcHomeActivity.drawerItemClick("cardManagement");
                    ftcHomeActivity.subMenuClicked(ftcHomeActivity.cardStatementCard,true);
                    ftcHomeActivity.replaceFragment(new FtcCardStatementFragment());
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
           // tvCardStatus.setText(loginResponse.getFtc().getCardDetails().get(position).getCardStatus());
            tvProductName.setText(loginResponse.getFtc().getCardDetails().get(position).getProductName());
            tvActDate.setText(loginResponse.getFtc().getCardDetails().get(position).getCardActivDate().substring(3,5) +" / "+ loginResponse.getFtc().getCardDetails().get(position).getCardActivDate().substring(6));
            tvExpDate.setText(loginResponse.getFtc().getCardDetails().get(position).getCardExpiryDate().substring(3,5)+" / "+loginResponse.getFtc().getCardDetails().get(position).getCardExpiryDate().substring(6));

            CardProxyNumber = loginResponse.getFtc().getCardDetails().get(position).getProxyNumber();
            cardPosition = position;

            currentCardStatus = loginResponse.getFtc().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("ACTIVE") || currentCardStatus.equals("A")){
                tvCardStatus.setText("Active");
                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));

            }else if(currentCardStatus.equals("BLOCKED")){
                tvCardStatus.setText("Blocked");
                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }else{
                tvCardStatus.setText("Inactive");
                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }


            FtcBalanceAdapter ftcBalanceAdapter = new FtcBalanceAdapter(getActivity(),position,loginResponse.ftc);
            rvBalance.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            rvBalance.setAdapter(ftcBalanceAdapter);

        }

    }
}