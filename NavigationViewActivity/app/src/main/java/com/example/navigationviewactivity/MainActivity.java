package com.example.navigationviewactivity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.navigationviewactivity.fragment.AccountFragment;
import com.example.navigationviewactivity.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    Fragment currentFragment;
    NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.setup:
//                    Toast.makeText(MainActivity.this,"Setting",Toast.LENGTH_LONG).show();
                    currentFragment = new SettingFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame, new SettingFragment());
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(Gravity.START);
                    return true;
                case R.id.account:
//                    Toast.makeText(MainActivity.this,"Account",Toast.LENGTH_LONG).show();
                    currentFragment = new AccountFragment();
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    fragmentTransaction1.replace(R.id.frame, new AccountFragment());
                    fragmentTransaction1.commit();
                    drawerLayout.closeDrawer(Gravity.START);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentFragment = new SettingFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, new SettingFragment());
        fragmentTransaction.commit();
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.mytoolbar);
        navigationView = findViewById(R.id.mynavigationview);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_naviagition,R.string.close_navigation);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        if(currentFragment instanceof SettingFragment){
            super.onBackPressed();
        }
        else if(currentFragment instanceof AccountFragment){
            currentFragment = new SettingFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame, new SettingFragment());
            fragmentTransaction.commit();
            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }
}
