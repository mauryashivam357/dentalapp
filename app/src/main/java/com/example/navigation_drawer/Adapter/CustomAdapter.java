package com.example.navigation_drawer.Adapter;// MyAdapter.java
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation_drawer.Item;
import com.example.navigation_drawer.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Item> items;

    public CustomAdapter(List<Item> items) {
        this.items = items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Item.TYPE_A:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item, parent, false);
                return new ViewHolderA(view);
            case Item.TYPE_B:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
                return new ViewHolderB(view);
            case Item.TYPE_C:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_text, parent, false);
                return new ViewHolderC(view);
            default:
                throw new IllegalArgumentException("Invalid item type");
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        switch (item.getType()) {
            case Item.TYPE_A:
                ((ViewHolderA) holder).bind(item);
                break;
            case Item.TYPE_B:
                ((ViewHolderB) holder).bind(item);
                break;
            case Item.TYPE_C:
                ((ViewHolderC) holder).bind(item);
                break;
            default:
                throw new IllegalArgumentException("Invalid item type");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public abstract static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ViewHolderA extends ViewHolder {
        private TextView textViewA;

        public ViewHolderA(View itemView) {
            super(itemView);
            textViewA = itemView.findViewById(R.id.title);
        }

        public void bind(Item item) {
            textViewA.setText(item.getData());
        }
    }

    public static class ViewHolderB extends ViewHolder {
        private VideoView videoView;

        public ViewHolderB(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            if (videoView == null) {
                throw new IllegalStateException("VideoView with ID 'videoView' not found in the provided layout");
            }
        }

        public void bind(Item item) {
            try {
                Uri videoUri = Uri.parse("android.resource://" + itemView.getContext().getPackageName() + "/" + R.raw.preview);
                videoView.setVideoURI(videoUri);

                MediaController mediaController = new MediaController(itemView.getContext());
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);

                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        // Handle completion event
                    }
                });

                videoView.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }




    public static class ViewHolderC extends ViewHolder {
        private TextView textViewC;

        public ViewHolderC(View itemView) {
            super(itemView);
            textViewC = itemView.findViewById(R.id.titl);
        }

        public void bind(Item item) {
            textViewC.setText(item.getData());
        }
    }
}
