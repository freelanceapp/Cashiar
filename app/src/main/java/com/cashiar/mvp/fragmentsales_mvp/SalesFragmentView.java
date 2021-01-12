package com.cashiar.mvp.fragmentsales_mvp;

import com.cashiar.models.SettingModel;

public interface SalesFragmentView {
    void onFailed(String msg);

    void onLoad();

    void onFinishload();




    void onpurchase(SettingModel body);

    void onDateSelected(String date,int i);
}
