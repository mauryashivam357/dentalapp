package com.example.navigation_drawer;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer.DataModel.AppointmentData;
import com.example.navigation_drawer.ResponseData.AppointmentResponse;
import com.example.navigation_drawer.DataModel.ChiefCompModel;
import com.example.navigation_drawer.ResponseData.ChiefCompResponse;
import com.example.navigation_drawer.DataModel.DoctorNearby;
import com.example.navigation_drawer.ResponseData.PatientResponse;
import com.example.navigation_drawer.Apis.ApiClient;
import com.example.navigation_drawer.Apis.ApiService;
import com.example.navigation_drawer.DataModel.PatientModel;
import com.example.navigation_drawer.utils.SessionManager;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAppointment extends AppCompatActivity {
    Button submit, add_button;
    EditText textviewMobileEditText, textviewRemarkEditText;
    TextView selectedDateTimeEditText;
    Spinner customSpinner, textviewPecent, textviewChiefComplaintEditText;
    private Calendar calendar;
    private String selectedOption;
    List<DoctorNearby> clinics  ;
    private SessionManager sessionManager;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment2);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(("#4CAF50"))));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

            // Set the title with a custom text color
            SpannableString title = new SpannableString("Book Appointment");
            title.setSpan(new ForegroundColorSpan(Color.WHITE), 0, title.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            actionBar.setTitle(title);
        }

        sessionManager = new SessionManager(this);

        add_button = findViewById(R.id.icon_button);
        submit = findViewById(R.id.submit_button);
        selectedDateTimeEditText = findViewById(R.id.selected_time_edit_text);
        textviewMobileEditText = findViewById(R.id.textview_mobile_edit_text);
        textviewRemarkEditText = findViewById(R.id.textview_remark_edit_text);
        customSpinner = findViewById(R.id.custom_spinner);
        textviewPecent = findViewById(R.id.textview_pecent);
        textviewChiefComplaintEditText = findViewById(R.id.textview_chief_complaint_edit_text);


        fetchchifDat();
         fetchClinicsData();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookAppointment.this, AddPatientActivity.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAppointment();

            }
        });

        calendar = Calendar.getInstance();
        setupClinics();
        setupPatient();
        setupComplaint();

        selectedDateTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePickerDialog();
            }
        });
    }
    public void fetchClinicsData() {

        token=  sessionManager.getAuthToken();

        ApiService apiService = ApiClient.getClient(token).create(ApiService.class);
        Call<PatientResponse> call = apiService.getPatients(token);
        call.enqueue(new Callback<PatientResponse>() {
            @Override
            public void onResponse(Call<PatientResponse> call, Response<PatientResponse> response) {
                if (response.isSuccessful()) {
                    PatientResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.isSuccess()) {
                        List<PatientModel> teams = apiResponse.getPatients();

                        JSONObject teamResponseJson = null;
                        if (teams != null) {

                            try {
                                // Log JSON response data
                                Gson gson = new Gson();
                                String jsonResponse = gson.toJson(apiResponse);
                                teamResponseJson = new JSONObject(jsonResponse);
                                Log.d(TAG, "JSON Response: " + jsonResponse);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (teamResponseJson != null) {
                                try {
                                    boolean success = teamResponseJson.getBoolean("success");
                                    JSONArray teamsArray = teamResponseJson.getJSONArray("patients");
                                    Log.d("Response JSON", "Response: " + new Gson().toJson(teamResponseJson));
                                    // Process teams data
                                    for (int i = 0; i < teamsArray.length(); i++) {
                                        JSONObject teamObject = teamsArray.getJSONObject(i);
                                        int  id = teamObject.getInt("id");
                                        String clinic_name = teamObject.getString("patient_id");
                                        String latitude = teamObject.getString("patient_name");
                                        String created_at = teamObject.getString("created_at");
                                        String updated_at = teamObject.getString("updated_at");
                                        // String imgurl = teamObject.getString("photo_url");

                                        PatientModel teamModel = new PatientModel(id,  clinic_name,  latitude, created_at,updated_at);
                                        sessionManager.setPatientsData(teams);
                                        Log.d(TAG, "onResponse: " +latitude);
                                        Log.d(TAG, "PatientModel Data: " + teamModel.toString());
                                    }} catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<PatientResponse> call, Throwable t) {
                Log.e("NearestClinics", "Failed to fetch clinics: " + t.getMessage());
            }
        });
    }
    public void fetchchifDat() {

        token=  sessionManager.getAuthToken();

        ApiService apiService = ApiClient.getClient(token).create(ApiService.class);
        Call<ChiefCompResponse> call = apiService.getChiefComplaints(token);
        call.enqueue(new Callback<ChiefCompResponse>() {
            @Override
            public void onResponse(Call<ChiefCompResponse> call, Response<ChiefCompResponse> response) {
                if (response.isSuccessful()) {
                    ChiefCompResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.isSuccess()) {
                        List<ChiefCompModel> chiefComplist = apiResponse.getChief_complaints();

                        JSONObject teamResponseJson = null;
                        if (chiefComplist != null) {

                            try {
                                // Log JSON response data
                                Gson gson = new Gson();
                                String jsonResponse = gson.toJson(apiResponse);
                                teamResponseJson = new JSONObject(jsonResponse);
                                Log.d(TAG, "JSON Response: " + jsonResponse);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (teamResponseJson != null) {
                                try {
                                    boolean success = teamResponseJson.getBoolean("success");
                                    JSONArray teamsArray = teamResponseJson.getJSONArray("chief_complaints");
                                    Log.d("Response JSON", "Response: " + new Gson().toJson(teamResponseJson));
                                    // Process teams data
                                    for (int i = 0; i < teamsArray.length(); i++) {
                                        JSONObject teamObject = teamsArray.getJSONObject(i);
                                        if (teamObject.has("id")) { // Check if "id" key exists
                                            int chiefId = teamObject.getInt("id");
                                            String chiefComplaintId = teamObject.getString("chief_id");
                                            String chiefComplaintName = teamObject.getString("chief_complaint_name");
                                            String createdAt = teamObject.getString("created_at");
                                            String updatedAt = teamObject.getString("updated_at");

                                            // Create a ChiefCompModel object
                                            ChiefCompModel chiefCompModel = new ChiefCompModel(chiefId, chiefComplaintId, chiefComplaintName, createdAt, updatedAt);
                                            sessionManager.saveChiefComplaint(chiefComplist);
                                            Log.d(TAG, "onResp  " + chiefComplaintName);
                                            Log.d(TAG, "Patien  Data: " + chiefCompModel.getChiefId());
                                        } else {
                                            Log.e(TAG, "No value for id");
                                        }
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ChiefCompResponse> call, Throwable t) {
                Log.e("NearestClinics", "Failed to fetch clinics: " + t.getMessage());
            }
        });
    }

    private void handleAppointment() {
        String token = sessionManager.getAuthToken();

        ApiService service = ApiClient.getClient(token).create(ApiService.class);
        String clinicId = customSpinner.getSelectedItem().toString();
        String chiefId = textviewChiefComplaintEditText.getSelectedItem().toString();
        String dateTime = selectedDateTimeEditText.getText().toString();
        String mobile = textviewMobileEditText.getText().toString();
        String remark = textviewRemarkEditText.getText().toString();
        String patientId = "textviewPecent.getText().toString()";
   //     Log.d("TAG", "handleAppointment: "+clinicId+ chiefId+dateTime+mobile);
        Log.d("TAG", "handleAppointment: "+dateTime);
        AppointmentData data = new AppointmentData(clinicId, chiefId, dateTime, "mobile", "Appointment for toothache", "patient_id_1");

        Call<AppointmentResponse> call = service.bookAppointment(token, data);
        call.enqueue(new Callback<AppointmentResponse>() {
            @Override
            public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {
                if (response.isSuccessful()) {
                    AppointmentResponse appointmentResponse = response.body();
                    if (appointmentResponse != null && "success".equals(appointmentResponse.getSuccess())) {
                        // Save appointment data in session
                        sessionManager.saveAppointmentData(data);

                        // Handle successful response
                        String message = appointmentResponse.getMessage();
                        Toast.makeText(BookAppointment.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle unsuccessful response
                        Toast.makeText(BookAppointment.this, "Failed to book appointment", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    // Handle other HTTP error codes
                    Toast.makeText(BookAppointment.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AppointmentResponse> call, Throwable t) {
                // Handle network errors
                Toast.makeText(BookAppointment.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDateTimePickerDialog() {
        new SingleDateAndTimePickerDialog.Builder(this)
                .curved()
                .setDayFormatter(new SimpleDateFormat("dd MMM yyyy")).title("Date & Time").listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        // Format the selected date as per your requirement
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
                        String formattedDate = dateFormat.format(date);



                        // Set the formatted date in the TextView
                        selectedDateTimeEditText.setText(formattedDate);
                    }
                }).display();
    }

//    private void setupClinics() {
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Clinic, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        customSpinner.setAdapter(adapter);
//    }
   private void setupClinics() {
    // Get the clinic data from the session
    List<DoctorNearby> clinics = sessionManager.getClinics();

    // Create a list to hold clinic names
    List<String> clinicNames = new ArrayList<>();

    // Iterate through the clinics and add their names to the list
    for (DoctorNearby clinic : clinics) {
        clinicNames.add(clinic.getClinicName());
    }

    // Create an adapter with the clinic names list
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clinicNames);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Set the adapter to the spinner
    customSpinner.setAdapter(adapter);
}


    private void setupPatient() {
        List<PatientModel> patients = sessionManager.getpatientsData();

        List<String> patientsNames = new ArrayList<>();
            // Process the list of PatientModel objects
            for (PatientModel patient : patients) {

                patientsNames.add(patient.getPatientName());
                Log.d(TAG, "setupPatient: "+patient.getPatientName());
                // Access patient data like id, name, latitude, etc.

            }    // Add more logs or processing as needed

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,patientsNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textviewPecent.setAdapter(adapter);
    }

    private void setupComplaint() {
        List<ChiefCompModel> List_complaints = sessionManager.getChiefComplaint();

        List<String> chief_complaints = new ArrayList<>();
        // Process the list of PatientModel objects
        for (ChiefCompModel chief_complaint : List_complaints) {

            chief_complaints.add(chief_complaint.getChiefComplaintName());
            Log.d(TAG, "setupComplaint: "+chief_complaint.getChiefComplaintName());
            // Access patient data like id, name, latitude, etc.

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,  android.R.layout.simple_spinner_item,chief_complaints);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textviewChiefComplaintEditText.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
