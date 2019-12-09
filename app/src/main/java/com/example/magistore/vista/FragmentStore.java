package com.example.magistore.vista;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.magistore.R;
import com.example.magistore.modelo.AdapterPost;
import com.example.magistore.modelo.Post;
import com.example.magistore.modelo.PostAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
public class FragmentStore extends Fragment {
    private List<Post> postList = new ArrayList<>();
    private PostAdapter mAdapter;
    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager gestor;
    private DatabaseReference mDatabaseRef;
    private FirebaseDatabase mDatabase;
    private FirebaseFirestore mFirestore;
    public FragmentStore() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
/*
        mFirestore=FirebaseFirestore.getInstance();
        CollectionReference query = mFirestore.collection("img_upload");
        FirestoreRecyclerOptions<Post> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post.class).build();
        mAdapter =new AdapterPost(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        rv.setAdapter(mAdapter);
*/

       FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference myRef = database.getReference("users");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    Post post = dataSnapshot.getValue(Post.class);
                    postList.add(post);

                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        reciclador =view.findViewById(R.id.rcv_store);
       // reciclador.setHasFixedSize(true);
        gestor = new LinearLayoutManager(getContext());
        reciclador.setLayoutManager(gestor);
        adaptador = new PostAdapter(postList);
        reciclador.setAdapter(adaptador);
        reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View hijo = rv.findChildViewUnder(e.getX(), e.getY());
                if (hijo != null && gestureDetector.onTouchEvent(e)){

                    int position = rv.getChildAdapterPosition(hijo);
                    Toast.makeText(getContext(), "HAS TOCADO LA FOTO DE  "+postList.get(position).getDecripcion(), Toast.LENGTH_SHORT ).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        return view;

    }

}

