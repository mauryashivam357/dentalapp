package com.example.navigation_drawer.ResponseData;

import com.example.navigation_drawer.DataModel.TeamModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("teams")
    private List<TeamModel> teams;

    public boolean isSuccess() {
        return success;
    }

    public List<TeamModel> getTeams() {
        return teams;
    }

}
