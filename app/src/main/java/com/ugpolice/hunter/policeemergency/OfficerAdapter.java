package com.ugpolice.hunter.policeemergency;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OfficerAdapter extends RecyclerView.Adapter<OfficerAdapter.ViewHolder> {

    private Context context;
    private List<OfficerData> list;
    private ArrayList<OfficerData> arrayList;
    private ProgressDialog progressDialog;

    public OfficerAdapter(Context context, List<OfficerData> list) {
        this.context = context;
        this.list = list;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(list);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final OfficerData o = list.get(position);
        holder.job_title.setText(o.getFirstname() +" "+o.getLastname());
        holder.job_posted.setText("Unique ID: "+o.getUnique_id());

            holder.job_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Send email here with unique_id for that user
                }
            });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        AppCompatTextView job_title, job_posted;
        AppCompatTextView job_edit, job_delete;

        public ViewHolder(View view){
            super(view);
            job_posted = view.findViewById(R.id.job_posted);
            job_title = view.findViewById(R.id.job_title);
           // product_date_created = view.findViewById(R.id.product_date_created);
            job_delete = view.findViewById(R.id.job_delete);
        }

        @Override
        public void onClick(View v) {


        }
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if(charText.length() == 0){
            list.addAll(arrayList);
        }else{
            for(OfficerData o : arrayList){
                if(o.getFirstname().toLowerCase(Locale.getDefault()).contains(charText) || o.getLastname().toLowerCase(Locale.getDefault()).contains(charText) ){
                    list.add(o);
                }
            }
        }

        notifyDataSetChanged();
    }
}
