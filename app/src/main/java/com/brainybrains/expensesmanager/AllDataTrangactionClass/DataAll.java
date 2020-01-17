package com.brainybrains.expensesmanager.AllDataTrangactionClass;

public class DataAll {

    private int id;
    private String amount;
    private int icon;
    private String transactionType;
    private String catagory;
    private String month;
    private String paymentType;
    private String time;
    private String date;

    public DataAll(int id, String amount, int icon, String catagory, String time, String date) {
        this.id = id;
        this.amount = amount;
        this.icon = icon;
        this.catagory = catagory;
        this.time = time;
        this.date = date;
    }

    public DataAll(String amount, int icon, String transactionType, String catagory, String time, String date) {
        this.amount = amount;
        this.icon = icon;
        this.transactionType = transactionType;
        this.catagory = catagory;
        this.time = time;
        this.date = date;
    }  //for Add

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


}
