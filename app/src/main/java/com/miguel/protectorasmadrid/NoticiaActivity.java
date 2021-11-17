package com.miguel.protectorasmadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticiaActivity extends AppCompatActivity {


    ImageView fotoNoticia;
    TextView tvTitulo, tvFecha, tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia2);

        tvTitulo = findViewById(R.id.tvTituloNoticia);
        tvDescripcion = findViewById(R.id.tvDescNoticia);
        tvFecha = findViewById(R.id.tvFechaNoticia);
        fotoNoticia = findViewById(R.id.imgvFotoNoticia);

        tvTitulo.setText(getIntent().getStringExtra("Titulo"));
        tvDescripcion.setText(getIntent().getStringExtra("Desc"));
        tvFecha.setText(getIntent().getStringExtra("Fecha"));



    }
}