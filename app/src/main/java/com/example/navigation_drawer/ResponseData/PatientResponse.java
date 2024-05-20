package com.example.navigation_drawer.ResponseData;

import com.example.navigation_drawer.DataModel.PatientModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatientResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("patients")
    private List<PatientModel> patients;

    public boolean isSuccess() {
        return success;
    }

    public List<PatientModel> getPatients() {
        return patients;
    }
}
