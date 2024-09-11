package com.sbi.oneview.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.sbi.oneview.R;
import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;
import com.sbi.oneview.ui.inrPrepaid.MyFragmentCallback;
import com.sbi.oneview.utils.CustomIndicatorView;

import org.w3c.dom.Text;

import java.util.List;

public class CourouselAdapter extends RecyclerView.Adapter<CourouselAdapter.ViewHolder> {
    private Context context;
    private List<CardDetailsItem> arrayList;
    private OnItemClickListener onItemClickListener;
    private int currentPosition = 0;
    private int initialPosition;
    private Integer customPosition;
    private Fragment fragment;
    private CustomIndicatorView customIndicator;
    private MyFragmentCallback callback;
    public CourouselAdapter(MyFragmentCallback callback, Context context, List<CardDetailsItem> arrayList, CustomIndicatorView customIndicator,Integer customPosition) {
        this.context = context;
        this.arrayList = arrayList;
        this.customIndicator = customIndicator;
        this.customPosition = customPosition;
        initialPosition = arrayList.size() / 2;
        customIndicator.setNumItems(arrayList.size());
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (customPosition!=null)
        {
            currentPosition = customPosition;
        }else{
            currentPosition = (initialPosition + position) % arrayList.size();
        }

        Log.e("POSITION",""+currentPosition);
        Log.e("IMAGE",""+arrayList.get(currentPosition));

        String productCode = arrayList.get(currentPosition).getProductCode();
        callback.onPositionChange(currentPosition);

        int currentImageResourceId;

        /*if (productCode.equals("266")){
            holder.imageView.setImageResource(R.drawable.city_chennai);
            currentImageResourceId=R.drawable.city_chennai;
        }else if (productCode.equals("262")){
            holder.imageView.setImageResource(R.drawable.city_noida);
            currentImageResourceId=R.drawable.city_noida;
        }else if (productCode.equals("267")){
            holder.imageView.setImageResource(R.drawable.city_mumbai);
            currentImageResourceId=R.drawable.city_mumbai;
        }else if (productCode.equals("263")){
            holder.imageView.setImageResource(R.drawable.city_nagpur);
            currentImageResourceId=R.drawable.city_nagpur;
        }else if (productCode.equals("270")){
            holder.imageView.setImageResource(R.drawable.city_kanpur);
            currentImageResourceId=R.drawable.city_kanpur;
        }else if (productCode.equals("123")){
            holder.imageView.setImageResource(R.drawable.city_kanpur);
            currentImageResourceId=R.drawable.city_kanpur;
        }else if (productCode.equals("GBRSBT")){
            holder.imageView.setImageResource(R.drawable.city_mumbai);
            currentImageResourceId=R.drawable.city_mumbai;
        }else{
            currentImageResourceId=R.drawable.city_kanpur;
        }*/

       // holder.imageView.setImageDrawable(context.getDrawable(R.drawable.sbi_card));

        //holder.imageView.setImageResource(cardImage);
        //Glide.with(context).load(arrayList.get(currentPosition)).into(holder.imageView);
        customIndicator.setActiveIndex(currentPosition);

        animateImageChange(holder.cardLayout, holder.tvCardNumber,holder.tvCardExp, true,arrayList.get(currentPosition).getCardNumber(),arrayList.get(currentPosition).getExpDate());

        String cardExpDate;
        if (arrayList.get(currentPosition).getExpDate()!=null)
        {
            cardExpDate = arrayList.get(currentPosition).getExpDate();
        }else{
            cardExpDate = arrayList.get(currentPosition).getCardExpiryDate();
        }

        holder.btnNext.setOnClickListener(v -> {
            if (currentPosition < arrayList.size() - 1) {
                currentPosition++;
                animateImageChange(holder.cardLayout, holder.tvCardNumber,holder.tvCardExp, true,arrayList.get(currentPosition).getCardNumber(),cardExpDate);
                customIndicator.setActiveIndex(currentPosition);
                callback.onPositionChange(currentPosition);
            }
        });

        holder.btnPrevious.setOnClickListener(v -> {
            if (currentPosition > 0) {
                currentPosition--;
                animateImageChange(holder.cardLayout, holder.tvCardNumber,holder.tvCardExp, false,arrayList.get(currentPosition).getCardNumber(),arrayList.get(currentPosition).getExpDate());
                customIndicator.setActiveIndex(currentPosition);
                callback.onPositionChange(currentPosition);
            }
        });

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null)
            {
               // onItemClickListener.onClick(holder.imageView, arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout cardLayout;
        ImageView btnNext;
        ImageView btnPrevious;
        TextView tvCardNumber,tvCardExp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardLayout = itemView.findViewById(R.id.cardLayout);
            btnNext = itemView.findViewById(R.id.btnNext);
            btnPrevious = itemView.findViewById(R.id.btnPrevious);
            tvCardNumber = itemView.findViewById(R.id.tvCardNumber);
            tvCardExp = itemView.findViewById(R.id.tvValidUpto);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(ImageView imageView, String path);
    }

