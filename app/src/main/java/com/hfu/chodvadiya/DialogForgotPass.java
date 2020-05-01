package com.hfu.chodvadiya;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DialogForgotPass extends AppCompatDialogFragment {
    private EditText editText;
    private ProgressDialog progressDialog;
    private Button button;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view);

        editText = view.findViewById(R.id.forgot_email);
        button = (Button)view.findViewById(R.id.forgot_pass_btn);
        progressDialog = new ProgressDialog(getContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPass();
            }
        });

        return builder.create();
    }

    private void resetPass(){
        final String email = editText.getText().toString().trim();
        if (validateEmail()){
            progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_RESET_PASS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (!jsonObject.getBoolean("error")){
                                    Toast.makeText(
                                            getContext(),
                                            "You will get your password soon..",
                                            Toast.LENGTH_LONG
                                    ).show();
                                    startActivity(new Intent(getContext(),MainActivity.class));
                                }else{
                                    Toast.makeText(
                                            getContext(),
                                            jsonObject.getString("message"),
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("email",email);
                    return params;
                }
            };
            RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
        }
    }

    private boolean validateEmail(){
        String emailInput = editText.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            editText.setError("*કૃપયા કરી તમારું સાચું ઇમેઇલ લખો*");
            return false;
        }else{
            editText.setError(null);
            return true;
        }
    }
}
