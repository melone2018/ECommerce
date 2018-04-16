package com.rjt.android.ecommerce;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rjt.R;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ProfileFragment mProfileFragment;
    ViewPager mViewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ArrayList<String> mImageViews;
    FragmentTransaction transaction;
    private onReceiveUserInfoListener inforListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] userInfo = new String[]{};
        userInfo = getIntent().getExtras().getStringArray("USERINFO");
        Log.d("ID:", userInfo[0]);
        Log.d("PW:", userInfo[1]);
        Log.d("API", userInfo[2]);
        String mobile = userInfo[0];
        String userPw = userInfo[1];
        String apiKey = userInfo[2];
        String userId = userInfo[3];
        Log.d("MOBILE", mobile);
        Log.d("PASSWORD", userPw);
        Log.d("APIKEY", apiKey);
        Log.d("USERID", userId);
        DefaultHomeFragment homeFragment = new DefaultHomeFragment();
        inforListener = (onReceiveUserInfoListener)homeFragment;
        inforListener.onUserInfoReceived(mobile, userPw, apiKey, userId);
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.fragmentContainer, homeFragment, "HOMEKEY").
                commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_profile) {
            // Handle the camera action
            Toast.makeText(this, "You cliked Profile", Toast.LENGTH_SHORT).show();
            transaction.replace(R.id.fragmentContainer, new ProfileFragment()).commit();
        } else if (id == R.id.nav_order) {
            Toast.makeText(this, "You cliked order", Toast.LENGTH_SHORT).show();
            transaction.replace(R.id.fragmentContainer, new OrderFragment()).commit();
        } else if (id == R.id.nav_shop) {
            Toast.makeText(this, "You chose Shop navigation", Toast.LENGTH_SHORT).show();
            transaction.replace(R.id.fragmentContainer, new ShopFragment()).commit();
        } else if (id == R.id.nav_logout) {
            Toast.makeText(this, "You chose Log out", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public interface onReceiveUserInfoListener{
        public void onUserInfoReceived(String mobile, String password, String apiKey, String userId);
    }


    public void initializeViewPager() {

    }
}
