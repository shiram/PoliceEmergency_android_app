package com.ugpolice.hunter.policeemergency;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.iid.FirebaseInstanceId;

public class CivilianRegister extends AppCompatActivity {
AppCompatButton user_login;
EditText user_firstname, user_lastname, user_phone;
String firstname, lastname, phone;
AwesomeValidation awesomeValidation;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilian_register);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Register");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        user_login = findViewById(R.id.user_login);
        user_login.setOnClickListener(mUserLogin);

        user_firstname = findViewById(R.id.user_firstname);
        user_lastname = findViewById(R.id.user_lastname);
        user_phone = findViewById(R.id.user_phone);

        progressDialog = new ProgressDialog(CivilianRegister.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Registering...");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    }

    private void initData(){
        firstname = user_firstname.getText().toString().trim();
        lastname = user_lastname.getText().toString().trim();
        phone = user_phone.getText().toString().trim();
        AddValidation();
    }

    private void AddValidation(){

        awesomeValidation.addValidation(user_firstname, RegexTemplate.NOT_EMPTY, getString(R.string.item_name_err));
        awesomeValidation.addValidation(user_lastname, RegexTemplate.NOT_EMPTY, getString(R.string.item_name_err));
        awesomeValidation.addValidation(user_phone, RegexTemplate.TELEPHONE, getString(R.string.phone_err));


    }

    private View.OnClickListener mUserLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initData();
            if(awesomeValidation.validate()){

                progressDialog.show();
                Uploader uploader = new Uploader(CivilianRegister.this, progressDialog);
                uploader.register(firstname,lastname,phone,FirebaseInstanceId.getInstance().getToken());
            }
        }
    };
}
