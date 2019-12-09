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

import java.util.List;

public class AdapterPost extends FirestoreRecyclerAdapter<Post, AdapterPost.ViewHolder> {
    public List<Post> compraList;
    public static Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterPost(@NonNull FirestoreRecyclerOptions<Post> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull AdapterPost.ViewHolder holder, int position, @NonNull Post model) {
        Post post = compraList.get(position);
        holder.user.setText(post.getUser());
        holder.title.setText(post.getDecripcion());
        holder.comenta.setText("56");
        holder.megusta.setText("34");

        Glide.with(context)
                .load(post.getUrl_img())
                .into(holder.img_foto);

    }

    @NonNull
    @Override
    public AdapterPost.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_store, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, user;
        Button megusta, comenta;
        ImageView img_foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_foto = itemView.findViewById(R.id.img_principal);
            user = itemView.findViewById(R.id.tv_user);
            title = itemView.findViewById(R.id.tv_titulo);
            megusta = itemView.findViewById(R.id.btn_megusta);
            comenta = itemView.findViewById(R.id.btn_comenta);
        }
    }
}
