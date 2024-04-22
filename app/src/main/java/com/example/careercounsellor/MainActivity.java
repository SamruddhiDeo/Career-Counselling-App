package com.example.careercounsellor;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.careercounsellor.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DBHelper dbHelper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        loadFragment(new HomeFragment(),true);

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.navHome){
                    loadFragment(new HomeFragment(),false);
                } else if(id == R.id.navExplore){
                    loadFragment(new ExploreFragment(),false);
                } else if(id == R.id.navTests){
                    loadFragment(new TestsFragment(),false);
                } else if(id == R.id.navCounsellor){
                    loadFragment(new CounsellorFragment(),false);
                } else if(id == R.id.navProfile){
                    loadFragment(new ProfileFragment(),false);
                }

                return true;
            }
        });

        binding.bottomNavigation.setSelectedItemId(R.id.navHome);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder exitApp = new AlertDialog.Builder(MainActivity.this);
                exitApp.setTitle("Exit?")
                        .setMessage("Are you sure you want to leave the app?")
                        .setIcon(R.drawable.baseline_exit_to_app_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                exitApp.show();
            }
        };

        // Register the callback with the OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);

    }

    public void loadFragment(Fragment fragment,boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag){
        ft.add(R.id.frameLayout,fragment);
        }else{
        ft.replace(R.id.frameLayout,fragment);
        }
        ft.commit();
    }


}