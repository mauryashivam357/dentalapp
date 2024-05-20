package com.example.navigation_drawer.ResponseData;

import com.example.navigation_drawer.DataModel.DoctorNearby;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClinicsResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("clinics")
    private List<DoctorNearby> clinics;

    public boolean isSuccess() {
        return success;
    }

    public List<DoctorNearby> getClinics() {
        return clinics;
    }
}
