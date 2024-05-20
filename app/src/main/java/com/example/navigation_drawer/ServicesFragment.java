package com.example.navigation_drawer;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.navigation_drawer.DataModel.ServiceModel;
import com.example.navigation_drawer.Apis.ApiClient;
import com.example.navigation_drawer.Apis.ApiService;
import com.example.navigation_drawer.ResponseData.ServicesResponse;
import com.example.navigation_drawer.utils.SessionManager;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ServicesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ProgressDialog progressDialog;

    SessionManager sessionTeam;
    private GridView coursesGV;
    String token;
    private final List<ServiceModel> teamModelList = new ArrayList<>();

    private ServicesAdepter adapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(String param1, String param2) {
        ServicesFragment fragment = new ServicesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ServicesFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        sessionTeam = new SessionManager(getContext());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);
        coursesGV = view.findViewById(R.id.idcourses);
        adapter = new ServicesAdepter(requireActivity(), new ArrayList<>());
        coursesGV.setAdapter(adapter);
        token = sessionTeam.getAuthToken();
        fetchTeamMembes();
        return view;
    }
    private void fetchTeamMembes() {
        progressDialog.show();
        // Create Retrofit instance
        token= sessionTeam.getAuthToken();
        Log.d("TAG", "fetchTeamMembers: "+token);
        String token2= sessionTeam.getAuthToken();
        ApiService apiService = ApiClient.getClient(token2).create(ApiService.class);
        // Create ApiService instance


        // Make the API call asynchronously
        Call<ServicesResponse> call = apiService.getDentalServices(token);
        call.enqueue(new Callback<ServicesResponse>() {
            @Override
            public void onResponse(Call<ServicesResponse> call, Response<ServicesResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ServicesResponse teamResponse = response.body();
                    if (teamResponse != null && teamResponse.isSuccess()) {
                        List<ServiceModel> teams = teamResponse.getServices();
                        JSONObject teamResponseJson = null;
                        if (teams != null) {

                            try {
                                // Log JSON response data
                                Gson gson = new Gson();
                                String jsonResponse = gson.toJson(teamResponse);
                                teamResponseJson = new JSONObject(jsonResponse);
                                Log.d(TAG, "JSON Response: " + jsonResponse);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (teamResponseJson != null) {
                                try {
                                    boolean success = teamResponseJson.getBoolean("success");
                                    JSONArray ServiceArray = teamResponseJson.getJSONArray("services");
                                    Log.d("Response JSON", "Response: " + new Gson().toJson(teamResponseJson));
                                    // Process teams data

                                        for (int i = 0; i < ServiceArray.length(); i++) {
                                            JSONObject serviceObject = ServiceArray.getJSONObject(i);

                                            int serviceId=serviceObject.getInt("id");
                                            String service_id = serviceObject.getString("service_id");

                                            String serviceName = serviceObject.getString("service_name");
                                            String serviceType = serviceObject.getString("service_type");

                                           String serviceImageUrl =  serviceObject.getString("service_image_url");

                                           String createdAt = serviceObject.getString("created_at");
                                           String updatedAt = serviceObject.getString("updated_at");
                                            ServiceModel serviceModel = new ServiceModel(serviceId,service_id, serviceName, serviceType, serviceImageUrl, createdAt, updatedAt);
                                            teamModelList.add(serviceModel);
                                           adapter.addAll(serviceModel);
                                           teamModelList.clear();
                                           adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }} else {
                                Log.e(TAG, "API response indicates failure.");
                            }
                        } else {
                            Log.e(TAG, "API call failed with response code: " + response.code());
                        }
                    }}}

            @Override
            public void onFailure(Call<ServicesResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, "API call failed:" + t.getMessage());
            }
        });
    }

}