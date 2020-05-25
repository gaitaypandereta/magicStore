package com.example.magistore.modelos;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.magistore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> implements View.OnClickListener {
    private List<Post> postList;
    private Post post;
    private AlertDialog.Builder dialogo;
    public  Context context;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public PostAdapter(List<Post> postList, int list_store, Context context){
        this.postList =postList;
        this.context=context;

    }

    @Override
    public void onClick(View view) {

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView comento, user, id;
        EditText cukis;
        Button  edita;
        ImageView img_foto;
        MyViewHolder(View view) {
            super(view);
            img_foto=view.findViewById(R.id.img_principal);
            user=view.findViewById(R.id.tv_user);
            comento = view.findViewById(R.id.tv_description);
            cukis=view.findViewById(R.id.edit_cukis);

            dialogo = new AlertDialog.Builder(view.getContext());

        }



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_store, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        post = postList.get(position);
        holder.user.setText(post.getUser());
        holder.comento.setText(post.getDecripcion());
        holder.cukis.setText(post.getMegusta());

        Glide.with(context)
                .load(post.getUrl_img())
                .into(holder.img_foto);




        holder.img_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogo.setTitle("¿Está seguro de eliminar este Post?");
                dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        postList.remove(position);
                        OttoClass.getBus().post(new MessageAFragmentFragment(post.getDecripcion()));
                    }
                });
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialogo.show();






            }
        });
    }


    @Override
    public int getItemCount() {
        return postList.size();
    }


}






