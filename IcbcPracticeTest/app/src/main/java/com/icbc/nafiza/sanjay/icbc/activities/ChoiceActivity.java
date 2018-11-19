package com.icbc.nafiza.sanjay.icbc.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;

public class ChoiceActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button btnResChoice;
    EditText editTxtLogUser;
    EditText editTxtLogPass;
    RadioGroup radGroupResOption;
    RadioButton radBtnNetwork, radBtnDatabase;
    DBHelper dbHelper;
    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        dbHelper=new DBHelper(this);

        TextView txtViewOptions = (TextView) findViewById(R.id.txtViewOptions);
        radGroupResOption = (RadioGroup) findViewById(R.id.radGroupResOption);
        radBtnNetwork = (RadioButton) findViewById(R.id.radBtnNetwork);
        radBtnDatabase = (RadioButton) findViewById(R.id.radBtnDatabase);
        btnResChoice = (Button) findViewById(R.id.btnResChoice);
        editTxtLogUser = (EditText) findViewById(R.id.editTxtLogUser);
        editTxtLogPass = (EditText) findViewById(R.id.editTxtLogPass);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();


        Typeface font = Typeface.SANS_SERIF;
        txtViewOptions.setTypeface(font);
        radBtnDatabase.setTypeface(font);
        radBtnNetwork.setTypeface(font);
        btnResChoice.setTypeface(font);

        addListener();


    }

    public void addListener() {
        final String logUser = editTxtLogUser.getText().toString();
        final String logPass = editTxtLogPass.getText().toString();

        btnResChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radBtnNetwork.isChecked()) {
                    editor.putInt("choice", 0);
                } else {
                    editor.putInt("choice", 1);
                }
                editor.commit();
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                startActivity(intent);




            }
        });
    }

}
