package com.cashiar.ui.activityacountmangment.fragments.overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cashiar.R;
import com.cashiar.adapters.ViewPagerAdapter;
import com.cashiar.databinding.FragmentOverviewBinding;
import com.cashiar.preferences.Preferences;
import com.cashiar.ui.activityacountmangment.AccountMangmentActivity;
import com.cashiar.ui.activityacountmangment.fragments.overview.overviewchild.FragmentEarn;
import com.cashiar.ui.activityacountmangment.fragments.overview.overviewchild.FragmentMostSale;
import com.cashiar.ui.activityacountmangment.fragments.overview.overviewchild.FragmentSale;

import java.util.ArrayList;
import java.util.List;

public class FragmentOverView extends Fragment {
    private AccountMangmentActivity activity;
    private FragmentOverviewBinding binding;
    private Preferences preferences;

    private List<Fragment> fragmentList;
    private List<String> titles;
    private ViewPagerAdapter adapter;
    public static FragmentOverView newInstance()
    {
        return new FragmentOverView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview,container,false);
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


    }

    private void addFragments_Titles() {
        fragmentList.add(FragmentMostSale.newInstance());

        fragmentList.add(FragmentEarn.newInstance());

        fragmentList.add(FragmentSale.newInstance());

        titles.add(getString(R.string.most_sale));

        titles.add(getString(R.string.earn));
        titles.add(getString(R.string.sales));


    }

}
