package com.example.navigation_drawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button notification, add_patient, edit_profile, book_history, logout;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Setting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Setting.
     */
    // TODO: Rename and change types and number of parameters
    public static Setting newInstance(String param1, String param2) {
        Setting fragment = new Setting();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        notification = view.findViewById(R.id.setting_notification);
        edit_profile = view.findViewById(R.id.editprofile);
        add_patient = view.findViewById(R.id.add_patient);
        book_history = view.findViewById(R.id.booked_and_history);
        logout = view.findViewById(R.id.logout);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        Intent intent = new Intent(getActivity(), AddPatientActivity.class);
        add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Handle button click to replace FirstFragment with SecondFragment
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new Notification())
                        .addToBackStack(null)
                        .commit();

            }
        });
        Intent bookapinment = new Intent(getActivity(), BookAppointment.class);

        book_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Handle button click to replace FirstFragment with SecondFragment
                startActivity(bookapinment);
            }
        });
        Intent logout1 = new Intent(getActivity(), Logout.class);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Handle button click to replace FirstFragment with SecondFragment
                startActivity(logout1);
            }
        });

        return view;
    }
}