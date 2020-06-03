package com.example.magistore.fragmentos;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegitrationInit extends Fragment {

    private Button btn_register, btn_session;
    private EditText edit_nombre, edit_email, edit_pass;
    private String nombre, email, password;
    private ProgressBar barraProgreso;
    private View vista;


    public FragmentRegitrationInit() {
        // Required empty public constructor
    }

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_registration_init, container, false);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        edit_nombre = vista.findViewById(R.id.edit_nombre_init);
        edit_email = vista.findViewById(R.id.edit_email_init);
        edit_pass = vista.findViewById(R.id.edit_pasword_init);
        btn_register = vista.findViewById(R.id.btn_regitrar_user);
        btn_session = vista.findViewById(R.id.btn_inicio_sesion);

        btn_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentLogin());
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = edit_nombre.getText().toString().trim();
                email = edit_email.getText().toString().trim();
                password = edit_pass.getText().toString().trim();


                //Validación de datos de entrada como email, nombre y password.

                Pattern patron = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher elMatcher = patron.matcher(email);

                if (!elMatcher.matches()) {
                    mostrarSnackbar("ESTE E-MAIL NO ES VÁLIDO");
                } else if (password.length() < 6) {
                    mostrarSnackbar("LAS CONTRASEÑA DEBE TENER POR LO MENOS 6 CARÁCTERES");
                    ;
                } else if (nombre.length() < 3 || nombre.length() > 15) {
                    mostrarSnackbar("LOS NOMBRES DEBEN DE TENER ENTRE 3 Y 15 CARÁCTERES");

                } else {


                    registerUser();
                }


            }
        });
        //Checkbox aceptación condiciones de registro inicial.

        CheckBox checkbox = vista.findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    edit_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    edit_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });


        return vista;
    }
    //Método para registro de usuario.

    private void registerUser() {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("email", email);
                    map.put("password", password);
                    map.put("telefono", "");
                    map.put("facebok", "");
                    map.put("twiter", "");
                    map.put("instagra", "");
                    map.put("direccion_envio", "");
                    map.put("edad", "");
                    map.put("sexo", "");
                    map.put("comenta_admin", "SIN VALORACIÓN");
                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("users").child(id).setValue(map).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                Toast.makeText(getContext(), "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                                ((MainActivity) getActivity()).cambiarFragmento(new FragmentLogin());


                            }

                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "No se pudo ser. Revisa los datos", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    private void mostrarSnackbar(String mensaje) {
        final Snackbar snackbar = Snackbar.make(getView(), mensaje, Snackbar.LENGTH_LONG);
        snackbar.setAction("ACEPTAR", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


}

