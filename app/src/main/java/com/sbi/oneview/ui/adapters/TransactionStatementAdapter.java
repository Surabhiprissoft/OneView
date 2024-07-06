package com.sbi.oneview.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.InrCardStatement.Data;
import com.sbi.oneview.network.ResponseModel.InrCardStatement.TxnResponsesItem;

public class TransactionStatementAdapter extends RecyclerView.Adapter<TransactionStatementAdapter.ViewHolder>{


    private final Context context;
    private final com.sbi.oneview.network.ResponseModel.InrCardStatement.Data data;


    public TransactionStatementAdapter(Context context, Data data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TransactionStatementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_statement_single_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionStatementAdapter.ViewHolder holder, int position) {

        String txnDate = data.getTxnResponses().get(position).getTxnDate();
        holder.tvDateTime.setText(txnDate.substring(0,2)+"/"+txnDate.substring(2,4)+"/"+txnDate.substring(4,8)+" "+data.getTxnResponses().get(position).getTxnTime());

        holder.tvForm.setText(data.getTxnResponses().get(position).getTxnForm());
        holder.tvStatus.setText(data.getTxnResponses().get(position).getTxnAmount().replaceFirst("^0+(?!$)", "")+"\n"+data.getTxnResponses().get(position).getTxnStatus());

        
    }

    @Override
    public int getItemCount() {
        return data.getTxnResponses().size();
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
