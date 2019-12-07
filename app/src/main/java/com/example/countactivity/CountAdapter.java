package com.example.countactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CountAdapter extends ArrayAdapter<CountRecord> {

    private int recourseID;

    public CountAdapter(Context context, int textViewResourceID, List<CountRecord> objects){
        super(context,textViewResourceID,objects);
        recourseID = textViewResourceID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CountRecord countRecord = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(recourseID,parent,false);
        TextView textView = (TextView)view.findViewById(R.id.nameText);
        TextView textView1 = (TextView)view.findViewById(R.id.costText);
        TextView textView2 = (TextView)view.findViewById(R.id.timeText);
        textView.setText(countRecord.getName());
        textView1.setText(countRecord.getPrice());
        textView2.setText(countRecord.getTime());
        return view;
    }
}
