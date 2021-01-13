package com.cashiar.mvp.fragment_most_sale_product_mvp;

import com.cashiar.models.AllProductsModel;
import com.cashiar.models.SettingModel;

public interface EarnSaleFragmentView {
    void onFailed(String msg);

    void onLoad();

    void onFinishload();




    void onpurchase(SettingModel body);

    void mostsale(AllProductsModel body);
}
