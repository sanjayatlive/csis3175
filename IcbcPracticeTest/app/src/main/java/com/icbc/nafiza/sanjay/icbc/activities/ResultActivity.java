package com.icbc.nafiza.sanjay.icbc.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;
import com.icbc.nafiza.sanjay.icbc.util.Parser;

public class ResultActivity extends AppCompatActivity {

    TextView txtViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtViewResult = (TextView) findViewById(R.id.txtViewResult);
        int score = DBHelper.getScoreFromDB(0);
        String showScore=score+" / "+Parser.dataList.size();
        txtViewResult.setText(showScore);


    }
}
