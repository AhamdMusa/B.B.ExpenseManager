package com.brainybrains.expensesmanager.fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.brainybrains.expensesmanager.DataBase.DatabaseOpenHelper;
import com.brainybrains.expensesmanager.R;
import com.brainybrains.expensesmanager.Spinner_Classes.AllArray;
import com.brainybrains.expensesmanager.Spinner_Classes.MonthAdapter;
//import com.ezzytech.expensesmanager.date_time.Date_Time;

import java.util.ArrayList;
import java.util.Calendar;


public class AddIncome extends Fragment {

    private Context context;
    private TextView addDate_Income,addTime_Income;
    private ImageView tooberBackArrowAddIncome,saveButtonAddAddIncome;
    private Spinner spinnerCategoryAddIncome;
    private EditText addIncomeAmountET;
    private FragmentManager fragmentManagerAddIncomeFragment;
    private FragmentTransaction fragmentTransactionAddIncomeFragment;
    private AllArray allArrayAddIncomeFregment,allArrayAddIncomeFregmentPaymentCat;
    private MonthAdapter monthAdapterAddIncomeFregment;
    private ArrayList<AllArray> allArrays_AddIncomeFregment,allArrays_AddIncomeFregmentPaymentCat;
    private DatePickerDialog.OnDateSetListener dateAddIncome;
    private TimePickerDialog.OnTimeSetListener timeAddIncome;
    private DatabaseOpenHelper helper;
    private String categoryBySpinner;
    private String dateAdd;    //----addexpense date----------
    private String timeAdd;    //----addexpense time----------
    private int categoryIconBySpinner;
    private int hour, minute;
    private int amPm;

    //private Date_Time date_time;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;

    }

    public AddIncome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View addIncomeView= inflater.inflate(R.layout.fragment_add_income, container, false);



        findId(addIncomeView);
        forSpinner();
        forDate();
        forTime();








        tooberBackArrowAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment =new HomeFragment();
                loadFragment(homeFragment);
            }
        });

        saveButtonAddAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(v);
            }
        });

        return addIncomeView;
    }

    private void forTime() {
        addTime_Income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                hour=calendar.get(Calendar.HOUR);
                minute=calendar.get(Calendar.MINUTE);
                amPm=calendar.get(Calendar.AM_PM);
                TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), timeAddIncome,hour,minute,true);
                timePickerDialog.show();
            }

        });
        timeAddIncome=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                timeAdd=hour+":"+minute;
                addTime_Income.setText(timeAdd);
            }
        };
    }

    private void forDate() {
        addDate_Income.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar calendaraddExpense=Calendar.getInstance();
            int year= calendaraddExpense.get(Calendar.YEAR);
            int month=calendaraddExpense.get(Calendar.MONTH);
            int day=calendaraddExpense.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog=new DatePickerDialog(getContext(),dateAddIncome,year,month,day);
            dialog.show();
        }
    });
        dateAddIncome =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateAdd=dayOfMonth+"."+month+"."+year;
                addDate_Income.setText(dateAdd);
            }
        };

    }

    private void forSpinner() {
        allArrayAddIncomeFregment = new AllArray();
        allArrays_AddIncomeFregment=allArrayAddIncomeFregment.getIncomeName();
        monthAdapterAddIncomeFregment=new MonthAdapter(getContext(),allArrays_AddIncomeFregment);
        spinnerCategoryAddIncome.setAdapter(monthAdapterAddIncomeFregment);

        spinnerCategoryAddIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AllArray clickedCatagory = (AllArray) parent.getItemAtPosition(position);
                categoryBySpinner = ((AllArray) parent.getItemAtPosition(position)).getItemName();
                categoryIconBySpinner=((AllArray) parent.getItemAtPosition(position)).getIcon();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void findId(View addIncomeView) {
        addDate_Income = addIncomeView.findViewById(R.id.addDate_Income);
        addTime_Income = addIncomeView.findViewById(R.id.addTime_Income);

        tooberBackArrowAddIncome=addIncomeView.findViewById(R.id.toober_back_arrow_add_income);
        spinnerCategoryAddIncome=addIncomeView.findViewById(R.id.incomeCategorySpinner_add_income);
        addIncomeAmountET=addIncomeView.findViewById(R.id.addAmount_add_income);
        saveButtonAddAddIncome=addIncomeView.findViewById(R.id.save_button_add_income_add_income);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        helper=new DatabaseOpenHelper(context);

    }


    public void loadFragment(Fragment fragment) {
        fragmentManagerAddIncomeFragment = getFragmentManager();
        fragmentTransactionAddIncomeFragment = fragmentManagerAddIncomeFragment.beginTransaction();
        fragmentTransactionAddIncomeFragment.replace(R.id.main_frame_container, fragment);
        fragmentTransactionAddIncomeFragment.addToBackStack(null);
        fragmentTransactionAddIncomeFragment.commit();

    }

    public void insert(View view){
        String transactionType="Income";
        String catagory=categoryBySpinner;
        String amount= addIncomeAmountET.getText().toString();
        int icon=categoryIconBySpinner;
        String date=dateAdd;
        String time=timeAdd;
        long id=helper.addExpenses(transactionType,catagory,amount,time,date,icon);

        Toast.makeText(getContext(), "Id is "+id, Toast.LENGTH_SHORT).show();
    }

}
