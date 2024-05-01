package com.example.careercounsellor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.careercounsellor.ModelClasses.TestResultModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewResultFragment newInstance(String param1, String param2) {
        ViewResultFragment fragment = new ViewResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_result, container, false);

        DBHelper dbHelper = new DBHelper(getContext());

        AppCompatButton aptitudeViewResult = view.findViewById(R.id.aptitudeViewResult);
        AppCompatButton personalityViewResult = view.findViewById(R.id.personalityViewResult);

        aptitudeViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestResultModel aptitudeResult = dbHelper.getLatestAptitudeResult();
                if(aptitudeResult == null){
                    AlertDialog.Builder viewResultDialog = new AlertDialog.Builder(getContext());
                    viewResultDialog.setTitle("Take Aptitude test")
                            .setMessage("Please first take the aptitude test to view result")
                            .setIcon(R.drawable.baseline_warning_24)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Remove the current fragment
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout); // Replace R.id.fragment_container with your fragment container ID
                                    if (currentFragment != null) {
                                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                                        transaction.remove(currentFragment).commit();
                                    }
                                    loadFragment(new TestsFragment());
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    viewResultDialog.show();
                }else {
                ResultFragment fragment = new ResultFragment();
                Bundle args = new Bundle();
                args.putString("testType", "none");
                args.putDouble("Percent A", aptitudeResult.getPercentCol1());
                args.putDouble("Percent B", aptitudeResult.getPercentCol2());
                args.putDouble("Percent C", aptitudeResult.getPercentCol3());
                args.putDouble("Percent D", aptitudeResult.getPercentCol4());
                args.putString("Recommendation A", aptitudeResult.getCol1());
                args.putString("Recommendation B", aptitudeResult.getCol2());
                args.putString("Recommendation C", aptitudeResult.getCol3());
                args.putString("Recommendation D", aptitudeResult.getCol4());
                args.putString("Recommendation message", aptitudeResult.getSuggestion());

                fragment.setArguments(args);
                loadFragment(fragment);
                }
            }
        });

        personalityViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestResultModel personalityResult = dbHelper.getLatestPersonalityResult();
                if(personalityResult == null){
                    AlertDialog.Builder viewResultDialog = new AlertDialog.Builder(getContext());
                    viewResultDialog.setTitle("Take Personality test")
                            .setMessage("Please first take the personality test to view result")
                            .setIcon(R.drawable.baseline_warning_24)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Remove the current fragment
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout); // Replace R.id.fragment_container with your fragment container ID
                                    if (currentFragment != null) {
                                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                                        transaction.remove(currentFragment).commit();
                                    }
                                    loadFragment(new TestsFragment());
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    viewResultDialog.show();
                }else {
                ResultFragment fragment = new ResultFragment();
                Bundle args = new Bundle();
                args.putString("testType", "none");
                args.putDouble("Percent A", personalityResult.getPercentCol1());
                args.putDouble("Percent B", personalityResult.getPercentCol2());
                args.putDouble("Percent C", personalityResult.getPercentCol3());
                args.putDouble("Percent D", personalityResult.getPercentCol4());
                args.putString("Recommendation A", personalityResult.getCol1());
                args.putString("Recommendation B", personalityResult.getCol2());
                args.putString("Recommendation C", personalityResult.getCol3());
                args.putString("Recommendation D", personalityResult.getCol4());
                args.putString("Recommendation message", personalityResult.getSuggestion());

                fragment.setArguments(args);
                loadFragment(fragment);
                }
            }
        });


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

    public void loadFragment(Fragment fragment){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout,fragment);
        ft.commit();
    }

}