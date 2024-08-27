package com.sbi.oneview.ui.registration;

import static com.sbi.oneview.utils.CommonUtils.isValidAadharNumber;
import static com.sbi.oneview.utils.CommonUtils.isValidPanCard;
import static com.sbi.oneview.utils.CommonUtils.isValidPassportNumber;
import static com.sbi.oneview.utils.CommonUtils.isValidVoterIdNumber;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.base.BaseActivity;
import com.sbi.oneview.base.ResponseBaseModel;
import com.sbi.oneview.network.APIRequests;
import com.sbi.oneview.network.NetworkResponseCallback;
import com.sbi.oneview.network.RequestModel.Transit.ProcessEformRequestModel;
import com.sbi.oneview.network.RequestModel.Transit.ValidateEformRequestModel;
import com.sbi.oneview.network.ResponseModel.ProcessEform.ProcessEformResponseModel;
import com.sbi.oneview.network.ResponseModel.TransitRequestHotlist.TransitRequestHotlistResponseModel;
import com.sbi.oneview.network.ResponseModel.ValidateEform.ValidateEformResponseModel;
import com.sbi.oneview.ui.CallBackListner.OtpDialogueCallBack;
import com.sbi.oneview.ui.transitScreen.CardHotlistFragment;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomInputFilter;
import com.sbi.oneview.utils.NetworkUtils;
import com.sbi.oneview.utils.OTPVerificationDialog;
import com.sbi.oneview.utils.encryption.CipherEncryption;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ApplyTransitCardActivity extends BaseActivity implements OtpDialogueCallBack {

    private Spinner spinnerSelectCard,spinnerSelectOvdType,spinnerSelectSalutation;
    private EditText etFirstName, etMiddleName, etLastName, etDob, etPan,etOvdDetails,etMobile;
    private AppCompatCheckBox checkBoxDeclaration;
    private MaterialButton btnApply;
    String productCode,selectedDate="",currentTxnId="";
    String selectedOVDType ="",selectedSalutation="",selectedCard="";
    OTPVerificationDialog otpVerificationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apply_transit_card);
        initWidgets();
        onClickListners();
    }

    private void initWidgets()
    {
        // Initialize Spinners
        spinnerSelectCard = findViewById(R.id.spinner_selectcard);
        spinnerSelectSalutation = findViewById(R.id.spinner_selectsalutation);
        spinnerSelectOvdType = findViewById(R.id.spinner_selectovdType);

        // Initialize EditTexts
        etFirstName = findViewById(R.id.et_firstname);
        etMiddleName = findViewById(R.id.et_middlename);
        etLastName = findViewById(R.id.et_lastname);
        etMobile = findViewById(R.id.et_mobile);
        etDob = findViewById(R.id.et_dob);
        etPan = findViewById(R.id.et_pan);
        etOvdDetails = findViewById(R.id.et_ovddetails);

        // Initialize CheckBox
        checkBoxDeclaration = findViewById(R.id.checkBox_declaration);

        // Initialize MaterialButton
        btnApply = findViewById(R.id.btnApply);

        String[] salutationArray = getResources().getStringArray(R.array.salutations_array);
        ArrayAdapter<String> salutationAdapter =new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, salutationArray);
        spinnerSelectSalutation.setAdapter(salutationAdapter);


        String[]cards_array  = getResources().getStringArray(R.array.cards_array);
        ArrayAdapter<String> cardsAdapter =new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, cards_array);
        spinnerSelectCard.setAdapter(cardsAdapter);

        String[] ovdType_array = getResources().getStringArray(R.array.ovdType_array);
        ArrayAdapter<String> ovdType_adapter =new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, ovdType_array);
        spinnerSelectOvdType.setAdapter(ovdType_adapter);
