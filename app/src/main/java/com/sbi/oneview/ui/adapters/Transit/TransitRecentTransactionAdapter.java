package com.sbi.oneview.ui.adapters.Transit;

import static com.sbi.oneview.ui.adapters.RecentTransactionAdapter.getMonthAbbreviation;

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
import com.sbi.oneview.network.ResponseModel.MiniStatement.Data;
import com.sbi.oneview.network.ResponseModel.TransitMiniStatement.DataItem;
import com.sbi.oneview.network.ResponseModel.TransitMiniStatement.TransitMiniStatementResponseModel;
import com.sbi.oneview.ui.adapters.RecentTransactionAdapter;
import com.sbi.oneview.utils.CommonUtils;

import java.util.List;

public class TransitRecentTransactionAdapter extends RecyclerView.Adapter<TransitRecentTransactionAdapter.ViewHolder> {

    Context context;
    TransitMiniStatementResponseModel recentTransactionData;

    Dialog dialog;

    public TransitRecentTransactionAdapter(Context context, TransitMiniStatementResponseModel recentTransactionData) {
        this.context = context;
        this.recentTransactionData = recentTransactionData;
    }

    @NonNull
    @Override
    public TransitRecentTransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_transaction_single_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransitRecentTransactionAdapter.ViewHolder holder, int position) {

        List<DataItem> miniStatementResponse;
        miniStatementResponse = recentTransactionData.getData();
        DataItem miniStatement = recentTransactionData.getData().get(position);
        dialog = new Dialog(context);
        holder.layoutTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showSingleTransaction(miniStatement,position);
            }
        });

        String txnForm = miniStatementResponse.get(position).getTxnForm();
        if (txnForm.equals("Credit"))
        {
            holder.tvTnxsForm.setTextColor(context.getColor(R.color.creditTransaction));
            holder.tvAmount.setTextColor(context.getColor(R.color.creditTransaction));
        }else{
            holder.tvTnxsForm.setTextColor(context.getColor(R.color.failedTransaction));
            holder.tvAmount.setTextColor(context.getColor(R.color.failedTransaction));
        }
        holder.tvTnxId.setText(miniStatementResponse.get(position).getTxnId());
        holder.tvRecievedUrl.setText(miniStatementResponse.get(position).getMerchantName()); // +"/"+miniStatementResponse.get(position).getTxnDescr()+"/"+miniStatementResponse.getTxnResponses().get(position).getAccountType());
        holder.tvAmount.setText(context.getString(R.string.Rs)+" "+miniStatementResponse.get(position).getAmount());
        holder.tvTnxsForm.setText(txnForm);
        holder.tvDayDate.setText(miniStatementResponse.get(position).getDate().substring(0,2));
        holder.tvMonthYear.setText(getMonthAbbreviation(miniStatementResponse.get(position).getDate())+" "+miniStatementResponse.get(position).getDate().substring(4));


    }

    @Override
    public int getItemCount() {
        return recentTransactionData.getData().size();
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


    public void showSingleTransaction(DataItem miniStatementResponse, int position){


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
        if (miniStatementResponse.getCardStatus().equalsIgnoreCase("A")){
            card_status_value.setText("Active");
        }
        else if (miniStatementResponse.getCardStatus().equalsIgnoreCase("PHL")){
            card_status_value.setText("Permanently Blocked");
        }
        description_value.setText(miniStatementResponse.getTxnDesc());
        form_value.setText(miniStatementResponse.getTxnForm());
        amount_value.setText(miniStatementResponse.getAmount().replaceFirst("^0+(?!$)", ""));
        type_value.setText(miniStatementResponse.getType());
        if (miniStatementResponse.getDate().length()==8)
        {
            date_value.setText(miniStatementResponse.getDate().substring(0,2)+"/"+miniStatementResponse.getDate().substring(2,4)+"/"+miniStatementResponse.getDate().substring(4,8));
        }
        if (miniStatementResponse.getTime().length()==6)
        {
            time_value.setText(miniStatementResponse.getTime().substring(0,2)+":"+miniStatementResponse.getTime().substring(2,4)+":"+miniStatementResponse.getTime().substring(4));
        }
        transaction_id_value.setText(miniStatementResponse.getTxnId());
        String status = miniStatementResponse.getStatus();
        transaction_status_value.setText(status.substring(0,7).equalsIgnoreCase("Success")? "Success":"Failed");
        failure_reason_value.setText(miniStatementResponse.getStatus().substring(0,7).equalsIgnoreCase("Success") ? "-":miniStatementResponse.getFailedReason());
        wallet_value.setText(miniStatementResponse.getWallet());
        merchant_value.setText(miniStatementResponse.getMerchantName());
        merchant_id_value.setText(miniStatementResponse.getMechantId());
        terminal_id_value.setText(miniStatementResponse.getTerminalId());
        mcc_value.setText(miniStatementResponse.getMcc());
        settlement_status_value.setText(miniStatementResponse.getSettStatus());
        currency_value.setText(miniStatementResponse.getCurrency());

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
