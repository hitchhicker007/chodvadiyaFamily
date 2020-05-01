package com.hfu.chodvadiya;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpinnerData
{
    public void village_list(final Context ctx, final Spinner spinner_village){
        final List<String> villages;
        villages = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SPINNER_VLG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            villages.add(0,"તમારું મૂળ વતન પસંદ કરો");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                villages.add(object.getString("village"));
                            }
                            ArrayAdapter<String> dataAdapter;
                            dataAdapter = new ArrayAdapter(ctx,android.R.layout.simple_spinner_item,villages);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_village.setAdapter(dataAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ctx,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        RequestHandler.getInstance(ctx).addToRequestQueue(stringRequest);
    }

    public void business_list(final Context ctx, final Spinner spinner_business){
        final List<String> business_list;
        business_list = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SPINNER_BUSINESS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            business_list.add(0,"તમારું વ્યયસાય ક્ષેત્ર પસંદ કરો");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                business_list.add(object.getString("data"));
                            }
                            ArrayAdapter<String> dataAdapter;
                            dataAdapter = new ArrayAdapter(ctx,android.R.layout.simple_spinner_item,business_list);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_business.setAdapter(dataAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ctx,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        RequestHandler.getInstance(ctx).addToRequestQueue(stringRequest);
    }

    public void taluka_list(final Context ctx, final Spinner spinner_taluka){
        final List<String> taluka_list;
        taluka_list = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SPINNER_TALUKA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            taluka_list.add(0,"તમારો તાલુકો પસંદ કરો");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                taluka_list.add(object.getString("taluka"));
                            }
                            ArrayAdapter<String> dataAdapter;
                            dataAdapter = new ArrayAdapter(ctx,android.R.layout.simple_spinner_item,taluka_list);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_taluka.setAdapter(dataAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ctx,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        RequestHandler.getInstance(ctx).addToRequestQueue(stringRequest);
    }

    public void district_list(final Context ctx, final Spinner spinner_district){
        final List<String> district_list;
        district_list = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SPINNER_DIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            district_list.add(0,"તમારો જિલ્લો પસંદ કરો");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                district_list.add(object.getString("dist"));
                            }
                            ArrayAdapter<String> dataAdapter;
                            dataAdapter = new ArrayAdapter(ctx,android.R.layout.simple_spinner_item,district_list);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_district.setAdapter(dataAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ctx,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        RequestHandler.getInstance(ctx).addToRequestQueue(stringRequest);
    }

    public void eductaion_list(final Context ctx, final Spinner spinner_eductaion){
        final List<String> eductaion_list;
        eductaion_list = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SPINNER_EDU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            eductaion_list.add(0,"તમારું શિક્ષણ પસંદ કરો");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                eductaion_list.add(object.getString("data"));
                            }
                            ArrayAdapter<String> dataAdapter;
                            dataAdapter = new ArrayAdapter(ctx,android.R.layout.simple_spinner_item,eductaion_list);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_eductaion.setAdapter(dataAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ctx,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        RequestHandler.getInstance(ctx).addToRequestQueue(stringRequest);
    }
}
