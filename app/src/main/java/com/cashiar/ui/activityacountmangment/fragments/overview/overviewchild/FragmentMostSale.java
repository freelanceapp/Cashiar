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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cashiar.R;
import com.cashiar.adapters.MostSellAdapter;
import com.cashiar.databinding.FragmentEarnSaleProductBinding;
import com.cashiar.databinding.FragmentMostSaleBinding;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.SettingModel;
import com.cashiar.models.SingleProductModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.fragment_most_sale_product_mvp.EarnSaleFragmentView;
import com.cashiar.mvp.fragment_most_sale_product_mvp.FragmentEarnSalePresenter;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.ui.activityacountmangment.AccountMangmentActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentMostSale extends Fragment implements EarnSaleFragmentView {
    private AccountMangmentActivity activity;
    private FragmentMostSaleBinding binding;
    private Preferences preferences;
    private UserModel userModel;

    private FragmentEarnSalePresenter presenter;
    private ProgressDialog dialog;
    private List<SingleProductModel> today;
    private List<SingleProductModel> yesterday;
    private List<SingleProductModel> this_month;
    private List<SingleProductModel> last_month;
    private List<SingleProductModel> all;
    private MostSellAdapter todayadapter;
    private MostSellAdapter yesterdayadapter;
    private MostSellAdapter this_monthadapter;
    private MostSellAdapter last_monthadapter;
    private MostSellAdapter alladapter;

    public static FragmentMostSale newInstance() {
        return new FragmentMostSale();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_most_sale, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        today = new ArrayList<>();
        yesterday = new ArrayList<>();
        this_month = new ArrayList<>();
        last_month = new ArrayList<>();
        all = new ArrayList<>();
        preferences = Preferences.getInstance();
        activity = (AccountMangmentActivity) getActivity();
        presenter = new FragmentEarnSalePresenter(this, activity);
        userModel = preferences.getUserData(activity);
        todayadapter = new MostSellAdapter(activity, today);
        yesterdayadapter = new MostSellAdapter(activity, yesterday);
        this_monthadapter = new MostSellAdapter(activity, this_month);
        last_monthadapter = new MostSellAdapter(activity, last_month);
        alladapter = new MostSellAdapter(activity, all);
        binding.recViewday.setLayoutManager(new LinearLayoutManager(activity));
        binding.recViewy.setLayoutManager(new LinearLayoutManager(activity));
        binding.recViewthismonth.setLayoutManager(new LinearLayoutManager(activity));
        binding.recViewlmonth.setLayoutManager(new LinearLayoutManager(activity));
        binding.recViewall.setLayoutManager(new LinearLayoutManager(activity));
        binding.recViewday.setAdapter(todayadapter);
        binding.recViewy.setAdapter(yesterdayadapter);
        binding.recViewthismonth.setAdapter(this_monthadapter);
        binding.recViewlmonth.setAdapter(last_monthadapter);
        binding.recViewall.setAdapter(alladapter);

        presenter.getMostSale(userModel);
        binding.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayout1.isExpanded()) {
                    binding.expandLayout1.collapse(true);
                } else {
                    binding.expandLayout1.expand(true);
                }
                binding.expandLayout2.collapse(true);
                binding.expandLayout3.collapse(true);
                binding.expandLayout4.collapse(true);
                binding.expandLayout5.collapse(true);

            }
        });
        binding.lll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayout2.isExpanded()) {
                    binding.expandLayout2.collapse(true);
                } else {
                    binding.expandLayout2.expand(true);
                }
                binding.expandLayout1.collapse(true);
                binding.expandLayout3.collapse(true);
                binding.expandLayout4.collapse(true);
                binding.expandLayout5.collapse(true);

            }
        });
        binding.llll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayout3.isExpanded()) {
                    binding.expandLayout3.collapse(true);
                } else {
                    binding.expandLayout3.expand(true);
                }
                binding.expandLayout2.collapse(true);
                binding.expandLayout1.collapse(true);
                binding.expandLayout4.collapse(true);
                binding.expandLayout5.collapse(true);

            }
        });
        binding.lllll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayout4.isExpanded()) {
                    binding.expandLayout4.collapse(true);
                } else {
                    binding.expandLayout4.expand(true);
                }
                binding.expandLayout2.collapse(true);
                binding.expandLayout3.collapse(true);
                binding.expandLayout1.collapse(true);
                binding.expandLayout5.collapse(true);

            }
        });
        binding.llllll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayout5.isExpanded()) {
                    binding.expandLayout5.collapse(true);
                } else {
                    binding.expandLayout5.expand(true);
                }
                binding.expandLayout2.collapse(true);
                binding.expandLayout3.collapse(true);
                binding.expandLayout4.collapse(true);
                binding.expandLayout1.collapse(true);

            }
        });
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
        today.clear();
        yesterday.clear();
        this_month.clear();
        last_month.clear();
        all.clear();
        today.addAll(body.getToday());
        yesterday.addAll(body.getYesterday());
        this_month.addAll(body.getThis_month());
        last_month.addAll(body.getLast_month());
        all.addAll(body.getAll());
        todayadapter.notifyDataSetChanged();
        yesterdayadapter.notifyDataSetChanged();
        this_monthadapter.notifyDataSetChanged();
        last_monthadapter.notifyDataSetChanged();
        alladapter.notifyDataSetChanged();

    }


}
