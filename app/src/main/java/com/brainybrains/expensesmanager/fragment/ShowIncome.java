package com.brainybrains.expensesmanager.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import com.brainybrains.expensesmanager.R;
import com.brainybrains.expensesmanager.Spinner_Classes.AllArray;
import com.brainybrains.expensesmanager.Spinner_Classes.MonthAdapter;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

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

    public ShowIncome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View showIncomeView = inflater.inflate(R.layout.fragment_show_income, container, false);

        tooberBackArrowshowIncome= showIncomeView.findViewById(R.id.show_income_backbutton);
        showIncomeFilterByMonth = showIncomeView.findViewById(R.id.show_income_mounth_Spinner);
        showIncomePieChart = showIncomeView.findViewById(R.id.show_income_PieChart);
        showIncomeRV = showIncomeView.findViewById(R.id.incomee_show_RV);
        addIncomeOnShowIncome = showIncomeView.findViewById(R.id.add_button_show_income);

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
    public void loadFragment(Fragment fragment) {
        fragmentManagerShowIncomeFragment = getFragmentManager();
        fragmentTransactionShowIncomeFragment = fragmentManagerShowIncomeFragment.beginTransaction();
        fragmentTransactionShowIncomeFragment.replace(R.id.main_frame_container, fragment);
        fragmentTransactionShowIncomeFragment.addToBackStack(null);
        fragmentTransactionShowIncomeFragment.commit();

    }

}
