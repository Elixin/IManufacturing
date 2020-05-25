package com.lenovo.manufacture;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lenovo.manufacture.base.BaseFragment;

public class TextfrFragment extends BaseFragment {

    private TextView textView;
    private static final String TAG = "TextfrFragment";
    private int t = 0;

    public TextfrFragment(int t) {
        this.t = t;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.textfr, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = (TextView) view.findViewById(R.id.textView);
    }

}
