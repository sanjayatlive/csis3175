package com.icbc.nafiza.sanjay.icbc.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icbc.nafiza.sanjay.icbc.R;


public class FragmentResults extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment

        return inflater.inflate(
                R.layout.fragment_fragment_results, container, false);
    }
}