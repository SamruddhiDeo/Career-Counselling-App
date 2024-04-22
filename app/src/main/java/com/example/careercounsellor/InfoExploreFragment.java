package com.example.careercounsellor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoExploreFragment extends Fragment {



    public InfoExploreFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info_explore, container, false);

        TextView jobTitle = view.findViewById(R.id.jobTitle);
        TextView jobProspectsDescription = view.findViewById(R.id.jobProspectsDescription);
        TextView jobSalaryDescription = view.findViewById(R.id.jobSalaryDescription);
        TextView jobSkillsDescription = view.findViewById(R.id.jobSkillsDescription);

        Bundle bundle = getArguments();
        String data = bundle.getString("career");

        if(bundle != null){


        if(data == "software"){
            jobTitle.setText("Software Developer");
            String desc = getResources().getString(R.string.jobDoctor);
            jobProspectsDescription.setText(desc);
            jobProspectsDescription.setText(getString(R.string.jobSoftware));
            jobSkillsDescription.setText(getString(R.string.skillsSoftware));
            jobSalaryDescription.setText(getString(R.string.salarySoftware));
        } else if (data == "civil") {
            jobTitle.setText("Civil Engineer");
            jobProspectsDescription.setText(getString(R.string.jobCivil));
            jobSkillsDescription.setText(getString(R.string.skillsCivil));
            jobSalaryDescription.setText(getString(R.string.salaryCivil));
        } else if (data == "graphic") {
            jobTitle.setText("Graphic Designer");
            jobProspectsDescription.setText(getString(R.string.jobGraphic));
            jobSkillsDescription.setText(getString(R.string.skillsGraphic));
            jobSalaryDescription.setText(getString(R.string.salaryGraphic));
        } else if (data == "lawyer") {
            jobTitle.setText("Lawyer");
            jobProspectsDescription.setText(getString(R.string.jobLawyer));
            jobSkillsDescription.setText(getString(R.string.skillsLawyer));
            jobSalaryDescription.setText(getString(R.string.salaryLawyer));
        } else if (data == "doctor") {
            jobTitle.setText("Doctor");
            jobProspectsDescription.setText(getString(R.string.jobDoctor));
            jobSkillsDescription.setText(getString(R.string.skillsDoctor));
            jobSalaryDescription.setText(getString(R.string.salaryDoctor));
        }

}
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    // Pop the back stack to return to the previous fragment
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {

                }
            }
        });
    }

}