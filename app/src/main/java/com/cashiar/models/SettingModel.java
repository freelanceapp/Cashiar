package com.cashiar.models;

import java.io.Serializable;
import java.util.List;

public class SettingModel implements Serializable {
    private double total_sales;
    private double total_discount_value;
    private double total_sales_after_discount;
    private double total_paid_price;
    private double total_remaining_price;
    private double total_earnings;
    private double total_expense;
    private double total_earnings_after_expense;
    private double rate_of_earnings;
    private double total_purchase;
    private double total_paid_purchase;
    private double total_remaining_purchase;
    private double today;
    private double yesterday;
    private double earningThisMonth;
    private double earningLastMonth;
    private double all;

    public double getTotal_sales() {
        return total_sales;
    }

    public double getTotal_discount_value() {
        return total_discount_value;
    }

    public double getTotal_sales_after_discount() {
        return total_sales_after_discount;
    }

    public double getTotal_paid_price() {
        return total_paid_price;
    }

    public double getTotal_remaining_price() {
        return total_remaining_price;
    }

    public double getTotal_earnings() {
        return total_earnings;
    }

    public double getTotal_expense() {
        return total_expense;
    }

    public double getTotal_earnings_after_expense() {
        return total_earnings_after_expense;
    }

    public double getRate_of_earnings() {
        return rate_of_earnings;
    }

    public double getTotal_purchase() {
        return total_purchase;
    }

    public double getTotal_paid_purchase() {
        return total_paid_purchase;
    }

    public double getTotal_remaining_purchase() {
        return total_remaining_purchase;
    }

    public double getToday() {
        return today;
    }

    public double getYesterday() {
        return yesterday;
    }

    public double getEarningThisMonth() {
        return earningThisMonth;
    }

    public double getEarningLastMonth() {
        return earningLastMonth;
    }

    public double getAll() {
        return all;
    }
}
