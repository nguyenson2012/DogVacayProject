package com.example.asus.dogvacayproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.dogvacayproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Asus on 8/10/2016.
 */
public class PlaceAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<String> listPlace;
    private ArrayList<String> arraylistPlace;
    public PlaceAdapter(Context context, List<String> places) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.listPlace=places;
        this.arraylistPlace=new ArrayList<String>();
        this.arraylistPlace.addAll(places);
    }

    @Override
    public int getCount() {
        return listPlace.size();
    }

    @Override
    public Object getItem(int i) {
        return listPlace.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.search_place_item_row,viewGroup,false);
            holder.tvPlace= (TextView) view.findViewById(R.id.search_location_text);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tvPlace.setText(listPlace.get(i).toString());
        return view;
    }
    public class ViewHolder {
        TextView tvPlace;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listPlace.clear();
        if (charText.length() == 0) {
            //listPlace.addAll(arraylistPlace);
            listPlace.add(new String("Current Location"));
        }
        else
        {
            for (String place : arraylistPlace)
            {
                if (place.toLowerCase(Locale.getDefault()).contains(charText))
                {
                    listPlace.add(place);
                }
            }
        }
        notifyDataSetChanged();
    }
    public String getPlace(int position){
        return listPlace.get(position).toString();
    }
}
