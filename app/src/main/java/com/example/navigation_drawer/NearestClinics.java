package com.example.navigation_drawer;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.navigation_drawer.Adapter.DoctorAdapter;
import com.example.navigation_drawer.DataModel.DoctorNearby;
import com.example.navigation_drawer.Apis.ApiClient;
import com.example.navigation_drawer.Apis.ApiService;
import com.example.navigation_drawer.ResponseData.ClinicsResponse;
import com.example.navigation_drawer.utils.SessionManager;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class NearestClinics extends AppCompatActivity {
    private SessionManager sessionNearest;
    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    List<DoctorNearby> teamModelList = new ArrayList<>();
    private ProgressDialog progressDialog; // Declare progress dialog

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_doctor);

        sessionNearest = new SessionManager(this);


        // Initialize progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4CAF50")));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
            SpannableString title = new SpannableString("Nearest Clinics");
            title.setSpan(new ForegroundColorSpan(Color.WHITE), 0, title.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            actionBar.setTitle(title);
        }

        token = sessionNearest.getAuthToken();
        Log.d("TAG", "onResponse: " + token);
        fetchClinicsData();

        recyclerView = findViewById(R.id.docterrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorAdapter = new DoctorAdapter(new ArrayList<>());
        recyclerView.setAdapter(doctorAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doctorAdapter.filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchClinicsData() {
        // Show progress dialog before making the API call


        ApiService apiService = ApiClient.getClient(token).create(ApiService.class);
        Call<ClinicsResponse> call = apiService.getClinics(token);
        call.enqueue(new Callback<ClinicsResponse>() {
            @Override
            public void onResponse(Call<ClinicsResponse> call, Response<ClinicsResponse> response) {
                // Dismiss progress dialog after receiving response
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    ClinicsResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.isSuccess()) {
                        List<DoctorNearby> teams = apiResponse.getClinics();

                        JSONObject teamResponseJson = null;
                        if (teams != null) {
                            try {
                                // Log JSON response data
                                Gson gson = new Gson();
                                String jsonResponse = gson.toJson(apiResponse);
                                teamResponseJson = new JSONObject(jsonResponse);
                                Log.d("TAG", "JSON Response: " + jsonResponse);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (teamResponseJson != null) {
                                try {
                                    boolean success = teamResponseJson.getBoolean("success");
                                    JSONArray teamsArray = teamResponseJson.getJSONArray("clinics");
                                    Log.d("Response JSON", "Response: " + new Gson().toJson(teamResponseJson));
                                    // Process teams data
                                    for (int i = 0; i < teamsArray.length(); i++) {
                                        JSONObject teamObject = teamsArray.getJSONObject(i);
                                        int id = teamObject.getInt("id");
                                        String clinic_id = teamObject.getString("clinic_id");
                                        String clinic_name = teamObject.getString("clinic_name");
                                        String latitude = teamObject.getString("latitude");
                                        String longitude = teamObject.getString("longitude");
                                        String created_at = teamObject.getString("created_at");
                                        String updated_at = teamObject.getString("updated_at");
                                        // String imgurl = teamObject.getString("photo_url");
                                        Log.d("TAG", "onResponse: " + clinic_id);
                                        DoctorNearby teamModel = new DoctorNearby(id, clinic_id, clinic_name, longitude, latitude, created_at, updated_at);
                                        sessionNearest.saveClinics(teams);
                                        teamModelList.add(teamModel);
                                        doctorAdapter.updateData(teamModelList);
                                        doctorAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<ClinicsResponse> call, Throwable t) {
                // Dismiss progress dialog on failure too
                progressDialog.dismiss();

                Log.e("NearestClinics", "Failed to fetch clinics: " + t.getMessage());
            }
        });
    }


    @Override
    protected void onResume() {

        super.onResume();
    }


    @Override
    public void onBackPressed() {
        if (!sessionNearest.hasNavigatedBack()) {
            sessionNearest.setNavigatedBack(true);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("fragment", "desboard");
            startActivity(intent);
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
