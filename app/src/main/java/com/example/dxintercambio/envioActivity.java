package com.example.dxintercambio;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class envioActivity extends AppCompatActivity {

    private EditText registradoPor  , comentarios;
    private Spinner tipoOperacion ,transportista, linea, estatus ;
    private Button enviar;
    private String tran;
    private SoapPrimitive resultString;
    private GridView gridView;
    private String [] tipoOpeArr ;
    private String [] tranportistasArr ;
    private String [] lineaArr ;
    private String [] estatusArr ;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_envio);


        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);

        user = preferences.getString("user","");
        password = preferences.getString("pass","");


        usuario = getIntent().getStringExtra("idUsuario");

        tipoOperacion = (Spinner) findViewById(R.id.spinner2);
        registradoPor = (EditText)findViewById(R.id.editText8);
        transportista = (Spinner) findViewById(R.id.spinner);
        operador = (AutoCompleteTextView)findViewById(R.id.spinner69);
        unidad = (AutoCompleteTextView)findViewById(R.id.editText7);
        noRemolque = (AutoCompleteTextView)findViewById(R.id.editText11);
        linea = (Spinner) findViewById(R.id.spinner10);
        estatus = (Spinner) findViewById(R.id.spinner3);
        comentarios = (EditText)findViewById(R.id.editText12);
        enviar = (Button) findViewById(R.id.button);

        registradoPor.setText(user);
        registradoPor.setEnabled(false);

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
                        .baseUrl("http://dxxpress.net/API/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                dxApi = retrofit.create(DxApi.class);

                fechaHora = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date());

                String tipoOpe = tipoOperacion.getSelectedItem().toString();
                String status  =  estatus.getSelectedItem().toString();
                int tipoOperacion = 0;
                int estatus = 0;

                switch (tipoOpe){
                    case "Salida" :
                        tipoOperacion = 1;
                        break;
                    case "Entrada" :
                        tipoOperacion = 0;
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
                }

                CFlota cFlota = (CFlota) transportista.getSelectedItem();
                int idTransportista = Integer.parseInt(cFlota.getId());

                CLinea cLinea = (CLinea)linea.getSelectedItem();
                int idLinea = Integer.parseInt(cLinea.getId());

                int sadas = idOperador;
                int asd = idUnidad;
                int sadaas = idRemolque;

                String comentario = comentarios.getText().toString();

                int idUsuario = Integer.parseInt(usuario);


                Post4 post4 = new Post4(user,password,fechaHora,tipoOperacion,idUsuario,idTransportista,idOperador,idUnidad,idRemolque,idLinea,estatus,comentario);

                Call<List<CEnvio>> callEnvio = dxApi.getEnvio(post4);

                callEnvio.enqueue(new Callback<List<CEnvio>>() {
                    @Override
                    public void onResponse(Call<List<CEnvio>> call, Response<List<CEnvio>> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(envioActivity.this, "Error 404E", Toast.LENGTH_LONG).show();
                        }
                        List<CEnvio> cEnvios = response.body();

                        String mensaje = cEnvios.get(0).getMensaje();

                        Toast.makeText(envioActivity.this, "Enviado Correctamente", Toast.LENGTH_LONG).show();

                         enviar();

                    }

                    @Override
                    public void onFailure(Call<List<CEnvio>> call, Throwable t) {
                        Toast.makeText(envioActivity.this, "Error 404E "+ t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        tipoOpeArr = new String[]{"Entrada", "Salida"};
        estatusArr = new String[]{"Cargado", "Vacio","Racks"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tipoOpeArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoOperacion.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, estatusArr);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estatus.setAdapter(adapter2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dxxpress.net/API/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dxApi = retrofit.create(DxApi.class);
        Post post = new Post(user,password);

        //FLOTA
        Call<List<CFlota>> callFlota = dxApi.getFlota(post);

        callFlota.enqueue(new Callback<List<CFlota>>() {
            @Override
            public void onResponse(Call<List<CFlota>> call, Response<List<CFlota>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(envioActivity.this, "Error 404F", Toast.LENGTH_LONG).show();
                }

                List<CFlota> cFlotas = response.body();
                ArrayAdapter<CFlota> adapter3 = new ArrayAdapter<CFlota>(envioActivity.this ,android.R.layout.simple_spinner_item, cFlotas);
                adapter3.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
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

                Post2 post2 = new Post2(user,password,idFlota);

                Call<List<CUnidad>> callUni = dxApi.getUnidad(post2);

                callUni.enqueue(new Callback<List<CUnidad>>() {
                    @Override
                    public void onResponse(Call<List<CUnidad>> call, Response<List<CUnidad>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(envioActivity.this, "Error 404U", Toast.LENGTH_LONG).show();
                        }
                        List<CUnidad> cUnidads = response.body();

                        ArrayAdapter<CUnidad> adapterU = new ArrayAdapter<CUnidad>(envioActivity.this ,android.R.layout.simple_spinner_item, cUnidads);
                        adapterU.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                        unidad.setAdapter(adapterU);
                        unidad.setText(null);
                    }

                    @Override
                    public void onFailure(Call<List<CUnidad>> call, Throwable t) {
                        Toast.makeText(envioActivity.this, "Error 404U", Toast.LENGTH_LONG).show();
                    }
                });

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
                ArrayAdapter<CLinea> adapter = new ArrayAdapter<CLinea>(envioActivity.this ,android.R.layout.simple_spinner_item, cLineas);
                adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
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

                Post3 post3 = new Post3(user,password,idLinea);

                Call<List<CRemolque>> callremo = dxApi.getRemolque(post3);

                callremo.enqueue(new Callback<List<CRemolque>>() {
                    @Override
                    public void onResponse(Call<List<CRemolque>> call, Response<List<CRemolque>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(envioActivity.this, "Error 404R", Toast.LENGTH_LONG).show();
                        }

                        List<CRemolque> cRemolques = response.body();

                        int sad  = cRemolques.size();

                        ArrayAdapter<CRemolque> adapterR = new ArrayAdapter<CRemolque>(envioActivity.this ,android.R.layout.simple_spinner_item, cRemolques);
                        adapterR.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                        noRemolque.setAdapter(adapterR);
                        noRemolque.setText(null);

                    }

                    @Override
                    public void onFailure(Call<List<CRemolque>> call, Throwable t) {
                        Toast.makeText(envioActivity.this, "Error 404R", Toast.LENGTH_LONG).show();
                    }
                });

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

                int sad = cOperadors.size();

                ArrayAdapter<COperador> adapter2 = new ArrayAdapter<COperador>(envioActivity.this ,android.R.layout.simple_spinner_item, cOperadors);
                adapter2.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
                operador.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<List<COperador>> call, Throwable t) {
                Toast.makeText(envioActivity.this, "Error 404O", Toast.LENGTH_LONG).show();
            }
        });


    }


    public void enviar () {

         String operacion = tipoOperacion.getSelectedItem().toString();
         String NoUnidad = unidad.getText().toString();
         String NoCaja = noRemolque.getText().toString();
         String nombreLinea = linea.getSelectedItem().toString();
         String nombreTransportista = transportista.getSelectedItem().toString();

           Intent i = new Intent(envioActivity.this, imgActivity.class);
            i.putExtra("operador", operacion);
            i.putExtra("NoUnidad", NoUnidad);
            i.putExtra("NoCaja", NoCaja);
            i.putExtra("nombreLinea", nombreLinea);
            i.putExtra("nombreTransportista", nombreTransportista);
           startActivity(i);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    public void onBackPressed() {

    }
}