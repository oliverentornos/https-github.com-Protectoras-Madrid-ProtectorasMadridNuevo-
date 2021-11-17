package com.miguel.protectorasmadrid.UsuarioActivities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.miguel.protectorasmadrid.CitasFragment;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.MainActivity;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.Utils.Utiles;
import com.miguel.protectorasmadrid.databinding.FragmentCuentaUsuarioBinding;

public class CuentaUsuario extends Fragment  {


    private FragmentCuentaUsuarioBinding binding;
    private TextView tvNombreUsuario;
    Preferences preferences;
   static ImageView imageViewCuenta;
    private TextView tvPerfilUsuario;

    Activity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        preferences = new Preferences(getContext());
        imageViewCuenta = binding.imageViewCuenta;
        tvNombreUsuario = binding.tvNombreCuenta;
        tvPerfilUsuario = binding.tvPerfil;
        Usuario usuario = MainActivity.usu;



        if (preferences.hasCredentials()) {
            Toast.makeText(getContext(), "Estas logueado", Toast.LENGTH_SHORT).show();
            tvNombreUsuario.setText(usuario.getNombre() +" "+usuario.getApe1());
            imageViewCuenta.setImageBitmap(Utiles.base64ToBitmap(usuario.getImagen()));
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

        binding.citasLayoutUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), CitasFragment.class);
                startActivity(intent);

            }
        });

        binding.tvLayoutPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), ActivityPerfil.class);
                startActivity(intent);

            }
        });






        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}