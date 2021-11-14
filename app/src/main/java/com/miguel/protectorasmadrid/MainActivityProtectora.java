package com.miguel.protectorasmadrid;

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
import com.miguel.protectorasmadrid.databinding.ActivityMainBinding;
import com.miguel.protectorasmadrid.databinding.ActivityMainProtectoraBinding;


public class MainActivityProtectora extends AppCompatActivity {

    private ActivityMainProtectoraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ProtectorasMadrid);

        binding = ActivityMainProtectoraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView r = findViewById(R.id.recyclerId);
        BottomNavigationView navigationView = findViewById(R.id.nav_view_protectora);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_dashboard,  R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_protect);
        NavigationUI.setupWithNavController(binding.navViewProtectora, navController);


        // Scroll hacia inicio del recyclerView


        navigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {

                    r.smoothScrollToPosition(0);

                }
            }
        });


    }
}