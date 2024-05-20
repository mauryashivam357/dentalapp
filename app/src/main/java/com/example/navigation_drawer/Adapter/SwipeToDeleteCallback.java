package com.example.navigation_drawer.Adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private BookingAdapter adapter;
    private Paint editButtonPaint;
    private RectF editButtonRect;

    public SwipeToDeleteCallback(BookingAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;

        editButtonPaint = new Paint();
        editButtonRect = new RectF();
        editButtonPaint.setColor(Color.parseColor("#64B5F6")); // Set color as per your requirement
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.LEFT) {
            // Perform your edit action here
            // You may want to start an activity or show a dialog for editing
        } else if (direction == ItemTouchHelper.RIGHT) {
            adapter.removeItem(position);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();

        if (dX < 0) { // Swiping to the left (reveal "Edit" button)
            editButtonRect.top = itemView.getTop();
            editButtonRect.bottom = itemView.getBottom();
            editButtonRect.right = itemView.getRight() + dX;
            editButtonRect.left = itemView.getRight();

            c.drawRect(editButtonRect, editButtonPaint);

            float textWidth = editButtonPaint.measureText("EDIT");
            float textX = editButtonRect.left + (editButtonRect.width() - textWidth) / 2;
            float textY = editButtonRect.top + (editButtonRect.height() / 2);

            editButtonPaint.setColor(Color.WHITE);
            c.drawText("EDIT", textX, textY, editButtonPaint);
        }
    }
}
