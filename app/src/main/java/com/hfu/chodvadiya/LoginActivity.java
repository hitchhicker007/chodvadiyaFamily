package com.hfu.chodvadiya;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername,etPassword,etEmail,etBuisnessLoc,etVillageMosal,etSurnameMosal,etMobile
            ,etAnnualIncome,etHomeAdd;
    private TextView etdob;
    private LinearLayout choose_profile_pic;
    private Button btnRegister;
    private ProgressDialog progressDialog;
    private Spinner spinner_education,spinner_business,spinner_blood_group,spinner_marriage_status,
            spinner_business_field,spinner_family_member,spinner_village,spinner_taluka,spinner_jilla,spinner_xender;
    private Calendar c;
    private DatePickerDialog dpd;
    private ImageView profile_pic;
    private Bitmap bitmap;
    private int IMAGE_REQUEST = 1;
    private CheckBox showHidePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;
        }


        etUsername = (EditText)findViewById(R.id.etMainMember);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etdob = (TextView)findViewById(R.id.etdob);
        etBuisnessLoc = (EditText) findViewById(R.id.business_location);
        etVillageMosal = (EditText) findViewById(R.id.et_gam_mosad);
        etSurnameMosal = (EditText) findViewById(R.id.et_surname_mosad);
        etMobile = (EditText) findViewById(R.id.et_mobile_number);
        etAnnualIncome = (EditText) findViewById(R.id.etAnnualIncome);
        etHomeAdd = (EditText) findViewById(R.id.etHomeAddress);
        profile_pic = (ImageView)findViewById(R.id.profile_picture);
        choose_profile_pic = (LinearLayout)findViewById(R.id.linear_layout_choose_pic);

        spinner_education = (Spinner) findViewById(R.id.spinner_education);
        spinner_business = (Spinner) findViewById(R.id.spinner_business);
        spinner_blood_group = (Spinner) findViewById(R.id.spinner_blood_group);
        spinner_marriage_status = (Spinner) findViewById(R.id.spinner_marriage_status);
        spinner_business_field= (Spinner) findViewById(R.id.spinner_business_field);
        spinner_family_member= (Spinner) findViewById(R.id.spinner_family_members);
        spinner_village = (Spinner) findViewById(R.id.spinner_village);
        spinner_taluka = (Spinner) findViewById(R.id.spinner_taluko);
        spinner_jilla = (Spinner) findViewById(R.id.spinner_jillo);
        spinner_xender = (Spinner) findViewById(R.id.spinner_xender);

        showHidePass = (CheckBox)findViewById(R.id.show_hide_pass_register);

        btnRegister = (Button)findViewById(R.id.btnRegister);
        Button btndobpicker = (Button) findViewById(R.id.btndobpicker);

        progressDialog = new ProgressDialog(this);
        btnRegister.setOnClickListener(this);
        choose_profile_pic.setOnClickListener(this);

        btndobpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(LoginActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int myear, int mmonth, int dayOfMonth) {
                        etdob.setText(dayOfMonth+"/"+(mmonth+1)+"/"+myear);
                    }
                },day,month,year);
                dpd.show();
            }
        });

