package com.example.navigation_drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
public class Notification extends Fragment {
    private Switch enabledPushSwitch;
    private Switch smsAlertSwitch;
    private Switch emailAlertSwitch;
    public Notification() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("Notification"); // Replace "Profile" with your desired title
            }
        }
        // Initialize switches
        enabledPushSwitch = view.findViewById(R.id.enabled_push);
        smsAlertSwitch = view.findViewById(R.id.smsalert);
        emailAlertSwitch = view.findViewById(R.id.emailalert);

        // Set switch listeners
        enabledPushSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the state change
                showToast("Enabled Push: " + isChecked);
            }
        });

        smsAlertSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the state change
                showToast("SMS Alert: " + isChecked);
            }
        });

        emailAlertSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the state change
                showToast("Email Alert: " + isChecked);

            }
        });

      return view;
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
