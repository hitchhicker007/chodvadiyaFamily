package com.hfu.chodvadiya.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hfu.chodvadiya.BloodGroupActivity;
import com.hfu.chodvadiya.BusinessActivity;
import com.hfu.chodvadiya.ContactActivity;
import com.hfu.chodvadiya.EducationActivity;
import com.hfu.chodvadiya.GallaryActivity;
import com.hfu.chodvadiya.MembersInfoActivity;
import com.hfu.chodvadiya.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewFlipper viewFlipper;
    private CardView cardGallary,cardContact,cardBloodGroup,cardEducation,cardProfession,cardInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        cardGallary = (CardView)root.findViewById(R.id.card_gallary);
        cardContact = (CardView)root.findViewById(R.id.card_contact);
        cardBloodGroup = (CardView)root.findViewById(R.id.card_blood_group);
        cardEducation = (CardView)root.findViewById(R.id.card_education);
        cardProfession = (CardView)root.findViewById(R.id.card_profession);
        cardInfo = (CardView)root.findViewById(R.id.card_info);

        cardContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ContactActivity.class));
            }
        });

        cardBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BloodGroupActivity.class));
            }
        });

        cardEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EducationActivity.class));
            }
        });

        cardGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GallaryActivity.class));
            }
        });

        cardProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BusinessActivity.class));
            }
        });

        cardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MembersInfoActivity.class));
            }
        });

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

}
