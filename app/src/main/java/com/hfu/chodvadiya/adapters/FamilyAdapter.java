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
import com.hfu.chodvadiya.models.Family;

import java.util.List;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.FamilyViewHolder>
{
    private Context mCtx;
    private List<Family> familiesList;

    public FamilyAdapter(Context mCtx, List<Family> familiesList) {
        this.mCtx = mCtx;
        this.familiesList = familiesList;
    }

    @NonNull
    @Override
    public FamilyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.family_member_card,null);
        return new FamilyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FamilyViewHolder holder, int position) {
        Family family = familiesList.get(position);

        holder.name.setText("નામ : "+family.getName());
        holder.relation.setText("કુટુંબ ના વડા સાથે નો સંબંધ : "+family.getRelation());
        holder.xender.setText("જાતિ : "+family.getXender());
        holder.blood_group.setText("રક્ત જૂથ : "+family.getBlood_group());
        holder.status.setText("લગ્ન ની સ્થિતિ : "+family.getStatus());
        holder.mobile_number.setText("મોબાઈલ નંબર : "+family.getMobile_number());
        holder.dob.setText("જન્મ ની તારીખ : "+family.getDob());
        holder.education.setText("શિક્ષણ : "+family.getEducation());
        holder.profession.setText("વ્યયસાય : "+family.getProfession());
        holder.profession_field.setText("વ્યયસાય ક્ષેત્ર : "+family.getProfession_field());
        holder.village_mosal.setText("મોસાળ ગામ : "+family.getVillage_mosal());
        holder.surname_mosal.setText("મોસાળ અટક : "+family.getSurname_mosal());

    }

    @Override
    public int getItemCount() {
        return familiesList.size();
    }

    class FamilyViewHolder extends RecyclerView.ViewHolder {

        TextView name,relation,xender,blood_group,status,mobile_number,dob,education,profession,profession_field,village_mosal,surname_mosal;

        public FamilyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.member_layout_name);
            relation = itemView.findViewById(R.id.member_layout_relation);
            xender = itemView.findViewById(R.id.member_layout_xender);
            blood_group = itemView.findViewById(R.id.member_layout_blood_group);
            status = itemView.findViewById(R.id.member_layout_status);
            mobile_number = itemView.findViewById(R.id.member_layout_mobile);
            dob = itemView.findViewById(R.id.member_layout_dob);
            education = itemView.findViewById(R.id.member_layout_education);
            profession = itemView.findViewById(R.id.member_layout_profession);
            profession_field = itemView.findViewById(R.id.member_layout_profession_field);
            village_mosal = itemView.findViewById(R.id.member_layout_village_mosal);
            surname_mosal = itemView.findViewById(R.id.member_layout_surname_mosal);

        }
    }
}
