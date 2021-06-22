package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
    private Boolean check_bisagras = true, check_defensaTrasera = true, check_luzGabildoTrasera= true ,check_plafonesDerechos= true , check_plafonesIzquierdos = true, check_luzPlaca= true,
                    check_sello1 = true, check_sello2 = true, check_sello1Alta = true,check_sello2Alta = true, check_vvt = true, check_escotilla = true;
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
    private final int THUMBSIZE = 128;
    private String  imageFileName , folio;
    private EditText comentarios , numeroPlaca  , numeroSello1, numeroSello2 , numeroSello3 ;
    private String usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa5);

        folio = getIntent().getStringExtra("folio");
        path = getIntent().getStringExtra("path");
        usuario = getIntent().getStringExtra("idUsuario");


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
        daño3 = (ImageView) findViewById(R.id.IV_dañoTrasero3);
        daño4 = (ImageView) findViewById(R.id.IV_dañoTrasero4);

        comentarios = (EditText) findViewById(R.id.editTextTextMultiLine2);
        numeroPlaca = (EditText) findViewById(R.id.ED_numeroPlaca);
        numeroSello1 = (EditText) findViewById(R.id.editTextTextPersonName3);
        numeroSello2 = (EditText) findViewById(R.id.editTextTextPersonName4);
        numeroSello3 = (EditText) findViewById(R.id.editTextTextPersonName5);

        comentarios = (EditText) findViewById(R.id.editTextTextMultiLine2);

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
                IV_puertas.setEnabled(false);
                IV_puertas.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IV_puertas.setEnabled(true);
                        IV_puertas.setClickable(true);

                    }
                }, TIME);
                    imgClick("chasisIzqFrontal", REQUEST_PUERTAS);

            }
        });

        IV_placas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IV_placas.setEnabled(false);
                IV_placas.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IV_placas.setEnabled(true);
                        IV_placas.setClickable(true);

                    }
                }, TIME);
                imgClick("chasisIzqFrontal", REQUEST_PLACAS);

            }
        });

        IV_sello1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IV_sello1.setEnabled(false);
                IV_sello1.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IV_sello1.setEnabled(true);
                        IV_sello1.setClickable(true);

                    }
                }, TIME);
                imgClick("chasisIzqFrontal", REQUEST_SELLO1);

            }
        });

        IV_sello2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IV_sello2.setEnabled(false);
                IV_sello2.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IV_sello2.setEnabled(true);
                        IV_sello2.setClickable(true);

                    }
                }, TIME);
                imgClick("chasisIzqFrontal", REQUEST_SELLO2);

            }
        });

        IV_sello3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IV_sello3.setEnabled(false);
                IV_sello3.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        IV_sello3.setEnabled(true);
                        IV_sello3.setClickable(true);

                    }
                }, TIME);
                imgClick("chasisIzqFrontal", REQUEST_SELLO3);

            }
        });

        daño1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daño1.setEnabled(false);
                daño1.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        daño1.setEnabled(true);
                        daño1.setClickable(true);

                    }
                }, TIME);
                imgClick("traseroDaño1", REQUEST_DAÑO1);

            }
        });

        daño2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daño2.setEnabled(false);
                daño2.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        daño2.setEnabled(true);
                        daño2.setClickable(true);

                    }
                }, TIME);
                imgClick("traseroDaño2", REQUEST_DAÑO2);

            }
        });

        daño3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daño3.setEnabled(false);
                daño3.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        daño3.setEnabled(true);
                        daño3.setClickable(true);

                    }
                }, TIME);
                imgClick("traseroDaño3", REQUEST_DAÑO3);

            }
        });

        daño4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daño4.setEnabled(false);
                daño4.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        daño4.setEnabled(true);
                        daño4.setClickable(true);

                    }
                }, TIME);
                imgClick("traseroDaño4", REQUEST_DAÑO4);

            }
        });


        btnEtapa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnEtapa5.setEnabled(false);
                btnEtapa5.setClickable(false);
                btnEtapa5.setText("Enviando...");
                btnEtapa5.setTextColor(Color.parseColor("#074EAB"));
                btnEtapa5.setBackgroundResource(R.drawable.round_btn2);

                int TIME = 10000;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        btnEtapa5.setEnabled(true);
                        btnEtapa5.setClickable(true);
                        btnEtapa5.setText("Siguiente");
                        btnEtapa5.setTextColor(Color.parseColor("#FFFFFF"));
                        btnEtapa5.setBackgroundResource(R.drawable.round_btn);

                    }
                }, TIME);


                if (check_bisagras) {
                    string_bisagras = "1";
                }else{
                    string_bisagras = "0";
                }
                if (check_defensaTrasera) {
                    string_defensaTrasera = "1";
                }else{
                    string_defensaTrasera = "0";
                }
                if (check_luzGabildoTrasera) {
                    string_luzGabildoTrasera = "1";
                }else{
                    string_luzGabildoTrasera = "0";
                }
                if (check_plafonesDerechos) {
                    string_plafonesDerechos = "1";
                }else{
                    string_plafonesDerechos = "0";
                }
                if (check_plafonesIzquierdos) {
                    string_plafonesIzquierdos = "1";
                }else{
                    string_plafonesIzquierdos = "0";
                }
                if (check_luzPlaca) {
                    string_luzPlaca = "1";
                }else{
                    string_luzPlaca = "0";
                }
                if (check_sello1) {
                    string_sello1 = "1";
                }else{
                    string_sello1 = "0";
                }
                if (check_sello2) {
                    string_sello2 = "1";
                }else{
                    string_sello2 = "0";
                }
                if (check_sello1Alta) {
                    string_sello1Alta = "1";
                }else{
                    string_sello1Alta = "0";
                }
                if (check_sello2Alta) {
                    string_sello2Alta = "1";
                }else{
                    string_sello2Alta = "0";
                }
                if (check_vvt) {
                    string_vvt = "1";
                }else{
                    string_vvt = "0";
                }
                if (check_escotilla) {
                    string_escotilla = "1";
                }else{
                    string_escotilla = "0";
                }

                String observacioens  = comentarios.getText().toString();

                String placa = numeroPlaca.getText().toString();
                String sello1 = numeroSello1.getText().toString();
                String sello2 = numeroSello2.getText().toString();
                String sello3 = numeroSello3.getText().toString();

                String D1 = null ;
                String D2 = null ;
                String D3 = null ;
                String D4 = null ;
                String S3 = null ;

                if(actual_daño1 != null){
                    D1 = "remolqueTraseroDano1FotoUrl-"+folio;
                }

                if(actual_daño2 != null){
                    D2 = "remolqueTraseroDano2FotoUrl-"+folio;
                }

                if(actual_daño3 != null){
                    D3 = "remolqueTraseroDano3FotoUrl-"+folio;
                }

                if(actual_daño4 != null){
                    D4 = "remolqueTraseroDano4FotoUrl-"+folio;
                }

                if(actual_sello3 != null){
                    S3 = "remolqueSello3FotoUrl-"+folio;
                }

                if(actual_puertas == null || actual_placas == null || actual_sello1 == null || actual_sello2 == null ||
                    placa.isEmpty() || sello1.isEmpty() || sello2.isEmpty()) {

                    Toast.makeText(etapa5_Activity.this, "Datos Incompletos", Toast.LENGTH_LONG).show();
                } else {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(etapa5_Activity.this);

                    long insertIntercambio1 = dataBaseHelper.insertIntercambioElectronico5(
                            "remolquePuertasFotoUrl-"+folio,
                            "remolquePlacasFotoUrl-"+folio,
                            "remolqueSello1FotoUrl-"+folio,
                            "remolqueSello2FotoUrl-"+folio,
                            S3,
                            D1,
                            D2,
                            D3,
                            D4,
                            "6",folio,observacioens,placa,sello1,sello2,sello3
                            ,string_bisagras,string_defensaTrasera,string_luzGabildoTrasera,string_plafonesDerechos,
                            string_plafonesIzquierdos,string_luzPlaca,"",string_sello1,string_sello1Alta,
                    string_escotilla,string_sello2,string_sello2Alta,string_vvt);

                    if(insertIntercambio1 == -1){
                        Toast.makeText(etapa5_Activity.this, "Error insertIntercambio5", Toast.LENGTH_LONG).show();
                    }else {
                        if(createDirectoryAndSaveFile( actual_puertas,  "remolquePuertasFotoUrl-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_placas,  "remolquePlacasFotoUrl-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_sello1,  "remolqueSello1FotoUrl-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_sello2,  "remolqueSello2FotoUrl-"+folio+".jpg", path)
                        )
                        {

                            if(actual_daño1 != null){
                                createDirectoryAndSaveFile(actual_daño1, "remolqueTraseroDano1FotoUrl-" + folio + ".jpg", path);
                            }

                            if(actual_daño2 != null){
                                createDirectoryAndSaveFile(actual_daño2, "remolqueTraseroDano2FotoUrl-" + folio + ".jpg", path);
                            }

                            if(actual_daño3 != null){
                                createDirectoryAndSaveFile(actual_daño3, "remolqueTraseroDano3FotoUrl-" + folio + ".jpg", path);
                            }

                            if(actual_daño4 != null){
                                createDirectoryAndSaveFile(actual_daño4, "remolqueTraseroDano4FotoUrl-" + folio + ".jpg", path);
                            }

                            if(actual_sello3 != null){
                                createDirectoryAndSaveFile(actual_sello3, "remolqueSello3FotoUrl-" + folio + ".jpg", path);
                            }

                            Intent i = new Intent(etapa5_Activity.this, etapa6_Activity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("folio", folio);
                            i.putExtra("path", path);
                            i.putExtra("idUsuario", usuario);
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
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_puertas = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_puertas,
                                THUMBSIZE,
                                THUMBSIZE);
                        IV_puertas.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        IV_puertas.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_PLACAS:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_placas = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_placas,
                                THUMBSIZE,
                                THUMBSIZE);
                        IV_placas.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        IV_placas.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_SELLO1:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_sello1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_sello1,
                                THUMBSIZE,
                                THUMBSIZE);
                        IV_sello1.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        IV_sello1.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_SELLO2:
                if (resultCode == RESULT_OK) {
                    try {

                        actual_sello2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_sello2,
                                THUMBSIZE,
                                THUMBSIZE);
                        IV_sello2.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        IV_sello2.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_SELLO3:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_sello3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_sello3,
                                THUMBSIZE,
                                THUMBSIZE);
                        IV_sello3.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        IV_sello3.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO1:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_daño1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_daño1,
                                THUMBSIZE,
                                THUMBSIZE);
                        daño1.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        daño1.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO2:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_daño2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_daño2,
                                THUMBSIZE,
                                THUMBSIZE);
                        daño2.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        daño2.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO3:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_daño3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_daño3,
                                THUMBSIZE,
                                THUMBSIZE);
                        daño3.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        daño3.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DAÑO4:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_daño4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_daño4,
                                THUMBSIZE,
                                THUMBSIZE);
                        daño4.setImageBitmap(thumbImage);


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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        IV_puertas.setImageBitmap(null);
        IV_placas.setImageBitmap(null);
        IV_sello1.setImageBitmap(null);
        IV_sello2.setImageBitmap(null);
        IV_sello3.setImageBitmap(null);
        daño1.setImageBitmap(null);
        daño2.setImageBitmap(null);
        daño3.setImageBitmap(null);
        daño4.setImageBitmap(null);


        actual_puertas = null;
        actual_placas = null;
        actual_sello1 = null;
        actual_sello2 = null;
        actual_sello3 = null;
        actual_daño1 = null;
        actual_daño2 = null;
        actual_daño3 = null;
        actual_daño4 = null;


        photoURI = null;
        imageFile = null;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}