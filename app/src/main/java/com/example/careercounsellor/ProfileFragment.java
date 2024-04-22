package com.example.careercounsellor;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
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


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());

        TextView profileName = view.findViewById(R.id.profileName);
        TextView profileEmail = view.findViewById(R.id.profileEmail);
        TextView profileLogout = view.findViewById(R.id.profileLogout);
        TextView profileAboutUs = view.findViewById(R.id.profileAboutUs);
        TextView profileShareFeedback = view.findViewById(R.id.profileShareFeedback);

        SharedPreferences prefs = getActivity().getSharedPreferences("user_session",MODE_PRIVATE);
        profileName.setText(prefs.getString("name","null"));
        profileEmail.setText(prefs.getString("email","null"));

        profileAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              loadFragment(new AboutUsFragment());
            }
        });

        profileShareFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog feedbackDialog = new Dialog(getContext());
                feedbackDialog.setContentView(R.layout.feedback_layout);

                AppCompatButton feedbackCancelBtn = feedbackDialog.findViewById(R.id.previousButton);
                AppCompatButton feedbackSaveBtn = feedbackDialog.findViewById(R.id.nextButton);

                feedbackCancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        feedbackDialog.dismiss();
                    }
                });

                feedbackSaveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText feedbackDescription = feedbackDialog.findViewById(R.id.feedbackDescription);
                        String desc = feedbackDescription.getText().toString();
                        dbHelper.feedbackAdd(desc);

                        feedbackDialog.dismiss();
                        Toast.makeText(getContext(), "Your feedback is saved", Toast.LENGTH_SHORT).show();
                    }
                });

                Window window = feedbackDialog.getWindow();
                if (window != null) {
                    window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                }

                feedbackDialog.show();
            }
        });


        profileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext());
                logoutDialog.setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setIcon(R.drawable.baseline_logout_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences prefs = getActivity().getSharedPreferences("user_session",MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putBoolean("is_logged_in", false);
                                editor.apply();

                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                logoutDialog.show();
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