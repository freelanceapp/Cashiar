package com.cashiar.mvp.fragment_purchases_mvp;

import com.cashiar.models.SettingModel;

public interface PurchasesFragmentView {
    void onFailed(String msg);

    void onLoad();

    void onFinishload();




    void onpurchase(SettingModel body);

    void onDateSelected(String date,int i);
}
