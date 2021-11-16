package com.miguel.protectorasmadrid;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.chip.Chip;
import com.miguel.protectorasmadrid.Adapters.*;
import com.miguel.protectorasmadrid.Api.AnimalApi;
import com.miguel.protectorasmadrid.Api.Api;

import com.miguel.protectorasmadrid.Api.ProtectoraApi;
import com.miguel.protectorasmadrid.Clases.Cita;
import com.miguel.protectorasmadrid.Clases.PortadaAnimal;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.databinding.FragmentHomeBinding;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioFragment extends Fragment implements SearchView.OnQueryTextListener, Chip.OnClickListener {

    private FragmentHomeBinding binding;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private NumberPicker numberPickerMin, numberPickerMax;
    ModalBottomSheet sheetButton;
    private RecycleAdapter adapter;
    static Chip chipTamano, chipEspecie, chipEdad, chipUbicacion;

    private SwipeRefreshLayout refreshLayout;

    ArrayList<PortadaAnimal> animales = new ArrayList<PortadaAnimal>();
    List<Bitmap> fotosAnimales;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sheetButton = new ModalBottomSheet();

        searchView = root.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(this);

        recyclerView = root.findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));

        chipEdad = root.findViewById(R.id.chipEdad);
        chipEspecie = root.findViewById(R.id.chipEspecie);
        chipTamano = root.findViewById(R.id.chipTamano);
        chipUbicacion = root.findViewById(R.id.chipUbicacion);

        chipUbicacion.setOnClickListener(this);
        chipEspecie.setOnClickListener(this);
        chipTamano.setOnClickListener(this);
        chipEdad.setOnClickListener(this);

        chipTamano.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipTamano.setText("Tamaño");
                chipTamano.setCloseIconVisible(false);
                chipTamano.setChipBackgroundColorResource(R.color.backgroundLightColor);
            }
        });

        refreshLayout = root.findViewById(R.id.swipeRefresh);
        // Refrescar Recycler
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                AnimalApi service = Api.getClient().create(AnimalApi.class);
                Call<ArrayList<PortadaAnimal>> listadoanimales = service.getAnimales();

                listadoanimales.enqueue(new Callback<ArrayList<PortadaAnimal>>() {
                    @Override
                    public void onResponse(Call<ArrayList<PortadaAnimal>> call, Response<ArrayList<PortadaAnimal>> response) {
                        animales = response.body();


                        adapter = new RecycleAdapter(animales, getContext());
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<PortadaAnimal>> call, Throwable t) {
                        Toast.makeText(getContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
                    }
                });

                refreshLayout.setRefreshing(false);
            }
        });

        AnimalApi service = Api.getClient().create(AnimalApi.class);
        Call<ArrayList<PortadaAnimal>> listadoanimales = service.getAnimales();

        listadoanimales.enqueue(new Callback<ArrayList<PortadaAnimal>>() {
            @Override
            public void onResponse(Call<ArrayList<PortadaAnimal>> call, Response<ArrayList<PortadaAnimal>> response) {
                animales = response.body();


                adapter = new RecycleAdapter(animales, getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<PortadaAnimal>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });

        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    // SearchView
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        try {
            adapter.filter(newText);
            return false;
        } catch (Exception ex){
            return false;
        }
    }

    //Filtros
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chipTamano:
                sheetButton.show(getActivity().getSupportFragmentManager(), ModalBottomSheet.TAG);
                break;
            case R.id.chipEdad:


        }
    }
}