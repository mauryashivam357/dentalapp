package com.example.navigation_drawer.ResponseData;

import com.example.navigation_drawer.DataModel.ServiceModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServicesResponse {
    public boolean isSuccess() {
        return success;
    }



    public List<ServiceModel> getServices() {
        return services;
    }
    private boolean success;
    @SerializedName("services")
    private List<ServiceModel> services;
}
