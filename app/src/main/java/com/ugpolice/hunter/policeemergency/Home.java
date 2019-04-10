package com.ugpolice.hunter.policeemergency;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
       TextView names = headerView.findViewById(R.id.names);
       TextView  contact = headerView.findViewById(R.id.contact);
       names.setText(new DataStore(Home.this).getUserNames());
       contact.setText(new DataStore(Home.this).getUserPhone());

       if(new DataStore(Home.this).getAccessLevel() == 1){
           //admin functions
           navigationView.getMenu().findItem(R.id.emergencies).setVisible(false);
           navigationView.getMenu().findItem(R.id.nav_emergency_report).setVisible(false);
           navigationView.getMenu().findItem(R.id.nav_civilian_emergencies).setVisible(false);
           navigationView.getMenu().findItem(R.id.nav_feedback).setVisible(false);
           fragment = new HomeFragment();
           loadFragment(fragment);
       }else if (new DataStore(Home.this).getAccessLevel() == 2){
           //officer functions
           navigationView.getMenu().findItem(R.id.nav_add_officer).setVisible(false);
           navigationView.getMenu().findItem(R.id.emergencies).setVisible(false);
           navigationView.getMenu().findItem(R.id.nav_feedback).setVisible(false);
       }else {
           //Client Functions
           navigationView.getMenu().findItem(R.id.nav_add_officer).setVisible(false);
           navigationView.getMenu().findItem(R.id.nav_emergency_report).setVisible(false);
           navigationView.getMenu().findItem(R.id.nav_civilian_emergencies).setVisible(false);
       }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            if(new DataStore(Home.this).getAccessLevel() == 1){
                //admin view
                fragment = new HomeFragment();
                loadFragment(fragment);
            }else if(new DataStore(Home.this).getAccessLevel() == 2){
                //officer view
            }else {
                //user view
            }
        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_ambulance) {

        } else if (id == R.id.nav_fire_brigade) {

        } else if (id == R.id.nav_patrol) {

        } else if (id == R.id.nav_civilian_emergencies) {

        } else if (id == R.id.nav_emergency_report) {

        } else if (id == R.id.nav_add_officer) {
            fragment = new AddOfficerFragment();
            loadFragment(fragment);

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
