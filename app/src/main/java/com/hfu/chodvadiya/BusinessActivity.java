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
import com.hfu.chodvadiya.adapters.BusinessAdapter;
import com.hfu.chodvadiya.models.Business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button button;
    private RecyclerView recyclerView;
    List<Business> businessList;
    RecyclerView.LayoutManager layoutManager;
    private BusinessAdapter businessAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        spinner = (Spinner)findViewById(R.id.spinner_choose_business);
        button = (Button)findViewById(R.id.find_button_business);
        progressDialog = new ProgressDialog(this);

        SpinnerData spinnerData = new SpinnerData();
        spinnerData.business_list(this,spinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBusiData();
            }
        });

    }

    private void loadBusiData(){
        final String busi = spinner.getSelectedItem().toString().trim();
        businessList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu_business);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        if (!busi.equals("તમારું વ્યયસાય ક્ષેત્ર પસંદ કરો")){
            progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_FIND_BUSINESS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONArray busidata = new JSONArray(response);
                                for (int i=0;i<busidata.length();i++){
                                    JSONObject object = busidata.getJSONObject(i);
                                    Business business = new Business(
                                            object.getInt("id"),
                                            object.getString("name"),
                                            object.getString("xender"),
                                            object.getString("education"),
                                            object.getString("profession"),
                                            object.getString("profession_loc"),
                                            object.getString("mobile_member"),
                                            object.getString("home_address"),
                                            object.getString("village"),
                                            object.getString("taluko"),
                                            object.getString("district")
                                    );
                                    businessList.add(business);
                                }
                                businessAdapter = new BusinessAdapter(getApplicationContext(),businessList);
                                recyclerView.setAdapter(businessAdapter);
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
                            onBackPressed();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("busi",busi);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }else{
            Toast.makeText(getApplicationContext(),"કૃપયા કરી બધી વિગતો ભરો",Toast.LENGTH_LONG).show();
        }
    }
}
