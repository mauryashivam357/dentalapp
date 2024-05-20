package com.example.navigation_drawer.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import com.example.navigation_drawer.DataModel.ServiceModel;
import com.example.navigation_drawer.R;
import com.squareup.picasso.Picasso;
import java.util.List;
public class ServiceAdepter extends RecyclerView.Adapter<ServiceAdepter.ViewHolder> {
    private List<ServiceModel> serviceModel;

    public ServiceAdepter(List<ServiceModel> serviceModel) {
        this.serviceModel= serviceModel;
    }

    public void setTeamModels(List<ServiceModel> serviceModel) {
        this.serviceModel = serviceModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceModel service = serviceModel.get(position);
        if (service.getServiceImageUrl() != null && !service.getServiceImageUrl().isEmpty()) {
            Picasso.get().load(service.getServiceImageUrl()).into(holder.imageView);
            Log.d("TAG", "onBindViewHolderd: "+service.getServiceImageUrl());
        } else {
            // If no image URL is available, you can set a placeholder or hide the ImageView
            holder.imageView.setVisibility(View.VISIBLE);
        }
        holder.bind(service);
    }

//     @Override
//    public int getItemCount() {
//        serviceModel.size();
//        return 0;
//   }

    @Override
    public int getItemCount() {
        if (serviceModel != null)
            return serviceModel.size();
        return 1;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewA;
        ImageView imageView;
        AppCompatButton button;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewA = itemView.findViewById(R.id.servicename);
            imageView=itemView.findViewById(R.id.teamimg);

        }

        public void bind(ServiceModel serviceModel) {
            textViewA.setText(serviceModel.getServiceName());

        }
    }
}
