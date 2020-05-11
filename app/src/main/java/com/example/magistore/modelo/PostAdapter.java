package com.example.magistore.modelo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.magistore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    DatabaseReference postReference;
    private FirebaseFirestore  mFirestore;
    private ArrayList<Post> postList;
    private int resource;
    public  Context context;
    private AlertDialog.Builder dialogo;

    public PostAdapter(ArrayList<Post> postList, int resource,Context context){
        this.postList =postList;
        this.context=context;
        this.resource=resource;
        mFirestore=FirebaseFirestore.getInstance();

    }


    class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnLongClickListener{
        TextView comento, user;
        Button megusta, comenta;
        ImageView img_foto;
        MyViewHolder(View view) {
            super(view);
            img_foto=view.findViewById(R.id.img_principal);
            user=view.findViewById(R.id.tv_user);
            comento = view.findViewById(R.id.tv_titulo);

            view.setOnLongClickListener(this);
        }


       @Override
        public boolean onLongClick(View view) {
            int position= getAdapterPosition();
           dialogo = new AlertDialog.Builder(view.getContext());
           dialogo.setTitle("¿Seguro que quiere eliminar este post?");
           dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {

               }
           });
           dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   dialogInterface.dismiss();
               }
           });
           dialogo.show();

            return true;
        }
    }

    public PostAdapter(FragmentActivity activity, ArrayList<Post> postList) {
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


        Glide.with(context)
                .load(post.getUrl_img())
                .into(holder.img_foto);
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }


}





