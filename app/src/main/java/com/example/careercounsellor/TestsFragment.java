package com.example.careercounsellor;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestsFragment extends Fragment {


    public TestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_tests, container, false);

        AppCompatButton takeTestAptitude = view.findViewById(R.id.takeTestAptitude);
        AppCompatButton takeTestPersonality = view.findViewById(R.id.takeTestPersonality);

        takeTestAptitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestInstructionsFragment fragment = new TestInstructionsFragment();
                Bundle args = new Bundle();
                args.putString("testType", "Aptitude test");
                fragment.setArguments(args);
                loadFragment(fragment);
            }
        });

        takeTestPersonality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestInstructionsFragment fragment = new TestInstructionsFragment();
                Bundle args = new Bundle();
                args.putString("testType", "Personality test");
                fragment.setArguments(args);
                loadFragment(fragment);
            }
        });

       return view;
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}