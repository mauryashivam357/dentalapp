package com.example.navigation_drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso; // Import Picasso

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String[] imageUrls; // Array to hold image URLs

    public SliderAdapter(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, null);
        ImageView imageView = view.findViewById(R.id.imageViewMain);

        // Load image from URL using Picasso
        Picasso.get().load(imageUrls[position]).into(imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click events
                Toast.makeText(context, "Slide " + (position + 1) + " Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
