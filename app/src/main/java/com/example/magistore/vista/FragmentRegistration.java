package com.example.magistore.vista;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.magistore.R;
import com.example.magistore.modelo.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegistration extends Fragment {
    private EditText  edit_telefono, edit_facebook, edit_twitter, edit_instagram, edit_direccion;
    private String telefono, facebok, twiter, instagra, direccion, eda, seso;
    private SeekBar edad;
    private TextView valor_edad;
    private RadioGroup radioGroup;
    private RadioButton radioButtonChico, getRadioButtonChica, getRadioButtonOtro;
    private Button btn_guardar_user;
    private Usuario usuario;
    private String id;
    private View vista;
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private  FirebaseFirestore db=FirebaseFirestore.getInstance();

    public FragmentRegistration() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vista = inflater.inflate(R.layout.fragment_registration, container, false);

       // mAuth = FirebaseAuth.getInstance();
       // mDatabase= FirebaseDatabase.getInstance().getReference();

        edit_telefono = vista.findViewById(R.id.editText_telefono);
        edit_facebook = vista.findViewById(R.id.editText_facebook);
        edit_twitter = vista.findViewById(R.id.editText_twitter);
        edit_instagram = vista.findViewById(R.id.editText_instagram);
        edit_direccion = vista.findViewById(R.id.editText_direccion);
        edad = vista.findViewById(R.id.seekBar);
        valor_edad = vista.findViewById(R.id.seekBar_edad);
        if (edad != null) {
            edad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Write code to perform some action when progress is changed.
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // Write code to perform some action when touch is started.
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // Write code to perform some action when touch is stopped.
                    valor_edad.setText(edad.getProgress() + "");
                    // Toast.makeText(MainActivity.this, "Current value is " + edad.getProgress(), Toast.LENGTH_SHORT).show();
                }
            });


        }

        radioGroup = vista.findViewById(R.id.radioGrou);

        btn_guardar_user = vista.findViewById(R.id.btn_guardar_usuario);
        btn_guardar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, " NO HAS INTRODUCIDO NINGÚN DATO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(Color.rgb(28, 181, 148));
                snackbar.show();

              //  Snackbar.make(vista, "FALTAN CAMPOS OBLIGATORIOS", Snackbar.LENGTH_SHORT).show();

               // actualizarRegistro();
            }
        });


        return vista;

    }



    //Volvemos todos los campos del formulario a su valor por defecto
    public void limpiarCampos() {
        edit_telefono.setText(" ");
        edit_facebook.setText(" ");
        edit_twitter.setText(" ");
        edit_instagram.setText(" ");
        edit_direccion.setText(" ");
        edad.setProgress(0);
        valor_edad.setText(edad.getProgress() + "");
        radioGroup.clearCheck();
        edit_telefono.requestFocus();
    }


    //Método que devuelve el valor de RadioButton seleccionado
    public String valorSexo(RadioGroup sexoChico) {
        int sex = sexoChico.getCheckedRadioButtonId();
        String sexo;
        if (sex == R.id.radioButonMasculino) {
            sexo = "chico";
        } else if (sex == R.id.radioButonFemenino) {
            sexo = "chica";
        } else {
            sexo = "---NO contesta en SEXO---";
        }
        return sexo;
    }


    public void actualizarRegistro() {
       // String id=usuario.getId();

        String nombre=usuario.getNombre();
        String email=usuario.getEmail();
        String telefono = edit_telefono.getText().toString();
        String facebok = edit_facebook.getText().toString();
        String twiter = edit_twitter.getText().toString();
        String instagra = edit_instagram.getText().toString();
        String direccion = edit_direccion.getText().toString();
        String edad = valor_edad.getText().toString();
        String sex = valorSexo(radioGroup);
        String pasword=usuario.getPass();
        int saldo =usuario.getSaldo();
/*
        Map<String, Object> map = new HashMap<>();
        map.put("telefono", telefono);
        map.put("facebok", facebok);
        map.put("twiter", twiter);
        map.put("instagra", instagra);
        map.put("direccion_envio", direccion);
        id="2vEM0lt2DnfgorlgbGzqMXaVn4h1";
      //  mDatabase.child("users").child(id).updateChildren(map);
       // db.collection("users").document(id).update(map);
       */
/*
        id="95PBlCffMDdELgLm7LRajVYXRSQ2";
        try {
       DatabaseReference     mDatabase= FirebaseDatabase.getInstance().getReference("users").child(id);
           usuario=new  Usuario(id, usuario.getNombre(), usuario.getEmail(), telefono,  facebok, twiter, instagra, direccion, edad, sex, usuario.getPass(), usuario.getSaldo());
          mDatabase.setValue(usuario);

            //
            // mDatabase.child("users").child(id).child(facebok).setValue("f345667");
        } catch (Exception e) {

            Toast.makeText(getContext(), "noooooooooooooooooooooo", Toast.LENGTH_SHORT).show();
        }
        */
    }


}