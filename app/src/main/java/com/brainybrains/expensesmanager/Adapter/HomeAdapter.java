package com.brainybrains.expensesmanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brainybrains.expensesmanager.AllDataTrangactionClass.DataAll;
import com.brainybrains.expensesmanager.DataBase.DatabaseOpenHelper;
import com.brainybrains.expensesmanager.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>  {
    private Context context;
    private ArrayList<DataAll> allDatas;
    private DatabaseOpenHelper helper;

    public HomeAdapter(Context context, ArrayList<DataAll> allDatas) {
        this.context = context;
        this.allDatas = allDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_exp_and_incom_card_desing, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final DataAll dataAll = allDatas.get(position);
        holder.itemIcon.setImageResource(dataAll.getIcon());
        holder.itemName.setText(dataAll.getCatagory());
        holder.date.setText(dataAll.getDate());
        holder.time.setText(dataAll.getTime());
        holder.price.setText(dataAll.getAmount());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                helper = new DatabaseOpenHelper(context);
                helper.deleteData(dataAll.getId());
                allDatas.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "1 item deleted", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return allDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemIcon;
        private TextView itemName, date, time, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.show_expense_card_design_icon);
            itemName = itemView.findViewById(R.id.show_expence_card_desing_catagory_TV);
            date = itemView.findViewById(R.id.show_expence_card_desing_date_TV);
            time = itemView.findViewById(R.id.show_expence_card_desing_time_TV);
            price = itemView.findViewById(R.id.show_expence_card_desing_amount_TV);

        }
    }
}
