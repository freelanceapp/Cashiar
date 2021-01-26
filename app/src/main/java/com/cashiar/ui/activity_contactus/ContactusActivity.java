package com.cashiar.ui.activity_contactus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.cashiar.R;
import com.cashiar.databinding.ActivityContactusBinding;
import com.cashiar.interfaces.Listeners;
import com.cashiar.language.Language;
import com.cashiar.models.SettingModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_contactus_mvp.ActivityContactusPresenter;
import com.cashiar.mvp.activity_contactus_mvp.ActivityContactusView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;

import io.paperdb.Paper;

public class ContactusActivity extends AppCompatActivity implements ActivityContactusView, Listeners.ContactActions {
    private ActivityContactusBinding binding;

    private ActivityContactusPresenter presenter;
    private Preferences preferences;
    private String lang;
    private ProgressDialog dialog2;
    private UserModel userModel;
    private SettingModel setting;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contactus);
        initView();

    }


    private void initView() {
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        Paper.init(this);

        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);

        presenter = new ActivityContactusPresenter(this, this);

        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        presenter.getSetting();

    }


    @Override
    public void onLoad() {
        dialog2 = Common.createProgressDialog(this, getString(R.string.wait));
        dialog2.setCancelable(false);
        dialog2.show();
    }

    @Override
    public void onFinishload() {
        dialog2.dismiss();
    }

    @Override
    public void onContactVaild() {
        finish();

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onnotconnect(String msg) {
        Toast.makeText(ContactusActivity.this, msg, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onFailed() {
        Toast.makeText(ContactusActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServer() {
        Toast.makeText(ContactusActivity.this, getString(R.string.server_error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onsetting(SettingModel body) {
        this.setting = body;

        binding.setModel(body);


    }

    @Override
    public void ViewSocial(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        startActivity(intent);

    }

    @Override
    public void email() {
        presenter.open(setting.getGoogle_plus());

    }

    @Override
    public void facebook() {
        presenter.open(setting.getFacebook());

    }

    @Override
    public void whats() {
        presenter.open("https://api.whatsapp.com/send?phone=" + setting.getWhatsapp());

    }

    @Override
    public void twitter() {
        presenter.open(setting.getTwitter());

    }

    @Override
    public void instegram() {
        presenter.open(setting.getInstagram());

    }

    @Override
    public void telegram() {
        presenter.open(setting.getTelegram());

    }

    @Override
    public void youtube() {
        presenter.open(setting.getYoutube());

    }
}