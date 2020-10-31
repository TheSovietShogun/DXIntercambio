package com.dx.dxintercambio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class imgActivity extends AppCompatActivity {

    private ImageView tractor , noEconomico, izqRemolqueP1 , vin , chasisFrontalIzq , chasisTraseroIzq , llantasIzqEje1
            ,llantasIzqEje2 , izqRemolqueP2 , puertas , placas , sello1 , sello2 , derRemolqueP1 , llantasDerEje2
            , llantasDerEje1 , chasisTraseroDer , chasisFrontalDER , derRemolqueP2 , damage1 , damage2 , damage3 , damage4, sello3;
    private static final int REQUEST_CODE_SIGN_IN = 1;
    private static final String TAG = "envioActivity";
    private Spinner llanta1SP, llanta2SP , llanta3SP ,llanta4SP,llanta5SP ,llanta6SP ,llanta7SP,llanta8SP;
    private CheckBox jumboRB1 , jumboRB2;
    private EditText sello1ET ,sello2ET, sello3ET;
    private Drive mService;
    private Drive googleDriveService;
    private String operacion ;
    private String NoUnidad ;
    private String NoCaja ;
    private String nombreLinea ;
    private String nombreTransportista ;
    private String folio ;
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
    private static final int REQUEST_TARJETA = 390;
    private static final int REQUEST_SELLO3 = 400;
    private static final int DAMAGE1= 500;
    private static final int DAMAGE2 = 501;
    private static final int DAMAGE3 = 502;
    private static final int DAMAGE4 = 503;
    private Boolean resk;
    private final int THUMBSIZE = 128;
    private int res = 0 ;
    private String tractorImg ;
    private String noEconomicoImg;
    private String izqRemolqueP1Img;
    private String vinImg;
    private String chasisFrontalIzqImg;
    private String chasisTraseroIzqImg;
    private  String llantasIzqEje1Img;
    private String [] Llantas ;
    private String llantasIzqEje2Img;
    private String izqRemolqueP2Img;
    private String puertasImg;
    private String placasImg;
    private String sello3Img;
    private String sello1Img;
    private int idRemolque;
    private String sello2Img;
    private String derRemolqueP1Img;
    private String llantasDerEje2Img;
    private String llantasDerEje1Img;
    private String chasisTraseroDerImg;
    private  String chasisFrontalDERImg;
    private String derRemolqueP2Img, tarjertaImg ;
    private DxApi dxApi;
    private String damage1Img ;
    private String damage2Img ;
    private String damage3Img;
    private String damage4Img ;
    private String idUsuario;
    boolean checked1;
    boolean checked2;
    private int lljumbo ;
    private int lljumbo2 ;
    private Object Network;
    private String user ;
    private String password ;
    private int mensaje;
    private String imageFileName ;

    @Override
    protected void onStart() {
        super.onStart();

       // requestSignIn();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu2,menu);
        return true ;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cancelar){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Desea cancelar este Intercambio ?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {

                            Intent i = new Intent(imgActivity.this, cancelarActivity.class);
                            i.putExtra("mensaje", mensaje);
                            i.putExtra("idUsuario", idUsuario);
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
        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);



        user = preferences.getString("user","");
        password = preferences.getString("pass","");

        operacion = getIntent().getStringExtra("operacion");
        NoUnidad = getIntent().getStringExtra("NoUnidad");
        NoCaja = getIntent().getStringExtra("NoCaja");
        idRemolque = getIntent().getIntExtra("idRemolque",0);
        nombreLinea = getIntent().getStringExtra("nombreLinea");
        nombreTransportista = getIntent().getStringExtra("nombreTransportista");
        folio = getIntent().getStringExtra("folio");
        mensaje = getIntent().getIntExtra("mensaje",0);
        idUsuario = getIntent().getStringExtra("idUsuario");


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

        sello3 = (ImageView) findViewById(R.id.imageView30);


        //tarjeta = (ImageView) findViewById(R.id.imageView420);
        jumboRB1 = (CheckBox) findViewById(R.id.radioButton2);
        llanta1SP = (Spinner) findViewById(R.id.spinner6);
        llanta2SP = (Spinner) findViewById(R.id.spinner9);
        llanta3SP = (Spinner) findViewById(R.id.spinner5);
        llanta4SP = (Spinner) findViewById(R.id.spinner8);
        llanta5SP = (Spinner) findViewById(R.id.spinner12);
        llanta6SP = (Spinner) findViewById(R.id.spinner13);
        llanta7SP = (Spinner) findViewById(R.id.spinner14);
        llanta8SP = (Spinner) findViewById(R.id.spinner11);
        sello1ET = (EditText) findViewById(R.id.editText);
        sello2ET = (EditText) findViewById(R.id.editText2);
        sello3ET = (EditText) findViewById(R.id.editText4);


        damage1 = (ImageView) findViewById(R.id.imageView18);
        damage2 = (ImageView) findViewById(R.id.imageView21);
        damage3 = (ImageView) findViewById(R.id.imageView23);
        damage4 = (ImageView) findViewById(R.id.imageView24);

        btnImg = (Button) findViewById(R.id.btnImg);

        Llantas = new String[]{"Sin Seleccionar","Hanck","Goodyear", "Michelin", "Yokohama"};

        ArrayAdapter<String> adapterll1 = new ArrayAdapter<String>(this, R.layout.mspinner_item, Llantas);
        llanta1SP.setAdapter(adapterll1);
        ArrayAdapter<String> adapterll2 = new ArrayAdapter<String>(this, R.layout.mspinner_item, Llantas);
        llanta2SP.setAdapter(adapterll2);
        ArrayAdapter<String> adapterll3 = new ArrayAdapter<String>(this, R.layout.mspinner_item, Llantas);
        llanta3SP.setAdapter(adapterll3);
        ArrayAdapter<String> adapterll4 = new ArrayAdapter<String>(this, R.layout.mspinner_item, Llantas);
        llanta4SP.setAdapter(adapterll4);
        ArrayAdapter<String> adapterll5 = new ArrayAdapter<String>(this, R.layout.mspinner_item, Llantas);
        llanta5SP.setAdapter(adapterll5);
        ArrayAdapter<String> adapterll6 = new ArrayAdapter<String>(this, R.layout.mspinner_item, Llantas);
        llanta6SP.setAdapter(adapterll6);
        ArrayAdapter<String> adapterll7 = new ArrayAdapter<String>(this, R.layout.mspinner_item, Llantas);
        llanta7SP.setAdapter(adapterll7);
        ArrayAdapter<String> adapterll8 = new ArrayAdapter<String>(this, R.layout.mspinner_item, Llantas);
        llanta8SP.setAdapter(adapterll8);


        jumboRB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                 checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    llanta3SP.setVisibility(View.INVISIBLE);
                    llanta4SP.setVisibility(View.INVISIBLE);
                    llanta7SP.setVisibility(View.INVISIBLE);
                    llanta8SP.setVisibility(View.INVISIBLE);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    tractor.setImageBitmap(thumbImage);
                    noEconomico.setImageBitmap(thumbImage);
                    izqRemolqueP1.setImageBitmap(thumbImage);
                    vin.setImageBitmap(thumbImage);
                    chasisFrontalIzq.setImageBitmap(thumbImage);
                    chasisTraseroIzq.setImageBitmap(thumbImage);
                    llantasIzqEje1.setImageBitmap(thumbImage);
                    llantasIzqEje2.setImageBitmap(thumbImage);
                    izqRemolqueP2.setImageBitmap(thumbImage);
                    puertas.setImageBitmap(thumbImage);
                    placas.setImageBitmap(thumbImage);
                    sello1.setImageBitmap(thumbImage);
                    sello2.setImageBitmap(thumbImage);
                    derRemolqueP1.setImageBitmap(thumbImage);
                    llantasDerEje2.setImageBitmap(thumbImage);
                    llantasDerEje1.setImageBitmap(thumbImage);
                    chasisTraseroDer.setImageBitmap(thumbImage);
                    chasisFrontalDER.setImageBitmap(thumbImage);
                    derRemolqueP2.setImageBitmap(thumbImage);

                }else{
                    llanta3SP.setVisibility(View.VISIBLE);
                    llanta4SP.setVisibility(View.VISIBLE);
                    llanta7SP.setVisibility(View.VISIBLE);
                    llanta8SP.setVisibility(View.VISIBLE);
                }
            }
        });



        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //300 VACIO
                //128 IMAGFN

                tractorImg =  String.valueOf(tractor.getDrawable().getBounds());
                noEconomicoImg = String.valueOf(noEconomico.getDrawable().getBounds());
                izqRemolqueP1Img = String.valueOf(izqRemolqueP1.getDrawable().getBounds());
                vinImg = String.valueOf(vin.getDrawable().getBounds());
                chasisFrontalIzqImg = String.valueOf(chasisFrontalIzq.getDrawable().getBounds());
                chasisTraseroIzqImg = String.valueOf(chasisTraseroIzq.getDrawable().getBounds());
                llantasIzqEje1Img = String.valueOf(llantasIzqEje1.getDrawable().getBounds());
                llantasIzqEje2Img = String.valueOf(llantasIzqEje2.getDrawable().getBounds());
                izqRemolqueP2Img = String.valueOf(izqRemolqueP2.getDrawable().getBounds());
                puertasImg = String.valueOf(puertas.getDrawable().getBounds());
                placasImg = String.valueOf(placas.getDrawable().getBounds());
                sello1Img = String.valueOf(sello1.getDrawable().getBounds());
                sello2Img = String.valueOf(sello2.getDrawable().getBounds());
                derRemolqueP1Img = String.valueOf(derRemolqueP1.getDrawable().getBounds());
                llantasDerEje2Img = String.valueOf(llantasDerEje2.getDrawable().getBounds());
                llantasDerEje1Img = String.valueOf(llantasDerEje1.getDrawable().getBounds());
                chasisTraseroDerImg = String.valueOf(chasisTraseroDer.getDrawable().getBounds());
                chasisFrontalDERImg = String.valueOf(chasisFrontalDER.getDrawable().getBounds());
                derRemolqueP2Img = String.valueOf(derRemolqueP2.getDrawable().getBounds());
                sello3Img = String.valueOf(sello3.getDrawable().getBounds());

                damage1Img = String.valueOf(damage1.getDrawable().getBounds());
                damage2Img = String.valueOf(damage2.getDrawable().getBounds());
                damage3Img = String.valueOf(damage3.getDrawable().getBounds());
                damage4Img = String.valueOf(damage4.getDrawable().getBounds());

                String ll1 =  llanta1SP.getSelectedItem().toString();
                String ll2 =  llanta2SP.getSelectedItem().toString();
                String ll3 =  llanta3SP.getSelectedItem().toString();
                String ll4 =  llanta4SP.getSelectedItem().toString();
                String ll5 =  llanta5SP.getSelectedItem().toString();
                String ll6 =  llanta6SP.getSelectedItem().toString();
                String ll7 =  llanta7SP.getSelectedItem().toString();
                String ll8 =  llanta8SP.getSelectedItem().toString();

                String sello1S = sello1ET.getText().toString();
                String sello2S = sello2ET.getText().toString();
                String sello3S = sello3ET.getText().toString();

                 lljumbo =0;


                int selloExtra ;

                if(checked1){
                    lljumbo = 1;
                }


                if(sello3Img.contains("128") && sello3S.length() > 0){
                    selloExtra = 1;
                }else {
                    selloExtra = 0;
                }


                // SI LAS LLANTAS SON JUMBO SOLO REVISA 4
                if(lljumbo == 1 ) {
                    if (tractorImg.contains("300") ||
                            noEconomicoImg.contains("300") ||
                            izqRemolqueP1Img.contains("300") ||
                            vinImg.contains("300") ||
                            chasisFrontalIzqImg.contains("300") ||
                            chasisTraseroIzqImg.contains("300") ||
                            llantasIzqEje1Img.contains("300") ||
                            llantasIzqEje2Img.contains("300") ||
                            izqRemolqueP2Img.contains("300") ||
                            puertasImg.contains("300") ||
                            placasImg.contains("300") ||
                            sello1Img.contains("300") ||
                            sello2Img.contains("300") ||
                            derRemolqueP1Img.contains("300") ||
                            llantasDerEje2Img.contains("300") ||
                            llantasDerEje1Img.contains("300") ||
                            chasisTraseroDerImg.contains("300") ||
                            chasisFrontalDERImg.contains("300") ||
                            derRemolqueP2Img.contains(" 300") ||
                            sello1S.length() == 0 ||
                            sello2S.length() == 0 ||
                            ll1 == "Sin Seleccionar" ||
                            ll2 == "Sin Seleccionar" ||
                            ll6 == "Sin Seleccionar" ||
                            ll5 == "Sin Seleccionar"

                    ) {
                        Toast.makeText(getBaseContext(), "Datos Incompletos", Toast.LENGTH_SHORT).show();
                    } else if (tractorImg.contains("128") &&
                            noEconomicoImg.contains("128") &&
                            izqRemolqueP1Img.contains("128") &&
                            vinImg.contains("128") &&
                            chasisFrontalIzqImg.contains("128") &&
                            chasisTraseroIzqImg.contains("128") &&
                            llantasIzqEje1Img.contains("128") &&
                            llantasIzqEje2Img.contains("128") &&
                            izqRemolqueP2Img.contains("128") &&
                            puertasImg.contains("128") &&
                            placasImg.contains("128") &&
                            sello1Img.contains("128") &&
                            sello2Img.contains("128") &&
                            derRemolqueP1Img.contains("128") &&
                            llantasDerEje2Img.contains("128") &&
                            llantasDerEje1Img.contains("128") &&
                            chasisTraseroDerImg.contains("128") &&
                            chasisFrontalDERImg.contains("128") &&
                            derRemolqueP2Img.contains("128") &&
                            sello1S.length() >= 0 &&
                            sello2S.length() >= 0 &&
                            ll1 != "Sin Seleccionar" &&
                            ll2 != "Sin Seleccionar" &&
                            ll6 != "Sin Seleccionar" &&
                            ll5 != "Sin Seleccionar") {


                        try {
                            Post6 post6 = new Post6(user, password, idRemolque, "0", mensaje, sello1S, sello2S, ll1,
                                    ll2, ll6, ll5, "", "", "", "", lljumbo, lljumbo2, selloExtra, sello3S, 0, "");


                            Call<List<CEnvio2>> callenvio2 = dxApi.getEnvio2(post6);

                            callenvio2.enqueue(new Callback<List<CEnvio2>>() {
                                @Override
                                public void onResponse(Call<List<CEnvio2>> call, Response<List<CEnvio2>> response) {

                                    if (!response.isSuccessful()) {
                                        Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                                    }

                                    List<CEnvio2> cEnvio2s = response.body();
                                        int tam = cEnvio2s.size();

                                    if(tam == 0){
                                        Toast.makeText(imgActivity.this, "Unidad Nueva !!", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(imgActivity.this, firmasActivity.class);
                                        i.putExtra("folio", folio);
                                        startActivity(i);
                                    }else{
                                        String incidencia  = cEnvio2s.get(0).getIncidencia();

                                         String A_sello1 = cEnvio2s.get(0).getA_sello1();
                                         String A_sello2 = cEnvio2s.get(0).getA_sello2();
                                         String A_llanta1 = cEnvio2s.get(0).getA_llanta1();
                                         String A_llanta2 = cEnvio2s.get(0).getA_llanta2();
                                         String A_llanta3 = cEnvio2s.get(0).getA_llanta3();
                                         String A_llanta4 = cEnvio2s.get(0).getA_llanta4();
                                         String A_llanta5 = cEnvio2s.get(0).getA_llanta5();
                                         String A_llanta6 = cEnvio2s.get(0).getA_llanta6();
                                         String A_llanta7 = cEnvio2s.get(0).getA_llanta7();
                                         String A_llanta8 = cEnvio2s.get(0).getA_llanta8();
                                         String A_llantajumbo = cEnvio2s.get(0).getA_llantajumbo();
                                         String A_selloExtra = cEnvio2s.get(0).getA_selloExtra();
                                         String A_sello3= cEnvio2s.get(0).getA_sello3();
                                        String A_tipoOperacion = cEnvio2s.get(0).getA_tipoOperacion();
                                        String A_estatusRemolque = cEnvio2s.get(0).getA_estatusRemolque();
                                        String A_tipoMovimiento = cEnvio2s.get(0).getA_tipoMovimiento();
                                        String A_comentario = cEnvio2s.get(0).getA_comentario();
                                        String A_patio = cEnvio2s.get(0).getA_patio();
                                        String A_fechaServidor = cEnvio2s.get(0).getA_fechaServidor();
                                        String A_operador = cEnvio2s.get(0).getA_operador();
                                        String A_unidad = cEnvio2s.get(0).getA_unidad();
                                        String A_transportista = cEnvio2s.get(0).getA_transportista();
                                        String A_linea = cEnvio2s.get(0).getA_linea();
                                        String A_remolque = cEnvio2s.get(0).getA_remolque();
                                        String A_usuario = cEnvio2s.get(0).getA_usuario();


                                         String P_sello1 = cEnvio2s.get(0).getP_sello1();
                                         String P_sello2 = cEnvio2s.get(0).getP_sello2();
                                         String P_llanta1 = cEnvio2s.get(0).getP_llanta1();
                                         String P_llanta2= cEnvio2s.get(0).getP_llanta2();
                                         String P_llanta3 = cEnvio2s.get(0).getP_llanta3();
                                         String P_llanta4= cEnvio2s.get(0).getP_llanta4();
                                         String P_llanta5 = cEnvio2s.get(0).getP_llanta5();
                                         String P_llanta6 = cEnvio2s.get(0).getP_llanta6();
                                         String P_llanta7 = cEnvio2s.get(0).getP_llanta7();
                                         String P_llanta8 = cEnvio2s.get(0).getP_llanta8();
                                         String P_llantajumbo = cEnvio2s.get(0).getP_llantajumbo();
                                         String P_selloExtra = cEnvio2s.get(0).getP_selloExtra();
                                         String P_sello3 = cEnvio2s.get(0).getP_sello3();
                                        String P_tipoOperacion = cEnvio2s.get(0).getP_tipoOperacion();
                                        String P_estatusRemolque = cEnvio2s.get(0).getP_estatusRemolque();
                                        String P_tipoMovimiento = cEnvio2s.get(0).getP_tipoMovimiento();
                                        String P_comentario = cEnvio2s.get(0).getP_comentario();
                                        String P_patio = cEnvio2s.get(0).getP_patio();
                                        String P_fechaServidor = cEnvio2s.get(0).getP_fechaServidor();
                                        String P_operador = cEnvio2s.get(0).getP_operador();
                                        String P_unidad = cEnvio2s.get(0).getP_unidad();
                                        String P_transportista = cEnvio2s.get(0).getP_transportista();
                                        String P_linea = cEnvio2s.get(0).getP_linea();
                                        String P_remolque = cEnvio2s.get(0).getP_remolque();
                                        String P_usuario = cEnvio2s.get(0).getP_usuario();

                                        if(incidencia.contains("SIUUU")){
                                            Intent i = new Intent(imgActivity.this, incidenciaActivity.class);
                                            i.putExtra("A_sello1", A_sello1);
                                            i.putExtra("A_sello2", A_sello2);
                                            i.putExtra("A_llanta1", A_llanta1);
                                            i.putExtra("A_llanta2", A_llanta2);
                                            i.putExtra("A_llanta3", A_llanta3);
                                            i.putExtra("A_llanta4", A_llanta4);
                                            i.putExtra("A_llanta5", A_llanta5);
                                            i.putExtra("A_llanta6", A_llanta6);
                                            i.putExtra("A_llanta7", A_llanta7);
                                            i.putExtra("A_llanta8", A_llanta8);
                                            i.putExtra("A_llantajumbo", A_llantajumbo);
                                            i.putExtra("A_selloExtra", A_selloExtra);
                                            i.putExtra("A_sello3", A_sello3);
                                            i.putExtra("A_tipoOperacion", A_tipoOperacion);
                                            i.putExtra("A_estatusRemolque", A_estatusRemolque);
                                            i.putExtra("A_tipoMovimiento", A_tipoMovimiento);
                                            i.putExtra("A_comentario", A_comentario);
                                            i.putExtra("A_patio", A_patio);
                                            i.putExtra("A_fechaServidor", A_fechaServidor);
                                            i.putExtra("A_operador", A_operador);
                                            i.putExtra("A_unidad", A_unidad);
                                            i.putExtra("A_transportista", A_transportista);
                                            i.putExtra("A_linea", A_linea);
                                            i.putExtra("A_remolque", A_remolque);
                                            i.putExtra("A_usuario", A_usuario);


                                            i.putExtra("P_sello1", P_sello1);
                                            i.putExtra("P_sello2", P_sello2);
                                            i.putExtra("P_llanta1", P_llanta1);
                                            i.putExtra("P_llanta2", P_llanta2);
                                            i.putExtra("P_llanta3", P_llanta3);
                                            i.putExtra("P_llanta4", P_llanta4);
                                            i.putExtra("P_llanta5", P_llanta5);
                                            i.putExtra("P_llanta6", P_llanta6);
                                            i.putExtra("P_llanta7", P_llanta7);
                                            i.putExtra("P_llanta8", P_llanta8);
                                            i.putExtra("P_llantajumbo", P_llantajumbo);
                                            i.putExtra("P_selloExtra", P_selloExtra);
                                            i.putExtra("P_sello3", P_sello3);
                                            i.putExtra("P_tipoOperacion", P_tipoOperacion);
                                            i.putExtra("P_estatusRemolque", P_estatusRemolque);
                                            i.putExtra("P_tipoMovimiento", P_tipoMovimiento);
                                            i.putExtra("P_comentario", P_comentario);
                                            i.putExtra("P_patio", P_patio);
                                            i.putExtra("P_fechaServidor", P_fechaServidor);
                                            i.putExtra("P_operador", P_operador);
                                            i.putExtra("P_unidad", P_unidad);
                                            i.putExtra("P_transportista", P_transportista);
                                            i.putExtra("P_linea", P_linea);
                                            i.putExtra("P_remolque", P_remolque);
                                            i.putExtra("P_usuario", P_usuario);

                                            i.putExtra("idIntercambio", mensaje);
                                            i.putExtra("folio", folio);
                                            startActivity(i);

                                        }else if (incidencia.contains("NO")){
                                            Intent i = new Intent(imgActivity.this, firmasActivity.class);
                                            i.putExtra("folio", folio);
                                            startActivity(i);

                                        }
                                    }

                                }

                                @Override
                                public void onFailure(Call<List<CEnvio2>> call, Throwable t) {
                                    Toast.makeText(imgActivity.this, "Error 404", Toast.LENGTH_LONG).show();
                                }
                            });

                        }catch (Exception e ){
                            Toast.makeText(imgActivity.this, "Error 404", Toast.LENGTH_LONG).show();
                        }


                    } else if (lljumbo == 0) {
                        if (tractorImg.contains("300") ||
                                noEconomicoImg.contains("300") ||
                                izqRemolqueP1Img.contains("300") ||
                                vinImg.contains("300") ||
                                chasisFrontalIzqImg.contains("300") ||
                                chasisTraseroIzqImg.contains("300") ||
                                llantasIzqEje1Img.contains("300") ||
                                llantasIzqEje2Img.contains("300") ||
                                izqRemolqueP2Img.contains("300") ||
                                puertasImg.contains("300") ||
                                placasImg.contains("300") ||
                                sello1Img.contains("300") ||
                                sello2Img.contains("300") ||
                                derRemolqueP1Img.contains("300") ||
                                llantasDerEje2Img.contains("300") ||
                                llantasDerEje1Img.contains("300") ||
                                chasisTraseroDerImg.contains("300") ||
                                chasisFrontalDERImg.contains("300") ||
                                derRemolqueP2Img.contains(" 300") ||
                                sello1S.length() == 0 ||
                                sello2S.length() == 0 ||
                                ll1 == "Sin Seleccionar" ||
                                ll2 == "Sin Seleccionar" ||
                                ll3 == "Sin Seleccionar" ||
                                ll4 == "Sin Seleccionar" ||
                                ll5 == "Sin Seleccionar" ||
                                ll6 == "Sin Seleccionar" ||
                                ll7 == "Sin Seleccionar" ||
                                ll8 == "Sin Seleccionar"

                        ) {
                            Toast.makeText(getBaseContext(), "Datos Incompletos", Toast.LENGTH_SHORT).show();
                        }   else if (tractorImg.contains("128") &&
                                noEconomicoImg.contains("128") &&
                                izqRemolqueP1Img.contains("128") &&
                                vinImg.contains("128") &&
                                chasisFrontalIzqImg.contains("128") &&
                                chasisTraseroIzqImg.contains("128") &&
                                llantasIzqEje1Img.contains("128") &&
                                llantasIzqEje2Img.contains("128") &&
                                izqRemolqueP2Img.contains("128") &&
                                puertasImg.contains("128") &&
                                placasImg.contains("128") &&
                                sello1Img.contains("128") &&
                                sello2Img.contains("128") &&
                                derRemolqueP1Img.contains("128") &&
                                llantasDerEje2Img.contains("128") &&
                                llantasDerEje1Img.contains("128") &&
                                chasisTraseroDerImg.contains("128") &&
                                chasisFrontalDERImg.contains("128") &&
                                derRemolqueP2Img.contains("128") &&
                                sello1S.length() >= 0 &&
                                sello2S.length() >= 0 &&
                                 ll1 != "Sin Seleccionar" &&
                                ll2 != "Sin Seleccionar" &&
                                ll3 != "Sin Seleccionar" &&
                                ll4 != "Sin Seleccionar" &&
                                ll5 != "Sin Seleccionar" &&
                                ll6 != "Sin Seleccionar" &&
                                ll7 != "Sin Seleccionar" &&
                                ll8 != "Sin Seleccionar"

                        ) {


                                Post6 post6 = new Post6(user, password, idRemolque, "0", mensaje, sello1S, sello2S, ll1,
                                        ll2, ll3, ll4, ll5, ll6, ll7, ll8, lljumbo, lljumbo2, selloExtra, sello3S, 0, "");

                                                String loko = "dfasfsdg";
                                                Call<List<CEnvio2>> callenvio2 = dxApi.getEnvio2(post6);

                                                callenvio2.enqueue(new Callback<List<CEnvio2>>() {
                                                    @Override
                                                    public void onResponse(Call<List<CEnvio2>> call, Response<List<CEnvio2>> response) {

                                                        if (!response.isSuccessful()) {
                                                            Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                                                        }

                                                        List<CEnvio2> cEnvio2s = response.body();
                                                        int tam = cEnvio2s.size();

                                                        if(tam == 0){
                                                            Toast.makeText(imgActivity.this, "Unidad Nueva !!", Toast.LENGTH_LONG).show();
                                                            Intent i = new Intent(imgActivity.this, firmasActivity.class);
                                                            i.putExtra("folio", folio);
                                                            startActivity(i);
                                                        }else{
                                                            String incidencia  = cEnvio2s.get(0).getIncidencia();

                                                            String A_sello1 = cEnvio2s.get(0).getA_sello1();
                                                            String A_sello2 = cEnvio2s.get(0).getA_sello2();
                                                            String A_llanta1 = cEnvio2s.get(0).getA_llanta1();
                                                            String A_llanta2 = cEnvio2s.get(0).getA_llanta2();
                                                            String A_llanta3 = cEnvio2s.get(0).getA_llanta3();
                                                            String A_llanta4 = cEnvio2s.get(0).getA_llanta4();
                                                            String A_llanta5 = cEnvio2s.get(0).getA_llanta5();
                                                            String A_llanta6 = cEnvio2s.get(0).getA_llanta6();
                                                            String A_llanta7 = cEnvio2s.get(0).getA_llanta7();
                                                            String A_llanta8 = cEnvio2s.get(0).getA_llanta8();
                                                            String A_llantajumbo = cEnvio2s.get(0).getA_llantajumbo();
                                                            String A_selloExtra = cEnvio2s.get(0).getA_selloExtra();
                                                            String A_sello3= cEnvio2s.get(0).getA_sello3();
                                                            String A_tipoOperacion = cEnvio2s.get(0).getA_tipoOperacion();
                                                            String A_estatusRemolque = cEnvio2s.get(0).getA_estatusRemolque();
                                                            String A_tipoMovimiento = cEnvio2s.get(0).getA_tipoMovimiento();
                                                            String A_comentario = cEnvio2s.get(0).getA_comentario();
                                                            String A_patio = cEnvio2s.get(0).getA_patio();
                                                            String A_fechaServidor = cEnvio2s.get(0).getA_fechaServidor();
                                                            String A_operador = cEnvio2s.get(0).getA_operador();
                                                            String A_unidad = cEnvio2s.get(0).getA_unidad();
                                                            String A_transportista = cEnvio2s.get(0).getA_transportista();
                                                            String A_linea = cEnvio2s.get(0).getA_linea();
                                                            String A_remolque = cEnvio2s.get(0).getA_remolque();
                                                            String A_usuario = cEnvio2s.get(0).getA_usuario();


                                                            String P_sello1 = cEnvio2s.get(0).getP_sello1();
                                                            String P_sello2 = cEnvio2s.get(0).getP_sello2();
                                                            String P_llanta1 = cEnvio2s.get(0).getP_llanta1();
                                                            String P_llanta2= cEnvio2s.get(0).getP_llanta2();
                                                            String P_llanta3 = cEnvio2s.get(0).getP_llanta3();
                                                            String P_llanta4= cEnvio2s.get(0).getP_llanta4();
                                                            String P_llanta5 = cEnvio2s.get(0).getP_llanta5();
                                                            String P_llanta6 = cEnvio2s.get(0).getP_llanta6();
                                                            String P_llanta7 = cEnvio2s.get(0).getP_llanta7();
                                                            String P_llanta8 = cEnvio2s.get(0).getP_llanta8();
                                                            String P_llantajumbo = cEnvio2s.get(0).getP_llantajumbo();
                                                            String P_selloExtra = cEnvio2s.get(0).getP_selloExtra();
                                                            String P_sello3 = cEnvio2s.get(0).getP_sello3();
                                                            String P_tipoOperacion = cEnvio2s.get(0).getP_tipoOperacion();
                                                            String P_estatusRemolque = cEnvio2s.get(0).getP_estatusRemolque();
                                                            String P_tipoMovimiento = cEnvio2s.get(0).getP_tipoMovimiento();
                                                            String P_comentario = cEnvio2s.get(0).getP_comentario();
                                                            String P_patio = cEnvio2s.get(0).getP_patio();
                                                            String P_fechaServidor = cEnvio2s.get(0).getP_fechaServidor();
                                                            String P_operador = cEnvio2s.get(0).getP_operador();
                                                            String P_unidad = cEnvio2s.get(0).getP_unidad();
                                                            String P_transportista = cEnvio2s.get(0).getP_transportista();
                                                            String P_linea = cEnvio2s.get(0).getP_linea();
                                                            String P_remolque = cEnvio2s.get(0).getP_remolque();
                                                            String P_usuario = cEnvio2s.get(0).getP_usuario();

                                                            if(incidencia.contains("SIUUU")){
                                                                Intent i = new Intent(imgActivity.this, incidenciaActivity.class);
                                                                i.putExtra("A_sello1", A_sello1);
                                                                i.putExtra("A_sello2", A_sello2);
                                                                i.putExtra("A_llanta1", A_llanta1);
                                                                i.putExtra("A_llanta2", A_llanta2);
                                                                i.putExtra("A_llanta3", A_llanta3);
                                                                i.putExtra("A_llanta4", A_llanta4);
                                                                i.putExtra("A_llanta5", A_llanta5);
                                                                i.putExtra("A_llanta6", A_llanta6);
                                                                i.putExtra("A_llanta7", A_llanta7);
                                                                i.putExtra("A_llanta8", A_llanta8);
                                                                i.putExtra("A_llantajumbo", A_llantajumbo);
                                                                i.putExtra("A_selloExtra", A_selloExtra);
                                                                i.putExtra("A_sello3", A_sello3);
                                                                i.putExtra("A_tipoOperacion", A_tipoOperacion);
                                                                i.putExtra("A_estatusRemolque", A_estatusRemolque);
                                                                i.putExtra("A_tipoMovimiento", A_tipoMovimiento);
                                                                i.putExtra("A_comentario", A_comentario);
                                                                i.putExtra("A_patio", A_patio);
                                                                i.putExtra("A_fechaServidor", A_fechaServidor);
                                                                i.putExtra("A_operador", A_operador);
                                                                i.putExtra("A_unidad", A_unidad);
                                                                i.putExtra("A_transportista", A_transportista);
                                                                i.putExtra("A_linea", A_linea);
                                                                i.putExtra("A_remolque", A_remolque);
                                                                i.putExtra("A_usuario", A_usuario);


                                                                i.putExtra("P_sello1", P_sello1);
                                                                i.putExtra("P_sello2", P_sello2);
                                                                i.putExtra("P_llanta1", P_llanta1);
                                                                i.putExtra("P_llanta2", P_llanta2);
                                                                i.putExtra("P_llanta3", P_llanta3);
                                                                i.putExtra("P_llanta4", P_llanta4);
                                                                i.putExtra("P_llanta5", P_llanta5);
                                                                i.putExtra("P_llanta6", P_llanta6);
                                                                i.putExtra("P_llanta7", P_llanta7);
                                                                i.putExtra("P_llanta8", P_llanta8);
                                                                i.putExtra("P_llantajumbo", P_llantajumbo);
                                                                i.putExtra("P_selloExtra", P_selloExtra);
                                                                i.putExtra("P_sello3", P_sello3);
                                                                i.putExtra("P_tipoOperacion", P_tipoOperacion);
                                                                i.putExtra("P_estatusRemolque", P_estatusRemolque);
                                                                i.putExtra("P_tipoMovimiento", P_tipoMovimiento);
                                                                i.putExtra("P_comentario", P_comentario);
                                                                i.putExtra("P_patio", P_patio);
                                                                i.putExtra("P_fechaServidor", P_fechaServidor);
                                                                i.putExtra("P_operador", P_operador);
                                                                i.putExtra("P_unidad", P_unidad);
                                                                i.putExtra("P_transportista", P_transportista);
                                                                i.putExtra("P_linea", P_linea);
                                                                i.putExtra("P_remolque", P_remolque);
                                                                i.putExtra("P_usuario", P_usuario);

                                                                i.putExtra("idIntercambio", mensaje);
                                                                i.putExtra("folio", folio);

                                                                startActivity(i);

                                                            }else if (incidencia.contains("NO")){
                                                                Intent i = new Intent(imgActivity.this, firmasActivity.class);
                                                                i.putExtra("folio", folio);
                                                                startActivity(i);
                                                            }
                                                        }

                                                    }

                                                    @Override
                                                    public void onFailure(Call<List<CEnvio2>> call, Throwable t) {
                                                        Toast.makeText(imgActivity.this, "Error 404", Toast.LENGTH_LONG).show();
                                                    }
                                                });





                        }
                    }
                }

            }
        });

        tractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tractor.setEnabled(false);
                imgClick("tractor" , REQUEST_TRACTOR);


            }
        });
        noEconomico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noEconomico.setEnabled(false);
                imgClick("noEconomico" , REQUEST_NoECONOMICO);
            }
        });
        izqRemolqueP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                izqRemolqueP1.setEnabled(false);
                imgClick("izqRemolqueP1" , REQUEST_IZQ_REMOLQUE_P1);
            }
        });
        vin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vin.setEnabled(false);
                imgClick("vin" , REQUEST_VIN);
            }
        });
        chasisFrontalIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chasisFrontalIzq.setEnabled(false);
                imgClick("chasisFrontalIzq" , REQUEST_CHASIS_FRONTAL_IZQ);
            }
        });
        chasisTraseroIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chasisTraseroIzq.setEnabled(false);
                imgClick("chasisTraseroIzq" , REQUEST_CHASIS_TRASERO_IZQ);
            }
        });
        llantasIzqEje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llantasIzqEje1.setEnabled(false);
                imgClick("llantasIzqEje1" , REQUEST_LLANTAS_IZQ_EJE1);
            }
        });
        llantasIzqEje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llantasIzqEje2.setEnabled(false);
                imgClick("llantasIzqEje2" , REQUEST__LLANTAS_IZQ_EJE2);
            }
        });
        izqRemolqueP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                izqRemolqueP2.setEnabled(false);
                imgClick("izqRemolqueP2" , REQUEST_IZQ_REMOLQUE_P2);
            }
        });
        puertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puertas.setEnabled(false);
                imgClick("puertas" , REQUEST_PUERTAS);
            }
        });
        placas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placas.setEnabled(false);
                imgClick("placas" , REQUEST_PLACAS);
            }
        });
        sello1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sello1.setEnabled(false);
                imgClick("sello1" , REQUEST_SELLO1);
            }
        });
        sello2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sello2.setEnabled(false);
                imgClick("sello2" , REQUEST_SELLO2);
            }
        });
        sello3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sello3.setEnabled(false);
                imgClick("sello3" , REQUEST_SELLO3);
            }
        });
        derRemolqueP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                derRemolqueP1.setEnabled(false);
                imgClick("derRemolqueP1" , REQUEST_DER_REMOLQUE_P1);
            }
        });
        llantasDerEje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llantasDerEje2.setEnabled(false);
                imgClick("llantasDerEje2" , REQUEST_LLANTAS_DER_EJE2);
            }
        });
        llantasDerEje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llantasDerEje1.setEnabled(false);
                imgClick("llantasDerEje1" , REQUEST_LLANTAS_DER_EJE1);
            }
        });
        chasisTraseroDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chasisTraseroDer.setEnabled(false);
                imgClick("chasisTraseroDer" , REQUEST_CHASIS_TRASERO_DER);
            }
        });
        chasisFrontalDER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chasisFrontalDER.setEnabled(false);
                imgClick("chasisFrontalDER" , REQUEST_CHASIS_FRONTAL_DER);
            }
        });
        derRemolqueP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                derRemolqueP2.setEnabled(false);
                imgClick("derRemolqueP2" , REQUEST_DER_REMOLQUE_P2);
            }
        });
        damage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage1.setEnabled(false);
                imgClick("damage1" , DAMAGE1);
            }
        });
        damage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage2.setEnabled(false);
                imgClick("damage2" , DAMAGE2);
            }
        });
        damage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage3.setEnabled(false);
                imgClick("damage3" , DAMAGE3);
            }
        });
        damage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage4.setEnabled(false);
                imgClick("damage4" , DAMAGE4);
            }
        });

        /*tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tarjeta.setEnabled(false);
                imgClick("tarjeta" , REQUEST_TARJETA);
            }
        });*/
    }

    private void imgClick (String photo , int code){

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File destPath = new File(getBaseContext().getExternalFilesDir(null).getAbsolutePath());
        //File destPath = new File(String.valueOf(getBaseContext().getCacheDir()));
        imageFileName = null ;
        imageFile = null ;
        photoURI = null;
        imageFileName = photo+ "-Folio"+folio ;

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

                    uploadServer(REQUEST_TRACTOR);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    tractor.setImageBitmap(thumbImage);
                }else {
                    tractor.setEnabled(true);
                }

                break;
            case REQUEST_NoECONOMICO:
                if (resultCode == Activity.RESULT_OK ) {


                    uploadServer(REQUEST_NoECONOMICO);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    noEconomico.setImageBitmap(thumbImage);
                } else {
                    noEconomico.setEnabled(true);
                }
                break;
            case REQUEST_IZQ_REMOLQUE_P1:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_IZQ_REMOLQUE_P1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);
                    izqRemolqueP1.setImageBitmap(thumbImage);


                }else {
                    izqRemolqueP1.setEnabled(true);
                }
                break;

            case REQUEST_VIN:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_VIN);
                    Bitmap  thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    vin.setImageBitmap(thumbImage);


                }else {
                    vin.setEnabled(true);
                }
                break;

            case REQUEST_CHASIS_FRONTAL_IZQ:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_CHASIS_FRONTAL_IZQ);
                    Bitmap  thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisFrontalIzq.setImageBitmap(thumbImage);


                }else {
                    chasisFrontalIzq.setEnabled(true);
                }
                break;

            case REQUEST_CHASIS_TRASERO_IZQ:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_CHASIS_TRASERO_IZQ);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisTraseroIzq.setImageBitmap(thumbImage);

                }else {
                    chasisTraseroIzq.setEnabled(true);
                }
                break;
            case REQUEST_LLANTAS_IZQ_EJE1:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_LLANTAS_IZQ_EJE1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasIzqEje1.setImageBitmap(thumbImage);



                }else {
                    llantasIzqEje1.setEnabled(true);
                }
                break;
            case REQUEST__LLANTAS_IZQ_EJE2:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST__LLANTAS_IZQ_EJE2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasIzqEje2.setImageBitmap(thumbImage);

                }else {
                    llantasIzqEje2.setEnabled(true);
                }
                break;
            case REQUEST_IZQ_REMOLQUE_P2:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_IZQ_REMOLQUE_P2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    izqRemolqueP2.setImageBitmap(thumbImage);

                }else {
                    izqRemolqueP2.setEnabled(true);
                }
                break;
            case REQUEST_PUERTAS:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_PUERTAS);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    puertas.setImageBitmap(thumbImage);

                }else {
                    puertas.setEnabled(true);
                }
                break;
            case REQUEST_PLACAS:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_PLACAS);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    placas.setImageBitmap(thumbImage);

                }else {
                    placas.setEnabled(true);
                }
                break;
            case REQUEST_SELLO1:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_SELLO1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    sello1.setImageBitmap(thumbImage);

                }else {
                    sello1.setEnabled(true);
                }
                break;
            case REQUEST_SELLO2:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_SELLO2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    sello2.setImageBitmap(thumbImage);

                }else {
                    sello2.setEnabled(true);
                }
                break;
            case REQUEST_DER_REMOLQUE_P1:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_DER_REMOLQUE_P1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    derRemolqueP1.setImageBitmap(thumbImage);

                }else {
                    derRemolqueP1.setEnabled(true);
                }
                break;
            case REQUEST_LLANTAS_DER_EJE2:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_LLANTAS_DER_EJE2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasDerEje2.setImageBitmap(thumbImage);

                }else {
                    llantasDerEje2.setEnabled(true);
                }
                break;
            case REQUEST_LLANTAS_DER_EJE1:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_LLANTAS_DER_EJE1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    llantasDerEje1.setImageBitmap(thumbImage);

                }else {
                    llantasDerEje1.setEnabled(true);
                }
                break;
            case REQUEST_CHASIS_TRASERO_DER:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(REQUEST_CHASIS_TRASERO_DER);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisTraseroDer.setImageBitmap(thumbImage);
                }else {
                    chasisTraseroDer.setEnabled(true);
                }
                break;
            case REQUEST_CHASIS_FRONTAL_DER:
                if (resultCode == Activity.RESULT_OK ) {
                    uploadServer(REQUEST_CHASIS_FRONTAL_DER);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    chasisFrontalDER.setImageBitmap(thumbImage);

                }else {
                    chasisFrontalDER.setEnabled(true);
                }
                break;
            case REQUEST_DER_REMOLQUE_P2:
                if (resultCode == Activity.RESULT_OK ) {
                    uploadServer(REQUEST_DER_REMOLQUE_P2);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    derRemolqueP2.setImageBitmap(thumbImage);

                }else {
                    derRemolqueP2.setEnabled(true);
                }
                break;
            case REQUEST_SELLO3:
                if (resultCode == Activity.RESULT_OK ) {
                    uploadServer(REQUEST_SELLO3);

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    sello3.setImageBitmap(thumbImage);

                }else {
                    sello3.setEnabled(true);
                }
                break;
            case DAMAGE1:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(DAMAGE1);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage1.setImageBitmap(thumbImage);

                }else {
                    damage1.setEnabled(true);
                }
                break;
            case DAMAGE2:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(DAMAGE2);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage2.setImageBitmap(thumbImage);

                }else {
                    damage2.setEnabled(true);
                }
                break;
            case DAMAGE3:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(DAMAGE3);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage3.setImageBitmap(thumbImage);

                }else {
                    damage3.setEnabled(true);
                }
                break;
            case DAMAGE4:
                if (resultCode == Activity.RESULT_OK ) {

                    uploadServer(DAMAGE4);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    damage4.setImageBitmap(thumbImage);

                }else {
                    damage4.setEnabled(true);
                }
                break;





        }
        super.onActivityResult(requestCode, resultCode, resultData);
    }

    private void handleSignInResult(Intent result) {
        /*GoogleSignIn.getSignedInAccountFromIntent(result)
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
                                    .setApplicationName("DXIntercambio")
                                    .build();

                    // The DriveServiceHelper encapsulates all REST API and SAF functionality.
                    // Its instantiation is required before handling any onClick actions.
                    //mDriveServiceHelper = new DriveServiceHelper(googleDriveService);
                })
                .addOnFailureListener(exception -> Log.e(TAG, "Unable to sign in.", exception));*/

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

    @Override
    public void onBackPressed() {

    }

    private void uploadServer (int codigo){


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String path = imageFile.getPath();
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                    String encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.4.87:80/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    dxApi = retrofit.create(DxApi.class);

                    Post5 post5 = new Post5(user,password,imageFileName,encodedImage,folio);
                    Call<String> callImg = dxApi.getImg(post5);

                    callImg.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            if(!response.isSuccessful()){
                                Toast.makeText(imgActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                            }
                            String cEnvios = String.valueOf(response);


                            Toast.makeText(getBaseContext(),"Enviado",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                            Toast.makeText(getBaseContext(),"Error 400Img"+ t.getMessage() ,Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {

                    e.getMessage();

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getBaseContext(),"Error al enviar imagen",Toast.LENGTH_SHORT).show();


                            switch (codigo){
                                case REQUEST_TRACTOR:

                                    tractor.setImageBitmap(null);
                                    tractor.setBackgroundColor(Color.parseColor("#074EAB"));
                                    tractor.setEnabled(true);

                                    break;

                                case REQUEST_NoECONOMICO:

                                    noEconomico.setImageBitmap(null);
                                    noEconomico.setBackgroundColor(Color.parseColor("#074EAB"));
                                    noEconomico.setEnabled(true);

                                    break;
                                case REQUEST_IZQ_REMOLQUE_P1:


                                    izqRemolqueP1.setImageBitmap(null);
                                    izqRemolqueP1.setBackgroundColor(Color.parseColor("#074EAB"));
                                    izqRemolqueP1.setEnabled(true);



                                    break;
                                case REQUEST_VIN:

                                    vin.setImageBitmap(null);
                                    vin.setBackgroundColor(Color.parseColor("#074EAB"));
                                    vin.setEnabled(true);


                                    break;
                                case REQUEST_CHASIS_FRONTAL_IZQ:


                                    chasisFrontalIzq.setImageBitmap(null);
                                    chasisFrontalIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                                    chasisFrontalIzq.setEnabled(true);

                                    break;
                                case REQUEST_CHASIS_TRASERO_IZQ:


                                    chasisTraseroIzq.setImageBitmap(null);
                                    chasisTraseroIzq.setBackgroundColor(Color.parseColor("#074EAB"));
                                    chasisTraseroIzq.setEnabled(true);

                                    break;
                                case REQUEST_LLANTAS_IZQ_EJE1:


                                    llantasIzqEje1.setImageBitmap(null);
                                    llantasIzqEje1.setBackgroundColor(Color.parseColor("#074EAB"));
                                    llantasIzqEje1.setEnabled(true);



                                    break;
                                case REQUEST__LLANTAS_IZQ_EJE2:

                                    llantasIzqEje2.setImageBitmap(null);
                                    llantasIzqEje2.setBackgroundColor(Color.parseColor("#074EAB"));
                                    llantasIzqEje2.setEnabled(true);

                                    break;
                                case REQUEST_IZQ_REMOLQUE_P2:


                                    izqRemolqueP2.setImageBitmap(null);
                                    izqRemolqueP2.setBackgroundColor(Color.parseColor("#074EAB"));
                                    izqRemolqueP2.setEnabled(true);



                                    break;
                                case REQUEST_PUERTAS:


                                    puertas.setImageBitmap(null);
                                    puertas.setBackgroundColor(Color.parseColor("#074EAB"));
                                    puertas.setEnabled(true);

                                    break;
                                case REQUEST_PLACAS:

                                    placas.setImageBitmap(null);
                                    placas.setBackgroundColor(Color.parseColor("#074EAB"));
                                    placas.setEnabled(true);

                                    break;
                                case REQUEST_SELLO1:


                                    sello1.setImageBitmap(null);
                                    sello1.setBackgroundColor(Color.parseColor("#074EAB"));
                                    sello1.setEnabled(true);



                                    break;
                                case REQUEST_SELLO2:


                                    sello2.setImageBitmap(null);
                                    sello2.setBackgroundColor(Color.parseColor("#074EAB"));
                                    sello2.setEnabled(true);

                                    break;
                                case REQUEST_DER_REMOLQUE_P1:



                                    derRemolqueP1.setImageBitmap(null);
                                    derRemolqueP1.setBackgroundColor(Color.parseColor("#074EAB"));
                                    derRemolqueP1.setEnabled(true);

                                    break;
                                case REQUEST_LLANTAS_DER_EJE2:


                                    llantasDerEje2.setImageBitmap(null);
                                    llantasDerEje2.setBackgroundColor(Color.parseColor("#074EAB"));
                                    llantasDerEje2.setEnabled(true);

                                    break;
                                case REQUEST_LLANTAS_DER_EJE1:


                                    llantasDerEje1.setImageBitmap(null);
                                    llantasDerEje1.setBackgroundColor(Color.parseColor("#074EAB"));
                                    llantasDerEje1.setEnabled(true);

                                    break;
                                case REQUEST_CHASIS_TRASERO_DER:


                                    chasisTraseroDer.setImageBitmap(null);
                                    chasisTraseroDer.setBackgroundColor(Color.parseColor("#074EAB"));
                                    chasisTraseroDer.setEnabled(true);

                                    break;
                                case REQUEST_CHASIS_FRONTAL_DER:


                                    chasisFrontalDER.setImageBitmap(null);
                                    chasisFrontalDER.setBackgroundColor(Color.parseColor("#074EAB"));
                                    chasisFrontalDER.setEnabled(true);

                                    break;
                                case REQUEST_DER_REMOLQUE_P2:


                                    derRemolqueP2.setImageBitmap(null);
                                    derRemolqueP2.setBackgroundColor(Color.parseColor("#074EAB"));
                                    derRemolqueP2.setEnabled(true);

                                    break;
                                case REQUEST_SELLO3:


                                    sello3.setImageBitmap(null);
                                    sello3.setBackgroundColor(Color.parseColor("#074EAB"));
                                    sello3.setEnabled(true);

                                    break;
                                case DAMAGE1:


                                    damage1.setImageBitmap(null);
                                    damage1.setBackgroundColor(Color.parseColor("#074EAB"));
                                    damage1.setEnabled(true);

                                    break;
                                case DAMAGE2:


                                    damage2.setImageBitmap(null);
                                    damage2.setBackgroundColor(Color.parseColor("#074EAB"));
                                    damage2.setEnabled(true);

                                    break;
                                case DAMAGE3:


                                    damage3.setImageBitmap(null);
                                    damage3.setBackgroundColor(Color.parseColor("#074EAB"));
                                    damage3.setEnabled(true);

                                    break;
                                case DAMAGE4:


                                    damage4.setImageBitmap(null);
                                    damage4.setBackgroundColor(Color.parseColor("#074EAB"));
                                    damage4.setEnabled(true);

                                    break;



                            }


                        }
                    });
                }



            }
        });
        t.start();



    }
}