// Set hint text programmatically

    }

    public void onClickListners()
    {
        spinnerSelectCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if(i==0)
                {
                    selectedCard="";
                    etPan.setVisibility(View.GONE);
                    ((TextView) view.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.transperant_white));
                }
                else
                {
                    if (i==1)
                    {
                        productCode = "215";
                      //  productCode = "266"; //UAT
                        etPan.setVisibility(View.VISIBLE);
                    }else if(i==2){
                        productCode = "208";
                      //  productCode = "262"; //UAT
                        etPan.setVisibility(View.GONE);
                    }else if(i==3){
                        productCode = "216";
                       // productCode = "267"; //UAT
                        etPan.setVisibility(View.VISIBLE);
                    }else if(i==4){
                        productCode = "216";
                      //  productCode = "267"; //UAT
                        etPan.setVisibility(View.VISIBLE);
                    }else if(i==5){
                        productCode = "213";
                     //   productCode = "263"; //UAT
                        etPan.setVisibility(View.GONE);
                    }else if(i==6){
                        productCode = "217";
                    //    productCode = "270"; //UAT
                        etPan.setVisibility(View.VISIBLE);
                    }

                    selectedCard=adapterView.getSelectedItem().toString();
                    ((TextView) view.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.hinttext_transperant_white));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerSelectOvdType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int checkedId, long l) {

                etOvdDetails.setText("");
                etOvdDetails.setError(null);
                if(checkedId==0)
                {
                    etOvdDetails.setHint("OVD Details");
                    selectedOVDType = "";
                    ((TextView) view.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.transperant_white));
                }
                else
                {
                    ((TextView) view.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.hinttext_transperant_white));

                    if (checkedId == 6) {
                        etOvdDetails.setHint(R.string.nprn);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_TEXT);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
                       // etOvdDetails.setFilters(new InputFilter[]{new CustomInputFilter()});
                        selectedOVDType = "NPR_NUMBER";

                    } else if (checkedId == 4) {
                        etOvdDetails.setHint(R.string.passport);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_TEXT);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                        selectedOVDType = "PASSPORT_NUMBER";

                    } else if (checkedId == 5) {
                        etOvdDetails.setHint(R.string.voter);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_TEXT);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
                        selectedOVDType = "VOTER_ID";

                    } else if (checkedId == 2) {
                        etOvdDetails.setHint(R.string.job);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_TEXT);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
                        //etOvdDetails.setFilters(new InputFilter[]{new CustomInputFilter()});
                        selectedOVDType = "NREGA_JOB_CARD";

                    } else if (checkedId == 3) {
                        etOvdDetails.setHint(R.string.licence);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_TEXT);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
                        selectedOVDType = "LICENSE_NUMBER";

                    } else if (checkedId == 1) {
                        etOvdDetails.setHint(R.string.aadhar);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
                        selectedOVDType = "AADHAR_NUMBER";
                    }
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerSelectSalutation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                { selectedSalutation = "";
                    ((TextView) view.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.transperant_white));
                }
                else
                {
                    selectedSalutation = adapterView.getSelectedItem().toString();
                    ((TextView) view.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.hinttext_transperant_white));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Intent i = new Intent(ApplyTransitCardActivity.this, SuccessfulRegistrationActivity.class);
                //startActivity(i);
                if (validateFields())
                {
                    validateEform();
                }



            }
        });

        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = CommonUtils.showDatePickerDialog(ApplyTransitCardActivity.this, etDob);


                // etDob.setText(selectedDate);
            }
        });

    }
    private boolean validateFields() {
        boolean isValid = true;

        // Validate Card Ref No



        if (TextUtils.isEmpty(etFirstName.getText().toString().trim())) {
            etFirstName.setError(getString(R.string.enter_first_name));
            isValid = false;
        }


        /*if (TextUtils.isEmpty(etMiddleName.getText().toString().trim())) {
            etMiddleName.setError("Middle Name is required");
            isValid = false;
        }*/

        if (TextUtils.isEmpty(etLastName.getText().toString().trim())) {
            etLastName.setError(getString(R.string.enter_last_name));
            isValid = false;
        }

        if (TextUtils.isEmpty(etMobile.getText().toString().trim())) {
            etMobile.setError(getString(R.string.enter_mobile));
            isValid = false;
        }

        // Validate Mobile Number

       /* if (TextUtils.isEmpty(etMobileNumber.getText().toString().trim())) {
            etMobileNumber.setError(getString(R.string.enter_mobile_number));
            isValid = false;
        }
*/
        // Validate OVD Type

        if (!TextUtils.isEmpty(etPan.getText().toString().trim()))
        {
            if((!isValidPanCard(etPan.getText().toString())))
            {
                etPan.setError(getString(R.string.invalid_pan_number));
                //Toast.makeText(getActivity(), R.string.invalid_pan_number, Toast.LENGTH_SHORT).show();
                isValid = false;
            }

        }

        if (selectedCard.equalsIgnoreCase("")) {
            Toast.makeText(App.getAppContext(), R.string.select_the_card, Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        else{

            if (selectedSalutation.equalsIgnoreCase("")) {
                Toast.makeText(App.getAppContext(), getString(R.string.select_salutation), Toast.LENGTH_SHORT).show();
                isValid = false;
            }else{

                if (selectedOVDType.equalsIgnoreCase("")) {
                    Toast.makeText(App.getAppContext(), R.string.ovd_type_is_required, Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else {

                    if (selectedOVDType.equalsIgnoreCase(App.getAppContext().getString(R.string.aadhar))) {
                        String ovdNumber = etOvdDetails.getText().toString().trim();
                        if (TextUtils.isEmpty(ovdNumber)) {
                            etOvdDetails.setError(getString(R.string.enter_aadhar_number));
                            isValid = false;
                        } else if (!isValidAadharNumber(ovdNumber)) {
                            etOvdDetails.setError(getString(R.string.invalid_aadhar_number));
                            isValid = false;
                        }
                    } else if (selectedOVDType.equalsIgnoreCase(App.getAppContext().getString(R.string.passport)))
                    {
                        String ovdNumber = etOvdDetails.getText().toString().trim();
                        if (TextUtils.isEmpty(ovdNumber)) {
                            etOvdDetails.setError(getString(R.string.invalid_passport_number));
                            isValid = false;
                        } else if (!isValidPassportNumber(ovdNumber)) {
                            etOvdDetails.setError(getString(R.string.invalid_passport_number));
                            isValid = false;
                        }
                    } else if(selectedOVDType.equalsIgnoreCase(App.getAppContext().getString(R.string.voter)))
                    {
                        String ovdNumber = etOvdDetails.getText().toString().trim();
                        if (TextUtils.isEmpty(ovdNumber)) {
                            etOvdDetails.setError(getString(R.string.enter_voter_id));
                            isValid = false;
                        } else if (!isValidVoterIdNumber(ovdNumber)) {
                            etOvdDetails.setError(getString(R.string.invalid_voter_id));
                            isValid = false;
                        }
                    } else if(selectedOVDType.equalsIgnoreCase(App.getAppContext().getString(R.string.job))) {
                        String ovdNumber = etOvdDetails.getText().toString().trim();
                        if (TextUtils.isEmpty(ovdNumber)) {
                            etOvdDetails.setError(getString(R.string.enter_nrega_job_card_number));
                            isValid = false;
                        }
                    } else if(selectedOVDType.equalsIgnoreCase(App.getAppContext().getString(R.string.nprn))){
                        String ovdNumber = etOvdDetails.getText().toString().trim();

                        if (TextUtils.isEmpty(ovdNumber)) {
                            etOvdDetails.setError(getString(R.string.enter_nprn));
                            isValid = false;
                        }

                    } else if (selectedOVDType.equalsIgnoreCase(App.getAppContext().getString(R.string.licence))) {
                        String ovdNumber = etOvdDetails.getText().toString().trim();
                        if (TextUtils.isEmpty(ovdNumber)) {
                            etOvdDetails.setError(getString(R.string.enter_driving_licence_number));
                            isValid = false;
                        } else if (ovdNumber.length() < 15) {
                            etOvdDetails.setError(getString(R.string.invalid_driving_licence_number));
                            isValid = false;
                        }

                    }

                }

            }

        }








        if (!checkBoxDeclaration.isChecked()) {
            // Show Toast message indicating that the declaration needs to be accepted
            //Toast.makeText(App.getAppContext(), "Please accept the declaration.", Toast.LENGTH_SHORT).show();
            isValid = false;

            // Shake the checkbox to indicate validation failure
            Animation shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
            checkBoxDeclaration.startAnimation(shakeAnimation);
        }
        return isValid;
    }


    public void validateEform(){

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        ValidateEformRequestModel validateEformRequestModel = new ValidateEformRequestModel();
        validateEformRequestModel.setProductCode(productCode);
        if (!selectedDate.isEmpty()){
            validateEformRequestModel.setDob(selectedDate+"T00:00:00.402");
        }
        validateEformRequestModel.setSalutation(selectedSalutation);
        validateEformRequestModel.setMobileNo(etMobile.getText().toString());
        validateEformRequestModel.setFirstName(etFirstName.getText().toString().trim());
        validateEformRequestModel.setMiddleName(etMiddleName.getText().toString().trim());
        validateEformRequestModel.setLastName(etLastName.getText().toString().trim());
        validateEformRequestModel.setPan(etPan.getText().toString());
        validateEformRequestModel.setOvdType(selectedOVDType);
        validateEformRequestModel.setOvdValue(etOvdDetails.getText().toString());

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(validateEformRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);

        if (NetworkUtils.isNetworkConnected(ApplyTransitCardActivity.this))
        {

            APIRequests.validateEform(this, encryptedMsg, randomKey, new NetworkResponseCallback<String>() {
                @Override
                public void onSuccess(Call<String> call, Response<String> response) {

                    if (response.isSuccessful())
                    {

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

                        if (responseBaseModel != null) {

                            if (responseBaseModel.getStatusCode()==200)
                            {
                                ValidateEformResponseModel validateEformResponseModel = null;
                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    validateEformResponseModel = om1.readValue(jsonString, ValidateEformResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (validateEformResponseModel!=null)
                                {
                                    if (validateEformResponseModel.getStatusCode()==200){

                                        currentTxnId = validateEformResponseModel.getData().getTxnId();
                                        Toast.makeText(ApplyTransitCardActivity.this, ""+validateEformResponseModel.getMessage(), Toast.LENGTH_SHORT).show();

                                        if (otpVerificationDialog != null && otpVerificationDialog.isShowing()) {
                                            otpVerificationDialog.dismiss();
                                        }
                                        otpVerificationDialog = new OTPVerificationDialog(ApplyTransitCardActivity.this::onVerifyClick,ApplyTransitCardActivity.this,etMobile.getText().toString(),"","EFORM");
                                        //otpVerificationDialog.setCancelable(false);
                                        otpVerificationDialog.show();

                                    }
                                }

                            }

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
                            Log.d("MSEF",responseBaseModel.getMessage());
                            Toast.makeText(ApplyTransitCardActivity.this, ""+responseBaseModel.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onVerifyClick(String otp) {

        showLoading();

        String randomKey = CommonUtils.generateRandomString();
        System.out.println("Random Key: " + randomKey);

        ProcessEformRequestModel processEformRequestModel = new ProcessEformRequestModel();
        processEformRequestModel.setOtp(otp);
        processEformRequestModel.setProductCode(productCode);
        processEformRequestModel.setRefTxnId(currentTxnId);
        processEformRequestModel.setSId("");

        ObjectMapper om = new ObjectMapper();
        String req = null;
        try {
            req = om.writeValueAsString(processEformRequestModel);
        } catch (JsonProcessingException e) {
            Log.d("EXCEPTION",""+e.getLocalizedMessage());
        }
        String encryptedMsg = CipherEncryption.encryptMessage(req,randomKey);
        System.out.println("Message : " + encryptedMsg);


        if (NetworkUtils.isNetworkConnected(ApplyTransitCardActivity.this))
        {

            APIRequests.processEform(ApplyTransitCardActivity.this, encryptedMsg, randomKey, new NetworkResponseCallback<String>() {
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

                        if (responseBaseModel!=null)
                        {
                            if (responseBaseModel.getStatusCode()==200){

                                ProcessEformResponseModel processEformResponseModel = null;
                                try{
                                    Object data = responseBaseModel;

                                    // Convert LinkedHashMap to JSON string
                                    ObjectMapper om1 = new ObjectMapper();
                                    String jsonString = om1.writeValueAsString(data);
                                    processEformResponseModel = om1.readValue(jsonString, ProcessEformResponseModel.class);

                                }catch (Exception e){
                                    Log.d("EXCEPTION",""+e.getLocalizedMessage());
                                }

                                if (processEformResponseModel!=null)
                                {
                                    if (processEformResponseModel.getStatusCode()==200)
                                    {
                                        Intent i = new Intent(ApplyTransitCardActivity.this, SuccessfulRegistrationActivity.class);
                                        i.putExtra("TXNID",""+processEformResponseModel.getData().getTempCustRefNumber());
                                        startActivity(i);
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
                            Log.d("MSEF",responseBaseModel.getMessage());
                            Toast.makeText(ApplyTransitCardActivity.this, ""+responseBaseModel.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
        }

    }
}