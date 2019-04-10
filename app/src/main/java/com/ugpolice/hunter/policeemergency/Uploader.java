package com.ugpolice.hunter.policeemergency;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Uploader {

    Context context;
    ProgressDialog progressDialog;

    private String server_msg;
    private int id;
    private int user_id;
    private SharedPreferences sharedPreferences;
    DataStore dataStore;

    public Uploader(Context context) {
        this.context = context;
    }

    public Uploader(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
        dataStore = new DataStore(context);
    }


    public void register(final String firstname, final String lastname, final String user_phone, final String user_token) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");
                    id = result.getInt("user_id");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    Toast.makeText(context,Config.NO_SERVER_RESPONSE,Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER ERROR: ",e.getMessage());
                }
                progressDialog.dismiss();
                if(id > 0) {
                    dataStore.setPrefs(id,firstname,lastname,user_phone,user_token);
                    Intent intent = new Intent(context, Home.class);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context,server_msg,Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(context,Config.NO_SERVER_RESPONSE,Toast.LENGTH_SHORT).show();
                        Log.d("SERVER ERROR: ",error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Config.FIRSTNAME, firstname);
                params.put(Config.LASTNAME, lastname);
                params.put(Config.PHONE, user_phone);
                params.put(Config.TOKEN, user_token);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    public void addOfficer(final String firstname, final String lastname, final String user_email, final String user_phone, final String unique_id) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ADD_OFFICER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");
                    //id = result.getInt("user_id");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    Toast.makeText(context,Config.NO_SERVER_RESPONSE,Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER ERROR: ",e.getMessage());
                }
                progressDialog.dismiss();

                    Toast.makeText(context,server_msg,Toast.LENGTH_SHORT).show();


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(context,Config.NO_SERVER_RESPONSE,Toast.LENGTH_SHORT).show();
                        Log.d("SERVER ERROR: ",error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Config.FIRSTNAME, firstname);
                params.put(Config.LASTNAME, lastname);
                params.put("email", user_email);
                params.put(Config.PHONE, user_phone);
                params.put("unique_id", unique_id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
/*

    public void addProducts(final String item_name, final String item_price, final String item_desc, final String item_cat, final String country, final String city, final String address, final String lat, final String lng,  final String item_image_en, final String image_file ) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ADD_PRODUCT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject result = new JSONObject(response);
                    server_msg = result.getString("success");

                }catch (JSONException e){
                    progressDialog.dismiss();
                    Toast.makeText(context,Config.NO_SERVER_RESPONSE,Toast.LENGTH_SHORT).show();
                    Log.d("JSON ERROR: ",e.getMessage());

                }
                progressDialog.dismiss();
                Toast.makeText(context,server_msg,Toast.LENGTH_SHORT).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(context,Config.NO_SERVER_RESPONSE,Toast.LENGTH_SHORT).show();
//                        Log.d("SERVER ERROR: ",error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.USER_ID, Integer.toString(new DataStore(context).getUserId()));
                params.put(Config.ITEM_NAME, item_name);
                params.put(Config.ITEM_PRICE, item_price);
                params.put(Config.ITEM_DESC, item_desc);
                params.put(Config.ITEM_CAT, item_cat);
                params.put(Config.BUSINESS_COUNTRY, country);
                params.put(Config.BUSINESS_CITY, city);
                params.put(Config.BUSINESS_ADDRESS, address);
                params.put(Config.LAT, lat);
                params.put(Config.LNG, lng);
                params.put(Config.ITEM_IMAGE_FILE, image_file);
                params.put(Config.ITEM_IMAGE_ENCODED, item_image_en);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

*/

}
