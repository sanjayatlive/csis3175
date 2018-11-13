package com.icbc.nafiza.sanjay.icbc.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.icbc.nafiza.sanjay.icbc.R;

public class IndexActivity extends AppCompatActivity {

    Button btnSignIn;
    Button btnGuest;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnGuest=(Button)findViewById(R.id.btnGuest);
        btnRegister=(Button)findViewById(R.id.btnRegister);

        indexListener();

    }

    public void indexListener(){

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, ChoiceActivity.class);
                startActivity(intent);
            }
        });


        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(IndexActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(IndexActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
