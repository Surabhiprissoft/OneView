package com.sbi.oneview.ui.adapters.Transit;

import static com.sbi.oneview.ui.adapters.RecentTransactionAdapter.getMonthAbbreviation;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.TransitMiniStatement.DataItem;
import com.sbi.oneview.network.ResponseModel.TransitMiniStatement.TransitMiniStatementResponseModel;
import com.sbi.oneview.ui.adapters.RecentTransactionAdapter;

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
        dialog = new Dialog(context);
        holder.layoutTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // showSingleTransaction(miniStatementResponse,position);
            }
        });

        holder.tvTnxId.setText(miniStatementResponse.get(position).getTxnId());
        holder.tvRecievedUrl.setText(miniStatementResponse.get(position).getMerchantName()); // +"/"+miniStatementResponse.get(position).getTxnDescr()+"/"+miniStatementResponse.getTxnResponses().get(position).getAccountType());
        holder.tvAmount.setText(context.getString(R.string.Rs)+" "+miniStatementResponse.get(position).getAmount());
        holder.tvTnxsForm.setText(miniStatementResponse.get(position).getTxnForm());
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
}
