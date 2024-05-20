package com.example.navigation_drawer.DataModel;

import com.google.gson.annotations.SerializedName;

public class ServiceModel {

        @SerializedName("id")
        private int id;
        @SerializedName("service_id")
        private String serviceId;
        @SerializedName("service_name")
        private String serviceName;
        @SerializedName("service_type")
        private String serviceType;
        @SerializedName("service_image_url")
        private String serviceImageUrl;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;

        public ServiceModel(int id, String serviceId, String serviceName, String serviceType, String serviceImageUrl, String createdAt, String updatedAt) {
            this.id = id;
            this.serviceId = serviceId;
            this.serviceName = serviceName;
            this.serviceType = serviceType;
            this.serviceImageUrl = serviceImageUrl;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getServiceImageUrl() {
            return serviceImageUrl;
        }

        public void setServiceImageUrl(String serviceImageUrl) {
            this.serviceImageUrl = serviceImageUrl;
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
    }
