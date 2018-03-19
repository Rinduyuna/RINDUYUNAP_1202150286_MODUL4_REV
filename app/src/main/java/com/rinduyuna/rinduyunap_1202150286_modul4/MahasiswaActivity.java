package com.rinduyuna.rinduyunap_1202150286_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MahasiswaActivity extends AppCompatActivity {

    //deklarasi
    private ListView mListView;
    private ProgressBar mProgressBar;
    private String[] mMahasiswa = {"Rindu", "Andi", "Andra", "Adhi", "Beni", "Budi", "Ayu", "Linda", "Nadia", "Bayu", "Tedi", "Azka"};

    private AddItemToListView mAddItemToListView;
    private Button mMulai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        //inisiasi

        mProgressBar = (ProgressBar) findViewById(R.id.progressbarMahasiswa);
        mListView = (ListView) findViewById(R.id.listviewMahasiswa);
        mMulai = (Button) findViewById(R.id.buttonMulai);

        //adapternya
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));


        //asynctask
        mMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }


    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        ProgressDialog mProgressDialog = new ProgressDialog(MahasiswaActivity.this);
        private ArrayAdapter<String> mAdapter;
        private int counter = 1;

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();


            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Waiting ...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (String item : mMahasiswa) {
                publishProgress(item);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    mAddItemToListView.cancel(true);
                    Intent cancel = new Intent(getApplicationContext(), MahasiswaActivity.class);
                    startActivity(cancel);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int) ((counter / (float) mMahasiswa.length) * 100);
            mProgressBar.setProgress(current_status);

            //set progress only working for horizontal loading
            mProgressDialog.setProgress(current_status);

            //set message will not working when using horizontal loading
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressBar.setVisibility(View.GONE);
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }
}

