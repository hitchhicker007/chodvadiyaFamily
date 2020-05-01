package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPassActivity extends AppCompatActivity {

    private EditText etCurrentPass,etNewPass;
    private Button button;
    private CheckBox showHidePass;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        button = (Button)findViewById(R.id.btn_reset_pass);
        etCurrentPass = (EditText) findViewById(R.id.fp_current_pass);
        etNewPass = (EditText) findViewById(R.id.fp_new_pass);
        showHidePass = (CheckBox)findViewById(R.id.show_hide_pass_fp);
        progressDialog = new ProgressDialog(this);

        showHidePass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    etCurrentPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etNewPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    etCurrentPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etNewPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCurrentPass.getText().toString().trim().isEmpty() || etNewPass.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"કૃપયા કરી બધી વિગતો ભરો",Toast.LENGTH_SHORT).show();
                }else{
                    resetPass();
                }
            }
        });

    }

    private void resetPass(){
        progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_CHANGE_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")){
                                Toast.makeText(
                                        getApplicationContext(),
                                        "તમારો પાસવર્ડ બદલાઈ ગયો છે.",
                                        Toast.LENGTH_SHORT
                                ).show();
                                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",SharedPrefManager.getInstance(getApplicationContext()).getUserEmail());
                params.put("currentPass",etCurrentPass.getText().toString().trim());
                params.put("newPass",etNewPass.getText().toString().trim());
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}
