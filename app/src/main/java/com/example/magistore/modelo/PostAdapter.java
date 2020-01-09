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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.magistore.R;

import java.util.ArrayList;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private ArrayList<Post> postList;
    private int resource;
    public  Context context;

    public PostAdapter(ArrayList<Post> postList, int resource,Context context){
        this.postList =postList;
        this.context=context;
        this.resource=resource;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView comento, user;
        Button megusta, comenta;
        ImageView img_foto;
        MyViewHolder(View view) {
            super(view);
            img_foto=view.findViewById(R.id.img_principal);
            user=view.findViewById(R.id.tv_user);
            comento = view.findViewById(R.id.tv_titulo);
            megusta=view.findViewById(R.id.btn_megusta);
        }
    }

    public PostAdapter(ArrayList<Post> postList) {
        this.postList = postList;

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
        Post post = postList.get(position);
        holder.user.setText(post.getUser());
        holder.comento.setText(post.getDecripcion());
        holder.megusta.setText("34");

        Glide.with(context)
                .load(post.getUrl_img())
                .into(holder.img_foto);
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }

}





/*
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private ArrayList<Post> postList;
    private int resource;
    public static Context context;

    public PostAdapter(ArrayList<Post> postList, int resource,Context context){
        this.postList =postList;
        this.context=context;
        this.resource=resource;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView comento, user;
        Button megusta, comenta;
        ImageView img_foto;
        MyViewHolder(View view) {
            super(view);
            img_foto=view.findViewById(R.id.img_principal);
            user=view.findViewById(R.id.tv_user);
            comento = view.findViewById(R.id.tv_titulo);
            megusta=view.findViewById(R.id.btn_megusta);
        }
    }

    public PostAdapter(ArrayList<Post> postList) {
        this.postList = postList;

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
        Post post = postList.get(position);
        holder.user.setText(post.getUser());
        holder.comento.setText(post.getDecripcion());
        holder.megusta.setText("24");

        Glide.with (context)
                .load (post.getUrl_img())
                .apply (RequestOptions.diskCacheStrategyOf (DiskCacheStrategy.NONE))
                .into (holder.img_foto);

    }
    @Override
    public int getItemCount() {
        return postList.size();
    }

}



*/