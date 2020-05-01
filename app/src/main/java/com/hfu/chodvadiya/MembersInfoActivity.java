package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfu.chodvadiya.adapters.MembersAdapter;
import com.hfu.chodvadiya.models.Members;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MembersInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Members> membersList;
    RecyclerView.LayoutManager layoutManager;
    private MembersAdapter membersAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_info);

        progressDialog = new ProgressDialog(this);
        membersList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu_committee_members);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        loadData();
    }

    private void loadData(){
        progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_MEMBERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray gallarydata = new JSONArray(response);
                            for(int i=0;i<gallarydata.length();i++){
                                JSONObject object = gallarydata.getJSONObject(i);
                                Members members = new Members(
                                        object.getInt("id"),
                                        object.getString("name"),
                                        object.getString("village"),
                                        object.getString("mobile"),
                                        object.getString("url")
                                        );
                                membersList.add(members);
                            }
                            membersAdapter = new MembersAdapter(getApplicationContext(),membersList);
                            recyclerView.setAdapter(membersAdapter);
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
                return super.getParams();
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
