package com.miguel.protectorasmadrid.UsuarioActivities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.miguel.protectorasmadrid.Api.Api;
import com.miguel.protectorasmadrid.Api.UsuarioApi;
import com.miguel.protectorasmadrid.CitasFragment;
import com.miguel.protectorasmadrid.Clases.Protectora;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.InicioFragment;
import com.miguel.protectorasmadrid.MainActivity;
import com.miguel.protectorasmadrid.MainActivityProtectora;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.Utils.Utiles;
import com.miguel.protectorasmadrid.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityPerfil extends AppCompatActivity {
    UsuarioApi serviceUsuario = Api.getClient().create(UsuarioApi.class);
    private ActivityMainBinding binding;
    Preferences preferences;
    ImageView imagen;
    TextView tvNombreUsuario;
    Button btnCerrarSesion,btnCambiarPasswdUsu,btnCambiarFotoUsu;
    Usuario usuario = MainActivity.usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        setTheme(R.style.Theme_ProtectorasMadrid);


        btnCerrarSesion = findViewById(R.id.btnCerrarSesionUsu);
        btnCambiarPasswdUsu = findViewById(R.id.btnCambiarPasswdUsu);
        btnCambiarFotoUsu = findViewById(R.id.btnCambiarFotoUsu);
        preferences = new Preferences(getApplicationContext());

        tvNombreUsuario = findViewById(R.id.tvNombreCuentaUsuario);
                imagen = findViewById(R.id.imageViewCuentaUsuario);

        Usuario usuario = MainActivity.usu;

        tvNombreUsuario.setText(usuario.getNombre() +" "+usuario.getApe1());
        imagen.setImageBitmap(Utiles.base64ToBitmap(usuario.getImagen()));


btnCambiarPasswdUsu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getApplicationContext(), ActivityCambioPass.class);
        startActivity(intent);

    }
});

btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        preferences.forgetCredentials();
        Toast.makeText(getApplicationContext(), "Sesion cerrada", Toast.LENGTH_SHORT).show();

        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
});

btnCambiarFotoUsu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        addFoto(view);

    }
});



    }

    public void addFoto(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, 100);

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        Uri imageUri;
        Bitmap bitmap;
        try {
            if (resultCode == RESULT_OK && reqCode == 100) {
                imageUri = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imagen.setImageBitmap(bitmap);

                Call<Void> llamadaimagen = serviceUsuario.updateImagen(usuario.getIdUsuario(),"data:image/png;base64,"+Utiles.bitmapToBase64(bitmap));

                llamadaimagen.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Foto cambiada correctamente", Toast.LENGTH_SHORT).show();

                            CuentaUsuario.imageViewCuenta.setImageBitmap(bitmap);
                            preferences.forgetCredentials();
                            preferences.saveCredentials(MainActivity.usu);
                            MainActivity.usu.setImagen("data:image/png;base64,"+Utiles.bitmapToBase64(bitmap));

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}