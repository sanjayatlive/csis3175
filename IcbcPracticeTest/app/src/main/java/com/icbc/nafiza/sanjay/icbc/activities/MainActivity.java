package com.icbc.nafiza.sanjay.icbc.activities;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.adapter.ItemDivider;
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

//dataList = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new ItemDivider(this, LinearLayoutManager.VERTICAL, 16));
          Volley.fetchData(this,recyclerView);


     //   Log.d("sizeofresponseonCreate>",""+responseglobal.length());



        //  Log.d("sizeoflistmainactivity>",""+dataList.size());




    }








}

