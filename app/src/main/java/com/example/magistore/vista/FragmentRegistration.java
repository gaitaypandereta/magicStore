package com.example.magistore.vista;


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
    String id;
    private FirebaseFirestore mFirestore;
   /* private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
*/
    public FragmentRegistration() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_registration, container, false);

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
Toast.makeText(getContext(), id+"donde tiene que ser", Toast.LENGTH_SHORT).show();


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

        String telefono = edit_telefono.getText().toString();
        String facebok = edit_facebook.getText().toString();
        String twiter = edit_twitter.getText().toString();
        String direccion = edit_direccion.getText().toString();
        String instagra = edit_instagram.getText().toString();
        String edad = valor_edad.getText().toString();
        String sex = valorSexo(radioGroup);

        Map<String, Object> map = new HashMap<>();
        map.put("telefono", telefono);
        map.put("facebok", facebok);
        map.put("twiter", twiter);
        map.put("instagra", instagra);
        map.put("direccion_envio", direccion);
        map.put("edad", edad);
        map.put("sexo", sex);




    }


}