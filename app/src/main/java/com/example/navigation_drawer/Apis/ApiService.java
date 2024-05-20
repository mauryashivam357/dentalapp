package com.example.navigation_drawer.Apis;

import com.example.navigation_drawer.DataModel.AppointmentData;
import com.example.navigation_drawer.ResponseData.AppointmentResponse;
import com.example.navigation_drawer.ResponseData.ChiefCompResponse;
import com.example.navigation_drawer.ResponseData.PatientResponse;
import com.example.navigation_drawer.ResponseData.ApiResponse;
import com.example.navigation_drawer.ResponseData.BookAppointmentResponse;
import com.example.navigation_drawer.ResponseData.ClinicsResponse;
import com.example.navigation_drawer.ResponseData.DesboardResponse;
import com.example.navigation_drawer.ResponseData.ServicesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("teams")
    Call<ApiResponse> getTeams(@Header("Authorization") String token);

    @POST("dashboard")
    Call<DesboardResponse> getDashboard(@Header("Authorization") String token);

    @GET("clinics")
    Call<ClinicsResponse> getClinics(@Header("Authorization") String token );

    @GET("chief-complaints")
    Call<ChiefCompResponse> getChiefComplaints(@Header("Authorization") String token);

    @GET("patients")
    Call<PatientResponse> getPatients(@Header("Authorization") String token);
//
   @POST("book-appointment")
   Call<AppointmentResponse> bookAppointment(@Header("Authorization") String token,@Body AppointmentData data);
   //    @POST("/api/teams")
//    Call<ApiResponse> getTeams();
//
   @GET("dental-service/dentalservices")
   Call<ServicesResponse> getDentalServices(@Header("Authorization") String token);
    @GET("appointments")
    Call<BookAppointmentResponse> getAppointments(@Header("Authorization") String token);
//
//    @GET("/api/send_test_email")
//    Call<Void> sendTestEmail();

}

