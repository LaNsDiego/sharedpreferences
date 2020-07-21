package com.example.autores;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;

public class HolderBook extends RecyclerView.ViewHolder {

    private TextView txvTitle;
    private TextView txvAuthor;
    private TextView txvNumberPages;
    private TextView txvCategory;
    private ImageView imgSmallThumbnail;
    public  ImageButton btnFavorite;
    public HolderBook(@NonNull View itemView) {
        super(itemView);
        txvTitle = itemView.findViewById(R.id.txv_title);
        txvAuthor = itemView.findViewById(R.id.txv_author);
        txvNumberPages = itemView.findViewById(R.id.txv_number_pages);
        txvCategory = itemView.findViewById(R.id.txv_category);
        imgSmallThumbnail = itemView.findViewById(R.id.img_photo_book);
        btnFavorite = itemView.findViewById(R.id.btn_favorites);
    }


    public TextView getTxvTitle() {
        return txvTitle;
    }

    public void setTextInTxvTitle(String title) {
        txvTitle.setText(title);
    }

    public TextView getTxvAuthor() {
        return txvAuthor;
    }

    public void setTextInTxvAuthor(String author) {
        txvAuthor.setText(author);
    }

    public TextView getTxvNumberPages() {
        return txvNumberPages;
    }

    public void setTextInTxvNumberPages(String numberPages) {
        txvNumberPages.setText(numberPages);
    }

    public TextView getTxvCategory() {
        return txvCategory;
    }

    public void setTextInTxvCategory(String category) {
        txvCategory.setText(category);
    }

    public ImageView getImgSmallThumbnail() {
        return imgSmallThumbnail;
    }

    public void setImgSmallThumbnail(String _imgSmallThumbnail) {
        if(_imgSmallThumbnail != null){
            new ConseguirSmallThumbnail(this.imgSmallThumbnail).execute(_imgSmallThumbnail);
    }
    }
}
