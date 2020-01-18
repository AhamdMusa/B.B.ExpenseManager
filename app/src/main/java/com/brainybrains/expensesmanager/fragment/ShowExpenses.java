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

public class ShowExpenses extends Fragment {

    private Cursor cursor;
    private Context context;
    private ImageView tooberBackArrowshowExpenses,showExpenseaddExpenses;
    private Spinner showExpensesFilterByMonth;
    private PieChart showExpensesPieChart;
    private RecyclerView showExpensesRV;
    private FragmentManager fragmentManagerShowExpenseFragment;
    private FragmentTransaction fragmentTransactionShowExpenseFragment;
    private AllArray allArrayShowExpenseFregment;
    private MonthAdapter monthAdapterShowExpenseFregment;
    private ArrayList<AllArray> allArrays_ShowExpenseFregment;
    private HomeAdapter homeAdapter;
    private ArrayList<DataAll> allDatas;
    private DatabaseOpenHelper helper;
    private ListViewDaapter listViewAdapter;
    private int amountInt,income,expenses,food,cioth,morgedes,health,fun,education,utilitus,retirment,gym,piza;
    private float persent;
    private TextView testTV; //<---------------for Test------------------------------>



    public ShowExpenses() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View showExpensesView= inflater.inflate(R.layout.fragment_show_expenses, container, false);


        findId(showExpensesView);
         handelshowData();
//        forSpinner();
        forPi(showExpensesView);
       // testTV.setText(String.valueOf(education)); <---------------fro Test------------------------------>


        // Inflate the layout for this fragment

        tooberBackArrowshowExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment =new HomeFragment();
                loadFragment(homeFragment);
            }
        });

        showExpenseaddExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpenses addExpenses =new AddExpenses();
                loadFragment(addExpenses);
            }
        });






        return showExpensesView;
    }

    public void forPi(View showExpensesView) {

        float use=0;
        int valu[]={food,cioth,morgedes,health,fun,education,utilitus,retirment,gym,piza};
        String title[]={"Food","Clothes","Mortgage","Health","Fun","Education","Utilities","Retirement","Gym","Pizza"};
        List<PieEntry> pieEntries=new ArrayList<>();
        for(int i=0;i<pieEntries.size();i++){
            use=(float)valu[i];
            pieEntries.add(new PieEntry(use,title[i]));

        }
        PieDataSet dataSet=new PieDataSet(pieEntries,"Expenses");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataSet);

        showExpensesPieChart=showExpensesView.findViewById(R.id.show_expenses_PieChart);
        showExpensesPieChart.setData(data);
        showExpensesPieChart.animateY(500);
        showExpensesPieChart.invalidate();

    }


//    private void forSpinner() {
//        allArrayShowExpenseFregment = new AllArray();
//        allArrays_ShowExpenseFregment=allArrayShowExpenseFregment.getMonthName();
//        monthAdapterShowExpenseFregment=new MonthAdapter(getContext(),allArrays_ShowExpenseFregment);
//        showExpensesFilterByMonth.setAdapter(monthAdapterShowExpenseFregment);
//    }

    public void loadFragment(Fragment fragment) {
        fragmentManagerShowExpenseFragment = getFragmentManager();
        fragmentTransactionShowExpenseFragment = fragmentManagerShowExpenseFragment.beginTransaction();
        fragmentTransactionShowExpenseFragment.replace(R.id.main_frame_container, fragment);
        fragmentTransactionShowExpenseFragment.addToBackStack(null);
        fragmentTransactionShowExpenseFragment.commit();

    }

    private void findId(View showExpensesView) {
        tooberBackArrowshowExpenses=showExpensesView.findViewById(R.id.show_expenses_backbutton);
        // showExpensesFilterByMonth=showExpensesView.findViewById(R.id.show_expenses_mounth_Spinner);
        //testTV=showExpensesView.findViewById(R.id.testTV);
        showExpensesRV=showExpensesView.findViewById(R.id.expenses_show_RV);
        showExpenseaddExpenses=showExpensesView.findViewById(R.id.subtract_button_show_expense);

    }

    private void handelshowData() {
        allDatas = new ArrayList<>();
        helper = new DatabaseOpenHelper(getContext());
        // listViewDaapter =new ListViewDaapter(getContext(),allDatas);//-------forLV-//
        //  HomFregmentlistView.setAdapter(listViewDaapter);//-------forLV-//
        homeAdapter = new HomeAdapter(getContext(),allDatas);
        showExpensesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        showExpensesRV.setAdapter(homeAdapter);

        DatabaseOpenHelper helper=new DatabaseOpenHelper(getContext());
         Cursor cursor =helper.getData();

        if(cursor.getCount() >= 1){
            amountInt=0;
            food=0;cioth=0;morgedes=0;health=0;fun=0;
            education=0;utilitus=0;retirment=0;gym=0;piza=0;
            income=0;
            while(cursor.moveToNext()) {
                String type = cursor.getString(cursor.getColumnIndex(helper.COL_TYPE));
               if (type.equals("Expense")) {
                    int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
                    int icon = cursor.getInt(cursor.getColumnIndex(helper.COL_ICON));
                    String catagory = cursor.getString(cursor.getColumnIndex(helper.COL_CATAGORY));
                    String date = cursor.getString(cursor.getColumnIndex(helper.COL_DATE));
                    String time = cursor.getString(cursor.getColumnIndex(helper.COL_TIME));
                    String amount = cursor.getString(cursor.getColumnIndex(helper.COL_AMOUNT));

                   if (catagory.equals("Food"))
                           food=food+Integer.parseInt(amount);
                       else if (catagory.equals("Clothes"))
                               cioth=cioth+Integer.parseInt(amount);
                           else if (catagory.equals("Mortgage"))
                               morgedes=morgedes+Integer.parseInt(amount);
                          else if (catagory.equals("Health"))
                               health=health+Integer.parseInt(amount);
                          else if (catagory.equals("Fun"))
                               fun=fun+Integer.parseInt(amount);
                           else if (catagory.equals("Education"))
                               education=education+Integer.parseInt(amount);
                          else if (catagory.equals("Utilities"))
                               utilitus=utilitus+Integer.parseInt(amount);
                          else if (catagory.equals("Retirement"))
                               retirment=retirment+Integer.parseInt(amount);
                          else if (catagory.equals("Gym"))
                               gym=gym+Integer.parseInt(amount);
                          else if (catagory.equals("Pizza"))
                               piza=piza+Integer.parseInt(amount);


                       expenses=expenses+Integer.parseInt(amount);

                   amountInt=income-expenses;


                    allDatas.add(new DataAll(id, amount, icon, catagory, time, date));
                    homeAdapter.notifyDataSetChanged();

//            listViewDaapter.notifyDataSetChanged();

                }

            }}
    }

}
