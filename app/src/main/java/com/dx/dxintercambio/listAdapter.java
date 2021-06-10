package com.dx.dxintercambio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;



public class listAdapter extends ArrayAdapter<CPopulateList> {

    List<CPopulateList> intercambioList ;
int resource ;
Context mCtx ;

    public listAdapter( Context mCtx, int resource, List<CPopulateList> intercambioList) {
        super(mCtx, resource, intercambioList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.intercambioList = intercambioList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater infalter = LayoutInflater.from(mCtx);

        View view = infalter.inflate(R.layout.mlist_item, null);


        ImageView send = view.findViewById(R.id.imageView3);
        ProgressBar progressBar = view.findViewById(R.id.progressBar3);
        send.setImageResource(R.drawable.ic_subir);

        progressBar.setVisibility(View.INVISIBLE);

        TextView remolque = view.findViewById(R.id.t3);
        TextView folio = view.findViewById(R.id.t1);
        TextView usuario = view.findViewById(R.id.t5);
        TextView unidad = view.findViewById(R.id.t4);
        TextView fecha = view.findViewById(R.id.t2);


        String folioData = "Folio Interno\n"+intercambioList.get(position).getFolioInterno();
        String fechaData = "Fecha y Hora\n"+intercambioList.get(position).getFecha();
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

        String Ufolio = intercambioList.get(position).getFolioInterno();

        send.setOnClickListener(v -> {

            progressBar.setVisibility(View.VISIBLE);

            DataBaseHelper dataBaseHelper = new DataBaseHelper(mCtx);

            List<CintercambioElectronico> intercambio = dataBaseHelper.selectIntercambio(Ufolio);



            File mydir = mCtx.getDir("intercambios", Context.MODE_PRIVATE);
            String dirPath = mydir.getPath()+"/"+Ufolio;

            File folder = new File(dirPath);

            if(folder.exists()) {

                File[] allFiles = folder.listFiles(
                        (dir, name) -> (name.endsWith(".jpg")));

                String l = String.valueOf(allFiles.length);

                Toast.makeText(mCtx, "Enviando..."  + l, Toast.LENGTH_LONG).show();

            }


        });



        return view;
    }


}
