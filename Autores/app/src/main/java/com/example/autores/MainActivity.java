package com.example.autores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InterfaceCallback.Protocol , InterfaceCallback.onClickListener {

    private EditText mInputLibro;
    private TextView mTextoTitulo;
    private RecyclerView recyclerView;
    public List<BookGoogle> favorites = new ArrayList<>();

    Boolean isConnected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputLibro = findViewById(R.id.ingresoLibro);
        mTextoTitulo = findViewById(R.id.titulo);
        recyclerView = findViewById(R.id.recyclerview_books);
        LinearLayoutManager l = new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(l);
        recyclerView.setHasFixedSize(true);

    }

    public void buscarLibro(View view){
        String cadenaBusqueda = mInputLibro.getText().toString();
        ConseguirLibro finderGoogleBook = new ConseguirLibro(this);
        finderGoogleBook.execute(cadenaBusqueda);
    }

    @Override
    public void passListBookGoogle(List<BookGoogle> bookGoogleList) {
        AdapterBook adapter = new AdapterBook(bookGoogleList,this, favorites);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void addFavorite(BookGoogle book, List<BookGoogle> list) {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        list.add(book);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FavoritesList", json);
        editor.apply();
        Toast.makeText(this, book.getTitle() + " agregado a favoritos", Toast.LENGTH_SHORT).show();
    }
}


