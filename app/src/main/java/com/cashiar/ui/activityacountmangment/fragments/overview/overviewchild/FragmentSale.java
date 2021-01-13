package com.cashiar.ui.activityacountmangment.fragments.overview.overviewchild;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cashiar.R;
import com.cashiar.databinding.FragmentEarnSaleProductBinding;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.SettingModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.fragment_most_sale_product_mvp.EarnSaleFragmentView;
import com.cashiar.mvp.fragment_most_sale_product_mvp.FragmentEarnSalePresenter;
import com.cashiar.mvp.fragment_purchases_mvp.FragmentPurchasesPresenter;
import com.cashiar.mvp.fragment_purchases_mvp.PurchasesFragmentView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.ui.activityacountmangment.AccountMangmentActivity;

public class FragmentSale extends Fragment implements EarnSaleFragmentView {
    private AccountMangmentActivity activity;
    private FragmentEarnSaleProductBinding binding;
    private Preferences preferences;
    private UserModel userModel;

    private FragmentEarnSalePresenter presenter;
    private ProgressDialog dialog;

    public static FragmentSale newInstance() {
        return new FragmentSale();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_earn_sale_product, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {

        preferences = Preferences.getInstance();
        activity = (AccountMangmentActivity) getActivity();
        presenter = new FragmentEarnSalePresenter(this, activity);
        userModel = preferences.getUserData(activity);
        presenter.getsale(userModel);

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoad() {
        dialog = Common.createProgressDialog(activity, getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onFinishload() {
        dialog.dismiss();
    }

    @Override
    public void onpurchase(SettingModel body) {
        binding.setModel(body);
    }

    @Override
    public void mostsale(AllProductsModel body) {

    }


}
