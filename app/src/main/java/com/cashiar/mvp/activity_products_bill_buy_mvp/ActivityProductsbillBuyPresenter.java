package com.cashiar.mvp.activity_products_bill_buy_mvp;

import android.content.Context;

import com.cashiar.models.UserModel;
import com.cashiar.preferences.Preferences;

public class ActivityProductsbillBuyPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private ProductsBillBuyActivityView view;
    private Context context;

    public ActivityProductsbillBuyPresenter(ProductsBillBuyActivityView view, Context context) {
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
