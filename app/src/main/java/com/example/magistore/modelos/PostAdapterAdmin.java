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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.magistore.R;

import java.util.ArrayList;
import java.util.List;


public class PostAdapterAdmin extends RecyclerView.Adapter<PostAdapterAdmin.MyViewHolder> implements View.OnClickListener {
    // DatabaseReference postReference;
    // private FirebaseFirestore  mFirestore;
    // private PotsAdminDAO potsAdminDAO;
    private List<Post> postList;
    private Post post;
    private int resource;
    public  Context context;
    private AlertDialog.Builder dialogo;

    public PostAdapterAdmin(List<Post> postList, int resource, Context context){
        this.postList =postList;
        this.context=context;
        this.resource=resource;
        // mFirestore=FirebaseFirestore.getInstance();

    }

    @Override
    public void onClick(View view) {

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView comento, user, id, cukis, coment_admin;
        Button  edita;
        ImageView img_foto;
        MyViewHolder(View view) {
            super(view);
            img_foto=view.findViewById(R.id.img_principal);
            user=view.findViewById(R.id.tv_user);
            comento = view.findViewById(R.id.tv_description);
            cukis=view.findViewById(R.id.tv_value_cukis);
            coment_admin=view.findViewById(R.id.tv_value_coment);


        }



    }

    public PostAdapterAdmin(FragmentActivity activity, ArrayList<Post> postList) {
        this.postList = postList;

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
        holder.coment_admin.setText(post.getComenta_admin());
        holder.cukis.setText(post.getCukis());

        Glide.with(context)
                .load(post.getUrl_img())
                .into(holder.img_foto);
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }


}