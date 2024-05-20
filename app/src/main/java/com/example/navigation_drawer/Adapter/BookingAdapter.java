package com.example.navigation_drawer.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.navigation_drawer.DataModel.BookAppointmentM;
import com.example.navigation_drawer.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookingAdapter extends BaseAdapter {

    private List<BookAppointmentM> mData;
    private List<BookAppointmentM> mDataFiltered;
    private LayoutInflater mInflater;
    public BookingAdapter(Context context, List<BookAppointmentM> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mDataFiltered = new ArrayList<>(data);
    }

    public void setData(List<BookAppointmentM> newData) {
        this.mData = newData;
        this.mDataFiltered = new ArrayList<>(newData);
        notifyDataSetChanged();
    }

    public void filterData(boolean upcoming) {
        List<BookAppointmentM> filteredList = new ArrayList<>();
        Calendar currentCalendar = Calendar.getInstance();

        for (BookAppointmentM appointment : mData) {
            if (appointment.getDatetime() != null) { // Add null check here
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date appointmentDate = sdf.parse(appointment.getDatetime());

                    Calendar appointmentCalendar = Calendar.getInstance();
                    appointmentCalendar.setTime(appointmentDate);

                    // Check if the appointment is upcoming or historical
                    if (upcoming && appointmentCalendar.after(currentCalendar)) {
                        filteredList.add(appointment);
                    } else if (!upcoming && appointmentCalendar.before(currentCalendar)) {
                        filteredList.add(appointment);
                    }
                } catch (ParseException e) {
                    Log.e("BookingAdapter", "Error parsing date: " + e.getMessage());
                }
            }
        }

        mDataFiltered = filteredList;
        notifyDataSetChanged();
    }
    public void filterDataByDate(String selectedDate) {
        List<BookAppointmentM> filteredList = new ArrayList<>();
        for (BookAppointmentM appointment : mData) {
            if (appointment.getDatetime() != null && appointment.getDatetime().startsWith(selectedDate)) {
                filteredList.add(appointment);
            }
        }
        mDataFiltered = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.booking_item, parent, false);
            holder = new ViewHolder();
            holder.textViewItem = convertView.findViewById(R.id.doctor_name);
            holder.text_remark = convertView.findViewById(R.id.remark);
            holder.mobile = convertView.findViewById(R.id.mobile);
            holder.textViewDate = convertView.findViewById(R.id.date);
            holder.textViewMonth = convertView.findViewById(R.id.month);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BookAppointmentM appointment = mDataFiltered.get(position);
        holder.textViewItem.setText(appointment.getClinicId());
        holder.text_remark.setText(appointment.getRemark());
        holder.mobile.setText(appointment.getMobile());

        if (appointment.getDatetime() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(appointment.getDatetime());

                // Extract day and month from the date
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String month = new SimpleDateFormat("MMM", Locale.ENGLISH).format(calendar.getTime());

                // Set day and month in TextViews
                holder.textViewDate.setText(String.valueOf(day));
                holder.textViewMonth.setText(month);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return convertView;
    }

    static class ViewHolder {
        TextView textViewItem, text_remark, mobile;
        TextView textViewDate;
        TextView textViewMonth;
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }
}
