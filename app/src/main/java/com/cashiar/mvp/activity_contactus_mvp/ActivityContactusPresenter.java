package com.cashiar.mvp.activity_contactus_mvp;

import android.content.Context;
import android.util.Log;


import com.cashiar.models.SettingModel;
import com.cashiar.remote.Api;

import java.io.IOException;
import com.cashiar.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cashiar.tags.Tags.base_url;


public class ActivityContactusPresenter {
    private Context context;
    private ActivityContactusView view;



    public ActivityContactusPresenter(Context context, ActivityContactusView view) {
        this.context = context;
        this.view = view;

    }





    public void getSetting() {
        view.onLoad();

        Api.getService(base_url).getSetting()
                .enqueue(new Callback<SettingModel>() {
                    @Override
                    public void onResponse(Call<SettingModel> call, Response<SettingModel> response) {
                        view.onFinishload();
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                view.onsetting(response.body());

                            }
                        } else {
                            // Log.e("xxxxx", settingModel.getSettings().getAbout_app_link() + "----");


                            view.onFinishload();
                            try {
                                Log.e("error_code", response.code() + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<SettingModel> call, Throwable t) {
                        try {
                            view.onFinishload();

                            if (t.getMessage() != null) {
                                Log.e("error", t.getMessage() + "__");

                                if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {

                                    view.onFailed(context.getResources().getString(R.string.something));
                                    // Toast.makeText(AboutAppActivity.this, getString(R.string.something), Toast.LENGTH_SHORT).show();
                                } else if (t.getMessage().toLowerCase().contains("socket") || t.getMessage().toLowerCase().contains("canceled")) {
                                } else {
                                    view.onFailed(context.getResources().getString(R.string.failed));

                                    //Toast.makeText(AboutAppActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                                }
                            }


                        } catch (Exception e) {

                        }
                    }
                });
    }

    public void open(String link) {
        view.ViewSocial(link);
    }

}
