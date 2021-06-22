package com.dx.dxintercambio;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SQLiteService extends Service {
    public SQLiteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        DxApi dxApi;
        DataBaseHelper dataBaseHelper =  new DataBaseHelper(SQLiteService.this);
        if(isOnline()){

            int response = dataBaseHelper.clearTables();

            if(response > 0){

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
                            Toast.makeText(SQLiteService.this, "Tranpo Error " + response.message(), Toast.LENGTH_LONG).show();
                        }
                        List<CFlota> cFlotas = response.body();

                        if(cFlotas!=null){

                            boolean insertTranspoResponse = dataBaseHelper.insertTranspo(cFlotas);

                        }else{
                            Toast.makeText(SQLiteService.this, "Tranpo Null Response", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<CFlota>> call, Throwable t) {
                        Toast.makeText(SQLiteService.this, "Tranpo Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                callLinea.enqueue(new Callback<List<CLinea>>() {
                    @Override
                    public void onResponse(Call<List<CLinea>> call, Response<List<CLinea>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SQLiteService.this, "Linea Error " + response.message(), Toast.LENGTH_LONG).show();
                        }
                        List<CLinea> cLineas = response.body();
                        if(cLineas!=null){

                            boolean insertLineaResponse = dataBaseHelper.insertLinea(cLineas);

                        }else{
                            Toast.makeText(SQLiteService.this, "Linea Null Response", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<CLinea>> call, Throwable t) {
                        Toast.makeText(SQLiteService.this, "Linea Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                callOpe.enqueue(new Callback<List<COperador>>() {
                    @Override
                    public void onResponse(Call<List<COperador>> call, Response<List<COperador>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SQLiteService.this, "Ope Error " + response.message(), Toast.LENGTH_LONG).show();
                        }
                        List<COperador> cOperadors = response.body();

                        if(cOperadors!=null){
                            boolean insertOpeResponse = dataBaseHelper.insertOperador(cOperadors);

                        }else{
                            Toast.makeText(SQLiteService.this, "Ope Null Response", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<COperador>> call, Throwable t) {
                        Toast.makeText(SQLiteService.this, "Ope Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                call_Llanta.enqueue(new Callback<List<CLlanta>>() {
                    @Override
                    public void onResponse(Call<List<CLlanta>> call, Response<List<CLlanta>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SQLiteService.this, "Llanta Error " + response.message(), Toast.LENGTH_LONG).show();
                        }
                        List<CLlanta> cLlantas = response.body();
                        if(cLlantas!=null){
                            boolean insertLlantaResponse = dataBaseHelper.insertLlanta(cLlantas);

                        }else{
                            Toast.makeText(SQLiteService.this, "Llanta Null Response", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<CLlanta>> call, Throwable t) {
                        Toast.makeText(SQLiteService.this, "Llanta Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                callUsuarioRel.enqueue(new Callback<List<CUsuarioRel>>() {
                    @Override
                    public void onResponse(Call<List<CUsuarioRel>> call, Response<List<CUsuarioRel>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SQLiteService.this, "Usuario Error " + response.message(), Toast.LENGTH_LONG).show();
                        }
                        List<CUsuarioRel> cUsuarioRels = response.body();
                        if(cUsuarioRels!=null){
                            boolean insertUsuarioResponse = dataBaseHelper.insertUsuario(cUsuarioRels);

                        }else{
                            Toast.makeText(SQLiteService.this, "Usuario Null Response", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<CUsuarioRel>> call, Throwable t) {
                        Toast.makeText(SQLiteService.this, "Usuario Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                callUnidad.enqueue(new Callback<List<CUnidad>>() {
                    @Override
                    public void onResponse(Call<List<CUnidad>> call, Response<List<CUnidad>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SQLiteService.this, "Unidad Error " + response.message(), Toast.LENGTH_LONG).show();
                        }
                        List<CUnidad> cUnidads = response.body();
                        if(cUnidads!=null){
                            boolean insertTranspoResponse = dataBaseHelper.insertUnidad(cUnidads);

                        }else{
                            Toast.makeText(SQLiteService.this, "Unidad Null Response", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<CUnidad>> call, Throwable t) {
                        Toast.makeText(SQLiteService.this, "Unidad Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                callRemolque.enqueue(new Callback<List<CRemolque>>() {
                    @Override
                    public void onResponse(Call<List<CRemolque>> call, Response<List<CRemolque>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SQLiteService.this, "Remolque Error " + response.message(), Toast.LENGTH_LONG).show();
                        }
                        List<CRemolque> cRemolques = response.body();
                        if(cRemolques!=null){
                            boolean insertRemolqueResponse = dataBaseHelper.insertRemolque(cRemolques);

                        }else{
                            Toast.makeText(SQLiteService.this, "Remolque Null Response", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<CRemolque>> call, Throwable t) {
                        Toast.makeText(SQLiteService.this, "Remolque Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                stopSelf();

            }

        }
        

        
        return super.onStartCommand(intent, flags, startId);
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