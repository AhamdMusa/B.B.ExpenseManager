package com.brainybrains.expensesmanager.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.brainybrains.expensesmanager.DataBase.DatabaseOpenHelper;
//import com.ezzytech.expensesmanager.Pogos.TimePiker;
import com.brainybrains.expensesmanager.R;
import com.brainybrains.expensesmanager.Spinner_Classes.AllArray;
import com.brainybrains.expensesmanager.Spinner_Classes.MonthAdapter;
//import com.ezzytech.expensesmanager.date_time.Date_Time;

import java.util.ArrayList;
import java.util.Calendar;

public class AddExpenses extends Fragment  {
    private Context context;
    private ImageView tooberBackArrowAddExpense,saveButtonAddAddExpense;
    private Spinner spinnerCategoryAddExpense;
    private EditText addExpenseAmountET;
    private FragmentManager fragmentManagerAddExpenseFragment;
    private FragmentTransaction fragmentTransactionAddExpenseFragment;
    private AllArray allArrayAddExpenseFregment,allArrayAddExpenseFregmentPaymentCat;
    private MonthAdapter monthAdapterAddExpenseFregment;
    private ArrayList<AllArray> allArrays_AddExpenseeFregment,allArrays_AddExpenseeFregmentPaymentCat;
    private TextView addDateExpense,addTimeExpense;
    private DatePickerDialog.OnDateSetListener dateAddExpense;
    private TimePickerDialog.OnTimeSetListener timeAddExpense;
    private DatabaseOpenHelper helper;
    private String categoryBySpinner;
    private String dateAdd;    //----addexpense date----------
    private String timeAdd;    //----addexpense time----------
    private int categoryIconBySpinner;
    private int hour, minute;
    private int amPm;

    public AddExpenses() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View addExpenseView= inflater.inflate(R.layout.fragment_add_expenses, container, false);

            findId(addExpenseView);
            forSpinner();
            forDate();
            forTime();



        tooberBackArrowAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment =new HomeFragment();
                loadFragment(homeFragment);

            }
        });

        saveButtonAddAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(v);   //-------for DATABASE value input-------------


            }
        });

        return addExpenseView;
    }

    private void forTime() {
        addTimeExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                hour=calendar.get(Calendar.HOUR);
                minute=calendar.get(Calendar.MINUTE);
                amPm=calendar.get(Calendar.AM_PM);
                TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), timeAddExpense,hour,minute,true);
                    timePickerDialog.show();
            }

        });
            timeAddExpense=new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hour, int minute) {
                    timeAdd=hour+":"+minute;
                    addTimeExpense.setText(timeAdd);
                }
            };






    }

    private void forDate() {

        addDateExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendaraddExpense=Calendar.getInstance();
                int year= calendaraddExpense.get(Calendar.YEAR);
                int month=calendaraddExpense.get(Calendar.MONTH);
                int day=calendaraddExpense.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(getContext(),dateAddExpense,year,month,day);
                dialog.show();
            }
        });
        dateAddExpense =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateAdd=dayOfMonth+"."+month+"."+year;
                addDateExpense.setText(dateAdd);
            }
        };

    }

    private void forSpinner() {
        allArrayAddExpenseFregment = new AllArray();
        allArrays_AddExpenseeFregment=allArrayAddExpenseFregment.getExpenseName();
        monthAdapterAddExpenseFregment=new MonthAdapter(getContext(),allArrays_AddExpenseeFregment);
        spinnerCategoryAddExpense.setAdapter(monthAdapterAddExpenseFregment);

        spinnerCategoryAddExpense.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void findId(View addExpenseView) {

        tooberBackArrowAddExpense=addExpenseView.findViewById(R.id.toober_back_arrow);
        spinnerCategoryAddExpense=addExpenseView.findViewById(R.id.incomeCategorySpinner);
        addExpenseAmountET=addExpenseView.findViewById(R.id.addAmount);
        saveButtonAddAddExpense=addExpenseView.findViewById(R.id.save_button);
        addDateExpense = addExpenseView.findViewById(R.id.addDateExpense);
        addTimeExpense = addExpenseView.findViewById(R.id.addTimeExpense);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            helper=new DatabaseOpenHelper(context);
            }



    public void loadFragment(Fragment fragment) {
        fragmentManagerAddExpenseFragment = getFragmentManager();
        fragmentManagerAddExpenseFragment.popBackStackImmediate();   //------something fishy,fragment is changing on its own.why men why?????---------//
        fragmentTransactionAddExpenseFragment = fragmentManagerAddExpenseFragment.beginTransaction();
        fragmentTransactionAddExpenseFragment.replace(R.id.main_frame_container, fragment);
        fragmentTransactionAddExpenseFragment.addToBackStack(null);
        fragmentTransactionAddExpenseFragment.commit();

    }

        public void insert(View view){
            String transactionType="Expense";
            String catagory=categoryBySpinner;
            String amount= addExpenseAmountET.getText().toString();
            int icon=categoryIconBySpinner;
            String date=dateAdd;
            String time=timeAdd;
            long id=helper.addExpenses(transactionType,catagory,amount,time,date,icon);

            Toast.makeText(getContext(), "Id is "+id, Toast.LENGTH_SHORT).show();
                    }



}
