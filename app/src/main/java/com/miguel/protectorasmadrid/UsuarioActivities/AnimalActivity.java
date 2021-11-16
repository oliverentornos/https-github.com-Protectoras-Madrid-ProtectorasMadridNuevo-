package com.miguel.protectorasmadrid.UsuarioActivities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.miguel.protectorasmadrid.Adapters.SliderAdapterAnimal;
import com.miguel.protectorasmadrid.Api.AnimalApi;
import com.miguel.protectorasmadrid.Api.Api;
import com.miguel.protectorasmadrid.Api.ProtectoraApi;
import com.miguel.protectorasmadrid.Api.UsuarioApi;
import com.miguel.protectorasmadrid.CitasFragment;
import com.miguel.protectorasmadrid.Clases.Animal;
import com.miguel.protectorasmadrid.Clases.Foto;
import com.miguel.protectorasmadrid.Clases.Protectora;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.Utils.Utiles;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalActivity extends AppCompatActivity {

AnimalActivity activity;
    private TextView tvNombre, tvDesc, tvFechaEntrada, tvFechaNacimiento, tvGenero, tvTamaño, tvProtectora;
    private ImageView imgvAtras, imgvShare;
    //private ImageView imagen;
    private LikeButton btnFavoritos;
    private Button btnPedirCita;
    SliderAdapterAnimal adapter;
    SliderView sliderView;
    Preferences preferences;
    int idAnimal;
    Animal animal;
    String fecha;
    List<Bitmap> fotosAnimal;
    List<Integer> listafavsIDS;
    AutoCompleteTextView spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        listafavsIDS = new ArrayList<>();
        //imagen = findViewById(R.id.imgvAnimal);
        activity = this;
        tvNombre = findViewById(R.id.tvNombre);
        tvDesc = findViewById(R.id.tvDesc);
        tvFechaEntrada = findViewById(R.id.tvFechaEntrada);
        tvFechaNacimiento = findViewById(R.id.tvFechaNac);
        btnPedirCita = findViewById(R.id.btnPedirCita);
        tvGenero = findViewById(R.id.tvGenero);
        tvTamaño = findViewById(R.id.tvTamano);
        tvProtectora = findViewById(R.id.tvProtectora);
        preferences = new Preferences(getApplicationContext());
        animal = new Animal();
        fotosAnimal = new ArrayList<>();
        idAnimal = getIntent().getIntExtra("idAnimal", 0);

        sliderView = findViewById(R.id.slideAnimal);
        sliderView.setIndicatorEnabled(true);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SCALE_DOWN); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        UsuarioApi serviceUsuario = Api.getClient().create(UsuarioApi.class);
        ProtectoraApi serviceProtectora = Api.getClient().create(ProtectoraApi.class);
        AnimalApi serviceAnimal = Api.getClient().create(AnimalApi.class);
        Call<Animal> llamadaanimal = serviceAnimal.getAnimalId(idAnimal);
        Usuario usuario = preferences.getUsuario();

        if(usuario==null){
            btnPedirCita.setVisibility(View.INVISIBLE);
        }


        llamadaanimal.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {

                animal = response.body();


                AnimalApi service = Api.getClient().create(AnimalApi.class);

                Call<List<Foto>> llamadaFotos = service.getImagenesAnimal(idAnimal);

                llamadaFotos.enqueue(new Callback<List<Foto>>() {
                    @Override
                    public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {


                        for (Foto foto : response.body()) {

                            Bitmap fotoAnimal = Utiles.base64ToBitmap(foto.getFoto());

                            fotosAnimal.add(fotoAnimal);


                        }
                        animal.setListaFotos(fotosAnimal);
                        //imagen.setImageBitmap(animal.getListaFotos().get(0));
                        tvNombre.setText(animal.getNombre());
                        tvDesc.setText(animal.getDescripcionAnimal());
                        tvFechaNacimiento.setText(animal.getFechaNacimiento());
                        tvFechaEntrada.setText(animal.getFechaEntrada());
                        tvGenero.setText(animal.getGenero());
                        tvTamaño.setText(animal.getTamanio());
                        tvProtectora.setText(animal.getProtectora());

                        // Metemos la lista de fotos en el Slider
                        adapter = new SliderAdapterAnimal(getApplicationContext(), animal.getListaFotos());
                        sliderView.setSliderAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<List<Foto>> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {

            }
        });

        if (usuario!=null) {

            Call<List<Integer>> llamadaFavs = serviceUsuario.getAnimalesFav(usuario.getIdUsuario());

            llamadaFavs.enqueue(new Callback<List<Integer>>() {
                @Override
                public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                    listafavsIDS = response.body();

                    for (Integer id : listafavsIDS) {

                        if (id.equals(animal.getIdAnimal())) {
                            btnFavoritos.setLiked(true);
                        }

                    }
                }

                @Override
                public void onFailure(Call<List<Integer>> call, Throwable t) {

                }
            });
        }


        // Boton favoritos
        btnFavoritos = findViewById(R.id.btnFavoritos);
        btnFavoritos.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {


                if (usuario !=null) {
                    Usuario usuario = preferences.getUsuario();
                    Call<Void> llamadainsert = serviceUsuario.insertFav(usuario.getIdUsuario(), idAnimal);

                    llamadainsert.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.body() != null) {
                                Toast.makeText(getApplicationContext(), "Toma ese like", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });

                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AnimalActivity.this);
                    builder.setTitle("Inicia sesion");
                    builder.setMessage("Necesitas tener cuenta para usar esta función");
                    builder.setPositiveButton("Iniciar sesion", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(AnimalActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Más tarde", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    likeButton.setLiked(false);
                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                if (preferences.hasCredentials()) {
                    Usuario usuario = preferences.getUsuario();
                    Call<Void> llamadadelete = serviceUsuario.eliminarFav(usuario.getIdUsuario(), idAnimal);
                    llamadadelete.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.body() != null) {
                                Toast.makeText(getApplicationContext(), "Animal eliminado", Toast.LENGTH_LONG);

                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });

                }
            }
        });

