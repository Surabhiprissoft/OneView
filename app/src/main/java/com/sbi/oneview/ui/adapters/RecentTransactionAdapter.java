package com.sbi.oneview.ui.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.MiniStatement.CardMiniStatementResponseModel;
import com.sbi.oneview.network.ResponseModel.MiniStatement.Data;
import com.sbi.oneview.network.ResponseModel.MiniStatement.TxnResponsesItem;
import com.sbi.oneview.utils.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentTransactionAdapter extends RecyclerView.Adapter<RecentTransactionAdapter.ViewHolder> {

    private final Context context;
    private Data miniStatementResponse;
    Dialog dialog;


    public RecentTransactionAdapter(Context context,Data miniStatementResponse) {
        this.context = context;
        this.miniStatementResponse = miniStatementResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_transaction_single_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        dialog = new Dialog(context);
        holder.layoutTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSingleTransaction(miniStatementResponse,position);
            }
        });

        holder.tvTnxId.setText(miniStatementResponse.getTxnResponses().get(position).getTxnId());
        holder.tvRecievedUrl.setText(miniStatementResponse.getTxnResponses().get(position).getMerchantName()+"/"+miniStatementResponse.getTxnResponses().get(position).getTxnDescr()+"/"+miniStatementResponse.getTxnResponses().get(position).getAccountType());
        holder.tvAmount.setText(context.getString(R.string.Rs)+" "+miniStatementResponse.getTxnResponses().get(position).getTxnAmount().replaceFirst("^0+(?!$)", ""));
        holder.tvTnxsForm.setText(miniStatementResponse.getTxnResponses().get(position).getTxnForm());
        holder.tvDayDate.setText(miniStatementResponse.getTxnResponses().get(position).getTxnDate().substring(0,2));
        holder.tvMonthYear.setText(getMonthAbbreviation(miniStatementResponse.getTxnResponses().get(position).getTxnDate())+" "+miniStatementResponse.getTxnResponses().get(position).getTxnDate().substring(4));


        if(miniStatementResponse.getTxnResponses().get(position).getTxnForm().equals("CREDIT")){
            holder.tvTnxsForm.setTextColor(context.getResources().getColor(R.color.creditTransaction));
            holder.tvAmount.setTextColor(context.getResources().getColor(R.color.creditTransaction));
        }else if(miniStatementResponse.getTxnResponses().get(position).getTxnForm().equals("DEBIT")){
            holder.tvTnxsForm.setTextColor(context.getResources().getColor(R.color.debitedTransaction));
            holder.tvAmount.setTextColor(context.getResources().getColor(R.color.debitedTransaction));
        }else{
            holder.tvTnxsForm.setTextColor(context.getResources().getColor(R.color.failedTransaction));
            holder.tvAmount.setTextColor(context.getResources().getColor(R.color.failedAmountTransaction));
        }


    }

    @Override
    public int getItemCount() {
        return miniStatementResponse.getTxnResponses().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutTransaction;
        TextView tvRecievedUrl,tvTnxId,tvAmount,tvTnxsForm,tvDayDate,tvMonthYear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutTransaction = itemView.findViewById(R.id.cardDate);
            tvRecievedUrl = itemView.findViewById(R.id.tvRecievedUrl);
            tvTnxId = itemView.findViewById(R.id.tvTxnId);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvDayDate = itemView.findViewById(R.id.tvDayDate);
            tvMonthYear = itemView.findViewById(R.id.tvMonthYear);
            tvTnxsForm = itemView.findViewById(R.id.tvTxnsForm);

        }
    }


    public static String getMonthAbbreviation(String dateString) {
        // Extract the month substring (3rd and 4th characters)
        String monthString = dateString.substring(2, 4);

        // Convert the month substring to an integer
        int month = Integer.parseInt(monthString);

        // Map the integer value to the corresponding month abbreviation
        Map<Integer, String> monthMap = new HashMap<>();
        monthMap.put(1, "Jan");
        monthMap.put(2, "Feb");
        monthMap.put(3, "Mar");
        monthMap.put(4, "Apr");
        monthMap.put(5, "May");
        monthMap.put(6, "Jun");
        monthMap.put(7, "Jul");
        monthMap.put(8, "Aug");
        monthMap.put(9, "Sep");
        monthMap.put(10, "Oct");
        monthMap.put(11, "Nov");
        monthMap.put(12, "Dec");

        // Return the month abbreviation
        return monthMap.getOrDefault(month, "Invalid month");
    }


    public void showSingleTransaction(Data miniStatementResponse,int position){


        dialog.setContentView(R.layout.detail_info_recent_transaction);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        TextView tvCustomerdetails,tvCardNumberValue,card_status_value,description_value,form_value,amount_value,type_value,date_value,time_value,transaction_id_value,transaction_status_value,failure_reason_value,wallet_value,merchant_value,merchant_id_value,terminal_id_value,mcc_value,settlement_status_value,currency_value;
        ImageView imgClose;

        imgClose = dialog.findViewById(R.id.imgClose);
        tvCustomerdetails = dialog.findViewById(R.id.tvCustomerdetails);
        tvCardNumberValue = dialog.findViewById(R.id.tvCardNumberValue);
        card_status_value = dialog.findViewById(R.id.card_status_value);
        description_value = dialog.findViewById(R.id.description_value);
        form_value = dialog.findViewById(R.id.form_value);
        amount_value = dialog.findViewById(R.id.amount_value);
        type_value = dialog.findViewById(R.id.type_value);
        date_value = dialog.findViewById(R.id.date_value);
        time_value = dialog.findViewById(R.id.time_value);
        transaction_id_value = dialog.findViewById(R.id.transaction_id_value);
        transaction_status_value = dialog.findViewById(R.id.transaction_status_value);
        failure_reason_value = dialog.findViewById(R.id.failure_reason_value);
        wallet_value = dialog.findViewById(R.id.wallet_value);
        merchant_value = dialog.findViewById(R.id.merchant_value);
        merchant_id_value = dialog.findViewById(R.id.merchant_id_value);
        terminal_id_value = dialog.findViewById(R.id.terminal_id_value);
        mcc_value = dialog.findViewById(R.id.mcc_value);
        settlement_status_value = dialog.findViewById(R.id.settlement_status_value);
        currency_value = dialog.findViewById(R.id.currency_value);

        CommonUtils.setGradientColor(tvCustomerdetails);

        tvCardNumberValue.setText(miniStatementResponse.getCardNumber());
        card_status_value.setText(miniStatementResponse.getCardStatus());
        description_value.setText(miniStatementResponse.getTxnResponses().get(position).getTxnDescr());
        form_value.setText(miniStatementResponse.getTxnResponses().get(position).getTxnForm());
        amount_value.setText(miniStatementResponse.getTxnResponses().get(position).getTxnAmount().replaceFirst("^0+(?!$)", ""));
        type_value.setText(miniStatementResponse.getTxnResponses().get(position).getTxnType());
        date_value.setText(miniStatementResponse.getTxnResponses().get(position).getTxnDate());
        time_value.setText(miniStatementResponse.getTxnResponses().get(position).getTxnTime());
        transaction_id_value.setText(miniStatementResponse.getTxnResponses().get(position).getTxnId());
        //transaction_status_value.setText(txnResponsesItem.getTr);
        failure_reason_value.setText(miniStatementResponse.getTxnResponses().get(position).getTxnFailReason());
        wallet_value.setText(miniStatementResponse.getTxnResponses().get(position).getAccountType());
        merchant_value.setText(miniStatementResponse.getTxnResponses().get(position).getMerchantName());
        merchant_id_value.setText(miniStatementResponse.getTxnResponses().get(position).getMerchantId());
        terminal_id_value.setText(miniStatementResponse.getTxnResponses().get(position).getTerminalId());
        mcc_value.setText(miniStatementResponse.getTxnResponses().get(position).getMccCode());
        settlement_status_value.setText(miniStatementResponse.getTxnResponses().get(position).getTxnSettStatus());
        currency_value.setText(miniStatementResponse.getTxnResponses().get(position).getBlngCurrency());

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
