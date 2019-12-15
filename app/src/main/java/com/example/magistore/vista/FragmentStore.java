package com.example.magistore.vista;
import android.os.Bundle;
import androidx.annotation.NonNull;
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
import com.example.magistore.modelo.Post;
import com.example.magistore.modelo.PostAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class FragmentStore extends Fragment {
    private ArrayList<Post> postList =new ArrayList<Post>();
    private PostAdapter postAdapter;
    private DatabaseReference mDatabase;
    private RecyclerView recyclerview;
    private String usuario, megustas, comento, img;




    public FragmentStore() {

    }


   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        recyclerview=view.findViewById(R.id.rcv_store);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        mDatabase=FirebaseDatabase.getInstance().getReference();
        mDatabase.child("img_upload").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    if (dataSnapshot.exists()) {
                        postList.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                             usuario = ds.child("user").getValue().toString();
                             megustas = ds.child("megusta").getValue().toString();
                             comento = ds.child("decripcion").getValue().toString();
                             img = ds.child("url_img").getValue().toString();

                            postList.add(new Post(usuario, img, comento, megustas));

                            Toast.makeText(getContext(), usuario,Toast.LENGTH_SHORT ).show();

                        }


                    postAdapter=new PostAdapter(postList, R.layout.list_store, getContext());
                    recyclerview.setAdapter(postAdapter);

                    }
                }catch ( Exception e){
                    Toast.makeText(getContext(), "Ir a tienda ", Toast.LENGTH_SHORT ).show();
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

/*
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
                       Toast.makeText(getContext(), "HAS TOCADO LA FOTO DE  ", Toast.LENGTH_SHORT ).show();
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
*/
        return view;

    }



}

