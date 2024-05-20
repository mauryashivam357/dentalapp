package com.example.navigation_drawer.DataModel;

import com.google.gson.annotations.SerializedName;

public class TeamModel {
    @SerializedName("name")
    private String name;

    @SerializedName("position")
    private String position;

    @SerializedName("highest_qualification")
    private String highestQualification;

    @SerializedName("years_of_experience")
    private int yearsOfExperience;

    @SerializedName("about_doctor")
    private String aboutDoctor;

    @SerializedName("photo_url")
    private String photoUrl;

    @SerializedName("associated_clinic_name")
    private String associatedClinicName;

    @SerializedName("clinic_id")
    private int clinicId;

    // Constructor
    public TeamModel(String name, String position, String highestQualification, int yearsOfExperience,
                     String aboutDoctor, String photoUrl, String associatedClinicName, int clinicId) {
        this.name = name;
        this.position = position;
        this.highestQualification = highestQualification;
        this.yearsOfExperience = yearsOfExperience;
        this.aboutDoctor = aboutDoctor;
        this.photoUrl = photoUrl;
        this.associatedClinicName = associatedClinicName;
        this.clinicId = clinicId;
    }

    // Getters and Setters (You can generate these using your IDE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getAboutDoctor() {
        return aboutDoctor;
    }

    public void setAboutDoctor(String aboutDoctor) {
        this.aboutDoctor = aboutDoctor;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAssociatedClinicName() {
        return associatedClinicName;
    }

    public void setAssociatedClinicName(String associatedClinicName) {
        this.associatedClinicName = associatedClinicName;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }
}
