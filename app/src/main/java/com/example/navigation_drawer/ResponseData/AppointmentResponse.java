package com.example.navigation_drawer.ResponseData;

import com.example.navigation_drawer.DataModel.AppointmentData;
import com.google.gson.annotations.SerializedName;

public class AppointmentResponse {
    @SerializedName("success")
    private String success;
    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private AppointmentData data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppointmentData getData() {
        return data;
    }

    public void setData(AppointmentData data) {
        this.data = data;
    }
}
