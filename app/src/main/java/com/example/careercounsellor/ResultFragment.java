package com.example.careercounsellor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.careercounsellor.databinding.ActivityMainBinding;
import com.example.careercounsellor.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {



    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_result, container, false);

        TextView txtRecommendationA = view.findViewById(R.id.txtRecommendationA);
        TextView txtRecommendationB = view.findViewById(R.id.txtRecommendationB);
        TextView txtRecommendationC = view.findViewById(R.id.txtRecommendationC);
        TextView txtRecommendationD = view.findViewById(R.id.txtRecommendationD);
        TextView txtRecommendationMessage = view.findViewById(R.id.txtRecommendationMessage);

        ProgressBar progressBarA = view.findViewById(R.id.progressBarA);
        ProgressBar progressBarB = view.findViewById(R.id.progressBarB);
        ProgressBar progressBarC = view.findViewById(R.id.progressBarC);
        ProgressBar progressBarD = view.findViewById(R.id.progressBarD);

        Bundle recievedArgs = getArguments();

        Double percentOptionA = recievedArgs.getDouble("Percent A");
        Double percentOptionB = recievedArgs.getDouble("Percent B");
        Double percentOptionC = recievedArgs.getDouble("Percent C");
        Double percentOptionD = recievedArgs.getDouble("Percent D");
        String recommendationA = recievedArgs.getString("Recommendation A");
        String recommendationB = recievedArgs.getString("Recommendation B");
        String recommendationC = recievedArgs.getString("Recommendation C");
        String recommendationD = recievedArgs.getString("Recommendation D");
        String recommendationMessage = recievedArgs.getString("Recommendation message");

        txtRecommendationA.setText(recommendationA);
        txtRecommendationB.setText(recommendationB);
        txtRecommendationC.setText(recommendationC);
        txtRecommendationD.setText(recommendationD);

        progressBarA.setProgress((int)Math.round(percentOptionA));
        progressBarB.setProgress((int)Math.round(percentOptionB));
        progressBarC.setProgress((int)Math.round(percentOptionC));
        progressBarD.setProgress((int)Math.round(percentOptionD));

        txtRecommendationMessage.setText(recommendationMessage);

        return view;
    }

}