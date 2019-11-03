package com.example.magistore.vista;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

import static com.firebase.ui.auth.ui.phone.SubmitConfirmationCodeFragment.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegisterInit extends Fragment {
private Button btn_register;
private EditText edit_nombre, edit_email, edit_pass;
private String nombre, email, password;

    public FragmentRegisterInit() {
        // Required empty public constructor
    }

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_regist_init, container, false);
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        edit_nombre=vista.findViewById(R.id.edit_nombre_init);
        edit_email=vista.findViewById(R.id.edit_email_init);
        edit_pass=vista.findViewById(R.id.edit_pasword_init);
        btn_register=vista.findViewById(R.id.btn_regitrar_user);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             nombre= edit_nombre.getText().toString().trim();
             email=edit_email.getText().toString().trim();
             password=edit_pass.getText().toString().trim();

            if(!nombre.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                if(password.length() >= 6){

                    registerUser();

                }
                else{
                    Toast.makeText(getActivity(), "La contraseña debe de tener más de 6 carateres", Toast.LENGTH_SHORT).show();
                }


            }




            }
        });

        return vista;
    }

    private void registerUser(){
        Toast.makeText(getActivity(), "antes de map_1", Toast.LENGTH_SHORT).show();
     mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {

         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful()) {
                 Toast.makeText(getActivity(), "antes de map_2", Toast.LENGTH_SHORT).show();
                 Map<String, Object> map = new HashMap<>();
                 map.put("nombre", nombre);
                 map.put("email", email);
                 map.put("password", password);
                 String  id = mAuth.getCurrentUser().getUid();
                 Toast.makeText(getActivity(), "despues de id", Toast.LENGTH_SHORT).show();
                 mDatabase.child("users").child(id).setValue(map).addOnCompleteListener(getActivity(),new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task2) {
                         if (task2.isSuccessful()){
                             Toast.makeText(getActivity(), "dentro del metodo", Toast.LENGTH_SHORT).show();
                             ((MainActivity) getActivity()).cambiarFragmento(new FragmentRegistration());
                            // finish();

                         }

                     }
                 });
             }

             else{
                   Toast.makeText(getActivity(), "No se pudo crear", Toast.LENGTH_SHORT).show();
             }

             Toast.makeText(getActivity(), "despues de mDatabase", Toast.LENGTH_SHORT).show();
         }
     });


    }

/*
    private void accionesFirebase(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario != null){
                    //usuario ya logueado
                    Log.d("Firebase", "onAuthStateChanged:usuario_logueado:" + usuario.getUid());
                }else{
                    //usuario no logueado
                    Log.d("Firebase", "onAuthStateChanged:usuario_no_logueado:");
                }
            }
        };
    }


*/

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }



/*
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }
*/

/*

 método que actualiza la pantalla
    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
           if (user != null) { mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
                  mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
                  findViewById(R.id.sign_in_button).setVisibility(View.GONE);
                  findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
           } else { mStatusTextView.setText(R.string.signed_out);
                  mDetailTextView.setText(null);
                  findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
                  findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
           }
    }

    */





}

