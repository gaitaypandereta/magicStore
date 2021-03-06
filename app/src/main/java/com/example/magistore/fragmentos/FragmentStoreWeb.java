package com.example.magistore.fragmentos;


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
public class FragmentStoreWeb extends Fragment {


    @Nullable
    private Bundle savedInstanceState;

    public FragmentStoreWeb() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_web, container, false);

        WebView myWebView1 = view.findViewById(R.id.webView1);

        myWebView1.getSettings().setJavaScriptEnabled(true);
        myWebView1.loadUrl("https://soyturevista.es/inicio.html");
        return view;
    }


}