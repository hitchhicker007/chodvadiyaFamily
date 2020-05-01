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
import com.hfu.chodvadiya.models.Education;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder>
{
    private Context mCtx;
    private List<Education> educationList;

    public EducationAdapter(Context mCtx, List<Education> educationList) {
        this.mCtx = mCtx;
        this.educationList = educationList;
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.education_layout_card,null);
        return new EducationViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        Education education = educationList.get(position);

        holder.name.setText("નામ : "+education.getName());
        holder.xender.setText("જાતિ : "+education.getXender());
        holder.mobile_number.setText("મોબાઈલ નંબર : "+education.getMobile_number());
        holder.address.setText("સરનામું : "+education.getHome_address());
        holder.village.setText("મૂળ વતન : "+education.getVillage());
        holder.taluko.setText("તાલુકો : "+education.getTaluka());
        holder.district.setText("જિલ્લો : "+education.getDistrict());
        holder.profession_field.setText("વ્યયસાય ક્ષેત્ર : "+education.getProfession_field());
        holder.profession.setText("વ્યયસાય : "+education.getProfession());

    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    class EducationViewHolder extends RecyclerView.ViewHolder {

        TextView name,mobile_number,xender,profession,profession_field,address,village,taluko,district;

        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.edu_layout_name);
            mobile_number = itemView.findViewById(R.id.edu_layout_mobile);
            xender = itemView.findViewById(R.id.edu_layout_xender);
            address = itemView.findViewById(R.id.edu_layout_address);
            profession = itemView.findViewById(R.id.edu_layout_profession);
            profession_field = itemView.findViewById(R.id.edu_layout_profession_field);
            village = itemView.findViewById(R.id.edu_layout_village);
            taluko = itemView.findViewById(R.id.edu_layout_taluka);
            district = itemView.findViewById(R.id.edu_layout_district);

        }
    }
}
