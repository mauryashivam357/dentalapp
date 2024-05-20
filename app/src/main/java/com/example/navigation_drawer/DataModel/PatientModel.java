package com.example.navigation_drawer.DataModel;

import com.google.gson.annotations.SerializedName;

public class PatientModel {
    @SerializedName("id")
    private int id;

    public PatientModel(int id, String patientId, String patientName, String createdAt, String updatedAt) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @SerializedName("patient_id")
    private String patientId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @SerializedName("patient_name")
    private String patientName;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;
}
