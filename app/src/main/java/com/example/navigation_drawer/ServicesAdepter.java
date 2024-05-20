package com.example.navigation_drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.navigation_drawer.DataModel.ServiceModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ServicesAdepter  extends ArrayAdapter<ServiceModel> {

        public ServicesAdepter(@NonNull Context context, ArrayList<ServiceModel> teamModelArrayList) {
            super(context, 0, teamModelArrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.servicescard, parent, false);
            }

            ServiceModel teamMember = getItem(position);
            TextView nameTV = listItemView.findViewById(R.id.idTVCourse);
            TextView positionTV = listItemView.findViewById(R.id.idTVCourses);
            ImageView photoIV = listItemView.findViewById(R.id.idIVcourse);
            Button bookNowBtn = listItemView.findViewById(R.id.Booknow);

            if (teamMember != null) {

                nameTV.setText(teamMember.getServiceName());
                positionTV.setText(teamMember.getServiceType());

                nameTV.setText(teamMember.getServiceName());
                positionTV.setText(teamMember.getServiceType());
                // Load image using Picasso if the URL is not null or empty
                if (teamMember != null && teamMember.getServiceImageUrl() != null && !teamMember.getServiceImageUrl().isEmpty()) {
                  //  photoIV.setImageResource(R.drawable.active_dot);
                    Picasso.get()
                            .load(teamMember.getServiceImageUrl())
                            .into(photoIV);
                } else {
                    // Handle the case where the URL is null or empty
                    // For example, you can set a default image or hide the ImageView
                    // Setting a default image:
                    photoIV.setImageResource(R.drawable.active_dot);
                    // Hiding the ImageView:
                    // photoIV.setVisibility(View.GONE);
                }

// Load image using Picasso


                // Set OnClickListener for the button
                bookNowBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Perform an action when the button is clicked
                        // Example: open a detail page for the team member
                    }
                });
            }

            return listItemView;
        }



}
