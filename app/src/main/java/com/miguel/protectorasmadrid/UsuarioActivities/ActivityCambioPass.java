package com.miguel.protectorasmadrid.UsuarioActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.miguel.protectorasmadrid.Api.Api;
import com.miguel.protectorasmadrid.Api.UsuarioApi;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.Utils.Utiles;
import com.miguel.protectorasmadrid.databinding.ActivityCambioPasswdBinding;
import com.miguel.protectorasmadrid.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCambioPass extends AppCompatActivity {

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_passwd);
        setTheme(R.style.Theme_ProtectorasMadrid);
        ActivityCambioPasswdBinding binding = ActivityCambioPasswdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UsuarioApi service = Api.getClient().create(UsuarioApi.class);
        preferences = new Preferences(getApplicationContext());

        Usuario sharedUsuario = preferences.getUsuario();


        binding.btnGuardar.setOnClickListener(v->{

            String currentPassword = binding.tvpassActual.getText().toString();
            String newPassword = binding.tvpassNueva.getText().toString();
            String repeatNewPassword = binding.tvpassNuevaRep.getText().toString();

            //Comprobar que la contraseña actual realmente es la misma que la que ha escrito
            if (currentPassword.equals(sharedUsuario.getPasswd())){
                if (!newPassword.equals("") || !repeatNewPassword.equals("")){

                    if (newPassword.equals(repeatNewPassword)){

                        Call<Void> client = service.updatePassword(newPassword,sharedUsuario.getIdUsuario());

                        client.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                preferences.refreshCurrentUser(sharedUsuario.getIdUsuario());
                                binding.tvpassActual.setText("");
                                binding.tvpassNuevaRep.setText("");
                                binding.tvpassNueva.setText("");
                                Toast.makeText(getApplicationContext(), "Contraseña cambiada", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Snackbar.make(v, "Comprueba la conexión", Snackbar.LENGTH_LONG).show();
                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext(), "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "No se puede quedar vacío", Toast.LENGTH_SHORT).show();

                }

            }else {
                Toast.makeText(getApplicationContext(), "La contraseña no coincide", Toast.LENGTH_SHORT).show();
            }
        });






    }

}
