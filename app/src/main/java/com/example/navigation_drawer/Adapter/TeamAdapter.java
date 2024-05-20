package com.example.navigation_drawer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.navigation_drawer.DataModel.TeamModel;
import com.example.navigation_drawer.Dr_about;
import com.example.navigation_drawer.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class TeamAdapter extends ArrayAdapter<TeamModel> {
    private Context mContext;
    public TeamAdapter(@NonNull Context context, ArrayList<TeamModel> teamModelArrayList) {
        super(context, 0, teamModelArrayList);
mContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.teamcard_item, parent, false);
        }

        TeamModel teamMember = getItem(position);
        TextView nameTV = listItemView.findViewById(R.id.idTVCourse);
        TextView positionTV = listItemView.findViewById(R.id.idTVCourses);
        ImageView photoIV = listItemView.findViewById(R.id.idIVcourse);
        AppCompatButton bookNowBtn = listItemView.findViewById(R.id.Booknow);

        if (teamMember != null) {
            nameTV.setText(teamMember.getName());
            positionTV.setText(teamMember.getHighestQualification());

            // Load image using Picasso
            Picasso.get()
                    .load(teamMember.getPhotoUrl())
                    .into(photoIV);

            // Set OnClickListener for the button
            bookNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, Dr_about.class);
                    intent.putExtra("name", teamMember.getAboutDoctor());
                    mContext.startActivity(intent);
                }
            });
        }

        return listItemView;
    }
}
