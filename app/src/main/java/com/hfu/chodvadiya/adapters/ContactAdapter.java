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
import com.hfu.chodvadiya.models.Contacts;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>
{
    private Context mCtx;
    private List<Contacts> contactsList;
    private OnContactListner mOnContactListner;

    public ContactAdapter(Context mCtx, List<Contacts> contactsList, OnContactListner onContactListner) {
        this.mCtx = mCtx;
        this.mOnContactListner = onContactListner;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.contact_layout,null);
        return new ContactViewHolder(view,mOnContactListner);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contacts contacts = contactsList.get(position);

        holder.name.setText(contacts.getName());
        holder.profession.setText("વ્યયસાય : "+contacts.getProfession());
        holder.mobile_number.setText("મોબાઈલ નંબર : "+contacts.getMobile_number());
        holder.address.setText("સરનામું : "+contacts.getAddress());
        Picasso.with(mCtx).load(contacts.getProfile_pic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView name,mobile_number,address,profession;
        OnContactListner onContactListner;

        public ContactViewHolder(@NonNull View itemView,OnContactListner onContactListner) {
            super(itemView);

            this.onContactListner = onContactListner;

            imageView = itemView.findViewById(R.id.contact_image);
            name = itemView.findViewById(R.id.contact_name);
            mobile_number = itemView.findViewById(R.id.contact_number);
            address = itemView.findViewById(R.id.contact_address);
            profession = itemView.findViewById(R.id.contact_profession);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactListner.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactListner{
        void onContactClick(int position);
    }
}
