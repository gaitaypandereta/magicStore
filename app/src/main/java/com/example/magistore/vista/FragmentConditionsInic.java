package com.example.magistore.vista;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.magistore.MainActivity;
import com.example.magistore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentConditionsInic
        extends Fragment {
    private Button btn_c_registro, btn_c_inic;



    public FragmentConditionsInic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_conditions_inic, container, false);

        btn_c_registro=view.findViewById(R.id.btn_registro_inic);
        btn_c_inic=view.findViewById(R.id.btn_volver_inic);

        btn_c_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentRegitrationInit()                                                                                                                                                                                                                                                                                                                                                                                                                                           );
            }
        });

        btn_c_inic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentStoreWeb());
            }
        });

        return view;
    }

}
