package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfu.chodvadiya.adapters.BloodGroupAdapter;
import com.hfu.chodvadiya.models.Bloods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BloodGroupActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button btn;
    private RecyclerView recyclerView;
    List<Bloods> bloodsList;
    RecyclerView.LayoutManager layoutManager;
    private BloodGroupAdapter bloodGroupAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_group);

        spinner = (Spinner)findViewById(R.id.spinner_choose_bg);
        btn = (Button)findViewById(R.id.find_button_bg);
        progressDialog = new ProgressDialog(this);

        ArrayAdapter<String> chooseBgAdapter = new ArrayAdapter<String>(BloodGroupActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.blood_grp));
        chooseBgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(chooseBgAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData(){
        final String bg = spinner.getSelectedItem().toString().trim();
        bloodsList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu_bg);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        if (!bg.equals("તમારું રક્ત જૂથ પસંદ કરો")){
            progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_FIND_BLOOD_GROUP,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            try {
                                JSONArray bgdata = new JSONArray(response);
                                for (int i=0;i<bgdata.length();i++){
                                    JSONObject object = bgdata.getJSONObject(i);
                                    Bloods bloods = new Bloods(
                                            object.getInt("id"),
                                            object.getString("name"),
                                            object.getString("village"),
                                            object.getString("taluko"),
                                            object.getString("district"),
                                            object.getString("home_address"),
                                            object.getString("xender"),
                                            object.getString("mobile_member")
                                            );
                                    bloodsList.add(bloods);
                                }
                                bloodGroupAdapter = new BloodGroupAdapter(getApplicationContext(),bloodsList);
                                recyclerView.setAdapter(bloodGroupAdapter);
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
                    params.put("bg",bg);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }else{
            Toast.makeText(getApplicationContext(),"કૃપયા કરી બધી વિગતો ભરો",Toast.LENGTH_LONG).show();
        }

    }
}
