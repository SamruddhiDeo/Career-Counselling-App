package com.example.careercounsellor;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DBHelper dbHelper = new DBHelper(getContext());

        CardView homeTests = view.findViewById(R.id.homeTests);
        CardView homeCounsellor = view.findViewById(R.id.homeCounsellor);
        CardView homeExplore = view.findViewById(R.id.homeExplore);
        CardView homeAboutUs = view.findViewById(R.id.homeAboutUs);
        TextView txtHello = view.findViewById(R.id.txtHello);

        SharedPreferences prefs = getActivity().getSharedPreferences("user_session",MODE_PRIVATE);
        txtHello.setText("Hello, "+prefs.getString("name","null"));

        homeTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new TestsFragment());
            }
        });

        homeCounsellor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CounsellorFragment());
            }
        });

        homeExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ExploreFragment());
            }
        });

        homeAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AboutUsFragment());
            }
        });
        

        return view;
    }

    public void loadFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout,fragment);
        ft.commit();
    }
    
}