package com.example.asus.dogvacayproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.dogvacayproject.R;
import com.example.asus.dogvacayproject.adapter.NavigationDrawerAdapter;
import com.example.asus.dogvacayproject.model.NavigationDrawerItem;
import com.example.asus.dogvacayproject.utils.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 8/8/2016.
 */
public class LoggedOutNavigationDrawer extends Fragment {
    private RecyclerView recyclerViewLoggedOut;
    private ImageView imgLogo;
    private RelativeLayout layoutUser;
    private ImageView imgAvatarUser;
    private TextView tvUsername;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    NavigationDrawerAdapter adapter;
    int currentStateLogIn=Const.LOGGED_IN_TYPE;
    private FragmentDrawerListener drawerListener;

    public LoggedOutNavigationDrawer(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.navigation_drawer_layout, container, false);
        setupView(layout);
        setAdapterForRecyclerView();
        return layout;
    }

    private void setupView(View layout) {
        recyclerViewLoggedOut= (RecyclerView) layout.findViewById(R.id.logged_out_navigation_list);
        imgLogo=(ImageView)layout.findViewById(R.id.img_logo_nav_drawer_item);
        layoutUser=(RelativeLayout)layout.findViewById(R.id.logged_in_img_username_layout);
        imgAvatarUser=(ImageView)layout.findViewById(R.id.img_avatar_logged_in_nav);
        tvUsername=(TextView)layout.findViewById(R.id.textview_username_nav);
        if(currentStateLogIn==Const.LOGGED_IN_TYPE){
            imgLogo.setVisibility(View.GONE);
            layoutUser.setVisibility(View.VISIBLE);
        }
    }

    private void setAdapterForRecyclerView() {
        if(currentStateLogIn==0) {
            adapter = new NavigationDrawerAdapter(getActivity(), getData(), Const.LOGGED_OUT_TYPE);
            recyclerViewLoggedOut.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        else {
            adapter = new NavigationDrawerAdapter(getActivity(), getData(), Const.LOGGED_IN_TYPE);
            recyclerViewLoggedOut.setLayoutManager(new GridLayoutManager(getActivity(),2));
        }
        recyclerViewLoggedOut.setAdapter(adapter);
        recyclerViewLoggedOut.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerViewLoggedOut, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }
    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
    public List<NavigationDrawerItem> getData() {
        List<NavigationDrawerItem> data = new ArrayList<>();
        // preparing navigation drawer items
        if(currentStateLogIn==Const.LOGGED_OUT_TYPE) {
            NavigationDrawerItem browse_item = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_browse, "Browse");
            data.add(browse_item);
            NavigationDrawerItem help_item = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_help, "Help");
            data.add(help_item);
            NavigationDrawerItem vacay_cam = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_vacay_cam, "VacayCam");
            data.add(vacay_cam);
            NavigationDrawerItem sign_up_item = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_sign_in, "Sign Up Or Log In");
            data.add(sign_up_item);
        }else {
            NavigationDrawerItem browse_item = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_browse, "Browse");
            data.add(browse_item);
            NavigationDrawerItem vacay_item = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_vacays, "Vacays");
            data.add(vacay_item);
            NavigationDrawerItem message_item = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_messages, "Messages");
            data.add(message_item);
            NavigationDrawerItem booking_item = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_bookings, "Bookings");
            data.add(booking_item);
            NavigationDrawerItem vacay_cam = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_vacay_cam, "VacayCam");
            data.add(vacay_cam);
            NavigationDrawerItem help_item = new NavigationDrawerItem(R.drawable.navigation_drawer_selector_help, "Help");
            data.add(help_item);
        }
        return data;
    }
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }
}

