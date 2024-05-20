package com.example.navigation_drawer.DataModel;

import com.google.gson.annotations.SerializedName;

public class BookAppointmentM {
    @SerializedName("id")
    private int id;

    @SerializedName("clinic_id")
    private String clinicId;

    @SerializedName("appointment_id")
    private String appointmentId;

    @SerializedName("chief_id")
    private String chiefId;

    @SerializedName("datetime")
    private String datetime;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("remark")
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getChiefId() {
        return chiefId;
    }

    public void setChiefId(String chiefId) {
        this.chiefId = chiefId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    @SerializedName("patient_id")
    private String patientId;

    public BookAppointmentM(int id, String clinicId, String appointmentId, String chiefId, String datetime, String mobile, String remark, String patientId, String createdAt, String updatedAt) {
        this.id = id;
        this.clinicId = clinicId;
        this.appointmentId = appointmentId;
        this.chiefId = chiefId;
        this.datetime = datetime;
        this.mobile = mobile;
        this.remark = remark;
        this.patientId = patientId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;
}
