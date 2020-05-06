package com.example.magistore.vista;
import android.content.ClipData;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {
    private EditText ed_email, ed_password;
    private String email="";
    private String password="";
    private FirebaseAuth mAuth;
    private Button btn_login, btn_registro;
    private Button btn_olvido_pass;
    private MenuItem secondItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();
        ed_email=vista.findViewById(R.id.editText_login_email);
        ed_password=vista.findViewById(R.id.editText_login_pass);
        btn_login=vista.findViewById(R.id.btn_login);
        btn_registro=vista.findViewById(R.id.btn_registro);
        btn_olvido_pass= vista.findViewById(R.id.btn_olvido_reset_pass);
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentConditions());
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=ed_email.getText().toString().trim();
                password=ed_password.getText().toString().trim();

                if(!email.isEmpty() && !password.isEmpty()){
                   loginUser();

                }else{
                    Toast.makeText(getContext(), "Ingrese correctamente los datos", Toast.LENGTH_SHORT).show();
                }





            }
        });

        btn_olvido_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentRessetPass());
            }
        });

        return vista;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
      //  updateUI(currentUser);
    }

 private void   loginUser(){
   mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
       @Override
       public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){

               ((MainActivity) getActivity()).cambiarFragmento(new FragmentNews());


           }else{

               Toast.makeText(getContext(), "Lo sentimos, no ha podido iniciar sesión", Toast.LENGTH_SHORT).show();
           }

       }
   });

    }


}
