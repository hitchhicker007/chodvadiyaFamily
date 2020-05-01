package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewImageActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        String img = getIntent().getStringExtra("img");

        imageView = (ImageView)findViewById(R.id.full_image_view);
        Picasso.with(this).load(img).into(imageView);
    }
}
