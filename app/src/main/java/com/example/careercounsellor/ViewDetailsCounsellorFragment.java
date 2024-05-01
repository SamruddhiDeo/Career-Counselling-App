package com.example.careercounsellor;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewDetailsCounsellorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDetailsCounsellorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewDetailsCounsellorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewDetailsCounsellorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDetailsCounsellorFragment newInstance(String param1, String param2) {
        ViewDetailsCounsellorFragment fragment = new ViewDetailsCounsellorFragment();
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
        View view = inflater.inflate(R.layout.fragment_view_details_counsellor, container, false);

        ImageView counsellorDetailImg = view.findViewById(R.id.counsellorDetailImg);
        TextView counsellorDetailName = view.findViewById(R.id.counsellorDetailName);
        TextView counsellorDetailRating = view.findViewById(R.id.counsellorDetailRating);
        TextView counsellorDetailPrice = view.findViewById(R.id.counsellorDetailPrice);
        TextView scheduleSession = view.findViewById(R.id.scheduleSession);

        Bundle recievedArgs = getArguments();

        String name = recievedArgs.getString("name");
        String rating = recievedArgs.getString("rating");
        String price = recievedArgs.getString("price");
        int img = recievedArgs.getInt("img");

        counsellorDetailName.setText(name);
        counsellorDetailPrice.setText(price);
        counsellorDetailRating.setText(rating);
        counsellorDetailImg.setImageResource(img);

        scheduleSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleSessionFragment fragment = new ScheduleSessionFragment();
                Bundle args = new Bundle();
                args.putString("name", name);
                args.putString("rating", rating);
                args.putString("price", price);
                args.putInt("img", img);

                fragment.setArguments(args);
                loadFragment(fragment);
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
        ft.addToBackStack(null);
        ft.commit();
    }

}