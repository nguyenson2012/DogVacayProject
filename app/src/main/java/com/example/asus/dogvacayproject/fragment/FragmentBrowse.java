package com.example.asus.dogvacayproject.fragment;

import android.content.Intent;
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
import com.example.asus.dogvacayproject.activities.ChoosePlaceActivity;
import com.example.asus.dogvacayproject.activities.MainActivity;
import com.example.asus.dogvacayproject.utils.Const;
import com.example.asus.dogvacayproject.view.NonSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 8/8/2016.
 */
public class FragmentBrowse extends Fragment implements View.OnClickListener{
    PagerSlidingTabStrip pagerSlidingTabStrip;
    NonSwipeViewPager viewPager;
    ViewPagerAdapter adapter;
    TextView tvPlace,tvDate;
    String currentLocation,currentService;
    int startDateChoose,endDateChoose;
    public FragmentBrowse(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutBrowse=inflater.inflate(R.layout.fragment_browse,container,false);
        setupView(layoutBrowse);
        setupViewPager(viewPager);
        registerEvent();
        return layoutBrowse;
    }

    private void registerEvent() {
        tvDate.setOnClickListener(this);
        tvPlace.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_place_text:
                Intent intent=new Intent(getActivity(), ChoosePlaceActivity.class);
                intent.putExtra(Const.SEND_PLACE_KEY,currentLocation);
                startActivityForResult(intent, MainActivity.REQUEST_CODE_MAIN_CHOOSE_PLACE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==MainActivity.REQUEST_CODE_MAIN_CHOOSE_PLACE){
            if(resultCode==MainActivity.RESULT_CODE_MAIN_CHOOSE_PLACE){
                currentLocation=data.getStringExtra(Const.SEND_PLACE_KEY);
                currentService=data.getStringExtra(Const.SEND_PLACE_SERVICE_KEY);
                tvPlace.setText(currentService+" near "+currentLocation);
            }
        }
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
