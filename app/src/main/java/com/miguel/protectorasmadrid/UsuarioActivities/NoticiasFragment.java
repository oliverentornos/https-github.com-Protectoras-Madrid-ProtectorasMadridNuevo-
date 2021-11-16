package com.miguel.protectorasmadrid.UsuarioActivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.miguel.protectorasmadrid.Adapters.NoticiasAdapter;
import com.miguel.protectorasmadrid.Clases.Noticia;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.databinding.FragmentCitasProtectoraBinding;
import com.miguel.protectorasmadrid.databinding.FragmentNoticiasBinding;

import java.util.ArrayList;
import java.util.List;


public class NoticiasFragment extends Fragment {

    FragmentNoticiasBinding binding;

    private RecyclerView recyclerNoticias;
    private NoticiasAdapter adapter;
    private List<Noticia> listNoticias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listNoticias = new ArrayList<>();
        recyclerNoticias = root.findViewById(R.id.recyclerNoticias);
        recyclerNoticias.setLayoutManager(new GridLayoutManager(getContext(), LinearLayout.VERTICAL));

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


        adapter = new NoticiasAdapter(getContext(), listNoticias);
        recyclerNoticias.setAdapter(adapter);



        return root;
    }
}