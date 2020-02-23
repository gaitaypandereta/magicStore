package com.example.magistore.vista;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.example.magistore.modelo.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegistration extends Fragment {
    private EditText  edit_telefono, edit_facebook, edit_twitter, edit_instagram, edit_direccion;
    private String telefono, facebok, twiter, instagra, direccion;
    private SeekBar edad;
    private TextView valor_edad;
    private RadioGroup radioGroup;
    private RadioButton radioButtonChico, radioButtonChica, radioButtonOtro;
    private Button btn_guardar_user, btn_en_otro_momento;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private  DatabaseReference dR ;

    public FragmentRegistration() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vista = inflater.inflate(R.layout.fragment_registration, container, false);
        mAuth= FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        edit_telefono = vista.findViewById(R.id.editText_telefono);
        edit_facebook = vista.findViewById(R.id.editText_facebook);
        edit_twitter = vista.findViewById(R.id.editText_twitter);
        edit_instagram = vista.findViewById(R.id.editText_instagram);
        edit_direccion = vista.findViewById(R.id.editText_direccion);
        edad = vista.findViewById(R.id.seekBar);
        valor_edad = vista.findViewById(R.id.seekBar_edad);
        radioButtonChico=vista.findViewById(R.id.radioButtonMasculino);
        radioButtonChica=vista.findViewById(R.id.radioButtonFemenino);
        radioButtonOtro=vista.findViewById(R.id.radioButtonOtro);
        getUsuario();

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

       // String id=mAuth.getCurrentUser().getUid();

        btn_en_otro_momento=vista.findViewById(R.id.btn_ir_a_session);
        btn_en_otro_momento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentLogin());
            }
        });
        btn_guardar_user = vista.findViewById(R.id.btn_guardar_usuario);
        btn_guardar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               actualizarRegistro();
/*
                Snackbar snackbar = Snackbar.make(view, " NO HAS INTRODUCIDO NINGÚN DATO", Snackbar.LENGTH_LONG).setAction("Action", null);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(Color.rgb(28, 181, 148));
                snackbar.show();
                limpiarCampos();
*/
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentLogin());


            }
        });




        return vista;

    }



    //Volvemos todos los campos del formulario a su valor por defecto

  /*  public void limpiarCampos() {
        edit_telefono.setText("");
        edit_facebook.setText("");
        edit_twitter.setText("");
        edit_instagram.setText("");
        edit_direccion.setText("");
        edad.setProgress(0);
        valor_edad.setText(edad.getProgress() + "");
        radioGroup.clearCheck();
        edit_telefono.requestFocus();
    }
*/

    //Método que devuelve el valor de RadioButton seleccionado

    public String valorSexo(RadioGroup sexoChico) {
        int sex = sexoChico.getCheckedRadioButtonId();
        String sexo;
        if (sex == R.id.radioButtonMasculino) {
            sexo = "chico";
        } else if (sex == R.id.radioButtonFemenino) {
            sexo = "chica";
        } else {
            sexo = "---NO contesta en SEXO---";
        }
        return sexo;
    }


    public void actualizarRegistro() {
        telefono = edit_telefono.getText().toString();
        facebok = edit_facebook.getText().toString();
        twiter = edit_twitter.getText().toString();
        instagra = edit_instagram.getText().toString();
        direccion = edit_direccion.getText().toString();
        String edad = valor_edad.getText().toString();
        String sex = valorSexo(radioGroup);

        String id=mAuth.getCurrentUser().getUid();
        dR = FirebaseDatabase.getInstance().getReference("users").child(id);
        dR.child("telefono").setValue(telefono);
        dR.child("facebok").setValue(facebok);
        dR.child("twiter").setValue(twiter);
        dR.child("instagra").setValue(instagra);
        dR.child("direccion_envio").setValue(direccion);
        dR.child("sexo").setValue(sex);
        dR.child("edad").setValue(edad);

    }


    private void getUsuario(){

       String id=mAuth.getCurrentUser().getUid();
Toast.makeText(getContext(), "este es el id:"+id, Toast.LENGTH_SHORT).show();
        mDatabase.child("users").child(id).addValueEventListener(new ValueEventListener() {

           @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String telefono=dataSnapshot.child("telefono").getValue().toString();
                    String facebook=dataSnapshot.child("facebok").getValue().toString();
                    String twitter=dataSnapshot.child("twiter").getValue().toString();
                    String instagram=dataSnapshot.child("instagra").getValue().toString();
                    String direccion=dataSnapshot.child("direccion_envio").getValue().toString();
                    String sexo=dataSnapshot.child("sexo").getValue().toString();
                    String edad=dataSnapshot.child("edad").getValue().toString();

                    edit_direccion.setText(direccion);
                    edit_facebook.setText(facebook);
                    edit_instagram.setText(instagram);
                    edit_telefono.setText(telefono);
                    edit_twitter.setText(twitter);
                    valor_edad.setText(edad);
                    mostrarSexo(sexo);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void mostrarSexo(String sexo){

        if(sexo=="chico"){
            radioButtonChico.setChecked(true);
        } else if (sexo=="chica"){
            radioButtonChica.setChecked(true);
        }else{ radioButtonOtro.setChecked(true);

        }
    }
// origin-gaitaypandereta 
}