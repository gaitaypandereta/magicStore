package com.example.magistore;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.example.magistore.modelos.MessageFragmentActivity;
import com.example.magistore.modelos.OttoClass;
import com.example.magistore.modelos.Post;
import com.example.magistore.modelos.Retrofit.CukisApi;
import com.example.magistore.modelos.Serve;
import com.example.magistore.fragmentos.FragmentConditionsInic;
import com.example.magistore.fragmentos.FragmentLogin;
import com.example.magistore.fragmentos.FragmentInic;
import com.example.magistore.fragmentos.FragmentStoreWeb;
import com.example.magistore.fragmentos.Fragment_campahne;
import com.example.magistore.modelos.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.okhttp.ResponseBody;
import com.squareup.otto.Subscribe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentInic fragmentInicio = new FragmentInic();
    private FirebaseAuth mAuth;
    private ImageView imv_Musica;
    private boolean estado = true;
    private boolean encendida = false;
    private DatabaseReference dR;
    private String valor;
    private String ides;
    private CukisApi api;
    View view;
    private List<Post> postList = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.add(R.id.contenedor, fragmentInicio);
        FT.commit();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar_email();
                cambiarFragmento(new FragmentInic());
            }
        });

        imv_Musica = findViewById(R.id.imv_sonido);
        imv_Musica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (encendida)
                    ofSound();
                else
                    onSound();
            }
        });


        //En este caso implementamos tambien Retrofit llamando a su clase.
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://192.168.43.113:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CukisApi.class);

    }


    public void cambiarFragmento(Fragment fragmento) {
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.replace(R.id.contenedor, fragmento);
        FT.addToBackStack(null);
        FT.commit();
    }

    public List<Post> getListPost() {
        return postList;
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item3 = menu.findItem(R.id.tablero);
        item3.setVisible(estado);

        return true;

    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.inic) {
            cambiarFragmento(new FragmentInic());
        }
        if (id == R.id.login) {
            cambiarFragmento(new FragmentLogin());
        }
        if (id == R.id.registro) {
            cambiarFragmento(new FragmentConditionsInic());
        }
        if (id == R.id.campahne) {
            cambiarFragmento(new Fragment_campahne());
        }

        if (id == R.id.web) {
            cambiarFragmento(new FragmentStoreWeb());
        }
        if (id == R.id.logout) {
            mAuth.signOut();
            cambiarFragmento(new FragmentInic());
        }
        if (id == R.id.tablero) {
            cambiarFragmento(new FragmentLogin());
        }
        if (id == R.id.salir) {
            finish();

        }

        return super.onOptionsItemSelected(item);
    }


    public void enviar_email() {
        boolean enviado = false;
        String[] TO = {"micolejaen@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Tu mensaje");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email....."));
            enviado = true;

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "No hay clientes de email instalados.", Toast.LENGTH_SHORT).show();
            enviado = false;
        }

        if(enviado){

            }



    }

    public void onSound() {
        Intent miReproductor = new Intent(this, Serve.class);
        this.startService(miReproductor);

        encendida = !encendida;
    }

    public void ofSound() {
        Intent miReproductor = new Intent(this, Serve.class);
        this.stopService(miReproductor);
        encendida = !encendida;

    }

    public void cerrarSesion() {

        FirebaseAuth.getInstance().signOut();

    }


    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {

            case R.id.mcSelect:
                if (getIdes() != null)
                    prizeUser(getIdes());
                    String premio="PREMIADO";
                    updateGetUsuario(getIdes(), premio);
                break;

            case R.id.mcSucces:
                if (getIdes() != null)
                    penalizeUser(getIdes());
                    String penalizado="PENALIZADO";
                    updateGetUsuario(getIdes(), penalizado);


                break;
            default:
                return super.onContextItemSelected(item);
        }

        return true;
    }


    public void prizeUser(String ide) {
        dR = FirebaseDatabase.getInstance().getReference("users").child(ide);
        dR.child("comenta_admin").setValue("PREMIADO");


    }

    public void penalizeUser(String ide) {
        dR = FirebaseDatabase.getInstance().getReference("users").child(ide);
        dR.child("comenta_admin").setValue("PENALIZADO");

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
    public void receiveMessageAtoF(MessageFragmentActivity message) {

        String idess = message.getMensaje();
        setIdes(idess);

    }

    public String getIdes() {
        return ides;
    }

    public void setIdes(String ides) {
        this.ides = ides;
    }


    //Actualizar usuario final de campaña en mysql
    public void updateUsuarioMysql(Usuario usuario){

        api.updateUser(usuario.getId(), usuario).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


    private void updateGetUsuario(String id, String estado) {

    final  String ide=id;
    final  String coment_admin = estado;
        dR = FirebaseDatabase.getInstance().getReference();
        dR.child("users").child(id).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String telefono = dataSnapshot.child("telefono").getValue().toString();
                    String facebook = dataSnapshot.child("facebok").getValue().toString();
                    String twitter = dataSnapshot.child("twiter").getValue().toString();
                    String instagram = dataSnapshot.child("instagra").getValue().toString();
                    String direccion = dataSnapshot.child("direccion_envio").getValue().toString();
                    String sexo = dataSnapshot.child("sexo").getValue().toString();
                    String edad = dataSnapshot.child("edad").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    String nombre = dataSnapshot.child("nombre").getValue().toString();

                    Usuario usuario = new Usuario(ide, telefono, direccion, edad, email, facebook,instagram, nombre, sexo, coment_admin , twitter);
                    updateUsuarioMysql(usuario);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


            private void hideTeclado(View v) {
        InputMethodManager teclado = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        teclado.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    @Override
    public void onClick(View v) {
        hideTeclado(v);
    }
}