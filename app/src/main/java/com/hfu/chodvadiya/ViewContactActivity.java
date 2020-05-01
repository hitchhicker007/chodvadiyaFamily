package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ViewContactActivity extends AppCompatActivity {
    private String id,key;
    private ImageView imageView;
    private TextView tvname,tvmobile,tvvillage_mosal,tvsurname_mosal,tvstatus,tvblood,tvemail,tvxender,tvdob,tvvillage,tvtaluko,tvdist,tvaddress,tvedu,tvproff,tvproff_field,tvproff_loc,tvmembers;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        id = Objects.requireNonNull(getIntent().getStringExtra("id"));

        imageView = (ImageView)findViewById(R.id.view_contact_image);
        tvname = (TextView)findViewById(R.id.view_contact_name);
        tvmobile = (TextView)findViewById(R.id.view_contact_mobile);
        tvblood = (TextView)findViewById(R.id.view_contact_blood_group);
        tvemail = (TextView)findViewById(R.id.view_contact_email);
        tvvillage = (TextView)findViewById(R.id.view_contact_village);
        tvtaluko = (TextView)findViewById(R.id.view_contact_taluko);
        tvdist = (TextView)findViewById(R.id.view_contact_district);
        tvaddress = (TextView)findViewById(R.id.view_contact_Address);
        tvedu = (TextView)findViewById(R.id.view_contact_education);
        tvproff = (TextView)findViewById(R.id.view_contact_profession);
        tvproff_field = (TextView)findViewById(R.id.view_contact_profession_field);
        tvproff_loc = (TextView)findViewById(R.id.view_contact_profession_location);
        tvmembers = (TextView)findViewById(R.id.view_contact_family_members);
        tvxender = (TextView)findViewById(R.id.view_contact_xender);
        tvdob = (TextView)findViewById(R.id.view_contact_dob);
        tvstatus = (TextView)findViewById(R.id.view_contact_status);
        tvvillage_mosal = (TextView)findViewById(R.id.view_contact_village_mosal);
        tvsurname_mosal = (TextView)findViewById(R.id.view_contact_surname_mosal);
        btn = (Button)findViewById(R.id.btn_view_family);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_VIEW_CONTACT,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);

                            key = object.getString("email");

                            tvname.setText("નામ : "+object.getString("name"));
                            tvmobile.setText("મોબાઈલ નંબર : "+object.getString("mobile_number"));
                            tvxender.setText("જાતિ : "+object.getString("xender"));
                            tvstatus.setText("લગ્ન ની સ્થિતિ : "+object.getString("status"));
                            tvdob.setText("જન્મ ની તારીખ : "+object.getString("dob"));
                            tvblood.setText("રક્ત જૂથ : "+object.getString("blood_group"));
                            tvemail.setText("ઈ-મેલ : "+object.getString("email"));
                            tvvillage_mosal.setText("મોસાળ ગામ : "+object.getString("village_mosal"));
                            tvsurname_mosal.setText("મોસાળ અટક : "+object.getString("surname_mosal"));
                            tvvillage.setText("મૂળ વતન : "+object.getString("village"));
                            tvtaluko.setText("તાલુકો : "+object.getString("taluko"));
                            tvdist.setText("જિલ્લો : "+object.getString("district"));
                            tvaddress.setText("સરનામું : "+object.getString("home_address"));
                            tvedu.setText("શિક્ષણ : "+object.getString("education"));
                            tvproff.setText("વ્યયસાય : "+object.getString("profession"));
                            tvproff_field.setText("વ્યયસાય ક્ષેત્ર : "+object.getString("profession_field"));
                            tvproff_loc.setText("વ્યયસાય સ્થળ : "+object.getString("profession_loc"));
                            tvmembers.setText("પરિવાર સભ્યો : "+object.getString("family_members"));
                            Picasso.with(getApplicationContext()).load(object.getString("profile_pic")).into(imageView);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewContactActivity.this,ViewFamilyActivity.class);
                intent.putExtra("foreign_key",key);
                startActivity(intent);
            }
        });

    }

}