//        village_list();

        SpinnerData spinnerData = new SpinnerData();
        spinnerData.village_list(this,spinner_village);
        spinnerData.business_list(this,spinner_business_field);
        spinnerData.taluka_list(this,spinner_taluka);
        spinnerData.district_list(this,spinner_jilla);
        spinnerData.eductaion_list(this,spinner_education);

        ArrayAdapter<String>businessAdapter = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.business));
        businessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_business.setAdapter(businessAdapter);

        ArrayAdapter<String>bldgrpAdapter = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.blood_grp));
        bldgrpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_blood_group.setAdapter(bldgrpAdapter);

        ArrayAdapter<String>statusAdapter = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.marriage_status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_marriage_status.setAdapter(statusAdapter);

        ArrayAdapter<String>membersAdapter = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.family_member));
        membersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_family_member.setAdapter(membersAdapter);

        ArrayAdapter<String>xenderAdapter = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.xender));
        xenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_xender.setAdapter(xenderAdapter);

        showHidePass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == btnRegister){
            if (validateEntry()){
                if (validateEmail() && validateMobile()){
                    if (validateProfilePic(bitmap)){
                        registerUser();
                        Intent intent = new Intent(LoginActivity.this,AddFamilyMembersActivity.class);
                        intent.putExtra("totalMembers",spinner_family_member.getSelectedItem().toString().trim());
                        intent.putExtra("key",etEmail.getText().toString().trim());
                        intent.putExtra("village",spinner_village.getSelectedItem().toString().trim());
                        intent.putExtra("taluko",spinner_taluka.getSelectedItem().toString().trim());
                        intent.putExtra("district",spinner_jilla.getSelectedItem().toString().trim());
                        intent.putExtra("home_address",etHomeAdd.getText().toString().trim());
                        startActivity(intent);

                    }else{
                        Toast.makeText(getApplicationContext(),"કૃપયા કરી તમારો ફોટો ઉમેરો",Toast.LENGTH_SHORT).show();
                    }
                }
            }else{
                Toast.makeText(getApplicationContext(),"કૃપયા કરી બધી વિગતો ભરો",Toast.LENGTH_SHORT).show();
            }
        }
        if(v==choose_profile_pic){
            selectImage();
        }
    }

    private void selectImage(){
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(this);
    }

    private boolean validateEntry(){
        return  validateUname() && validatePass() && validateEtmail() &&
                validateBusiLoc() && validateVlgMsl() && validateSrMsl() &&
                validateetMo() && validateInco() && validateHmAdd() &&
                validatedob() && !spinner_business.getSelectedItem().toString().equals("તમારો વ્યયસાય પસંદ કરો") &&
                !spinner_blood_group.getSelectedItem().toString().equals("તમારું રક્ત જૂથ પસંદ કરો") && !spinner_marriage_status.getSelectedItem().toString().equals("તમારા લગ્ન ની સ્થિતિ પસંદ કરો") &&
                !spinner_business_field.getSelectedItem().toString().equals("તમારું વ્યયસાય ક્ષેત્ર પસંદ કરો") && !spinner_family_member.getSelectedItem().toString().equals("તમારા સિવાય પરિવાર માં કેટલા સભ્યો છે તે પસંદ કરો") &&
                !spinner_village.getSelectedItem().toString().equals("તમારું મૂળ વતન પસંદ કરો") && !spinner_taluka.getSelectedItem().toString().equals("તમારો તાલુકો પસંદ કરો") &&
                !spinner_jilla.getSelectedItem().toString().equals("તમારો જિલ્લો પસંદ કરો") && !spinner_xender.getSelectedItem().toString().equals("તમારા લગ્ન ની સ્થિતિ પસંદ કરો");
    }

    private boolean validateUname(){
        if (etUsername.getText().toString().isEmpty()){
            etUsername.setError("*Please Enter Username*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validatePass(){
        if (etPassword.getText().toString().isEmpty()){
            etPassword.setError("*Please Enter Password*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validateVlgMsl(){
        if (etVillageMosal.getText().toString().isEmpty()){
            etVillageMosal.setError("*Please Enter Village*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validateBusiLoc(){
        if (etBuisnessLoc.getText().toString().isEmpty()){
            etBuisnessLoc.setError("*Please Enter Location*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validateEtmail(){
        if (etEmail.getText().toString().isEmpty()){
            etEmail.setError("*Please Enter Your Email*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validateSrMsl(){
        if (etSurnameMosal.getText().toString().isEmpty()){
            etSurnameMosal.setError("*Please Enter Surname*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validateetMo(){
        if (etMobile.getText().toString().isEmpty()){
            etMobile.setError("*Please Enter Mobile Number*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validateHmAdd(){
        if (etHomeAdd.getText().toString().isEmpty()){
            etHomeAdd.setError("*Please Enter Address*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validateInco(){
        if (etAnnualIncome.getText().toString().isEmpty()){
            etAnnualIncome.setError("*Please Enter Income*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validatedob(){
        if (etdob.getText().toString().equals("જન્મ ની તારીખ પસંદ કરો")){
            etdob.setError("*Please Enter Birth Date*");
            return false;
        }else{
            return true;
        }
    }

    private boolean validateEmail(){
        String emailInput = etEmail.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            etEmail.setError("*કૃપયા કરી તમારું સાચું ઇમેઇલ લખો*");
            return false;
        }else{
            etEmail.setError(null);
            return true;
        }
    }

    private boolean validateMobile(){
        String mobileInput = etMobile.getText().toString().trim();
        if(mobileInput.length()==10){
            return true;
        }else{
            etMobile.setError("કૃપયા કરી તમારો સાચો મોબાઈલ નંબર લખો");
            return false;
        }
    }

    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode==RESULT_OK){
                assert result != null;
                Uri filepath = result.getUri();
                try {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), filepath);
                    bitmap = ImageDecoder.decodeBitmap(source);
                    profile_pic.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean validateProfilePic(Bitmap bitmap){
        if(bitmap == null){
            return false;
        }else {
            return true;
        }
    }

    private void registerUser() {
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String username = etUsername.getText().toString().trim();
        final String dateOfBirth = etdob.getText().toString().trim();
        final String xender = spinner_xender.getSelectedItem().toString().trim();
        final String education = spinner_education.getSelectedItem().toString().trim();
        final String profession = spinner_business.getSelectedItem().toString().trim();
        final String profession_field = spinner_business_field.getSelectedItem().toString().trim();
        final String profession_loc = etBuisnessLoc.getText().toString().trim();
        final String status = spinner_marriage_status.getSelectedItem().toString().trim();
        final String blood_group = spinner_blood_group.getSelectedItem().toString().trim();
        final String village_mosal = etVillageMosal.getText().toString().trim();
        final String surname_mosal = etSurnameMosal.getText().toString().trim();
        final String mobile_number = etMobile.getText().toString().trim();
        final String family_members = spinner_family_member.getSelectedItem().toString().trim();
        final String village = spinner_village.getSelectedItem().toString().trim();
        final String taluko = spinner_taluka.getSelectedItem().toString().trim();
        final String district = spinner_jilla.getSelectedItem().toString().trim();
        final String annual_income = etAnnualIncome.getText().toString().trim();
        final String home_address = etHomeAdd.getText().toString().trim();
        final String profile_picture = imageToString(bitmap);

        progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",username);
                params.put("xender",xender);
                params.put("dob",dateOfBirth);
                params.put("education",education);
                params.put("profession",profession);
                params.put("profession_field",profession_field);
                params.put("profession_loc",profession_loc);
                params.put("status",status);
                params.put("blood_group",blood_group);
                params.put("village_mosal",village_mosal);
                params.put("surname_mosal",surname_mosal);
                params.put("mobile_number",mobile_number);
                params.put("family_members",family_members);
                params.put("village",village);
                params.put("taluko",taluko);
                params.put("district",district);
                params.put("annual_income",annual_income);
                params.put("home_address",home_address);
                params.put("password",password);
                params.put("email",email);
                params.put("profile_pic",profile_picture);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
