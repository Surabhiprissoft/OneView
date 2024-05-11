package com.sbi.oneview.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sbi.oneview.R;
import com.sbi.oneview.utils.CustomIndicatorView;

import java.util.ArrayList;

public class CourouselAdapter extends RecyclerView.Adapter<CourouselAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> arrayList;
    private OnItemClickListener onItemClickListener;
    private int currentPosition = 0;
    private int initialPosition;
    private CustomIndicatorView customIndicator;
    public CourouselAdapter(Context context, ArrayList<String> arrayList, CustomIndicatorView customIndicator) {
        this.context = context;
        this.arrayList = arrayList;
        this.customIndicator = customIndicator;
        initialPosition = arrayList.size() / 2;
        customIndicator.setNumItems(arrayList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        currentPosition = (initialPosition + position) % arrayList.size();

        Log.e("POSITION",""+currentPosition);
        Log.e("IMAGE",""+arrayList.get(currentPosition));
        Glide.with(context).load(arrayList.get(currentPosition)).into(holder.imageView);
        customIndicator.setActiveIndex(currentPosition);


        holder.btnNext.setOnClickListener(v -> {
            if (currentPosition < arrayList.size() - 1) {
                currentPosition++;
                animateImageChange(holder.imageView, arrayList.get(currentPosition), true);
                customIndicator.setActiveIndex(currentPosition);
            }
        });

        holder.btnPrevious.setOnClickListener(v -> {
            if (currentPosition > 0) {
                currentPosition--;
                animateImageChange(holder.imageView, arrayList.get(currentPosition), false);
                customIndicator.setActiveIndex(currentPosition);
            }
        });

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(holder.imageView, arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView btnNext;
        ImageView btnPrevious;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            btnNext = itemView.findViewById(R.id.btnNext);
            btnPrevious = itemView.findViewById(R.id.btnPrevious);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(ImageView imageView, String path);
    }

    private void animateImageChange(ImageView imageView, String imagePath, boolean isNext) {
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
    }
}
