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
public class FragmentLogin extends Fragment {
private Button btn_registro, btn_login;

    public FragmentLogin() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_login, container, false);



        btn_registro=vista.findViewById(R.id.btn_registro);
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentRegistration());
            }
        });

        btn_login=vista.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentStore());
            }
        });
        return vista;
    }

}
