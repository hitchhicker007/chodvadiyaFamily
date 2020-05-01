package com.hfu.chodvadiya.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfu.chodvadiya.R;
import com.hfu.chodvadiya.models.Ads;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>
{
    private Context mCtx;
    private List<Ads> sliderList;
    private OnSliderListner mOnSliderListner;

    public SliderAdapter(Context mCtx, List<Ads> sliderList,OnSliderListner onSliderListner) {
        this.mCtx = mCtx;
        this.sliderList = sliderList;
        this.mOnSliderListner = onSliderListner;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.slide_images,null);
        return new SliderViewHolder(view,mOnSliderListner);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Ads ads = sliderList.get(position);
        Picasso.with(mCtx).load(ads.getImg()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return sliderList.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView thumbnail;
        TextView title,date;
        SliderAdapter.OnSliderListner onSliderListner;

        public SliderViewHolder(@NonNull View itemView, OnSliderListner onSliderListner) {
            super(itemView);

            this.onSliderListner = onSliderListner;
            thumbnail = itemView.findViewById(R.id.slider_imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSliderListner.onSliderClick(getAdapterPosition());
        }
    }

    public interface OnSliderListner{
        void onSliderClick(int position);
    }

}
