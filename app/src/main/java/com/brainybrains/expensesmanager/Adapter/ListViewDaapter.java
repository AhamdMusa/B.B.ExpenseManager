package com.brainybrains.expensesmanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.brainybrains.expensesmanager.AllDataTrangactionClass.DataAll;
import com.brainybrains.expensesmanager.DataBase.DatabaseOpenHelper;
import com.brainybrains.expensesmanager.R;

import java.util.ArrayList;

public class ListViewDaapter extends ArrayAdapter<DataAll> {
    private ArrayList<DataAll> allDatas;
    private Context context;
    private DatabaseOpenHelper helper;

    public ListViewDaapter(@NonNull Context context, ArrayList<DataAll> students) {
        super(context, R.layout.show_exp_and_incom_card_desing,students);
        this.context = context;
        this.allDatas = allDatas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView catagoryTV,dateTV,timeTV,priceTV;
        ImageView iconIV;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.show_exp_and_incom_card_desing,parent,false);
//        final DataAll dataAll = allDatas.get(position);
//        holder.itemIcon.setImageResource(dataAll.getIcon());
//        holder.itemName.setText(dataAll.getCatagory());
//        holder.date.setText(dataAll.getDate());
//        holder.time.setText(dataAll.getTime());
//        holder.price.setText(dataAll.getAmount());

        catagoryTV = view.findViewById(R.id.show_expence_card_desing_catagory_TV);
        dateTV = view.findViewById(R.id.show_expence_card_desing_date_TV);
        timeTV=view.findViewById(R.id.show_expence_card_desing_time_TV);
        priceTV=view.findViewById(R.id.show_expence_card_desing_amount_TV);
        iconIV = view.findViewById(R.id.show_expense_card_design_icon);

        final DataAll dataAll = allDatas.get(position);
        catagoryTV.setText(dataAll.getCatagory());
        dateTV.setText(dataAll.getDate());
        timeTV.setText(dataAll.getTime());
        priceTV.setText(dataAll.getAmount());
        iconIV.setImageResource(dataAll.getIcon());
        return view;
    }
}
