package com.sbi.oneview.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbi.oneview.R;
import com.sbi.oneview.utils.ContactUsMethod;

import java.util.List;

public class ContactUsMethodAdapter extends RecyclerView.Adapter<ContactUsMethodAdapter.ContactUsMethodViewHolder> {

    private Context mContext;
    private List<ContactUsMethod> mContactUsMethodList;

    public ContactUsMethodAdapter(Context context, List<ContactUsMethod> contactUsMethodList) {
        mContext = context;
        mContactUsMethodList = contactUsMethodList;
    }

    @NonNull
    @Override
    public ContactUsMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.contact_us_single_card, parent, false);
        return new ContactUsMethodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactUsMethodViewHolder holder, int position) {
        ContactUsMethod contactUsMethod = mContactUsMethodList.get(position);
        holder.bind(contactUsMethod);
    }

    @Override
    public int getItemCount() {
        return mContactUsMethodList.size();
    }

    public static class ContactUsMethodViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContactMethod;
        private TextView tvContactInfo,tvContactExplain;
        private ImageView imgContactMethod;

        public ContactUsMethodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContactMethod = itemView.findViewById(R.id.tvContactMethod);
            tvContactInfo = itemView.findViewById(R.id.tvContactInfo);
            imgContactMethod = itemView.findViewById(R.id.imgContactMethod);
            tvContactExplain = itemView.findViewById(R.id.tvContactExplain);
        }

        public void bind(ContactUsMethod contactUsMethod) {
            tvContactMethod.setText(contactUsMethod.getContactMethod());
            tvContactInfo.setText(contactUsMethod.getContactInfo());
            tvContactExplain.setText(contactUsMethod.getContactExplain());
            imgContactMethod.setImageResource(contactUsMethod.getImageResource());
        }
    }
}

