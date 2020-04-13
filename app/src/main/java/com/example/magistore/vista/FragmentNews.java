package com.example.magistore.vista;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.magistore.MainActivity;
import com.example.magistore.R;

public class FragmentNews extends Fragment {
private Button completa_registro, mis_ideas, subir_ideas;

    public FragmentNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_news, container, false);
        completa_registro=vista.findViewById(R.id.btn_completa_registro);
        mis_ideas=vista.findViewById(R.id.btn_mis_cosas);
        subir_ideas=vista.findViewById(R.id.btn_sube_tus_cosas);

        completa_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentConditions());
            }
        });

        mis_ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentStore());
            }
        });

        subir_ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentUploadStore());
            }
        });

        return vista;
    }

}
