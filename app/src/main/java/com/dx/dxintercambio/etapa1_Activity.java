package com.dx.dxintercambio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class etapa1_Activity extends AppCompatActivity {

    private EditText registradoPor, comentarios;
    private Spinner tipoOperacionSP, transportista, linea, estatusCaja, tipoMovimiento;
    private AutoCompleteTextView unidad, noRemolque, operador;
    private ImageView licencia;
    private Button enviar;
    private String[] tipoOpeArr;
    private String[] estatusArr;
    private String[] vacioArr;
    private String[] vacioArr2;
    private String[] tipoMovArr;
    private Bitmap actual_Bitmap;
    private String user, password, usuario, fechaHora, imageFileName, hora,
            folio, licenciaImg, unidad2, remolque2, ip ,idOperador,
            idUnidad, idRemolque , nombreUnidad , nombreOperador , nombreRemolque ;
    private int mensaje, alfa, bravo, charlie, delta, foxtrop;
    private File imageFile;
    private Uri photoURI;
    private static final int REQUEST_LICENCIA = 888;
    private final int THUMBSIZE = 128;
    private int PERMISSION_ALL = 1;
    private int azul = Color.parseColor("#074EAB");
    private int widthScreen;
    public static final int PICK_USER_PROFILE_IMAGE = 1000;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_etapa1);


        usuario = getIntent().getStringExtra("idUsuario");

        tipoOperacionSP = (Spinner) findViewById(R.id.tipoOp);
        registradoPor = (EditText) findViewById(R.id.registradoPor);
        transportista = (Spinner) findViewById(R.id.nombreTranspo);
        operador = (AutoCompleteTextView) findViewById(R.id.nombreOp);
        unidad = (AutoCompleteTextView) findViewById(R.id.numeroUnidad);
        noRemolque = (AutoCompleteTextView) findViewById(R.id.numeroRemolque);
        linea = (Spinner) findViewById(R.id.nombreLinea);
        estatusCaja = (Spinner) findViewById(R.id.spinner3);
        comentarios = (EditText) findViewById(R.id.comentario1);
        enviar = (Button) findViewById(R.id.btnDatos);
        licencia = (ImageView) findViewById(R.id.licenciaFoto);
        tipoMovimiento = (Spinner) findViewById(R.id.tipoMov);

        registradoPor.setText(user);
        registradoPor.setEnabled(false);

        licencia.setClipToOutline(true);

        vacioArr = new String[]{"Sin Seleccionar"};
        vacioArr2 = new String[]{"Sin Seleccionar"};
        tipoOpeArr = new String[]{"Sin Seleccionar", "Entrada", "Salida"};
        estatusArr = new String[]{"Sin Seleccionar", "Cargado", "Vacio", "Racks"};
        tipoMovArr = new String[]{"Sin Seleccionar", "Exportacion", "Importacion"};

        widthScreen = getApplicationContext().getResources().getDisplayMetrics().widthPixels;

        //480 cel

        if (widthScreen > 480) {

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
        } else {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mspinner_item2, tipoOpeArr);
            tipoOperacionSP.setAdapter(adapter);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.mspinner_item2, estatusArr);
            estatusCaja.setAdapter(adapter2);

            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.mspinner_item2, vacioArr);
            unidad.setAdapter(adapter3);

            ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.mspinner_item2, vacioArr2);
            noRemolque.setAdapter(adapter4);

            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, R.layout.mspinner_item2, tipoMovArr);
            tipoMovimiento.setAdapter(adapter5);
        }


        DataBaseHelper dataBaseHelper = new DataBaseHelper(etapa1_Activity.this);

        List<COperador> operadorSelect = dataBaseHelper.selectOperador();
        List<CFlota> transportistaSelect = dataBaseHelper.selectTranspo();
        List<CLinea> lineaSelect = dataBaseHelper.selectLinea();


        CFlota cFlota12 = new CFlota("-1", "404", "Otro");
        CFlota cFlota11 = new CFlota("0", "404", "Sin Seleccionar");

        transportistaSelect.add(0, cFlota11);
        transportistaSelect.add(1, cFlota12);


        CLinea cLinea12 = new CLinea("-1", "Otro");
        CLinea cLinea1 = new CLinea("0", "Sin Seleccionar");

        lineaSelect.add(0, cLinea1);
        lineaSelect.add(1, cLinea12);


        if (widthScreen > 480) {
            ArrayAdapter<CLinea> adapter2 = new ArrayAdapter<CLinea>(etapa1_Activity.this, R.layout.mspinner_item, lineaSelect);
            linea.setAdapter(adapter2);
        } else {
            ArrayAdapter<CLinea> adapter2 = new ArrayAdapter<CLinea>(etapa1_Activity.this, R.layout.mspinner_item2, lineaSelect);
            linea.setAdapter(adapter2);
            ;
        }

        if (widthScreen > 480) {
            ArrayAdapter<CFlota> adapterFlota = new ArrayAdapter<CFlota>(etapa1_Activity.this, R.layout.mspinner_item, transportistaSelect);
            transportista.setAdapter(adapterFlota);
        } else {
            ArrayAdapter<CFlota> adapterFlota = new ArrayAdapter<CFlota>(etapa1_Activity.this, R.layout.mspinner_item2, transportistaSelect);
            transportista.setAdapter(adapterFlota);
        }

        if (widthScreen > 480) {
            ArrayAdapter<COperador> adapterOperador = new ArrayAdapter<COperador>(etapa1_Activity.this, R.layout.mspinner_item, operadorSelect);
            operador.setAdapter(adapterOperador);
        } else {
            ArrayAdapter<COperador> adapterOperador = new ArrayAdapter<COperador>(etapa1_Activity.this, R.layout.mspinner_item2, operadorSelect);
            operador.setAdapter(adapterOperador);
        }


        transportista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                CFlota cFlota = (CFlota) transportista.getSelectedItem();

                String idFlota = cFlota.getId();
                String nombreS = cFlota.getNombre();
                String clave = cFlota.getClave();


                if (nombreS == "Otro" || nombreS == "Sin Seleccionar") {
                    unidad.setEnabled(false);
                    unidad.setText("");
                    idUnidad = "";

                    unidad2 = "0";

                    if (nombreS == "Otro") {
                        Toast.makeText(etapa1_Activity.this, "ESCRIBIR EN COMENTARIO : " +
                                "\n-Nombre del Transportista" +
                                "\n-Nombre Completo de Operador" +
                                "\n-Numero Economico de la Unidad", Toast.LENGTH_LONG).show();
                    }

                } else {
                    unidad.setEnabled(true);
                    unidad.setText("");
                    idUnidad = "";

                    List<CUnidad> cUnidads = dataBaseHelper.selectUnidad(clave);

                    CUnidad cUnidad11 = new CUnidad("0", "Sin Seleccionar");
                    cUnidads.add(0, cUnidad11);

                    if (widthScreen > 480) {
                        ArrayAdapter<CUnidad> adapterU = new ArrayAdapter<CUnidad>(etapa1_Activity.this, R.layout.mspinner_item, cUnidads);
                        unidad.setAdapter(adapterU);
                    } else {
                        ArrayAdapter<CUnidad> adapterU = new ArrayAdapter<CUnidad>(etapa1_Activity.this, R.layout.mspinner_item2, cUnidads);
                        unidad.setAdapter(adapterU);
                    }


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        linea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CLinea cLinea = (CLinea) linea.getSelectedItem();

                String idLinea = cLinea.getId();
                String nombreS = cLinea.getNombreLinea();

                if (nombreS == "Otro" || nombreS == "Sin Seleccionar") {
                    noRemolque.setEnabled(false);
                    noRemolque.setText("");
                    idRemolque = "";


                    remolque2 = "0";

                    if (nombreS == "Otro") {
                        Toast.makeText(etapa1_Activity.this, "ESCRIBIR EN COMENTARIO : \n-Nombre de la Linea\n-Numero Economico del Remolque", Toast.LENGTH_LONG).show();
                    }

                } else {
                    noRemolque.setEnabled(true);
                    noRemolque.setText("");
                    idRemolque = "";


                    List<CRemolque> cRemolques = dataBaseHelper.selectRemolque(idLinea);
                    CRemolque cRemolque = new CRemolque("0", "Sin Seleccionar", "");

                    cRemolques.add(0, cRemolque);

                    if (widthScreen > 480) {
                        ArrayAdapter<CRemolque> adapterR = new ArrayAdapter<CRemolque>(etapa1_Activity.this, R.layout.mspinner_item, cRemolques);
                        noRemolque.setAdapter(adapterR);
                    } else {
                        ArrayAdapter<CRemolque> adapterR = new ArrayAdapter<CRemolque>(etapa1_Activity.this, R.layout.mspinner_item2, cRemolques);
                        noRemolque.setAdapter(adapterR);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        operador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                COperador cOperador = (COperador) operador.getAdapter().getItem(i);
                idOperador = cOperador.getIdOperador();
            }
        });

        unidad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CUnidad cUnidad = (CUnidad) unidad.getAdapter().getItem(i);
                idUnidad = cUnidad.getId();



            }
        });

        noRemolque.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CRemolque cRemolque = (CRemolque) noRemolque.getAdapter().getItem(i);
                idRemolque = cRemolque.getId();



            }
        });

        licencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                licencia.setEnabled(false);
                licencia.setClickable(false);
                int TIME = 5000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        licencia.setEnabled(true);
                        licencia.setClickable(true);

                    }
                }, TIME);

                imgClick("tractor", REQUEST_LICENCIA);

            }
        });

        //btn
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviar.setEnabled(false);
                enviar.setClickable(false);
                enviar.setText("Enviando...");
                enviar.setTextColor(Color.parseColor("#074EAB"));
                enviar.setBackgroundResource(R.drawable.round_btn2);

                int TIME = 10000;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        enviar.setEnabled(true);
                        enviar.setClickable(true);
                        enviar.setText("Siguiente");
                        enviar.setTextColor(Color.parseColor("#FFFFFF"));
                        enviar.setBackgroundResource(R.drawable.round_btn);

                    }
                }, TIME);


                fechaHora = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date());
                licenciaImg = String.valueOf(licencia.getDrawable().getBounds());
                String tipoOpe = tipoOperacionSP.getSelectedItem().toString();
                String status = estatusCaja.getSelectedItem().toString();
                String tipmov = tipoMovimiento.getSelectedItem().toString();
                String tipoOperacion = "3";
                String estatus = "3";
                String movimiento = "3";


                switch (tipoOpe) {
                    case "Salida":
                        tipoOperacion = "1";
                        break;
                    case "Entrada":
                        tipoOperacion = "0";
                        break;
                    case "Sin Seleccionar":
                        tipoOperacion = "3";
                        break;
                }

                switch (status) {
                    case "Cargado":
                        estatus = "1";
                        break;
                    case "Vacio":
                        estatus = "0";
                        break;
                    case "Racks":
                        estatus = "2";
                        break;
                    case "Sin Seleccionar":
                        estatus = "3";
                        break;
                }

                switch (tipmov) {
                    case "Importacion":
                        movimiento = "1";
                        break;
                    case "Exportacion":
                        movimiento = "0";
                        break;
                    case "Sin Seleccionar":
                        movimiento = "3";
                        break;
                }


                //GET FLOTA
                CFlota cFlota = (CFlota) transportista.getSelectedItem();
                String idTransportista = cFlota.getId();

                //GET LINEA
                CLinea cLinea = (CLinea) linea.getSelectedItem();
                String idLinea = cLinea.getId();

                //GET COMENTARIO
                String comentario = comentarios.getText().toString();

                //GET USUARIO
                String idUsuario = usuario;

                String varUnidad , varRemolque , varOperador;

                varUnidad = idUnidad;
                varRemolque = idRemolque;
                varOperador = idOperador;

                nombreUnidad = unidad.getText().toString();
                nombreRemolque = noRemolque.getText().toString();
                nombreOperador = operador.getText().toString();


                if(varUnidad.length() == 0){

                   varUnidad= nombreUnidad;

                }

                if(varRemolque.length() == 0){

                    varRemolque= nombreRemolque;

                }

                if(varOperador ==  null  ){

                    varOperador= nombreOperador;

                }


                if (idTransportista == "0" || idLinea == "0") {

                        Toast.makeText(etapa1_Activity.this, "Campos vacios existente", Toast.LENGTH_LONG).show();

                } else if (idTransportista == "-1" && idLinea == "-1") {

                    if (tipoOperacion == "3" || estatus == "3" || comentario.isEmpty() || movimiento == "3" || actual_Bitmap == null) {

                        Toast.makeText(etapa1_Activity.this, "Campos vacios existente", Toast.LENGTH_LONG).show();

                    } else {

                        folio = "";
                        hora = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                        folio = hora ;
                        envioSiuuu(fechaHora, tipoOperacion, idUsuario, idTransportista, idOperador, idUnidad,
                                idRemolque, idLinea, estatus, comentario, folio, movimiento, "",nombreOperador,nombreUnidad, nombreRemolque);
                    }

                } else  {

                        if (idTransportista == "-1" && idLinea != "-1" ) {
                            if ( varUnidad=="" || varOperador == "" || estatus == "3" || tipoOperacion == "3" || movimiento == "3" || actual_Bitmap == null) {
                                Toast.makeText(etapa1_Activity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                            } else {

                                folio = "";
                                hora = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                                folio = hora ;

                                envioSiuuu(fechaHora, tipoOperacion, idUsuario, idTransportista, idOperador, idUnidad,
                                        idRemolque, idLinea, estatus, comentario, folio, movimiento, "",nombreOperador,nombreUnidad, nombreRemolque);

                            }
                        } else if (idTransportista != "1" && idLinea == "-1") {
                            if ( varRemolque == "" || varOperador == "" || estatus == "3" || tipoOperacion == "3" || movimiento == "3" || actual_Bitmap == null) {

                                Toast.makeText(etapa1_Activity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                            } else {

                                folio = "";
                                hora = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                                folio = hora ;

                                envioSiuuu(fechaHora, tipoOperacion, idUsuario, idTransportista, idOperador, idUnidad,
                                        idRemolque, idLinea, estatus, comentario, folio, movimiento, "",nombreOperador,nombreUnidad, nombreRemolque);

                            }

                        } else  {
                            if (varUnidad.isEmpty() || varRemolque.isEmpty() || varOperador == "" || estatus == "3" || tipoOperacion == "3" || movimiento == "3" ) {
                                Toast.makeText(etapa1_Activity.this, "Campos vacios existentes", Toast.LENGTH_LONG).show();
                            } else {

                                folio = "";
                                hora = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
                                folio = hora ;

                                envioSiuuu(fechaHora, tipoOperacion, idUsuario, idTransportista, idOperador, idUnidad,
                                        idRemolque, idLinea, estatus, comentario, folio, movimiento, "",nombreOperador,nombreUnidad, nombreRemolque);

                            }
                        }

                }
            }
        });


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {

                    File destPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    String path = destPath.toString();
                    File directory = new File(path);
                    File[] files = directory.listFiles();

                    for (File file : files) {
                        file.delete();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


        thread.start();


    }




    @Override
    public void onBackPressed() {

    }


    private void imgClick(String photo, int code) {

        File destPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        imageFileName = null;
        imageFile = null;
        photoURI = null;
        actual_Bitmap = null;
        imageFileName = photo + "-Folio" + folio;

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
        startActivityForResult(camera, code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_LICENCIA) {
                try {
                    actual_Bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(actual_Bitmap,
                            THUMBSIZE,
                            THUMBSIZE);
                    licencia.setImageBitmap(thumbImage);

                } catch (IOException e) {
                    licencia.setImageResource(R.drawable.ic_baseline_error_24);
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, resultData);
    }


    private void envioSiuuu(String UfechaHora, String UtipoOperacion, String UidUsuario, String UidTransportista, String UidOperador, String UidUnidad,
                            String UidRemolque, String UidLinea, String UestatusRemolque, String Ucomentario, String Ufolio, String UtipoMovimiento,
                            String Upatio , String UnombreOperador ,String UnombreUniad , String UnombreRemolque) {

        DataBaseHelper dataBaseHelper =  new DataBaseHelper(etapa1_Activity.this);

        long insertIntercambio1 = dataBaseHelper.insertIntercambioElectronico1("licenciaUrl-"+folio,"2",Ufolio,"",UidUsuario,UtipoOperacion,UtipoMovimiento,UestatusRemolque,Ucomentario,UnombreOperador,
                UidOperador,UidTransportista,UnombreUniad,UidUnidad,UidLinea,UnombreRemolque,UidRemolque,UfechaHora);

        if(insertIntercambio1 == -1){

            Toast.makeText(etapa1_Activity.this, "Error insertIntercambio1", Toast.LENGTH_LONG).show();

        }else {

            File mydir = getBaseContext().getDir("intercambios", Context.MODE_PRIVATE);
            mydir.mkdirs();
            String dirPath = mydir.getPath();
            File projDir = new File(dirPath,folio);
            projDir.mkdir();
            String childDirPath = projDir.getPath();

            if(createDirectoryAndSaveFile( actual_Bitmap,  "licenciaUrl-"+folio+".jpg", childDirPath )){
                Intent i = new Intent(etapa1_Activity.this, etapa2_Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("folio", folio);
                i.putExtra("path", childDirPath);
                i.putExtra("idUsuario", usuario);
                startActivity(i);
            }else{
                Toast.makeText(etapa1_Activity.this, "Error al guardar imagen", Toast.LENGTH_LONG).show();
            }

        }
    }

    private boolean createDirectoryAndSaveFile(Bitmap imageToSave, String fileName, String path) {

        File file = new File(path, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        licencia.setImageBitmap(null);
        actual_Bitmap = null ;
        photoURI = null;
        imageFile = null;
    }
}