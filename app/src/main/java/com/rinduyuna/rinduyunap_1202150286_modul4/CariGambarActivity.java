package com.rinduyuna.rinduyunap_1202150286_modul4;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class CariGambarActivity extends AppCompatActivity {

    //mendeklarasikan variablenya
    private EditText mPictureInput;
    private ImageView mGambar;
    private Button mSearch;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        // Memanggil variablenya
        mPictureInput = (EditText) findViewById(R.id.gambar);
        mGambar = (ImageView) findViewById(R.id.hasilGambar);
        mSearch = (Button) findViewById(R.id.search);

    }

    public void search(View view) {
        loadImageInit();
    }

    private void loadImageInit() {
        String ImgUrl = mPictureInput.getText().toString();
        //AsyncTask mencari gambar di internet
        new loadImage().execute(ImgUrl);
    }

    private class loadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Membuat Progress Dialog
            mProgressDialog = new ProgressDialog(CariGambarActivity.this);

            // settingnya proggres dialog
            mProgressDialog.setMessage("Loading...");

            // menampilkan Progress Dialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                // mendownload gambar dari url
                URL url = new URL(params[0]);
                // mengkonversikan gambar ke bitmat
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // menampung gambar ke imageView dan menampilkannya
            mGambar.setImageBitmap(bitmap);

            // menghilangkan Progress Dialog
            mProgressDialog.dismiss();
        }

    }
}
