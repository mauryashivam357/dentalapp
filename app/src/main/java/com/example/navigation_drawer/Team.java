package com.example.navigation_drawer;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigation_drawer.Adapter.TeamAdapter;
import com.example.navigation_drawer.DataModel.TeamModel;
import com.example.navigation_drawer.Apis.ApiClient;
import com.example.navigation_drawer.ResponseData.ApiResponse;
import com.example.navigation_drawer.Apis.ApiService;
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

public class Team extends Fragment {
    ProgressDialog progressDialog;

    SessionManager sessionTeam;
    private GridView coursesGV;
    private ApiService teamApiService;
    String token;
    private List<TeamModel> teamModelList = new ArrayList<>();

    private TeamAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        sessionTeam = new SessionManager(getContext());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);
        coursesGV = view.findViewById(R.id.idGVcourses);
        adapter = new TeamAdapter(requireActivity(), new ArrayList<>());
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
        // Create ApiService instance-----------------------


        // Make the API call asynchronously-----------------
        Call<ApiResponse> call = apiService.getTeams(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ApiResponse teamResponse = response.body();
                    if (teamResponse != null && teamResponse.isSuccess()) {
                        List<TeamModel> teams = teamResponse.getTeams();
                        JSONObject teamResponseJson = null;
                        if (teams != null) {

                            try {
                                // Log JSON response data--------------------------
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
                                JSONArray teamsArray = teamResponseJson.getJSONArray("teams");
                                Log.d("Response JSON", "Response: " + new Gson().toJson(teamResponseJson));
                                // Process teams data -------------------------------
                                for (int i = 0; i < teamsArray.length(); i++) {
                                    JSONObject teamObject = teamsArray.getJSONObject(i);
                                    String name = teamObject.getString("name");
                                    String position = teamObject.getString("position");
                                    String qualification = teamObject.getString("highest_qualification");
                                    int experience = teamObject.getInt("years_of_experience");
                                    String about_doctor = teamObject.getString("about_doctor");
                                    String photo =  teamObject.getString("photo_url");
                                    String associated_clinic = teamObject.getString("associated_clinic_name");
                                   // String imgurl = teamObject.getString("photo_url");--------------------------------
                                    Log.d(TAG, "onResponse: "+name);
                                    TeamModel teamModel = new TeamModel(name,position,qualification,experience,about_doctor,photo,associated_clinic,2);
                                    teamModelList.add(teamModel);
                                    adapter.addAll(teamModel);
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
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, "API call failed: " + t.getMessage());
            }
        });
    }

}