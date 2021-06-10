package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class firmasActivity extends AppCompatActivity {


    private SignatureView signatureView;
    private SignatureView signatureView2;
    private ImageView imgClear;
    private ImageView imgClear2;
    private Button btnEnvio ;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private String folio ,path;
    private String mensaje;
    private String fechaHora;
    private AlertDialog alert  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firmas);

        signatureView =  (SignatureView) findViewById(R.id.signature_view);
        imgClear = (ImageView) findViewById(R.id.imageView32);

        signatureView2 =  (SignatureView) findViewById(R.id.signature_view2);
        imgClear2 = (ImageView) findViewById(R.id.imageView31);

        btnEnvio = (Button) findViewById(R.id.button2);

        folio = getIntent().getStringExtra("folio");
        path = getIntent().getStringExtra("path");



        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signatureView2.clearCanvas();
            }
        });

        imgClear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signatureView.clearCanvas();
            }
        });

        btnEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bitmap1 = signatureView2.getSignatureBitmap();
                bitmap2 = signatureView.getSignatureBitmap();

                fechaHora = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date());

                if (signatureView.isBitmapEmpty() || signatureView2.isBitmapEmpty()) {
                    Toast.makeText(firmasActivity.this, "Falta firma", Toast.LENGTH_LONG).show();
                }else  {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(firmasActivity.this);

                    long insertIntercambio1 = dataBaseHelper.insertIntercambioElectronico200(
                            folio,fechaHora,
                            "firmaOperadorUrl-"+folio,
                            "firmaIntercambistaUrl-"+folio
                    );

                    if(insertIntercambio1 == -1){
                        Toast.makeText(firmasActivity.this, "Error insertIntercambio200", Toast.LENGTH_LONG).show();
                    }else {
                        if(createDirectoryAndSaveFile( bitmap1,  "firmaOperadorUrl-"+folio+".jpg", path) &&
                                createDirectoryAndSaveFile( bitmap2,  "firmaIntercambistaUrl-"+folio+".jpg", path)
                        )
                        {

                             AlertDialog.Builder builder = new AlertDialog.Builder(firmasActivity.this);
                            builder.setMessage("Desea realizar otro intercambio ?")
                                    .setCancelable(false)
                                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Intent i = new Intent(firmasActivity.this, etapa1_Activity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(i);

                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Intent i = new Intent(firmasActivity.this, menuActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(i);


                                        }
                                    });

                            alert = builder.create();
                            alert.show();


                        }else{
                            Toast.makeText(firmasActivity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
                        }
                    }

                }



            }
        });

    }

    @Override
    public void onBackPressed() {

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
