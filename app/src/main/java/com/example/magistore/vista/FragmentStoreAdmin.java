package com.example.magistore.vista;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.example.magistore.modelo.Post;
import com.example.magistore.modelo.PostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentStoreAdmin extends Fragment {
    private ArrayList<Post> postList = new ArrayList<Post>();
    private PostAdapter postAdapter;
    private DatabaseReference mDatabase;
    private RecyclerView recyclerview;
    private String usuario, megustas, comento, img;
    private FirebaseAuth mAuth;

    public FragmentStoreAdmin() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        mAuth = FirebaseAuth.getInstance();
        recyclerview = view.findViewById(R.id.rcv_store);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance().getReference();
      //  String ide = mAuth.getCurrentUser().getUid();
        mDatabase.child("img_desing").addValueEventListener(new ValueEventListener() {

            //mDatabase.child("img_desing").addValueEventListener(new ValueEventListener() {
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


                        postAdapter = new PostAdapter(postList, R.layout.list_store, getContext());
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