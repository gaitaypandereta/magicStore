package com.example.magistore.vista;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.magistore.R;
import com.example.magistore.modelo.Compra;
import com.example.magistore.modelo.CompraAdapter;
import java.util.ArrayList;
import java.util.List;

public class FragmentStore extends Fragment {
    private List<Compra> movieList = new ArrayList<>();
    private CompraAdapter mAdapter;

    public FragmentStore() {
    }


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);


    RecyclerView recyclerView = view.findViewById(R.id.rcv_store);
    mAdapter = new CompraAdapter(movieList, getContext());
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(mAdapter);

    prepareMovieData();

    return view;

}
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


}











