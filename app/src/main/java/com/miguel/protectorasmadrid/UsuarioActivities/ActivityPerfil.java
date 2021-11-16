package com.miguel.protectorasmadrid.UsuarioActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.miguel.protectorasmadrid.CitasFragment;
import com.miguel.protectorasmadrid.Clases.Protectora;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.MainActivityProtectora;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.Utils.Utiles;
import com.miguel.protectorasmadrid.databinding.ActivityMainBinding;


public class ActivityPerfil extends AppCompatActivity {

    private ActivityMainBinding binding;
    Preferences preferences;
    ImageView imagen;
    TextView tvNombreUsuario;
    Button btnCerrarSesion,btnCambiarPasswdUsu,btnCambiarFotoUsu;

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

        Usuario usuario = preferences.getUsuario();

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
    }
});



    }
}