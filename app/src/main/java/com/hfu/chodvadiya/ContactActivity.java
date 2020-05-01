package com.hfu.chodvadiya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.hfu.chodvadiya.adapters.ContactAdapter;
import com.hfu.chodvadiya.models.Contacts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactActivity extends AppCompatActivity implements ContactAdapter.OnContactListner {

    private RecyclerView recyclerView;
    ContactAdapter adapter;
    List<Contacts> contactsList;
    private Spinner spinner;
    private Button button;
    private Spinner spinner_villagename;
    private ProgressDialog progressDialog;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        button = (Button)findViewById(R.id.find_button);
        spinner_villagename = (Spinner)findViewById(R.id.spinner_choose_village);
        progressDialog = new ProgressDialog(this);

        SpinnerData spinnerData = new SpinnerData();
        spinnerData.village_list(this,spinner_villagename);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadContacts();
            }
        });
    }


    private void loadContacts(){
        final String village = spinner_villagename.getSelectedItem().toString().trim();
        contactsList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu_contacts);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        if (!village.equals("તમારું મૂળ વતન પસંદ કરો")){
            progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_CONTACT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONArray contacts_list = new JSONArray(response);
                                for(int i=0;i<contacts_list.length();i++){
                                    JSONObject contactObject = contacts_list.getJSONObject(i);

                                    int id = contactObject.getInt("id");
                                    String profile_pic = contactObject.getString("profile_pic");
                                    String name = contactObject.getString("name");
                                    String mobile_number = contactObject.getString("mobile_member");
                                    String address = contactObject.getString("home_address");
                                    String profession = contactObject.getString("profession");
//                                    String email = contactObject.getString("email");

                                    Contacts contacts = new Contacts(id,name,profile_pic,mobile_number,address,profession);
                                    contactsList.add(contacts);
                                }
                                adapter = new ContactAdapter(getApplicationContext(),contactsList,ContactActivity.this);
                                recyclerView.setAdapter(adapter);

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
                    params.put("village_name",village);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }else{
            Toast.makeText(getApplicationContext(),"કૃપયા કરી બધી વિગતો ભરો",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onContactClick(int position) {
        contactsList.get(position);
        Intent intent = new Intent(this,ViewContactActivity.class);
        intent.putExtra("id",String.valueOf(contactsList.get(position).getId()));
        startActivity(intent);
    }
}
