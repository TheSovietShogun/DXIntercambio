package com.dx.dxintercambio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.Menu;

import com.kyanogen.signatureview.SignatureView;

public class envioActivity extends AppCompatActivity {

    private EditText registradoPor  , comentarios ,unidadO ,remolqueO;
    private Spinner tipoOperacionSP,  transportista , linea , estatusCaja , tipoMovimiento;
    private AutoCompleteTextView unidad,noRemolque,operador;
    private ImageView licencia;
    private Button enviar;
    private String [] tipoOpeArr ;
    private String [] estatusArr ;
    private String [] vacioArr ;
    private String [] vacioArr2 ;
    private String [] tipoMovArr;
    private DxApi dxApi;
    private CheckBox checkUnidad , checkRemolque;
    private String user , password ,usuario ,fechaHora, imageFileName, hora,folio,licenciaImg,unidad2,remolque2, ip , patio;
    private int idOperador, idUnidad,idRemolque,mensaje,alfa,bravo,charlie,delta,foxtrop;
    private  AlertDialog alert ;
    private File imageFile ;
    private Uri photoURI;
    private static final int REQUEST_LICENCIA = 888;
    private final int THUMBSIZE = 128;
    private String[] PERMISSIONS = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.WAKE_LOCK,
            android.Manifest.permission.RECEIVE_BOOT_COMPLETED,
            android.Manifest.permission.VIBRATE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.FOREGROUND_SERVICE
    };
    private int PERMISSION_ALL = 1;
    private int azul = Color.parseColor("#074EAB");
    private boolean checked1;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Desea cerrar sesion ?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {


                            SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("user", "");
                            editor.putString("pass", "");
                            editor.commit();

                            Intent i = new Intent(envioActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_envio);

        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);
        ip = preferences.getString("Aip","");
        patio = preferences.getString("Apatio","");
        user = preferences.getString("user","");
        password = preferences.getString("pass","");
        usuario = getIntent().getStringExtra("idUsuario");


        tipoOperacionSP = (Spinner) findViewById(R.id.tipoOp);
        registradoPor = (EditText)findViewById(R.id.registradoPor);
        transportista = (Spinner) findViewById(R.id.nombreTranspo);
        operador = (AutoCompleteTextView)findViewById(R.id.nombreOp);
        unidad = (AutoCompleteTextView)findViewById(R.id.numeroUnidad);
        noRemolque = (AutoCompleteTextView)findViewById(R.id.numeroRemolque);
        linea = (Spinner) findViewById(R.id.nombreLinea);
        estatusCaja = (Spinner) findViewById(R.id.spinner3);
        comentarios = (EditText)findViewById(R.id.comentario1);
        enviar = (Button) findViewById(R.id.btnDatos);
        licencia = (ImageView) findViewById(R.id.licenciaFoto);
        tipoMovimiento = (Spinner) findViewById(R.id.tipoMov);
        checkUnidad = (CheckBox) findViewById(R.id.checkU);
        checkRemolque = (CheckBox) findViewById(R.id.checkR);
        unidadO = (EditText) findViewById(R.id.otroUnidad) ;
        remolqueO = (EditText) findViewById(R.id.nRemolqueOtro) ;

        registradoPor.setText(user);
        registradoPor.setEnabled(false);


        vacioArr = new String[]{"Sin Seleccionar"};
        vacioArr2 = new String[]{"Sin Seleccionar"};
        tipoOpeArr = new String[]{"Sin Seleccionar","Entrada", "Salida"};
        estatusArr = new String[]{"Sin Seleccionar","Cargado", "Vacio","Racks"};
        tipoMovArr = new String[]{"Sin Seleccionar","Exportacion","Importacion"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mspinner_item, tipoOpeArr);
        tipoOperacionSP.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusArr);
        estatusCaja.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.mspinner_item, vacioArr);
        unidad.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.mspinner_item, vacioArr2);
        noRemolque.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, R.layout.mspinner_item, tipoMovArr);
        tipoMovimiento.setAdapter(adapter5);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dxApi = retrofit.create(DxApi.class);
        Post post = new Post(user,password);


        //FLOTA(Transportista)
        Call<List<CFlota>> callFlota = dxApi.getFlota(post);

        callFlota.enqueue(new Callback<List<CFlota>>() {
            @Override
            public void onResponse(Call<List<CFlota>> call, Response<List<CFlota>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(envioActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                }

                List<CFlota> cFlotas = response.body();

                CUnidad cUnidad11 = new CUnidad(0,"Sin Seleccionar");
                List<CUnidad> cUnidads =  new ArrayList<CUnidad>();
                cUnidads.add(0,cUnidad11);
                ArrayAdapter<CUnidad> adapterU = new ArrayAdapter<CUnidad>(envioActivity.this , R.layout.mspinner_item, cUnidads);
                unidad.setAdapter(adapterU);


                CFlota cFlota12 = new CFlota("-1","Otro");
                CFlota cFlota11 = new CFlota("0","Sin Seleccionar");


                cFlotas.add(0,cFlota11);
                cFlotas.add(1,cFlota12);


                ArrayAdapter<CFlota> adapter3 = new ArrayAdapter<CFlota>(envioActivity.this , R.layout.mspinner_item, cFlotas);
                transportista.setAdapter(adapter3);


            }

            @Override
            public void onFailure(Call<List<CFlota>> call, Throwable t) {
                Toast.makeText(envioActivity.this, "Error 404F", Toast.LENGTH_LONG).show();
            }
        });


        //UNIDAD
        transportista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                CFlota cFlota = (CFlota) transportista.getSelectedItem();

                String idFlota =  cFlota.getId();
                String nombreS = cFlota.getNombre();


                if(nombreS == "Otro" || nombreS == "Sin Seleccionar"){
                    unidad.setEnabled(false);
                    unidad.setText("");
                    idUnidad = 0;

                    checkUnidad.setVisibility(View.INVISIBLE);
                    checkUnidad.setChecked(false);
                    unidadO.setText("");
                    unidadO.setVisibility(View.INVISIBLE);
                    unidad2 = "0";

                    if(nombreS == "Otro" ){
                        Toast.makeText(envioActivity.this, "ESCRIBIR EN COMENTARIO : " +
                                "\n-Nombre del Transportista" +
                                "\n-Nombre Completo de Operador" +
                                "\n-Numero Economico de la Unidad", Toast.LENGTH_LONG).show();
                    }

                }else {
                    unidad.setEnabled(true);
                    unidad.setText("");
                    idUnidad = 0;
                    checkUnidad.setVisibility(View.VISIBLE);


                    Post2 post2 = new Post2(user,password,idFlota);

                    Call<List<CUnidad>> callUni = dxApi.getUnidad(post2);

                    callUni.enqueue(new Callback<List<CUnidad>>() {
                        @Override
                        public void onResponse(Call<List<CUnidad>> call, Response<List<CUnidad>> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(envioActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                            }
                            List<CUnidad> cUnidads = response.body();

                            CUnidad cUnidad11 = new CUnidad(0,"Sin Seleccionar");
                            cUnidads.add(0,cUnidad11);

                            ArrayAdapter<CUnidad> adapterU = new ArrayAdapter<CUnidad>(envioActivity.this , R.layout.mspinner_item, cUnidads);
                            unidad.setAdapter(adapterU);

                        }

                        @Override
                        public void onFailure(Call<List<CUnidad>> call, Throwable t) {
                            Toast.makeText(envioActivity.this, "Error 404U", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //LINEA
        Call<List<CLinea>> call = dxApi.getLinea(post);

        call.enqueue(new Callback<List<CLinea>>() {
            @Override
            public void onResponse(Call<List<CLinea>> call, Response<List<CLinea>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(envioActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                }

                List<CLinea> cLineas = response.body();

                CRemolque cRemolque = new CRemolque(0,"Sin Seleccionar");
                List<CRemolque> cRemolques =  new ArrayList<CRemolque>();
                cRemolques.add(0,cRemolque);
                ArrayAdapter<CRemolque> adapterU = new ArrayAdapter<CRemolque>(envioActivity.this , R.layout.mspinner_item, cRemolques);
                noRemolque.setAdapter(adapterU);

                CLinea cLinea12 = new CLinea("-1","Otro");
                CLinea cLinea1 = new CLinea("0","Sin Seleccionar");

                cLineas.add(0,cLinea1);
                cLineas.add(1,cLinea12);



                ArrayAdapter<CLinea> adapter = new ArrayAdapter<CLinea>(envioActivity.this , R.layout.mspinner_item, cLineas);
                // adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                linea.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CLinea>> call, Throwable t) {
                Toast.makeText(envioActivity.this, "Error 404L", Toast.LENGTH_LONG).show();
            }
        });


        //REMOLQUE
        linea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CLinea cLinea = (CLinea) linea.getSelectedItem();

                String idLinea =  cLinea.getId();
                String nombreS = cLinea.getNombreLinea();

                if(nombreS == "Otro" || nombreS == "Sin Seleccionar"){
                    noRemolque.setEnabled(false);
                    noRemolque.setText("");
                    idRemolque = 0;

                    checkRemolque.setVisibility(View.INVISIBLE);
                    checkRemolque.setChecked(false);
                    remolqueO.setText("");
                    remolqueO.setVisibility(View.INVISIBLE);
                    remolque2 = "0";

                    if(nombreS == "Otro" ){
                        Toast.makeText(envioActivity.this, "ESCRIBIR EN COMENTARIO : \n-Nombre de la Linea\n-Numero Economico del Remolque", Toast.LENGTH_LONG).show();
                    }

                }else {
                    noRemolque.setEnabled(true);
                    noRemolque.setText("");
                    idRemolque = 0;
                    checkRemolque.setVisibility(View.VISIBLE);

                    Post3 post3 = new Post3(user, password, idLinea);

                    Call<List<CRemolque>> callremo = dxApi.getRemolque(post3);

                    callremo.enqueue(new Callback<List<CRemolque>>() {
                        @Override
                        public void onResponse(Call<List<CRemolque>> call, Response<List<CRemolque>> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(envioActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                            }

                            List<CRemolque> cRemolques = response.body();
                            CRemolque cRemolque = new CRemolque(0,"Sin Seleccionar");

                            cRemolques.add(0,cRemolque);

                            ArrayAdapter<CRemolque> adapterR = new ArrayAdapter<CRemolque>(envioActivity.this, R.layout.mspinner_item, cRemolques);
                            // adapterR.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                            noRemolque.setAdapter(adapterR);
                            //noRemolque.setText(null);

                        }

                        @Override
                        public void onFailure(Call<List<CRemolque>> call, Throwable t) {
                            Toast.makeText(envioActivity.this, "Error 404R", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //OPERADOR
        Call<List<COperador>> callOpe = dxApi.getOperador(post);

        callOpe.enqueue(new Callback<List<COperador>>() {
            @Override
            public void onResponse(Call<List<COperador>> call, Response<List<COperador>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(envioActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                }

                List<COperador> cOperadors = response.body();

                ArrayAdapter<COperador> adapter2 = new ArrayAdapter<COperador>(envioActivity.this , R.layout.mspinner_item, cOperadors);
                operador.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<List<COperador>> call, Throwable t) {
                Toast.makeText(envioActivity.this, "Error 404Op", Toast.LENGTH_LONG).show();
            }
        });


        checkUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                   unidad.setEnabled(false);
                   unidad.setText("");
                   unidadO.setText("");
                   idUnidad = 0;
                   unidadO.setVisibility(View.VISIBLE);
                   unidad2 = "1";

                }else{
                    unidad.setEnabled(true);
                    idUnidad = 0;
                    unidad.setText("");
                    unidadO.setText("");
                    unidadO.setVisibility(View.INVISIBLE);
                    unidad2 = "0";
                }
            }
        });

        checkRemolque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the button now checked?
                checked1 = ((CheckBox) view).isChecked();

                if (checked1) {
                    noRemolque.setEnabled(false);
                    noRemolque.setText("");
                    remolqueO.setText("");
                    idRemolque = 0;
                    remolqueO.setVisibility(View.VISIBLE);
                    remolque2 = "1";

                }else{
                    noRemolque.setEnabled(true);
                    idRemolque = 0;
                    noRemolque.setText("");
                    remolqueO.setText("");
                    remolqueO.setVisibility(View.INVISIBLE);
                    remolque2 = "0";
                }
            }
        });


        operador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                COperador cOperador = (COperador) operador.getAdapter().getItem(i);
                idOperador = Integer.parseInt(cOperador.getIdOperador());
            }
        });


        unidad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CUnidad cUnidad = (CUnidad) unidad.getAdapter().getItem(i);
                idUnidad = Integer.parseInt(String.valueOf(cUnidad.getId()));
            }
        });

        noRemolque.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CRemolque cRemolque = (CRemolque) noRemolque.getAdapter().getItem(i);
                idRemolque = Integer.parseInt(String.valueOf(cRemolque.getId()));
            }
        });

        licencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                licencia.setEnabled(false);
                imgClick("tractor" , REQUEST_LICENCIA);
            }
        });

        //btn
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://"+ip+"/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                dxApi = retrofit.create(DxApi.class);

                fechaHora = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date());
                licenciaImg =  String.valueOf(licencia.getDrawable().getBounds());
                String tipoOpe = tipoOperacionSP.getSelectedItem().toString();
                String status  =  estatusCaja.getSelectedItem().toString();
                String tipmov = tipoMovimiento.getSelectedItem().toString();
                int tipoOperacion = 3;
                int estatus =3;
                int movimiento = 3;
                String unidadStr = "";
                String remolqueStr = "";

                switch (tipoOpe){
                    case "Salida" :
                        tipoOperacion = 1;
                        break;
                    case "Entrada" :
                        tipoOperacion = 0;
                        break;
                    case "Sin Seleccionar" :
                        tipoOperacion = 3;
                        break;
                }

                switch (status){
                    case "Cargado" :
                        estatus = 1;
                        break;
                    case "Vacio" :
                        estatus = 0;
                        break;
                    case "Racks" :
                        estatus = 2;
                        break;
                    case "Sin Seleccionar" :
                        estatus = 3;
                        break;
                }

                switch (tipmov){
                    case "Importacion" :
                        movimiento = 1;
                        break;
                    case "Exportacion" :
                        movimiento = 0;
                        break;
                    case "Sin Seleccionar" :
                        movimiento = 3;
                        break;
                }


                //GET FLOTA
                CFlota cFlota = (CFlota) transportista.getSelectedItem();
                int idTransportista = Integer.parseInt(cFlota.getId());

                //GET LINEA
                CLinea cLinea = (CLinea)linea.getSelectedItem();
                int idLinea = Integer.parseInt(cLinea.getId());

                //GET COMENTARIO
                String comentario = comentarios.getText().toString();

                //GET USUARIO
                int idUsuario = Integer.parseInt(usuario);


               String comentarioCancel = "";
               int idIntercambio =0 ;


                        if(idTransportista == 0 || idLinea == 0 ){
                            Toast.makeText(envioActivity.this, "Campos vacios existente", Toast.LENGTH_LONG).show();
                        }else if (idTransportista == -1 && idLinea == -1 ){

                                if(tipoOperacion == 3 || estatus == 3 || comentario.isEmpty() || movimiento == 3 || !licenciaImg.contains("128"))
                                {
                                    Toast.makeText(envioActivity.this, "Campos vacios existente", Toast.LENGTH_LONG).show();
                                } else {

                                    String imageFilePath = imageFile.getPath();
                                    Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                                    byte [] byte_arr = stream.toByteArray();
                                    String encodedImage = new String(Base64.encode(byte_arr,2));


                                    folio = "";
                                    hora =  new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                                    folio = ("RA"+hora+idRemolque);


                                    envioSiuuu(user,password,fechaHora,tipoOperacion,idUsuario,0,idTransportista,idOperador,idUnidad,
                                            idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,0,movimiento,patio,encodedImage,
                                            "licencia"+folio,"","");

                                    }



                        //SI NO SON NULOS LOS CAMPOS FORZOSOS
                    } else if (idTransportista != -1 && idLinea != -1 ){

                                 if(unidad2 == "1" || remolque2 == "1"){

                                         unidadStr= unidadO.getText().toString();
                                         remolqueStr= remolqueO.getText().toString();

                                            if(unidad2 == "1" && remolque2 == "1"){
                                                if(remolqueStr.isEmpty() || unidadStr.isEmpty() ||idOperador == 0|| estatus == 3 || tipoOperacion == 3 || movimiento == 3 || !licenciaImg.contains("128")){
                                                    Toast.makeText(envioActivity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                                                }else {
                                                    String imageFilePath = imageFile.getPath();
                                                    Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
                                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                                                    byte [] byte_arr = stream.toByteArray();
                                                    String encodedImage = new String(Base64.encode(byte_arr,2));

                                                    folio = "";
                                                    hora =  new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                                                    folio = ("RA"+hora+idRemolque);

                                                    envioSiuuu(user,password,fechaHora,tipoOperacion,idUsuario,0,idTransportista,idOperador,idUnidad,
                                                            idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,0,movimiento,patio,encodedImage,
                                                            "licencia"+folio,unidadStr,remolqueStr);

                                                }
                                            }else if(unidad2 == "1" && remolque2 == "0"){
                                                if( unidadStr.isEmpty() || idRemolque == 0 ||idOperador == 0|| estatus == 3 || tipoOperacion == 3 || movimiento == 3 || !licenciaImg.contains("128")){
                                                    Toast.makeText(envioActivity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                                                }else {
                                                    String imageFilePath = imageFile.getPath();
                                                    Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
                                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                                                    byte [] byte_arr = stream.toByteArray();
                                                    String encodedImage = new String(Base64.encode(byte_arr,2));

                                                    folio = "";
                                                    hora =  new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                                                    folio = ("RA"+hora+idRemolque);

                                                    envioSiuuu(user,password,fechaHora,tipoOperacion,idUsuario,0,idTransportista,idOperador,idUnidad,
                                                            idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,0,movimiento,patio,encodedImage,
                                                            "licencia"+folio,unidadStr,remolqueStr);

                                                }

                                            }else if(unidad2 == "0" && remolque2 == "1"){
                                                if(idUnidad == 0 || remolqueStr.isEmpty()  ||idOperador == 0|| estatus == 3 || tipoOperacion == 3 || movimiento == 3 || !licenciaImg.contains("128")){
                                                    Toast.makeText(envioActivity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                                                }else {
                                                    String imageFilePath = imageFile.getPath();
                                                    Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
                                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                                                    byte [] byte_arr = stream.toByteArray();
                                                    String encodedImage = new String(Base64.encode(byte_arr,2));

                                                    folio = "";
                                                    hora =  new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                                                    folio = ("RA"+hora+idRemolque);

                                                    envioSiuuu(user,password,fechaHora,tipoOperacion,idUsuario,0,idTransportista,idOperador,idUnidad,
                                                            idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,0,movimiento,patio,encodedImage,
                                                            "licencia"+folio,unidadStr,remolqueStr);

                                                } }
                                 }else if (unidad2 == "0" && remolque2 == "0"){

                                     if(  idUnidad == 0 || idRemolque == 0 ||idOperador == 0|| estatus == 3 || tipoOperacion == 3 || movimiento == 3 || !licenciaImg.contains("128")){
                                         Toast.makeText(envioActivity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                                     }else {
                                         String imageFilePath = imageFile.getPath();
                                         Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
                                         ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                         bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                                         byte [] byte_arr = stream.toByteArray();
                                         String encodedImage = new String(Base64.encode(byte_arr,2));

                                         folio = "";
                                         hora =  new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                                         folio = ("RA"+hora+idRemolque);

                                         envioSiuuu(user,password,fechaHora,tipoOperacion,idUsuario,0,idTransportista,idOperador,idUnidad,
                                                 idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,0,movimiento,patio,encodedImage,
                                                 "licencia"+folio,"","");
                                                 }
                                             }


                    }else {


                                            String unidadStr2= unidadO.getText().toString();
                                            String remolqueStr2= remolqueO.getText().toString();

                                        alfa = 0;
                                        bravo = 0;
                                        charlie = 0;
                                        delta = 0;
                                        foxtrop = 0;


                                             if (idTransportista == -1){
                                                 bravo = 0;
                                             } else  if ( idTransportista != 0) {
                                                if (idUnidad == 0 && unidad2 == "0" ) {
                                                    bravo = 400;
                                                }else if(idUnidad == 0 && unidad2 == "1" ){
                                                    if(unidadStr2.isEmpty()){
                                                        bravo = 400;
                                                    }
                                                }
                                            }


                                         if (idLinea == -1){
                                             bravo = 0;
                                         }else if ( idLinea != 0) {
                                             if (idRemolque == 0 && remolque2 == "0" ) {
                                                 charlie = 400;
                                             }else if(idRemolque == 0 && remolque2 == "1" ){
                                                 if(remolqueStr2.isEmpty()){
                                                     charlie = 400;
                                                 }
                                             }
                                         }



                                        if (tipoOperacion == 3 || estatus == 3 || comentario.isEmpty() || movimiento == 3 || !licenciaImg.contains("128") ) {
                                            delta = 400;
                                        }

                                        if( bravo == 400 || charlie == 400 || delta == 400  ){
                                            Toast.makeText(envioActivity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                                        }else if( bravo == 0 || charlie == 0 || delta == 0 ){

                                            String imageFilePath = imageFile.getPath();
                                            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
                                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                                            byte [] byte_arr = stream.toByteArray();
                                            String encodedImage = new String(Base64.encode(byte_arr,2));

                                            folio = "";
                                            hora =  new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                                            folio = ("RA"+hora+idRemolque);

                                            envioSiuuu(user,password,fechaHora,tipoOperacion,idUsuario,0,idTransportista,idOperador,idUnidad,
                                                    idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,0,movimiento,patio,encodedImage,
                                                    "licencia"+folio,unidadStr2,remolqueStr2);

                                        }


                    }


            }
        });


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    File destPath = new File(getBaseContext().getExternalFilesDir(null).getAbsolutePath());
                    String path = destPath.toString();
                    File directory = new File(path);
                    File[] files = directory.listFiles();


                    for(File file : files) {
                        Log.d("Files", "FileName:" + file.getName());
                        file.delete();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();



    }


    @Override
    protected void onStart() {

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        super.onStart();

    }

    @Override
    public void onBackPressed() {

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void imgClick (String photo , int code){

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File destPath = new File(getBaseContext().getExternalFilesDir(null).getAbsolutePath());
        imageFileName = null ;
        imageFile = null ;
        photoURI = null ;
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
                if (resultCode == Activity.RESULT_OK ) {

                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(imageFile.getAbsolutePath()),
                            THUMBSIZE,
                            THUMBSIZE);

                    licencia.setImageBitmap(thumbImage);
                }else {
                    licencia.setEnabled(true);
                }
        super.onActivityResult(requestCode, resultCode, resultData);
    }


    private void envioSiuuu (String Uuser , String Upassword, String UfechaHora ,
                        int UtipoOperacion , int UidUsuario, int UidSQL , int UidTransportista ,int UidOperador , int UidUnidad,
                        int UidRemolque , int UidLinea , int UestatusRemolque , String Ucomentario , String Ufolio, String UcomentarioCancel , int UidIntercambio ,
                        int UtipoMovimiento , String Upatio , String Uimg , String UimgNombre , String UotroUnidad, String UotroRemolque){



        Post4 post4 = new Post4(Uuser,Upassword,UfechaHora,UtipoOperacion,UidUsuario,UidSQL,UidTransportista,UidOperador,UidUnidad,
                UidRemolque,UidLinea,UestatusRemolque,Ucomentario,Ufolio,UcomentarioCancel,UidIntercambio,UtipoMovimiento,Upatio,Uimg,
                "licencia"+Ufolio,UotroUnidad,UotroRemolque
        );

        AlertDialog.Builder builder = new AlertDialog.Builder(envioActivity.this);
        builder.setMessage("Favor de revisar la informacion antes de ser enviada \n" +
                "¡¡En el campo comentario deberan ir los datos faltantes!!")
                .setCancelable(false)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<List<CEnvio>> callEnvio = dxApi.getEnvio(post4);


                        callEnvio.enqueue(new Callback<List<CEnvio>>() {
                            @Override
                            public void onResponse(Call<List<CEnvio>> call, Response<List<CEnvio>> response) {

                                if (!response.isSuccessful()) {
                                    Toast.makeText(envioActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                                }
                                List<CEnvio> cEnvios = response.body();

                                mensaje = cEnvios.get(0).getReturn_value();

                                if (mensaje == 0) {
                                    Toast.makeText(envioActivity.this, "Error 202", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(envioActivity.this, "Intercambio Iniciado", Toast.LENGTH_LONG).show();

                                    String unidad = String.valueOf(idUnidad);
                                    int remolque = idRemolque;



                                    Intent i = new Intent(envioActivity.this, imgActivity.class);
                                    i.putExtra("NoUnidad", unidad);
                                    i.putExtra("NoCaja", remolque);
                                    i.putExtra("idUsuario", usuario);
                                    i.putExtra("folio", Ufolio);
                                    i.putExtra("mensaje", mensaje);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                }


                            }

                            @Override
                            public void onFailure(Call<List<CEnvio>> call, Throwable t) {
                                Toast.makeText(envioActivity.this, "Error 404 " + t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alert = builder.create();
        alert.show();

    }



}