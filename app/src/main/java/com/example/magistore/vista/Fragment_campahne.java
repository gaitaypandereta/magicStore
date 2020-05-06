package com.example.magistore.vista;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.magistore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_campahne extends Fragment {


    public Fragment_campahne() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campahne, container, false);

        WebView myWebView1 = view.findViewById(R.id.webView1);

        myWebView1.getSettings().setJavaScriptEnabled(true);
        myWebView1.loadUrl("https://www.google.es/");
        return view;
    }


}
