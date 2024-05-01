package com.example.careercounsellor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.careercounsellor.ModelClasses.CounsellorModel;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleSessionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleSessionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScheduleSessionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleSessionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleSessionFragment newInstance(String param1, String param2) {
        ScheduleSessionFragment fragment = new ScheduleSessionFragment();
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

    String selectedTime;
    String selectedTypeOfCall;
    String selectedDate;
    TextView time1;
    TextView time2;
    TextView time3;
    TextView time4;
    TextView time5;
    TextView time6;
    TextView call1;
    TextView call2;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule_session, container, false);

        dbHelper = new DBHelper(getContext());

        DatePicker datePickerContainer = view.findViewById(R.id.datePickerContainer);
        AppCompatButton confirmCounsellor = view.findViewById(R.id.confirmCounsellor);
        time1 = view.findViewById(R.id.time1);
        time2 = view.findViewById(R.id.time2);
        time3 = view.findViewById(R.id.time3);
        time4 = view.findViewById(R.id.time4);
        time5 = view.findViewById(R.id.time5);
        time6 = view.findViewById(R.id.time6);
        call1 = view.findViewById(R.id.call1);
        call2 = view.findViewById(R.id.call2);

        Calendar calendar = Calendar.getInstance();
        datePickerContainer.setMinDate(calendar.getTimeInMillis());

        int day = datePickerContainer.getDayOfMonth();
        int month = datePickerContainer.getMonth() + 1;
        int year = datePickerContainer.getYear();
        selectedDate = day + "/" + month + "/" + year;

        confirmCounsellor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle recievedArgs = getArguments();

                String name = recievedArgs.getString("name");
                String rating = recievedArgs.getString("rating");
                String price = recievedArgs.getString("price");
                int img = recievedArgs.getInt("img");

                dbHelper.counsellorAdd(new CounsellorModel(img,name,rating,price,selectedDate,selectedTime,selectedTypeOfCall));

                AlertDialog.Builder confirmDialog = new AlertDialog.Builder(getContext());
                confirmDialog.setTitle("Session booked")
                        .setMessage("Your session with counsellor have been booked successfully")
                        .setIcon(R.drawable.baseline_done_24)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout); // Replace R.id.fragment_container with your fragment container ID
                                if (currentFragment != null) {
                                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                                    transaction.remove(currentFragment).commit();
                                }
                                loadFragment(new BookedCounsellorFragment());
                                dialog.dismiss();
                            }
                        });
                confirmDialog.show();

            }
        });

//        datePickerContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("checkbug","ckjdkd");
//                int day = datePickerContainer.getDayOfMonth();
//                int month = datePickerContainer.getMonth();
//                int year = datePickerContainer.getYear();
//
//                selectedDate = day + "/" + month + "/" + year;
//                Log.d("checkbug",selectedDate);
//            }
//        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePickerContainer.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    selectedDate = dayOfMonth + "/" + monthOfYear+1 + "/" + year;
                    Log.d("checkbug",selectedDate);
                }
            });
        }

        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime(v);
            }
        });

        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime(v);
            }
        });

        time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime(v);
            }
        });

        time4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime(v);
            }
        });

        time5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime(v);
            }
        });

        time6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime(v);
            }
        });

        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTypeOfCall(v);
            }
        });

        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTypeOfCall(v);
            }
        });

        return view;
    }

    public void selectTime(View v){
        TextView time = getView().findViewById(v.getId());
        selectedTime = time.getText().toString();
        time.setBackground(getResources().getDrawable(R.drawable.counselling_selected_time_bg));
        if(R.id.time1 != v.getId()){
            time1.setBackground(getResources().getDrawable(R.drawable.counselling_time_bg));
        }
         if(R.id.time2 != v.getId()){
            time2.setBackground(getResources().getDrawable(R.drawable.counselling_time_bg));
        }
         if(R.id.time3 != v.getId()){
            time3.setBackground(getResources().getDrawable(R.drawable.counselling_time_bg));
        }
         if(R.id.time4 != v.getId()){
            time4.setBackground(getResources().getDrawable(R.drawable.counselling_time_bg));
        }
         if(R.id.time5 != v.getId()){
            time5.setBackground(getResources().getDrawable(R.drawable.counselling_time_bg));
        }
         if(R.id.time6 != v.getId()){
            time6.setBackground(getResources().getDrawable(R.drawable.counselling_time_bg));
        }
    }

    public void selectTypeOfCall(View v){
        TextView typeOfCall = getView().findViewById(v.getId());
        selectedTypeOfCall = typeOfCall.getText().toString();
        typeOfCall.setBackground(getResources().getDrawable(R.drawable.counselling_selected_time_bg));
        if(R.id.call1 != v.getId()){
            call1.setBackground(getResources().getDrawable(R.drawable.counselling_time_bg));
        }
        if(R.id.call2 != v.getId()){
            call2.setBackground(getResources().getDrawable(R.drawable.counselling_time_bg));
        }
    }

    public void loadFragment(Fragment fragment){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout,fragment);
        ft.addToBackStack(null);
        ft.commit();
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