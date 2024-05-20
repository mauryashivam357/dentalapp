package com.example.navigation_drawer.DataModel;

import com.google.gson.annotations.SerializedName;

public class DoctorNearby {

    public DoctorNearby(int id, String clinicId, String clinicName, String latitude, String longitude, String createdAt, String updatedAt) {
        this.id = id;
        this.clinicId = clinicId;
        this.clinicName = clinicName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @SerializedName("id")
        private int id;

        @SerializedName("clinic_id")
        private String clinicId;

        @SerializedName("clinic_name")
        private String clinicName;

        @SerializedName("latitude")
        private String latitude;

        @SerializedName("longitude")
        private String longitude;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("updated_at")
        private String updatedAt;

        public int getId() {
            return id;
        }

        public String getClinicId() {
            return clinicId;
        }

        public String getClinicName() {
            return clinicName;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

}
