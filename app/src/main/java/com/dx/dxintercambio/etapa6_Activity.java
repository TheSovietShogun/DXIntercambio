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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class etapa6_Activity extends AppCompatActivity {

    private CheckBox amortiguadorFrontal , bolsaFrontal , matracaFrontal , muelleFrontal , rotachamberFrontal
            ,amortiguadorTrasera , bolsaTrasera , matracaTrasera , muelleTrasera , rotachamberTrasera,
            eje1Birlos , llantaP7 , llantaP8 , eje1MasasYoyo, eje1Rin , eje1Lodera,
            eje2Birlos , llantaP3 , llantaP4 , eje2MasasYoyo, eje2Rin , eje2Lodera , jumbo;
    private Boolean check_amortiguadorFrontal , check_bolsaFrontal , check_matracaFrontal , check_muelleFrontal , check_rotachamberFrontal
            ,check_amortiguadorTrasera , check_bolsaTrasera , check_matracaTrasera , check_muelleTrasera , check_rotachamberTrasera,
            check_eje1Birlos , check_llantaP7 , check_llantaP8 , check_eje1MasasYoyo, check_eje1Rin , check_eje1Lodera,
            check_eje2Birlos , check_llantaP3 , check_llantaP4 , check_eje2MasasYoyo, check_eje2Rin , check_eje2Lodera , check_jumbo;
    private String path ,string_amortiguadorFrontal , string_bolsaFrontal , string_matracaFrontal , string_muelleFrontal , string_rotachamberFrontal
            ,string_amortiguadorTrasera , string_bolsaTrasera , string_matracaTrasera , string_muelleTrasera , string_rotachamberTrasera,
            string_eje1Birlos , string_llantaP7 , string_llantaP8 , string_eje1MasasYoyo, string_eje1Rin , string_eje1Lodera,
            string_eje2Birlos , string_llantaP3 , string_llantaP4 , string_eje2MasasYoyo, string_eje2Rin , string_eje2Lodera , string_jumbo , string_comentarios;
    private ImageView IV_chasisDerFrontal , IV_llantaEje1 , IV_llantaEje2 ,IV_chasisDerTrasero ;
    private Bitmap actual_chasisDerFrontal , actual_llantaEje1 , actual_llantaEje2 ,actual_chasisDerTrasero ;
    private Button btnEtapa6 ;
    public static final int REQUEST_CHASIS_DER_FRONTAL = 100;
    public static final int REQUEST_LLANTAEJE1 = 101;
    public static final int REQUEST_LLANTAEJE2 = 102;
    public static final int REQUEST_CHASIS_DER_TRASERO = 103;
    private File imageFile;
    private int widthScreen;
    private Uri photoURI;
    private String  imageFileName , folio;
    private Spinner llantaP3Marca , llantaP3Estatus, llantaP4Marca , llantaP4Estatus, llantaP7Marca , llantaP7Estatus,llantaP8Marca , llantaP8Estatus;
    private String [] estatusLlanta = new String[]{"Sin Seleccionar","Recapeada","Original"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa6);

        folio = getIntent().getStringExtra("folio");
        path = getIntent().getStringExtra("path");

        amortiguadorTrasera = (CheckBox) findViewById(R.id.CB_amortigadorDerTrasero);
        bolsaTrasera = (CheckBox) findViewById(R.id.CB_bolsaDerTrasero);
        matracaTrasera = (CheckBox) findViewById(R.id.CB_matracaDerTrasero);
        muelleTrasera = (CheckBox) findViewById(R.id.CB_muelleDerTrasero);
        rotachamberTrasera = (CheckBox) findViewById(R.id.CB_rotachamberDerTrasero);

        amortiguadorFrontal = (CheckBox) findViewById(R.id.CB_amortigadorDerFrontal);
        bolsaFrontal = (CheckBox) findViewById(R.id.CB_bolsaDerFrontal);
        matracaFrontal = (CheckBox) findViewById(R.id.CB_matracaDerFrontal);
        muelleFrontal = (CheckBox) findViewById(R.id.CB_muelleDerFrontal);
        rotachamberFrontal = (CheckBox) findViewById(R.id.CB_rotachamberDerFrontal);

        eje2Birlos = (CheckBox) findViewById(R.id.CB_birlosEje2Der);
        llantaP7 = (CheckBox) findViewById(R.id.CB_llantaP7);
        llantaP8 = (CheckBox) findViewById(R.id.CB_llantaP8);
        eje2MasasYoyo = (CheckBox) findViewById(R.id.CB_masaEje2Der);
        eje2Rin = (CheckBox) findViewById(R.id.CB_rinesEje2Der);
        eje2Lodera = (CheckBox) findViewById(R.id.CB_loderaEje2Der);

        eje1Birlos = (CheckBox) findViewById(R.id.CB_birlosEje1Der);
        llantaP3 = (CheckBox) findViewById(R.id.CB_llantaP3);
        llantaP4 = (CheckBox) findViewById(R.id.CB_llantaP4);
        eje1MasasYoyo = (CheckBox) findViewById(R.id.CB_masaEje1Der);
        eje1Rin = (CheckBox) findViewById(R.id.CB_rinesEje1Der);
        eje1Lodera = (CheckBox) findViewById(R.id.CB_loderaEje1Der);

        jumbo = (CheckBox) findViewById(R.id.CB_jumboIzq);


        IV_chasisDerFrontal = (ImageView) findViewById(R.id.IV_chasisDerFrontal);
        IV_llantaEje1 = (ImageView) findViewById(R.id.IV_llantaDerEje1);
        IV_llantaEje2 = (ImageView) findViewById(R.id.IV_llantaDerEje2);
        IV_chasisDerTrasero = (ImageView) findViewById(R.id.IV_chasisDerTrasero);


        llantaP3Marca = (Spinner) findViewById(R.id.s_marcaP3);
        llantaP4Marca = (Spinner) findViewById(R.id.s_marcaP4);
        llantaP7Marca = (Spinner) findViewById(R.id.S_marcaP7);
        llantaP8Marca = (Spinner) findViewById(R.id.S_marcaP8);

        llantaP3Estatus = (Spinner) findViewById(R.id.s_estatusP3);
        llantaP4Estatus = (Spinner) findViewById(R.id.s_estatusP4);
        llantaP7Estatus = (Spinner) findViewById(R.id.S_estatusP7);
        llantaP8Estatus = (Spinner) findViewById(R.id.S_estatusP8);

        btnEtapa6 =  (Button) findViewById(R.id.btn_etapa6);

        widthScreen = getApplicationContext().getResources().getDisplayMetrics().widthPixels;

        DataBaseHelper dataBaseHelper = new DataBaseHelper(etapa6_Activity.this);

        List<CLlanta> llantaSelect = dataBaseHelper.selectLlanta();

        if (widthScreen > 480) {
            ArrayAdapter<CLlanta> adapter1 = new ArrayAdapter<CLlanta>(etapa6_Activity.this, R.layout.mspinner_item, llantaSelect);
            llantaP3Marca.setAdapter(adapter1);
            llantaP4Marca.setAdapter(adapter1);
            llantaP7Marca.setAdapter(adapter1);
            llantaP8Marca.setAdapter(adapter1);

            ArrayAdapter<String> adapter0 = new ArrayAdapter<>(this, R.layout.mspinner_item, estatusLlanta);
            llantaP3Estatus.setAdapter(adapter0);
            llantaP4Estatus.setAdapter(adapter0);
            llantaP7Estatus.setAdapter(adapter0);
            llantaP8Estatus.setAdapter(adapter0);
        } else {
            ArrayAdapter<CLlanta> adapter1 = new ArrayAdapter<CLlanta>(etapa6_Activity.this, R.layout.mspinner_item2, llantaSelect);
            llantaP3Marca.setAdapter(adapter1);
            llantaP4Marca.setAdapter(adapter1);
            llantaP7Marca.setAdapter(adapter1);
            llantaP8Marca.setAdapter(adapter1);

            ArrayAdapter<String> adapter0 = new ArrayAdapter<>(this, R.layout.mspinner_item2, estatusLlanta);
            llantaP3Estatus.setAdapter(adapter0);
            llantaP4Estatus.setAdapter(adapter0);
            llantaP7Estatus.setAdapter(adapter0);
            llantaP8Estatus.setAdapter(adapter0);
        }

        amortiguadorFrontal.setOnClickListener(view -> {

            check_amortiguadorFrontal = ((CheckBox) view).isChecked();

        });
        bolsaFrontal.setOnClickListener(view -> {

            check_bolsaFrontal = ((CheckBox) view).isChecked();

        });
        matracaFrontal.setOnClickListener(view -> {

            check_matracaFrontal = ((CheckBox) view).isChecked();

        });
        muelleFrontal.setOnClickListener(view -> {

            check_muelleFrontal = ((CheckBox) view).isChecked();

        });
        rotachamberFrontal.setOnClickListener(view -> {

            check_rotachamberFrontal = ((CheckBox) view).isChecked();

        });
        amortiguadorTrasera.setOnClickListener(view -> {

            check_amortiguadorTrasera = ((CheckBox) view).isChecked();

        });
        bolsaTrasera.setOnClickListener(view -> {

            check_bolsaTrasera = ((CheckBox) view).isChecked();

        });
        matracaTrasera.setOnClickListener(view -> {

            check_matracaTrasera = ((CheckBox) view).isChecked();

        });
        muelleTrasera.setOnClickListener(view -> {

            check_muelleTrasera = ((CheckBox) view).isChecked();

        });
        rotachamberTrasera.setOnClickListener(view -> {

            check_rotachamberTrasera = ((CheckBox) view).isChecked();

        });
        eje1Birlos.setOnClickListener(view -> {

            check_eje1Birlos = ((CheckBox) view).isChecked();

        });
        llantaP3.setOnClickListener(view -> {

            check_llantaP3 = ((CheckBox) view).isChecked();

        });
        llantaP4.setOnClickListener(view -> {

            check_llantaP4 = ((CheckBox) view).isChecked();

        });
        eje1MasasYoyo.setOnClickListener(view -> {

            check_eje1MasasYoyo = ((CheckBox) view).isChecked();

        });
        eje1Rin.setOnClickListener(view -> {

            check_eje1Rin = ((CheckBox) view).isChecked();

        });
        eje1Lodera.setOnClickListener(view -> {

            check_eje1Lodera = ((CheckBox) view).isChecked();

        });
        eje2Birlos.setOnClickListener(view -> {

            check_eje2Birlos = ((CheckBox) view).isChecked();

        });
        llantaP7.setOnClickListener(view -> {

            check_llantaP7 = ((CheckBox) view).isChecked();

        });
        llantaP8.setOnClickListener(view -> {

            check_llantaP8 = ((CheckBox) view).isChecked();

        });
        eje2MasasYoyo.setOnClickListener(view -> {

            check_eje2MasasYoyo= ((CheckBox) view).isChecked();

        });
        eje2Rin.setOnClickListener(view -> {

            check_eje2Rin = ((CheckBox) view).isChecked();

        });
        eje2Lodera.setOnClickListener(view -> {

            check_eje2Lodera = ((CheckBox) view).isChecked();

        });
        jumbo.setOnClickListener(view -> {

            check_jumbo = ((CheckBox) view).isChecked();

            if(check_jumbo){

                llantaP3Marca.setVisibility(View.INVISIBLE);
                llantaP4Marca.setVisibility(View.INVISIBLE);
                llantaP7Estatus.setVisibility(View.INVISIBLE);
                llantaP8Estatus.setVisibility(View.INVISIBLE);

                string_jumbo = "1";
            }else{
                llantaP3Marca.setVisibility(View.VISIBLE);
                llantaP4Marca.setVisibility(View.VISIBLE);
                llantaP7Estatus.setVisibility(View.VISIBLE);
                llantaP8Estatus.setVisibility(View.VISIBLE);

                string_jumbo = "0";
            }

        });


        IV_chasisDerFrontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisDerFrontal", REQUEST_CHASIS_DER_FRONTAL);

            }
        });

        IV_llantaEje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("llantaDerEje1", REQUEST_LLANTAEJE1);

            }
        });
        IV_llantaEje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("llantaDerEje2", REQUEST_LLANTAEJE2);

            }
        });

        IV_chasisDerTrasero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisDerTrasero", REQUEST_CHASIS_DER_TRASERO);

            }
        });

        btnEtapa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CLlanta cLlanta = (CLlanta) llantaP3Marca.getSelectedItem();
                String idLlantaP1 = cLlanta.getId();

                CLlanta cLlanta1 = (CLlanta) llantaP4Marca.getSelectedItem();
                String idLlantaP2 = cLlanta1.getId();

                CLlanta cLlanta2 = (CLlanta) llantaP7Marca.getSelectedItem();
                String idLlantaP5 = cLlanta2.getId();

                CLlanta cLlanta3 = (CLlanta) llantaP8Marca.getSelectedItem();
                String idLlantaP6 = cLlanta3.getId();


                String llantaEstatusP1 = llantaP3Marca.getSelectedItem().toString();
                String llantaEstatusP2 = llantaP4Marca.getSelectedItem().toString();
                String llantaEstatusP5 = llantaP7Marca.getSelectedItem().toString();
                String llantaEstatusP6 = llantaP8Marca.getSelectedItem().toString();


                if (check_amortiguadorFrontal) {
                    string_amortiguadorFrontal = "0";
                }else{
                    string_amortiguadorFrontal = "1";
                }
                if (check_bolsaFrontal) {
                    string_bolsaFrontal = "0";
                }else{
                    string_bolsaFrontal = "1";
                }
                if (check_matracaFrontal) {
                    string_matracaFrontal = "0";
                }else{
                    string_matracaFrontal = "1";
                }
                if (check_muelleFrontal) {
                    string_muelleFrontal = "0";
                }else{
                    string_muelleFrontal = "1";
                }
                if (check_rotachamberFrontal) {
                    string_rotachamberFrontal = "0";
                }else{
                    string_rotachamberFrontal = "1";
                }
                if (check_amortiguadorTrasera) {
                    string_amortiguadorTrasera = "0";
                }else{
                    string_amortiguadorTrasera = "1";
                }
                if (check_bolsaTrasera) {
                    string_bolsaTrasera = "0";
                }else{
                    string_bolsaTrasera = "1";
                }
                if (check_matracaTrasera) {
                    string_matracaTrasera = "0";
                }else{
                    string_matracaTrasera = "1";
                }
                if (check_muelleTrasera) {
                    string_muelleTrasera = "0";
                }else{
                    string_muelleTrasera = "1";
                }
                if (check_rotachamberTrasera) {
                    string_rotachamberTrasera = "0";
                }else{
                    string_rotachamberTrasera = "1";
                }
                if (check_eje1Birlos) {
                    string_eje1Birlos = "0";
                }else{
                    string_eje1Birlos = "1";
                }
                if (check_llantaP3) {
                    string_llantaP3 = "0";
                }else{
                    string_llantaP3 = "1";
                }
                if (check_llantaP4) {
                    string_llantaP4 = "0";
                }else{
                    string_llantaP4 = "1";
                }
                if (check_eje1MasasYoyo) {
                    string_eje1MasasYoyo = "0";
                }else{
                    string_eje1MasasYoyo = "1";
                }
                if (check_eje1Rin) {
                    string_eje1Rin = "0";
                }else{
                    string_eje1Rin = "1";
                }
                if (check_eje1Lodera) {
                    string_eje1Lodera = "0";
                }else{
                    string_eje1Lodera = "1";
                }
                if (check_eje2Birlos) {
                    string_eje2Birlos = "0";
                }else{
                    string_eje2Birlos = "1";
                }
                if (check_llantaP7) {
                    string_llantaP7 = "0";
                }else{
                    string_llantaP7 = "1";
                }
                if (check_llantaP8) {
                    string_llantaP8 = "0";
                }else{
                    string_llantaP8 = "1";
                }
                if (check_eje2MasasYoyo) {
                    string_eje2MasasYoyo = "0";
                }else{
                    string_eje2MasasYoyo = "1";
                }
                if (check_eje2Rin) {
                    string_eje2Rin = "0";
                }else{
                    string_eje2Rin = "1";
                }
                if (check_eje2Lodera) {
                    string_eje2Lodera = "0";
                }else{
                    string_eje2Lodera = "1";
                }

                if(string_jumbo.contains("1")){

                    if(actual_chasisDerFrontal == null || actual_chasisDerTrasero == null ||
                            idLlantaP1.isEmpty() || idLlantaP5.isEmpty() || llantaEstatusP1.isEmpty() || llantaEstatusP5.isEmpty()) {
                        Toast.makeText(etapa6_Activity.this, "Datos Incompletos", Toast.LENGTH_LONG).show();
                    } else {

                        DataBaseHelper dataBaseHelper2 = new DataBaseHelper(etapa6_Activity.this);

                        long insertIntercambio1 = dataBaseHelper2.insertIntercambioElectronico6(
                                "6",
                                folio,
                                string_amortiguadorTrasera,
                                string_bolsaTrasera,
                                string_matracaTrasera,
                                string_muelleTrasera,
                                string_rotachamberTrasera,

                                string_eje2Birlos,

                                "idLlantaP7",
                                "llantaEstatusP7",
                                "idLlantaP8",
                                "llantaEstatusP8",

                                string_llantaP7,
                                string_llantaP8,

                                "",
                                "",
                                "",
                                "",

                                "idLlantaP3",
                                "llantaEstatusP3",
                                "idLlantaP4",
                                "llantaEstatusP4",

                                string_llantaP3,
                                string_llantaP4,

                                "",
                                "",
                                "",

                                string_amortiguadorFrontal,
                                string_bolsaFrontal,
                                string_matracaFrontal,
                                string_muelleFrontal,
                                string_rotachamberFrontal);

                        if(insertIntercambio1 == -1){
                            Toast.makeText(etapa6_Activity.this, "Error insertIntercambio6", Toast.LENGTH_LONG).show();
                        }else {
                            if(createDirectoryAndSaveFile( actual_chasisDerFrontal,  "chasisIzqFrontal"+folio+".jpg", path) &&
                                    createDirectoryAndSaveFile( actual_chasisDerTrasero,  "chasisIzqTrasero"+folio+".jpg", path)
                            )
                            {
                                Intent i = new Intent(etapa6_Activity.this, etapa7_Activity.class);
                                i.putExtra("folio", folio);
                                i.putExtra("path", path);
                                startActivity(i);
                            }else{
                                Toast.makeText(etapa6_Activity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }else {

                    if (actual_chasisDerFrontal == null || actual_chasisDerTrasero == null
                            || idLlantaP1.isEmpty() || idLlantaP2.isEmpty() || idLlantaP5.isEmpty() || idLlantaP6.isEmpty()
                            || llantaEstatusP1.isEmpty() || llantaEstatusP2.isEmpty() || llantaEstatusP5.isEmpty() || llantaEstatusP6.isEmpty() ) {
                        Toast.makeText(etapa6_Activity.this, "Datos Incompletos", Toast.LENGTH_LONG).show();
                    } else {

                        DataBaseHelper dataBaseHelper2 = new DataBaseHelper(etapa6_Activity.this);

                        long insertIntercambio1 = dataBaseHelper2.insertIntercambioElectronico4(
                                "4", folio, string_jumbo,
                                idLlantaP1,
                                llantaEstatusP1,
                                idLlantaP2,
                                llantaEstatusP2,

                                string_eje1Birlos,
                                string_llantaP3,
                                string_llantaP4,
                                string_eje1MasasYoyo,
                                string_eje1Rin,
                                string_eje1Lodera,

                                idLlantaP5,
                                llantaEstatusP5,
                                idLlantaP6,
                                llantaEstatusP6,

                                string_eje2Birlos,
                                string_llantaP7,
                                string_llantaP8,
                                string_eje2MasasYoyo,
                                string_eje2Rin,
                                string_eje2Lodera,

                                string_amortiguadorFrontal,
                                string_bolsaFrontal,
                                string_matracaFrontal,
                                string_muelleFrontal,
                                string_rotachamberFrontal,

                                string_amortiguadorTrasera,
                                string_bolsaTrasera,
                                string_matracaTrasera,
                                string_muelleTrasera,
                                string_rotachamberTrasera,

                                string_comentarios);

                        if (insertIntercambio1 == -1) {
                            Toast.makeText(etapa6_Activity.this, "Error insertIntercambio6", Toast.LENGTH_LONG).show();
                        } else {
                            if (createDirectoryAndSaveFile(actual_chasisDerFrontal, "chasisIzqFrontal" + folio + ".jpg", path) &&
                                    createDirectoryAndSaveFile(actual_chasisDerTrasero, "chasisIzqTrasero" + folio + ".jpg", path)
                            ) {


                                Intent i = new Intent(etapa6_Activity.this, etapa7_Activity.class);
                                i.putExtra("folio", folio);
                                i.putExtra("path", path);
                                startActivity(i);

                            } else {
                                Toast.makeText(etapa6_Activity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
                            }
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
            case REQUEST_CHASIS_DER_FRONTAL:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_chasisDerFrontal.setImageResource(R.drawable.ic_ok);
                        actual_chasisDerFrontal = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_chasisDerFrontal.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_LLANTAEJE1:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_llantaEje1.setImageResource(R.drawable.ic_ok);
                        actual_llantaEje1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_llantaEje1.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_LLANTAEJE2:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_llantaEje2.setImageResource(R.drawable.ic_ok);
                        actual_llantaEje2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_llantaEje2.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_CHASIS_DER_TRASERO:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {

                        IV_chasisDerTrasero.setImageResource(R.drawable.ic_ok);
                        actual_chasisDerTrasero = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_chasisDerTrasero.setImageResource(R.drawable.ic_baseline_error_24);
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