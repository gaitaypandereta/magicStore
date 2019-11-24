package com.example.magistore.vista;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStore extends Fragment {
private Button btn_signout;
private FirebaseAuth mAuth;

    public FragmentStore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista= inflater.inflate(R.layout.fragment_store, container, false);
        mAuth = FirebaseAuth.getInstance();
        btn_signout=vista.findViewById(R.id.btn_signOut);
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentInicio());
               // getActivity().finish();
            }
        });





        return vista;
    }

}
