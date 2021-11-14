package com.miguel.protectorasmadrid.ProtectoraActivities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.miguel.protectorasmadrid.CitasFragment;
import com.miguel.protectorasmadrid.UsuarioActivities.LoginActivity;
import com.miguel.protectorasmadrid.UsuarioActivities.RegisterActivity;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.databinding.FragmentCuentaProtectoraBinding;


public class CuentaProtectora extends Fragment  {


    private FragmentCuentaProtectoraBinding binding;

    Activity activity;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = binding.inflate(inflater, container, false);
        View root = binding.getRoot();


binding.fragmentCitasProtec.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getContext(), CitasFragment.class);
        startActivity(intent);

    }
});

binding.tvTusAnimales.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});

        Preferences preferences = new Preferences(getContext());
        if (preferences.hasCredentials()) {
            Toast.makeText(getContext(), "Estas logueado", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Inicia sesion");
            builder.setMessage("Necesitas tener cuenta para usar esta función");
            builder.setPositiveButton("Iniciar sesion", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Más tarde", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getActivity().onBackPressed();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}