package com.sbi.oneview.ui.registration;

import static com.sbi.oneview.utils.CommonUtils.isValidAadharNumber;
import static com.sbi.oneview.utils.CommonUtils.isValidPanCard;
import static com.sbi.oneview.utils.CommonUtils.isValidPassportNumber;
import static com.sbi.oneview.utils.CommonUtils.isValidVoterIdNumber;

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

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.ui.login.LoginActivity;
import com.sbi.oneview.utils.CustomInputFilter;

public class ApplyTransitCardActivity extends AppCompatActivity {

    private Spinner spinnerSelectCard,spinnerSelectOvdType,spinnerSelectSalutation;
    private EditText etFirstName, etMiddleName, etLastName, etDob, etPan,etOvdDetails;
    private AppCompatCheckBox checkBoxDeclaration;
    private MaterialButton btnApply;
    String selectedOVDType ="",selectedSalutation="",selectedCard="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    ((TextView) view.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.transperant_white));
                }
                else
                {
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
                        etOvdDetails.setFilters(new InputFilter[]{new CustomInputFilter()});
                        selectedOVDType = getString(R.string.nprn);

                    } else if (checkedId == 4) {
                        etOvdDetails.setHint(R.string.passport);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_TEXT);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                        selectedOVDType = getString(R.string.passport);

                    } else if (checkedId == 5) {
                        etOvdDetails.setHint(R.string.voter);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_TEXT);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
                        selectedOVDType = getString(R.string.voter);

                    } else if (checkedId == 2) {
                        etOvdDetails.setHint(R.string.job);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_TEXT);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
                        etOvdDetails.setFilters(new InputFilter[]{new CustomInputFilter()});
                        selectedOVDType = getString(R.string.job);

                    } else if (checkedId == 3) {
                        etOvdDetails.setHint(R.string.licence);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_TEXT);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
                        selectedOVDType = getString(R.string.licence);

                    } else if (checkedId == 1) {
                        etOvdDetails.setHint(R.string.aadhar);
                        etOvdDetails.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etOvdDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
                        selectedOVDType = getString(R.string.aadhar);
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
                Intent i = new Intent(ApplyTransitCardActivity.this, SuccessfulRegistrationActivity.class);
                startActivity(i);
                /*if (validateFields())
                {
                    Intent i = new Intent(ApplyTransitCardActivity.this, SuccessfulRegistrationActivity.class);
                    startActivity(i);
                }*/

            }
        });
    }
    private boolean validateFields() {
        boolean isValid = true;

        // Validate Card Ref No


        if (selectedSalutation.equalsIgnoreCase("")) {
            Toast.makeText(App.getAppContext(), getString(R.string.select_salutation), Toast.LENGTH_SHORT).show();
            isValid = false;
        }


        if (TextUtils.isEmpty(etFirstName.getText().toString().trim())) {
            etFirstName.setError(getString(R.string.enter_first_name));
            isValid = false;
        }


        if (TextUtils.isEmpty(etMiddleName.getText().toString().trim())) {
            etMiddleName.setError("Middle Name is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etLastName.getText().toString().trim())) {
            etLastName.setError(getString(R.string.enter_last_name));
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
                } /*else if (ovdNumber.length()<12) {
                    etOvdDetails.setError(getString(R.string.enter_aadhar_number));
                    isValid = false;
                }*/
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
                /*else if(ovdNumber.length()<8)
                {
                    etOvdDetails.setError(getString(R.string.invalid_passport_number));
                    isValid=false;
                }*/
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
                /*else if(ovdNumber.length()<10)
                {
                    etOvdDetails.setError(getString(R.string.invalid_voter_id));
                    isValid=false;
                }*/
            } else if(selectedOVDType.equalsIgnoreCase(App.getAppContext().getString(R.string.job))) {
                String ovdNumber = etOvdDetails.getText().toString().trim();
                if (TextUtils.isEmpty(ovdNumber)) {
                    etOvdDetails.setError(getString(R.string.enter_nrega_job_card_number));
                    isValid = false;
                }
            } else if(selectedOVDType.equalsIgnoreCase(App.getAppContext().getString(R.string.aadhar))){
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

        if (selectedCard.equalsIgnoreCase("")) {
            Toast.makeText(App.getAppContext(), R.string.select_the_card, Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (!checkBoxDeclaration.isChecked()) {
            // Show Toast message indicating that the declaration needs to be accepted
            Toast.makeText(App.getAppContext(), "Please accept the declaration.", Toast.LENGTH_SHORT).show();
            isValid = false;

            // Shake the checkbox to indicate validation failure
            Animation shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
            checkBoxDeclaration.startAnimation(shakeAnimation);
        }
        return isValid;
    }
}