package com.example.navigation_drawer.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;

import com.example.navigation_drawer.Apis.ApiClient;
import com.example.navigation_drawer.Apis.ApiService;
import com.example.navigation_drawer.DataModel.PatientModel;
import com.example.navigation_drawer.DataModel.ChiefCompModel;
import com.example.navigation_drawer.ResponseData.ChiefCompResponse;
import com.example.navigation_drawer.ResponseData.PatientResponse;
import com.example.navigation_drawer.utils.SessionManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientChiefComp {
    SessionManager sessionManager;
    Context context;
    String token;
    public PatientChiefComp(Context context) {
        sessionManager = new SessionManager(context);
    }

    public void fetchClinicsData() {

        token=  sessionManager.getAuthToken();

        ApiService apiService = ApiClient.getClient(token).create(ApiService.class);
        Call<PatientResponse> call = apiService.getPatients(token);
        call.enqueue(new Callback<PatientResponse>() {
            @Override
            public void onResponse(Call<PatientResponse> call, Response<PatientResponse> response) {
                if (response.isSuccessful()) {
                    PatientResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.isSuccess()) {
                        List<PatientModel> teams = apiResponse.getPatients();

                        JSONObject teamResponseJson = null;
                        if (teams != null) {

                            try {
                                // Log JSON response data
                                Gson gson = new Gson();
                                String jsonResponse = gson.toJson(apiResponse);
                                teamResponseJson = new JSONObject(jsonResponse);
                                Log.d(TAG, "JSON Response: " + jsonResponse);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (teamResponseJson != null) {
                                try {
                                    boolean success = teamResponseJson.getBoolean("success");
                                    JSONArray teamsArray = teamResponseJson.getJSONArray("patients");
                                    Log.d("Response JSON", "Response: " + new Gson().toJson(teamResponseJson));
                                    // Process teams data
                                    for (int i = 0; i < teamsArray.length(); i++) {
                                        JSONObject teamObject = teamsArray.getJSONObject(i);
                                        int  id = teamObject.getInt("id");
                                        String clinic_name = teamObject.getString("patient_id");
                                        String latitude = teamObject.getString("patient_name");
                                        String created_at = teamObject.getString("created_at");
                                        String updated_at = teamObject.getString("updated_at");
                                        // String imgurl = teamObject.getString("photo_url");

                                        PatientModel teamModel = new PatientModel(id,  clinic_name,  latitude, created_at,updated_at);
                                        sessionManager.setPatientsData(teams);
                                        Log.d(TAG, "onResponse: " +latitude);
                                        Log.d(TAG, "PatientModel Data: " + teamModel.toString());
                                    }} catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<PatientResponse> call, Throwable t) {
                Log.e("NearestClinics", "Failed to fetch clinics: " + t.getMessage());
            }
        });
    }

    public void fetchClinicsDat() {

        token=  sessionManager.getAuthToken();

        ApiService apiService = ApiClient.getClient(token).create(ApiService.class);
        Call<ChiefCompResponse> call = apiService.getChiefComplaints(token);
        call.enqueue(new Callback<ChiefCompResponse>() {
            @Override
            public void onResponse(Call<ChiefCompResponse> call, Response<ChiefCompResponse> response) {
                if (response.isSuccessful()) {
                    ChiefCompResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.isSuccess()) {
                        List<ChiefCompModel> chiefCompResponse = apiResponse.getChief_complaints();

                        JSONObject teamResponseJson = null;
                        if (chiefCompResponse != null) {

                            try {
                                // Log JSON response data
                                Gson gson = new Gson();
                                String jsonResponse = gson.toJson(apiResponse);
                                teamResponseJson = new JSONObject(jsonResponse);
                                Log.d(TAG, "JSON Response: " + jsonResponse);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (teamResponseJson != null) {
                                try {
                                    boolean success = teamResponseJson.getBoolean("success");
                                    JSONArray teamsArray = teamResponseJson.getJSONArray("chief_complaints");
                                    Log.d("Response JSON", "Response: " + new Gson().toJson(teamResponseJson));
                                    // Process teams data
                                    for (int i = 0; i < teamsArray.length(); i++) {
                                        JSONObject teamObject = teamsArray.getJSONObject(i);
                                        int  id = teamObject.getInt("id");
                                        String clinic_name = teamObject.getString("chief_id");
                                        String latitude = teamObject.getString("chief_complaint_name");
                                        String created_at = teamObject.getString("created_at");
                                        String updated_at = teamObject.getString("updated_at");
                                        // String imgurl = teamObject.getString("photo_url");

                                        ChiefCompModel chief_complaint  = new ChiefCompModel(id,  clinic_name,  latitude, created_at,updated_at);

                                        Log.d(TAG, "onResp  " +latitude);
                                        Log.d(TAG, "Patien  Data: " + chief_complaint.getChiefComplaintId());
                                    }} catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ChiefCompResponse> call, Throwable t) {
                Log.e("NearestClinics", "Failed to fetch clinics: " + t.getMessage());
            }
        });
    }

}
