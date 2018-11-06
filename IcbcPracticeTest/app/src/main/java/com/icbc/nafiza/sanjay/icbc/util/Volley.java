package com.icbc.nafiza.sanjay.icbc.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.icbc.nafiza.sanjay.icbc.bean.Item;

import java.util.ArrayList;
import java.util.List;

public class Volley {
    static List<Item> dataList = new ArrayList<>();
    public static void fetchData(Context ctx)
    {

        // final Parser parser = new Parser();

        RequestQueue queue = com.android.volley.toolbox.Volley.newRequestQueue(ctx);
        String url = "http://practicetest.icbc.com/data/opkt/english.xml";


        final ProgressDialog progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Fetching The File....");
        progressDialog.show();

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // Log.d("Response is: ", ""+response);
                        try {

                            //      list = parser.parseIt(response);

                            progressDialog.dismiss();
                            dataList = Parser.parseIt(response.toString());


                         //   Log.d("size","sizeofresponsevolley" + response.length());

                            // do something like setting recycler adapter

                            Log.d("size","sizeofdataListFromVolley" + dataList.size());



                            //   parseIt(response.toString());

                            // Log.d("size","<<<<<<<<<<<<<>>>>>>>>>>>>>>>" + dataList.size());
                            /*System.out.println("objectq>>>>>>>>>>>>>>>" + list.get(0).getQuestion());
                            System.out.println("objecta>>>>>>>>>>>>>>>" + list.get(0).getAnswer());
                            System.out.println("objectd1>>>>>>>>>>>>>>>" + list.get(0).getDistractor1());
                            System.out.println("objectd2>>>>>>>>>>>>>>>" + list.get(0).getDistractor2());
                            System.out.println("objectd3>>>>>>>>>>>>>>>" + list.get(0).getDistractor3());*/


                            //         list2=list;
                        }catch (Exception e){
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // mTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        //   stringRequest.
        //  Item c = new Item();
        //     Log.d("sizeoflistvolley>",""+responsemain.length());



        //   return list;
    }




}


