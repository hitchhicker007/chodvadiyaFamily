package com.hfu.chodvadiya.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfu.chodvadiya.R;
import com.hfu.chodvadiya.models.Bloods;

import java.util.List;

public class BloodGroupAdapter extends RecyclerView.Adapter<BloodGroupAdapter.BgViewHolder>
{
    private Context mCtx;
    private List<Bloods> bloodsList;

    public BloodGroupAdapter(Context mCtx, List<Bloods> bloodsList) {
        this.mCtx = mCtx;
        this.bloodsList = bloodsList;
    }

    @NonNull
    @Override
    public BgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.blood_grp_card,null);
        return new BgViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BgViewHolder holder, int position) {
        Bloods bloods = bloodsList.get(position);

        holder.name.setText("નામ : "+bloods.getName());
        holder.xender.setText("જાતિ : "+bloods.getXender());
        holder.mobile_number.setText("મોબાઈલ નંબર : "+bloods.getMobile());
        holder.address.setText("સરનામું : "+bloods.getAddress());
        holder.village.setText("મૂળ વતન : "+bloods.getVillage());
        holder.taluko.setText("તાલુકો : "+bloods.getTaluko());
        holder.district.setText("જિલ્લો : "+bloods.getDistrict());

    }

    @Override
    public int getItemCount() {
        return bloodsList.size();
    }

    class BgViewHolder extends RecyclerView.ViewHolder {

        TextView name,mobile_number,xender,address,village,taluko,district;

        public BgViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.bg_layout_name);
            mobile_number = itemView.findViewById(R.id.bg_layout_mobile);
            xender = itemView.findViewById(R.id.bg_layout_xender);
            address = itemView.findViewById(R.id.bg_layout_address);
            village = itemView.findViewById(R.id.bg_layout_village);
            taluko = itemView.findViewById(R.id.bg_layout_taluko);
            district = itemView.findViewById(R.id.bg_layout_district);

        }
    }
}
