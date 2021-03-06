package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Context;
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

public class etapa2_Activity extends AppCompatActivity {

    private CheckBox defensa, cabina , quintaRueda , tuboEscape , base , techos , llantas , tanqueDiesel , tanqueAire , transmision , motor ;
    private Boolean check_defensa = true,
            check_cabina = true,
            check_quintaRueda = true,
            check_tuboEscape = true,
            check_base = true,
            check_techos = true,
            check_llantas = true,
            check_tanqueDiesel = true,
            check_tanqueAire = true,
            check_transmision = true,
            check_motor = true;
    private String string_defensa, string_cabina , string_quintaRueda , string_tuboEscape , string_base ,
            string_techos , string_llantas , string_tanqueDiesel , string_tanqueAire , string_transmision , string_motor ,path;
    private ImageView izqTracto, frenteTracto, derTracto ;
    private Bitmap actual_izqTracto, actual_frenteTracto, actual_derTracto ;
    private Button btnEtapa2 ;
    private final int THUMBSIZE = 128;
    public static final int REQUEST_IZQ_TRACTO = 100;
    public static final int REQUEST_DER_TRACTO = 101;
    public static final int REQUEST_FRENTE_TRACTO = 102;
    private File imageFile;
    private Uri photoURI;
    private String  imageFileName , folio , usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa2);

        folio = getIntent().getStringExtra("folio");
        path = getIntent().getStringExtra("path");
        usuario = getIntent().getStringExtra("idUsuario");

        defensa = (CheckBox) findViewById(R.id.CB_defensa);
        cabina = (CheckBox) findViewById(R.id.CB_cabina);
        quintaRueda = (CheckBox) findViewById(R.id.CB_quinta);
        tuboEscape = (CheckBox) findViewById(R.id.CB_tubo);
        base = (CheckBox) findViewById(R.id.CB_base);
        techos = (CheckBox) findViewById(R.id.CB_techos);
        llantas = (CheckBox) findViewById(R.id.CB_llanta);
        tanqueDiesel = (CheckBox) findViewById(R.id.CB_tanqueDiesel);
        tanqueAire = (CheckBox) findViewById(R.id.CB_tanqueAire);
        transmision = (CheckBox) findViewById(R.id.CB_ejeTrans);
        motor = (CheckBox) findViewById(R.id.CB_motor);

        izqTracto = (ImageView) findViewById(R.id.IV_TractoIzq);
        frenteTracto = (ImageView) findViewById(R.id.IV_tractorDer);
        derTracto = (ImageView) findViewById(R.id.IV_tractoFrente);

        izqTracto.setClipToOutline(true);
        frenteTracto.setClipToOutline(true);
        derTracto.setClipToOutline(true);


       btnEtapa2 =  (Button) findViewById(R.id.btn_etapa2);



        defensa.setOnClickListener(view -> {

            check_defensa = ((CheckBox) view).isChecked();

        });

        cabina.setOnClickListener(view -> {

            check_cabina = ((CheckBox) view).isChecked();

        });

        quintaRueda.setOnClickListener(view -> {

            check_quintaRueda = ((CheckBox) view).isChecked();

        });

        tuboEscape.setOnClickListener(view -> {

            check_tuboEscape = ((CheckBox) view).isChecked();

        });

        base.setOnClickListener(view -> {

            check_base = ((CheckBox) view).isChecked();

        });

        techos.setOnClickListener(view -> {

            check_techos = ((CheckBox) view).isChecked();

        });

        llantas.setOnClickListener(view -> {

            check_llantas = ((CheckBox) view).isChecked();

        });

        tanqueDiesel.setOnClickListener(view -> {

            check_tanqueDiesel = ((CheckBox) view).isChecked();

        });

        tanqueAire.setOnClickListener(view -> {

            check_tanqueAire = ((CheckBox) view).isChecked();

        });

        transmision.setOnClickListener(view -> {

            check_transmision = ((CheckBox) view).isChecked();

        });

        motor.setOnClickListener(view -> {

            check_motor = ((CheckBox) view).isChecked();

        });

        izqTracto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                izqTracto.setEnabled(false);
                izqTracto.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        izqTracto.setEnabled(true);
                        izqTracto.setClickable(true);

                    }
                }, TIME);
                imgClick("tractoIzq", REQUEST_IZQ_TRACTO);

            }
        });

        frenteTracto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frenteTracto.setEnabled(false);
                frenteTracto.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        frenteTracto.setEnabled(true);
                        frenteTracto.setClickable(true);

                    }
                }, TIME);
                imgClick("tractoFrente", REQUEST_FRENTE_TRACTO);

            }
        });

        derTracto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                derTracto.setEnabled(false);
                derTracto.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        derTracto.setEnabled(true);
                        derTracto.setClickable(true);

                    }
                }, TIME);
                imgClick("tractoDer", REQUEST_DER_TRACTO);

            }
        });

        btnEtapa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnEtapa2.setEnabled(false);
                btnEtapa2.setClickable(false);
                btnEtapa2.setText("Enviando...");
                btnEtapa2.setTextColor(Color.parseColor("#074EAB"));
                btnEtapa2.setBackgroundResource(R.drawable.round_btn2);

                int TIME = 10000;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        btnEtapa2.setEnabled(true);
                        btnEtapa2.setClickable(true);
                        btnEtapa2.setText("Siguiente");
                        btnEtapa2.setTextColor(Color.parseColor("#FFFFFF"));
                        btnEtapa2.setBackgroundResource(R.drawable.round_btn);

                    }
                }, TIME);


                if (check_defensa) {
                    string_defensa = "1";
                }else{
                    string_defensa = "0";
                }
                if (check_cabina) {
                    string_cabina = "1";
                }else{
                    string_cabina = "0";
                }
                if (check_quintaRueda) {
                    string_quintaRueda = "1";

                }else{
                    string_quintaRueda = "0";
                }
                if (check_tuboEscape) {
                    string_tuboEscape = "1";

                }else{
                    string_tuboEscape = "0";
                }
                if (check_base) {
                    string_base = "1";

                }else{
                    string_base = "0";
                }
                if (check_techos) {
                    string_techos = "1";

                }else{
                    string_techos = "0";
                }
                if (check_llantas) {
                    string_llantas = "1";

                }else{
                    string_llantas = "0";
                }
                if (check_tanqueDiesel) {
                    string_tanqueDiesel = "1";

                }else{
                    string_tanqueDiesel = "0";
                }
                if (check_tanqueAire) {
                    string_tanqueAire = "1";

                }else{
                    string_tanqueAire = "0";
                }
                if (check_transmision) {
                    string_transmision = "1";

                }else{
                    string_transmision = "0";
                }
                if (check_motor) {
                    string_motor = "1";

                }else{
                    string_motor = "0";
                }

                if(actual_derTracto == null || actual_frenteTracto == null || actual_izqTracto == null) {

                    Toast.makeText(etapa2_Activity.this, "Datos Incompletos", Toast.LENGTH_LONG).show();
                } else {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(etapa2_Activity.this);

                    long insertIntercambio1 = dataBaseHelper.insertIntercambioElectronico2("tractoIzqUrl-"+folio,"tractoFrenteUrl-"+folio,"tractoDerUrl-"+folio,
                            "3",folio,string_defensa,string_cabina,string_quintaRueda,string_tuboEscape
                            ,string_base,string_techos,string_llantas,string_tanqueDiesel,
                            string_tanqueAire,string_transmision,string_motor);

                    if(insertIntercambio1 == -1){

                        Toast.makeText(etapa2_Activity.this, "Error insertIntercambio2", Toast.LENGTH_LONG).show();

                    }else {

                        if(createDirectoryAndSaveFile( actual_derTracto,  "tractoDerUrl-"+folio+".jpg", path) &&
                        createDirectoryAndSaveFile( actual_frenteTracto,  "tractoFrenteUrl-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( actual_izqTracto,  "tractoIzqUrl-"+folio+".jpg", path)
                        )
                        {
                            Intent i = new Intent(etapa2_Activity.this, etapa3_Activity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("folio", folio);
                            i.putExtra("path", path);
                            i.putExtra("idUsuario", usuario);
                            startActivity(i);
                        }else{
                            Toast.makeText(etapa2_Activity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
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
            case REQUEST_IZQ_TRACTO:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_izqTracto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_izqTracto,
                                THUMBSIZE,
                                THUMBSIZE);
                        izqTracto.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        izqTracto.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_DER_TRACTO:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_derTracto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_derTracto,
                                THUMBSIZE,
                                THUMBSIZE);
                        derTracto.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        derTracto.setImageResource(R.drawable.ic_baseline_error_24);
                    }
                }
                break;
            case REQUEST_FRENTE_TRACTO:
                if (resultCode == RESULT_OK ) {
                    try {

                        actual_frenteTracto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                        Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_frenteTracto,
                                THUMBSIZE,
                                THUMBSIZE);
                        frenteTracto.setImageBitmap(thumbImage);


                    } catch (IOException e) {
                        frenteTracto.setImageResource(R.drawable.ic_baseline_error_24);
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

        izqTracto.setImageBitmap(null);
        derTracto.setImageBitmap(null);
        frenteTracto.setImageBitmap(null);

        actual_derTracto = null ;
        actual_frenteTracto = null ;
        actual_izqTracto = null ;

        photoURI = null;
        imageFile = null;

    }

    @Override
    public void onBackPressed() {

    }
}