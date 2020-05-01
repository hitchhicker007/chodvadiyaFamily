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
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView helpline,forgotPassword;
    private EditText etUsername,etPassword;
    private Button btnLogin,btngotoregister;
    private ProgressDialog progressDialog;
    private CheckBox showHidePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,DashboardActivity.class));
            return;
        }

        etUsername = (EditText)findViewById(R.id.etUsernameLogin);
        etPassword = (EditText)findViewById(R.id.etPasswordLogin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        helpline = (TextView)findViewById(R.id.app_helpline);
        forgotPassword = (TextView)findViewById(R.id.forgotPassword);

        showHidePass = (CheckBox)findViewById(R.id.show_hide_pass);
        btngotoregister = (Button)findViewById(R.id.btngotoregister);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("કૃપયા કરી રાહ જુવો");

        btnLogin.setOnClickListener(this);
        btngotoregister.setOnClickListener(this);

        helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AppHelplineActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

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
        if (v==btnLogin){
            if (etUsername.getText().toString().trim().isEmpty() || etPassword.getText().toString().trim().isEmpty()){
                Toast.makeText(getApplicationContext(),"કૃપયા કરી બધી વિગતો ભરો",Toast.LENGTH_SHORT).show();
            }else{
                userLogin();
            }
        }

        if(v==btngotoregister){
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    private void userLogin(){
        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(response);
                            if (!object.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                object.getInt("id"),
                                                object.getString("user_name"),
                                                object.getString("email"),
                                                object.getString("profile_pic")
                                        );
                                Toast.makeText(
                                        getApplicationContext(),
                                        "સ્વાગત છે.",
                                        Toast.LENGTH_SHORT
                                ).show();
                                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        object.getString("message"),
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
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",username);
                params.put("password",password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void openDialog(){
        DialogForgotPass dialogForgotPass = new DialogForgotPass();
        dialogForgotPass.show(getSupportFragmentManager(),"forgot password dialog");
    }

}
