package com.icbc.nafiza.sanjay.icbc.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText editTxtUser;
    EditText editTxtPassword;
    EditText editTxtEmail;
    String username;
    String password;
    String email;
    DBHelper dbHelper;

    private static final int PASSWORD_LENGTH = 6;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        editTxtUser = (EditText) findViewById(R.id.editTxtUser);
        editTxtPassword = (EditText) findViewById(R.id.editTxtPassword);
        editTxtEmail = (EditText) findViewById(R.id.editTxtEmail);

        addListener();

    }

    public void addListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editTxtUser.getText().toString();
                password = editTxtPassword.getText().toString();
                email = editTxtEmail.getText().toString();

            }
        });
    }






}
