package com.example.asus.dogvacayproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.dogvacayproject.R;

/**
 * Created by Asus on 8/9/2016.
 */
public class FragmentBrowseList extends Fragment{
    public FragmentBrowseList(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_browse_list,container,false);
        return layout;
    }
}
