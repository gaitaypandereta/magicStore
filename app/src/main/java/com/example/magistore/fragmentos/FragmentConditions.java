package com.example.magistore.fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.magistore.MainActivity;
import com.example.magistore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentConditions extends Fragment {
    private Button btn_c_registro, btn_ir_store;
    private CheckBox checkBox;
    public FragmentConditions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_conditions, container, false);
        checkBox=view.findViewById(R.id.chk1);
        btn_c_registro=view.findViewById(R.id.btn_completa_registro);
        btn_ir_store=view.findViewById(R.id.btn_i_store);

        btn_c_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(checkBox.isChecked())
                    ((MainActivity) getActivity()).cambiarFragmento(new FragmentRegistration());

                else
                    Toast.makeText(getContext(), "Tienes que  leer y aceptar antes las condiciones", Toast.LENGTH_LONG).show();

            }
        });

        btn_ir_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentStore());
            }
        });

        return view;
    }

}
