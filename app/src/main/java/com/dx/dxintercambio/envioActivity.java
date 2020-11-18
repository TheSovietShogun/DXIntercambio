package com.dx.dxintercambio;

import android.Manifest;
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
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import org.ksoap2.serialization.SoapPrimitive;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.Menu;

public class envioActivity extends AppCompatActivity {

    private EditText registradoPor  , comentarios;
    private Spinner tipoOperacionSP ,transportista, linea, estatusCaja ,unidad ,noRemolque, tipoMovimiento;
    private ImageView licencia;
    private Button enviar;
    private String [] tipoOpeArr ;
    private String [] estatusArr ;
    private String [] vacioArr ;
    private String [] vacioArr2 ;
    private String [] tipoMovArr;
    private String usuario ;
    private DxApi dxApi;
    private String user , password ;
    private AutoCompleteTextView operador  ;
    private String fechaHora ;
    private int idOperador;
    private int idUnidad;
    private int idRemolque;
    private  AlertDialog alert ;
    private File imageFile ;
    private Uri photoURI;
    private String imageFileName ;
    private static final int REQUEST_LICENCIA = 888;
    private String hora;
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
    private String folio;
    private String licenciaImg;
    private int mensaje ;
    private int alfa ;
    private int bravo ;
    private int charlie ;
    private int delta;
    private int foxtrop;
    private String nombreOpeBTN , nombreCajaBTN , nombreUnidadBTN;
    private String nombreOpe , nombreCaja , nombreUnidad;


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

