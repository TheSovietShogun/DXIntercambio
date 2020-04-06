package com.example.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class imgActivity extends AppCompatActivity {

    private ImageView tractor , noEconomico, izqRemolqueP1 , vin , chasisFrontalIzq , chasisTraseroIzq , llantasIzqEje1
            ,llantasIzqEje2 , izqRemolqueP2 , puertas , placas , sello1 , sello2 , derRemolqueP1 , llantasDerEje2
            , llantasDerEje1 , chasisTraseroDer , chasisFrontalDER , derRemolqueP2 , damage1 , damage2 , damage3 , damage4;
    private static final int REQUEST_CODE_SIGN_IN = 1;
    private static final String TAG = "envioActivity";
    private Drive mService;
    private Drive googleDriveService;
    private String operacion ;
    private String NoUnidad ;
    private String NoCaja ;
    private String nombreLinea ;
    private String nombreTransportista ;
    private File imageFile ;
    private Uri photoURI;
    private ProgressBar progressBar1 ;
    private Button btnImg ;
    private static final int REQUEST_TRACTOR = 200;
    private static final int REQUEST_NoECONOMICO = 210;
    private static final int REQUEST_IZQ_REMOLQUE_P1 = 220;
    private static final int REQUEST_VIN = 230;
    private static final int REQUEST_CHASIS_FRONTAL_IZQ = 240;
    private static final int REQUEST_CHASIS_TRASERO_IZQ = 250;
    private static final int REQUEST_LLANTAS_IZQ_EJE1= 260;
    private static final int REQUEST__LLANTAS_IZQ_EJE2 = 270;
    private static final int REQUEST_IZQ_REMOLQUE_P2 = 280;
    private static final int REQUEST_PUERTAS = 290;
    private static final int REQUEST_PLACAS = 300;
    private static final int REQUEST_SELLO1 = 310;
    private static final int REQUEST_SELLO2 = 320;
    private static final int REQUEST_DER_REMOLQUE_P1 = 330;
    private static final int REQUEST_LLANTAS_DER_EJE2 = 340;
    private static final int REQUEST_LLANTAS_DER_EJE1 = 350;
    private static final int REQUEST_CHASIS_TRASERO_DER = 360;
    private static final int REQUEST_CHASIS_FRONTAL_DER = 370;
    private static final int REQUEST_DER_REMOLQUE_P2 = 380;
    private static final int DAMAGE1= 500;
    private static final int DAMAGE2 = 501;
    private static final int DAMAGE3 = 502;
    private static final int DAMAGE4 = 503;
    private int descarga;
    final int THUMBSIZE = 128;


    @Override
    protected void onStart() {
        super.onStart();

        requestSignIn();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        operacion = getIntent().getStringExtra("operador");
        NoUnidad = getIntent().getStringExtra("NoUnidad");
        NoCaja = getIntent().getStringExtra("NoCaja");
        nombreLinea = getIntent().getStringExtra("nombreLinea");
        nombreTransportista = getIntent().getStringExtra("nombreTransportista");

        tractor = (ImageView) findViewById(R.id.imageView3);
        noEconomico = (ImageView) findViewById(R.id.imageView4);
        izqRemolqueP1 = (ImageView) findViewById(R.id.imageView5);
        vin = (ImageView) findViewById(R.id.imageView11);
        chasisFrontalIzq = (ImageView) findViewById(R.id.imageView7);
        chasisTraseroIzq = (ImageView) findViewById(R.id.imageView2);
        llantasIzqEje1 = (ImageView) findViewById(R.id.imageView6);
        llantasIzqEje2 = (ImageView) findViewById(R.id.imageView8);
        izqRemolqueP2 = (ImageView) findViewById(R.id.imageView10);
        puertas = (ImageView) findViewById(R.id.imageView9);
        placas = (ImageView) findViewById(R.id.imageView12);
        sello1 = (ImageView) findViewById(R.id.imageView13);
        sello2 = (ImageView) findViewById(R.id.imageView17);
        derRemolqueP1 = (ImageView) findViewById(R.id.imageView16);
        llantasDerEje2 = (ImageView) findViewById(R.id.imageView15);
        llantasDerEje1 = (ImageView) findViewById(R.id.imageView14);
        chasisTraseroDer = (ImageView) findViewById(R.id.imageView19);
        chasisFrontalDER = (ImageView) findViewById(R.id.imageView20);
        derRemolqueP2 = (ImageView) findViewById(R.id.imageView22);

        damage1 = (ImageView) findViewById(R.id.imageView18);
        damage2 = (ImageView) findViewById(R.id.imageView21);
        damage3 = (ImageView) findViewById(R.id.imageView23);
        damage4 = (ImageView) findViewById(R.id.imageView24);

        btnImg = (Button) findViewById(R.id.btnImg);


        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                tractor.getDrawable();

                String tractorImg =  String.valueOf(tractor.getDrawable().getBounds());
                String noEconomicoImg = String.valueOf(noEconomico.getDrawable().getBounds());
                String izqRemolqueP1Img = String.valueOf(izqRemolqueP1.getDrawable().getBounds());
                String vinImg = String.valueOf(vin.getDrawable().getBounds());
                String chasisFrontalIzqImg = String.valueOf(chasisFrontalIzq.getDrawable().getBounds());
                String chasisTraseroIzqImg = String.valueOf(chasisTraseroIzq.getDrawable().getBounds());
                String llantasIzqEje1Img = String.valueOf(llantasIzqEje1.getDrawable().getBounds());
                String llantasIzqEje2Img = String.valueOf(llantasIzqEje2.getDrawable().getBounds());
                String izqRemolqueP2Img = String.valueOf(izqRemolqueP2.getDrawable().getBounds());
                String puertasImg = String.valueOf(puertas.getDrawable().getBounds());
                String placasImg = String.valueOf(placas.getDrawable().getBounds());
                String sello1Img = String.valueOf(sello1.getDrawable().getBounds());
                String sello2Img = String.valueOf(sello2.getDrawable().getBounds());
                String derRemolqueP1Img = String.valueOf(derRemolqueP1.getDrawable().getBounds());
                String llantasDerEje2Img = String.valueOf(llantasDerEje2.getDrawable().getBounds());
                String llantasDerEje1Img = String.valueOf(llantasDerEje1.getDrawable().getBounds());
                String chasisTraseroDerImg = String.valueOf(chasisTraseroDer.getDrawable().getBounds());
                String chasisFrontalDERImg = String.valueOf(chasisFrontalDER.getDrawable().getBounds());
                String derRemolqueP2Img = String.valueOf(derRemolqueP2.getDrawable().getBounds());

                String damage1Img = String.valueOf(damage1.getDrawable().getBounds());
                String damage2Img = String.valueOf(damage2.getDrawable().getBounds());
                String damage3Img = String.valueOf(damage3.getDrawable().getBounds());
                String damage4Img = String.valueOf(damage4.getDrawable().getBounds());



                if (tractorImg.contains("128") ||
                        noEconomicoImg.contains("128") ||
                        izqRemolqueP1Img.contains("128") ||
                        vinImg.contains("128") ||
                        chasisFrontalIzqImg.contains("128") ||
                        chasisTraseroIzqImg.contains("128") ||
                        llantasIzqEje1Img.contains("128") ||
                        llantasIzqEje2Img.contains("128") ||
                        izqRemolqueP2Img.contains("128") ||
                        puertasImg.contains("128") ||
                        placasImg.contains("128") ||
                        sello1Img.contains("128") ||
                        sello2Img.contains("128") ||
                        derRemolqueP1Img.contains("128") ||
                        llantasDerEje2Img.contains("128") ||
                        llantasDerEje1Img.contains("128") ||
                        chasisTraseroDerImg.contains("128") ||
                        chasisFrontalDERImg.contains("128") ||
                        derRemolqueP2Img.contains("128")
                ){

                    if (damage1Img.contains("300") &&
                            damage2Img.contains("300") &&
                            damage3Img.contains("300") &&
                            damage4Img.contains("300") ){

                        AlertDialog.Builder builder = new AlertDialog.Builder(imgActivity.this);
                        builder.setMessage("Ninguna imagen de daños fue tomada , desea continuar ?")
                                .setCancelable(false)
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog, final int id) {

                                        Intent i = new Intent(imgActivity.this, splash.class);
                                        startActivity(i);

                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog, final int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }



                }else {
                    Toast.makeText(getBaseContext(),"Faltan imagenes por tomar",Toast.LENGTH_SHORT).show();
                }




            }
        });

        tractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    imgClick("tractor" , REQUEST_TRACTOR);
            }
        });

        noEconomico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("noEconomico" , REQUEST_NoECONOMICO);
            }
        });
        izqRemolqueP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("izqRemolqueP1" , REQUEST_IZQ_REMOLQUE_P1);
            }
        });
        vin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("vin" , REQUEST_VIN);
            }
        });
        chasisFrontalIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisFrontalIzq" , REQUEST_CHASIS_FRONTAL_IZQ);
            }
        });
        chasisTraseroIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisTraseroIzq" , REQUEST_CHASIS_TRASERO_IZQ);
            }
        });
        llantasIzqEje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("llantasIzqEje1" , REQUEST_LLANTAS_IZQ_EJE1);
            }
        });
        llantasIzqEje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("llantasIzqEje2" , REQUEST__LLANTAS_IZQ_EJE2);
            }
        });
        izqRemolqueP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("izqRemolqueP2" , REQUEST_IZQ_REMOLQUE_P2);
            }
        });
        puertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("puertas" , REQUEST_PUERTAS);
            }
        });
        placas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("placas" , REQUEST_PLACAS);
            }
        });
        sello1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("sello1" , REQUEST_SELLO1);
            }
        });
        sello2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("sello2" , REQUEST_SELLO2);
            }
        });
        derRemolqueP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("derRemolqueP1" , REQUEST_DER_REMOLQUE_P1);
            }
        });
        llantasDerEje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("llantasDerEje2" , REQUEST_LLANTAS_DER_EJE2);
            }
        });
        llantasDerEje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("llantasDerEje1" , REQUEST_LLANTAS_DER_EJE1);
            }
        });
        chasisTraseroDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisTraseroDer" , REQUEST_CHASIS_TRASERO_DER);
            }
        });
        chasisFrontalDER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("chasisFrontalDER" , REQUEST_CHASIS_FRONTAL_DER);
            }
        });
        derRemolqueP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("derRemolqueP2" , REQUEST_DER_REMOLQUE_P2);
            }
        });
        damage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("damage1" , DAMAGE1);
            }
        });
        damage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("damage2" , DAMAGE2);
            }
        });
        damage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("damage3" , DAMAGE3);
            }
        });
        damage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick("damage4" , DAMAGE4);
            }
        });
    }

    private void imgClick (String photo , int code){

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_hora:" + timeStamp + "_foto:"+photo+ "_tipoOperacion:"+operacion+"_transportista:"+nombreTransportista+"_unidad:"+NoUnidad+"_linea:"+nombreLinea+"_caja:"+NoCaja;
        File destPath = new File(getBaseContext().getExternalFilesDir(null).getAbsolutePath());
        imageFile = null ;
        photoURI = null;

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
        startActivityForResult(camera,code);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        switch (requestCode) {

            case REQUEST_CODE_SIGN_IN:
                if (resultCode == Activity.RESULT_OK && resultData != null) {
                    handleSignInResult(resultData);
                }
                break;

            case REQUEST_TRACTOR:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                   tractor.setImageBitmap(thumbImage);
                   //Picasso.get().load(String.valueOf(thumbImage)).into(tractor);
                    uploadDrive();
                }
                break;
            case REQUEST_NoECONOMICO:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    noEconomico.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(noEconomico);
                    uploadDrive();
                }
                break;
            case REQUEST_IZQ_REMOLQUE_P1:
                if (resultCode == Activity.RESULT_OK ) {


                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    izqRemolqueP1.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(izqRemolqueP1);
                    uploadDrive();
                }
                break;
            case REQUEST_VIN:
                if (resultCode == Activity.RESULT_OK ) {


                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    vin.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(vin);
                    uploadDrive();
                }
                break;
            case REQUEST_CHASIS_FRONTAL_IZQ:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisFrontalIzq.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(chasisFrontalIzq);
                    uploadDrive();
                }
                break;
            case REQUEST_CHASIS_TRASERO_IZQ:
                if (resultCode == Activity.RESULT_OK ) {


                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisTraseroIzq.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(chasisTraseroIzq);
                    uploadDrive();
                }
                break;
            case REQUEST_LLANTAS_IZQ_EJE1:
                if (resultCode == Activity.RESULT_OK ) {


                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasIzqEje1.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(llantasIzqEje1);
                    uploadDrive();
                }
                break;
            case REQUEST__LLANTAS_IZQ_EJE2:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasIzqEje2.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(llantasIzqEje2);
                    uploadDrive();
                }
                break;
            case REQUEST_IZQ_REMOLQUE_P2:
                if (resultCode == Activity.RESULT_OK ) {


                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    izqRemolqueP2.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(izqRemolqueP2);
                    uploadDrive();
                }
                break;
                case REQUEST_PUERTAS:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    puertas.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(puertas);
                    uploadDrive();
                }
                break;
                case REQUEST_PLACAS:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    placas.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(placas);
                    uploadDrive();
                }
                break;
            case REQUEST_SELLO1:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    sello1.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(sello1);
                    uploadDrive();
                }
               break;
                case REQUEST_SELLO2:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    sello2.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(sello2);
                    uploadDrive();
                }
                 break;
                case REQUEST_DER_REMOLQUE_P1:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    derRemolqueP1.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(derRemolqueP1);
                    uploadDrive();
                }
                break;
                case REQUEST_LLANTAS_DER_EJE2:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasDerEje2.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(llantasDerEje2);
                    uploadDrive();
                }
                break;
            case REQUEST_LLANTAS_DER_EJE1:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasDerEje1.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(llantasDerEje1);
                    uploadDrive();
                }
                break;
            case REQUEST_CHASIS_TRASERO_DER:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisTraseroDer.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(chasisTraseroDer);
                    uploadDrive();
                }
                break;
            case REQUEST_CHASIS_FRONTAL_DER:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisFrontalDER.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(chasisFrontalDER);
                    uploadDrive();
                }
                break;
            case REQUEST_DER_REMOLQUE_P2:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    derRemolqueP2.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(derRemolqueP2);
                    uploadDrive();
                }
                break;
            case DAMAGE1:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage1.setImageBitmap(thumbImage);
                    //Picasso.get().load(photoURI).into(damage1);
                    uploadDrive();
                }
                break;
            case DAMAGE2:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage2.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(damage2);
                    uploadDrive();
                }
                break;
            case DAMAGE3:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage3.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(damage3);
                    uploadDrive();
                }
                break;
            case DAMAGE4:
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage4.setImageBitmap(thumbImage);
                   // Picasso.get().load(photoURI).into(damage4);
                    uploadDrive();
                }
                break;





        }
        super.onActivityResult(requestCode, resultCode, resultData);
    }

        private void uploadDrive () {

         descarga = 0 ;

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // Create URI from real path
                    String path = imageFile.getPath();
                    String name = imageFile.getName();
                    Boolean res ;



                    com.google.api.services.drive.model.File metadata = new com.google.api.services.drive.model.File();
                    metadata.setName(name);

                    java.io.File filePath = new java.io.File(path);
                    FileContent mediaContent = new FileContent("image/jpeg", filePath);

                    try {
                        com.google.api.services.drive.model.File file = googleDriveService.files().create(metadata, mediaContent)
                                .setFields("id")
                                .execute();

                    } catch (IOException e) {
                        e.printStackTrace();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getBaseContext(),"Error al enviar imagen",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }
            });
            t.start();


        }

    private void handleSignInResult(Intent result) {
        GoogleSignIn.getSignedInAccountFromIntent(result)
                .addOnSuccessListener(googleAccount -> {
                    Log.d(TAG, "Signed in as " + googleAccount.getEmail());

                    // Use the authenticated account to sign in to the Drive service.
                    GoogleAccountCredential credential =
                            GoogleAccountCredential.usingOAuth2(
                                    this, Collections.singleton(DriveScopes.DRIVE_FILE));
                    credential.setSelectedAccount(googleAccount.getAccount());
                    googleDriveService =
                            new Drive.Builder(
                                    AndroidHttp.newCompatibleTransport(),
                                    new GsonFactory(),
                                    credential)
                                    .setApplicationName("Drive API Migration")
                                    .build();

                    // The DriveServiceHelper encapsulates all REST API and SAF functionality.
                    // Its instantiation is required before handling any onClick actions.
                    //mDriveServiceHelper = new DriveServiceHelper(googleDriveService);
                })
                .addOnFailureListener(exception -> Log.e(TAG, "Unable to sign in.", exception));

    }

    private void requestSignIn() {

        GoogleSignInOptions signInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestScopes(new Scope(DriveScopes.DRIVE_FILE))
                        .build();
        GoogleSignInClient client = GoogleSignIn.getClient(this, signInOptions);

        // The result of the sign-in Intent is handled in onActivityResult.
        startActivityForResult(client.getSignInIntent(), REQUEST_CODE_SIGN_IN);
    }
}
