package com.example.careercounsellor;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.careercounsellor.ModelClasses.CounsellorModel;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookedCounsellorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookedCounsellorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookedCounsellorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookedCounsellorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookedCounsellorFragment newInstance(String param1, String param2) {
        BookedCounsellorFragment fragment = new BookedCounsellorFragment();
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

    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_booked_counsellor, container, false);

        dbHelper = new DBHelper(getContext());

        ImageView bookedCounsellorImg = view.findViewById(R.id.bookedCounsellorImg);
        TextView bookedCounsellorName = view.findViewById(R.id.bookedCounsellorName);
        TextView bookedCounsellorRating = view.findViewById(R.id.bookedCounsellorRating);
        TextView bookedCounsellorPrice = view.findViewById(R.id.bookedCounsellorPrice);
        TextView noSessions = view.findViewById(R.id.noSessions);
        TextView dateTimeCall = view.findViewById(R.id.dateTimeCall);

        CounsellorModel counsellorModel = dbHelper.getLatestCounsellor();

        if(counsellorModel == null){
            noSessions.setVisibility(View.VISIBLE);
            dateTimeCall.setVisibility(View.GONE);
        }else{
            noSessions.setVisibility(View.GONE);
            dateTimeCall.setVisibility(View.VISIBLE);
            dateTimeCall.setText("Your counselling session has been successfully scheduled on "+counsellorModel.getDate()+ " at "+counsellorModel.getTime()+ " on "+counsellorModel.getTypeOfCall()+" with the expert");
        bookedCounsellorImg.setImageResource(counsellorModel.getImg());
        bookedCounsellorName.setText(counsellorModel.getName());
        bookedCounsellorRating.setText(counsellorModel.getRating());
        bookedCounsellorPrice.setText(counsellorModel.getPrice());
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