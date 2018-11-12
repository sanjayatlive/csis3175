package com.icbc.nafiza.sanjay.icbc.activities;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.adapter.ItemDivider;
import com.icbc.nafiza.sanjay.icbc.bean.Item;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;
import com.icbc.nafiza.sanjay.icbc.util.Volley;

import java.io.IOException;
import java.util.List;

public class MainActivity  extends AppCompatActivity  {
    List<Item> dataList;
    String responseglobal = "";
    Snackbar snackBar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//dataList = new ArrayList<>();

         recyclerView = (RecyclerView)findViewById(R.id.recyclerView);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new ItemDivider(this, LinearLayoutManager.VERTICAL, 16));



doStuff();

    }

    public void doStuff()
    {
        try {
            if(true) {

                try {
                    Volley.fetchData(this, recyclerView);
                } catch (Exception e) {
                    Snackbar.make(findViewById(R.id.constraintLayout), e.getMessage(), Snackbar.LENGTH_LONG).show();

                }

                DBHelper dbHelper = new DBHelper(this);
                dbHelper.createTables();



            }
            else

            {
                snackBar = Snackbar.make(findViewById(R.id.constraintLayout), "No Internet Available", Snackbar.LENGTH_INDEFINITE);
                snackBar.setAction("Retry", new RetryListener());
                snackBar.show();

            }
        }
        catch (Exception e)
        {
            Snackbar.make(findViewById(R.id.constraintLayout), e.getMessage(), Snackbar.LENGTH_LONG).show();

        }

    }

    public class RetryListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            doStuff();
            // Code to undo the user's last action
        }
    }

    public boolean isConnected() throws InterruptedException, IOException {

        final String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }



}

