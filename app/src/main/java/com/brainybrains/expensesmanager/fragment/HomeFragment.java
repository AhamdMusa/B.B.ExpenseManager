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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.brainybrains.expensesmanager.Adapter.HomeAdapter;
import com.brainybrains.expensesmanager.Adapter.ListViewDaapter;
import com.brainybrains.expensesmanager.AllDataTrangactionClass.DataAll;
import com.brainybrains.expensesmanager.DataBase.DatabaseOpenHelper;
import com.brainybrains.expensesmanager.R;
import com.brainybrains.expensesmanager.Spinner_Classes.AllArray;
import com.brainybrains.expensesmanager.Spinner_Classes.MonthAdapter;
import com.yangp.ypwaveview.YPWaveView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private Context context;
    private Cursor cursor;
    private TextView incomeHomFregment, expensesHomFregment, balanceHomFregment;
    private RecyclerView HomFregmentRV;
    private ListView HomFregmentlistView;
    private ImageView addButtonHomFregment, subtractButtonHomFregment;
    private Spinner spinnerHomeFregment;
    private FragmentManager fragmentManagerHomeFragment;
    private FragmentTransaction fragmentTransactionHomeFragment;
    private LinearLayout incomeLayoutHomeFregment,expensesLayoutHomeFregment;
    private AllArray allArrayHomeFregment;
    private MonthAdapter monthAdapterHomeFregment;
    private ArrayList<AllArray> allArrays_HomeFregment;
    private YPWaveView ypWaveViewHomeFragment;
    private HomeAdapter homeAdapter;
    private ArrayList<DataAll> allDatas;
    private DatabaseOpenHelper helper;
    private ListViewDaapter listViewDaapter;
    //----for calculations---------//
    private int amountInt,income,expenses;
    private float persent;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View homeFregmentView= inflater.inflate(R.layout.fragment_home, container, false);

        findId(homeFregmentView);
        //spinner();
        handelshowData();
        listener();
        balanceHomFregment.setText(String.valueOf(amountInt));
        incomeHomFregment.setText(String.valueOf(income));
        expensesHomFregment.setText(String.valueOf(expenses));




        ypWaveViewHomeFragment.setMax(income);
        ypWaveViewHomeFragment.setProgress(amountInt);
       // ypWaveViewHomeFragment.startAnimation();



        return homeFregmentView;
    }

    private void spinner() {
        allArrayHomeFregment = new AllArray();
        allArrays_HomeFregment=allArrayHomeFregment.getMonthName();
        monthAdapterHomeFregment=new MonthAdapter(context,allArrays_HomeFregment);
        spinnerHomeFregment.setAdapter(monthAdapterHomeFregment);
    }

    private void findId(View homeFregmentView) {
        //spinnerHomeFregment=homeFregmentView.findViewById(R.id.spinner_Home_Fregment);            //----spinner is off---------/
        incomeHomFregment = homeFregmentView.findViewById(R.id.incomehome_fragment);
        expensesHomFregment = homeFregmentView.findViewById(R.id.expenseshome_fragment);
        balanceHomFregment = homeFregmentView.findViewById(R.id.balancehome_fragment);
        HomFregmentRV = (RecyclerView)homeFregmentView.findViewById(R.id.home_fragmentRV);
        // HomFregmentlistView=homeFregmentView.findViewById(R.id.home_fragmentRV);       //-------------visvarsa-/
        HomFregmentRV.setNestedScrollingEnabled(false);
        addButtonHomFregment = homeFregmentView.findViewById(R.id.add_button_home_fragment);
        subtractButtonHomFregment = homeFregmentView.findViewById(R.id.subtract_button_home_fragment);
        incomeLayoutHomeFregment= homeFregmentView.findViewById(R.id.incomTitelLayouthome_fragment);
        expensesLayoutHomeFregment= homeFregmentView.findViewById(R.id.expensesTitelLayouthome_fragment);
        ypWaveViewHomeFragment=homeFregmentView.findViewById(R.id.waveViewHomeFregment);

    }

    private void handelshowData() {
        allDatas = new ArrayList<>();
        helper = new DatabaseOpenHelper(context);
        // listViewDaapter =new ListViewDaapter(getContext(),allDatas);//-------forLV-//
        //  HomFregmentlistView.setAdapter(listViewDaapter);//-------forLV-//
        homeAdapter = new HomeAdapter(context,allDatas);
        HomFregmentRV.setLayoutManager(new LinearLayoutManager(getContext()));
        HomFregmentRV.setAdapter(homeAdapter);

        DatabaseOpenHelper helper=new DatabaseOpenHelper(context);
       Cursor cursor =helper.getData();

        if(cursor.getCount() >= 1){
            amountInt=0;
            expenses=0;
            income=0;
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
                int icon=cursor.getInt(cursor.getColumnIndex(helper.COL_ICON));
                String catagory = cursor.getString(cursor.getColumnIndex(helper.COL_CATAGORY));
                String date = cursor.getString(cursor.getColumnIndex(helper.COL_DATE));
                String time = cursor.getString(cursor.getColumnIndex(helper.COL_TIME));
                String amount = cursor.getString(cursor.getColumnIndex(helper.COL_AMOUNT));
                String type=cursor.getString(cursor.getColumnIndex(helper.COL_TYPE));

                if (type.equals("Income"))
                     income=income+Integer.parseInt(amount);
                   else
                       expenses=expenses+Integer.parseInt(amount);

                amountInt=income-expenses;

                allDatas.add(new DataAll(id,amount,icon,catagory,time,date));
             homeAdapter.notifyDataSetChanged();
//            listViewDaapter.notifyDataSetChanged();

            }}
    }

    private void listener() {

        addButtonHomFregment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddIncome addIncome = new AddIncome();
                loadFragment(addIncome);

            }
        });
        subtractButtonHomFregment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpenses addExpenses = new AddExpenses();
                loadFragment(addExpenses);
            }
        });

        incomeHomFregment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowIncome showIncome = new ShowIncome();
                loadFragment(showIncome);
            }
        });

        expensesHomFregment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowExpenses showExpenses = new ShowExpenses();
                loadFragment(showExpenses);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;

    }
    public HomeFragment() {
        // Required empty public constructor
    }

    public void loadFragment(Fragment fragment) {
        fragmentManagerHomeFragment = getFragmentManager();
        fragmentTransactionHomeFragment = fragmentManagerHomeFragment.beginTransaction();
        fragmentTransactionHomeFragment.replace(R.id.main_frame_container, fragment);
        fragmentTransactionHomeFragment.addToBackStack(null);
        fragmentTransactionHomeFragment.commit();

    }
}
