package com.example.autores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class AdapterBook extends RecyclerView.Adapter<HolderBook> {

    private List<BookGoogle> bookList;
    private List<BookGoogle> favoritesList;
    InterfaceCallback.onClickListener fnAddFavorite;
    public AdapterBook(List<BookGoogle> _bookList , InterfaceCallback.onClickListener fnAddFavorite ,List<BookGoogle> favoritesList){
        bookList = _bookList ;
        this.fnAddFavorite = fnAddFavorite;
        this.favoritesList = favoritesList;
    }
    @NonNull
    @Override
    public HolderBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderBook(LayoutInflater.from(parent.getContext()).inflate(R.layout.component_row_book,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBook holder, int position) {
        final BookGoogle objBook = bookList.get(position);
        holder.setTextInTxvAuthor(objBook.getAutor());
        holder.setTextInTxvCategory(objBook.getCategoria());
        holder.setTextInTxvNumberPages(objBook.getPaginas());
        holder.setTextInTxvTitle(objBook.getTitle());
        holder.setImgSmallThumbnail(objBook.getSmallThumbnail());
        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnAddFavorite.addFavorite(objBook,favoritesList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

}
