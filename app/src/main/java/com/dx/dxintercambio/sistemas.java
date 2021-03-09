package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class sistemas extends AppCompatActivity {

    private String mode, Aip , Apatio ;
    private EditText ETip , ETpatio;
    private TextView OT , OT7;
    private ImageView OT2,OT3,OT4,OT5,OT6;
    private Button btnCambiar ;
    private ConstraintLayout constraintLayout;
    private TextView txip ,txpatio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistemas);

        mode = getIntent().getStringExtra("mode");


        SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);
        Aip = preferences.getString("Aip","");
        Apatio = preferences.getString("Apatio","");

        ETip= (EditText) findViewById(R.id.ETip);
        ETpatio = (EditText) findViewById(R.id.ETpatio);
        btnCambiar = (Button) findViewById(R.id.btnCambiar);
        constraintLayout = (ConstraintLayout) findViewById(R.id.sistemasCon);
        txip = (TextView) findViewById(R.id.textView37);
        txpatio = (TextView) findViewById(R.id.textView70);
        OT = (TextView) findViewById(R.id.OT);
        OT2 = (ImageView) findViewById(R.id.OT2);
        OT3 = (ImageView) findViewById(R.id.OT3);
        OT4 = (ImageView) findViewById(R.id.OT4);
        OT5 = (ImageView) findViewById(R.id.OT5);
        OT6 = (ImageView) findViewById(R.id.OT6);
        OT7 = (TextView) findViewById(R.id.OT7);


        ETip.setText(Aip);
        ETpatio.setText(Apatio);

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nip = ETip.getText().toString();
                String Npatio = ETpatio.getText().toString();

                SharedPreferences preferences = getSharedPreferences ("credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Aip", Nip);
                editor.putString("Apatio", Npatio);
                editor.commit();

                Intent i = new Intent(sistemas.this, splash.class);
                startActivity(i);

            }
        });

        if(mode.contains("420")){

            constraintLayout.setBackgroundColor(Color.parseColor("#060606"));
            ETip.setBackgroundColor(Color.parseColor("#57D60E"));
            ETpatio.setBackgroundColor(Color.parseColor("#57D60E"));
            btnCambiar.setBackgroundColor(Color.parseColor("#57D60E"));
            txip.setBackgroundColor(Color.parseColor("#57D60E"));
            txpatio.setBackgroundColor(Color.parseColor("#57D60E"));

            ETip.setTextColor(Color.parseColor("#000000"));
            ETpatio.setTextColor(Color.parseColor("#000000"));
            btnCambiar.setTextColor(Color.parseColor("#000000"));
            txip.setTextColor(Color.parseColor("#000000"));
            txpatio.setTextColor(Color.parseColor("#000000"));


            OT.setTextColor(Color.parseColor("#FE0ADD"));
            OT.setText("THE GAME");
            OT.setTextSize(100);
            OT.setVisibility(View.VISIBLE);

            OT2.setImageResource(R.drawable.bobcholo);
            OT2.setVisibility(View.VISIBLE);

            OT3.setImageResource(R.drawable.dxlogo);
            OT3.setVisibility(View.VISIBLE);

            OT4.setImageResource(R.drawable.dxlogo);
            OT4.setVisibility(View.VISIBLE);

            OT5.setImageResource(R.drawable.dxlogo);
            OT5.setVisibility(View.VISIBLE);

            OT6.setImageResource(R.drawable.dxlogo);
            OT6.setVisibility(View.VISIBLE);

            OT7.setTextColor(Color.parseColor("#FE0ADD"));
            OT7.setText("Que Truene Lo Que Tenga Que Tronar\n -Marba Agoriuq");
            OT7.setTextSize(30);
            OT7.setVisibility(View.VISIBLE);

        }


    }
}