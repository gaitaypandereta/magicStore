package com.example.magistore.vista;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magistore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStoreWeb extends Fragment {


    public FragmentStoreWeb() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_store_detail, container, false);
        return vista;
    }

}
