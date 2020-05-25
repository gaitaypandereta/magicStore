package com.example.magistore.fragmentos;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.example.magistore.modelos.MessageFragmentFragment;
import com.example.magistore.modelos.OttoClass;
import com.example.magistore.modelos.Post;
import com.example.magistore.modelos.PostAdapterAdmin;
import com.example.magistore.modelos.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class FragmentStoreAdmin extends Fragment {
    private List<Post> postList = new ArrayList<>();
    private PostAdapterAdmin postAdapterAdmin;
    private Post post;
    private Usuario user;
    private DatabaseReference mDatabase;
    private String usuario, megustas, comento, img, id_user;
    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    private RecyclerView recyclerview;
    //private FirebaseDatabase database;
   // private EditText campo;
    //private TextView ver_detalle;
    private Button fin_campanha;
    private List<String> campos;
    private Spinner spinner_campos;
    private AlertDialog.Builder dialogo;
    private ImageView imageView_admin_not;

    public FragmentStoreAdmin() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_admin, container, false);
        //  ButterKnife.bind(this, view);

        dialogo = new AlertDialog.Builder(view.getContext());
        mAuth = FirebaseAuth.getInstance();
        recyclerview = view.findViewById(R.id.rcv_store_admin);
        fin_campanha = view.findViewById(R.id.btn_fin_campanha);
        spinner_campos = view.findViewById(R.id.spinner_campos);
        imageView_admin_not = view.findViewById(R.id.imv_admin_not);

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance().getReference();


        fin_campanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialogo.setTitle("¿Está seguro de finalizar esta CAMPAÑA?");
                dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mDatabase.child("img_desing").removeValue();
                        ((MainActivity) getActivity()).cambiarFragmento(new FragmentInic());


                    }
                });
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialogo.show();


            }
        });


        campos = new ArrayList<String>();
        campos.add("ORDENAR POR CUKIS");
        campos.add("ORDENAR POR ID");

        ArrayAdapter<String> adapterCampos = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, campos);
        spinner_campos.setAdapter(adapterCampos);
        String campo = spinner_campos.getSelectedItem().toString();
        String id = mAuth.getCurrentUser().getUid();

        spinner_campos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (campos.get(position) == "ORDENAR POR MEGUSTA") {

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
                                        id_user = ds.child("id").getValue().toString();
                                        postList.add(new Post(id_user, usuario, img, comento, megustas));
                                    }

                                    postAdapterAdmin = new PostAdapterAdmin(postList, R.layout.list_store_admin, getContext());
                                    recyclerview.setAdapter(postAdapterAdmin);
                                    registerForContextMenu(recyclerview);

                                }

                                if (postList.size() == 0)
                                    imageView_admin_not.setVisibility(View.VISIBLE);

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
                                        id_user = ds.child("id").getValue().toString();
                                        postList.add(new Post(id_user, usuario, img, comento, megustas));

                                    }


                                    postAdapterAdmin = new PostAdapterAdmin(postList, R.layout.list_store, getContext());
                                    recyclerview.setAdapter(postAdapterAdmin);
                                    registerForContextMenu(recyclerview);

                                }

                                if (postList.size() == 0)
                                    imageView_admin_not.setVisibility(View.VISIBLE);
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

    public void deleteCampahna() {

        mDatabase.child("img_desing").removeValue();

    }

    class MDialogo {
        public AlertDialog createSingleListDialog(String telefono, String direccion, String edad, String email, String facebo, String instagra, String nombre, String sexo, String estado, String twiter) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            final CharSequence[] items = new CharSequence[10];

            items[0] = "Nombre: " + nombre;
            items[1] = "Email: " + email;
            items[2] = "Teléfono " + telefono;
            items[3] = "Compras para: " + edad + " años";
            items[4] = "Compras para: " + sexo;
            items[5] = "Facebook: " + facebo;
            items[6] = "Instagram: " + instagra;
            items[7] = "Twitter: " + twiter;
            items[8] = "Estado: " + estado;
            items[9] = "Dirección: " + direccion;

            builder.setTitle(Html.fromHtml("<font color='#F44336'>Datos de Usuario</font>"))
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                      /* Toast.makeText(
                                getActivity(),
                                "Seleccionaste:" + items[which],
                                Toast.LENGTH_SHORT)
                                .show();*/
                        }
                    });

            return builder.create();
        }

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
    public void receiveMessageAtoF(MessageFragmentFragment message) {

        String mg = message.getMensaje();
        getUsuario(mg);

    }


    private void getUsuario(String id) {

        mDatabase.child("users").child(id).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String telefono = dataSnapshot.child("telefono").getValue().toString();
                    String sexo = dataSnapshot.child("sexo").getValue().toString();
                    String eda = dataSnapshot.child("edad").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    String estado = dataSnapshot.child("comenta_admin").getValue().toString();
                    String twitte = dataSnapshot.child("twiter").getValue().toString();
                    String instagra = dataSnapshot.child("instagra").getValue().toString();
                    String faceboo = dataSnapshot.child("facebok").getValue().toString();
                    String direccion = dataSnapshot.child("direccion_envio").getValue().toString();
                    int edad = Integer.parseInt(eda);
                    user = new Usuario(telefono, direccion, edad, email, faceboo, instagra, nombre, sexo, estado, twitte);

                    MDialogo dialogo = new MDialogo();
                    dialogo.createSingleListDialog(telefono, direccion, eda, email, faceboo, instagra, nombre, sexo, estado, twitte).show();


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }


        });


    }


}