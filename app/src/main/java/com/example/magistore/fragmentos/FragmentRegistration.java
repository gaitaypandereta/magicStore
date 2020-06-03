package com.example.magistore.fragmentos;

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
import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.example.magistore.modelos.Retrofit.CukisApi;
import com.example.magistore.modelos.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegistration extends Fragment {
    private EditText edit_telefono, edit_facebook, edit_twitter, edit_instagram, edit_direccion, hide_nombre, hide_id, hide_email, hide_sexo;
    private String telefono, facebok, twiter, instagra, direccion, h_id, h_nombre, h_email, h_sexo;
    private SeekBar edad;
    private TextView valor_edad;
    private RadioGroup radioGroup;
    private RadioButton radioButtonChico, radioButtonChica, radioButtonOtro;
    private Button btn_guardar_user, btn_en_otro_momento;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference dR;
    private CukisApi api;
    private String TAG;

    public FragmentRegistration() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vista = inflater.inflate(R.layout.fragment_registration, container, false);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        hide_nombre =vista.findViewById(R.id.edi_hide_nombre);
        hide_email =vista.findViewById(R.id.edi_hide_email);
        hide_id =vista.findViewById(R.id.edi_hide_id);
        hide_sexo = vista.findViewById(R.id.edi_hide_sexo);
        edit_telefono = vista.findViewById(R.id.editText_telefono);
        edit_facebook = vista.findViewById(R.id.editText_facebook);
        edit_twitter = vista.findViewById(R.id.editText_twitter);
        edit_instagram = vista.findViewById(R.id.editText_instagram);
        edit_direccion = vista.findViewById(R.id.editText_direccion);
        edad = vista.findViewById(R.id.seekBar);
        valor_edad = vista.findViewById(R.id.seekBar_edad);
        radioButtonChico = vista.findViewById(R.id.radioButtonMasculino);
        radioButtonChica = vista.findViewById(R.id.radioButtonFemenino);
        radioButtonOtro = vista.findViewById(R.id.radioButtonOtro);
        getUsuario();

        //SeekBack edad
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

                }
            });


        }

        radioGroup = vista.findViewById(R.id.radioGrou);

        btn_en_otro_momento = vista.findViewById(R.id.btn_ir_a_session);
        btn_en_otro_momento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentNews());
            }
        });
        btn_guardar_user = vista.findViewById(R.id.btn_guardar_usuario);
        btn_guardar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarRegistro();
                guardarUsuarioMysql(getUsuarioMysql());
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentNews());


            }
        });
        //En este caso implementamos tambien Retrofit llamando a su clase.
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://192.168.43.113:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CukisApi.class);
        String id = mAuth.getCurrentUser().getUid();


        return vista;

    }


    //Método que devuelve el valor de RadioButton seleccionado

    public String valorSexo(RadioGroup sexoChico) {
        int sex = sexoChico.getCheckedRadioButtonId();
        String sexo;
        if (sex == R.id.radioButtonMasculino) {
            sexo = "chico";
        } else if (sex == R.id.radioButtonFemenino) {
            sexo = "chica";
        } else {
            sexo = " compra para chico y chica";
        }
        return sexo;
    }

    //Método que actualiza los datos de registro en firebase.

    public void actualizarRegistro() {
        h_email = hide_email.getText().toString();
        h_nombre = hide_nombre.getText().toString();
        h_id = hide_id.getText().toString();
        telefono = edit_telefono.getText().toString();
        facebok = edit_facebook.getText().toString();
        twiter = edit_twitter.getText().toString();
        instagra = edit_instagram.getText().toString();
        direccion = edit_direccion.getText().toString();
        String edad = valor_edad.getText().toString();
        String sex = valorSexo(radioGroup);
        String id = mAuth.getCurrentUser().getUid();
        dR = FirebaseDatabase.getInstance().getReference("users").child(id);
        dR.child("telefono").setValue(telefono);
        dR.child("facebok").setValue(facebok);
        dR.child("twiter").setValue(twiter);
        dR.child("instagra").setValue(instagra);
        dR.child("direccion_envio").setValue(direccion);
        dR.child("sexo").setValue(sex);
        dR.child("edad").setValue(edad);


    }

    //Método que trae desde firebase los datos de registro para mostrarlos en la vista.

    private void getUsuario() {

        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("users").child(id).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String telefono = dataSnapshot.child("telefono").getValue().toString();
                    String facebook = dataSnapshot.child("facebok").getValue().toString();
                    String twitter = dataSnapshot.child("twiter").getValue().toString();
                    String instagram = dataSnapshot.child("instagra").getValue().toString();
                    String direccion = dataSnapshot.child("direccion_envio").getValue().toString();
                    String sexo = dataSnapshot.child("sexo").getValue().toString();
                    String edad = dataSnapshot.child("edad").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    String ide = mAuth.getCurrentUser().getUid();
                    hide_id.setText(ide);
                    hide_email.setText(email);
                    hide_nombre.setText(nombre);
                    edit_direccion.setText(direccion);
                    valor_edad.setText(edad);
                    edit_facebook.setText(facebook);
                    edit_instagram.setText(instagram);
                    edit_telefono.setText(telefono);
                    edit_twitter.setText(twitter);
                    mostrarSexo(sexo);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //Método que guarda y actualiza los datos de registro en mysql por medio de retrofit.

    public void guardarUsuarioMysql(Usuario usuario){


        Call<Usuario> call = api.postUsuario(usuario);


        api.postUsuario(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }

        });


    }

    public void  mostrarSexo(String sexo) {

        if (sexo == "chico") {
            radioButtonChico.setChecked(true);
            hide_sexo.setText("chico");
        } else if (sexo == "chica") {
            radioButtonChica.setChecked(true);
            hide_sexo.setText("chica");
        } else {
            radioButtonOtro.setChecked(true);
            hide_sexo.setText("Compra para los dos");

        }
    }
    //Método que recoge los datos al método anterior guardarUsuarioMysql().

    public Usuario getUsuarioMysql(){

        h_email = hide_email.getText().toString();
        h_nombre = hide_nombre.getText().toString();
        h_id = hide_id.getText().toString();
        telefono = edit_telefono.getText().toString();
        facebok = edit_facebook.getText().toString();
        twiter = edit_twitter.getText().toString();
        instagra = edit_instagram.getText().toString();
        direccion = edit_direccion.getText().toString();
        int edad =Integer.parseInt (valor_edad.getText().toString());
        h_sexo = hide_sexo.getText().toString();


        Usuario usuario = new Usuario(h_id,telefono, direccion, edad, h_email, facebok,instagra, h_nombre, h_sexo, twiter);

        return usuario;
    }


}