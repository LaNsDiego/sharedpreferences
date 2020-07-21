package com.example.autores;

import java.util.List;

public interface InterfaceCallback {

    interface Protocol{
        void passListBookGoogle(List<BookGoogle> bookGoogleList);
    }

    interface onClickListener{
        void addFavorite(BookGoogle book , List<BookGoogle> list);
    }
}
