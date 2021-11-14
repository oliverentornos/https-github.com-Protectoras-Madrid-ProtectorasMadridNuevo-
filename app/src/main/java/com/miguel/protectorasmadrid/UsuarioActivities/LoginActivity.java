package com.miguel.protectorasmadrid.UsuarioActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.miguel.protectorasmadrid.Api.Api;
import com.miguel.protectorasmadrid.Api.ProtectoraApi;
import com.miguel.protectorasmadrid.Api.UsuarioApi;
import com.miguel.protectorasmadrid.Clases.Protectora;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.MainActivity;
import com.miguel.protectorasmadrid.MainActivityProtectora;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.UsuarioActivities.RegisterActivity;
import com.miguel.protectorasmadrid.Utils.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  {

    private TextView tvRegistrarse;
    private Button btnEntrar;
    private EditText nombre,passwd;
   public static boolean admin = false;

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        preferences = new Preferences(getApplicationContext());
        nombre = findViewById(R.id.etNombre);
        passwd =  findViewById(R.id.etPassword);
        btnEntrar = findViewById(R.id.btnEntrar);
        tvRegistrarse = findViewById(R.id.tvRegistrarse);






        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombretxt,passwdtxt;
                nombretxt = nombre.getText().toString();
                passwdtxt = passwd.getText().toString();

                login(view,nombretxt,passwdtxt);

            }
        });
        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

    }
    private void login(View v,String useraux,String passwdaux) {

        String nombre = useraux;
        String passwd = passwdaux;


        UsuarioApi service = Api.getClient().create(UsuarioApi.class);

        Call<Usuario> llamadaUsuario = service.doLogin(nombre, passwd);
        llamadaUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (nombre.equals("") || passwd.equals("")) {

                        Snackbar.make(v, "Ingrese los campos obligatorios", Snackbar.LENGTH_LONG).show();

                    } else {
                        Usuario sharedClient = response.body();
                        // Credenciales correctas
                        if (sharedClient != null) {
                            // Credenciales correctas, guardamos al usuario y redirigimos a pantalla principal

                            preferences.saveCredentials(sharedClient);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();


                        } else {

                            ProtectoraApi service = Api.getClient().create(ProtectoraApi.class);
                            Call<Protectora> llamadaProtectora = service.doLoginPro(nombre, passwd);

                            llamadaProtectora.enqueue(new Callback<Protectora>() {
                                @Override
                                public void onResponse(Call<Protectora> call, Response<Protectora> response) {

                                    if(response.isSuccessful()){
                                        Protectora sharedProtect = response.body();
                                        if(sharedProtect != null) {
                                            preferences.saveCredentialsProtect(sharedProtect);
                                            admin = true;
                                            startActivity(new Intent(getApplicationContext(), MainActivityProtectora.class));
                                            finish();
                                        }else {
                                            Snackbar.make(v, "Usuario o contraseña inválida", Snackbar.LENGTH_LONG).show();

                                        }


                                    }else {
                                        Snackbar.make(v, "Usuario o contraseña inválida", Snackbar.LENGTH_LONG).show();
                                    }


                                }

                                @Override
                                public void onFailure(Call<Protectora> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Comprueba la conexión", Toast.LENGTH_SHORT).show();

                                }
                            });


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Comprueba la conexión", Toast.LENGTH_SHORT).show();

            }

        });
    }

}