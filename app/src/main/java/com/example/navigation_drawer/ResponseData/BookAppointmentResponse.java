package com.example.navigation_drawer.ResponseData;

import com.example.navigation_drawer.DataModel.BookAppointmentM;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookAppointmentResponse {
    @SerializedName("success")
    private boolean success;
    @SerializedName("book_appointment")
    private List<BookAppointmentM> bookAppointment;

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<BookAppointmentM> getBookAppointment() {
        return bookAppointment;
    }

    public void setBookAppointment(List<BookAppointmentM> bookAppointment) {
        this.bookAppointment = bookAppointment;
    }
}