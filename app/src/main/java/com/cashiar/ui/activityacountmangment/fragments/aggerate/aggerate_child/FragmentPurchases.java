package com.cashiar.ui.activityacountmangment.fragments.aggerate.aggerate_child;

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
import com.cashiar.databinding.FragmentPurchasesBinding;
import com.cashiar.models.SettingModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.fragment_purchases_mvp.FragmentPurchasesPresenter;
import com.cashiar.mvp.fragment_purchases_mvp.PurchasesFragmentView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.ui.activityacountmangment.AccountMangmentActivity;

public class FragmentPurchases extends Fragment implements PurchasesFragmentView {
    private AccountMangmentActivity activity;
    private FragmentPurchasesBinding binding;
    private Preferences preferences;
    private String type = "today";
    private UserModel userModel;
    private String str = "", end = "";
    private FragmentPurchasesPresenter presenter;
    private ProgressDialog dialog;

    public static FragmentPurchases newInstance() {
        return new FragmentPurchases();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_purchases, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {

        preferences = Preferences.getInstance();
        activity = (AccountMangmentActivity) getActivity();
        presenter = new FragmentPurchasesPresenter(this, activity, this);
        userModel = preferences.getUserData(activity);
        presenter.getpurchases(userModel, type, str, end);

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
    public void onDateSelected(String date, int type) {
if(type==1){
    str=date;
    presenter.show(activity.getFragmentManager(),2);
}
else {
    end=date;
    presenter.getpurchases(userModel,this.type,str,end);
}

    }

    public void getdata(String type) {

        this.type = type;
        if (type.equals("custom")) {
            presenter.show(activity.getFragmentManager(),1);
        } else {
            presenter.getpurchases(userModel, type, str, end);
        }
    }
}
