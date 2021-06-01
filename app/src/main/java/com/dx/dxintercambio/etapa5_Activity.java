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

public class etapa5_Activity extends AppCompatActivity {

    private CheckBox bisagras , defensaTrasera , luzGabildoTrasera ,plafonesDerechos , plafonesIzquierdos ,  luzPlaca , sello1 , sello2 , sello1Alta , sello2Alta , vvt , escotilla;
    private Boolean check_bisagras , check_defensaTrasera , check_luzGabildoTrasera ,check_plafonesDerechos , check_plafonesIzquierdos , check_luzPlaca,
                    check_sello1 , check_sello2 , check_sello1Alta ,check_sello2Alta , check_vvt , check_escotilla ;
    private String path ,string_bisagras,string_defensaTrasera,string_luzGabildoTrasera,string_plafonesDerechos,string_plafonesIzquierdos ,string_luzPlaca,
            string_sello1 , string_sello2 , string_sello1Alta ,string_sello2Alta , string_vvt , string_escotilla ;

    private ImageView IV_puertas , IV_placas , IV_sello1 ,IV_sello2 , IV_sello3 , daño1,daño2,daño3,daño4;
    private Bitmap actual_puertas , actual_placas , actual_sello1 ,actual_sello2 , actual_sello3,actual_daño1,actual_daño2,actual_daño3,actual_daño4;
    private Button btnEtapa5 ;
    public static final int REQUEST_PUERTAS = 100;
    public static final int REQUEST_PLACAS = 101;
    public static final int REQUEST_SELLO1 = 102;
    public static final int REQUEST_SELLO2 = 103;
    public static final int REQUEST_SELLO3 = 104;
    public static final int REQUEST_DAÑO1 = 105;
    public static final int REQUEST_DAÑO2 = 106;
    public static final int REQUEST_DAÑO3 = 107;
    public static final int REQUEST_DAÑO4 = 108;
    private File imageFile;
    private int widthScreen;
    private Uri photoURI;
    private String  imageFileName , folio;
    private EditText comentarios , numeroPlaca  , numeroSello1, numeroSello2 , numeroSello3 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa5);

        folio = getIntent().getStringExtra("folio");
        path = getIntent().getStringExtra("path");

        bisagras = (CheckBox) findViewById(R.id.CB_bisagras);
        defensaTrasera = (CheckBox) findViewById(R.id.CB_defensaTrasera);
        luzGabildoTrasera = (CheckBox) findViewById(R.id.CB_gabildoTrasera);
        plafonesDerechos = (CheckBox) findViewById(R.id.CB_plafonesDer);
        plafonesIzquierdos = (CheckBox) findViewById(R.id.CB_plafonesIzq);
        luzPlaca = (CheckBox) findViewById(R.id.CB_luzPlaca);
        sello1 = (CheckBox) findViewById(R.id.CB_sello1);
        sello2 = (CheckBox) findViewById(R.id.CB_sello2);
        sello1Alta = (CheckBox) findViewById(R.id.CB_sello1Alta);
        sello2Alta = (CheckBox) findViewById(R.id.CB_sello2Alta);
        vvt = (CheckBox) findViewById(R.id.CB_vvt);
        escotilla = (CheckBox) findViewById(R.id.CB_escotilla);


        IV_puertas = (ImageView) findViewById(R.id.IV_puertas);
        IV_placas = (ImageView) findViewById(R.id.IV_placas);
        IV_sello1 = (ImageView) findViewById(R.id.IV_sello1);
        IV_sello2 = (ImageView) findViewById(R.id.IV_sello2);
        IV_sello3 = (ImageView) findViewById(R.id.IV_selloExtra);
        daño1 = (ImageView) findViewById(R.id.IV_dañoTrasero1);
        daño2 = (ImageView) findViewById(R.id.IV_dañoTrasero2);
        daño1 = (ImageView) findViewById(R.id.IV_dañoTrasero3);
        daño1 = (ImageView) findViewById(R.id.IV_dañoTrasero4);

        comentarios = (EditText) findViewById(R.id.editTextTextMultiLine2);
        numeroPlaca = (EditText) findViewById(R.id.ED_numeroPlaca);
        numeroSello1 = (EditText) findViewById(R.id.editTextTextPersonName3);
        numeroSello2 = (EditText) findViewById(R.id.editTextTextPersonName4);
        numeroSello3 = (EditText) findViewById(R.id.editTextTextPersonName5);


        btnEtapa5 =  (Button) findViewById(R.id.btn_etapa5);

        bisagras.setOnClickListener(view -> {

            check_bisagras = ((CheckBox) view).isChecked();

        });

        defensaTrasera.setOnClickListener(view -> {

            check_defensaTrasera = ((CheckBox) view).isChecked();

        });

        luzGabildoTrasera.setOnClickListener(view -> {

            check_luzGabildoTrasera = ((CheckBox) view).isChecked();

        });

        plafonesDerechos.setOnClickListener(view -> {

            check_plafonesDerechos = ((CheckBox) view).isChecked();

        });

        plafonesIzquierdos.setOnClickListener(view -> {

            check_plafonesIzquierdos = ((CheckBox) view).isChecked();

        });

        luzPlaca.setOnClickListener(view -> {

            check_luzPlaca = ((CheckBox) view).isChecked();

        });

        sello1.setOnClickListener(view -> {

            check_sello1 = ((CheckBox) view).isChecked();

        });

        sello2.setOnClickListener(view -> {

            check_sello2 = ((CheckBox) view).isChecked();

        });

        sello1Alta.setOnClickListener(view -> {

            check_sello1Alta = ((CheckBox) view).isChecked();

        });

        sello2Alta.setOnClickListener(view -> {

            check_sello2Alta = ((CheckBox) view).isChecked();

        });

        vvt.setOnClickListener(view -> {

            check_vvt = ((CheckBox) view).isChecked();

        });

        escotilla.setOnClickListener(view -> {

            check_escotilla = ((CheckBox) view).isChecked();

        });

        IV_puertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    imgClick("chasisIzqFrontal", REQUEST_PUERTAS);

            }
        });

        IV_placas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisIzqFrontal", REQUEST_PLACAS);

            }
        });

        IV_sello1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisIzqFrontal", REQUEST_SELLO1);

            }
        });

        IV_sello2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisIzqFrontal", REQUEST_SELLO2);

            }
        });

        IV_sello3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisIzqFrontal", REQUEST_SELLO3);

            }
        });

        daño1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("traseroDaño1", REQUEST_DAÑO1);

            }
        });

        daño2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("traseroDaño2", REQUEST_DAÑO2);

            }
        });

        daño3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("traseroDaño3", REQUEST_DAÑO3);

            }
        });

        daño4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("traseroDaño4", REQUEST_DAÑO4);

            }
        });


        btnEtapa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check_bisagras) {
                    string_bisagras = "0";
                }else{
                    string_bisagras = "1";
                }
                if (check_defensaTrasera) {
                    string_defensaTrasera = "0";
                }else{
                    string_defensaTrasera = "1";
                }
                if (check_luzGabildoTrasera) {
                    string_luzGabildoTrasera = "0";
                }else{
                    string_luzGabildoTrasera = "1";
                }
                if (check_plafonesDerechos) {
                    string_plafonesDerechos = "0";
                }else{
                    string_plafonesDerechos = "1";
                }
                if (check_plafonesIzquierdos) {
                    string_plafonesIzquierdos = "0";
                }else{
                    string_plafonesIzquierdos = "1";
                }
                if (check_luzPlaca) {
                    string_luzPlaca = "0";
                }else{
                    string_luzPlaca = "1";
                }
                if (check_sello1) {
                    string_sello1 = "0";
                }else{
                    string_sello1 = "1";
                }
                if (check_sello2) {
                    string_sello2 = "0";
                }else{
                    string_sello2 = "1";
                }
                if (check_sello1Alta) {
                    string_sello1Alta = "0";
                }else{
                    string_sello1Alta = "1";
                }
                if (check_sello2Alta) {
                    string_sello2Alta = "0";
                }else{
                    string_sello2Alta = "1";
                }
                if (check_vvt) {
                    string_vvt = "0";
                }else{
                    string_vvt = "1";
                }
                if (check_escotilla) {
                    string_escotilla = "0";
                }else{
                    string_escotilla = "1";
                }

                String placa = numeroPlaca.getText().toString();
                String sello1 = numeroSello1.getText().toString();
                String sello2 = numeroSello2.getText().toString();
                String sello3 = numeroSello3.getText().toString();

                if(actual_puertas == null || actual_placas == null || actual_sello1 == null || actual_sello2 == null ||
                    placa.isEmpty() || sello1.isEmpty() || sello2.isEmpty()) {

                    Toast.makeText(etapa5_Activity.this, "Datos Incompletos", Toast.LENGTH_LONG).show();
                } else {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(etapa5_Activity.this);

                    long insertIntercambio1 = dataBaseHelper.insertIntercambioElectronico5(
                            "5",folio,placa,sello1,sello2,sello3
                            ,string_bisagras,string_defensaTrasera,string_luzGabildoTrasera,string_plafonesDerechos,
                            string_plafonesIzquierdos,string_luzPlaca,"",string_sello1,string_sello1Alta,
                    string_escotilla,string_sello2,string_sello2Alta,string_vvt);

                    if(insertIntercambio1 == -1){
                        Toast.makeText(etapa5_Activity.this, "Error insertIntercambio3", Toast.LENGTH_LONG).show();
                    }else {
                        if(createDirectoryAndSaveFile( actual_puertas,  "puertas"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_placas,  "placas"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_sello1,  "sello1"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_sello2,  "sello2"+folio+".jpg", path)
                        )
                        {

                            if(actual_daño1 != null){
                                createDirectoryAndSaveFile(actual_daño1, "dañoIzq1" + folio + ".jpg", path);
                            }

                            if(actual_daño2 != null){
                                createDirectoryAndSaveFile(actual_daño2, "dañoIzq2" + folio + ".jpg", path);
                            }

                            if(actual_daño3 != null){
                                createDirectoryAndSaveFile(actual_daño3, "dañoIzq3" + folio + ".jpg", path);
                            }

                            if(actual_daño4 != null){
                                createDirectoryAndSaveFile(actual_daño4, "dañoIzq4" + folio + ".jpg", path);
                            }

                            if(actual_sello3 != null){
                                createDirectoryAndSaveFile(actual_sello3, "sello3" + folio + ".jpg", path);
                            }

                            Intent i = new Intent(etapa5_Activity.this, etapa3_Activity.class);
                            i.putExtra("folio", folio);
                            i.putExtra("path", path);
                            startActivity(i);

                        }else{
                            Toast.makeText(etapa5_Activity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
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
            case REQUEST_PUERTAS:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {
                        Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                        IV_puertas.setImageBitmap(photo);
                        actual_puertas = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_puertas.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_PLACAS:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {
                        Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                        IV_placas.setImageBitmap(photo);
                        actual_placas = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_placas.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_SELLO1:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {
                        Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                        IV_sello1.setImageBitmap(photo);
                        actual_sello1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_sello1.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_SELLO2:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {
                        Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                        IV_sello2.setImageBitmap(photo);
                        actual_sello2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_sello2.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_SELLO3:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {
                        Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                        IV_sello3.setImageBitmap(photo);
                        actual_sello3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        IV_sello3.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO1:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {
                        Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                        daño1.setImageBitmap(photo);
                        actual_daño1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        daño1.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO2:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {
                        Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                        daño2.setImageBitmap(photo);
                        actual_daño2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        daño2.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO3:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {
                        Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                        daño3.setImageBitmap(photo);
                        actual_daño3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    } catch (IOException e) {
                        daño3.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO4:
                if (resultCode == RESULT_OK && resultData != null) {
                    try {
                        Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                        daño4.setImageBitmap(photo);
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