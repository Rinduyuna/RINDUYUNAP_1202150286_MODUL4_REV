package com.rinduyuna.rinduyunap_1202150286_modul4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tampilNama(View view) {
        Intent pindah = new Intent(getApplicationContext(), MahasiswaActivity.class);
        startActivity(pindah);
    }

    public void cariGambar(View view) {
        Intent a = new Intent(getApplicationContext(), CariGambarActivity.class);
        startActivity(a);
    }
}
