package com.example.magistore.fragmentos;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.example.magistore.modelos.Post;
import com.example.magistore.modelos.PostAdapterAdmin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class FragmentStoreAdmin extends Fragment {
    private List<Post> postList = new ArrayList<>();
    private PostAdapterAdmin postAdapter;
    private Post post;
    private DatabaseReference mDatabase;
    private String usuario, megustas, comento, img;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private RecyclerView recyclerview;
    private FirebaseDatabase database;
    private EditText campo;
    private Button fin_campanha, edit, save;
    private List<String> campos;
    private Spinner spinner_campos;

    public FragmentStoreAdmin() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_admin, container, false);


        mAuth = FirebaseAuth.getInstance();
        recyclerview = view.findViewById(R.id.rcv_store_admin);
        fin_campanha = view.findViewById(R.id.btn_fin_campnha);
        edit=view.findViewById(R.id.btn_edit_admin);
        save=view.findViewById(R.id.btn_save_admin);
        spinner_campos = view.findViewById(R.id.spinner_campos);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance().getReference();

        campos = new ArrayList<String>();
        campos.add("ORDENAR POR MEGUSTA");
        campos.add("ORDENAR POR ID");

        ArrayAdapter<String> adapterCampos = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, campos);
        spinner_campos.setAdapter(adapterCampos);
        String campo = spinner_campos.getSelectedItem().toString();
        String id = mAuth.getCurrentUser().getUid();

        spinner_campos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (campos.get(position) == "ORDENAR POR MEGUSTA"){

                    mDatabase.child("img_desing").orderByChild("megusta").addValueEventListener(new ValueEventListener() {


                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                if (dataSnapshot.exists()) {
                                    postList.clear();
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        usuario = ds.child("user").getValue().toString();
                                        megustas = ds.child("megusta").getValue().toString();
                                        comento = ds.child("decripcion").getValue().toString();
                                        img = ds.child("url_img").getValue().toString();
                                        postList.add(new Post(usuario, img, comento, megustas));

                                    }


                                    postAdapter = new PostAdapterAdmin(postList, R.layout.list_store_admin, getContext());
                                    recyclerview.setAdapter(postAdapter);


                                }
                            } catch (Exception e) {

                                ((MainActivity) getActivity()).cambiarFragmento(new FragmentStoreWeb());
                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {


                    mDatabase.child("img_desing").orderByChild("id").addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                if (dataSnapshot.exists()) {
                                    postList.clear();
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        usuario = ds.child("user").getValue().toString();
                                        megustas = ds.child("megusta").getValue().toString();
                                        comento = ds.child("decripcion").getValue().toString();
                                        img = ds.child("url_img").getValue().toString();
                                        postList.add(new Post(usuario, img, comento, megustas));

                                    }


                                    postAdapter = new PostAdapterAdmin(postList, R.layout.list_store, getContext());
                                    recyclerview.setAdapter(postAdapter);


                                }
                            } catch (Exception e) {

                                ((MainActivity) getActivity()).cambiarFragmento(new FragmentStoreWeb());
                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;



    }


    @Override
    public void onStart() {
        super.onStart();
        //-----
    }

    @Override
    public void onStop() {
        super.onStop();
        //-----
    }

}