package com.example.ecommerce;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoad extends AsyncTask<String, Void, Bitmap> {
//    protected String url;
//    protected ImageView imageView;

//    public ImageLoad(String url, ImageView imageView) {
//        this.url = url;
//        this.imageView = imageView;
//    }


    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
