package com.example.navigation_drawer;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.navigation_drawer.Adapter.BookingAdapter;
import com.example.navigation_drawer.Apis.ApiClient;
import com.example.navigation_drawer.Apis.ApiService;
import com.example.navigation_drawer.DataModel.BookAppointmentM;
import com.example.navigation_drawer.ResponseData.BookAppointmentResponse;
import com.example.navigation_drawer.utils.SessionManager;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.trinnguyen.SegmentView;
import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Appointments extends Fragment implements DatePickerListener {
    ProgressDialog progressDialog;

    private SwipeMenuListView listView; // Change RecyclerView to SwipeMenuListView
    private BookingAdapter bookingAdapter;
    private SegmentView segmentView;

    SessionManager sessionManager;
    List<BookAppointmentM> bookingList = new ArrayList<>();
    String token;

    public Appointments() {

    }

    public static Appointments newInstance() {
        return new Appointments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointments, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);
        listView = view.findViewById(R.id.listView); // Change RecyclerView to SwipeMenuListView

        bookingAdapter = new BookingAdapter(getContext(), new ArrayList<>());
        listView.setAdapter(bookingAdapter);

        fetchClinicsData();
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // Create edit item
                SwipeMenuItem editItem = new SwipeMenuItem(getContext());
                editItem.setBackground(R.drawable.swipe_edit_background);
                editItem.setWidth(dp2px(90));
                editItem.setIcon(R.drawable.baseline_edit_24 );
                menu.addMenuItem(editItem);

                // Create delete item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setBackground(R.drawable.swipe_delete_background);
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.drawable.ic_baseline_delete_24);
                menu.addMenuItem(deleteItem);
            }
        };

        // Set swipe menu creator
        listView.setMenuCreator(creator);

        // Set click listener for swipe menu items
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0: // Edit item
                        // Implement edit functionality here
                        Intent intent = new Intent(getActivity(), BookAppointment.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "Edit option clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 1: // Delete item
                        // Implement delete functionality here
                        bookingAdapter.removeItem(position); // Remove item from adapter
                        Toast.makeText(getContext(), "Delete option clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


        segmentView = view.findViewById(R.id.segment1);
        segmentView.setNumSegments(2);
        segmentView.setText(0, "Upcoming");
        segmentView.setText(1, "History");
        segmentView.setSelectedIndex(0);

        // Set segment selection listener
        segmentView.setOnSegmentItemSelectedListener(new SegmentView.OnSegmentItemSelectedListener() {
            @Override
            public void onSegmentItemSelected(int index) {
                if (index == 0) {
                    bookingAdapter.filterData(true); // Show upcoming appointments
                } else {
                    bookingAdapter.filterData(false); // Show historical appointments
                }
            }

            @Override
            public void onSegmentItemReselected(int index) {
                segmentView.setNumSegments(index);
            }
        });

        HorizontalPicker picker = view.findViewById(R.id.datePicker);
        picker.setListener(this)
                .setDays(365)
                .setOffset(90)
                .setDateSelectedColor((Color.parseColor("#4CAF50")))
                .setDateSelectedTextColor(Color.WHITE)
                .setMonthAndYearTextColor(Color.BLACK)
                .setTodayButtonTextColor((Color.parseColor("#4CAF50")))
                .setTodayDateTextColor((Color.RED))
                .setTodayDateBackgroundColor(Color.WHITE)
                .setUnselectedDayTextColor(Color.WHITE)
                .setDayOfWeekTextColor(Color.BLACK)
                .setUnselectedDayTextColor((Color.parseColor("#4CAF50")))
                .showTodayButton(false)
                .init();

        // or on the View directly after init was completed
        picker.setBackgroundColor(Color.WHITE);
        picker.setDate(new DateTime());

        return view;
    }

    @Override
    public void onDateSelected(DateTime dateSelected) {
        // Filter appointments based on selected date
        String selectedDate = DateFormat.format("yyyy-MM-dd", dateSelected.toDate()).toString();
        bookingAdapter.filterDataByDate(selectedDate);
    }

    private void fetchClinicsData() {
        progressDialog.show();
        ApiService apiService = ApiClient.getClient(token).create(ApiService.class);
        Call<BookAppointmentResponse> call = apiService.getAppointments(token);
        call.enqueue(new Callback<BookAppointmentResponse>() {
            @Override
            public void onResponse(Call<BookAppointmentResponse> call, Response<BookAppointmentResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    BookAppointmentResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.isSuccess()) {
                        List<BookAppointmentM> appointments = apiResponse.getBookAppointment();
                        bookingList.addAll(appointments);
                        bookingAdapter.setData(bookingList);
                    } else {
                        Log.e(TAG, "API response unsuccessful or API response is null");
                    }
                } else {
                    Log.e(TAG, "API call unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<BookAppointmentResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, "Failed to fetch clinics: " + t.getMessage());

            }
        });
    }
    private int dp2px(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
