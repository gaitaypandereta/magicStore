package com.example.magistore;
import android.os.Bundle;

import com.example.magistore.modelo.Post;
import com.example.magistore.vista.FragmentConditions;
import com.example.magistore.vista.FragmentConditionsInic;
import com.example.magistore.vista.FragmentLogin;
import com.example.magistore.vista.FragmentNews;
import com.example.magistore.vista.FragmentRegitrationInit;
import com.example.magistore.vista.FragmentInic;
import com.example.magistore.vista.FragmentRegistration;
import com.example.magistore.vista.FragmentStore;
import com.example.magistore.vista.FragmentStoreWeb;
import com.example.magistore.vista.FragmentUploadDesing;
import com.example.magistore.vista.FragmentUploadStore;
import com.example.magistore.vista.Fragment_campahne;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
private FragmentInic fragmentInicio = new FragmentInic();
private FirebaseAnalytics mFirebaseAnalytics;
private FirebaseAuth mAuth;
private boolean estado=true;
private List<Post> postList =new ArrayList<Post>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.add(R.id.contenedor, fragmentInicio);
        FT.commit();



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean mostrarItem(Boolean dato){
       boolean estado=false;
       if(dato)
        estado=true;

       return estado;
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

        }
        if (id == R.id.tablero) {
            cambiarFragmento(new FragmentLogin());
        }

        return super.onOptionsItemSelected(item);
    }


}
