package com.icbc.nafiza.sanjay.icbc.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.adapter.ItemDivider;
import com.icbc.nafiza.sanjay.icbc.bean.Item;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;
import com.icbc.nafiza.sanjay.icbc.util.Volley;

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


        DBHelper dbHelper = new DBHelper(this);
        dbHelper.createTables();


     //   Log.d("sizeofresponseonCreate>",""+responseglobal.length());



        //  Log.d("sizeoflistmainactivity>",""+dataList.size());




    }





}

