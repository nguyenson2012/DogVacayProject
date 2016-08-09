package com.example.asus.dogvacayproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.asus.dogvacayproject.R;
import com.example.asus.dogvacayproject.view.NonSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 8/8/2016.
 */
public class FragmentBrowse extends Fragment {
    PagerSlidingTabStrip pagerSlidingTabStrip;
    NonSwipeViewPager viewPager;
    ViewPagerAdapter adapter;
    TextView tvPlace,tvDate;
    public FragmentBrowse(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutBrowse=inflater.inflate(R.layout.fragment_browse,container,false);
        setupView(layoutBrowse);
        setupViewPager(viewPager);
        return layoutBrowse;
    }

    private void setupViewPager(NonSwipeViewPager viewPager) {
        adapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new FragmentBrowseList(),"List");
        adapter.addFragment(new FragmentBrowseMap(),"Map");
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
    }

    private void setupView(View layoutBrowse) {
        pagerSlidingTabStrip=(PagerSlidingTabStrip)layoutBrowse.findViewById(R.id.pager_tab_strip);
        viewPager=(NonSwipeViewPager)layoutBrowse.findViewById(R.id.pager_sitter_and_map);
        tvPlace=(TextView)layoutBrowse.findViewById(R.id.tv_place_text);
        tvDate=(TextView)layoutBrowse.findViewById(R.id.tv_dates_text);
        tvPlace.setText(getResources().getString(R.string.default_choose_place));
        tvDate.setText(getResources().getString(R.string.default_choose_date));
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment,String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
