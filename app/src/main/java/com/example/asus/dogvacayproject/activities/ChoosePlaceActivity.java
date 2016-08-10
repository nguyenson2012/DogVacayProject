package com.example.asus.dogvacayproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.asus.dogvacayproject.R;
import com.example.asus.dogvacayproject.adapter.AutoCompleteTextViewAdapter;
import com.example.asus.dogvacayproject.adapter.PlaceAdapter;
import com.example.asus.dogvacayproject.utils.Const;
import com.example.asus.dogvacayproject.view.ClearableAutoCompleteTextView;
import com.example.asus.dogvacayproject.view.ClearableEditText;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Asus on 8/10/2016.
 */
public class ChoosePlaceActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout layoutLookingService;
    RelativeLayout layoutLookingAllSitters;
    RelativeLayout layoutLookingBoarding;
    RelativeLayout layoutLookingSitting;
    RelativeLayout layoutlookingDaycare;
    ImageView imgBack;
    RadioButton rdAllSitter,rdBoarding,rdSitting,rdDaycare;
    Toolbar toolbar;
    ClearableEditText editTextSearchPlace;
    ListView lvPlace;
    PlaceAdapter adapter;
    ArrayList<String> place;
    String currentLocation;
    int numberClickBack=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_place);
        setDefaultPlace();
        setupView();
        registerEvent();
        setPlaceToEditText();
    }

    private void setDefaultPlace() {
        place=new ArrayList<String>();
        place.add("New York");
        place.add("New Orlean");
        place.add("SanFrancisco");
        place.add("Ohio");
        place.add("Texas");
        place.add("Washington DC");
    }

    private void setPlaceToEditText() {
        Intent intent=getIntent();
        currentLocation=intent.getStringExtra(Const.SEND_PLACE_KEY);
        editTextSearchPlace.setText(currentLocation);
    }

    private void registerEvent() {
        layoutLookingAllSitters.setOnClickListener(this);
        layoutlookingDaycare.setOnClickListener(this);
        layoutLookingBoarding.setOnClickListener(this);
        layoutLookingSitting.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        editTextSearchPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layoutLookingService.setVisibility(View.GONE);
                lvPlace.setVisibility(View.VISIBLE);
                String text = editTextSearchPlace.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextSearchPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutLookingService.setVisibility(View.GONE);
                lvPlace.setVisibility(View.VISIBLE);
            }
        });
        lvPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                sendPlaceBack(adapter.getPlace(position));
            }
        });
    }

    private void sendPlaceBack(String place) {
        Intent intent=getIntent();
        //String place= clearableAutoCompleteTextView.getText()+"";
        intent.putExtra(Const.SEND_PLACE_KEY, place);
        if(rdAllSitter.isChecked()){
            intent.putExtra(Const.SEND_PLACE_SERVICE_KEY,getResources().getString(R.string.all_sitter));
        }else {
            if(rdBoarding.isChecked()){
                intent.putExtra(Const.SEND_PLACE_SERVICE_KEY,getResources().getString(R.string.boarding));
            }else {
                if(rdSitting.isChecked())
                    intent.putExtra(Const.SEND_PLACE_SERVICE_KEY,getResources().getString(R.string.sitting));
                else
                    if(rdDaycare.isChecked())
                        intent.putExtra(Const.SEND_PLACE_SERVICE_KEY,getResources().getString(R.string.daycare));
            }
        }
        setResult(MainActivity.RESULT_CODE_MAIN_CHOOSE_PLACE, intent);
        finish();
    }

    private void setupView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_choose_place);
        setSupportActionBar(toolbar);
        layoutLookingService=(LinearLayout)findViewById(R.id.layout_looking_for_choose_place);
        layoutLookingAllSitters=(RelativeLayout)findViewById(R.id.looking_all_sitter_layout);
        layoutLookingBoarding=(RelativeLayout)findViewById(R.id.boarding_layout);
        layoutLookingSitting=(RelativeLayout)findViewById(R.id.sitting_layout);
        layoutlookingDaycare=(RelativeLayout)findViewById(R.id.daycare_layout);
        rdAllSitter=(RadioButton)findViewById(R.id.radio_choose_all_sitters);
        rdAllSitter.setChecked(true);
        rdBoarding=(RadioButton)findViewById(R.id.radio_choose_boarding);
        rdSitting=(RadioButton)findViewById(R.id.radio_choose_sitting);
        rdDaycare=(RadioButton)findViewById(R.id.radio_choose_daycare);
        imgBack=(ImageView)findViewById(R.id.img_back_toolbar_choose_place);
        editTextSearchPlace=(ClearableEditText)findViewById(R.id.clearable_edittext_search_place);
        lvPlace=(ListView)findViewById(R.id.lvChoosePlace);
        adapter=new PlaceAdapter(ChoosePlaceActivity.this,place);
        lvPlace.setAdapter(adapter);
        adapter.filter("");
        lvPlace.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.looking_all_sitter_layout:
                chooseRadio(rdAllSitter);
                break;
            case R.id.boarding_layout:
                chooseRadio(rdBoarding);
                break;
            case R.id.sitting_layout:
                chooseRadio(rdSitting);
                break;
            case R.id.daycare_layout:
                chooseRadio(rdDaycare);
                break;
            case R.id.img_back_toolbar_choose_place:
                finish();
                break;
        }
    }

    private void chooseRadio(RadioButton radioButton) {
        rdAllSitter.setChecked(false);
        rdDaycare.setChecked(false);
        rdSitting.setChecked(false);
        rdBoarding.setChecked(false);
        radioButton.setChecked(true);
    }

    @Override
    public void onBackPressed() {
        if(numberClickBack==1)
            super.onBackPressed();
        if(numberClickBack==0){
            layoutLookingService.setVisibility(View.VISIBLE);
            numberClickBack=1;
        }
    }
}
