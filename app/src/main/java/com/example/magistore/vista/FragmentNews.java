package com.example.magistore.vista;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.magistore.MainActivity;
import com.example.magistore.R;
import com.example.magistore.modelo.Cuki;
import com.example.magistore.modelo.Retrofit.CukisApi;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Integer.parseInt;

public class FragmentNews extends Fragment {
    private FirebaseAuth mAuth;
    private CukisApi api;
    private TextView  tv_cukis_face_date, tv_cukis_twit_date, tv_cukis_inst_date, tv_cukis_facebook, tv_cukis_twitter, tv_cukis_instagram, tv_cukis_compras, tv_cukis_aportaciones, tv_cukis_total;
    private Button completa_registro, mis_ideas, subir_ideas;

    public FragmentNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_news, container, false);
        mAuth = FirebaseAuth.getInstance();
        completa_registro=vista.findViewById(R.id.btn_completa_registro);
        mis_ideas=vista.findViewById(R.id.btn_mis_cosas);
        subir_ideas=vista.findViewById(R.id.btn_sube_tus_cosas);
        tv_cukis_aportaciones=vista.findViewById(R.id.tv_cukis_aportaciones);
        tv_cukis_compras=vista.findViewById(R.id.tv_cukis_compras);
        tv_cukis_facebook=vista.findViewById(R.id.tv_cukis_facebook);
        tv_cukis_twitter=vista.findViewById(R.id.tv_cukis_twitter);
        tv_cukis_instagram=vista.findViewById(R.id.tv_cukis_instagram);
        tv_cukis_total=vista.findViewById(R.id.tv_cukis_total);
        tv_cukis_face_date=vista.findViewById(R.id.tv_fecha_facebook);
        tv_cukis_inst_date=vista.findViewById(R.id.tv_fecha_instagram);
        tv_cukis_twit_date=vista.findViewById(R.id.tv_fecha_twitter);
        completa_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentConditions());
            }
        });

        mis_ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentStore());
            }
        });

        subir_ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).cambiarFragmento(new FragmentUploadStore());
            }
        });


        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://192.168.43.113:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CukisApi.class);

                String id = mAuth.getCurrentUser().getUid();


                    Call<Cuki> call = api.getCuki(id);

                    call.enqueue(new Callback<Cuki>() {
                        @Override
                        public void onResponse(Call<Cuki> call, Response<Cuki> response) {
                            Cuki cuki = response.body();
                            if (cuki != null) {
                                int cuki_acumuladas = parseInt(cuki.getAcumuladas() + "");
                                int cuki_apor = parseInt(cuki.getAportaciones() + "");
                                int cuki_face = parseInt(cuki.getFacebook() + "");
                                int cuki_twit = parseInt(cuki.getTwitter() + "");
                                int cuki_inst = parseInt(cuki.getInstagram() + "");
                                int total_cukis = cuki_apor + cuki_acumuladas + cuki_face + cuki_inst + cuki_twit;
                                tv_cukis_face_date.setText(cuki.getCampahna());
                                tv_cukis_inst_date.setText(cuki.getCampahna());
                                tv_cukis_twit_date.setText(cuki.getCampahna());
                                tv_cukis_aportaciones.setText("Por tus  ideas tienes " + cuki_apor + " Cukis");
                                tv_cukis_compras.setText("Otras campañas tienes " + cuki_acumuladas + " Cukis");
                                tv_cukis_facebook.setText(cuki_face + "");
                                tv_cukis_twitter.setText(cuki_twit + "");
                                tv_cukis_instagram.setText(cuki_inst + "");
                                tv_cukis_total.setText("Total " + total_cukis + " Cukis");

                            } else {

                            }

                        }

                        @Override
                        public void onFailure(Call<Cuki> call, Throwable t) {

                        }
                    });




        return vista;
    }

}
