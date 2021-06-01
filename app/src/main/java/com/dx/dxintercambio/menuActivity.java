package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class menuActivity extends AppCompatActivity {

    private EditText Hcontra;
    private TextView redEstatus;
    private Button generarInter , sincronizar, enviarInter ;
    private DxApi dxApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        generarInter = (Button) findViewById(R.id.generaIntercambio);
        sincronizar = (Button) findViewById(R.id.sincronizaDatos);
        enviarInter = (Button) findViewById(R.id.enviarIntercambio);
        redEstatus = (TextView) findViewById(R.id.estatusRed);


        /*File mydir = getBaseContext().getDir("intercambios", Context.MODE_PRIVATE);
        mydir.mkdirs();

        String dirPath = mydir.getPath();
        File projDir = new File(dirPath,"FOLIO");
        projDir.mkdir();*/



        if(isOnline()){
            redEstatus.setText("Estatus Red : Conectado");
            redEstatus.setTextColor(Color.parseColor("#197210"));
        }else{
            redEstatus.setText("Estatus Red : Sin Red");
            redEstatus.setTextColor(Color.parseColor("#DD4626"));
        }

        sincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(menuActivity.this);
                LayoutInflater inflater = menuActivity.this.getLayoutInflater();

                View view = inflater.inflate(R.layout.custom_alert,null);

                Hcontra = view.findViewById(R.id.hcontra);
                DataBaseHelper dataBaseHelper =  new DataBaseHelper(menuActivity.this);

                builder.setView(view)
                        .setTitle("Configuracion de Sistemas")
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String contraCulera = Hcontra.getText().toString();

                                if (contraCulera.contains("500360")){


                                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                                            .callTimeout(3, TimeUnit.MINUTES)
                                            .connectTimeout(3, TimeUnit.MINUTES)
                                            .readTimeout(2, TimeUnit.MINUTES)
                                            .writeTimeout(2, TimeUnit.MINUTES);

                                    Retrofit.Builder builder = new Retrofit.Builder()
                                            .baseUrl("http://"+"dxxpress.net/API/api/")
                                            .addConverterFactory(GsonConverterFactory.create())
                                            ;

                                    builder.client(httpClient.build());

                                    Retrofit retrofit = builder.build();

                                    dxApi = retrofit.create(DxApi.class);


                                    Post post = new Post("C-01","42069");
                                    Call<List<CFlota>> callFlota = dxApi.getTranspo(post);
                                    Call<List<CLinea>> callLinea = dxApi.getLinea(post);
                                    Call<List<COperador>> callOpe = dxApi.getOperador(post);
                                    Call<List<CLlanta>> call_Llanta = dxApi.getLlanta(post);
                                    Call<List<CUsuarioRel>> callUsuarioRel = dxApi.getUsuarioRel(post);

                                    Post2 post2 = new Post2("C-01","42069");
                                    Call<List<CUnidad>> callUnidad = dxApi.getUnidad(post2);


                                    Post3 post3 = new Post3("C-01","42069");
                                    Call<List<CRemolque>> callRemolque = dxApi.getRemolque(post3);

                                    //the game
                                    callFlota.enqueue(new Callback<List<CFlota>>() {
                                        @Override
                                        public void onResponse(Call<List<CFlota>> call, Response<List<CFlota>> response) {
                                            if(!response.isSuccessful()){
                                                Toast.makeText(menuActivity.this, "Tranpo Error " + response.message(), Toast.LENGTH_LONG).show();
                                            }
                                            List<CFlota> cFlotas = response.body();
                                            if(cFlotas!=null){
                                                for(int i = 0; i < cFlotas.size() ; i++){
                                                    String idTransportista = cFlotas.get(i).getId();
                                                    String nombreTransportista = cFlotas.get(i).getNombre();
                                                    String clave = cFlotas.get(i).getClave();

                                                    CFlota cFlota = new CFlota(idTransportista,clave,nombreTransportista);
                                                    boolean insertTranspoResponse = dataBaseHelper.insertTranspo(cFlota);
                                                }
                                            }else{
                                                Toast.makeText(menuActivity.this, "Tranpo Null Response", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<List<CFlota>> call, Throwable t) {
                                            Toast.makeText(menuActivity.this, "Tranpo Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });



                                    callLinea.enqueue(new Callback<List<CLinea>>() {
                                        @Override
                                        public void onResponse(Call<List<CLinea>> call, Response<List<CLinea>> response) {
                                            if(!response.isSuccessful()){
                                                Toast.makeText(menuActivity.this, "Linea Error " + response.message(), Toast.LENGTH_LONG).show();
                                            }
                                            List<CLinea> cLineas = response.body();
                                            if(cLineas!=null){
                                                for(int i = 0; i < cLineas.size() ; i++){
                                                    String idLinea = cLineas.get(i).getId();
                                                    String nombreLinea = cLineas.get(i).getNombreLinea();

                                                    CLinea cLinea = new CLinea(idLinea,nombreLinea);
                                                    boolean insertLineaResponse = dataBaseHelper.insertLinea(cLinea);
                                                }
                                            }else{
                                                Toast.makeText(menuActivity.this, "Linea Null Response", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<List<CLinea>> call, Throwable t) {
                                            Toast.makeText(menuActivity.this, "Linea Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });


                                    callOpe.enqueue(new Callback<List<COperador>>() {
                                        @Override
                                        public void onResponse(Call<List<COperador>> call, Response<List<COperador>> response) {
                                            if(!response.isSuccessful()){
                                                Toast.makeText(menuActivity.this, "Ope Error " + response.message(), Toast.LENGTH_LONG).show();
                                            }
                                            List<COperador> cOperadors = response.body();
                                            if(cOperadors!=null){
                                                for(int i = 0; i < cOperadors.size() ; i++){
                                                    String idOperador = cOperadors.get(i).getIdOperador();
                                                    String nombreOperador = cOperadors.get(i).getNombreCompleto();

                                                    COperador cOperador = new COperador(idOperador,nombreOperador);
                                                    boolean insertOpeResponse = dataBaseHelper.insertOperador(cOperador);
                                                }
                                            }else{
                                                Toast.makeText(menuActivity.this, "Ope Null Response", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<List<COperador>> call, Throwable t) {
                                            Toast.makeText(menuActivity.this, "Ope Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    call_Llanta.enqueue(new Callback<List<CLlanta>>() {
                                        @Override
                                        public void onResponse(Call<List<CLlanta>> call, Response<List<CLlanta>> response) {
                                            if(!response.isSuccessful()){
                                                Toast.makeText(menuActivity.this, "Llanta Error " + response.message(), Toast.LENGTH_LONG).show();
                                            }
                                            List<CLlanta> cLlantas = response.body();
                                            if(cLlantas!=null){
                                                for(int i = 0; i < cLlantas.size() ; i++){
                                                    String idLlanta = cLlantas.get(i).getId();
                                                    String nombreLlanta = cLlantas.get(i).getNombre();

                                                    CLlanta cLlanta = new CLlanta(idLlanta,nombreLlanta);
                                                    boolean insertLlantaResponse = dataBaseHelper.insertLlanta(cLlanta);
                                                }
                                            }else{
                                                Toast.makeText(menuActivity.this, "Llanta Null Response", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<List<CLlanta>> call, Throwable t) {
                                            Toast.makeText(menuActivity.this, "Llanta Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    callUsuarioRel.enqueue(new Callback<List<CUsuarioRel>>() {
                                        @Override
                                        public void onResponse(Call<List<CUsuarioRel>> call, Response<List<CUsuarioRel>> response) {
                                            if(!response.isSuccessful()){
                                                Toast.makeText(menuActivity.this, "Usuario Error " + response.message(), Toast.LENGTH_LONG).show();
                                            }
                                            List<CUsuarioRel> cUsuarioRels = response.body();
                                            if(cUsuarioRels!=null){
                                                for(int i = 0; i < cUsuarioRels.size() ; i++){
                                                    String idUsuario = cUsuarioRels.get(i).getId();
                                                    String login = cUsuarioRels.get(i).getLogin();
                                                    String password = cUsuarioRels.get(i).getPassword();

                                                    CUsuarioRel cUsuarioRel = new CUsuarioRel(idUsuario,login,password);
                                                    boolean insertUsuarioResponse = dataBaseHelper.insertUsuario(cUsuarioRel);
                                                }
                                            }else{
                                                Toast.makeText(menuActivity.this, "Usuario Null Response", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<List<CUsuarioRel>> call, Throwable t) {
                                            Toast.makeText(menuActivity.this, "Usuario Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    callUnidad.enqueue(new Callback<List<CUnidad>>() {
                                        @Override
                                        public void onResponse(Call<List<CUnidad>> call, Response<List<CUnidad>> response) {
                                            if(!response.isSuccessful()){
                                                Toast.makeText(menuActivity.this, "Unidad Error " + response.message(), Toast.LENGTH_LONG).show();
                                            }
                                            List<CUnidad> cUnidads = response.body();
                                            if(cUnidads!=null){
                                                for(int i = 0; i < cUnidads.size() ; i++){
                                                    String idUnidad = cUnidads.get(i).getId();
                                                    String clave = cUnidads.get(i).getClave();

                                                    CUnidad cUnidad = new CUnidad(idUnidad,clave);
                                                    boolean insertTranspoResponse = dataBaseHelper.insertUnidad(cUnidad);
                                                }
                                            }else{
                                                Toast.makeText(menuActivity.this, "Unidad Null Response", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<List<CUnidad>> call, Throwable t) {
                                            Toast.makeText(menuActivity.this, "Unidad Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    callRemolque.enqueue(new Callback<List<CRemolque>>() {
                                        @Override
                                        public void onResponse(Call<List<CRemolque>> call, Response<List<CRemolque>> response) {
                                            if(!response.isSuccessful()){
                                                Toast.makeText(menuActivity.this, "Remolque Error " + response.message(), Toast.LENGTH_LONG).show();
                                            }
                                            List<CRemolque> cRemolques = response.body();
                                            if(cRemolques!=null){
                                                for(int i = 0; i < cRemolques.size() ; i++){
                                                    String idRemolque = cRemolques.get(i).getId();
                                                    String nombre = cRemolques.get(i).getRemolques();
                                                    String idLinea = cRemolques.get(i).getIdLinea();

                                                    CRemolque cRemolque = new CRemolque(idRemolque,nombre,idLinea);
                                                    boolean insertRemolqueResponse = dataBaseHelper.insertRemolque(cRemolque);
                                                }
                                            }else{
                                                Toast.makeText(menuActivity.this, "Remolque Null Response", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<List<CRemolque>> call, Throwable t) {
                                            Toast.makeText(menuActivity.this, "Remolque Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });



                                } else {
                                    Toast.makeText(menuActivity.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        generarInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menuActivity.this, etapa1_Activity.class);
                startActivity(i);
            }
        });

    }

    public boolean isOnline() {

        boolean isConnected = false;
        ConnectivityManager connectivityMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            // Checking internet connectivity
            NetworkInfo activeNetwork = null;
            if (connectivityMgr != null) {
                activeNetwork = connectivityMgr.getActiveNetworkInfo(); // Deprecated in API 29
            }
            isConnected = activeNetwork != null;

        } else {
            Network[] allNetworks = connectivityMgr.getAllNetworks(); // added in API 21 (Lollipop)

            for (Network network : allNetworks) {
                NetworkCapabilities networkCapabilities = connectivityMgr.getNetworkCapabilities(network);
                if (networkCapabilities != null) {
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                        isConnected = true;
                }
            }
        }

        return isConnected;

    }

}