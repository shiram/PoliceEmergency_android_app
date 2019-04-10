package com.ugpolice.hunter.policeemergency;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterConnector {

    private RecyclerView jobs_list;
    private List<OfficerData> officerData;
    private  RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private String data;

    public static OfficerAdapter officerAdapter;
    private Context context;

    public AdapterConnector(RecyclerView jobs_list, String data, Context context) {
        this.jobs_list = jobs_list;
        this.data = data;
        this.context = context;
    }

    public void populateList(){
        try{
            officerData = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int officer_id = jsonObject.getInt("officer_id");
                String firstname = jsonObject.getString("firstname");
                String lastname = jsonObject.getString("lastname");
                String email = jsonObject.getString("email");
                String phone = jsonObject.getString("phone");
                String unique_id = jsonObject.getString("unique_id");

                officerData.add(new OfficerData(officer_id,firstname,lastname,email,phone,unique_id));
            }
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            jobs_list.setLayoutManager(layoutManager);

            officerAdapter = new OfficerAdapter(context, officerData);
            jobs_list.setAdapter(officerAdapter);
        }catch (JSONException e){
            Toast.makeText(context, Config.NO_DATA_ERROR, Toast.LENGTH_SHORT).show();
            Log.e("MSG", e.getMessage().toString());
        }
    }
}
