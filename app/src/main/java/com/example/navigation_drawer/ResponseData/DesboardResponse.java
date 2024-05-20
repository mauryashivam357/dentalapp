package com.example.navigation_drawer.ResponseData;

import com.example.navigation_drawer.DataModel.DesboardModel;
import com.example.navigation_drawer.DataModel.ServiceModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DesboardResponse {

    private boolean success;
    @SerializedName("teams")
    private List<DesboardModel> teams;

    public boolean isSuccess() {
        return success;
    }

    public List<DesboardModel> getTeams() {
        return teams;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    @SerializedName("services")
    private List<ServiceModel> services;


}
