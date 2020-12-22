package com.cashiar.mvp.activity_products_bill_Sell_mvp;

import android.content.Context;

import com.cashiar.models.UserModel;
import com.cashiar.preferences.Preferences;

public class ActivityProductsbillSellPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private ProductsBillSellActivityView view;
    private Context context;

    public ActivityProductsbillSellPresenter(ProductsBillSellActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void backPress() {

        view.onFinished();


    }

    public void Cart() {

        view.onCart();


    }

}
