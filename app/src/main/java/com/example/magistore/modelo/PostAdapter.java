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

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    public List<Post> compraList;
    public static Context context;


    public PostAdapter(List<Post> moviesList, Context context){
        this.compraList =moviesList;
        this.context=context;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, user;
        Button megusta, comenta;
        ImageView img_foto;
        MyViewHolder(View view) {
            super(view);
            img_foto=view.findViewById(R.id.img_principal);
            user=view.findViewById(R.id.tv_user);
            title = view.findViewById(R.id.tv_titulo);
            megusta=view.findViewById(R.id.btn_megusta);
            comenta=view.findViewById(R.id.btn_comenta);
        }
    }





    public PostAdapter(List<Post> moviesList) {
        this.compraList = moviesList;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_store, parent, false);
        return new MyViewHolder(itemView);


    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post post = compraList.get(position);
        holder.user.setText(post.getUser());
        holder.title.setText(post.getDecripcion());
        holder.comenta.setText("56");
        holder.megusta.setText("34");

        Glide.with(context)
                .load(post.getUrl_img())
                .into(holder.img_foto);
    }
    @Override
    public int getItemCount() {
        return compraList.size();
    }

}




