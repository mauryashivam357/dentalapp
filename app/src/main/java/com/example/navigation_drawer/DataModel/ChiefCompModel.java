package com.example.navigation_drawer.DataModel;

import com.google.gson.annotations.SerializedName;

public class ChiefCompModel {
    @SerializedName("id")
    private int chiefId; // Renamed from 'id' to 'chiefId'
    @SerializedName("chief_id")
    private String chiefComplaintId;
    @SerializedName("chief_complaint_name")
    private String chiefComplaintName;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    // Constructor
    public ChiefCompModel(int chiefId, String chiefComplaintId, String chiefComplaintName, String createdAt, String updatedAt) {
        this.chiefId = chiefId;
        this.chiefComplaintId = chiefComplaintId;
        this.chiefComplaintName = chiefComplaintName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters
    public int getChiefId() {
        return chiefId;
    }

    public String getChiefComplaintId() {
        return chiefComplaintId;
    }

    public String getChiefComplaintName() {
        return chiefComplaintName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
