package com.icbc.nafiza.sanjay.icbc.activities;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText editTxtUserReg;
    EditText editTxtPassReg;
    EditText editTxtEmailReg;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTxtUserReg = (EditText) findViewById(R.id.editTxtUserReg);
        editTxtPassReg = (EditText) findViewById(R.id.editTxtPassReg);
        editTxtEmailReg = (EditText) findViewById(R.id.editTxtEmailReg);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        registerListener();

    }


    public void registerListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    public void validate() {

        try{
            String username="";
            String password="";
            String email="";
            String emailregex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

            //////////////
            if (editTxtUserReg.getText().toString().matches("[a-zA-Z ]+")) {
                username = editTxtUserReg.getText().toString();
            } else if (editTxtUserReg.getText().length() == 0) {
                Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Enter a valid username", Toast.LENGTH_SHORT).show();
            }


            /////////////////
            if (editTxtPassReg.getText().toString().matches("^[a-zA-Z0-9]*$")) {
                password=editTxtPassReg.getText().toString();
            } else if (editTxtPassReg.getText().length() == 0) {
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            } else {

            }

            ////////////////////
            if (editTxtEmailReg.getText().length() == 0) {
                Toast.makeText(this, "Enter Email Address", Toast.LENGTH_SHORT).show();
            } else if (editTxtEmailReg.getText().toString().matches(emailregex)) {
                email=editTxtEmailReg.getText().toString();
            } else {
                Toast.makeText(this, "Enter a valid Email Address", Toast.LENGTH_SHORT).show();
            }


            DBHelper.addRegisterDataToDB(username,password,email);

        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }





    }
}
