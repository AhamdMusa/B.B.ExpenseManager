package com.brainybrains.expensesmanager.Spinner_Classes;

import com.brainybrains.expensesmanager.R;

import java.util.ArrayList;

public class AllArray {

    private int Icon;
    private String ItemName;

    public AllArray(int icon, String itemName) {
        Icon = icon;
        ItemName = itemName;
    }

    public AllArray() {

    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public ArrayList<AllArray> getMonthName(){
            ArrayList<AllArray> monthNames=new ArrayList<>();
        monthNames.add(new AllArray(R.drawable.month_icon,"January"));
        monthNames.add(new AllArray(R.drawable.month_icon,"February"));
        monthNames.add(new AllArray(R.drawable.month_icon,"March"));
        monthNames.add(new AllArray(R.drawable.month_icon,"April"));
        monthNames.add(new AllArray(R.drawable.month_icon,"May"));
        monthNames.add(new AllArray(R.drawable.month_icon,"June"));
        monthNames.add(new AllArray(R.drawable.month_icon,"July"));
        monthNames.add(new AllArray(R.drawable.month_icon,"August"));
        monthNames.add(new AllArray(R.drawable.month_icon,"September"));
        monthNames.add(new AllArray(R.drawable.month_icon,"October"));
        monthNames.add(new AllArray(R.drawable.month_icon,"November"));
        monthNames.add(new AllArray(R.drawable.month_icon,"December"));
            return monthNames;
    }

    public ArrayList<AllArray> getExpenseName(){
        ArrayList<AllArray> epenseName=new ArrayList<>();
        epenseName.add(new AllArray(R.drawable.food,"Food"));
        epenseName.add(new AllArray(R.drawable.clothes,"Clothes"));
        epenseName.add(new AllArray(R.drawable.mortgage,"Mortgage"));
        epenseName.add(new AllArray(R.drawable.health,"Health"));
        epenseName.add(new AllArray(R.drawable.fun,"Fun"));
        epenseName.add(new AllArray(R.drawable.education,"Education"));
        epenseName.add(new AllArray(R.drawable.utilities,"Utilities"));
        epenseName.add(new AllArray(R.drawable.retirement,"Retirement"));
        epenseName.add(new AllArray(R.drawable.gym,"Gym"));
        epenseName.add(new AllArray(R.drawable.pizza,"Pizza"));
        return epenseName;
    }

    public ArrayList<AllArray> getIncomeName(){
        ArrayList<AllArray> incomeName=new ArrayList<>();
        incomeName.add(new AllArray(R.drawable.salary,"Salary"));
        incomeName.add(new AllArray(R.drawable.refund,"Refund"));
        incomeName.add(new AllArray(R.drawable.sale,"Sale"));
        incomeName.add(new AllArray(R.drawable.savings,"Savings"));
        return incomeName;
    }

    public ArrayList<AllArray> getPaymentCatagory(){
        ArrayList<AllArray> paymentCatagory=new ArrayList<>();
        paymentCatagory.add(new AllArray(R.drawable.cash,"Cash"));
        paymentCatagory.add(new AllArray(R.drawable.cheque,"Cheque"));
        paymentCatagory.add(new AllArray(R.drawable.debit_card,"Debit Card"));
        return paymentCatagory;
    }




}
