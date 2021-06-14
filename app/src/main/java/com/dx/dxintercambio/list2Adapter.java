package com.dx.dxintercambio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.UFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.util.List;


public class list2Adapter extends ArrayAdapter<CPopulateList> {

    List<CPopulateList> intercambioList ;
    int resource ;
    Context mCtx ;

    public list2Adapter(Context mCtx, int resource, List<CPopulateList> intercambioList) {
        super(mCtx, resource, intercambioList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.intercambioList = intercambioList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater infalter = LayoutInflater.from(mCtx);

        View view = infalter.inflate(R.layout.mlist_item2, null);

        ImageView cont = view.findViewById(R.id.imageView3);
        cont.setImageResource(R.drawable.ic_continuar);


        TextView remolque = view.findViewById(R.id.t3);
        TextView folio = view.findViewById(R.id.t1);
        TextView usuario = view.findViewById(R.id.t5);
        TextView unidad = view.findViewById(R.id.t4);
        TextView fecha = view.findViewById(R.id.t2);


        String folioData = "Folio Interno\n"+intercambioList.get(position).getFolioInterno();
        String fechaData = "Fecha y Hora\n"+intercambioList.get(position).getFechaInicio();
        String remolqueData = "Remolque\n"+intercambioList.get(position).getRemolque();
        String unidadData = "Unidad\n"+intercambioList.get(position).getTracto();
        String usuarioData = "Usuario\n"+intercambioList.get(position).getUsuario();

        if(intercambioList.get(position).getRemolque()== null){
            remolqueData = "Remolque\n"+"Otro";
        }

        if(intercambioList.get(position).getTracto()== null){
            unidadData = "Unidad\n"+"Otro";
        }


        remolque.setText(remolqueData);
        folio.setText(folioData);
        usuario.setText(usuarioData);
        unidad.setText(unidadData);
        fecha.setText(fechaData);


        cont.setOnClickListener(v -> {

            String Ufolio = intercambioList.get(position).getFolioInterno();
            String Uestatus = intercambioList.get(position).getEstatus();
            String path = "/data/user/0/com.dx.dxintercambio/app_intercambios/"+ Ufolio;


          switch (Uestatus){

              case "2":
                  Intent i2 = new Intent(mCtx, etapa2_Activity.class);
                  i2.putExtra("folio", Ufolio);
                  i2.putExtra("path", path);
                  i2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  mCtx.startActivity(i2);
                  break;
              case "3":
                  Intent i3 = new Intent(mCtx, etapa3_Activity.class);
                  i3.putExtra("folio", Ufolio);
                  i3.putExtra("path", path);
                  i3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  mCtx.startActivity(i3);
                  break;
              case "4":
                  Intent i4 = new Intent(mCtx, etapa4_Activity.class);
                  i4.putExtra("folio", Ufolio);
                  i4.putExtra("path", path);
                  i4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  mCtx.startActivity(i4);
                  break;
              case "5":
                  Intent i5 = new Intent(mCtx, etapa5_Activity.class);
                  i5.putExtra("folio", Ufolio);
                  i5.putExtra("path", path);
                  i5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  mCtx.startActivity(i5);
                  break;
              case "6":
                  Intent i6 = new Intent(mCtx, etapa6_Activity.class);
                  i6.putExtra("folio", Ufolio);
                  i6.putExtra("path", path);
                  i6.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  mCtx.startActivity(i6);
                  break;
              case "7":
                  Intent i7 = new Intent(mCtx, etapa7_Activity.class);
                  i7.putExtra("folio", Ufolio);
                  i7.putExtra("path", path);
                  i7.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  mCtx.startActivity(i7);
                  break;
              case "8":
                  Intent i8 = new Intent(mCtx, firmasActivity.class);
                  i8.putExtra("folio", Ufolio);
                  i8.putExtra("path", path);
                  i8.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  mCtx.startActivity(i8);
                  break;

          }


        });



        return view;
    }


}
