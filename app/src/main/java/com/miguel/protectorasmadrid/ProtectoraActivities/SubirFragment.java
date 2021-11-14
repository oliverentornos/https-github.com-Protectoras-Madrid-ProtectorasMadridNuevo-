package com.miguel.protectorasmadrid.ProtectoraActivities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import com.miguel.protectorasmadrid.Api.AnimalApi;
import com.miguel.protectorasmadrid.Api.Api;
import com.miguel.protectorasmadrid.Api.UsuarioApi;
import com.miguel.protectorasmadrid.Clases.Animal;
import com.miguel.protectorasmadrid.Clases.Protectora;
import com.miguel.protectorasmadrid.Clases.Usuario;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.Adapters.SliderAdapter;
import com.miguel.protectorasmadrid.Utils.Preferences;
import com.miguel.protectorasmadrid.Utils.Utiles;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubirFragment extends AppCompatActivity {
    final static int RESULT_LOAD_IMG = 100;
    private ImageView btnCerrar;
    private SwitchMaterial switchSalud;
    private TextInputEditText etNombre, etDesc, etDescSalud, etFechaEntrada, etFechaNacimiento, etEspecie;
    private ScrollView scrollview;
    private AutoCompleteTextView acGenero, acTamano;
    private SliderView imageSlider;
    private SliderAdapter adapter;
    private Button btnSubir;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_subir);


        btnCerrar = findViewById(R.id.btnCerrar);
        switchSalud = findViewById(R.id.switchSalud);
        preferences = new Preferences(getApplicationContext());

        Protectora sharedProtec = preferences.getProtectora();
        AnimalApi service = Api.getClient().create(AnimalApi.class);
        imageSlider = findViewById(R.id.imageSlider);
        etNombre = findViewById(R.id.etNombre);
        acTamano = findViewById(R.id.acTamano);
        acGenero = findViewById(R.id.acGenero);
        etEspecie = findViewById(R.id.etEspecie);
        etFechaEntrada = findViewById(R.id.etFechaEntrada);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etDesc = findViewById(R.id.etDescripcion);
        etDescSalud = findViewById(R.id.etSalud);
        btnSubir = findViewById(R.id.btnSubir);

        scrollview = findViewById(R.id.svSubir);


        adapter = new SliderAdapter(this);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.SCALE_DOWN); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);


        // Esconder teclado al hacer scroll
        scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return false;
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        switchSalud.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    etDescSalud.setEnabled(true);
                } else {
                    etDescSalud.setEnabled(false);
                    etDescSalud.setText("");
                }

            }
        });
        btnSubir.setOnClickListener(v -> {
            if (etNombre.getText().equals("") || acTamano.getText().equals("") || etDescSalud.getText().equals("")
                    || etFechaNacimiento.getText().equals("") || etEspecie.getText().equals("")
                    || etFechaEntrada.getText().equals("") || acGenero.getText().equals("")) {

                Toast.makeText(getApplicationContext(), "Rellene todos los campos obligatorios", Toast.LENGTH_SHORT).show();

            } else {
                Animal animal = new Animal();
                animal.setIdAnimal(animal.generarId(etNombre.getEditableText().toString(), sharedProtec.getidProtectora()));

                Call<Void> registro = service.insertarAnimal(
                        animal.getIdAnimal(),
                        sharedProtec.getidProtectora(),
                        etNombre.getText().toString(),
                        acTamano.getText().toString(),
                        etDescSalud.getText().toString(),
                        etDesc.getText().toString(),
                        etFechaNacimiento.getText().toString(),
                        etEspecie.getText().toString(),
                        etFechaEntrada.getText().toString(),
                        acGenero.getText().toString());

                registro.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {





                            for (Bitmap imagen : adapter.getListlistFotos()) {
                                String imagensql = "data:image/png;base64," + Utiles.bitmapToBase64(imagen);
                                Call<Void> subirFoto = service.insertarFoto(animal.getIdAnimal(), imagensql);
                                Log.i("fff", imagensql);
                                subirFoto.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {


                                            etNombre.setText("");
                                            acTamano.setText("");
                                            etDescSalud.setText("");
                                            etDesc.setText("");
                                            etFechaNacimiento.setText("");
                                            etEspecie.setText("");
                                            etFechaEntrada.setText("");
                                            acGenero.setText("");

                                            Toast.makeText(getApplicationContext(), "Animal subido correctamente", Toast.LENGTH_LONG).show();


                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });
                            }
                        finish();


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });

        String[] generos = {"Macho", "Hembra"};
        ArrayAdapter<String> adapterGenero = new ArrayAdapter<String>(this, R.layout.item_textview, generos);
        acGenero.setAdapter(adapterGenero);

        String[] tamanos = {"Pequeño", "Mediano", "Grande"};
        ArrayAdapter<String> adapterTamano = new ArrayAdapter<String>(this, R.layout.item_textview, tamanos);
        acTamano.setAdapter(adapterTamano);


    }



    public void deleteFoto(View view) {
        int pos = imageSlider.getCurrentPagePosition();
        adapter.deleteItem(pos);


    }

    public void addFoto(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMG);

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        Uri imageUri;
        Bitmap bitmap;
        try {
            if (resultCode == RESULT_OK && reqCode == 100) {
                imageUri = data.getData();
                imageSlider.setSliderAdapter(adapter);

                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                adapter.addItem(bitmap);


                imageSlider.setInfiniteAdapterEnabled(adapter.getCount() != 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectFecha(View view) {
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

                if (view.getId() == R.id.etFechaEntrada) {
                    etFechaEntrada.setText(simpleFormat.format(date));
                } else {
                    etFechaNacimiento.setText(simpleFormat.format(date));
                }

            }
        });
    }


}