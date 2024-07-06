package com.sbi.oneview.ui.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardAccountDetailsItem;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.Ftc;

public class FtcBalanceAdapter extends RecyclerView.Adapter<FtcBalanceAdapter.ViewHolder> {

    Context context;
    int cardPosition;
    Ftc ftcData;

    public FtcBalanceAdapter(Context context, int cardPosition, Ftc ftcData) {
        this.context = context;
        this.cardPosition = cardPosition;
        this.ftcData = ftcData;
    }

    @NonNull
    @Override
    public FtcBalanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftc_balance_single_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FtcBalanceAdapter.ViewHolder holder, int position) {

        CardAccountDetailsItem cardAccountDetailsItem = ftcData.getCardDetails().get(cardPosition).getCardAccountDetails().get(position);


        if (cardAccountDetailsItem.getCurrencyCode().equals("USD")){
            holder.imgCountry.setImageDrawable(context.getDrawable(R.drawable.usa));
            holder.tvCountryName.setText("United State of America");
            holder.tvBalanceAmount.setText("$"+cardAccountDetailsItem.getAccountBalance());
        }
        else if (cardAccountDetailsItem.getCurrencyCode().equals("EUR")){
            holder.imgCountry.setImageDrawable(context.getDrawable(R.drawable.europe));
            holder.tvCountryName.setText("Europe");
            holder.tvBalanceAmount.setText("€"+cardAccountDetailsItem.getAccountBalance());
        }
        else if (cardAccountDetailsItem.getCurrencyCode().equals("KHR")){
            holder.imgCountry.setImageDrawable(context.getDrawable(R.drawable.europe));
            holder.tvCountryName.setText("KHR");
            holder.tvBalanceAmount.setText("€"+cardAccountDetailsItem.getAccountBalance());
        }
        else if (cardAccountDetailsItem.getCurrencyCode().equals("SGD")){
            holder.imgCountry.setImageDrawable(context.getDrawable(R.drawable.singapore));
            holder.tvCountryName.setText("Singapore");
            holder.tvBalanceAmount.setText("S$"+cardAccountDetailsItem.getAccountBalance());
        }
        else if (cardAccountDetailsItem.getCurrencyCode().equals("CAD")){
            holder.imgCountry.setImageDrawable(context.getDrawable(R.drawable.canada));
            holder.tvCountryName.setText("Canada");
            holder.tvBalanceAmount.setText("CA$"+cardAccountDetailsItem.getAccountBalance());
        }
        else if (cardAccountDetailsItem.getCurrencyCode().equals("AUD")){
            holder.imgCountry.setImageDrawable(context.getDrawable(R.drawable.australia));
            holder.tvCountryName.setText("Australia");
            holder.tvBalanceAmount.setText("AU$"+cardAccountDetailsItem.getAccountBalance());
        }
        else if (cardAccountDetailsItem.getCurrencyCode().equals("JPY")){
            holder.imgCountry.setImageDrawable(context.getDrawable(R.drawable.japan));
            holder.tvCountryName.setText("Japan");
            holder.tvBalanceAmount.setText("¥"+cardAccountDetailsItem.getAccountBalance());
        }
        else if (cardAccountDetailsItem.getCurrencyCode().equals("SAR")){
            holder.imgCountry.setImageDrawable(context.getDrawable(R.drawable.ksa));
            holder.tvCountryName.setText("Saudi Arabia");
            holder.tvBalanceAmount.setGravity(Gravity.START);
            holder.tvBalanceAmount.setText(cardAccountDetailsItem.getAccountBalance()+"ر.س");
        }
        else if (cardAccountDetailsItem.getCurrencyCode().equals("AED")){
            holder.imgCountry.setImageDrawable(context.getDrawable(R.drawable.uae));
            holder.tvCountryName.setText("United Arab Emirates");
            holder.tvBalanceAmount.setGravity(Gravity.START);
            holder.tvBalanceAmount.setText(cardAccountDetailsItem.getAccountBalance()+"د.إ");
        }

        holder.tvCurrency.setText(cardAccountDetailsItem.getCurrencyCode());


    }

    @Override
    public int getItemCount() {
        return ftcData.getCardDetails().get(cardPosition).getCardAccountDetails().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCountry;
        TextView tvCountryName,tvCurrency,tvBalanceAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCountry = itemView.findViewById(R.id.imgCountryFlag);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            tvCurrency = itemView.findViewById(R.id.tvCurrency);
            tvBalanceAmount = itemView.findViewById(R.id.tvBalanceAmount);

        }
    }
}
