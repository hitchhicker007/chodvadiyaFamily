package com.hfu.chodvadiya.ui.gallery;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfu.chodvadiya.Constants;
import com.hfu.chodvadiya.R;
import com.hfu.chodvadiya.RequestHandler;
import com.hfu.chodvadiya.adapters.SliderAdapter;
import com.hfu.chodvadiya.models.Ads;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GalleryFragment extends Fragment implements SliderAdapter.OnSliderListner {

    private GalleryViewModel galleryViewModel;
    private RecyclerView recyclerView;
    SliderAdapter sliderAdapter;
    List<Ads> adsList;
    private ProgressDialog progressDialog;
    RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        recyclerView = (RecyclerView)root.findViewById(R.id.recycler_menu_ad);

        adsList = new ArrayList<>();

        progressDialog = new ProgressDialog(getContext());
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        progressDialog.setMessage("કૃપયા કરી રાહ જુવો");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SLIDER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray contacts_list = new JSONArray(response);
                            for(int i=0;i<contacts_list.length();i++){
                                JSONObject contactObject = contacts_list.getJSONObject(i);

                                Ads ads = new Ads(contactObject.getString("url"));
                                adsList.add(ads);
//                                contactsList.add(contacts);
                            }
                            sliderAdapter = new SliderAdapter(getContext(),adsList,GalleryFragment.this);
                            recyclerView.setAdapter(sliderAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"Please Try again",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

        final TextView textView = root.findViewById(R.id.text_gallery);

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onSliderClick(int position) {

    }
}
