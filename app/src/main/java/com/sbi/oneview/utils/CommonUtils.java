package com.sbi.oneview.utils;

import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class CommonUtils {
    static String formattedDate = "";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    public static void changeBallPosition(ImageView topRightImg, ImageView bottomRightImg, ImageView bottomLeftImg){
        // Random number generator with a seed for potentially more predictable behavior
        Random random = new Random(System.currentTimeMillis());

        // Define base values and maximum ranges for translations (all positive)
        int[] baseValues = {100, 100, 150, 150, 50};  // Assuming all base values are positive
        int[] maxRanges = {20, 20, 20, 20, 20};

        // Animate images with random translations
        for (int i = 0; i < baseValues.length; i++) {
            int randomX = baseValues[i] + random.nextInt(maxRanges[i] + 1);
            int randomY = random.nextInt(maxRanges[i] + 1);  // Generate random Y value as well

            // Choose random sign (+ or -) for X and Y translations
            int signX = random.nextBoolean() ? 1 : -1;
            int signY = random.nextBoolean() ? 1 : -1;

            ImageView imageView;  // Assuming you have references to your image views
            switch (i) {
                case 0:
                    imageView = topRightImg;
                    break;
                case 1:
                    imageView = bottomLeftImg;
                    break;
                case 2:
                    imageView = bottomRightImg;
                    break;
                // ... (similar cases for other images)
                default:
                    continue;  // Skip if index is out of bounds
            }

            imageView.animate()
                    .translationXBy(randomX * signX)
                    .translationYBy(randomY * signY)
                    .setDuration(2000)
                    // .setFillAfter(true)
                    .start();
        }
    }

    public static void changeBallPositionWithTime(ImageView topRightImg, ImageView bottomRightImg, ImageView bottomLeftImg,int animTime){
        // Random number generator with a seed for potentially more predictable behavior
        Random random = new Random(System.currentTimeMillis());

        // Define base values and maximum ranges for translations (all positive)
        int[] baseValues = {100, 100, 150, 150, 50};  // Assuming all base values are positive
        int[] maxRanges = {20, 20, 20, 20, 20};

        // Animate images with random translations
        for (int i = 0; i < baseValues.length; i++) {
            int randomX = baseValues[i] + random.nextInt(maxRanges[i] + 1);
            int randomY = random.nextInt(maxRanges[i] + 1);  // Generate random Y value as well

            // Choose random sign (+ or -) for X and Y translations
            int signX = random.nextBoolean() ? 1 : -1;
            int signY = random.nextBoolean() ? 1 : -1;

            ImageView imageView;  // Assuming you have references to your image views
            switch (i) {
                case 0:
                    imageView = topRightImg;
                    break;
                case 1:
                    imageView = bottomLeftImg;
                    break;
                case 2:
                    imageView = bottomRightImg;
                    break;
                // ... (similar cases for other images)
                default:
                    continue;  // Skip if index is out of bounds
            }

            imageView.animate()
                    .translationXBy(randomX * signX)
                    .translationYBy(randomY * signY)
                    .setDuration(animTime)
                    // .setFillAfter(true)
                    .start();
        }
    }

    public static boolean isValidAadharNumber(String aadharNumber) {
        // Modify the regex pattern as needed for Aadhar Number validation
        String regex = "^[2-9]{1}[0-9]{11}$";
        return aadharNumber.matches(regex);
    }

    public static boolean isValidPanCard(String panNumber) {

        String PAN_REGEX = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        // Remove any whitespace and convert to uppercase
        panNumber = panNumber.replaceAll("\\s", "").toUpperCase();

        // Check if the PAN card number matches the expected format
        return panNumber.matches(PAN_REGEX);
    }

    public static boolean isValidNPRN(String ovdNumber) {
        String PAN_REGEX = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        // Remove any whitespace and convert to uppercase
        //  panNumber = panNumber.replaceAll("\\s", "").toUpperCase();

        // Check if the PAN card number matches the expected format
        return ovdNumber.matches(PAN_REGEX);
    }

    // Regex validation for Passport Number
    public static boolean isValidPassportNumber(String passportNumber) {
        // Modify the regex pattern as needed for Passport Number validation
        //String regex = "^[A-Z0-9]{6,9}$";
        String regex = "^[A-Z][1-9][0-9]{5}[1-9]$";
        //String regex = "^[A-PR-WY][1-9]\\\\d\\\\s?\\\\d{4}[1-9]$";

        return passportNumber.matches(regex);
    }

    public static boolean isValidVoterIdNumber(String voterIdNumber) {
        // Modify the regex pattern as needed for Voter ID Number validation
        String regex = "^[A-Z]{3}[0-9]{7}$";
        return voterIdNumber.matches(regex);
    }

    // Regex validation for NREGA Job Card Number
    public static boolean isValidNregaJobCardNumber(String jobCardNumber) {
        // Modify the regex pattern as needed for NREGA Job Card Number validation
        String regex = "^[A-Z0-9]{12}$";
        return jobCardNumber.matches(regex);
    }

    // Regex validation for Driving Licence Number
    public static boolean isValidDrivingLicenceNumber(String licenceNumber) {
        // Modify the regex pattern as needed for Driving Licence Number validation
        //String regex = "^[A-Z0-9]{12}$";
        String regex = "^(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}$";
        return licenceNumber.matches(regex);
    }

    public static String showDatePickerDialog(Context context, EditText etDob) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Month is 0-based, so add 1 to get the correct month
                        formattedDate = String.format("%02d-%02d-%04d", dayOfMonth, monthOfYear + 1, year);

                        etDob.setText(formattedDate);


                    }
                },
                year,
                month,
                day
        );


        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etDob.setText("");
            }
        });
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

        return formattedDate;
    }

    public static String showDatePickerDialogOnTextView(Context context, TextView textView) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Month is 0-based, so add 1 to get the correct month
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, monthOfYear, dayOfMonth);
                        String formattedDate = formatDate(selectedDate);
                        textView.setText(formattedDate);


                    }
                },
                year,
                month,
                day
        );


        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("");
            }
        });
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        Calendar minDate = getDate180DaysBack();
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        datePickerDialog.show();

        return formattedDate;
    }

    public static Calendar getDate180DaysBack() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -180);
        return calendar;
    }

    private static String formatDate(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(date.getTime());
    }


    public static String setCurrentDate()
    {
        Date currentDate = new Date();

        // Define the format for the date string
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

        // Format the current date into a string
        String dateString = dateFormat.format(currentDate);
        return dateString;
    }

    public static void setGradientColor(TextView textView)
    {
        LinearGradient shader = new LinearGradient(
                0f, 0f, 0f, textView.getTextSize(),
                App.getAppContext().getColor(R.color.background_two), App.getAppContext().getColor(R.color.background_one),
                Shader.TileMode.CLAMP
        );
        textView.getPaint().setShader(shader);
    }



    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        try {
            progressDialog.show();
            if (progressDialog.getWindow() != null) {
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            progressDialog.setContentView(R.layout.layout_progress_dialog);

            ImageView imgLoading = progressDialog.findViewById(R.id.imgLaoding);
            imgLoading.setImageResource(R.drawable.rotate_anim_vector);
            ObjectAnimator flipAnimator = ObjectAnimator.ofFloat(imgLoading, "rotationY", 0f, 360f);
            flipAnimator.setDuration(1000); // Duration for one flip
            flipAnimator.setRepeatCount(ObjectAnimator.INFINITE); // Repeat indefinitely
            flipAnimator.setInterpolator(new LinearInterpolator());
            flipAnimator.start();

            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        } catch (Exception ex) {
            Log.e(App.getAppContext().getString(R.string.dialog), ex.getMessage());
        }
        return progressDialog;
    }


    // next two methods to generate 16 byte random key
    public static byte[] generateRandomKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16]; // 4 bytes = 32 bits
        secureRandom.nextBytes(key);
        return key;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(32);

        for (int i = 0; i < 32; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }


    public static String convertToJson(String input) {

        String json = input.replaceAll("\\b(\\w+)\\s*=\\s*", "\"$1\": ");
        json = json.replaceAll("(?<=\": )(?!\\[|\\{|(?:(?:null)[,}\n]))([^,}\n]*)", "\"$1\"");
        return json;
    }

}
