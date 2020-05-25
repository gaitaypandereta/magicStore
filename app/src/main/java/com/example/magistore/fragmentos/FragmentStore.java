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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.example.magistore.fragmentos.FragmentStoreWeb;
import com.example.magistore.modelos.MessageAFragmentFragment;
import com.example.magistore.modelos.OttoClass;
import com.example.magistore.modelos.Post;
import com.example.magistore.modelos.PostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class FragmentStore extends Fragment {
    private ArrayList<Post> postList = new ArrayList<Post>();
    private PostAdapter postAdapter;
    private DatabaseReference mDatabase;
    private RecyclerView recyclerview;
    private String usuario, megustas, comento, img;
    private FirebaseAuth mAuth;
    private ImageView imageView, imageView_store;


    public FragmentStore() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        mAuth = FirebaseAuth.getInstance();
        recyclerview = view.findViewById(R.id.rcv_store);
        imageView_store = view.findViewById(R.id.imv_store_not);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String ide = mAuth.getCurrentUser().getUid();


        mDatabase.child("img_desing").orderByChild("id").equalTo(ide).addValueEventListener(new ValueEventListener() {

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


                    if(postList.size()==0)
                        imageView_store.setVisibility(View.VISIBLE);

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


    @Override
    public void onResume() {
        super.onResume();
        OttoClass.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        OttoClass.getBus().unregister(this);
    }

    @Subscribe
    public void receiveMessageAtoF(MessageAFragmentFragment message) {

        String mg = message.getAMensaje();
        deletePost(mg);

    }


    public void deletePost(String mg) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query query = ref.child("img_desing").orderByChild("decripcion").equalTo(mg);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                    postAdapter.notifyDataSetChanged();
                }
                Toast.makeText(getContext(), "Se ha eliminado correctamente", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "no ha podido ser", Toast.LENGTH_LONG).show();
            }
        });
    }


}