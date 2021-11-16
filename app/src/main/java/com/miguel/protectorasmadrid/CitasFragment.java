package com.miguel.protectorasmadrid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.miguel.protectorasmadrid.Adapters.CitasAdapter;
import com.miguel.protectorasmadrid.Api.Api;
import com.miguel.protectorasmadrid.Api.ProtectoraApi;
import com.miguel.protectorasmadrid.Api.UsuarioApi;
import com.miguel.protectorasmadrid.Clases.Cita;
import com.miguel.protectorasmadrid.Clases.Protectora;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.databinding.FragmentCitasProtectoraBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitasFragment extends AppCompatActivity {


    ArrayList<Cita> citas;
    private RecyclerView recyclerCitas;
    private CitasAdapter citasAdapter;
    private List<Cita> listaCitas = new ArrayList<>();
    private TextView tvTitulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_citas_protectora);



        citas = new ArrayList<>();
        recyclerCitas = (RecyclerView)findViewById(R.id.lvCitas);

        recyclerCitas.setLayoutManager(new GridLayoutManager(getApplicationContext(), LinearLayout.VERTICAL));


        Preferences preferences = new Preferences(getApplicationContext());

        Protectora protectora = preferences.getProtectora();
        Usuario usuario = preferences.getUsuario();




        if (protectora.getidProtectora() != 0){
            ProtectoraApi serviceProtect = Api.getClient().create(ProtectoraApi.class);
            String fecha = "";
            Call<ArrayList<Cita>> listaCitas = serviceProtect.getCitas(preferences.getProtectora().getidProtectora(),fecha);
            listaCitas.enqueue(new Callback<ArrayList<Cita>>() {
                @Override
                public void onResponse(Call<ArrayList<Cita>> call, Response<ArrayList<Cita>> response) {

                    citas = response.body();

                    citasAdapter = new CitasAdapter(getApplicationContext(), citas);
                    recyclerCitas.setAdapter(citasAdapter);

                }

                @Override
                public void onFailure(Call<ArrayList<Cita>> call, Throwable t) {

                }
            });

    }

        if (usuario.getIdUsuario() != 0){

            UsuarioApi serviceUsu = Api.getClient().create(UsuarioApi.class);
            Call<ArrayList<Cita>> listaCitasusu = serviceUsu.getCitas(preferences.getUsuario().getIdUsuario());
            listaCitasusu.enqueue(new Callback<ArrayList<Cita>>() {
                @Override
                public void onResponse(Call<ArrayList<Cita>> call, Response<ArrayList<Cita>> response) {

                    citas = response.body();

                    citasAdapter = new CitasAdapter(getApplicationContext(), citas);
                    recyclerCitas.setAdapter(citasAdapter);

                }

                @Override
                public void onFailure(Call<ArrayList<Cita>> call, Throwable t) {

                }
            });

        }







    }
}
