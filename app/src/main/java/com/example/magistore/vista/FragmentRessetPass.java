package com.example.magistore.vista;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.magistore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRessetPass extends Fragment {
private EditText edit_email;
private Button btn_reset_pass;
private String email="";
private FirebaseAuth mAuth;
private ProgressDialog mDialogo;

    public FragmentRessetPass() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_resset_pass, container, false);
       mAuth=FirebaseAuth.getInstance();
       mDialogo=new ProgressDialog(getContext());
       edit_email=vista.findViewById(R.id.editext_reset_pass_Email);
       btn_reset_pass=vista.findViewById(R.id.btn_Reset_Pass);
       btn_reset_pass.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

             email = edit_email.getText().toString();

             if(!email.isEmpty()) {

                 mDialogo.setMessage("Espere por favor");
                 mDialogo.setCanceledOnTouchOutside(false);
                 mDialogo.show();
                 ressetPass();
             }else{
                 Toast.makeText(getContext(), "Ingresa el email", Toast.LENGTH_SHORT).show();
             }
           }
       });




        return vista;
    }
 private void ressetPass(){
     mAuth.setLanguageCode("es");
     mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
         @Override
         public void onComplete(@NonNull Task<Void> task) {
             if(task.isSuccessful()){

                Toast.makeText(getContext(), "Se ha enviado un email para restablecer la contraseña", Toast.LENGTH_LONG).show();


             }else{ Toast.makeText(getContext(), "No hemos podido enviar el email para restablecer password", Toast.LENGTH_SHORT).show();

             }
             mDialogo.dismiss();
         }
     });
 }
}
