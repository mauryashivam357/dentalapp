package com.example.navigation_drawer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation_drawer.DataModel.DesboardModel;
import com.example.navigation_drawer.Dr_about;
import com.example.navigation_drawer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DesboardAdepter extends RecyclerView.Adapter<DesboardAdepter.ViewHolder> {
    private List<DesboardModel> desboardModel;
    Context context;

    public DesboardAdepter(Context context,List<DesboardModel> desboardModel) {
        this.desboardModel = desboardModel;
        this.context = context;
    }

    public void setTeamModels(List<DesboardModel> desboardModel) {
        this.desboardModel = desboardModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teamlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DesboardModel desboardmodel = desboardModel.get(position);
        if (desboardmodel != null && !desboardmodel.getPhoto_url().isEmpty()) {
            Picasso.get().load(desboardmodel.getPhoto_url()).into(holder.imageView);
        } else {
            // If no image URL is available, you can set a placeholder or hide the ImageView
            holder.imageView.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start new activity when item clicked
                Intent intent = new Intent(context, Dr_about.class);
                // Pass data if needed
                intent.putExtra("name",desboardmodel.getAbout_doctor());
                context.startActivity(intent);
            }
        });
        holder.bind(desboardmodel);
    }

    @Override
    public int getItemCount() {
        return desboardModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewA,textViewB;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewA = itemView.findViewById(R.id.teamname);
            textViewB = itemView.findViewById(R.id.position);
            imageView=itemView.findViewById(R.id.imageid);

        }

        public void bind(DesboardModel teamModel) {
            textViewA.setText(teamModel.getName());
            textViewB.setText(teamModel.getPosition());// Set the name of the team member to the TextView
        }
    }
}
