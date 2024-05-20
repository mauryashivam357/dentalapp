package com.example.navigation_drawer.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation_drawer.DataModel.HospitalsModel;
import com.example.navigation_drawer.DataModel.ServiceModel;
import com.example.navigation_drawer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HospitalsAdepter extends RecyclerView.Adapter<HospitalsAdepter.ViewHolder> {
    private List<HospitalsModel> serviceModel;

    public  HospitalsAdepter(List<HospitalsModel> serviceModel) {
        this.serviceModel= serviceModel;
    }

    public void setTeamModels(List<HospitalsModel> serviceModel) {
        this.serviceModel = serviceModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HospitalsAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospitals_layout, parent, false);
        return new HospitalsAdepter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull HospitalsAdepter.ViewHolder holder, int position) {
        HospitalsModel service = serviceModel.get(position);
        if (service.getServiceImageUrl() != null && !service.getServiceImageUrl().isEmpty()) {
            Picasso.get().load(service.getServiceImageUrl()).into(holder.imageView);
            Log.d("TAG", "onBindViewHolderd: "+service.getServiceImageUrl());
        } else {
            // If no image URL is available, you can set a placeholder or hide the ImageView
            holder.imageView.setVisibility(View.VISIBLE);
        }
        holder.bind(service);
    }

//    @Override
//    public int getItemCount() {
//        serviceModel.size();
//        return 0;
//    }

    @Override
    public int getItemCount() {
        if (serviceModel != null)
            return serviceModel.size();
        return 1;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewA;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewA = itemView.findViewById(R.id.title11);
            imageView=itemView.findViewById(R.id.imageVieww);

        }

        public void bind(HospitalsModel serviceModel) {
            textViewA.setText(serviceModel.getServiceName());
            // System.out.print(teamModel.getServiceName());
            ;// Set the name of the team member to the TextView
        }
    }
}
