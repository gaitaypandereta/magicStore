package com.example.magistore.modelo;

import android.util.Log;

import com.example.magistore.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;

public class UsuarioDAO {
    private ArrayList<Object> listaUsuarios = new ArrayList<Object>();
    //Firebase
    FirebaseAuth mAuth;
    FirebaseUser usuario;
/*
    public void removeUsuario(Object usuario){
        listaUsuarios.remove(usuario);
        guardarListaUsuariosEnFirebase(listaUsuarios);
    }

   public ArrayList<Object> mostrarUsuarios(){
        return this.listaUsuarios;
    }

    public void actualizarUsuarios(ArrayList<Object> listaActualizada) { this.listaUsuarios = listaActualizada; }

    public void addUsuarioFireBase(Usuario usuario, final UsuarioDAO dao, final MainActivity activity){
        Log.v("FirebaseEmail", "UsuarioDAO:addEmpresaFireBase:Longitud lista:" + listaUsuarios.size());
        String posicionEnLaBd = listaUsuarios.size() + "";

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(""+usuario.getUid());

        myRef.child("listaUsuarios").child(posicionEnLaBd).setValue(usuario);
        //myRef.setValue("Hello, World!");

        myRef.child("listaUsuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("FirebaseEmail", "UsuarioDAO:addUsuarioFirebase:DataSnapshot:"+dataSnapshot.getValue());
                if((dataSnapshot.getValue()) != null)
                    listaUsuarios = (ArrayList<Object>) dataSnapshot.getValue();
                Log.v("FirebaseEmail", "UsuarioDAO:addUsuarioFirebase:onDataChange:Contenido lista en dao:"+mostrarUsuarios());
                // activity.cambiarFragmento(new Fragmento1(dao));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void guardarListaUsuariosEnFirebase(ArrayList<Object> listaUsuarios) {
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("" + usuario.getUid());

        myRef.child("listaUsuarios").setValue(listaUsuarios);
        //myRef.setValue("siiiiiiiiiiii");

        Log.v("FirebaseEmail", "UsuarioDAO:listaLongitud:" + listaUsuarios);
    }
    */
}
