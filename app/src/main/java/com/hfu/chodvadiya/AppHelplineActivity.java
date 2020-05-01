package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AppHelplineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_helpline);

        TextView website = (TextView)findViewById(R.id.tv_hfu_web_hl);
        TextView parth = (TextView)findViewById(R.id.call_pp);
        TextView bhautik = (TextView)findViewById(R.id.call_bc);

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

    }
}
