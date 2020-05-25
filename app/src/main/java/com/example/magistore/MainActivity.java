package com.example.magistore;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.magistore.modelos.MessageFragmentActivity;
import com.example.magistore.modelos.MessageFragmentFragment;
import com.example.magistore.modelos.OttoClass;
import com.example.magistore.modelos.Post;
import com.example.magistore.modelos.Serve;
import com.example.magistore.fragmentos.FragmentConditionsInic;
import com.example.magistore.fragmentos.FragmentLogin;
import com.example.magistore.fragmentos.FragmentInic;
import com.example.magistore.fragmentos.FragmentStoreAdmin;
import com.example.magistore.fragmentos.FragmentStoreWeb;
import com.example.magistore.fragmentos.Fragment_campahne;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.otto.Subscribe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private FragmentInic fragmentInicio = new FragmentInic();
    private FirebaseAuth mAuth;
    private ImageView imv_Musica;
    private boolean estado = true;
    private boolean encendida = false;
    private DatabaseReference dR;
    private String valor;
    private String ides;
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
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Indica el  motivo: Denuncia un Post inadecuado, Comentario, Consuta, Otros");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Tu mensaje");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email....."));
            finish();
            enviado = true;
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "No hay clientes de email instalados.", Toast.LENGTH_SHORT).show();
            enviado = false;
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
                break;

            case R.id.mcSucces:
                if (getIdes() != null)
                    penalizeUser(getIdes());


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
}