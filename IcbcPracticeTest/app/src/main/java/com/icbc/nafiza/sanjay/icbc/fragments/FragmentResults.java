package com.icbc.nafiza.sanjay.icbc.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.util.DBHelper;
import com.icbc.nafiza.sanjay.icbc.util.Parser;


public class FragmentResults extends Fragment {
    TextView txtViewResult;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment

        return inflater.inflate(
                R.layout.fragment_fragment_results, container, false);
    }

    public void setText(String text) {

        int score = DBHelper.getScoreFromDB(0);
        String showScore=score+" / "+ Parser.dataList.size();
        txtViewResult = (TextView) getView().findViewById(R.id.txtViewFragment);  //UPDATE
        txtViewResult.setText(showScore);
    }
}