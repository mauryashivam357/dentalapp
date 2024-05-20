package com.example.navigation_drawer;// FeedbackFragment.java
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation_drawer.Adapter.CustomAdapter;
import com.example.navigation_drawer.R;
import com.trinnguyen.SegmentView;

import java.util.ArrayList;
import java.util.List;

public class FeedbackFragment extends Fragment implements SegmentView.OnSegmentItemSelectedListener {
    private RecyclerView recyclerView;
    private CustomAdapter myAdapter;
    private List<Item> items;

    private SegmentView segmentView;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        segmentView = view.findViewById(R.id.segment_2);
        segmentView.setNumSegments(3);

        segmentView.setText(0, "Text");
        segmentView.setText(1, "Video");
        segmentView.setText(2, "Review");
        segmentView.setSelectedIndex(0);
        recyclerView = view.findViewById(R.id.feedback_text);
        items = new ArrayList<>(); // Initialize an empty list

        // Set up RecyclerView with the initial data and layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new CustomAdapter(items);
        recyclerView.setAdapter(myAdapter);

        // Initialize data for the first segment (Text)
        List<Item> textItems = initializeTextData();
        myAdapter.setItems(textItems);

        return view;
    }

    private List<Item> initializeTextData() {
        List<Item> textItems = new ArrayList<>();

        // Add sample data, replace this with your actual data
        textItems.add(new Item("Text Data 1", Item.TYPE_A));
        textItems.add(new Item("Text Data 2", Item.TYPE_A));
        textItems.add(new Item("Text Data 3", Item.TYPE_A));

        return textItems;
    }

    private List<Item> initializeVideoData() {
        List<Item> videoItems = new ArrayList<>();

        // Add sample data, replace this with your actual data
        videoItems.add(new Item("Video Data 1", Item.TYPE_B));


        return videoItems;
    }

    private List<Item> initializeReviewData() {
        List<Item> reviewItems = new ArrayList<>();

        // Add sample data, replace this with your actual data
        reviewItems.add(new Item("Review Data 1", Item.TYPE_C));
        reviewItems.add(new Item("Review Data 2", Item.TYPE_C));
        reviewItems.add(new Item("Review Data 3", Item.TYPE_C));

        return reviewItems;
    }


    @Override
    public void onSegmentItemSelected(int index) {
        switch (index) {
            case 0:
                // Text selected, update RecyclerView with text data
                List<Item> textItems = initializeTextData();
                myAdapter.setItems(textItems);
                break;
            case 1:
                // Video selected, update RecyclerView with video data
                List<Item> videoItems = initializeVideoData();
                myAdapter.setItems(videoItems);
                break;
            case 2:
                // Review selected, update RecyclerView with review data
                List<Item> reviewItems = initializeReviewData();
                myAdapter.setItems(reviewItems);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        segmentView.setOnSegmentItemSelectedListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        segmentView.setOnSegmentItemSelectedListener(null);
    }

    @Override
    public void onSegmentItemReselected(int index) {
        Log.d("", "onSegmentItemReselected: ");
        segmentView.setNumSegments(index);

        // Optional: If you want to scroll to the top of the RecyclerView when the segment is selected
        recyclerView.smoothScrollToPosition(0);
        // This method is called when the currently selected segment is selected again.
        // You can use it to scroll to the top of the RecyclerView or perform any other actions.
    }
}
