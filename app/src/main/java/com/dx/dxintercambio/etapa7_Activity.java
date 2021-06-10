package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class etapa7_Activity extends AppCompatActivity {

    private CheckBox fondoPlaga , pisoPlaga , techoPlaga , lucesCheck1 , luzGabildo,  paredDerPlagas , paredIzqPlagas , patinDer,
            cuartoDer, loderaDer , lucesCheck2, luzBarco , rompevientosDer;
    private Boolean check_fondoPlaga = true, check_pisoPlaga = true, check_techoPlaga = true, check_lucesCheck1 = true, check_luzGabildo= true,
            check_paredDerPlagas = true, check_paredIzqPlagas = true, check_patinDer= true,
            check_cuartoDer= true, check_loderaDer = true, check_lucesCheck2= true, check_luzBarco = true, check_rompevientosDer= true;
    private String path ,string_fondoPlaga , string_pisoPlaga , string_techoPlaga , string_lucesCheck1 , string_luzGabildo,  string_paredDerPlagas , string_paredIzqPlagas , string_patinDer,
            string_cuartoDer, string_loderaDer , string_lucesCheck2, string_luzBarco , string_rompevientosDer;
    private ImageView IV_costadoDerAtras ,IV_costadoDerFrente ,daño1,daño2,daño3,daño4;;
    private Bitmap actualDerFrontal  ,actualDerTrasero ,actual_daño1,actual_daño2,actual_daño3,actual_daño4;
    private Button btnEtapa7 ;
    public static final int REQUEST_DER_COSTADO_ATRAS = 100;
    public static final int REQUEST_DER_COSTADO_FRENTE = 101;
    public static final int REQUEST_DAÑO1 = 102;
    public static final int REQUEST_DAÑO2 = 103;
    public static final int REQUEST_DAÑO3 = 104;
    public static final int REQUEST_DAÑO4 = 105;
    private File imageFile;
    private int widthScreen;
    private Uri photoURI;
    private EditText comentarios ;
    private String  imageFileName , folio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa7);

        folio = getIntent().getStringExtra("folio");
        path = getIntent().getStringExtra("path");

        fondoPlaga = (CheckBox) findViewById(R.id.CB_fondoCaja);
        pisoPlaga = (CheckBox) findViewById(R.id.CB_pisoPlagas);
        techoPlaga = (CheckBox) findViewById(R.id.CB_techoPlagas);
        lucesCheck1 = (CheckBox) findViewById(R.id.CB_lucesCheckDer);
        luzGabildo = (CheckBox) findViewById(R.id.CB_luzGabDer);
        paredDerPlagas = (CheckBox) findViewById(R.id.CB_paredPlagasDer);
        paredIzqPlagas = (CheckBox) findViewById(R.id.CB_paredPlagasIzq);
        patinDer = (CheckBox) findViewById(R.id.CB_patinDer);
        cuartoDer = (CheckBox) findViewById(R.id.CB_cuartoDer);
        loderaDer = (CheckBox) findViewById(R.id.CB_loderaDer);
        lucesCheck2 = (CheckBox) findViewById(R.id.CB_derLucesP2);
        luzBarco = (CheckBox) findViewById(R.id.CB_luzBarcDer);
        rompevientosDer = (CheckBox) findViewById(R.id.CB_rompevientesDer);

        comentarios= (EditText) findViewById(R.id.editTextTextMultiLine5);

        IV_costadoDerAtras = (ImageView) findViewById(R.id.IV_costadoIzqAtras);
        IV_costadoDerFrente = (ImageView) findViewById(R.id.IV_costadoIzqFrente);
        daño1 = (ImageView) findViewById(R.id.IV_dañoDer);
        daño2 = (ImageView) findViewById(R.id.IV_dañoDer1);
        daño3 = (ImageView) findViewById(R.id.IV_dañoDer2);
        daño4 = (ImageView) findViewById(R.id.IV_dañoDer3);

        btnEtapa7 =  (Button) findViewById(R.id.btn_etapa7);

        fondoPlaga.setOnClickListener(view -> {

            check_fondoPlaga = ((CheckBox) view).isChecked();

        });
        pisoPlaga.setOnClickListener(view -> {

            check_pisoPlaga = ((CheckBox) view).isChecked();

        });
        techoPlaga.setOnClickListener(view -> {

            check_techoPlaga = ((CheckBox) view).isChecked();

        });
        lucesCheck1.setOnClickListener(view -> {

            check_lucesCheck1 = ((CheckBox) view).isChecked();

        });
        luzGabildo.setOnClickListener(view -> {

            check_luzGabildo = ((CheckBox) view).isChecked();

        });
        paredDerPlagas.setOnClickListener(view -> {

            check_paredDerPlagas = ((CheckBox) view).isChecked();

        });
        paredIzqPlagas.setOnClickListener(view -> {

            check_paredIzqPlagas = ((CheckBox) view).isChecked();

        });
        patinDer.setOnClickListener(view -> {

            check_patinDer = ((CheckBox) view).isChecked();

        });
        cuartoDer.setOnClickListener(view -> {

            check_cuartoDer = ((CheckBox) view).isChecked();

        });
        loderaDer.setOnClickListener(view -> {

            check_loderaDer = ((CheckBox) view).isChecked();

        });
        lucesCheck2.setOnClickListener(view -> {

            check_lucesCheck2 = ((CheckBox) view).isChecked();

        });
        luzBarco.setOnClickListener(view -> {

            check_luzBarco = ((CheckBox) view).isChecked();

        });
        rompevientosDer.setOnClickListener(view -> {

            check_rompevientosDer = ((CheckBox) view).isChecked();

        });

        IV_costadoDerAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisIzqFrontal", REQUEST_DER_COSTADO_ATRAS);

            }
        });

        IV_costadoDerFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisIzqFrontal", REQUEST_DER_COSTADO_FRENTE);

            }
        });

        btnEtapa7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check_fondoPlaga) {
                    string_fondoPlaga = "1";
                }else{
                    string_fondoPlaga = "0";
                }
                if (check_pisoPlaga) {
                    string_pisoPlaga = "1";
                }else{
                    string_pisoPlaga = "0";
                }
                if (check_techoPlaga) {
                    string_techoPlaga = "1";
                }else{
                    string_techoPlaga = "0";
                }
                if (check_lucesCheck1) {
                    string_lucesCheck1 = "1";
                }else{
                    string_lucesCheck1 = "0";
                }
                if (check_luzGabildo) {
                    string_luzGabildo = "1";
                }else{
                    string_luzGabildo = "0";
                }
                if (check_paredDerPlagas) {
                    string_paredDerPlagas = "1";
                }else{
                    string_paredDerPlagas = "0";
                }
                if (check_paredIzqPlagas) {
                    string_paredIzqPlagas = "1";
                }else{
                    string_paredIzqPlagas = "0";
                }
                if (check_patinDer) {
                    string_patinDer = "1";
                }else{
                    string_patinDer = "0";
                }
                if (check_cuartoDer) {
                    string_cuartoDer = "1";
                }else{
                    string_cuartoDer = "0";
                }
                if (check_loderaDer) {
                    string_loderaDer = "1";
                }else{
                    string_loderaDer = "0";
                }
                if (check_lucesCheck2) {
                    string_lucesCheck2 = "1";
                }else{
                    string_lucesCheck2 = "0";
                }
                if (check_luzBarco) {
                    string_luzBarco = "1";
                }else{
                    string_luzBarco = "0";
                }
                if (check_rompevientosDer) {
                    string_rompevientosDer = "1";
                }else{
                    string_rompevientosDer = "0";
                }

                String D1 = null ;
                String D2 = null ;
                String D3 = null ;
                String D4 = null ;

                if(actual_daño1 != null){
                    D1 = "remolqueDerDano1FotoUrl-"+folio;
                }

                if(actual_daño2 != null){
                    D2 = "remolqueDerDano2FotoUrl-"+folio;
                }

                if(actual_daño3 != null){
                    D3 = "remolqueDerDano3FotoUrl-"+folio;
                }

                if(actual_daño4 != null){
                    D4 = "remolqueDerDano4FotoUrl-"+folio;
                }

                String observacioens  = comentarios.getText().toString();

                if(actualDerFrontal == null || actualDerTrasero == null ) {

                    Toast.makeText(etapa7_Activity.this, "Datos Incompletos", Toast.LENGTH_LONG).show();
                } else {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(etapa7_Activity.this);

                    long insertIntercambio1 = dataBaseHelper.insertIntercambioElectronico7(
                            "remolqueCostadoTraseroDerUrl-"+folio,
                            "remolqueCostadoFrenteDerechoUrl-"+folio,
                            D1,
                            D2,
                            D3,
                            D4,
                            "7",folio,observacioens,string_pisoPlaga,string_techoPlaga,string_lucesCheck1,string_luzGabildo
                            ,string_paredDerPlagas,string_paredIzqPlagas,string_patinDer,string_cuartoDer,
                            string_loderaDer,string_lucesCheck2,string_luzBarco,string_rompevientosDer);

                    if(insertIntercambio1 == -1){
                        Toast.makeText(etapa7_Activity.this, "Error insertIntercambio7", Toast.LENGTH_LONG).show();
                    }else {
                        if(createDirectoryAndSaveFile( actualDerTrasero,  "remolqueCostadoTraseroDerUrl-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actualDerFrontal,  "remolqueCostadoFrenteDerechoUrl-"+folio+".jpg", path)
                        )
                        {

                            if(actual_daño1 != null){
                                createDirectoryAndSaveFile(actual_daño1, "remolqueDerDano1FotoUrl" + folio + ".jpg", path);
                            }

                            if(actual_daño2 != null){
                                createDirectoryAndSaveFile(actual_daño2, "remolqueDerDano2FotoUrl" + folio + ".jpg", path);
                            }

                            if(actual_daño3 != null){
                                createDirectoryAndSaveFile(actual_daño3, "remolqueDerDano3FotoUrl" + folio + ".jpg", path);
                            }

                            if(actual_daño4 != null){
                                createDirectoryAndSaveFile(actual_daño4, "remolqueDerDano4FotoUrl" + folio + ".jpg", path);
                            }


                            Intent i = new Intent(etapa7_Activity.this, firmasActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("folio", folio);
                            i.putExtra("path", path);
                            startActivity(i);

                        }else{
                            Toast.makeText(etapa7_Activity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }

    private void imgClick(String photo, int code) {

        File destPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        imageFileName = null;
        imageFile = null;
        photoURI = null;
        imageFileName = photo + "-Folio" + folio;

        try {
            imageFile = File.createTempFile(
                    imageFileName,  // prefix
                    ".jpeg",         // suffix
                    destPath      // directory
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoURI = FileProvider.getUriForFile(getBaseContext(), getBaseContext().getApplicationContext().getPackageName() + ".provider", imageFile);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(camera, code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        switch (requestCode){
            case REQUEST_DER_COSTADO_ATRAS:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_costadoDerAtras.setImageResource(R.drawable.ic_ok);
                        actualDerTrasero = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_costadoDerAtras.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DER_COSTADO_FRENTE:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_costadoDerFrente.setImageResource(R.drawable.ic_ok);
                        actualDerFrontal = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_costadoDerFrente.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;

            case REQUEST_DAÑO1:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        daño1.setImageResource(R.drawable.ic_ok);
                        actual_daño1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        daño1.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO2:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        daño2.setImageResource(R.drawable.ic_ok);
                        actual_daño2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        daño2.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO3:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        daño3.setImageResource(R.drawable.ic_ok);
                        actual_daño3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        daño3.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO4:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        daño4.setImageResource(R.drawable.ic_ok);
                        actual_daño4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        daño4.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, resultData);
    }

    private boolean createDirectoryAndSaveFile(Bitmap imageToSave, String fileName, String path) {

        File file = new File(path, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}