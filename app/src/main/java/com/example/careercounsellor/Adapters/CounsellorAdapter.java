package com.example.careercounsellor.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercounsellor.DBHelper;
import com.example.careercounsellor.ModelClasses.CounsellorModel;
import com.example.careercounsellor.R;
import com.example.careercounsellor.ViewDetailsCounsellorFragment;

import java.util.ArrayList;

public class CounsellorAdapter extends RecyclerView.Adapter<CounsellorAdapter.ViewHolder> {
    Context context;
    ArrayList<CounsellorModel> arrCounsellors;
    FragmentManager fm;
    DBHelper dbHelper;
    public CounsellorAdapter(Context context, ArrayList<CounsellorModel> arrCounsellors,FragmentManager fm){
        this.context= context;
        this.arrCounsellors = arrCounsellors;
        dbHelper = new DBHelper(context);
        this.fm = fm;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.counsellors_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CounsellorAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.counsellorName.setText(arrCounsellors.get(position).name);
        holder.counsellorRating.setText(arrCounsellors.get(position).rating);
        holder.counsellorPrice.setText(arrCounsellors.get(position).price);
        holder.counsellorImg.setImageResource(arrCounsellors.get(position).img);

        holder.counsellorMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDetailsCounsellorFragment fragment = new ViewDetailsCounsellorFragment();
                Bundle args = new Bundle();
                args.putString("name", arrCounsellors.get(position).name);
                args.putString("rating", arrCounsellors.get(position).rating);
                args.putString("price", arrCounsellors.get(position).price);
                args.putInt("img", arrCounsellors.get(position).img);

                fragment.setArguments(args);
                loadFragment(fragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrCounsellors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout counsellorMainLayout;
        TextView counsellorName,counsellorRating,counsellorPrice;
        ImageView counsellorImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            counsellorMainLayout = itemView.findViewById(R.id.bookedCounsellorMainLayout);
            counsellorImg = itemView.findViewById(R.id.bookedCounsellorImg);
            counsellorName = itemView.findViewById(R.id.bookedCounsellorName);
            counsellorRating = itemView.findViewById(R.id.bookedCounsellorRating);
            counsellorPrice = itemView.findViewById(R.id.bookedCounsellorPrice);
        }
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}
