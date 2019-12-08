package com.example.magistore.vista;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInic extends Fragment {
private Button btn_session;
private TextView tv_sesion;
private FirebaseAuth mAuth;

    public FragmentInic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_inic, container, false);

       //// firebaseAuth.getInstance();

        btn_session=vista. findViewById(R.id.btn_of_session);
        tv_sesion=vista.findViewById(R.id.tv_saludo_session);

        btn_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //firebaseAuth.signOut();
               btn_session.setVisibility(View.GONE);
               // stopButton.setVisibility(View.VISIBLE);

            }
        });



        return vista;
    }

}
