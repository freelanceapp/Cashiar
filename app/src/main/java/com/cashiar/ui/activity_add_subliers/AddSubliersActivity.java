package com.cashiar.ui.activity_add_subliers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cashiar.R;
import com.cashiar.databinding.ActivityAddSubliersBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AddCustomerModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_add_subliers_mvp.ActivityAddSubliersPresenter;
import com.cashiar.mvp.activity_add_subliers_mvp.AddSubliersActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;

import io.paperdb.Paper;

public class AddSubliersActivity extends AppCompatActivity implements AddSubliersActivityView {
    private ActivityAddSubliersBinding binding;


    private AddCustomerModel model;
    private ActivityAddSubliersPresenter presenter;
    private Preferences preferences;
    private UserModel userModel;
    private ProgressDialog dialog;
    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_subliers);
        initView();

    }

    private void initView() {
        model = new AddCustomerModel();
        preferences= Preferences.getInstance();
        userModel=preferences.getUserData(this);
        binding.setModel(model);
        presenter = new ActivityAddSubliersPresenter(this, this);


        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkData(model,userModel);
            }
        });
        binding.llBack.setOnClickListener(view -> {
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        presenter.backPress();
    }

    @Override
    public void onFinished() {
        finish();
    }
    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onLoad() {
        dialog = Common.createProgressDialog(this, getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onFinishload() {
        dialog.dismiss();
    }




    @Override
    public void onSuccess() {
        finish();
    }

}
