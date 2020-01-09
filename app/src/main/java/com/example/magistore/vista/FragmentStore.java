package com.example.magistore.vista;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.magistore.R;
import com.example.magistore.modelo.Post;
import com.example.magistore.modelo.PostAdapter;
import com.example.magistore.modelo.Tejido;
import com.example.magistore.modelo.TejidoAdapter;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
public class FragmentStore extends Fragment {
    private ArrayList<Tejido> tejidosList = new ArrayList<Tejido>();
    private TejidoAdapter tejidoAdapter;
    private DatabaseReference mDatabase;
    private RecyclerView recyclerview;
    private String nombre, megusta, descripcion, img;




    public FragmentStore() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        recyclerview=view.findViewById(R.id.rcv_store);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        mDatabase=FirebaseDatabase.getInstance().getReference();
        mDatabase.child("relacion_tejidos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    if (dataSnapshot.exists()) {
                        tejidosList.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            nombre = ds.child("nombre").getValue().toString();
                            megusta = ds.child("megusta").getValue().toString();
                            descripcion = ds.child("descripcion").getValue().toString();
                            img = ds.child("url_img").getValue().toString();

                            tejidosList.add(new Tejido(nombre, megusta, descripcion, img));

                        }



                        tejidoAdapter=new TejidoAdapter(tejidosList, R.layout.list_store, getContext());
                        recyclerview.setAdapter(tejidoAdapter);

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

StorageReference fileReference = mStorageRef.child(phone.getText().toString().trim()
                    + "." + getFileExtension(mImageUri));

fileReference.putFile(File).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
        {
             filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                           //Do what you want with the url
                        }
            Toast.makeText(MtActivity.this, "Upload Done", Toast.LENGTH_LONG).show();
        }
    });












           reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
               GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                   @Override
                   public boolean onSingleTapUp(MotionEvent e) {
                       return true;
                   }
               });

               @Override
               public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                   View myChail = rv.findChildViewUnder(e.getX(), e.getY());
                   if (myChail != null && gestureDetector.onTouchEvent(e)){
                       int position = rv.getChildAdapterPosition(myChail);
                       Toast.makeText(getContext(), "HAS TOCADO LA FOTO .. ", Toast.LENGTH_SHORT ).show();
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

/*

StorageReference fileReference = mStorageRef.child(phone.getText().toString().trim()
                    + "." + getFileExtension(mImageUri));

fileReference.putFile(File).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
        {
             filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                           //Do what you want with the url
                        }
            Toast.makeText(MtActivity.this, "Upload Done", Toast.LENGTH_LONG).show();
        }
    });


    Glide
        .with(mContext)
        .load(productoModel.getImagen())
        .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                productoViewHolder.mProgressBar.setVisibility(View.GONE);
                productoViewHolder.mImagenProducto.setVisibility(View.VISIBLE);
                productoViewHolder.mImagenProducto.setImageResource(R.drawable.ic_error_black_24dp);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                productoViewHolder.mProgressBar.setVisibility(View.GONE);
                productoViewHolder.mImagenProducto.setVisibility(View.VISIBLE);
                return false;
            }
        })

        .into(productoViewHolder.mImagenProducto);










*/
