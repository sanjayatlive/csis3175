package com.icbc.nafiza.sanjay.icbc.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.icbc.nafiza.sanjay.icbc.adapter.RecyclerAdapter;
import com.icbc.nafiza.sanjay.icbc.bean.Item;

import java.util.ArrayList;
import java.util.List;

public class Volley {
    static List<Item> dataList = new ArrayList<>();

    public static void fetchData(final Context ctx, final RecyclerView recyclerView) {

        dataList.clear();
        RequestQueue queue = com.android.volley.toolbox.Volley.newRequestQueue(ctx);
        String url = "https://practicetest.icbc.com/data/opkt/english.xml";

        final ProgressDialog progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Fetching The Data....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            dataList = Parser.parseIt(response.toString());
                            Log.d("size", "sizeofdataListFromVolley" + dataList.size());
                            createRecyclerViewAndSetAdapter(ctx, recyclerView);
                            DBHelper.addQuestionsToDB(dataList);
                            progressDialog.dismiss();

                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }


    public static void createRecyclerViewAndSetAdapter(Context ctx, RecyclerView recyclerView) {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(dataList, ctx, recyclerView);
        recyclerView.setAdapter(recyclerAdapter);
    }

}


