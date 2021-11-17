package com.miguel.protectorasmadrid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.miguel.protectorasmadrid.Adapters.NoticiasAdapter;
import com.miguel.protectorasmadrid.Clases.Noticia;

import java.util.ArrayList;
import java.util.List;

public class NoticiasActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private NoticiasAdapter adapter;

    private List<Noticia> listNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_protectora);

        listNoticias = new ArrayList<>();
        recycler = findViewById(R.id.recyclerNoticiasProtectora);
        recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), LinearLayout.VERTICAL));

        //Noticias a capón
        //TODO: Añadir foto
        listNoticias.add(new Noticia("", "El CIMPA tendrá nueva aplicación móvil que podrá" +
                " descargarse en enero del año que viene", "3 alumnos de DAM del avellaneda " +
                "han desarrollado una app android que sera cedida a la protectora para su uso",
                "12/12/2021", "CIMPA"));

        listNoticias.add(new Noticia("", "El CIMPA tendrá nueva aplicación móvil que podrá" +
                " descargarse en enero del año que viene", "3 alumnos de DAM del avellaneda " +
                "han desarrollado una app android que sera cedida a la protectora para su uso",
                "12/12/2021", "CIMPA"));


        adapter = new NoticiasAdapter(getApplicationContext(), listNoticias);
        recycler.setAdapter(adapter);
    }
}