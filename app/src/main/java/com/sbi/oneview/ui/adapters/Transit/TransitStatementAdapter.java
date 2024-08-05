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

        if (data.getStatements().get(position).getDate().length()==8 && data.getStatements().get(position).getTime().length()==6)
        {
            String date = data.getStatements().get(position).getDate();
            String day = date.substring(0, 2);
            String month = date.substring(2, 4);
            String year = date.substring(4, 8);

            String time = data.getStatements().get(position).getTime();
            String hour = time.substring(0, 2);
            String minute = time.substring(2, 4);
            String second = time.substring(4, 6);

            holder.tvDateTime.setText(day + "/" + month + "/" + year+"\n "+hour + ":" + minute + ":" + second);
        }else{
            holder.tvDateTime.setText(data.getStatements().get(position).getDate().length());
        }
        String txnDate = data.getStatements().get(position).getDate();

        String status = data.getStatements().get(position).getStatus();


        holder.tvForm.setText(data.getStatements().get(position).getTxnForm()!=null ? data.getStatements().get(position).getTxnForm():"NA");
        holder.tvStatus.setText(status.substring(0,7).equalsIgnoreCase("Success")? "Success":"Failed");
        holder.tvDetails.setText("Amount: "+data.getStatements().get(position).getAmount()+"\n"+"Merchant Name: "+data.getStatements().get(position).getMerchantName());

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
