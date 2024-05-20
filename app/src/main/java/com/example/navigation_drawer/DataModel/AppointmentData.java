package com.example.navigation_drawer.DataModel;

import com.google.gson.annotations.SerializedName;

public class AppointmentData {
    @SerializedName("appointment_id")
    private String appointmentId;
    @SerializedName("clinic_id")
    private String clinicId;
    @SerializedName("chief_id")
    private String chiefId;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("remark")
    private String remark;
    @SerializedName("patient_id")
    private String patientId;

    public AppointmentData(String clinicId, String chiefId, String datetime, String mobile, String remark, String patientId) {
        this.clinicId = clinicId;
        this.chiefId = chiefId;
        this.datetime = datetime;
        this.mobile = mobile;
        this.remark = remark;
        this.patientId = patientId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
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
}
