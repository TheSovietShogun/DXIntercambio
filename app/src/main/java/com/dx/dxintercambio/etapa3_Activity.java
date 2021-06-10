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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class etapa3_Activity extends AppCompatActivity {

    private CheckBox inspeccion , patinIzq , lucesP1 , luzGabildo , manitas ,manivela , cuartoIzq , loderaIzq, lucesP2 ,luzABS , luzBarcoIzq , rompevientosIzq;
    private Boolean check_inspeccion = true,
            check_patinIzq = true,
            check_lucesP1 = true,
            check_luzGabildo = true,
            check_manitas = true,
            check_manivela = true,
            check_cuartoIzq = true,
            check_loderaIzq= true,
            check_lucesP2= true,
            check_luzABS = true,
            check_luzBarcoIzq = true,
            check_rompevientosIzq= true ;
    private String path ,string_inspeccion , string_patinIzq , string_lucesP1 , string_luzGabildo , string_manitas ,string_manivela, string_cuartoIzq , string_loderaIzq, string_lucesP2 ,string_luzABS , string_luzBarcoIzq , string_rompevientosIzq;
    private ImageView  IV_NoEconomico , IV_manitas , IV_fotoCostadoFrente ,IV_fotoCostadoAtras;
    private Bitmap  actual_NoEconomico , actual_manitas , actual_fotoCostadoFrente ,actual_fotoCostadoAtras;
    private Button btnEtapa3 ;
    public static final int REQUEST_NO_ECONOMICO = 100;
    public static final int REQUEST_MANITAS = 101;
    public static final int REQUEST_FOTO_COSTADO_FRENTE = 102;
    public static final int REQUEST_FOTO_COSTADO_ATRAS = 103;
    private File imageFile;
    private Uri photoURI;
    private String  imageFileName , folio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa3);

        folio = getIntent().getStringExtra("folio");
        path = getIntent().getStringExtra("path");

        inspeccion = (CheckBox) findViewById(R.id.CB_inspeccionMecanica);
        patinIzq = (CheckBox) findViewById(R.id.CB_patinIzq);
        lucesP1 = (CheckBox) findViewById(R.id.CB_izqLucesP1);
        luzGabildo = (CheckBox) findViewById(R.id.CB_luzGabIzq);
        manitas = (CheckBox) findViewById(R.id.CB_manitasIzq);
        manivela = (CheckBox) findViewById(R.id.CB_manivelaIzq);
        cuartoIzq = (CheckBox) findViewById(R.id.CB_cuartoIzq);
        loderaIzq = (CheckBox) findViewById(R.id.CB_loderaIzq);
        lucesP2 = (CheckBox) findViewById(R.id.CB_izqLucesP2);
        luzABS = (CheckBox) findViewById(R.id.CB_luzABS3);
        luzBarcoIzq = (CheckBox) findViewById(R.id.CB_luzBarcIzq);
        rompevientosIzq = (CheckBox) findViewById(R.id.CB_rompevientesIzq);

        IV_NoEconomico = (ImageView) findViewById(R.id.IV_numeroEco);
        IV_manitas = (ImageView) findViewById(R.id.IV_manitasIzq);
        IV_fotoCostadoFrente = (ImageView) findViewById(R.id.IV_costadoIzqFrente);
        IV_fotoCostadoAtras = (ImageView) findViewById(R.id.IV_costadoIzqAtras);

        btnEtapa3 =  (Button) findViewById(R.id.btn_etapa3);


        inspeccion.setOnClickListener(view -> {

            check_inspeccion = ((CheckBox) view).isChecked();

        });
        patinIzq.setOnClickListener(view -> {

            check_patinIzq = ((CheckBox) view).isChecked();

        });
        lucesP1.setOnClickListener(view -> {

            check_lucesP1 = ((CheckBox) view).isChecked();

        });
        luzGabildo.setOnClickListener(view -> {

            check_luzGabildo = ((CheckBox) view).isChecked();

        });
        manitas.setOnClickListener(view -> {

            check_manitas = ((CheckBox) view).isChecked();

        });
        manivela.setOnClickListener(view -> {

            check_manivela = ((CheckBox) view).isChecked();

        });
        cuartoIzq.setOnClickListener(view -> {

            check_cuartoIzq = ((CheckBox) view).isChecked();

        });
        loderaIzq.setOnClickListener(view -> {

            check_loderaIzq = ((CheckBox) view).isChecked();

        });
        lucesP2.setOnClickListener(view -> {

            check_lucesP2 = ((CheckBox) view).isChecked();

        });
        luzABS.setOnClickListener(view -> {

            check_luzABS = ((CheckBox) view).isChecked();

        });
        luzBarcoIzq.setOnClickListener(view -> {

            check_luzBarcoIzq = ((CheckBox) view).isChecked();

        });
        rompevientosIzq.setOnClickListener(view -> {

            check_rompevientosIzq = ((CheckBox) view).isChecked();

        });

        btnEtapa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check_inspeccion) {
                    string_inspeccion = "1";
                }else{
                    string_inspeccion = "0";
                }
                if (check_patinIzq) {
                    string_patinIzq = "1";
                }else{
                    string_patinIzq = "0";
                }
                if (check_lucesP1) {
                    string_lucesP1 = "1";
                }else{
                    string_lucesP1 = "0";
                }
                if (check_luzGabildo) {
                    string_luzGabildo = "1";
                }else{
                    string_luzGabildo = "0";
                }
                if (check_manitas) {
                    string_manitas = "1";
                }else{
                    string_manitas = "0";
                }
                if (check_manivela) {
                    string_manivela = "1";
                }else{
                    string_manivela = "0";
                }
                if (check_cuartoIzq) {
                    string_cuartoIzq = "1";
                }else{
                    string_cuartoIzq = "0";
                }
                if (check_loderaIzq) {
                    string_loderaIzq = "1";
                }else{
                    string_loderaIzq = "0";
                }
                if (check_lucesP2) {
                    string_lucesP2 = "1";
                }else{
                    string_lucesP2 = "0";
                }
                if (check_luzABS) {
                    string_luzABS = "1";
                }else{
                    string_luzABS = "0";
                }
                if (check_luzBarcoIzq) {
                    string_luzBarcoIzq = "1";
                }else{
                    string_luzBarcoIzq = "0";
                }
                if (check_rompevientosIzq) {
                    string_rompevientosIzq = "1";
                }else{
                    string_rompevientosIzq = "0";
                }


                if(actual_NoEconomico == null || actual_manitas == null || actual_fotoCostadoAtras == null || actual_fotoCostadoFrente == null) {

                    Toast.makeText(etapa3_Activity.this, "Datos Incompletos", Toast.LENGTH_LONG).show();
                } else {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(etapa3_Activity.this);

                    long insertIntercambio1 = dataBaseHelper.insertIntercambioElectronico3(
                            "noEcoUrl-"+folio,"vinUrl-"+folio,"remolqueCostadoTraseroIzqUrl-"+folio,"remolqueCostadoFrenteIzquierdoUrl-"+folio,
                            "3",folio,string_inspeccion,string_lucesP1,string_luzGabildo,string_manitas
                            ,string_manivela,string_patinIzq,string_cuartoIzq,string_loderaIzq,
                            string_lucesP2,string_luzABS,string_luzBarcoIzq,string_rompevientosIzq);

                    if(insertIntercambio1 == -1){
                        Toast.makeText(etapa3_Activity.this, "Error insertIntercambio3", Toast.LENGTH_LONG).show();
                    }else {
                        if(createDirectoryAndSaveFile( actual_NoEconomico,  "noEcoUrl-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_manitas,  "manitas-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_fotoCostadoAtras,  "remolqueCostadoTraseroIzqUrl-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_fotoCostadoFrente,  "remolqueCostadoFrenteIzquierdoUrl-"+folio+".jpg", path)
                        )
                        {
                            Intent i = new Intent(etapa3_Activity.this, etapa4_Activity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("folio", folio);
                            i.putExtra("path", path);
                            startActivity(i);
                        }else{
                            Toast.makeText(etapa3_Activity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        IV_NoEconomico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("NoEconomico", REQUEST_NO_ECONOMICO);

            }
        });

        IV_manitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("ManitasIzq", REQUEST_MANITAS);

            }
        });

        IV_fotoCostadoFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("CostadoFrente", REQUEST_FOTO_COSTADO_FRENTE);

            }
        });

        IV_fotoCostadoAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("CostadoAtras", REQUEST_FOTO_COSTADO_ATRAS);

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
            case REQUEST_NO_ECONOMICO:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_NoEconomico.setImageResource(R.drawable.ic_ok);
                        actual_NoEconomico = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_NoEconomico.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_MANITAS:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_manitas.setImageResource(R.drawable.ic_ok);
                        actual_manitas = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_manitas.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_FOTO_COSTADO_FRENTE:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_fotoCostadoFrente.setImageResource(R.drawable.ic_ok);
                        actual_fotoCostadoFrente = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_fotoCostadoFrente.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_FOTO_COSTADO_ATRAS:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_fotoCostadoAtras.setImageResource(R.drawable.ic_ok);
                        actual_fotoCostadoAtras = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_fotoCostadoAtras.setImageResource(R.drawable.ic_baseline_error_24);
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