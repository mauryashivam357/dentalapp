package com.example.navigation_drawer;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.navigation_drawer.Adapter.DesboardAdepter;
import com.example.navigation_drawer.Adapter.HospitalsAdepter;
import com.example.navigation_drawer.DataModel.DesboardModel;
import com.example.navigation_drawer.Adapter.ServiceAdepter;
import com.example.navigation_drawer.DataModel.HospitalsModel;
import com.example.navigation_drawer.DataModel.ServiceModel;

import com.example.navigation_drawer.Apis.ApiClient;
import com.example.navigation_drawer.Apis.ApiService;
import com.example.navigation_drawer.ResponseData.DesboardResponse;
import com.example.navigation_drawer.utils.SessionManager;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesboardFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ViewPager viewPager;

    DesboardAdepter adapter;
    ProgressDialog progressDialog;
    ServiceAdepter serviceAdepter;
    //  private List<ServiceModel> services = new ArrayList<>();
    List<DesboardModel> doctorsList = new ArrayList<>();
    List<ServiceModel> servicesList = new ArrayList<>();
    List<HospitalsModel> hospitalList = new ArrayList<>();

    LinearLayout sliderDotspanel;
    SliderAdapter mViewPagerAdapter;
    HospitalsAdepter hospitalsAdepter;
    SessionManager session;
    RecyclerView recyclerView, secyclerView,hospital_view_list;
    Button Booknow,view_services,team_view;
    String token_;
    //  List<DesboardModel> teamModelList = new ArrayList<>();
    private List<DesboardModel> doctors = new ArrayList<>();
    private int dotscount;
    private ImageView[] dots;
    private String mParam1;
    private String mParam2;

    public DesboardFragment() {
        // Required empty public constructor
    }

    public static DesboardFragment newInstance(String param1, String param2) {
        DesboardFragment fragment = new DesboardFragment();
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
        View view = inflater.inflate(R.layout.fragment_desboard, container, false);
        session = new SessionManager(getContext());
        token_ = session.getAuthToken();
        progressDialog = new ProgressDialog(getContext());
        progressDialog .setContentView(R.layout.activity_progress_bar);

        progressDialog.setIndeterminate(true);
        hospital_view_list=view.findViewById(R.id.list_hospital);
        recyclerView = view.findViewById(R.id.listteamview);
        secyclerView = view.findViewById(R.id.list_services);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        secyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
         hospital_view_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
         hospitalsAdepter=new HospitalsAdepter(hospitalList);
        serviceAdepter = new ServiceAdepter(servicesList);
        adapter = new DesboardAdepter(getContext(),doctorsList);
        recyclerView.setAdapter(adapter);
        hospital_view_list.setAdapter(hospitalsAdepter);
        secyclerView.setAdapter(serviceAdepter);

        // Fetch team members data and update the list

       hospitalList.add(new HospitalsModel(1, "service_id_1", "Jaypee Hospital", "Type 1", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT4ae0NNmYYhCF6lJVjuGaLw8S2Xgthz6jO-7wseQhBl5cBCJKf", "2024-05-01", "2024-05-01"));
        hospitalList.add(new HospitalsModel(4, "service_id_4", "Gum Disease", "Type 1", "https://lbdental.net/wp-content/uploads/2014/07/crowns-bridges-280x280.jpg", "2024-05-01", "2024-05-01"));
        hospitalList.add(new HospitalsModel(5, "service_id_5", "Crown & Bridge", "Type 2", "https://www.riograndeoralsurgery.com/wp-content/uploads/dental-implants-vs-root-canal-what-you-should-know.jpg", "2024-05-01", "2024-05-01"));
        fetchTeamMembers();
//        servicesList.add(new ServiceModel(1, "service_id_1", "Dental Implant", "Type 1", "https://www.riograndeoralsurgery.com/wp-content/uploads/dental-implants-vs-root-canal-what-you-should-know.jpg", "2024-05-01", "2024-05-01"));
//        servicesList.add(new ServiceModel(4, "service_id_4", "Gum Disease", "Type 1", "https://lbdental.net/wp-content/uploads/2014/07/crowns-bridges-280x280.jpg", "2024-05-01", "2024-05-01"));
//        servicesList.add(new ServiceModel(5, "service_id_5", "Crown & Bridge", "Type 2", "https://www.riograndeoralsurgery.com/wp-content/uploads/dental-implants-vs-root-canal-what-you-should-know.jpg", "2024-05-01", "2024-05-01"));
//
//        // Static data for doctors
//        doctorsList.add(new DesboardModel("Dr. Deepti Goel", "Position 1", "Qualification 1", 5, "About Doctor 1", "https://www.flossdental.net/team_imgs/dr-deepti-goel.jpg", "Clinic 1", 1));
//        doctorsList.add(new DesboardModel("Dr. (Prof.).Prashant Chetal", "Position 2", "Qualification 2", 7, "About Doctor 2", "https://www.flossdental.net/team_imgs/dr-to-prof-to-prashant-chetal.jpg", "Clinic 2", 2));
//        doctorsList.add(new DesboardModel("Dr. Anika Arora", "Position 3", "Qualification 3", 10, "About Doctor 3", "https://www.flossdental.net/team_imgs/dr-anika.jpg", "Clinic 3", 3));

        serviceAdepter.notifyDataSetChanged();
        hospitalsAdepter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();

        // fetchServices();
//        Log.d("TAG", "onCreate: "+user_token);
//        Toast.makeText(getActivity(), user_token, Toast.LENGTH_SHORT).show();


          Booknow = view.findViewById(R.id.Booknow);
          view_services=view.findViewById(R.id.view_services);
          team_view = view.findViewById(R.id.team_all );
          team_view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  FragmentTransaction transaction = getFragmentManager().beginTransaction();
                  transaction.replace(R.id.fragment_container, new Team());
                  transaction.addToBackStack(null);  // Add the transaction to the back stack
                  transaction.commit();
              }
          });
        view_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new ServicesFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
            }
        });

        Intent intent = new Intent(getActivity(), LoginAcount.class);
        Booknow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (token_ != "") {
                    Intent intent = new Intent(getActivity(), NearestClinics.class);
                    startActivity(intent);
                } else {

                    startActivity(intent);

                }
            }
        });


        viewPager = view.findViewById(R.id.viewPagerMain);
        sliderDotspanel = view.findViewById(R.id.SliderDots);
        mViewPagerAdapter = new SliderAdapter(getContext(),getImageUrls());
        viewPager.setAdapter(mViewPagerAdapter);

        dotscount = mViewPagerAdapter.getCount();
        dots = new ImageView[dotscount];


        dotscount = mViewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(requireContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Additional logic if needed when the page is scrolled
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    // Use getContext() instead of requireContext()
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                }
                // Use getContext() instead of requireContext()
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Additional logic if needed when the page scroll state changes
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(this), 2000, 4000);


        return view;
    }

    private void navigateToFeedbackActivity() {


     }


    private void fetchTeamMembers() {
        progressDialog.show();
        Log.d("TAG", "fetchTeamMembers: " + token_);
        String token2 = session.getAuthToken();
        ApiService apiService = ApiClient.getClient(token2).create(ApiService.class);
        Log.d("TAG", "token2 : " + token_);
        Call<DesboardResponse> call = apiService.getDashboard(token_);
        call.enqueue(new Callback<DesboardResponse>() {
            @Override
            public void onResponse(Call<DesboardResponse> call, Response<DesboardResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    DesboardResponse apiRespons = response.body();
                    if (apiRespons != null && apiRespons.isSuccess()) {
                        List<DesboardModel> teams = apiRespons.getTeams();
                        List<ServiceModel> services = apiRespons.getServices();
                        JSONObject teamResponseJson = null;
                        if (teams != null && services != null) {

                            try {
                                // Log JSON response data
                                Gson gson = new Gson();
                                String jsonResponse = gson.toJson(apiRespons);
                                teamResponseJson = new JSONObject(jsonResponse);
                                Log.d(TAG, "JSON Response: " + jsonResponse);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (teamResponseJson != null) {
                                try {
                                    boolean success = teamResponseJson.getBoolean("success");
                                    JSONArray teamsArray = teamResponseJson.getJSONArray("teams");
                                    JSONArray ServiceArray = teamResponseJson.getJSONArray("services");
                                    Log.d("Response JSON", "Response: " + new Gson().toJson(teamResponseJson));
                                    // Process teams data

                                    for (int i = 0; i < ServiceArray.length(); i++) {
                                        JSONObject serviceObject = ServiceArray.getJSONObject(i);

                                         int serviceId=serviceObject.getInt("id");
                                        String service_id = serviceObject.getString("service_id");

                                        String serviceName = serviceObject.getString("service_name");

                                        String serviceType = serviceObject.getString("service_type");

                                        String serviceImageUrl = "";
                                        if (serviceObject.has("service_image_url")) {
                                            serviceImageUrl = serviceObject.getString("service_image_url");
                                        } else {
                                            // Handle the case where "service_image_url" is missing
                                            // You can set a default image URL or skip processing this item
                                            serviceImageUrl = "service_image_url"; // Set your default image URL here
//
                                        }

//                                        String serviceImageUrl = serviceObject.getString("service_image_url");
//                                        if (serviceImageUrl == null || serviceImageUrl.isEmpty()) {
//
//                                            serviceImageUrl = "https://www.amayadental.in/wp-content/uploads/2023/07/Banner-Image-4.jpg"; // Set your default image URL here
//                                        }
                                        String createdAt = serviceObject.getString("created_at");

                                        String updatedAt = serviceObject.getString("updated_at");

                                        ServiceModel serviceModel = new ServiceModel(serviceId,service_id, serviceName, serviceType, serviceImageUrl, createdAt, updatedAt);
                                        servicesList.add(serviceModel);
                                        serviceAdepter.setTeamModels(services);
                                        Log.d(TAG, "onResponse: "+serviceModel.getServiceName());
                                        servicesList.clear();
                                        serviceAdepter.notifyDataSetChanged();

                                    }
                                    for (int i = 0; i < teamsArray.length(); i++) {
                                        JSONObject teamObject = teamsArray.getJSONObject(i);
                                        int teamId=teamObject.getInt("clinic_id");
                                        String name = teamObject.getString("name");
                                        String position = teamObject.getString("position");
                                        String qualification = teamObject.getString("highest_qualification");
                                        int experience = teamObject.getInt("years_of_experience");
                                        String about_doctor = teamObject.getString("about_doctor");
                                        String photo = teamObject.getString("photo_url");
                                        String associated_clinic = teamObject.getString("associated_clinic_name");
                                        // String imgurl = teamObject.getString("photo_url");
                                        Log.d(TAG, "onResponse: " + photo);
                                        DesboardModel teamModel = new DesboardModel(name, position, qualification, experience, about_doctor, photo, associated_clinic,teamId);
                                        doctors.add(teamModel);
                                        adapter.setTeamModels(doctors);
                                        doctorsList.clear();
                                        adapter.notifyDataSetChanged();
                                    }

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }


                            }

                        }
                    }
                } else {
                    Log.e("TAG", "Failed to fetch team members:2 " + response.message());
                }
            }

            @Override
            public void onFailure(Call<DesboardResponse> call, Throwable t) {
                progressDialog.dismiss();
                // Handle failure
                Log.e("TAG", "Failed to fetch team members:2 " + t.getMessage());
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    public class MyTimerTask extends TimerTask {
        private WeakReference<DesboardFragment> fragmentRef;

        public MyTimerTask(DesboardFragment fragment) {
            fragmentRef = new WeakReference<>(fragment);
        }

        @Override
        public void run() {
            DesboardFragment fragment = fragmentRef.get();
            if (fragment != null && fragment.isAdded()) {
                fragment.requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ViewPager viewPager = fragment.viewPager;
                        if (viewPager.getCurrentItem() == 0) {
                            viewPager.setCurrentItem(1);
                        } else if (viewPager.getCurrentItem() == 1) {
                            viewPager.setCurrentItem(2);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }
    }
    private String[] getImageUrls() {
        return new String[]{ "https://t3.ftcdn.net/jpg/00/91/95/80/240_F_91958064_ungBuwmGQ70kPptl9mNEN27BuADArJob.jpg",
                  "https://t4.ftcdn.net/jpg/06/34/14/43/240_F_634144399_aehy3Qno61q5434V95IVMJNYXeaS1ZgI.jpg",
                "https://t4.ftcdn.net/jpg/07/17/05/61/240_F_717056109_hjL8k41jGCJwGfv4FQmWuVzyRrCJqYZ0.jpg","https://t4.ftcdn.net/jpg/06/07/61/51/240_F_607615153_4ldeHzGqQy1cNWSgqdzLvSwxk28gR35N.jpg"
                ,"https://t3.ftcdn.net/jpg/01/94/94/80/240_F_194948096_sHLYX16uENHlpbsAjjTHC1vFzS8kUhXD.jpg","https://t3.ftcdn.net/jpg/04/48/18/00/240_F_448180073_v0OYDe1NDyLXXRRzfhjiAr6H8T8JICrM.jpg"
        };}

    }
