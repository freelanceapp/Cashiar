package com.cashiar.models;

import java.io.Serializable;
import java.util.List;

public class AllProductsModel implements Serializable {
private List<SingleProductModel> data;

    public List<SingleProductModel> getData() {
        return data;
    }
    private List<SingleProductModel> today;
    private List<SingleProductModel> yesterday;
    private List<SingleProductModel> this_month;
    private List<SingleProductModel> last_month;
    private List<SingleProductModel> all;

    public List<SingleProductModel> getToday() {
        return today;
    }

    public List<SingleProductModel> getYesterday() {
        return yesterday;
    }

    public List<SingleProductModel> getThis_month() {
        return this_month;
    }

    public List<SingleProductModel> getLast_month() {
        return last_month;
    }

    public List<SingleProductModel> getAll() {
        return all;
    }
}
