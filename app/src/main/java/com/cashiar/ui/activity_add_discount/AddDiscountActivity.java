package com.cashiar.ui.activity_add_discount;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cashiar.R;
import com.cashiar.databinding.ActivityAddDiscountBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AddDiscountModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_add_discount_mvp.ActivityAddDiscountPresenter;
import com.cashiar.mvp.activity_add_discount_mvp.AddDiscountActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;

import io.paperdb.Paper;

public class AddDiscountActivity extends AppCompatActivity implements AddDiscountActivityView {
    private ActivityAddDiscountBinding binding;
    private ActivityAddDiscountPresenter presenter;
    private String lang;
    private AddDiscountModel addDiscountModel;
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_discount);
        initView();
    }


    private void initView() {
        preferences=Preferences.getInstance();
        userModel=preferences.getUserData(this);
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });
        presenter = new ActivityAddDiscountPresenter(this, this);

        addDiscountModel = new AddDiscountModel();
        addDiscountModel.setType("pre");

        binding.setModel(addDiscountModel);
        binding.tvpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!addDiscountModel.getType().trim().isEmpty() && addDiscountModel.getType().equals("value")) {
                    binding.tvpre.setBackground(getResources().getDrawable(R.drawable.rounded_white));
                    binding.tvvalue.setBackground(getResources().getDrawable(R.drawable.rounded_gray2));
                    addDiscountModel.setType("pre");
                }
            }
        });
        binding.tvvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!addDiscountModel.getType().trim().isEmpty() && addDiscountModel.getType().equals("pre")) {
                    binding.tvvalue.setBackground(getResources().getDrawable(R.drawable.rounded_white));
                    binding.tvpre.setBackground(getResources().getDrawable(R.drawable.rounded_gray2));
                    addDiscountModel.setType("value");

                }
            }
        });
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkData(addDiscountModel, userModel);
            }
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