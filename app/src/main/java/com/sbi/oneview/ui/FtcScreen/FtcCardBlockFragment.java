package com.sbi.oneview.ui.FtcScreen;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.base.BaseFragment;
import com.sbi.oneview.base.RequestBaseModel;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.CardBlockUnblockRequestModel;
import com.sbi.oneview.network.RequestModel.CardHotlistRequestModel;
import com.sbi.oneview.network.ResponseModel.BlockUnblockCard.CardBlockUnblockResponseModel;
import com.sbi.oneview.network.ResponseModel.HotlistCard.CardHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Data;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.inrPrepaid.InrPrepaidHomeActivity;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.SharedConfig;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class FtcCardBlockFragment extends BaseFragment implements MyFragmentCallback {


    TextView tvBlockHeading,tvCurrentDate,tvCardBlock,tvMyCards,tvCardDetails;
    TextView tvCardNumber,tvCRN,tvCardStatus,tvProductName,tvActDate,tvExpDate;
    TextView tvCardBlockUnblock,tvCardHotlist,tvTempBlockNote,tvPerBlockNote,tvPerBlockNote1;
    LinearLayout cardBlockUnblock,cardHotlist,layoutBlockUnblock,layoutHotlist,layoutCardStatus;
    MaterialButton btnTempBlockUnblock,btnHotlist;
    String currentCardStatus,token;
    MaterialCardView cardStatusCard;
    Data loginResponse;
    String CardProxyNumber;
    int cardPosition;
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
        loginResponse = SharedConfig.getInstance(getActivity()).getLoginResponse(getActivity());

        List<CardDetailsItem> arrayList = loginResponse.getFtc().getCardDetails();



        CourouselAdapter adapter = new CourouselAdapter(this,getActivity(), arrayList,customIndicatorView,null);

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
        layoutCardStatus = view.findViewById(R.id.layoutCardStatus);
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

        btnTempBlockUnblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (currentCardStatus.equals("ACTIVE")) {
                        showConfirmationDialogue("B","Do you really want to block this card ?");
                    } else  if(currentCardStatus.equals("BLOCKED")){
                        showConfirmationDialogue("U","Do you really want to Un-Block this card ?");
                    }


            }
        });

        btnHotlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentCardStatus.equals("ACTIVE")){
                    showConfirmationDialogue("H","Do you really want to permanently block this card ?");
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

            tvCRN.setText(loginResponse.getFtc().getCardDetails().get(position).getProxyNumber());
            tvCardNumber.setText(loginResponse.getFtc().getCardDetails().get(position).getCardNumber());
            //tvCardStatus.setText(loginResponse.getFtc().getCardDetails().get(position).getCardStatus());
            tvProductName.setText(loginResponse.getFtc().getCardDetails().get(position).getProductName());
            tvActDate.setText(loginResponse.getFtc().getCardDetails().get(position).getCardActivDate().substring(3,5) +" / "+ loginResponse.getFtc().getCardDetails().get(position).getCardActivDate().substring(6));
            tvExpDate.setText(loginResponse.getFtc().getCardDetails().get(position).getCardExpiryDate().substring(3,5)+" / "+loginResponse.getFtc().getCardDetails().get(position).getCardExpiryDate().substring(6));

            CardProxyNumber = loginResponse.getFtc().getCardDetails().get(position).getProxyNumber();
            cardPosition = position;
            token = loginResponse.getToken();

            currentCardStatus = loginResponse.getFtc().getCardDetails().get(position).getCardStatus();

            if (currentCardStatus.equals("ACTIVE") || currentCardStatus.equals("A")){
                tvCardStatus.setText("ACTIVE");
                btnTempBlockUnblock.setBackgroundColor(Color.RED);
                btnTempBlockUnblock.setText("Temporary Block");
                tvTempBlockNote.setVisibility(View.GONE);
                tvPerBlockNote.setVisibility(View.GONE);
                tvPerBlockNote1.setVisibility(View.GONE);


                tvCardStatus.setTextColor(Color.BLACK);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.activeCardBackground));

            }else if(currentCardStatus.equals("BLOCKED")){
                tvCardStatus.setText("BLOCKED");
                btnTempBlockUnblock.setBackgroundColor(getResources().getColor(R.color.creditTransaction));
                btnTempBlockUnblock.setText("Unblock");
                tvTempBlockNote.setVisibility(View.VISIBLE);
                tvPerBlockNote.setVisibility(View.GONE);
                tvPerBlockNote1.setVisibility(View.GONE);

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }else{
                tvCardStatus.setText("INACTIVE");
                tvTempBlockNote.setVisibility(View.GONE);
                tvPerBlockNote.setVisibility(View.VISIBLE);
                tvPerBlockNote1.setVisibility(View.VISIBLE);

                tvCardStatus.setTextColor(Color.WHITE);
                layoutCardStatus.setBackgroundColor(getResources().getColor(R.color.failedTransaction));

            }
        }

    }

    public void BlockCard(String proxyNumber,int position,String token){

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        CardBlockUnblockRequestModel cardBlockUnblockRequestModel = new CardBlockUnblockRequestModel();
        cardBlockUnblockRequestModel.setProxyNumber(proxyNumber);
        cardBlockUnblockRequestModel.setSId("");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(cardBlockUnblockRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);



        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.CardBlock(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
                @Override
                public void onSuccess(Call<String> call, Response<String> response) {

                    if (response.isSuccessful()){

                        String encryptedResponse = response.body();
                        encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                        ObjectMapper om = new ObjectMapper();
                        ResponseBaseModel responseBaseModel = null;
                        JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                        try {
                            responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                        }catch (Exception e)
                        {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }

                        if (responseBaseModel!=null) {

                            if (responseBaseModel.getStatusCode()==200){

                                CardBlockUnblockResponseModel cardBlockUnblockResponseModel = null;

                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    cardBlockUnblockResponseModel = om1.readValue(jsonString, CardBlockUnblockResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (cardBlockUnblockResponseModel!=null){

                                    if (cardBlockUnblockResponseModel.getStatusCode()==200){

                                        CommonUtils.showSuccessDialogue(getActivity(),"Congratulation, Your Card has been block successfully.");
                                        loginResponse.ftc.cardDetails.get(position).setCardStatus("BLOCKED");
                                        currentCardStatus="BLOCKED";
                                        SharedConfig.getInstance(getActivity()).saveLoginResponse(getActivity(),loginResponse);
                                        onPositionChange(cardPosition);

                                    }
                                }

                            }

                        }else{
                            Toast.makeText(getActivity(), "responseBaseModel"+getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();

                        }

                    }
                    else{

                        String encryptedResponse ="";
                        try {
                            encryptedResponse = response.errorBody().string();
                        } catch (IOException e) {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }
                        encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                        ObjectMapper om = new ObjectMapper();
                        ResponseBaseModel responseBaseModel = null;
                        JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                        try {
                            responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                        }catch (Exception e)
                        {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }

                        if (responseBaseModel!=null)
                        {
                            Toast.makeText(getActivity(), ""+responseBaseModel.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    hideLoading();
                }

                @Override
                public void onResponseBodyNull(Call<String> call, Response<String> response) {
                    hideLoading();

                }

                @Override
                public void onResponseUnsuccessful(Call<String> call, Response<String> response) {
                    hideLoading();

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    hideLoading();

                }

                @Override
                public void onInternalServerError() {
                    hideLoading();

                }
            });


        }else{
            Toast.makeText(getActivity(),  getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }
    }

    public void UnblockCard(String cardProxyNumber,int cardPosition,String token){

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        CardBlockUnblockRequestModel cardBlockUnblockRequestModel = new CardBlockUnblockRequestModel();
        cardBlockUnblockRequestModel.setProxyNumber(cardProxyNumber);
        cardBlockUnblockRequestModel.setSId("");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(cardBlockUnblockRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);

        if (NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.CardUnBlock(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
                @Override
                public void onSuccess(Call<String> call, Response<String> response) {

                    if (response.isSuccessful()){

                        String encryptedResponse = response.body();
                        encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                        ObjectMapper om = new ObjectMapper();
                        ResponseBaseModel responseBaseModel = null;
                        JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                        try {
                            responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                        }catch (Exception e)
                        {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }

                        if (responseBaseModel!=null) {

                            if (responseBaseModel.getStatusCode() == 200) {

                                CardBlockUnblockResponseModel cardBlockUnblockResponseModel = null;

                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    cardBlockUnblockResponseModel = om1.readValue(jsonString, CardBlockUnblockResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }


                                if (cardBlockUnblockResponseModel!=null){

                                    if (cardBlockUnblockResponseModel.getStatusCode()==200){
                                        CommonUtils.showSuccessDialogue(getActivity(),getResources().getString(R.string.unblock_successfully));
                                        loginResponse.ftc.cardDetails.get(cardPosition).setCardStatus("ACTIVE");
                                        currentCardStatus="ACTIVE";
                                        SharedConfig.getInstance(getActivity()).saveLoginResponse(getActivity(),loginResponse);
                                        onPositionChange(cardPosition);
                                    }
                                }



                            }
                        }


                    }else
                    {

                        String encryptedResponse ="";
                        try {
                            encryptedResponse = response.errorBody().string();
                        } catch (IOException e) {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }
                        encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                        ObjectMapper om = new ObjectMapper();
                        ResponseBaseModel responseBaseModel = null;
                        JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                        try {
                            responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                        }catch (Exception e)
                        {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }

                        if (responseBaseModel!=null)
                        {
                            Toast.makeText(getActivity(), ""+responseBaseModel.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    hideLoading();
                }

                @Override
                public void onResponseBodyNull(Call<String> call, Response<String> response) {
                    hideLoading();

                }

                @Override
                public void onResponseUnsuccessful(Call<String> call, Response<String> response) {
                    hideLoading();

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    hideLoading();

                }

                @Override
                public void onInternalServerError() {
                    hideLoading();

                }
            });

        }else{
            Toast.makeText(getActivity(), getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }

    public void hotlistCard(String cardProxyNumber,String token){

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        CardHotlistRequestModel cardHotlistRequestModel = new CardHotlistRequestModel();
        cardHotlistRequestModel.setProxyNumber(cardProxyNumber);
        cardHotlistRequestModel.setAction("CARDLOST");
        cardHotlistRequestModel.setSId("");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(cardHotlistRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);

        if(NetworkUtils.isNetworkConnected(getActivity())){

            APIRequests.CardHotlist(getActivity(), encryptedMsg, randomKey, token, new NetworkResponseCallback<String>() {
                @Override
                public void onSuccess(Call<String> call, Response<String> response) {

                    if (response.isSuccessful()){

                        String encryptedResponse = response.body();
                        encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                        ObjectMapper om = new ObjectMapper();
                        ResponseBaseModel responseBaseModel = null;
                        JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                        try {
                            responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                        }catch (Exception e)
                        {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }

                        if (responseBaseModel!=null) {

                            if (responseBaseModel.getStatusCode() == 200) {

                                CardHotlistResponseModel cardHotlistResponseModel = null;
                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    cardHotlistResponseModel = om1.readValue(jsonString, CardHotlistResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if(cardHotlistResponseModel!=null){

                                    if (cardHotlistResponseModel.getStatusCode()==200){

                                        CommonUtils.showSuccessDialogue(getActivity(),"Congratulation, Your Card has been permanently hotlisted.");
                                        loginResponse.ftc.cardDetails.get(cardPosition).setCardStatus("INACTIVE");
                                        currentCardStatus="INACTIVE";
                                        SharedConfig.getInstance(getActivity()).saveLoginResponse(getActivity(),loginResponse);
                                        onPositionChange(cardPosition);

                                    }
                                }


                            }
                        }

                    }else{

                        String encryptedResponse ="";
                        try {
                            encryptedResponse = response.errorBody().string();
                        } catch (IOException e) {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }
                        encryptedResponse = encryptedResponse.replaceAll("^\"|\"$", "");

                        ObjectMapper om = new ObjectMapper();
                        ResponseBaseModel responseBaseModel = null;
                        JsonNode node = (JsonNode) CipherEncryption.decryptMessage(encryptedResponse, randomKey);
                        try {
                            responseBaseModel = om.treeToValue(node, ResponseBaseModel.class);
                        }catch (Exception e)
                        {
                            Log.d("EXCEPTION",e.getLocalizedMessage());
                        }

                        if (responseBaseModel!=null)
                        {
                            Toast.makeText(getActivity(), ""+responseBaseModel.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    hideLoading();
                }

                @Override
                public void onResponseBodyNull(Call<String> call, Response<String> response) {
                    hideLoading();

                }

                @Override
                public void onResponseUnsuccessful(Call<String> call, Response<String> response) {
                    hideLoading();

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    hideLoading();

                }

                @Override
                public void onInternalServerError() {
                    hideLoading();

                }
            });

        }else{
            Toast.makeText(getActivity(), ""+getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
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
                    BlockCard(CardProxyNumber,cardPosition,token);
                }
                else if (opration.equals("U")){
                    UnblockCard(CardProxyNumber,cardPosition,token);
                }
                else if (opration.equals("H")){
                    hotlistCard(CardProxyNumber,token);
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