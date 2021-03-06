package com.example.asus.dogvacayproject.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asus.dogvacayproject.R;
import com.example.asus.dogvacayproject.fragment.FragmentBrowse;
import com.example.asus.dogvacayproject.fragment.LoggedOutNavigationDrawer;
import com.example.asus.dogvacayproject.utils.Const;

public class MainActivity extends AppCompatActivity implements LoggedOutNavigationDrawer.FragmentDrawerListener{
    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;
    private Button buttonFilter;
    private LoggedOutNavigationDrawer drawerFragment;
    FragmentBrowse fragmentBrowse;
    private int currentStateLoggedIn= Const.LOGGED_IN_TYPE;
    public static final int REQUEST_CODE_MAIN_CHOOSE_PLACE=110;
    public static final int RESULT_CODE_MAIN_CHOOSE_PLACE=111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        registerEvent();
    }

    private void registerEvent() {
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Filter Host",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        buttonFilter= (Button) mToolbar.findViewById(R.id.button_filter_host);
        drawerFragment = (LoggedOutNavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        mFragmentManager=getSupportFragmentManager();
        addFragmentBrowse();
    }

    private void addFragmentBrowse() {
        fragmentBrowse=new FragmentBrowse();
        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentBrowse);
        fragmentTransaction.commit();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        handleClickOnNavItem(position);
    }

    private void handleClickOnNavItem(int position) {
        if(currentStateLoggedIn==Const.LOGGED_OUT_TYPE){
            switch (position){
                case 0://Browse
                    break;
                case 1://Help
                    Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
                    break;
                case 2://Vacay cam
                    Toast.makeText(getApplicationContext(),"VacayCam",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(),"Sign Up Or Sign In",Toast.LENGTH_SHORT).show();
                    break;
            }
        }else {
            switch (position){
                case 0://browse
                    break;
                case 1://vacay
                    Toast.makeText(getApplicationContext(),"Vacays",Toast.LENGTH_SHORT).show();
                    break;
                case 2://Message
                    Toast.makeText(getApplicationContext(),"Message",Toast.LENGTH_SHORT).show();
                    break;
                case 3://Booking
                    Toast.makeText(getApplicationContext(),"Booking",Toast.LENGTH_SHORT).show();
                    break;
                case 4://VacayCam
                    Toast.makeText(getApplicationContext(),"VacayCam",Toast.LENGTH_SHORT).show();
                    break;
                case 5://Help
                    Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }
}
