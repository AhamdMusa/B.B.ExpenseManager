package com.brainybrains.expensesmanager.Spinner_Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.brainybrains.expensesmanager.R;

import java.util.ArrayList;

public class MonthAdapter extends ArrayAdapter<AllArray> {

    private ImageView itemIcon;
    private TextView itemName;
    private ArrayList<AllArray> allArrays;
    private Context context;




    public MonthAdapter(@NonNull Context context, ArrayList<AllArray> allArrays) {
        super(context,R.layout.spinner_desing,allArrays);
        this.context = context;
        this.allArrays=allArrays;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_desing, parent, false);
        }

        itemIcon = convertView.findViewById(R.id.image_view_icon);
        itemName = convertView.findViewById(R.id.month_nameTV);

        AllArray currentItem = getItem(position);

        if (currentItem != null) {
            itemIcon.setImageResource(currentItem.getIcon());
            itemName.setText(currentItem.getItemName());
        }

        return convertView;
    }
}
