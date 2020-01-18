package com.brainybrains.expensesmanager.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.brainybrains.expensesmanager.Adapter.HomeAdapter;
import com.brainybrains.expensesmanager.Adapter.ListViewDaapter;
import com.brainybrains.expensesmanager.AllDataTrangactionClass.DataAll;
import com.brainybrains.expensesmanager.DataBase.DatabaseOpenHelper;
import com.brainybrains.expensesmanager.R;
import com.brainybrains.expensesmanager.Spinner_Classes.AllArray;
import com.brainybrains.expensesmanager.Spinner_Classes.MonthAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ShowIncome extends Fragment {

    private Context context;
    private ImageView tooberBackArrowshowIncome,addIncomeOnShowIncome;
    private Spinner showIncomeFilterByMonth;
    private PieChart showIncomePieChart;
    private RecyclerView showIncomeRV;
    private FragmentManager fragmentManagerShowIncomeFragment;
    private FragmentTransaction fragmentTransactionShowIncomeFragment;
    private AllArray allArrayShowIncomeFregment;
    private MonthAdapter monthAdapterShowIncomeFregment;
    private ArrayList<AllArray> allArrays_ShowIncomeFregment;
    private HomeAdapter homeAdapter;
    private ArrayList<DataAll> allDatas;
    private DatabaseOpenHelper helper;
    private ListViewDaapter listViewAdapter;
    private float persent;
    private TextView testTV;
    private Fragment fragment;
    private int salary,refund,sales,savings,income,expenses,amountInt;



    public ShowIncome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View showIncomeView = inflater.inflate(R.layout.fragment_show_income, container, false);


        findId(showIncomeView);
        handelshowData();
        forPi(showIncomeView);


        showIncomeFilterByMonth = showIncomeView.findViewById(R.id.show_income_mounth_Spinner);
        allArrayShowIncomeFregment = new AllArray();
        allArrays_ShowIncomeFregment=allArrayShowIncomeFregment.getMonthName();
        monthAdapterShowIncomeFregment=new MonthAdapter(getContext(),allArrays_ShowIncomeFregment);
        showIncomeFilterByMonth.setAdapter(monthAdapterShowIncomeFregment);


        tooberBackArrowshowIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment =new HomeFragment();
                loadFragment(homeFragment);
            }

        });

        addIncomeOnShowIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddIncome addIncome =new AddIncome();
                loadFragment(addIncome);
            }
        });

        return showIncomeView;
    }

    public void loadFragment(Fragment fragment){

        fragmentManagerShowIncomeFragment = getFragmentManager();
        fragmentTransactionShowIncomeFragment = fragmentManagerShowIncomeFragment.beginTransaction();
        fragmentTransactionShowIncomeFragment.replace(R.id.main_frame_container, fragment);
        fragmentTransactionShowIncomeFragment.addToBackStack(null);
        fragmentTransactionShowIncomeFragment.commit();

    }



    private void findId(View showIncomeView) {
        tooberBackArrowshowIncome=showIncomeView.findViewById(R.id.show_income_backbutton);
        showIncomeRV=showIncomeView.findViewById(R.id.income_show_RV);
        addIncomeOnShowIncome=showIncomeView.findViewById(R.id.add_button_show_income);

    }


    private void forPi(View showIncomeView) {


        float use=0;
        int valu[]={salary,refund,sales,savings};
        String title[]={"Salary","Refund","Sales","Savings"};
        List<PieEntry> pieEntries=new ArrayList<>();
        for(int i=0;i<pieEntries.size();i++){
            use=(float)valu[i];
            pieEntries.add(new PieEntry(use,title[i]));

        }
        PieDataSet dataSet=new PieDataSet(pieEntries,"Expenses");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataSet);

        showIncomePieChart=showIncomeView.findViewById(R.id.show_income_PieChart);
        showIncomePieChart.setData(data);
        showIncomePieChart.animateY(500);
        showIncomePieChart.invalidate();
    }

    private void handelshowData() {

        allDatas = new ArrayList<>();
        helper = new DatabaseOpenHelper(getContext());
        // listViewDaapter =new ListViewDaapter(getContext(),allDatas);//-------forLV-//
        //  HomFregmentlistView.setAdapter(listViewDaapter);//-------forLV-//
        homeAdapter = new HomeAdapter(getContext(),allDatas);
        showIncomeRV.setLayoutManager(new LinearLayoutManager(getContext()));
        showIncomeRV.setAdapter(homeAdapter);

        DatabaseOpenHelper helper=new DatabaseOpenHelper(getContext());
        Cursor cursor =helper.getData();

        if(cursor.getCount() >= 1){
            amountInt=0;
            salary=0;refund=0;sales=0;savings=0;
            while(cursor.moveToNext()) {
                String type = cursor.getString(cursor.getColumnIndex(helper.COL_TYPE));
                if (type.equals("Income")) {
                    int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
                    int icon = cursor.getInt(cursor.getColumnIndex(helper.COL_ICON));
                    String catagory = cursor.getString(cursor.getColumnIndex(helper.COL_CATAGORY));
                    String date = cursor.getString(cursor.getColumnIndex(helper.COL_DATE));
                    String time = cursor.getString(cursor.getColumnIndex(helper.COL_TIME));
                    String amount = cursor.getString(cursor.getColumnIndex(helper.COL_AMOUNT));

                    if (catagory.equals("Salary"))
                        salary = salary + Integer.parseInt(amount);
                    else if (catagory.equals("Refund"))
                        refund = refund + Integer.parseInt(amount);
                    else if (catagory.equals("Sales"))
                        sales = sales + Integer.parseInt(amount);
                    else if (catagory.equals("Savings"))
                        savings = savings + Integer.parseInt(amount);

                    income = income + Integer.parseInt(amount);

                    amountInt = income - expenses;


                    allDatas.add(new DataAll(id, amount, icon, catagory, time, date));
                    homeAdapter.notifyDataSetChanged();
                }


            }}}}

