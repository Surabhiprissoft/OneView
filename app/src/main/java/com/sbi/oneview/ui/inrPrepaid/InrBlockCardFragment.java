package com.sbi.oneview.ui.inrPrepaid;

import android.app.Dialog;
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
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.network.APIClient;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.CardBlockUnblockRequestModel;
import com.sbi.oneview.network.RequestModel.CardHotlistRequestModel;
import com.sbi.oneview.network.ResponseModel.BlockUnblockCard.CardBlockUnblockResponseModel;
import com.sbi.oneview.network.ResponseModel.HotlistCard.CardHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.OTPVerificationDialog;
import com.sbi.oneview.utils.SharedConfig;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class InrBlockCardFragment extends Fragment implements MyFragmentCallback{

    TextView tvHeadingCardBlock,tvCardBlock;
    TextView tvCurrentDate,tvMyCards,tvCardDetails;
    TextView tvCardBlockUnblock,tvCardHotlist,tvTempBlockNote,tvPerBlockNote,tvPerBlockNote1;
    LinearLayout cardBlockUnblock,cardHotlist,layoutBlockUnblock,layoutHotlist,layoutCardStatus;
    InrPrepaidHomeActivity inrPrepaidHomeActivity;
    String currentCardStatus;
    MaterialButton btnTempBlockUnblock,btnHotlist;
    MaterialCardView cardStatusCard;
    Data loginResponse;
    String CardProxyNumber;
    int cardPosition;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate,tvCardBal,tvChipBal,tvViewAll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inr_block_card, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidget(view);
        clickListener();
        changeTab("block");
    }


    public void initWidget(View view){
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);

        tvCardNumber = view.findViewById(R.id.tvCardNumber);
        tvCRN = view.findViewById(R.id.tvCRNNumber);
        tvCardStatus = view.findViewById(R.id.tvCardStatus);
        cardStatusCard = view.findViewById(R.id.cardStatusCard);
        layoutCardStatus = view.findViewById(R.id.layoutCardStatus);
        tvProductName = view.findViewById(R.id.tvCardProductName);
        tvActDate = view.findViewById(R.id.tvCardActiveDate);
        tvExpDate = view.findViewById(R.id.tvCardExpDate);
        tvCardBal = view.findViewById(R.id.tvCardBalance);
        tvChipBal = view.findViewById(R.id.tvChipBalance);
        tvCardBlock = view.findViewById(R.id.tvCardBlock);
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

        tvHeadingCardBlock = view.findViewById(R.id.tvHeader);
        tvHeadingCardBlock.setText("Card Block");
        CommonUtils.setGradientColor(tvHeadingCardBlock);

        tvCurrentDate.setText(CommonUtils.setCurrentDate());

        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvCardBlock);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getPrepaid().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);


        inrPrepaidHomeActivity = (InrPrepaidHomeActivity) getActivity();
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


        btnTempBlockUnblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!currentCardStatus.equals("INACTIVE")) {

                    if (currentCardStatus.equals("ACTIVE")) {
                        showConfirmationDialogue("B","Do you really want to block this card ?");
                    } else {
                        showConfirmationDialogue("U","Do you really want to Un-Block this card ?");
                    }
                }
                else{
                    Toast.makeText(getActivity(), "You card has been permanently block, please contact your branch to activate it.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnHotlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentCardStatus.equals("ACTIVE")){
                    showConfirmationDialogue("H","Do you really want to permanently block this card ?");
                    //hotlistCard(CardProxyNumber);
                }
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

            tvCRN.setText(loginResponse.getPrepaid().getCardDetails().get(position).getProxyNumber());
            tvCardNumber.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardNumber());
            tvCardStatus.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardStatus());
            tvProductName.setText(loginResponse.getPrepaid().getCardDetails().get(position).getProductName());
            tvActDate.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardActivDate().substring(3,5) +" / "+ loginResponse.getPrepaid().getCardDetails().get(position).getCardActivDate().substring(6));
            tvExpDate.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardExpiryDate().substring(3,5)+" / "+loginResponse.getPrepaid().getCardDetails().get(position).getCardExpiryDate().substring(6));


            for(int i=0;i<loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().size();i++){
                if (loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().get(i).getAccountType().equals("Online")){
                    tvCardBal.setText(getResources().getString(R.string.Rs)+""+loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().get(i).getAccountBalance());
                }
                else{
                    tvChipBal.setText(loginResponse.getPrepaid().getCardDetails().get(position).getCardAccountDetails().get(i).getAccountBalance());
                }
            }

            CardProxyNumber = loginResponse.getPrepaid().getCardDetails().get(position).getProxyNumber();
            cardPosition = position;

            currentCardStatus = loginResponse.getPrepaid().getCardDetails().get(position).getCardStatus();
            if (currentCardStatus.equals("ACTIVE")){
                btnTempBlockUnblock.setBackgroundColor(Color.RED);
                btnTempBlockUnblock.setText("Temporary Block");
                tvTempBlockNote.setVisibility(View.GONE);

                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));

            }else if(currentCardStatus.equals("BLOCKED")){
                btnTempBlockUnblock.setBackgroundColor(getResources().getColor(R.color.creditTransaction));
                btnTempBlockUnblock.setText("Unblock");
                tvTempBlockNote.setVisibility(View.VISIBLE);

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }else if (currentCardStatus.equals("INACTIVE")){
                tvPerBlockNote.setVisibility(View.VISIBLE);
                tvPerBlockNote1.setVisibility(View.VISIBLE);

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }


        }

    }

    public void BlockCard(String proxyNumber,int position){

        RequestBaseModel<CardBlockUnblockRequestModel> data = new RequestBaseModel<>();
        CardBlockUnblockRequestModel cardBlockUnblockRequestModel = new CardBlockUnblockRequestModel();

        cardBlockUnblockRequestModel.setProxyNumber(proxyNumber);
        cardBlockUnblockRequestModel.setSId("");

        data.setRequest(cardBlockUnblockRequestModel);

        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.CardBlock(getActivity(), cardBlockUnblockRequestModel, new NetworkResponseCallback<CardBlockUnblockResponseModel>() {
                @Override
                public void onSuccess(Call<CardBlockUnblockResponseModel> call, Response<CardBlockUnblockResponseModel> response) {

                    if (response.body().getStatusCode()==200){
                        Toast.makeText(inrPrepaidHomeActivity, getResources().getString(R.string.your_card_has_been_successfully), Toast.LENGTH_SHORT).show();
                      //  loginResponse.getPrepaid().getCardDetails().get(position).setCardStatus("");
                        loginResponse.prepaid.cardDetails.get(position).setCardStatus("BLOCKED");
                        currentCardStatus="BLOCKED";
                        SharedConfig.getInstance(getActivity()).saveLoginResponse(getActivity(),loginResponse);
                        onPositionChange(cardPosition);
                    }else{
                        Toast.makeText(inrPrepaidHomeActivity, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onResponseBodyNull(Call<CardBlockUnblockResponseModel> call, Response<CardBlockUnblockResponseModel> response) {

                }

                @Override
                public void onResponseUnsuccessful(Call<CardBlockUnblockResponseModel> call, Response<CardBlockUnblockResponseModel> response) {

                }

                @Override
                public void onFailure(Call<CardBlockUnblockResponseModel> call, Throwable t) {
                    Toast.makeText(inrPrepaidHomeActivity, "Something went wrong :"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onInternalServerError() {
                    Toast.makeText(inrPrepaidHomeActivity, "Internal server error, please try again later", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(getActivity(),  getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }
    }

    public void UnblockCard(String cardProxyNumber,int cardPosition){

        RequestBaseModel<CardBlockUnblockRequestModel> data = new RequestBaseModel<>();
        CardBlockUnblockRequestModel cardBlockUnblockRequestModel = new CardBlockUnblockRequestModel();

        cardBlockUnblockRequestModel.setProxyNumber(cardProxyNumber);
        cardBlockUnblockRequestModel.setSId("");

        data.setRequest(cardBlockUnblockRequestModel);

        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.CardUnBlock(getActivity(), cardBlockUnblockRequestModel, new NetworkResponseCallback<CardBlockUnblockResponseModel>() {
                @Override
                public void onSuccess(Call<CardBlockUnblockResponseModel> call, Response<CardBlockUnblockResponseModel> response) {

                    if (response.body().getStatusCode()==200){
                        Toast.makeText(inrPrepaidHomeActivity, getResources().getString(R.string.unblock_successfully), Toast.LENGTH_SHORT).show();
                        loginResponse.prepaid.cardDetails.get(cardPosition).setCardStatus("ACTIVE");
                        currentCardStatus="ACTIVE";
                        SharedConfig.getInstance(getActivity()).saveLoginResponse(getActivity(),loginResponse);
                        onPositionChange(cardPosition);
                    }else{
                        Toast.makeText(inrPrepaidHomeActivity, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onResponseBodyNull(Call<CardBlockUnblockResponseModel> call, Response<CardBlockUnblockResponseModel> response) {

                }

                @Override
                public void onResponseUnsuccessful(Call<CardBlockUnblockResponseModel> call, Response<CardBlockUnblockResponseModel> response) {

                }

                @Override
                public void onFailure(Call<CardBlockUnblockResponseModel> call, Throwable t) {

                }

                @Override
                public void onInternalServerError() {

                }
            });

        }else{
            Toast.makeText(getActivity(), getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }


    public void hotlistCard(String cardProxyNumber){

        RequestBaseModel<CardHotlistRequestModel> data = new RequestBaseModel<>();
        CardHotlistRequestModel cardHotlistRequestModel = new CardHotlistRequestModel();

        cardHotlistRequestModel.setProxyNumber(cardProxyNumber);
        cardHotlistRequestModel.setAction("CARDLOST");
        cardHotlistRequestModel.setSId("");

        data.setRequest(cardHotlistRequestModel);

        if(NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.CardHotlist(getActivity(), cardHotlistRequestModel, new NetworkResponseCallback<CardHotlistResponseModel>() {
                @Override
                public void onSuccess(Call<CardHotlistResponseModel> call, Response<CardHotlistResponseModel> response) {
                    if (response.body().getStatusCode()==200){
                        Toast.makeText(getActivity(), "Your card has been hotlisted successfully", Toast.LENGTH_SHORT).show();
                        loginResponse.prepaid.cardDetails.get(cardPosition).setCardStatus("INACTIVE");
                        currentCardStatus="INACTIVE";
                        SharedConfig.getInstance(getActivity()).saveLoginResponse(getActivity(),loginResponse);
                        onPositionChange(cardPosition);
                    }
                }

                @Override
                public void onResponseBodyNull(Call<CardHotlistResponseModel> call, Response<CardHotlistResponseModel> response) {

                }

                @Override
                public void onResponseUnsuccessful(Call<CardHotlistResponseModel> call, Response<CardHotlistResponseModel> response) {

                }

                @Override
                public void onFailure(Call<CardHotlistResponseModel> call, Throwable t) {

                }

                @Override
                public void onInternalServerError() {

                }
            });
        }else{
            Toast.makeText(inrPrepaidHomeActivity, ""+getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }


    public void showConfirmationDialogue(String opration,String message){

        Dialog dialog = new Dialog(getActivity());

        dialog.setContentView(R.layout.dialog_hotlist_confirmation);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        ImageView closeImg = dialog.findViewById(R.id.imgClose);
        TextView tvMesssage = dialog.findViewById(R.id.h2);
        MaterialButton btnYes = dialog.findViewById(R.id.btnYes);
        MaterialButton btnNo = dialog.findViewById(R.id.btnNo);


        tvMesssage.setText(message);
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (opration.equals("B"))
                {
                    BlockCard(CardProxyNumber,cardPosition);
                }
                else if (opration.equals("U")){
                    UnblockCard(CardProxyNumber,cardPosition);
                }
                else if (opration.equals("H")){
                    hotlistCard(CardProxyNumber);
                }
                dialog.dismiss();
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