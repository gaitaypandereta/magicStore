package com.example.magistore.modelo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.magistore.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class TejidoAdapter extends RecyclerView.Adapter<TejidoAdapter.ViewHolder> {
    private List<Tejido> tejidosList;
    private  Context context;
    int resource;


    public TejidoAdapter(List<Tejido> tejidosList, int resource, Context context ) {
        this.tejidosList = tejidosList;
        this.resource = resource;
    }


    @NonNull
    @Override
    public TejidoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tejido tejido = tejidosList.get(position);
        holder.nombre.setText(tejido.getNombre());
        holder.descripcion.setText(tejido.getDescripcion());
        holder.megusta.setText("34");

        Glide.with(context)
                .load(tejido.getUrl_img())
                .into(holder.img_foto);
    }


    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView descripcion, nombre;
        Button megusta, comenta;
        ImageView img_foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_foto = itemView.findViewById(R.id.img_principal);
            nombre = itemView.findViewById(R.id.tv_user);
            megusta = itemView.findViewById(R.id.btn_megusta);
            descripcion= itemView.findViewById(R.id.btn_comenta);
        }
    }
}
