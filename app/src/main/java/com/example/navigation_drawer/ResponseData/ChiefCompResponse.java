package com.example.navigation_drawer.ResponseData;

import com.example.navigation_drawer.DataModel.ChiefCompModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChiefCompResponse {
    @SerializedName("success")

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public List<ChiefCompModel> getChief_complaints() {
        return chief_complaints;
    }
    @SerializedName("chief_complaints")

    private List<ChiefCompModel> chief_complaints;
}
