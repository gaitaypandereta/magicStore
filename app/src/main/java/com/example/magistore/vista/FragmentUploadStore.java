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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
public class FragmentUploadStore extends Fragment {
private static  final int PICK_IMAGE_REQUEST =1;
private Button btn_chooseImage;
private Button btn_upload;
private EditText descripcion, user;
private ImageView  chooseImageView;
private ProgressBar uploadProgressBar;


private Uri mImageUri;

private StorageReference mStorageReference;
private DatabaseReference mDatabase;
private StorageTask mUploadTask;
private FirebaseAuth mAuth;
private FirebaseFirestore mfirestore;

    public FragmentUploadStore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     View view =inflater.inflate(R.layout.fragment_upload_store, container, false);
     btn_chooseImage=view.findViewById(R.id.btn_choose_img);
     btn_upload=view.findViewById(R.id.ulpoadBtn);
     descripcion =view.findViewById(R.id.ed_description);
     user=view.findViewById(R.id.ed_user);
     chooseImageView=view.findViewById(R.id.chooseImageView);
     uploadProgressBar=view.findViewById(R.id.progress_bar);

     mStorageReference= FirebaseStorage.getInstance().getReference("img_upload");
     mDatabase= FirebaseDatabase.getInstance().getReference("img_upload");
     mAuth=FirebaseAuth.getInstance();
     mfirestore=FirebaseFirestore.getInstance();
     btn_upload.setVisibility(View.GONE);



     btn_chooseImage.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
           openFileChooser();
             btn_chooseImage.setVisibility(View.GONE);
             btn_upload.setVisibility(View.VISIBLE);
         }
     });


     btn_upload.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mUploadTask !=null && mUploadTask.isInProgress()){
                Toast.makeText(getContext(), "Espere por favor, aún se está subiendo el archivo anterior"+mImageUri, Toast.LENGTH_SHORT).show();

            }else{
                uploadFile();
                btn_chooseImage.setVisibility(View.VISIBLE);
                btn_upload.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Subiendo post actual, espere por favor:"+mImageUri, Toast.LENGTH_SHORT).show();


            }

        }
    });


     return view;
    }

    private void openFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Glide.with(getContext()).load(mImageUri).into(chooseImageView);

        }

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
                           String my_user=user.getText().toString().trim();
                           String my_img=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                           String my_descripcion=descripcion.getText().toString().trim();
                           Toast.makeText(getContext(), "Su pots se ha creado correctamente", Toast.LENGTH_SHORT).show();
                           Post post  =new Post(""+id_user, "@"+my_user, ""+my_descripcion  , ""+my_img  , "4");
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
            Toast.makeText(getContext(), "No ha seleccionado ningún archivo", Toast.LENGTH_SHORT).show();
        }


    }




}
