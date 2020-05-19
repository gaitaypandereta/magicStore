package com.example.magistore.fragmentos;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magistore.MainActivity;
import com.example.magistore.R;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUploadDesing extends Fragment {
    ImageButton negro;
    ImageButton blanco;
    ImageButton rojo;
    ImageButton verde;
    ImageButton azul;
    ImageButton amarillo;
    ImageButton gris;
    ImageButton naranja;
    ImageButton marron;
    ImageButton azul_c;
    ImageButton volver;
    private static Lienzo lienzo;
    float ppequenyo;
    float pmediano;
    float pgrande;
    float pdefecto;
    ImageButton grueso_trazo;
    ImageButton anadir;
    ImageButton borrar;
    ImageButton guardar;
    String color = null;
    public FragmentUploadDesing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_upload_desing, container, false);

        negro =view.findViewById(R.id.colornegro);
        blanco =view.findViewById(R.id.colorblanco);
        rojo = view.findViewById(R.id.colorrojo);
        verde =view.findViewById(R.id.colorverde);
        azul =view.findViewById(R.id.colorazul_o);
        naranja =view.findViewById(R.id.colornaranja);
        amarillo =view.findViewById(R.id.coloramarillo);
        marron= view.findViewById(R.id.colormarron);
        gris =view.findViewById(R.id.colorgris);
        azul_c =view.findViewById(R.id.colorazul_c);
        volver=view.findViewById(R.id.volver);
        grueso_trazo = view.findViewById(R.id.trazo);
        anadir = view.findViewById(R.id.anyadir);
        borrar = view.findViewById(R.id.borrar);
        guardar = view.findViewById(R.id.guardar);

        negro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });
        blanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });
        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });
        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });
        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });


        gris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });
        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });
        naranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });
        azul_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });
        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });

        marron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = view.getTag().toString();
                lienzo.setColor(color);
            }
        });


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentUploadStore());
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               borrarDibujo();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               guardarDibujo(view);
               ((MainActivity) getActivity()).cambiarFragmento(new FragmentUploadStore());
            }
        });

        grueso_trazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionaGruesoLinea();
            }
        });

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              nuevoDibujo();
            }
        });

        lienzo = view.findViewById(R.id.lienzo);

        ppequenyo= 10;
        pmediano= 20;
        pgrande= 30;

        pdefecto= pmediano;

        return view;
    }

    public void seleccionaGruesoLinea(){
        final Dialog tamanyopunto = new Dialog(getContext());
        tamanyopunto.setTitle("Tamaño del punto:");
        tamanyopunto.setContentView(R.layout.tamanyo_punto);
        //listen for clicks on tamaños de los botones
        TextView smallBtn = (TextView)tamanyopunto.findViewById(R.id.tpequenyo);
        smallBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Lienzo.setBorrado(false);
                Lienzo.setTamanyoPunto(ppequenyo);

                tamanyopunto.dismiss();
            }
        });
        TextView mediumBtn = (TextView)tamanyopunto.findViewById(R.id.tmediano);
        mediumBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Lienzo.setBorrado(false);
                Lienzo.setTamanyoPunto(pmediano);

                tamanyopunto.dismiss();
            }
        });
        TextView largeBtn = (TextView)tamanyopunto.findViewById(R.id.tgrande);
        largeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lienzo.setBorrado(false);
                Lienzo.setTamanyoPunto(pgrande);

                tamanyopunto.dismiss();
            }
        });
        //show and wait for user interaction
        tamanyopunto.show();

    }
    public void guardarDibujo(View view){

        AlertDialog.Builder salvarDibujo = new AlertDialog.Builder(getContext());
        salvarDibujo.setTitle("Guarda tu diseño");
        salvarDibujo.setMessage("¿Guarda tu diseño en la galeria?");
        salvarDibujo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

                lienzo.setDrawingCacheEnabled(true);
                 String myimg=UUID.randomUUID().toString();
                //guardar imagen
                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContext().getContentResolver(), lienzo.getDrawingCache(),
                        myimg+".png", "drawing");

                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getContext(),
                            "¡Diseño guardado en galeria!", Toast.LENGTH_SHORT);
                    savedToast.show();
                    ((MainActivity) getActivity()).cambiarFragmento(new FragmentUploadStore());
                }
                else{
                    Toast unsavedToast = Toast.makeText(getContext(),
                            "¡Error! La imagen no ha podido ser guardada.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }
                lienzo.destroyDrawingCache();


            }

        });
        salvarDibujo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        salvarDibujo.show();
    }
    public void borrarDibujo(){

        final Dialog borrarpunto = new Dialog(getContext());
        borrarpunto.setTitle("Tamaño de borrado:");
        borrarpunto.setContentView(R.layout.tamanyo_punto);
        //listen for clicks on tamaños de los botones
        TextView smallBtnBorrar = (TextView)borrarpunto.findViewById(R.id.tpequenyo);
        smallBtnBorrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Lienzo.setBorrado(true);
                Lienzo.setTamanyoPunto(ppequenyo);

                borrarpunto.dismiss();
            }
        });
        TextView mediumBtnBorrar = (TextView)borrarpunto.findViewById(R.id.tmediano);
        mediumBtnBorrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Lienzo.setBorrado(true);
                Lienzo.setTamanyoPunto(pmediano);

                borrarpunto.dismiss();
            }
        });
        TextView largeBtnBorrar = (TextView)borrarpunto.findViewById(R.id.tgrande);
        largeBtnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lienzo.setBorrado(true);
                Lienzo.setTamanyoPunto(pgrande);

                borrarpunto.dismiss();
            }
        });
        //show and wait for user interaction
        borrarpunto.show();

    }

    public void nuevoDibujo(){
        AlertDialog.Builder newDialog = new AlertDialog.Builder(getContext());
        newDialog.setTitle("Nuevo Diseño");
        newDialog.setMessage("¡OJO! Si comienzas con un diseño nuevo perderás el actual");
        newDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

                lienzo.NuevoDibujo();
                dialog.dismiss();
            }
        });
        newDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        newDialog.show();


    }


}
