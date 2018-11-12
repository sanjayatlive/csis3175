package com.icbc.nafiza.sanjay.icbc.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.icbc.nafiza.sanjay.icbc.R;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        TextView txtViewOptions = (TextView) findViewById(R.id.txtViewOptions);
        RadioGroup radGroupResOption = (RadioGroup) findViewById(R.id.radGroupResOption);
        RadioButton radBtnNetwork = (RadioButton) findViewById(R.id.radBtnNetwork);
        RadioButton radBtnDatabase = (RadioButton) findViewById(R.id.radBtnDatabase);
        Button btnResChoice = (Button) findViewById(R.id.btnResChoice);

        Typeface font = Typeface.SANS_SERIF;
        txtViewOptions.setTypeface(font);
        radBtnDatabase.setTypeface(font);
        radBtnNetwork.setTypeface(font);
        btnResChoice.setTypeface(font);

        btnResChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
