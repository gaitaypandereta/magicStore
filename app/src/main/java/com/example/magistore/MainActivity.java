package com.example.magistore;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
private FragmentInic fragmentInicio = new FragmentInic();
private FirebaseAuth mAuth;
private ImageView imv_Musica;
private boolean estado=true;
private boolean encendida=false;
private List<Post> postList =new ArrayList<Post>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mAuth=FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

       // myRef.setValue("Hello, World!");

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

        imv_Musica=findViewById(R.id.imv_sonido);
        imv_Musica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(encendida)
                   apagaMusica();
                else
                    enciendeMusica();
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
        }
        if (id == R.id.tablero) {
            cambiarFragmento(new FragmentLogin());
        }
        if (id == R.id.salir) {
           // finish();
            cambiarFragmento(new FragmentStoreAdmin());

        }

        return super.onOptionsItemSelected(item);
    }


    public void enviar_email() {
        boolean enviado=false;
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
            enviado=true;
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "No hay clientes de email instalados.", Toast.LENGTH_SHORT).show();
            enviado=false;
        }

    }

    public void enciendeMusica(){

        // botonMusica.setImageResource(R.drawable.l_azul_o);
        Intent miReproductor= new Intent(this, Serve.class);
        this.startService(miReproductor);

        encendida=!encendida;
    }

    public void apagaMusica(){
        //botonMusica.setImageResource(R.drawable.l_gris);
        Intent miReproductor= new Intent(this, Serve.class);
        this.stopService(miReproductor);
        encendida=!encendida;

    }

    public void cerrarSesion() {

        FirebaseAuth.getInstance().signOut();

    }

}
