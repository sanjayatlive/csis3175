package com.icbc.nafiza.sanjay.icbc.activities;

import android.graphics.Typeface;
import  android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.icbc.nafiza.sanjay.icbc.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    String userChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        TextView txtViewQuesDetail = (TextView) findViewById(R.id.txtViewQuesDetail);
        RadioGroup radGrpDetail = (RadioGroup) findViewById(R.id.radGrpDetail);
        RadioButton radBtnFirst = (RadioButton) findViewById(R.id.radBtnFirst);
        RadioButton radBtnSecond = (RadioButton) findViewById(R.id.radBtnSecond);
        RadioButton radBtnThird = (RadioButton) findViewById(R.id.radBtnThird);
        RadioButton radBtnFourth = (RadioButton) findViewById(R.id.radBtnFourth);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        final Button btnNext=(Button)findViewById(R.id.btnNext);

        btnNext.setEnabled(false);

        txtViewQuesDetail.setText(getIntent().getStringExtra("question"));

        List<String> options = new ArrayList<>();
        options.add(getIntent().getStringExtra("answer"));
        options.add(getIntent().getStringExtra("dist1"));
        options.add(getIntent().getStringExtra("dist2"));
        options.add(getIntent().getStringExtra("dist3"));

        Collections.shuffle(options);

        radBtnFirst.setText(options.get(0));
        radBtnSecond.setText(options.get(1));
        radBtnThird.setText(options.get(2));
        radBtnFourth.setText(options.get(3));

       // Typeface font=getResources().getFont(R.font.myFont);
        Typeface font=Typeface.SANS_SERIF;
        radBtnFirst.setTypeface(font);
        radBtnSecond.setTypeface(font);
        radBtnThird.setTypeface(font);
        radBtnFourth.setTypeface(font);
        txtViewQuesDetail.setTypeface(font);
        btnSubmit.setTypeface(font);
        btnNext.setTypeface(font);


        radGrpDetail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radChkID = radioGroup.getCheckedRadioButtonId();
                RadioButton selected = (RadioButton) findViewById(radChkID);
                userChoice = selected.getText().toString();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userChoice!=null) {
                    Toast.makeText(DetailActivity.this, userChoice, Toast.LENGTH_SHORT).show();
                    btnNext.setEnabled(true);
                }
                else{
                    Toast.makeText(DetailActivity.this, "Select atleast one option", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
