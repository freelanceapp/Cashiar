package com.cashiar.models;

import java.io.Serializable;

public class SingleCustomerSuplliersModel implements Serializable {
    private int id;
    private String name;
    private String phone_code;
    private String phone;

    public SingleCustomerSuplliersModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone_code() {
        return phone_code;
    }

    public String getPhone() {
        return phone;
    }
}
