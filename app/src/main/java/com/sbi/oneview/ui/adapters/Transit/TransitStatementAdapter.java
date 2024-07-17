package com.sbi.oneview.ui.adapters.Transit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.TransitStatement.Data;
import com.sbi.oneview.ui.adapters.TransactionStatementAdapter;

public class TransitStatementAdapter extends RecyclerView.Adapter<TransitStatementAdapter.ViewHolder> {

    private final Context context;
    private final Data data;

    public TransitStatementAdapter(Context context, Data data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TransitStatementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_statement_single_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransitStatementAdapter.ViewHolder holder, int position) {

        String txnDate = data.getStatements().get(position).getDate();

        holder.tvDateTime.setText(txnDate);
        holder.tvForm.setText(data.getStatements().get(position).getTxnForm());
        holder.tvStatus.setText(data.getStatements().get(position).getStatus());
        holder.tvDetails.setText(data.getStatements().get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return data.getStatements().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateTime,tvDetails,tvForm,tvStatus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvDetails = itemView.findViewById(R.id.tvDescription);
            tvForm = itemView.findViewById(R.id.tvForm);
            tvStatus = itemView.findViewById(R.id.tvStatus);

        }

    }
}
