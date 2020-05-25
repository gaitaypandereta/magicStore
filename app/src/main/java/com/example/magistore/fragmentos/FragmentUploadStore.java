package com.example.magistore.fragmentos;
import android.app.AlertDialog;
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
import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.example.magistore.modelos.Post;
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
public class FragmentUploadStore extends Fragment {
    private static  final int PICK_IMAGE_REQUEST =1;
    private Button btn_upload, btn_tu_design, btn_cancelar, btn_chooseImage;
    private EditText descripcion, user, cukis;
    private ImageView  chooseImageView;
    private ProgressBar uploadProgressBar;
    private String my_img=null;
    private Uri mImageUri;
    private View view;
    private StorageReference mStorageReference;
    private DatabaseReference mDatabase;
    private StorageTask mUploadTask;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mfirestore;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private AlertDialog.Builder dialogo;
    public FragmentUploadStore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_upload_store, container, false);
        dialogo = new AlertDialog.Builder(view.getContext());
        btn_chooseImage=view.findViewById(R.id.btn_choose_img);
        btn_upload=view.findViewById(R.id.ulpoadBtn);
        btn_tu_design=view.findViewById(R.id.btn_tu_desing);
        btn_cancelar=view.findViewById(R.id.btn_cancelar);
        descripcion =view.findViewById(R.id.ed_description);
        cukis = view.findViewById(R.id.ed_cukis);
        user=view.findViewById(R.id.ed_user);
        chooseImageView=view.findViewById(R.id.chooseImageView);
        uploadProgressBar=view.findViewById(R.id.progress_bar);

        descripcion.setVisibility(View.GONE);
        user.setVisibility(View.GONE);
        cukis.setVisibility(View.GONE);

        mStorageReference= FirebaseStorage.getInstance().getReference("img_desing");
        mDatabase= FirebaseDatabase.getInstance().getReference("img_desing");
        mAuth=FirebaseAuth.getInstance();
        mfirestore=FirebaseFirestore.getInstance();
        btn_upload.setVisibility(View.GONE);
        btn_cancelar.setVisibility(View.GONE);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentNews());
            }
        });


        btn_chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
                btn_chooseImage.setVisibility(View.GONE);
                btn_tu_design.setVisibility(View.GONE);
                btn_upload.setVisibility(View.VISIBLE);
                btn_cancelar.setVisibility(View.VISIBLE);
            }
        });


        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUploadTask !=null && mUploadTask.isInProgress()){
                    Toast.makeText(getContext(), "Espere por favor, aún se está subiendo el archivo anterior", Toast.LENGTH_SHORT).show();

                }else{
                    uploadFile();
                    btn_chooseImage.setVisibility(View.VISIBLE);


                }

                ((MainActivity) getActivity()).cambiarFragmento(new FragmentNews());

            }
        });

        btn_tu_design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentUploadDesing());
            }
        });


        return view;
    }

    private void openFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
        descripcion.setVisibility(View.VISIBLE);
        user.setVisibility(View.VISIBLE);
        cukis.setVisibility(View.VISIBLE);
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


    private void uploadFile() {

        if (mImageUri != null) {
            final StorageReference fileReference = mStorageReference.child(System.currentTimeMillis()
                    + ", " + getFileExtension(mImageUri));

            uploadProgressBar.setVisibility(View.VISIBLE);
            ;
            uploadProgressBar.setIndeterminate(true);


            mUploadTask = fileReference.putFile(mImageUri)


                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    uploadProgressBar.setVisibility(View.VISIBLE);
                                    uploadProgressBar.setIndeterminate(false);
                                    uploadProgressBar.setProgress(0);
                                }
                            }, 500);

                            StorageReference storageReference = FirebaseStorage.getInstance().getReference();

                            taskSnapshot.getStorage()
                                    .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    my_img=uri.toString();
                                    String id_user = mAuth.getCurrentUser().getUid();
                                    String my_user = user.getText().toString().trim();
                                    String my_descripcion = descripcion.getText().toString().trim();
                                    String my_cukis=cukis.getText().toString().trim();
                                    Post post = new Post("" + id_user,  my_user, "" + my_img, my_descripcion+"", my_cukis+"");
                                    String postId = mDatabase.push().getKey();
                                    mDatabase.child(postId).setValue(post);



                                }
                            });


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
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            uploadProgressBar.setProgress((int) progress);

                        }
                    });

        } else {
            Toast.makeText(getContext(), "No ha seleccionado ningún archivo", Toast.LENGTH_SHORT).show();
        }

    }

}

