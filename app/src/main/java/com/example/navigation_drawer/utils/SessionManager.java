package com.example.navigation_drawer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.navigation_drawer.DataModel.AppointmentData;
import com.example.navigation_drawer.DataModel.ChiefCompModel;
import com.example.navigation_drawer.DataModel.DoctorNearby;
import com.example.navigation_drawer.DataModel.PatientModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private static final String PREF_NAME = "UserData";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_AUTH_TOKEN = "authToken";
    private static final String KEY_APPOINTMENT_DATA = "appointment_data";
    private static final String KEY_NAVIGATED_BACK = "navigated_back";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setNavigatedBack(boolean navigatedBack) {
        editor.putBoolean(KEY_NAVIGATED_BACK, navigatedBack);
        editor.apply();
    }

    public void setPatientsData(List<PatientModel> clinicsData) {
        Gson gson = new Gson();
        String clinicsJson = gson.toJson(clinicsData);
        editor.putString("patients", clinicsJson);
        editor.apply();
    }

    public List<PatientModel> getpatientsData() {
        String patientsJson = sharedPreferences.getString("patients", "");
        if (!patientsJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<PatientModel>>() {}.getType();
            return gson.fromJson(patientsJson, type);
        }
        return new ArrayList<>();
    }

    public void saveChiefComplaint(List<ChiefCompModel> chief_complaint) {
        Gson gson = new Gson();
        String chiefJson = gson.toJson(chief_complaint);
        editor.putString("chief_complaints", chiefJson);
        editor.apply();
    }

    public List<ChiefCompModel> getChiefComplaint() {
        String chiefJson = sharedPreferences.getString("chief_complaints", "");
        if (!chiefJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<ChiefCompModel>>() {}.getType();
            return gson.fromJson(chiefJson, type);
        }
        return new ArrayList<>();
    }

    public void saveClinics(List<DoctorNearby> clinics) {
        Gson gson = new Gson();
        String clinicsJson = gson.toJson(clinics);
        editor.putString("clinics", clinicsJson);
        editor.apply();
    }

    public List<DoctorNearby> getClinics() {
        String clinicsJson = sharedPreferences.getString("clinics", "");
        if (!clinicsJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<DoctorNearby>>() {}.getType();
            return gson.fromJson(clinicsJson, type);
        }
        return new ArrayList<>();
    }

    public void saveAppointmentData(AppointmentData appointmentData) {
        Gson gson = new Gson();
        String json = gson.toJson(appointmentData);
        editor.putString(KEY_APPOINTMENT_DATA, json);
        editor.apply();
    }

    public AppointmentData getAppointmentData() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_APPOINTMENT_DATA, "");
        return gson.fromJson(json, AppointmentData.class);
    }

    public void saveUserData(int userId, String username, String email, String mobile, String authToken) {
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_AUTH_TOKEN, authToken);
        editor.apply();
    }

    public int getUserId() {
        return sharedPreferences.getInt(KEY_USER_ID, -1); // Default value if not found
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public String getMobile() {
        return sharedPreferences.getString(KEY_MOBILE, "");
    }

    public String getAuthToken() {
        return sharedPreferences.getString(KEY_AUTH_TOKEN, "");
    }

    public boolean hasNavigatedBack() {
        return sharedPreferences.getBoolean(KEY_NAVIGATED_BACK, false);
    }

    public void clearUserData() {
        editor.clear();
        editor.apply();
    }
}
