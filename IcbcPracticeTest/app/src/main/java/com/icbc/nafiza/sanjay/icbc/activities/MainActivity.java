package com.icbc.nafiza.sanjay.icbc.activities;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.bean.Item;
import com.icbc.nafiza.sanjay.icbc.util.Parser;
import com.icbc.nafiza.sanjay.icbc.util.Volley;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity  {
    List<Item> dataList;
    String responseglobal = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

dataList = new ArrayList<>();
          Volley.fetchData(this);


     //   Log.d("sizeofresponseonCreate>",""+responseglobal.length());



        //  Log.d("sizeoflistmainactivity>",""+dataList.size());

    }








}

