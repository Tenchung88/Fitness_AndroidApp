package example.com.finalproject_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Fitness_Tracker extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String LOG = "Fitness App";
   private static final int MY_PERMISSIONS_REQUEST_WRITE_CONTACT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness__tracker);
        Log.i(LOG,"TENZIN CHOZOM 2759999");
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.CalculateBMI_id) {
            Intent i = new Intent(this, CalculateActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.Addroutine_id) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS);
            if(permissionCheck != PackageManager.PERMISSION_GRANTED)
            {
                Intent i = new Intent(this, AddTrainerActivity.class);
                startActivity(i);
                //case permission is not granted at runtime by the user
                //request for only 1 permission:callphone
                //array allows to define multiple permissions
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.WRITE_CONTACTS},
                        MY_PERMISSIONS_REQUEST_WRITE_CONTACT);

            } else{

                Intent i = new Intent(this,AddTrainerActivity.class);
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) ==
                        PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
            } else{
                    finish();
                }}

        } else if (id == R.id.contactus_id) {
            Intent i = new Intent(this, ContactUsActivity.class);
            startActivity(i);

        } else if (id == R.id.location_id){
            Intent i = new Intent(this, MapActivity.class);
            startActivity(i);
        }
        else if (id == R.id.about_id) {
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);


        } else if (id == R.id.exit_id) {
            finish();


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


