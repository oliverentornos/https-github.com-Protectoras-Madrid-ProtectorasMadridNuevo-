package com.miguel.protectorasmadrid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.miguel.protectorasmadrid.Clases.Protectora;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ProtectorasMadrid);

        preferences = new Preferences(getApplicationContext());
        if (preferences.hasCredentials()) {
            Protectora pro = preferences.getProtectora();
            Usuario usu = preferences.getUsuario();
            if (usu.getIdUsuario()==0)
            {
                startActivity(new Intent(getApplicationContext(), MainActivityProtectora.class));
            }
        }
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        RecyclerView r = findViewById(R.id.recyclerId);
        BottomNavigationView navView = findViewById(R.id.nav_view_usuario);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home_usuario,  R.id.navigation_notifications_usuario)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navViewUsuario, navController);


        // Scroll hacia inicio del recyclerView


        navView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home_usuario) {

                    r.smoothScrollToPosition(0);

                }
            }
        });


    }
}