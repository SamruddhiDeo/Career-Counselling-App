package com.example.careercounsellor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.careercounsellor.Adapters.CounsellorAdapter;
import com.example.careercounsellor.ModelClasses.CounsellorModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CounsellorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CounsellorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CounsellorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CounsellorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CounsellorFragment newInstance(String param1, String param2) {
        CounsellorFragment fragment = new CounsellorFragment();
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
        View view = inflater.inflate(R.layout.fragment_counsellor, container, false);

        ArrayList<CounsellorModel> arrCounsellors = new ArrayList<>();

        arrCounsellors.add(new CounsellorModel(R.drawable.counsellor1, "David Pal", "4.5⭐","Rs.2000"));
        arrCounsellors.add(new CounsellorModel(R.drawable.counsellor2, "John Blanchard", "4.9⭐","Rs.3000"));
        arrCounsellors.add(new CounsellorModel(R.drawable.counsellor3, "Ricky Gill", "4.4⭐","Rs.1500"));
        arrCounsellors.add(new CounsellorModel(R.drawable.counsellor4, "Robin Mayer", "4.8⭐","Rs.2500"));
        arrCounsellors.add(new CounsellorModel(R.drawable.counsellor5, "Jordan Fischer", "4.7⭐","Rs.1800"));
        arrCounsellors.add(new CounsellorModel(R.drawable.counsellor6, "Albert Davila", "5.0⭐","Rs.3000"));
        arrCounsellors.add(new CounsellorModel(R.drawable.counsellor7, "Charles Harmon", "4.7⭐","Rs.1200"));

        RecyclerView recyclerViewCounsellors = view.findViewById(R.id.recyclerViewCounsellors);
        recyclerViewCounsellors.setLayoutManager(new LinearLayoutManager(getContext()));
        CounsellorAdapter counsellorAdapter = new CounsellorAdapter(getContext(),arrCounsellors,getParentFragmentManager());
        recyclerViewCounsellors.setAdapter(counsellorAdapter);

        return view;
    }



}