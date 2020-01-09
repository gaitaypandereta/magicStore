package com.example.magistore.vista;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.magistore.R;
import com.example.magistore.modelo.Post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUploadDesing extends Fragment {
    private Button btn_upload;
    private EditText descripcion, nombre, url_img;
    private ProgressBar uploadProgressBar;
    private Uri mImageUri;
    private StorageReference mStorageReference;
    private DatabaseReference mDatabase;
    private StorageTask mUploadTask;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mfirestore;
    FirebaseStorage storage;
    StorageReference storageReference;

    public FragmentUploadDesing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_upload_desing, container, false);
        btn_upload=view.findViewById(R.id.ulpoadBtn);
        descripcion =view.findViewById(R.id.ed_description);
        nombre=view.findViewById(R.id.ed_user);
        url_img=view.findViewById(R.id.ed_url_img);
        uploadProgressBar=view.findViewById(R.id.progress_bar);

        mStorageReference= FirebaseStorage.getInstance().getReference("relacion_tejidos");
        mDatabase= FirebaseDatabase.getInstance().getReference("relacion_tejidos");
        mAuth=FirebaseAuth.getInstance();
        mfirestore=FirebaseFirestore.getInstance();

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUploadTask !=null && mUploadTask.isInProgress()){
                    Toast.makeText(getContext(), "Espere por favor, aún se está subiendo el diseño anterior", Toast.LENGTH_SHORT).show();

                }else{
                    uploadFile();
                    btn_upload.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Subiendo diseño actual, espere por favor:", Toast.LENGTH_SHORT).show();


                }

            }
        });


        return view;
    }


    private String getFileExtension(Uri uri){
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime =MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadFile(){
        if(mImageUri !=null) {
            StorageReference fileReference =mStorageReference.child(System.currentTimeMillis()
                    + ", " + getFileExtension(mImageUri));

            uploadProgressBar.setVisibility(View.VISIBLE);
            uploadProgressBar.setIndeterminate(true);


            mUploadTask=fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler =new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    uploadProgressBar.setVisibility(View.VISIBLE);
                                    uploadProgressBar.setIndeterminate(false);
                                    uploadProgressBar.setProgress(0);
                                }
                            },500);
                            String id_user =mAuth.getCurrentUser().getUid();
                            String my_user=nombre.getText().toString().trim();
                            String my_img=url_img.getText().toString().trim();
                            String my_descripcion=descripcion.getText().toString().trim();
                            Post post  =new Post(""+id_user, "@"+my_user, ""+my_descripcion  , my_img  , "4");
                            String postId=mDatabase.push().getKey();
                            mDatabase.child(postId).setValue(post);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress=(100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            uploadProgressBar.setProgress((int) progress);

                        }
                    });
        }else{
            Toast.makeText(getContext(), "No ha introducido ninguna url de  archivo", Toast.LENGTH_SHORT).show();
        }


    }


}
