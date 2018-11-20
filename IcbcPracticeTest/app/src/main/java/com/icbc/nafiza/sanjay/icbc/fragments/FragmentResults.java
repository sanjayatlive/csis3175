package com.icbc.nafiza.sanjay.icbc.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.adapter.RecyclerAdapter;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;
import com.icbc.nafiza.sanjay.icbc.util.Parser;


public class FragmentResults extends Fragment {
    TextView txtViewResult;
    FloatingActionButton closeFab;
    public static Fragment fr;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(
                R.layout.fragment_fragment_results, container, false);
        closeFab = (FloatingActionButton)view.findViewById(R.id.closeFab);
        addListener();
        return view;
    }


    public void setText(String text) {

        int score = DBHelper.getScoreFromDB(0);
        String showScore = score + " / " + Parser.dataList.size();
        txtViewResult = (TextView) getView().findViewById(R.id.txtViewFragment);  //UPDATE
        txtViewResult.setText(showScore);
    }

    public void addListener(){
        closeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>"+"close");
             //   getActivity().onBackPressed();

                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.remove(fr);
               // fragmentTransaction.replace(R.id.frame, fr);
                fragmentTransaction.commit();

                RecyclerAdapter.isClickable = true;

            }
        });
    }


}