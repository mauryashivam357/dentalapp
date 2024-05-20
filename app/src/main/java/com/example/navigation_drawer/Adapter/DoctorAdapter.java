package com.example.navigation_drawer.Adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation_drawer.BookAppointment;
import com.example.navigation_drawer.DataModel.DoctorNearby;
import com.example.navigation_drawer.R;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private List<DoctorNearby> originalDoctorList;
    private static List<DoctorNearby> filteredDoctorList;

    public DoctorAdapter(List<DoctorNearby> doctorList) {
        this.originalDoctorList = doctorList;
        this.filteredDoctorList = new ArrayList<>(doctorList);
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        DoctorNearby doctor = filteredDoctorList.get(position);

        // Set data to your views in the ViewHolder
        holder.nameTextView.setText(doctor.getClinicName());
        holder.specialistTextView.setText(doctor.getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return filteredDoctorList.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView specialistTextView;
        Button btnBookNow;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.doctername);
            specialistTextView = itemView.findViewById(R.id.spacilest);
            btnBookNow = itemView.findViewById(R.id.btnbooknow);
            btnBookNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the position of the clicked item
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        DoctorNearby clickedDoctor = filteredDoctorList.get(position);

                        // Handle the "Book Now" button click
                        // For example, open a new activity passing some data
                        // You can customize this based on your requirements
                        Intent intent = new Intent(itemView.getContext(), BookAppointment.class);
                        intent.putExtra("doctorName", clickedDoctor.getClinicName());
                        intent.putExtra("specialist", clickedDoctor.getCreatedAt());
                        itemView.getContext().startActivity(intent);
                    }
                }
            });

            // Add other views as needed
        }
    }

    public void filter(String query) {
        filteredDoctorList.clear();

        if (TextUtils.isEmpty(query)) {
            filteredDoctorList.addAll(originalDoctorList);
        } else {
            for (DoctorNearby doctor : originalDoctorList) {
                if (doctor.getClinicName().toLowerCase().contains(query.toLowerCase()) ||
                        doctor.getClinicId().toLowerCase().contains(query.toLowerCase())) {
                    filteredDoctorList.add(doctor);
                }
            }
        }

        notifyDataSetChanged();
    }
    public void updateData(List<DoctorNearby> newData) {
        originalDoctorList.clear();
        originalDoctorList.addAll(newData);
        filter(""); // Apply any existing filter
    }
}
