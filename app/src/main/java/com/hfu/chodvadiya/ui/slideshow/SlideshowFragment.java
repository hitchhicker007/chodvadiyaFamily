package com.hfu.chodvadiya.ui.slideshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.hfu.chodvadiya.R;

public class SlideshowFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        TextView website = (TextView)root.findViewById(R.id.tv_hfu_web);
        TextView parth = (TextView)root.findViewById(R.id.call_pp_1);
        TextView bhautik = (TextView)root.findViewById(R.id.call_bc_1);

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://hfugroup.com/");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        parth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7227856454"));
                startActivity(intent);
            }
        });

        bhautik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7201916649"));
                startActivity(intent);
            }
        });


        return root;
    }
}
