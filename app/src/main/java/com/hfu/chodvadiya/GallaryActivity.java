package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfu.chodvadiya.adapters.GallaryAdapter;
import com.hfu.chodvadiya.models.Gallary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GallaryActivity extends AppCompatActivity implements GallaryAdapter.OnGallaryListner{

    private RecyclerView recyclerView;
    List<Gallary> gallaryList;
    RecyclerView.LayoutManager layoutManager;
    private GallaryAdapter gallaryAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);

        progressDialog = new ProgressDialog(this);
        gallaryList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu_gallary);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        loadData();

    }

    private void loadData(){
        progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_GALLARY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray gallarydata = new JSONArray(response);
                            for(int i=0;i<gallarydata.length();i++){
                                JSONObject object = gallarydata.getJSONObject(i);
                                Gallary gallary = new Gallary(
                                        object.getInt("id"),
                                        object.getString("title"),
                                        object.getString("image"),
                                        object.getString("date")
                                        );
                                gallaryList.add(gallary);
                            }
                            gallaryAdapter = new GallaryAdapter(getApplicationContext(),gallaryList,GallaryActivity.this);
                            recyclerView.setAdapter(gallaryAdapter);
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

    @Override
    public void onGallaryClick(int position) {
        gallaryList.get(position);
        Intent intent = new Intent(getApplicationContext(),GallaryImagesActivity.class);
        intent.putExtra("cat",gallaryList.get(position).getTitle());
        startActivity(intent);
        Toast.makeText(getApplicationContext(),gallaryList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
    }
}
