package com.example.asus.dogvacayproject.adapter;

/**
 * Created by Ravi on 29/07/15.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.dogvacayproject.R;
import com.example.asus.dogvacayproject.model.NavigationDrawerItem;
import com.example.asus.dogvacayproject.utils.Const;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavigationDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private int typeNavItem;


    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data,int type_nav_item) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.typeNavItem=type_nav_item;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=null;
        if(typeNavItem==0) {
            View view = inflater.inflate(R.layout.logged_out_navigation_drawer_item, parent, false);
            holder = new MyViewHolder(view,0);
        }else {
            View view = inflater.inflate(R.layout.logged_in_nav_drawer_item, parent, false);
            holder = new MyViewHolder(view,1);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavigationDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.imageViewItem.setImageResource(current.getImage());
        if(position==3&&typeNavItem== Const.LOGGED_OUT_TYPE)
            holder.divider.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageViewItem;
        View divider;

        public MyViewHolder(View itemView,int typeNavItem) {
            super(itemView);
            if(typeNavItem==Const.LOGGED_OUT_TYPE) {
                title = (TextView) itemView.findViewById(R.id.textview_nav_drawer_item);
                imageViewItem = (ImageView) itemView.findViewById(R.id.img_nav_drawer_item);
                divider = (View) itemView.findViewById(R.id.divider_nav_drawer_item);
            }else {
                title = (TextView) itemView.findViewById(R.id.textview_logged_in_nav_drawer_item);
                imageViewItem = (ImageView) itemView.findViewById(R.id.img_logged_in_nav_drawer_item);
            }
        }
    }
}
