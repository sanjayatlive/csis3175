package com.icbc.nafiza.sanjay.icbc.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.bean.Item;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;
import com.icbc.nafiza.sanjay.icbc.util.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    String userChoice;
    Button btnNext;
    Button btnSubmit;
    RadioGroup radGrpDetail;
    int questionId;
    int status = 0;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        TextView txtViewQuesDetail = (TextView) findViewById(R.id.txtViewQuesDetail);
        radGrpDetail = (RadioGroup) findViewById(R.id.radGrpDetail);
        RadioButton radBtnFirst = (RadioButton) findViewById(R.id.radBtnFirst);
        RadioButton radBtnSecond = (RadioButton) findViewById(R.id.radBtnSecond);
        RadioButton radBtnThird = (RadioButton) findViewById(R.id.radBtnThird);
        RadioButton radBtnFourth = (RadioButton) findViewById(R.id.radBtnFourth);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setVisibility(View.INVISIBLE);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        questionId = getIntent().getIntExtra("questionId", 0);
        txtViewQuesDetail.setText(Parser.dataList.get(questionId).getQuestion());

        List<String> options = new ArrayList<>();
        options.add(Parser.dataList.get(questionId).getAnswer());
        options.add(Parser.dataList.get(questionId).getDistractor1());
        options.add(Parser.dataList.get(questionId).getDistractor2());
        options.add(Parser.dataList.get(questionId).getDistractor3());


        Collections.shuffle(options);

        radBtnFirst.setText(options.get(0));
        radBtnSecond.setText(options.get(1));
        radBtnThird.setText(options.get(2));
        radBtnFourth.setText(options.get(3));

        //specific font to all text in activity
        Typeface font = Typeface.SANS_SERIF;
        radBtnFirst.setTypeface(font);
        radBtnSecond.setTypeface(font);
        radBtnThird.setTypeface(font);
        radBtnFourth.setTypeface(font);
        txtViewQuesDetail.setTypeface(font);
        btnSubmit.setTypeface(font);
        btnNext.setTypeface(font);

        status = DBHelper.getResultFromDB(questionId);

        if (status != 0) {
            btnNext.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.INVISIBLE);
        }

        System.out.println("<<<<<<<<<<<InDetailOnC>>>>>>>>>>" + questionId + " " + Parser.dataList.size());
        if (questionId >= Parser.dataList.size() - 1) {
            btnNext.setText(getResources().getString(R.string.btnClose));
        }

        addListener();
    }


    public void addListener() {
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
                if (userChoice != null) {
                    //  Toast.makeText(DetailActivity.this, userChoice, Toast.LENGTH_SHORT).show();
                    //btnNext.setEnabled(true);
                    btnNext.setVisibility(View.VISIBLE);
                    btnSubmit.setVisibility(View.INVISIBLE);

                    if (userChoice.equals(Parser.dataList.get(questionId).getAnswer()))
                        status = 1;
                    else
                        status = -1;

                    DBHelper.addResponseToDB(questionId, status);
                    Snackbar.make(findViewById(R.id.constraintLayout), "Answer Submitted Successfully", Snackbar.LENGTH_SHORT).show();
                    System.out.println("wow" + Parser.dataList.get(0).getAnswer());

                } else {
                    Toast.makeText(DetailActivity.this, "Select atleast one option", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (questionId < Parser.dataList.size() - 1) {
                    Intent intent = new Intent(DetailActivity.this, DetailActivity.class);
                    intent.putExtra("questionId", questionId + 1);
                    startActivity(intent);
                }
                finish();
            }
        });
    }

}
