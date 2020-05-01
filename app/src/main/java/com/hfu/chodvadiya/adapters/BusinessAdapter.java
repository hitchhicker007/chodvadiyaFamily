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
import com.hfu.chodvadiya.models.Business;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>
{
    private Context mCtx;
    private List<Business> businessList;

    public BusinessAdapter(Context mCtx, List<Business> businessList) {
        this.mCtx = mCtx;
        this.businessList = businessList;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.business_layout_card,null);
        return new BusinessViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
        Business business = businessList.get(position);

        holder.name.setText("નામ : "+business.getName());
        holder.xender.setText("જાતિ : "+business.getXender());
        holder.mobile_number.setText("મોબાઈલ નંબર : "+business.getMobile_number());
        holder.address.setText("સરનામું : "+business.getHome_address());
        holder.village.setText("મૂળ વતન : "+business.getVillage());
        holder.taluko.setText("તાલુકો : "+business.getTaluka());
        holder.district.setText("જિલ્લો : "+business.getDistrict());
        holder.profession_loc.setText("વવ્યયસાય સ્થળ : "+business.getProfession_loc());
        holder.profession.setText("વ્યયસાય : "+business.getProfession());
        holder.education.setText("શિક્ષણ : "+business.getEducation());
    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }

    class BusinessViewHolder extends RecyclerView.ViewHolder {

        TextView name,mobile_number,xender,education,profession,profession_loc,address,village,taluko,district;

        public BusinessViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.business_card_name);
            mobile_number = itemView.findViewById(R.id.business_card_mobile);
            xender = itemView.findViewById(R.id.business_card_xender);
            education = itemView.findViewById(R.id.business_card_education);
            address = itemView.findViewById(R.id.business_card_address);
            profession = itemView.findViewById(R.id.business_card_profession);
            profession_loc = itemView.findViewById(R.id.business_card_profession_loc);
            village = itemView.findViewById(R.id.business_card_village);
            taluko = itemView.findViewById(R.id.business_card_taluka);
            district = itemView.findViewById(R.id.business_card_district);

        }
    }
}
