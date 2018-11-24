package com.icbc.nafiza.sanjay.icbc.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
    TextView txtViewPercentage;
    FloatingActionButton closeFab;

    public static Fragment fr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_results, container, false);
        closeFab = (FloatingActionButton)view.findViewById(R.id.closeFab);
        txtViewResult = (TextView)view.findViewById(R.id.txtViewFragment);
        txtViewPercentage = (TextView)view.findViewById(R.id.txtViewPercentage);
        txtViewResult.setText(setScoreText());
        txtViewPercentage.setText(setPrecentText());
        addListener();
        return view;
    }

    public String setScoreText(){
        int score = DBHelper.getScoreFromDB();
        String showScore = score + " / " + Parser.dataList.size();
        return showScore;
    }

    public String setPrecentText(){
        int score = DBHelper.getScoreFromDB();
        String showScore = score + " / " + Parser.dataList.size();
        int total = Parser.dataList.size();
        int percentage = score*100/total;
        String percent = percentage + "%";
        return  percent;
    }

    public void addListener(){
        closeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.remove(fr);
                fragmentTransaction.commit();
                RecyclerAdapter.isClickable = true;

            }
        });
    }
}