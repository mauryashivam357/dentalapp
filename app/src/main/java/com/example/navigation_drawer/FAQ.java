package com.example.navigation_drawer;



import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FAQ#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQ extends Fragment {
     private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button general,dental_implant,all_dental,single_tooth,dental_crown,dental_bridge,other;
    public FAQ() {
        //Required public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FAQ.
     */
    // TODO: Rename and change types and number of parameters
    public static FAQ newInstance(String param1, String param2) {
        FAQ fragment = new FAQ();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_a_q, container, false);
        general=view.findViewById(R.id.general);
       all_dental=view.findViewById(R.id.all_dental);
        single_tooth=view.findViewById(R.id.single_tooth);
       dental_crown=view.findViewById(R.id.dental_crown);
        dental_bridge=view.findViewById(R.id.dental_bridge);
        other=view.findViewById(R.id.other);
       dental_implant=view.findViewById(R.id.dental_implant);


        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Handle button click to replace FirstFragment with SecondFragment
                Intent general = new Intent(getActivity(), GeneralActivity.class);
                startActivity(general);
            }
        });
        dental_implant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dental_implant = new Intent(getActivity(), DentalImplant.class);
                startActivity(dental_implant);
            }
        });
        all_dental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent all_dental = new Intent(getActivity(), AllDentalImplant.class);
                startActivity(all_dental);
            }
        });
        single_tooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent single_tooth = new Intent(getActivity(), SingleToothimplant.class);
                startActivity(single_tooth);
            }
        });
       dental_crown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dental_crown = new Intent(getActivity(), DentalCrown.class);
                startActivity(dental_crown);
            }
        });
        dental_bridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dental_bridge = new Intent(getActivity(), DentalBridge.class);
                startActivity(dental_bridge);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent other= new Intent(getActivity(), OtherActivity.class);
                startActivity(other);
            }
        });
        return view;
    }
}