        setContentView(R.layout.activity_envio);

        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);

        user = preferences.getString("user","");
        password = preferences.getString("pass","");
        usuario = getIntent().getStringExtra("idUsuario");

        tipoOperacionSP = (Spinner) findViewById(R.id.spinner2);
        registradoPor = (EditText)findViewById(R.id.editText8);
        transportista = (Spinner) findViewById(R.id.spinner);
        operador = (AutoCompleteTextView)findViewById(R.id.spinner69);
        unidad = (Spinner)findViewById(R.id.spinner7);
        noRemolque = (Spinner)findViewById(R.id.editText11);
        linea = (Spinner) findViewById(R.id.spinner10);
        estatusCaja = (Spinner) findViewById(R.id.spinner3);
        comentarios = (EditText)findViewById(R.id.editText12);
        enviar = (Button) findViewById(R.id.btnDatos);
        licencia = (ImageView) findViewById(R.id.imageView26);
        tipoMovimiento = (Spinner) findViewById(R.id.spinner4);

        registradoPor.setText(user);
        registradoPor.setEnabled(false);

        vacioArr = new String[]{"Sin Seleccionar"};
        vacioArr2 = new String[]{"Sin Seleccionar"};
        tipoOpeArr = new String[]{"Sin Seleccionar","Entrada", "Salida"};
        estatusArr = new String[]{"Sin Seleccionar","Cargado", "Vacio","Racks"};
        tipoMovArr = new String[]{"Sin Seleccionar","Importacion","Exportacion"};


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



        ////////////////////////////////////////////////////////////////////////////////////////////
        //BORRA TODAS LAS IMAGENES GUARDADAS
        File destPath = new File(getBaseContext().getExternalFilesDir(null).getAbsolutePath());
        String path = destPath.toString();
        File directory = new File(path);
        File[] files = directory.listFiles();

        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
            getApplicationContext().deleteFile(files[i].getName());
            //String nombre = files[i].getName();
        }

        ////////////////////////////////////////////////////////////////////////////////////////////



        operador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                COperador cOperador = (COperador) operador.getAdapter().getItem(i);
                idOperador = Integer.parseInt(cOperador.getIdOperador());
                nombreOpeBTN = String.valueOf(cOperador.getNombreCompleto());

            }
        });


        licencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                licencia.setEnabled(false);
                imgClick("tractor" , REQUEST_LICENCIA);
            }
        });



        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //ENVIAR
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.4.92/api/")
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

                //GET UNIDAD
                CUnidad cUnidad = (CUnidad)unidad.getSelectedItem();

                String loco ;
                 idUnidad = cUnidad.getId();


                //GET COMENTARIO
                String comentario = comentarios.getText().toString();

                //GET REMOLQUE
                CRemolque cRemolque = (CRemolque)noRemolque.getSelectedItem();
                idRemolque = cRemolque.getId();

                //GET USUARIO
                int idUsuario = Integer.parseInt(usuario);

                //SET GET FOLIO
                hora =  new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
               folio = ("NL"+hora+idRemolque);
               String comentarioCancel = "";
               int idIntercambio =0 ;






                //REVISA POR OPCION "OTRO"
                     if (idTransportista == 42069 && idLinea == 42069 ){
                        //CAMPOS QUE FORZOSOS
                        if(tipoOperacion == 3 || estatus == 3 || comentario.isEmpty() || movimiento == 3 || licenciaImg.contains("480")
                                || idTransportista == 69420 || idLinea == 69420 || idUnidad == 69420 || idRemolque == 69420){
                            Toast.makeText(envioActivity.this, "Campos vacios existente", Toast.LENGTH_LONG).show();
                        } else {

                            String imageFilePath = imageFile.getPath();

                            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);

                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bytes);
                            String encodedImage = Base64.encodeToString(bytes.toByteArray(), Base64.NO_WRAP);

                            Post4 post4 = new Post4(user,password,fechaHora,tipoOperacion,idUsuario,0,idTransportista,idOperador,idUnidad,
                                    idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,0,movimiento,"MTY",encodedImage,"licencia"+folio);

                                AlertDialog.Builder builder = new AlertDialog.Builder(envioActivity.this);
                                builder.setMessage("Favor de revisar la informacion antes de ser enviada \n¡¡En el campo comentario deberan ir los datos faltantes!!")
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
                                                            Toast.makeText(envioActivity.this, "Enviado Correctamente", Toast.LENGTH_LONG).show();
                                                            enviar();
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

                        //SI NO SON NULOS LOS CAMPOS FORZOSOS
                    } else if (idTransportista != 42069 && idLinea != 42069 ){
                        //ESTA COMPLETO ?
                        if(idOperador == 0 || idUnidad == 0 || idRemolque == 0 || estatus == 3 || tipoOperacion == 3
                                || movimiento == 3 || licenciaImg.contains("480") || idTransportista == 69420 || idLinea == 69420 || idUnidad == 69420 || idRemolque == 69420){
                            //NO
                            Toast.makeText(envioActivity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                        }else {
                            //SI

                            String imageFilePath = imageFile.getPath();

                            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);

                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bytes);
                            String encodedImage = Base64.encodeToString(bytes.toByteArray(), Base64.NO_WRAP);

                            Post4 post4 = new Post4(user,password,fechaHora,tipoOperacion,idUsuario,0,idTransportista,idOperador,idUnidad,
                                    idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,0,movimiento,"MTY",encodedImage,"licencia"+folio);

                              AlertDialog.Builder builder = new AlertDialog.Builder(envioActivity.this);
                            builder.setMessage("Favor de revisar la informacion antes de ser enviada !")
                            .setCancelable(false)
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Call<List<CEnvio>> callEnvio = dxApi.getEnvio(post4);


                                    callEnvio.enqueue(new Callback<List<CEnvio>>() {
                                        @Override
                                        public void onResponse(Call<List<CEnvio>> call, Response<List<CEnvio>> response) {

                                            if(!response.isSuccessful()){
                                                Toast.makeText(envioActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                                            }
                                            List<CEnvio> cEnvios = response.body();

                                            mensaje = cEnvios.get(0).getReturn_value();

                                            if (mensaje == 0){
                                                Toast.makeText(envioActivity.this, "Error 202", Toast.LENGTH_LONG).show();
                                            }else {
                                                Toast.makeText(envioActivity.this, "Enviado Correctamente", Toast.LENGTH_LONG).show();
                                                enviar();
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<List<CEnvio>> call, Throwable t) {
                                            Toast.makeText(envioActivity.this, "Error 404 "+ t.getMessage(), Toast.LENGTH_LONG).show();
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

                    }else {

                        alfa = 0;
                        bravo = 0;
                        charlie = 0;
                        delta = 0;
                        foxtrop = 0;


                        if (idTransportista != 42069) {
                            if (idUnidad == 0) {
                                unidad.setBackgroundColor(Color.parseColor("#D32929"));
                                bravo = 400;
                            }
                        }

                        if (idLinea != 42069) {
                            if (idRemolque == 0) {
                                noRemolque.setBackgroundColor(Color.parseColor("#D32929"));
                                charlie = 400;
                            }
                        }


                        if (tipoOperacion == 3 || estatus == 3 || comentario.isEmpty() || movimiento == 3 || licenciaImg.contains("480") || idTransportista == 69420 || idLinea == 69420) {

                           // Toast.makeText(envioActivity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                            delta = 400;
                        }


                        if(alfa == 400 || bravo == 400 || charlie == 400 || delta == 400 || foxtrop == 400 ){
                            Toast.makeText(envioActivity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                        }else if(alfa == 0 || bravo == 0 || charlie == 0 || delta == 0 || foxtrop == 0){



                            String imageFilePath = imageFile.getPath();

                            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);

                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bytes);
                            String encodedImage = Base64.encodeToString(bytes.toByteArray(), Base64.NO_WRAP);

                            Post4 post4 = new Post4(user,password,fechaHora,tipoOperacion,idUsuario,0,idTransportista,idOperador,idUnidad,
                                    idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,0,movimiento,"MTY",encodedImage,"licencia"+folio);

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
                                                        Toast.makeText(envioActivity.this, "Enviado Correctamente", Toast.LENGTH_LONG).show();
                                                        enviar();
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


            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.4.92/api/")
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

                CUnidad cUnidad11 = new CUnidad(69420,"Sin Seleccionar");
                List<CUnidad> cUnidads =  new ArrayList<CUnidad>();
                cUnidads.add(0,cUnidad11);
                ArrayAdapter<CUnidad> adapterU = new ArrayAdapter<CUnidad>(envioActivity.this , R.layout.mspinner_item, cUnidads);
                unidad.setAdapter(adapterU);


                CFlota cFlota12 = new CFlota("42069","Otro");
                CFlota cFlota11 = new CFlota("69420","Sin Seleccionar");


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
                    unidad.setSelection(0);
                  //

                    if(nombreS == "Otro" ){
                        Toast.makeText(envioActivity.this, "ESCRIBIR EN COMENTARIO : " +
                                "\n-Nombre del Transportista" +
                                "\n-Nombre Completo de Operador" +
                                "\n-Numero Economico de la Unidad", Toast.LENGTH_LONG).show();
                    }

                }else {
                    unidad.setEnabled(true);

                    Post2 post2 = new Post2(user,password,idFlota);

                    Call<List<CUnidad>> callUni = dxApi.getUnidad(post2);

                    callUni.enqueue(new Callback<List<CUnidad>>() {
                        @Override
                        public void onResponse(Call<List<CUnidad>> call, Response<List<CUnidad>> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(envioActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                            }
                            List<CUnidad> cUnidads = response.body();

                            CUnidad cUnidad11 = new CUnidad(69420,"Sin Seleccionar");
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

                CRemolque cRemolque = new CRemolque(69420,"Sin Seleccionar");
                List<CRemolque> cRemolques =  new ArrayList<CRemolque>();
                cRemolques.add(0,cRemolque);
                ArrayAdapter<CRemolque> adapterU = new ArrayAdapter<CRemolque>(envioActivity.this , R.layout.mspinner_item, cRemolques);
                noRemolque.setAdapter(adapterU);

                CLinea cLinea12 = new CLinea("42069","Otro");
                CLinea cLinea1 = new CLinea("69420","Sin Seleccionar");

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
                    noRemolque.setSelection(0);
                    if(nombreS == "Otro" ){
                        Toast.makeText(envioActivity.this, "ESCRIBIR EN COMENTARIO : \n-Nombre de la Linea\n-Numero Economico del Remolque", Toast.LENGTH_LONG).show();
                    }

                }else {
                    noRemolque.setEnabled(true);

                    Post3 post3 = new Post3(user, password, idLinea);

                    Call<List<CRemolque>> callremo = dxApi.getRemolque(post3);

                    callremo.enqueue(new Callback<List<CRemolque>>() {
                        @Override
                        public void onResponse(Call<List<CRemolque>> call, Response<List<CRemolque>> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(envioActivity.this, "Error 500", Toast.LENGTH_LONG).show();
                            }

                            List<CRemolque> cRemolques = response.body();
                            CRemolque cRemolque = new CRemolque(69420,"Sin Seleccionar");

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






    }


    public void enviar () {

         String operacion = tipoOperacionSP.getSelectedItem().toString();
         String NoUnidad = unidad.getSelectedItem().toString();
         String NoCaja = noRemolque.getSelectedItem().toString();
         String nombreLinea = linea.getSelectedItem().toString();
         String nombreTransportista = transportista.getSelectedItem().toString();
        CRemolque cremolque = (CRemolque) noRemolque.getSelectedItem();
        int boladenieve =  cremolque.getId();

           Intent i = new Intent(envioActivity.this, imgActivity.class);
            i.putExtra("operacion", operacion);
            i.putExtra("NoUnidad", NoUnidad);
            i.putExtra("NoCaja", NoCaja);
            i.putExtra("nombreLinea", nombreLinea);
            i.putExtra("nombreTransportista", nombreTransportista);
            i.putExtra("idUsuario", usuario);
            i.putExtra("folio", folio);
            i.putExtra("mensaje", mensaje);
        i.putExtra("idRemolque", boladenieve);
           startActivity(i);

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




}