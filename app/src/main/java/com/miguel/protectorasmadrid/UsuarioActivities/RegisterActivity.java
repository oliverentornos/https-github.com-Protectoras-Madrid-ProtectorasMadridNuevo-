package com.miguel.protectorasmadrid.UsuarioActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.miguel.protectorasmadrid.Api.Api;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.Api.UsuarioApi;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    //private ActivityRegisterBinding binding;
    private EditText etNombre, etPrimerApe, etSegApe, etTelefono, etContrasena, etRepContrasena, etNickname;
    private Button btnRegistrar;
    private Preferences preferences;
    private ImageView backButton;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNombre = findViewById(R.id.etNombre);
        etNickname = findViewById(R.id.etNickName);
        etPrimerApe = findViewById(R.id.etPrimerApe);
        etSegApe = findViewById(R.id.etSegApe);
        etTelefono = findViewById(R.id.etTelefono);
        etContrasena = findViewById(R.id.etContrasena);
        etRepContrasena = findViewById(R.id.etRepContrasena);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        //binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        preferences = new Preferences(getBaseContext());

        backButton = findViewById(R.id.imgAtras);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etContrasena.getText().toString().equals(etRepContrasena.getText().toString())) {
                    Usuario user = new Usuario( etNombre.getText().toString(), etPrimerApe.getText().toString(), etSegApe.getText().toString(), etTelefono.getText().toString(), etContrasena.getText().toString(),etNickname.getText().toString(),"data:image/png;base64,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");

                    UsuarioApi service = Api.getClient().create(UsuarioApi.class);
                    Call<Void> registro = service.registrarUsuario(
                            user.getIdUsuario(),
                            user.getNombre(),
                            user.getApe1(),
                            user.getApe2(),
                            user.getTelefono(),
                            user.getNickname(),
                            user.getPasswd(),
                            user.getImagen()
                    );
                                                                    //CAMBIAR EL ETNOMBRE POR EL ETNICKNAME.GETTEXT
                    Call<String> checkUser = service.checkUsername(etNickname.getText().toString());
                    checkUser.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.body() != null) {
                                Snackbar.make(v, "Elija otro nombre de usuario", Snackbar.LENGTH_LONG).show();


                            } else {
                                registro.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Snackbar.make(v, "Algo ha salido mal :(", Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Snackbar.make(v, "Comprueba la conexión", Snackbar.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    etRepContrasena.setText("");
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG);
                }
            }
        });
    }






}