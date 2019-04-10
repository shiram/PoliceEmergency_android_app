package com.ugpolice.hunter.policeemergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {
AppCompatButton police_account, civilian_account, admin_account;
DataStore dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataStore = new DataStore(MainActivity.this);
        if((dataStore.getAccessLevel() <= 0) && (dataStore.getUserId() > 0)){
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
        }

        police_account = findViewById(R.id.police_account);
        civilian_account = findViewById(R.id.civilian_account);
        admin_account = findViewById(R.id.admin_account);

        police_account.setOnClickListener(mPoliceAccount);
        civilian_account.setOnClickListener(mCivilianAccount);
        admin_account.setOnClickListener(mAdminAccount);

    }

    private View.OnClickListener mPoliceAccount = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, PoliceLogin.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mCivilianAccount = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, CivilianRegister.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mAdminAccount = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, PoliceLogin.class);
            startActivity(intent);
        }
    };
}
