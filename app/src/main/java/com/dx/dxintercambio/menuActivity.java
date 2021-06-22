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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        generarInter = (Button) findViewById(R.id.generaIntercambio);
        enviarInter = (Button) findViewById(R.id.enviarIntercambio);
        redEstatus = (TextView) findViewById(R.id.estatusRed);
        listView = (ListView) findViewById(R.id.listView);
        sinIntercambios = (TextView) findViewById(R.id.textView112);


        if(isOnline()){
            redEstatus.setText("Estatus Red : Conectado");
            redEstatus.setTextColor(Color.parseColor("#197210"));
        }else{
            redEstatus.setText("Estatus Red : Sin Red");
            redEstatus.setTextColor(Color.parseColor("#DD4626"));
        }

        Intent i = new Intent(menuActivity.this, SQLiteService.class);
        startService(i);


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


            list2Adapter = new list2Adapter(getApplicationContext(),R.layout.mlist_item2,intercambioSelect);
            listView.setAdapter(list2Adapter);
        }




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