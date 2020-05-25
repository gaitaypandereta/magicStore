package com.example.magistore.fragmentos;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInic extends Fragment {

    private Button btn_session;
    private TextView tv_entrar, tv_registro;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;



    public FragmentInic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_inic, container, false);
        mAuth= FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        RelativeLayout relativeclic =vista.findViewById(R.id.layout_inic);
        relativeclic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentLogin());
            }
        });


        return vista;
    }

    private void saludoUser(){


        if(mAuth.getCurrentUser().getUid().isEmpty()){

            tv_registro.setText("¡Hola! ¿Aún no tienes cuenta?");

        }else{

            String id= mAuth.getCurrentUser().getUid();
            mDatabase.child("users").child(id).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String nombre = dataSnapshot.child("nombre").getValue().toString();

                        tv_entrar.setText("¡Hola! Vas a iniciar sesión " );

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        }
    }


}
