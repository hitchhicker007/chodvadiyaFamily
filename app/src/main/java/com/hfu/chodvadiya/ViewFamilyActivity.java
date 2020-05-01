package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfu.chodvadiya.adapters.FamilyAdapter;
import com.hfu.chodvadiya.models.Family;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ViewFamilyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    FamilyAdapter familyAdapter;
    List<Family> familyList;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_family);

        loadmembers();

    }

    private void loadmembers() {
        familyList = new ArrayList<>();
        final String foreign_key = Objects.requireNonNull(getIntent().getStringExtra("foreign_key"));
        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu_family_members);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_GET_FAMILY_MEMBERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray family_list = new JSONArray(response);
                            for(int i=0;i<family_list.length();i++){
                                JSONObject jsonObject = family_list.getJSONObject(i);
                                Family family = new Family(jsonObject.getInt("id")
                                        ,jsonObject.getString("name")
                                        ,jsonObject.getString("relation")
                                        ,jsonObject.getString("xender")
                                        ,jsonObject.getString("blood_group")
                                        ,jsonObject.getString("status")
                                        ,jsonObject.getString("mobile_member")
                                        ,jsonObject.getString("dob")
                                        ,jsonObject.getString("education")
                                        ,jsonObject.getString("profession")
                                        ,jsonObject.getString("profession_field")
                                        ,jsonObject.getString("village_mosal")
                                        ,jsonObject.getString("surname_mosal"));

                                familyList.add(family);
                            }
                            familyAdapter = new FamilyAdapter(getApplicationContext(),familyList);
                            recyclerView.setAdapter(familyAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Please Try again",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("foreign_key",foreign_key);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
