package com.example.magistore.modelos;
import android.app.AlertDialog;
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


public class PostAdapterAdmin extends RecyclerView.Adapter<PostAdapterAdmin.MyViewHolder> implements View.OnClickListener {
    private List<Post> postList;
    private Post post;
    public  Context context;

    public PostAdapterAdmin(List<Post> postList, int resource, Context context){
        this.postList =postList;
        this.context=context;
    }

    @Override
    public void onClick(View view) {

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView comento, user, cukis;
        Button ver_user;
        ImageView img_foto;

        MyViewHolder(View view) {
            super(view);
            img_foto=view.findViewById(R.id.img_principal);
            user=view.findViewById(R.id.tv_admin_user);
            comento = view.findViewById(R.id.tv_admin_description);
            cukis=view.findViewById(R.id.edit_admin_cukis);
            ver_user=view.findViewById(R.id.btn_ver_users);

            ver_user.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    OttoClass.getBus().post(new MessageFragmentActivity(postList.get(getAdapterPosition()).getId()));

                    return false;
                }
            });

            img_foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OttoClass.getBus().post(new MessageFragmentFragment(postList.get(getAdapterPosition()).getId()));
                }
            });

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_store_admin, parent, false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        post = postList.get(position);
        holder.user.setText(post.getUser());
        holder.comento.setText(post.getDecripcion());
        holder.cukis.setText(post.getMegusta());
        holder.ver_user.setText(post.getId());

        Glide.with(context)
                .load(post.getUrl_img())
                .into(holder.img_foto);


    }
    @Override
    public int getItemCount() {
        return postList.size();
    }

}