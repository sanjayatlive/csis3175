package com.icbc.nafiza.sanjay.icbc.activities;

import android.content.ContentValues;
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
                validate(username, password, email);
            }
        });
    }

    public void validate(String username, String password, String email) {

        try {
            if (username.isEmpty() || username.equals(null) || username.equals(" ") ) {
                Snackbar.make((this.findViewById(R.id.regConstLayout)), "Enter Valid Username", Snackbar.LENGTH_LONG).show();
            } else {
                if (password.isEmpty() || password.equals(null) || password.equals(" ") ||  password.length()>PASSWORD_LENGTH) {
                    Snackbar.make((this.findViewById(R.id.regConstLayout)), "Enter Valid Password", Snackbar.LENGTH_LONG).show();
                } else {
                    if(email.isEmpty() || email.equals(null) || email.equals(" ") || !email.matches(EMAIL_REGEX)){
                        Snackbar.make((this.findViewById(R.id.regConstLayout)), "Enter Valid EMail-Id", Snackbar.LENGTH_LONG).show();
                    }
                    else{
                        dbHelper=new DBHelper(this);
                        dbHelper.createTables();
                        dbHelper.insertUserDataToDB(0,username,password,email);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }





}
