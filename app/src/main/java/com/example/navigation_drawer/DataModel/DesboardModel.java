package com.example.navigation_drawer.DataModel;

public class DesboardModel {
    private String name;
    private String position;
    private String highest_qualification;
    private int years_of_experience;
    private String about_doctor;
    private String photo_url;
    private String associated_clinic_name;
    private int clinic_id;

    public DesboardModel(String name, String position, String highest_qualification, int years_of_experience, String about_doctor, String photo_url, String associated_clinic_name, int clinic_id) {
        this.name = name;
        this.position = position;
        this.highest_qualification = highest_qualification;
        this.years_of_experience = years_of_experience;
        this.about_doctor = about_doctor;
        this.photo_url = photo_url;
        this.associated_clinic_name = associated_clinic_name;
        this.clinic_id = clinic_id;
    }

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

    public String getHighest_qualification() {
        return highest_qualification;
    }

    public void setHighest_qualification(String highest_qualification) {
        this.highest_qualification = highest_qualification;
    }

    public int getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public String getAbout_doctor() {
        return about_doctor;
    }

    public void setAbout_doctor(String about_doctor) {
        this.about_doctor = about_doctor;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getAssociated_clinic_name() {
        return associated_clinic_name;
    }

    public void setAssociated_clinic_name(String associated_clinic_name) {
        this.associated_clinic_name = associated_clinic_name;
    }

    public int getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(int clinic_id) {
        this.clinic_id = clinic_id;
    }


        // Getters and setters (you can generate these using your IDE or write them manually)
    }