   /* private void animateImageChange(ImageView imageView, String imagePath, boolean isNext) {
        Animation slideOut = AnimationUtils.loadAnimation(context, isNext ? R.anim.slide_out_left : R.anim.slide_out_right);
        imageView.startAnimation(slideOut);
        slideOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                Glide.with(context).load(imagePath).into(imageView);
                Animation slideIn = AnimationUtils.loadAnimation(context, isNext ? R.anim.slide_in_right : R.anim.slide_in_left);
                imageView.startAnimation(slideIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }*/


    public int getImageResources(String productCode){
        if (productCode.equals("266")){
            //holder.imageView.setImageResource(R.drawable.city_chennai);
            return  R.drawable.city_chennai;
        }else if (productCode.equals("262")){
            //holder.imageView.setImageResource(R.drawable.city_noida);
            return R.drawable.city_noida;
        }else if (productCode.equals("267")){
            //holder.imageView.setImageResource(R.drawable.city_mumbai);
            return R.drawable.city_mumbai;
        }else if (productCode.equals("263")){
            //holder.imageView.setImageResource(R.drawable.city_nagpur);
            return R.drawable.city_nagpur;
        }else if (productCode.equals("270")){
            //holder.imageView.setImageResource(R.drawable.city_kanpur);
            return R.drawable.city_kanpur;
        }else if (productCode.equals("123")){
            //holder.imageView.setImageResource(R.drawable.city_kanpur);
            return R.drawable.city_kanpur;
        }else if (productCode.equals("GBRSBT")){
            //holder.imageView.setImageResource(R.drawable.city_mumbai);
            return R.drawable.city_mumbai;
        }else{
            return R.drawable.city_kanpur;
        }
    }

    private void animateImageChange(LinearLayout cardLayout, TextView tvCardNumber,TextView tvCardExp, boolean isNext, String cardNumber, String expDate) {
        Animation slideOut = AnimationUtils.loadAnimation(context, isNext ? R.anim.slide_out_left : R.anim.slide_out_right);
        cardLayout.startAnimation(slideOut);
        slideOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                //imageView.setImageDrawable(context.getDrawable(R.drawable.sbi_card));
                Animation slideIn = AnimationUtils.loadAnimation(context, isNext ? R.anim.slide_in_right : R.anim.slide_in_left);
                if (cardNumber.length()>4)
                {
                    tvCardNumber.setText("XXXX XXXX XXXX " + cardNumber.substring(cardNumber.length()-4));
                }else {
                    tvCardNumber.setText("XXXX XXXX XXXX " + cardNumber);
                }
                if (expDate!=null)
                {
                    if (expDate.length()>4){
                        tvCardExp.setText(expDate.substring(3,5)+" / "+expDate.substring(6));
                    }else {
                        tvCardExp.setText(expDate.substring(0, 2) + "/" + expDate.substring(2));
                    }
                }
                cardLayout.startAnimation(slideIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}
