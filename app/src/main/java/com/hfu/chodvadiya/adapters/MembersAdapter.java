package com.hfu.chodvadiya.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfu.chodvadiya.R;
import com.hfu.chodvadiya.models.Members;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersViewHolder>
{
    private Context mCtx;
    private List<Members> membersList;

    public MembersAdapter(Context mCtx, List<Members> membersList) {
        this.mCtx = mCtx;
        this.membersList = membersList;
    }

    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.members_layout,null);
        return new MembersViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {
        final Members member = membersList.get(position);

        holder.name.setText("નામ : "+member.getName());
        holder.mobile_number.setText("મોબાઈલ નંબર : "+member.getMobile());
        holder.village.setText("ગામ : "+member.getVillage());
        Picasso.with(mCtx).load(member.getImage()).into(holder.imageView);

        holder.goto_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("tel:"+member.getMobile()));
                mCtx.startActivity(intent);
//                Toast.makeText(mCtx,member.getMobile(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return membersList.size();
    }

    class MembersViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView,goto_call;
        TextView name,mobile_number,village;

        public MembersViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.members_profile_pic);
            name = itemView.findViewById(R.id.members_name);
            village = itemView.findViewById(R.id.members_village);
            mobile_number = itemView.findViewById(R.id.members_mobile);
            goto_call = itemView.findViewById(R.id.members_goto_call);
        }

    }


}
