package com.dx.dxintercambio;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.ksoap2.serialization.SoapPrimitive;

import java.io.File;
import java.text.SimpleDateFormat;
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
    private Spinner tipoOperacionSP ,transportista, linea, estatusCaja ;
    private Button enviar;
    private String tran;
    private SoapPrimitive resultString;
    private GridView gridView;
    private String [] tipoOpeArr ;
    private String [] tranportistasArr ;
    private String [] lineaArr ;
    private String [] estatusArr ;
    int PICK_IMAGE_MULTIPLE = 1;
    private String imageEncoded;
    private List<String> imagesEncodedList;
    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    private String usuario ;
    private DxApi dxApi;
    private String user , password ;
    private AutoCompleteTextView operador , unidad,noRemolque ;
    private String fechaHora ;
    private int idOperador;
    private int idUnidad;
    private int idRemolque;
    private  AlertDialog alert ;
    private String hora;
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
    private ConnectivityManager cm;
    private ConnectivityManager.NetworkCallback callback;
    private NetworkRequest networkRequest;
    int mensaje ;


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
        unidad = (AutoCompleteTextView)findViewById(R.id.editText7);
        noRemolque = (AutoCompleteTextView)findViewById(R.id.editText11);
        linea = (Spinner) findViewById(R.id.spinner10);
        estatusCaja = (Spinner) findViewById(R.id.spinner3);
        comentarios = (EditText)findViewById(R.id.editText12);
        enviar = (Button) findViewById(R.id.btnDatos);

        registradoPor.setText(user);
        registradoPor.setEnabled(false);


        ////////////////////////////////////////////////////////////////////////////////////////////

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
            }
        });

        unidad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CUnidad cUnidad = (CUnidad) unidad.getAdapter().getItem(i);
                idUnidad =  (cUnidad.getId());
            }
        });

        noRemolque.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CRemolque cRemolque = (CRemolque) noRemolque.getAdapter().getItem(i);
                idRemolque =  (cRemolque.getId());
            }
        });


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //ENVIAR
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.4.87:80/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                dxApi = retrofit.create(DxApi.class);

                fechaHora = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date());

                String tipoOpe = tipoOperacionSP.getSelectedItem().toString();
                String status  =  estatusCaja.getSelectedItem().toString();
                int tipoOperacion = 3;
                int estatus =3;

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


                //GET FLOTA
                CFlota cFlota = (CFlota) transportista.getSelectedItem();
                int idTransportista = Integer.parseInt(cFlota.getId());

                //GET LINEA
                CLinea cLinea = (CLinea)linea.getSelectedItem();
                int idLinea = Integer.parseInt(cLinea.getId());

                //GET COMENTARIO
                String comentario = comentarios.getText().toString();

                //GET OPERADOR
                int loco  = operador.getId();

                //GET USUARIO
                int idUsuario = Integer.parseInt(usuario);

                //SET GET FOLIO
                hora =  new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
               folio = (hora+"_"+idRemolque);
               String comentarioCancel = "";
               int idIntercambio =0 ;

                Post4 post4 = new Post4(user,password,fechaHora,tipoOperacion,idUsuario,idTransportista,idOperador,idUnidad,idRemolque,idLinea,estatus,comentario,folio,comentarioCancel,idIntercambio);


                //REVISA POR OPCION "OTRO"
                    if (idTransportista == 42069 || idLinea == 42069 ){
                        //CAMPOS QUE FORZOSOS
                        if(tipoOperacion == 3 || estatus == 3 || comentario.isEmpty() ){
                            Toast.makeText(envioActivity.this, "Campos vacios existente", Toast.LENGTH_LONG).show();
                        }else {
                           // Toast.makeText(envioActivity.this, "SE ENVIIO", Toast.LENGTH_LONG).show();
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

                                            if(!response.isSuccessful()){
                                                Toast.makeText(envioActivity.this, "Error 404E", Toast.LENGTH_LONG).show();
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
                                            Toast.makeText(envioActivity.this, "Error 404E "+ t.getMessage(), Toast.LENGTH_LONG).show();
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
                        if(idOperador == 0 || idUnidad == 0 || idRemolque == 0 || estatus == 3 || tipoOperacion == 3){
                            //NO
                            if(idOperador== 0){
                                operador.setBackgroundColor(Color.parseColor("#D32929"));
                            }
                            if(idUnidad== 0){
                                unidad.setBackgroundColor(Color.parseColor("#D32929"));
                            }
                            if(idRemolque== 0){
                                noRemolque.setBackgroundColor(Color.parseColor("#D32929"));
                            }
                            if(estatus== 0){
                                estatusCaja.setBackgroundColor(Color.parseColor("#D32929"));
                            }
                            if(tipoOperacion== 0){
                                tipoOperacionSP.setBackgroundColor(Color.parseColor("#D32929"));
                            }


                            Toast.makeText(envioActivity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                        }else {
                            //SI
                           // Toast.makeText(envioActivity.this, "SE ENVIIO", Toast.LENGTH_LONG).show();
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
                                                Toast.makeText(envioActivity.this, "Error 404E", Toast.LENGTH_LONG).show();
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
                                            Toast.makeText(envioActivity.this, "Error 404E "+ t.getMessage(), Toast.LENGTH_LONG).show();
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


        tipoOpeArr = new String[]{"Sin Seleccionar","Entrada", "Salida"};
        estatusArr = new String[]{"Sin Seleccionar","Cargado", "Vacio","Racks"};



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mspinner_item, tipoOpeArr);
       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoOperacionSP.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.mspinner_item, estatusArr);
       // adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estatusCaja.setAdapter(adapter2);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.4.87:80/api/")
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
                    Toast.makeText(envioActivity.this, "Error 404F", Toast.LENGTH_LONG).show();
                }

                List<CFlota> cFlotas = response.body();


                CFlota cFlota12 = new CFlota("42069","Otro");
                CFlota cFlota11 = new CFlota("42069","Sin Seleccionar");


                cFlotas.add(0,cFlota12);
                cFlotas.add(0,cFlota11);

                ArrayAdapter<CFlota> adapter3 = new ArrayAdapter<CFlota>(envioActivity.this , R.layout.mspinner_item, cFlotas);
                //adapter3.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
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
                    operador.setEnabled(false);
                    operador.setBackgroundColor(azul);
                    unidad.setEnabled(false);
                    unidad.setBackgroundColor(azul);
                    if(nombreS == "Otro" ){
                        Toast.makeText(envioActivity.this, "ESCRIBIR EN COMENTARIO : \n-Nombre del Transportista\n-Nombre Completo de Operador\n-Numero Economico de la Unidad", Toast.LENGTH_LONG).show();
                    }

                }else {
                    operador.setEnabled(true);
                    operador.setBackgroundColor(Color.parseColor("#D6D9D6"));
                    unidad.setEnabled(true);
                    unidad.setBackgroundColor(Color.parseColor("#D6D9D6"));
                    Post2 post2 = new Post2(user,password,idFlota);

                    Call<List<CUnidad>> callUni = dxApi.getUnidad(post2);

                    callUni.enqueue(new Callback<List<CUnidad>>() {
                        @Override
                        public void onResponse(Call<List<CUnidad>> call, Response<List<CUnidad>> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(envioActivity.this, "Error 404U", Toast.LENGTH_LONG).show();
                            }
                            List<CUnidad> cUnidads = response.body();


                            ArrayAdapter<CUnidad> adapterU = new ArrayAdapter<CUnidad>(envioActivity.this , R.layout.mspinner_item, cUnidads);
                            //adapterU.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                            unidad.setAdapter(adapterU);
                            unidad.setText(null);
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
                    Toast.makeText(envioActivity.this, "Error 404L", Toast.LENGTH_LONG).show();
                }

                List<CLinea> cLineas = response.body();

                CLinea cLinea12 = new CLinea("42069","Otro");
                CLinea cLinea1 = new CLinea("42069","Sin Seleccionar");


                cLineas.add(0,cLinea12);
                cLineas.add(0,cLinea1);


                ArrayAdapter<CLinea> adapter = new ArrayAdapter<CLinea>(envioActivity.this , R.layout.mspinner_item, cLineas);
               // adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                linea.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CLinea>> call, Throwable t) {
                Toast.makeText(envioActivity.this, "Error 404L", Toast.LENGTH_LONG).show();
            }
        });



        //CAJA
        linea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CLinea cLinea = (CLinea) linea.getSelectedItem();

                String idLinea =  cLinea.getId();
                String nombreS = cLinea.getNombreLinea();

                if(nombreS == "Otro" || nombreS == "Sin Seleccionar"){
                    noRemolque.setEnabled(false);
                    noRemolque.setBackgroundColor(azul);
                    if(nombreS == "Otro" ){
                        Toast.makeText(envioActivity.this, "ESCRIBIR EN COMENTARIO : \n-Nombre de la Linea\n-Numero Economico del Remolque", Toast.LENGTH_LONG).show();
                    }

                }else {
                    noRemolque.setEnabled(true);
                    noRemolque.setBackgroundColor(Color.parseColor("#D6D9D6"));


                    Post3 post3 = new Post3(user, password, idLinea);

                    Call<List<CRemolque>> callremo = dxApi.getRemolque(post3);

                    callremo.enqueue(new Callback<List<CRemolque>>() {
                        @Override
                        public void onResponse(Call<List<CRemolque>> call, Response<List<CRemolque>> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(envioActivity.this, "Error 404R", Toast.LENGTH_LONG).show();
                            }

                            List<CRemolque> cRemolques = response.body();
                            int sad = cRemolques.size();

                            ArrayAdapter<CRemolque> adapterR = new ArrayAdapter<CRemolque>(envioActivity.this, R.layout.mspinner_item, cRemolques);
                            // adapterR.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                            noRemolque.setAdapter(adapterR);
                            noRemolque.setText(null);

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
                    Toast.makeText(envioActivity.this, "Error 404O", Toast.LENGTH_LONG).show();
                }

                List<COperador> cOperadors = response.body();

                ArrayAdapter<COperador> adapter2 = new ArrayAdapter<COperador>(envioActivity.this , R.layout.mspinner_item, cOperadors);
               // adapter2.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                operador.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<List<COperador>> call, Throwable t) {
                Toast.makeText(envioActivity.this, "Error 404O", Toast.LENGTH_LONG).show();
            }
        });





    }


    public void enviar () {

         String operacion = tipoOperacionSP.getSelectedItem().toString();
         String NoUnidad = unidad.getText().toString();
         String NoCaja = noRemolque.getText().toString();
         String nombreLinea = linea.getSelectedItem().toString();
         String nombreTransportista = transportista.getSelectedItem().toString();


           Intent i = new Intent(envioActivity.this, imgActivity.class);
            i.putExtra("operacion", operacion);
            i.putExtra("NoUnidad", NoUnidad);
            i.putExtra("NoCaja", NoCaja);
            i.putExtra("nombreLinea", nombreLinea);
            i.putExtra("nombreTransportista", nombreTransportista);
            i.putExtra("folio", folio);
            i.putExtra("mensaje", mensaje);
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

       /* private void checkInternet (Context context){

             cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            
             networkRequest = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .build();
             callback = new ConnectivityManager.NetworkCallback(){

                @Override
                public void onAvailable(@NonNull Network network) {
                    super.onAvailable(network);
                }

                @Override
                public void onLost(@NonNull Network network) {
                    super.onLost(network);
                }

            };
        }

    @Override
    protected void onResume() {
        super.onResume();

        cm.registerNetworkCallback(networkRequest, callback);

    }

    @Override
    protected void onPause() {

        cm.unregisterNetworkCallback(callback);
        super.onPause();
    }*/
}