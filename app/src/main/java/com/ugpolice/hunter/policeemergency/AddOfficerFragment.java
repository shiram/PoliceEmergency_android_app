package com.ugpolice.hunter.policeemergency;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddOfficerFragment extends Fragment {

    EditText user_firstname, user_lastname, user_email, user_phone, user_unique_id;
    AppCompatButton add_officer;
    String firstname, lastname, email, phone, unique_id;

    AwesomeValidation awesomeValidation;
    ProgressDialog progressDialog;


    public AddOfficerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_add_officer, container, false);
        user_firstname = v.findViewById(R.id.user_firstname);
        user_lastname = v.findViewById(R.id.user_lastname);
        user_email = v.findViewById(R.id.user_email);
        user_phone = v.findViewById(R.id.user_phone);
        user_unique_id = v.findViewById(R.id.unique_id);
        add_officer = v.findViewById(R.id.add_officer);

        add_officer.setOnClickListener(mAddOfficer);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Adding...");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        return v;
    }

    private View.OnClickListener mAddOfficer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //send to server:: first initdata
            initData();
            if(awesomeValidation.validate()){
                //send from here
                progressDialog.show();
                new Uploader(getContext(),progressDialog).addOfficer(firstname,lastname,email,phone,unique_id);
                user_firstname.setText("");
                user_lastname.setText("");
                user_email.setText("");
                user_phone.setText("");
                user_unique_id.setText("");
            }
        }
    };

    private void initData(){
        firstname = user_firstname.getText().toString().trim();
        lastname = user_lastname.getText().toString().trim();
        email = user_email.getText().toString().trim();
        phone = user_phone.getText().toString().trim();
        unique_id = user_unique_id.getText().toString().trim();
        AddValidation();
    }

    private void AddValidation(){
        awesomeValidation.addValidation(user_firstname, RegexTemplate.NOT_EMPTY, getString(R.string.item_name_err));
        awesomeValidation.addValidation(user_lastname, RegexTemplate.NOT_EMPTY, getString(R.string.item_name_err));
        awesomeValidation.addValidation(user_email, Patterns.EMAIL_ADDRESS, getString(R.string.item_name_err));
        awesomeValidation.addValidation(user_phone, Patterns.PHONE, getString(R.string.item_name_err));
        awesomeValidation.addValidation(user_unique_id, RegexTemplate.NOT_EMPTY, getString(R.string.item_name_err));
    }

}
