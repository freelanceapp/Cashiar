package com.cashiar.ui.activityacountmangment.fragments.aggerate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.cashiar.R;
import com.cashiar.adapters.ViewPagerAdapter;
import com.cashiar.databinding.FragmentAggregateDataBinding;
import com.cashiar.preferences.Preferences;
import com.cashiar.ui.activityacountmangment.AccountMangmentActivity;
import com.cashiar.ui.activityacountmangment.fragments.aggerate.aggerate_child.FragmentPurchases;
import com.cashiar.ui.activityacountmangment.fragments.aggerate.aggerate_child.FragmentSales;
import com.cashiar.ui.activityacountmangment.fragments.overview.FragmentOverView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FragmentAggregateData extends Fragment {
    private AccountMangmentActivity activity;
    private FragmentAggregateDataBinding binding;
    private Preferences preferences;

    private List<Fragment> fragmentList;
    private List<String> titles;
    private ViewPagerAdapter adapter;
    private int pos;
    private String type;
    private String type1,type2;

    public static FragmentAggregateData newInstance() {
        return new FragmentAggregateData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_aggregate_data, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {

        preferences = Preferences.getInstance();
        activity = (AccountMangmentActivity) getActivity();


        fragmentList = new ArrayList<>();
        titles = new ArrayList<>();
        binding.tab.setupWithViewPager(binding.pager);
        addFragments_Titles();
        binding.pager.setOffscreenPageLimit(fragmentList.size());

        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragments(fragmentList);
        adapter.addTitles(titles);
        binding.pager.setAdapter(adapter);
        type1=activity.getResources().getString(R.string.day);
        type2=activity.getResources().getString(R.string.day);

        binding.tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pos = tab.getPosition();
                if(pos==0){
                binding.tv.setText(type1);}

                else {
                    binding.tv.setText(type2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.fl.setOnClickListener(view -> openSheet());
        binding.tvday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvday.setText(activity.getResources().getString(R.string.day));
                closeSheet();
                type = "day";
                getdata();
                if(pos==0){
                    type1=type;
                }
                else {
                    type2=type;
                }
            }
        });
        binding.tvthismonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.this_month));
                closeSheet();
                type = "this_month";
                getdata();
                if(pos==0){
                    type1=activity.getResources().getString(R.string.this_month);
                }
                else {
                    type2=activity.getResources().getString(R.string.this_month);
                }
            }
        });
        binding.tvlsevday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.last_seven_day));
                closeSheet();
                type = "last7days";
                getdata();
                if(pos==0){
                    type1=activity.getResources().getString(R.string.last_seven_day);
                }
                else {
                    type2=activity.getResources().getString(R.string.last_seven_day);
                }
            }
        });
        binding.tvextentofwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.extent_of_work));
                closeSheet();
                type = "all";
                getdata();
                if(pos==0){
                    type1=activity.getResources().getString(R.string.extent_of_work);
                }
                else {
                    type2=activity.getResources().getString(R.string.extent_of_work);
                }
            }
        });
        binding.tvlmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.last_month));
                closeSheet();
                type = "last_month";
                getdata();
                if(pos==0){
                    type1=activity.getResources().getString(R.string.last_month);
                }
                else {
                    type2=activity.getResources().getString(R.string.last_month);
                }
            }
        });
        binding.tvlthrityday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.last_thirty_day));
                closeSheet();
                type = "last30days";
                getdata();
                if(pos==0){
                    type1=activity.getResources().getString(R.string.last_thirty_day);
                }
                else {
                    type2=activity.getResources().getString(R.string.last_thirty_day);
                }

            }
        });
        binding.tvyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.yesterday));
                closeSheet();
                type = "yesterday";
                getdata();
                if(pos==0){
                    type1=activity.getResources().getString(R.string.yesterday);
                }
                else {
                    type2=activity.getResources().getString(R.string.yesterday);
                }

            }
        });
        binding.tvcustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.custom_history));
                closeSheet();
                type = "custom";
                getdata();
                if(pos==0){
                    type1=activity.getResources().getString(R.string.custom_history);
                }
                else {
                    type2=activity.getResources().getString(R.string.custom_history);
                }
            }
        });
    }

    private void getdata() {
        if (pos == 0) {
            FragmentPurchases fragmentPurchases = (FragmentPurchases) fragmentList.get(pos);
            fragmentPurchases.getdata(type);
        }
        else {
            FragmentSales fragmentPurchases = (FragmentSales) fragmentList.get(pos);
            fragmentPurchases.getdata(type);
        }
    }

    private void addFragments_Titles() {
        fragmentList.add(FragmentPurchases.newInstance());
        fragmentList.add(FragmentSales.newInstance());


        titles.add(getString(R.string.purchases));
        titles.add(getString(R.string.sales));


    }

    private void openSheet() {
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.slide_up);


        binding.flfilter.clearAnimation();
        binding.flfilter.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.flfilter.setVisibility(View.VISIBLE);


            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void closeSheet() {
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.slide_down);


        binding.flfilter.clearAnimation();
        binding.flfilter.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                binding.flfilter.setVisibility(View.GONE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}
