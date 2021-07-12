package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView redEstatus, sinIntercambios;
    private Button generarInter , enviarInter ;
    private DxApi dxApi;
    private ListView listView;
    private list2Adapter list2Adapter ;
    private int widthScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        generarInter = (Button) findViewById(R.id.generaIntercambio);
        enviarInter = (Button) findViewById(R.id.enviarIntercambio);
        redEstatus = (TextView) findViewById(R.id.estatusRed);
        listView = (ListView) findViewById(R.id.listView);
        sinIntercambios = (TextView) findViewById(R.id.textView112);

        widthScreen = getApplicationContext().getResources().getDisplayMetrics().widthPixels;



        if(isNetworkAvailable(getApplicationContext())){
            redEstatus.setText("Estatus Red : Conectado");
            redEstatus.setTextColor(Color.parseColor("#197210"));
        }else{
            redEstatus.setText("Estatus Red : Sin Red");
            redEstatus.setTextColor(Color.parseColor("#DD4626"));
        }



        if(isNetworkAvailable(getApplicationContext())){

            DataBaseHelper dataBaseHelper =  new DataBaseHelper(menuActivity.this);


            int response = dataBaseHelper.clearTables();
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

                        boolean insertTranspoResponse = dataBaseHelper.insertTranspo(cFlotas);

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

                        boolean insertLineaResponse = dataBaseHelper.insertLinea(cLineas);

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
                        boolean insertOpeResponse = dataBaseHelper.insertOperador(cOperadors);

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
                        boolean insertLlantaResponse = dataBaseHelper.insertLlanta(cLlantas);

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
                        boolean insertUsuarioResponse = dataBaseHelper.insertUsuario(cUsuarioRels);

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
                        boolean insertTranspoResponse = dataBaseHelper.insertUnidad(cUnidads);

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
                        boolean insertRemolqueResponse = dataBaseHelper.insertRemolque(cRemolques);

                    }else{
                        Toast.makeText(menuActivity.this, "Remolque Null Response", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<List<CRemolque>> call, Throwable t) {
                    Toast.makeText(menuActivity.this, "Remolque Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Toast.makeText(menuActivity.this, "SIN RED " , Toast.LENGTH_LONG).show();
        }





        generarInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menuActivity.this, loginActivity.class);
                startActivity(i);
            }
        });

        enviarInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menuActivity.this, listActivity.class);
                startActivity(i);
            }
        });

       DataBaseHelper dataBaseHelper = new DataBaseHelper(menuActivity.this);

        List<CPopulateList> intercambioSelect = dataBaseHelper.selectIntercambioAbierto();

        if(intercambioSelect == null|| intercambioSelect.isEmpty()){

            sinIntercambios.setVisibility(View.VISIBLE);

        }else {

            if (widthScreen > 480) {
                list2Adapter = new list2Adapter(getApplicationContext(),R.layout.mlist_item2,intercambioSelect);
            }else{
                list2Adapter = new list2Adapter(getApplicationContext(),R.layout.mlist_item2_cel,intercambioSelect);
            }


            listView.setAdapter(list2Adapter);
        }



    }


    public static boolean isNetworkAvailable(Context context) {

        if(context == null)  return false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    }
                }
            }
            else{
                try {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        Log.i("update_statut", "Network is available : true");
                        return true;
                    }
                } catch (Exception e) {
                    Log.i("update_statut", "" + e.getMessage());
                }
            }
        }
        Log.i("update_statut","Network is available : FALSE ");
        return false;
    }

}