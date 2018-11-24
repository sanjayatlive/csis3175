package com.icbc.nafiza.sanjay.icbc.activities;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.adapter.ItemDivider;
import com.icbc.nafiza.sanjay.icbc.adapter.RecyclerAdapter;
import com.icbc.nafiza.sanjay.icbc.bean.Item;
import com.icbc.nafiza.sanjay.icbc.fragments.FragmentResults;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;
import com.icbc.nafiza.sanjay.icbc.util.Volley;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Item> dataList;
    String responseglobal = "";
    Snackbar snackBar;
    RecyclerView recyclerView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    FloatingActionButton fab;
    FrameLayout frame;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ItemDivider(this, LinearLayoutManager.VERTICAL, 16));

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        int choice = pref.getInt("choice", 0);

        if (choice == 0) {
            getDataFromNetwork();
        } else {
            getDataFromDB();
            createRecyclerViewAndSetAdapter(recyclerView);
        }

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        addListener();
        getIds();
    }

    public void getIds() {
        frame = (FrameLayout) findViewById(R.id.frame);
        frame.setVisibility(View.INVISIBLE);
        frame.setEnabled(false);
    }


    public void addListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame.setVisibility(View.VISIBLE);
                frame.setEnabled(true);

                Fragment fr = new FragmentResults();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fr);
                fragmentTransaction.commit();
                FragmentResults.fr = fr;
                RecyclerAdapter.isClickable = false;
            }
        });
    }

    public void getDataFromNetwork() {
        try {
            if (isNetworkAvailable()) {

                try {
                    Volley.fetchData(this, recyclerView);
                } catch (Exception e) {
                    Snackbar.make(findViewById(R.id.constraintLayout), e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            } else

            {
                snackBar = Snackbar.make(findViewById(R.id.constraintLayout), "No Internet Available", Snackbar.LENGTH_INDEFINITE);
                snackBar.setAction("Retry", new RetryListener());
                snackBar.show();

            }
        } catch (Exception e) {
            Snackbar.make(findViewById(R.id.constraintLayout), e.getMessage(), Snackbar.LENGTH_LONG).show();

        }

    }

    public class RetryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            getDataFromNetwork();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getDataFromDB() {

        dataList = DBHelper.getQuestionsFromDB();


    }

    public void createRecyclerViewAndSetAdapter(RecyclerView recyclerView) {

        recyclerAdapter = new RecyclerAdapter(dataList, this, recyclerView);
        recyclerView.setAdapter(recyclerAdapter);
    }

}

