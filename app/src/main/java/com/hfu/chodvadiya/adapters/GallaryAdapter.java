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
import com.hfu.chodvadiya.models.Gallary;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GallaryAdapter extends RecyclerView.Adapter<GallaryAdapter.GallaryViewHolder>
{
    private Context mCtx;
    private List<Gallary> gallaryList;
    private OnGallaryListner mOnGallaryListner;

    public GallaryAdapter(Context mCtx, List<Gallary> gallaryList,OnGallaryListner onGallaryListner) {
        this.mCtx = mCtx;
        this.gallaryList = gallaryList;
        this.mOnGallaryListner = onGallaryListner;
    }

    @NonNull
    @Override
    public GallaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.gallary_layout_card,null);
        return new GallaryViewHolder(view,mOnGallaryListner);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GallaryViewHolder holder, int position) {
        Gallary gallary = gallaryList.get(position);

        holder.title.setText(gallary.getTitle());
        holder.date.setText(gallary.getDate());
        Picasso.with(mCtx).load(gallary.getImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return gallaryList.size();
    }

    class GallaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView thumbnail;
        TextView title,date;
        GallaryAdapter.OnGallaryListner onGallaryListner;

        public GallaryViewHolder(@NonNull View itemView, OnGallaryListner onGallaryListner) {
            super(itemView);

            this.onGallaryListner = onGallaryListner;
            thumbnail = itemView.findViewById(R.id.gallary_card_img);
            title = itemView.findViewById(R.id.gallary_card_title);
            date = itemView.findViewById(R.id.gallary_card_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onGallaryListner.onGallaryClick(getAdapterPosition());
        }
    }

    public interface OnGallaryListner{
        void onGallaryClick(int position);
    }

}
