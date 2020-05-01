package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfu.chodvadiya.adapters.EducationAdapter;
import com.hfu.chodvadiya.models.Education;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EducationActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button button;
    private RecyclerView recyclerView;
    List<Education> educationList;
    RecyclerView.LayoutManager layoutManager;
    private EducationAdapter educationAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        spinner = (Spinner)findViewById(R.id.spinner_choose_education);
        button = (Button)findViewById(R.id.find_button_education);
        progressDialog = new ProgressDialog(this);

        SpinnerData spinnerData = new SpinnerData();
        spinnerData.eductaion_list(this,spinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadEduData();
            }
        });

    }

    private void loadEduData(){
        final String edu = spinner.getSelectedItem().toString().trim();
        educationList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu_education);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        if (!edu.equals("તમારું શિક્ષણ પસંદ કરો")){
            progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_FIND_EDUCATION,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONArray edudata = new JSONArray(response);
                                for (int i=0;i<edudata.length();i++){
                                    JSONObject object = edudata.getJSONObject(i);
                                    Education education = new Education(
                                            object.getInt("id"),
                                            object.getString("name"),
                                            object.getString("mobile_member"),
                                            object.getString("profession"),
                                            object.getString("profession_field"),
                                            object.getString("village"),
                                            object.getString("taluko"),
                                            object.getString("xender"),
                                            object.getString("district"),
                                            object.getString("home_address")
                                    );
                                    educationList.add(education);
                                }
                                educationAdapter = new EducationAdapter(getApplicationContext(),educationList);
                                recyclerView.setAdapter(educationAdapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Please Try again",Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("edu",edu);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }else{
            Toast.makeText(getApplicationContext(),"કૃપયા કરી બધી વિગતો ભરો",Toast.LENGTH_LONG).show();
        }
    }
}
