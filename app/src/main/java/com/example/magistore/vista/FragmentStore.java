package com.example.magistore.vista;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
public class FragmentStore extends Fragment {
private Button btn_signout;
private TextView ed_user, ed_email, ed_apikey;
private FirebaseAuth mAuth;
private DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista= inflater.inflate(R.layout.fragment_store, container, false);
        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        ed_user=vista.findViewById(R.id.ed_user_store);
        ed_email=vista.findViewById(R.id.ed_email_store);
        ed_apikey=vista.findViewById(R.id.ed_apikey_store);

        btn_signout=vista.findViewById(R.id.btn_signOut);
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();
               // getActivity().finish();
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentInicio());

            }
        });


       getDatosUser();


        return vista;
    }
/*
    @Override
    public void onStart(){
        super.onStart();

        if(mAuth.getCurrentUser() != null){

            ((MainActivity) getActivity()).cambiarFragmento(new FragmentStore());
            getActivity().finish();

        }
    }

    */

   private void getDatosUser(){

    String id =mAuth.getCurrentUser().getUid();
    mDatabase.child("users").child(id).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            if(dataSnapshot.exists()){

                String nombre=dataSnapshot.child("nombre").getValue().toString();
                String email=dataSnapshot.child("email").getValue().toString();
                String apky=mAuth.getCurrentUser().getUid();

                Toast.makeText(getContext(), nombre, Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), email, Toast.LENGTH_SHORT).show();
                ed_user.setText(nombre);
                ed_email.setText(email);
                ed_apikey.setText(apky);

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

   }

}
