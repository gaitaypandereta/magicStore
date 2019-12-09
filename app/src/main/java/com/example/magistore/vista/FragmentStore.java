package com.example.magistore.vista;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.magistore.R;
import com.example.magistore.modelo.Post;
import com.example.magistore.modelo.PostAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.ui.phone.SubmitConfirmationCodeFragment.TAG;

public class FragmentStore extends Fragment {
    private List<Post> compraList = new ArrayList<>();
    private PostAdapter mAdapter;
    private DatabaseReference mDatabase;

    public FragmentStore() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("img_upload");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("img_upload").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.exists()){
                    Post compra = dataSnapshot.getValue(Post.class);
                    compraList.add(compra);

                  //  mAdapter.notifyDataSetChanged();


                    // Toast.makeText(getContext(), url_img+"--"+titulo, Toast.LENGTH_SHORT).show();





                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /*
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Post compra = dataSnapshot.getValue(Post.class);
                compraList.add(compra);
                Toast.makeText(getContext(), compra.getDecripcion(), Toast.LENGTH_SHORT);
                // ...
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        }
*/
    return view;
    }
}

    /*
    mPostReference.addValueEventListener(postListener);


    mDatabase = FirebaseDatabase.getInstance().getReference();

    myRef.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                                            Post post = dataSnapshot.getValue(Post.class);
                                            compraList.add(post);

                                            mAdapter.notifyDataSetChanged();
                                        }
                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }

    });

    RecyclerView recyclerView = view.findViewById(R.id.rcv_store);

    mAdapter = new PostAdapter(compraList, getContext());
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(mAdapter);

   // prepareMovieData();

    return view;

}
*/

/*

    private void prepareMovieData() {
        Compra movie = new Compra("https://postimg.cc/34pYFndx", "Action & Adventure", "2015", "56");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Animation, Kids & Family", "2015", "678");
        movieList.add(movie);
        movie = new Compra("https://upload.wikimedia.org/wikipedia/commons/1/12/Pocho_Lavezzi_con_los_chicos_de_Ansur.jpg", "Action", "2015", "345");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Animation", "2015", "67");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Science Fiction & Fantasy", "2015", "5");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Action", "2015", "0");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Animation", "2009", "2");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Science Fiction", "2009", "56");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Animation", "2014", "6");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Action & Adventure", "2008", "6");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Science Fiction", "1986", "1");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Animation", "2000", "0");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Science Fiction", "1985", "5");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Action & Adventure", "1981", "4");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Action & Adventure", "1965", "3456");
        movieList.add(movie);
        movie = new Compra("https://postimg.cc/34pYFndx", "Science Fiction & Fantasy", "2014", "1");
        movieList.add(movie);
        mAdapter.notifyDataSetChanged();

    }

*/














