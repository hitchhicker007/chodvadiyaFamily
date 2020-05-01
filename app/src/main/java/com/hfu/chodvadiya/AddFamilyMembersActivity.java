package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddFamilyMembersActivity extends AppCompatActivity {

    private EditText menberName,mosalVillage,mosalSurname,mobile_number;
    private String name,xender,dob,village,surname,mo_number,education,profession,profession_field,status,blood_grp,relation;
    private Spinner spinner_education,spinner_xender,spinner_profession,spinner_profession_field,spinner_status,spinner_bloodGroup,spinner_relation;
    private int members,current_member=1;
    private TextView member_no,etDob;
    private ProgressDialog progressDialog;
    private String key,village_mem,taluka_mem,district_mem,address_mem;
    private ScrollView scrollView;
    private Button datepicker;
    private Calendar c;
    private DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family_members);

        members = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("totalMembers")));
        key = getIntent().getStringExtra("key");
        village_mem = getIntent().getStringExtra("village");
        district_mem = getIntent().getStringExtra("district");
        taluka_mem = getIntent().getStringExtra("taluko");
        address_mem = getIntent().getStringExtra("home_address");


        member_no = (TextView)findViewById(R.id.member_text);
        progressDialog = new ProgressDialog(this);

        scrollView = (ScrollView)findViewById(R.id.scroll_add_member);
        Button btnAddMember = (Button) findViewById(R.id.btnAddMember);

        menberName = (EditText)findViewById(R.id.etMemberName);
        mosalSurname = (EditText)findViewById(R.id.et_member_surname_mosad);
        mosalVillage = (EditText)findViewById(R.id.et_member_gam_mosad);
        mobile_number = (EditText)findViewById(R.id.et_member_mobile_number);

        datepicker = (Button)findViewById(R.id.btndobpickerMember);

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(AddFamilyMembersActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int myear, int mmonth, int dayOfMonth) {
                        etDob.setText(dayOfMonth+"/"+(mmonth+1)+"/"+myear);
                        dob = dayOfMonth+"/"+(mmonth+1)+"/"+myear;
                    }
                },day,month,year);
                dpd.show();
            }
        });

        etDob = (TextView)findViewById(R.id.etMemberDob);
        spinner_relation = (Spinner)findViewById(R.id.spinner_member_relation);
        spinner_education = (Spinner) findViewById(R.id.spinner_member_education);
        spinner_profession = (Spinner) findViewById(R.id.spinner_member_business);
        spinner_profession_field = (Spinner) findViewById(R.id.spinner_member_business_field);
        spinner_status = (Spinner) findViewById(R.id.spinner_member_marriage_status);
        spinner_bloodGroup = (Spinner) findViewById(R.id.spinner_member_blood_group);
        spinner_xender = (Spinner) findViewById(R.id.spinner_member_xender);

        ArrayAdapter<String> eduAdapter = new ArrayAdapter<String>(AddFamilyMembersActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.education));
        eduAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_education.setAdapter(eduAdapter);

        ArrayAdapter<String>profAdapter = new ArrayAdapter<String>(AddFamilyMembersActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.business));
        profAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_profession.setAdapter(profAdapter);

        ArrayAdapter<String>pfAdapter = new ArrayAdapter<String>(AddFamilyMembersActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.business_field));
        pfAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_profession_field.setAdapter(pfAdapter);

        ArrayAdapter<String>statusAdapter = new ArrayAdapter<String>(AddFamilyMembersActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.marriage_status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_status.setAdapter(statusAdapter);

        ArrayAdapter<String>bgAdapter = new ArrayAdapter<String>(AddFamilyMembersActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.blood_grp));
        bgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_bloodGroup.setAdapter(bgAdapter);

        ArrayAdapter<String>relationAdapter = new ArrayAdapter<String>(AddFamilyMembersActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.relation));
        relationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_relation.setAdapter(relationAdapter);

        ArrayAdapter<String>xenderAdapter = new ArrayAdapter<String>(AddFamilyMembersActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.xender));
        xenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_xender.setAdapter(xenderAdapter);

        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (current_member==members){
                    getData();
                    addMember();
                    finish();
                    startActivity(new Intent(AddFamilyMembersActivity.this,MainActivity.class));
                    Toast.makeText(AddFamilyMembersActivity.this,"all members are added!!",Toast.LENGTH_LONG).show();
                }else{
                    getData();
                    addMember();
                    clearData();
                    scrollView.scrollTo(0,0);
                    current_member++;
                    member_no.setText("સભ્ય "+current_member);
                }
            }
        });
    }

    private void clearData(){
        etDob.setText("જન્મ ની તારીખ પસંદ કરો");
        menberName.setText("");
        spinner_xender.setSelection(0);
        mosalVillage.setText("");
        mosalSurname.setText("");
        mobile_number.setText("");
        spinner_education.setSelection(0);
        spinner_profession.setSelection(0);
        spinner_profession_field.setSelection(0);
        spinner_status.setSelection(0);
        spinner_bloodGroup.setSelection(0);
        spinner_relation.setSelection(0);

    }

    private void getData(){
        name = menberName.getText().toString().trim();
        xender = spinner_xender.getSelectedItem().toString().trim();
        village = mosalVillage.getText().toString().trim();
        surname = mosalSurname.getText().toString().trim();
        mo_number = mobile_number.getText().toString().trim();
        education = spinner_education.getSelectedItem().toString().trim();
        profession = spinner_profession.getSelectedItem().toString().trim();
        profession_field = spinner_profession_field.getSelectedItem().toString().trim();
        status = spinner_status.getSelectedItem().toString().trim();
        blood_grp = spinner_bloodGroup.getSelectedItem().toString().trim();
        relation = spinner_relation.getSelectedItem().toString().trim();
    }

    private void addMember() {
        progressDialog.setMessage("adding data..");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_ADD_MEMBER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        long start = System.currentTimeMillis();
                        long end = start + 2*1000;
                        while (System.currentTimeMillis()<end){
                            continue;
                        }
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("foreign_key",key);
                params.put("name",name);
                params.put("xender",xender);
                params.put("dateOfBirth",dob);
                params.put("education",education);
                params.put("profession",profession);
                params.put("profession_field",profession_field);
                params.put("relation",relation);
                params.put("status",status);
                params.put("blood_group",blood_grp);
                params.put("village_mosal",village);
                params.put("surname_mosal",surname);
                params.put("mobile_number",mo_number);
                params.put("village",village_mem);
                params.put("taluka",taluka_mem);
                params.put("district",district_mem);
                params.put("home_address",address_mem);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(AddFamilyMembersActivity.this,"you cant go back",Toast.LENGTH_LONG).show();
    }
}
