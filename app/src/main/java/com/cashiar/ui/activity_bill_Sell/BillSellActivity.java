package com.cashiar.ui.activity_bill_Sell;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cashiar.R;
import com.cashiar.adapters.ProductsSellAdapter;
import com.cashiar.adapters.SpinnerCustomerAdapter;
import com.cashiar.databinding.ActivityBillSellBinding;
import com.cashiar.databinding.ActivityPaymentSellBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AllCustomersModel;
import com.cashiar.models.BillModel;
import com.cashiar.models.CreateOrderModel;
import com.cashiar.models.ItemCartModel;
import com.cashiar.models.PaymentModel;
import com.cashiar.models.SingleCustomerSuplliersModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_bill_sell_mvp.ActivitBillSellPresenter;
import com.cashiar.mvp.activity_bill_sell_mvp.BillSellActivityView;
import com.cashiar.mvp.activity_payment_sell_mvp.ActivityPAymentSellPresenter;
import com.cashiar.mvp.activity_payment_sell_mvp.PaymentSellActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.tags.Tags;
import com.cashiar.ui.activity_add_Customer.AddCustomerActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class BillSellActivity extends AppCompatActivity implements BillSellActivityView {
    private ActivityBillSellBinding binding;
    private ActivitBillSellPresenter presenter;
    private String lang;
    private UserModel userModel;
    private ProgressDialog dialog;
    private BillModel billModel;
    private CreateOrderModel createOrderModel;
    private double paid;
    private String taxamount = "0";
    private Preferences preferences;
    private List<ItemCartModel> itemCartModelList;
    private ProductsSellAdapter productsSellAdapter;
private String currecny="";
    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bill_sell);
        getdatafromintent();
        initView();
    }

    private void getdatafromintent() {
        Intent intent = getIntent();
        if (intent != null) {
            createOrderModel = (CreateOrderModel) intent.getSerializableExtra("data");
            billModel = (BillModel) intent.getSerializableExtra("databill");
        }
    }


    private void initView() {
        itemCartModelList = new ArrayList<>();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        binding.setModel(createOrderModel);
        binding.setLogo("");
        binding.setCurrency("");
        binding.setAddress("");
        binding.setTax("");
        presenter = new ActivitBillSellPresenter(this, this);
        presenter.getprofile(userModel);
        binding.setBillmodel(billModel);

        binding.setTotal((createOrderModel.getTotal_price() - Double.parseDouble(taxamount) + createOrderModel.getDiscount_value()) + "");
        productsSellAdapter = new ProductsSellAdapter(this, itemCartModelList,currecny);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(productsSellAdapter);
        itemCartModelList.addAll(createOrderModel.getOrder_details());
        productsSellAdapter.notifyDataSetChanged();
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });


        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
    public void onLoad() {
        if (dialog == null) {
            dialog = Common.createProgressDialog(this, getString(R.string.wait));
            dialog.setCancelable(false);
        } else {
            dialog.dismiss();
        }

        dialog.show();
    }

    @Override
    public void onFinishload() {
        dialog.dismiss();
    }


    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onCustomers() {
        Intent intent = new Intent(this, AddCustomerActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onsucess() {
        preferences.clearCart(this);
        finish();

    }

    @Override
    public void onprofileload(UserModel body) {
        this.taxamount = body.getTax_amount();
        currecny=body.getCurrency();
        binding.setCurrency(currecny);
        binding.setAddress(body.getAddress());
        binding.setTax(taxamount);
        binding.setTotal((createOrderModel.getTotal_price() - Double.parseDouble(taxamount) + createOrderModel.getDiscount_value()) + "");
        if (body.getLogo() != null) {
            binding.setLogo(body.getLogo());
        }

    }

    //    private void takeScreenshot() {
//        Date now = new Date();
//        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
//
//        try {
//            // image naming and path  to include sd card  appending name you choose for file
//            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpeg";
//
//            // create bitmap screen capture
//            ScrollView v1 = (ScrollView) getWindow().getDecorView().findViewById(R.id.scrollView);
//            v1.setDrawingCacheEnabled(true);
//            Bitmap bitmap = getBitmapFromView(v1, v1.getChildAt(0).getHeight(), v1.getChildAt(0).getWidth());
//            v1.setDrawingCacheEnabled(false);
//
//            File imageFile = new File(mPath);
//
//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();
//
//            //setting screenshot in imageview
//            String filePath = imageFile.getPath();
//
//            //   Bitmap ssbitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
//
//        } catch (Throwable e) {
//            // Several error may come out with file handling or DOM
//            e.printStackTrace();
//        }
//    }
//    private Bitmap getBitmapFromView(View view, int height, int width) {
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        Drawable bgDrawable = view.getBackground();
//        if (bgDrawable != null)
//            bgDrawable.draw(canvas);
//        else
//            canvas.drawColor(Color.WHITE);
//        view.draw(canvas);
//        return bitmap;
//    }
    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getprofile(userModel);
    }
}