package com.example.asus.dogvacayproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.asus.dogvacayproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Asus on 8/10/2016.
 */
public class AutoCompleteTextViewAdapter extends ArrayAdapter<String> {
    Context mContext;
    int layoutResources;
    ArrayList<String> data,suggestions,tempItems;

    public AutoCompleteTextViewAdapter(Context context, int resource,ArrayList<String> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.layoutResources=resource;
        this.data=objects;
        tempItems = new ArrayList<String>(objects); // this makes the difference.
        suggestions = new ArrayList<String>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(layoutResources, parent, false);
        }
        TextView tvPlace= (TextView) convertView.findViewById(R.id.search_location_text);
        tvPlace.setText(data.get(position).toString()+"");
        return convertView;
    }
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((String) resultValue);
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (String place : tempItems) {
                    if (place.toString().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(place);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<String> filterList = (ArrayList<String>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (String people : filterList) {
                    add(people);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
