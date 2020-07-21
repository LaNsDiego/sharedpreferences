package com.example.autores;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

public class ConseguirSmallThumbnail extends AsyncTask<String,Void,Bitmap> {
    ImageView imgV;
    Bitmap bitmap;
    public ConseguirSmallThumbnail(ImageView imgV){
        this.imgV = imgV;
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        String urldisplay = url[0];
        bitmap = null;
        try {
            InputStream srt = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(srt);
        } catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imgV.setImageBitmap(bitmap);
    }
}
