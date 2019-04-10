package com.ugpolice.hunter.policeemergency;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class PoliceLogin extends AppCompatActivity {

    EditText user_id_number;
    AppCompatButton police_login;

    String unique_id;
    AwesomeValidation awesomeValidation;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_login);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Login");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        police_login = findViewById(R.id.police_login);
        user_id_number = findViewById(R.id.user_id_number);

        police_login.setOnClickListener(mLogin);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        progressDialog = new ProgressDialog(PoliceLogin.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Login...");
    }

    private void initData(){
        unique_id = user_id_number.getText().toString().trim();
        AddValidation();
    }

    private void AddValidation(){
        awesomeValidation.addValidation(user_id_number, RegexTemplate.NOT_EMPTY, getString(R.string.item_name_err));
    }

    private View.OnClickListener mLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            initData();
            if(awesomeValidation.validate()){
                //to server
                progressDialog.show();
                Receiver receiver = new Receiver(PoliceLogin.this, progressDialog);
                receiver.userLogin(unique_id);
            }

        }
    };
}
