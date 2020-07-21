package com.example.autores;

import android.os.AsyncTask;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ConseguirLibro extends AsyncTask<String,Void,String> {

    InterfaceCallback.Protocol protocol;

    ConseguirLibro(InterfaceCallback.Protocol protocol){
        this.protocol = protocol;
    }

    @Override
    protected String doInBackground(String... strings) {
        return UtilidadesRed.obtenerInformacionLibro(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        List<BookGoogle> bookGoogleList = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i= 0;

            while(i < itemsArray.length()){
                BookGoogle objBookGoogle = new BookGoogle();
                JSONObject libro = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = libro.getJSONObject("volumeInfo");
                try{
                    objBookGoogle.setTitle(volumeInfo.getString("title"));

                    //AUTHORS
                    JSONArray authors = volumeInfo.getJSONArray("authors");
                    String strAuthors = "";
                    strAuthors = authors.getString(0);
                    for(int a = 1 ; a < authors.length();a++){
                        strAuthors = strAuthors +" ," + authors.getString(a);
                    }
                    objBookGoogle.setAutor(strAuthors);

                    //CATEGORIRES
                    String strCategories = "" ;
                    if(volumeInfo.has("categories")){
                        JSONArray categories = volumeInfo.getJSONArray("categories");
                        strCategories = categories.getString(0);
                        for(int cat = 1 ; cat < authors.length();cat++){
                            strCategories = strCategories +" ," + categories.getString(cat);
                        }
                    }
                    objBookGoogle.setCategoria(strCategories);

                    //PAGE COUNT
                    if(volumeInfo.has("pageCount")){
                        objBookGoogle.setPaginas(volumeInfo.getString("pageCount"));
                    }
                    if(volumeInfo.has("imageLinks")){
                        JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                        objBookGoogle.setSmallThumbnail(imageLinks.getString("smallThumbnail"));
                    }
                    bookGoogleList.add(objBookGoogle);
                }catch (Exception e){
                    e.printStackTrace();
                }
                i++;
            }
            protocol.passListBookGoogle(bookGoogleList);


        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}