//Boton pedir cita
        btnPedirCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialogPersonalizado = new Dialog(AnimalActivity.this);
                dialogPersonalizado.setContentView(R.layout.dialog_cita);
                TextInputEditText etFechaCita = dialogPersonalizado.findViewById(R.id.etFechaCita);

                etFechaCita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker().setTitleText("Selecciona fecha");
                        MaterialDatePicker<Long> materialDatePicker = builder.build();
                        materialDatePicker.show(getSupportFragmentManager(), "tag");
                        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                            @Override
                            public void onPositiveButtonClick(Long selection) {
                                // Get the offset from our timezone and UTC.
                                TimeZone timeZoneUTC = TimeZone.getDefault();
                                // It will be negative, so that's the -1
                                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
                                // Create a date format, then a date object with our offset
                                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                Date date = new Date(selection + offsetFromUTC);



                                etFechaCita.setText(simpleFormat.format(date));

                                 fecha = etFechaCita.getText().toString();

                                spinner = dialogPersonalizado.findViewById(R.id.acHora);
                                ArrayList<String> horas = new ArrayList<String>();
                                horas.add("10:00");horas.add("11:00");horas.add("12:00");horas.add("13:00");horas.add("14:00");horas.add("15:00");horas.add("16:00");
                                horas.add("17:00");horas.add("18:00");horas.add("19:00");horas.add("20:00");
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner_verde, horas);
                                spinner.setAdapter(adapter);

                                Call<ArrayList<String>> llamadacita = serviceProtectora.citasProtectora(animal.getIdProtectora(),fecha);

                                llamadacita.enqueue(new Callback<ArrayList<String>>() {
                                    @Override
                                    public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {

                                        ArrayList<String>listaAux = response.body();
                                        ArrayList<Integer>borrarLista = new ArrayList<>();
                                        int contador = 0;
                                        for (String citabbdd:listaAux) {

                                            for (String hora:horas) {

                                                String horaRecortada = hora.substring(0,2);

                                                if (citabbdd.equals(horaRecortada)){
                                                    borrarLista.add(contador);
                                                    contador = -1;
                                                    break;
                                                }
                                                contador++;
                                            }

                                        }
                                        for (int i :borrarLista) {

                                            horas.remove(i);


                                        }
                                        adapter.notifyDataSetChanged();


                                    }


                                    @Override
                                    public void onFailure(Call<ArrayList<String>> call, Throwable t) {

                                    }
                                });




                            }
                        });
                    }
                });








                Button btnConfirmar = dialogPersonalizado.findViewById(R.id.btnConfirmar);
                btnConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (preferences.hasCredentials()) {
                            Call<Void> llamadainsert = serviceProtectora.insertCita(animal.getIdAnimal(),animal.getIdProtectora(),usuario.getIdUsuario(),fecha,spinner.getText().toString());

                            llamadainsert.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if(response.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Cita recibida :)", Toast.LENGTH_SHORT).show();
                                        dialogPersonalizado.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                        }
                    }
                });
                dialogPersonalizado.show();
            }
        });

        // Boton retroceder
        imgvAtras = findViewById(R.id.imgAtras);
        imgvAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Boton compartir
        imgvShare = findViewById(R.id.imgvShare);
        imgvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compartir = new Intent(Intent.ACTION_SEND);
                compartir.setType("text/plain");
                String mensaje = "https://flagcdn.com/ar.svg";
                compartir.putExtra(android.content.Intent.EXTRA_SUBJECT, "Empleos Baja App");
                compartir.putExtra(android.content.Intent.EXTRA_TEXT, mensaje);
                startActivity(Intent.createChooser(compartir, "Compartir vía"));
            }
        });
    }
    public static int obtenerPosicionItem(Spinner spinner, String fruta) {
        //Creamos la variable posicion y lo inicializamos en 0
        int posicion = 0;
        //Recorre el spinner en busca del ítem que coincida con el parametro `String fruta`
        //que lo pasaremos posteriormente
        for (int i = 0; i < spinner.getCount(); i++) {
            //Almacena la posición del ítem que coincida con la búsqueda
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(fruta)) {
                posicion = i;
            }
        }
        //Devuelve un valor entero (si encontro una coincidencia devuelve la
        // posición 0 o N, de lo contrario devuelve 0 = posición inicial)
        return posicion;
    }
}