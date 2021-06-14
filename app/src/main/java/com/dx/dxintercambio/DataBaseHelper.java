package com.dx.dxintercambio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_UNIDAD = "unidad";
    public static final String TABLE_OPERADOR = "operador";
    public static final String TABLE_TRANSPORTISTA = "transportista";
    public static final String TABLE_REMOLQUE = "remolque";
    public static final String TABLE_LINEA = "linea";
    public static final String TABLE_LLANTA = "llanta";
    public static final String TABLE_USUARIO = "usuario";
    public static final String TABLE_INTERCAMBIO = "IntercambioElectronico";
    public static final String COLUMN_ID_UNIDAD = "idUnidad";
    public static final String COLUMN_CLAVE_UNIDAD = "claveUnidad";
    public static final String COLUMN_ID_OPERADOR = "idOperador";
    public static final String COLUMN_NOMBRE_OPERADOR = "nombreOperador";
    public static final String COLUMN_CLAVE_TRANSPORTISTA = "clave";
    public static final String COLUMN_ID_TRANSPORTISTA = "idTransportista";
    public static final String COLUMN_NOMBRE_TRANSPOSTISTA = "nombreTranspostista";
    public static final String COLUMN_ID_REMOLQUE = "idRemolque";
    public static final String COLUMN_CLAVE_REMOLQUE = "claveRemolque";
    public static final String COLUMN_ID_LINEA = "idLinea";
    public static final String COLUMN_NOMBRE_LINEA = "nombreLinea";
    public static final String COLUMN_ID_LLANTA = "idLlanta";
    public static final String COLUMN_NOMBRE_LLANTA = "nombreLlanta";
    public static final String COLUMN_ID_USUARIO = "idUsuario";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ID_LINEA_REMOLQUE = "idLinea";

    public DataBaseHelper(@Nullable Context context) {
        super(context, TABLE_INTERCAMBIO + ".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableUnidad = "CREATE TABLE " + TABLE_UNIDAD + " ( " + COLUMN_ID_UNIDAD + " TEXT , " + COLUMN_CLAVE_UNIDAD + " TEXT)";
        String createTableOperador = "CREATE TABLE " + TABLE_OPERADOR + " (" + COLUMN_ID_OPERADOR + " TEXT , " + COLUMN_NOMBRE_OPERADOR + " TEXT)";
        String createTableTransportista = "CREATE TABLE " + TABLE_TRANSPORTISTA + " ( " + COLUMN_ID_TRANSPORTISTA + " TEXT , " + COLUMN_NOMBRE_TRANSPOSTISTA + " TEXT , " + COLUMN_CLAVE_TRANSPORTISTA + " TEXT)";
        String createTableRemolque = "CREATE TABLE " + TABLE_REMOLQUE + " ( " + COLUMN_ID_REMOLQUE + " TEXT , " + COLUMN_CLAVE_REMOLQUE + " TEXT , " + COLUMN_ID_LINEA_REMOLQUE + " TEXT)";
        String createTableLinea = "CREATE TABLE " + TABLE_LINEA + " (" + COLUMN_ID_LINEA + " TEXT , " + COLUMN_NOMBRE_LINEA + " TEXT)";
        String createTableLlanta = "CREATE TABLE " + TABLE_LLANTA + " (" + COLUMN_ID_LLANTA + " TEXT , " + COLUMN_NOMBRE_LLANTA + " TEXT)";
        String createTableUsuario = "CREATE TABLE " + TABLE_USUARIO + " (" + COLUMN_ID_USUARIO + " TEXT , " + COLUMN_LOGIN + " TEXT, " + COLUMN_PASSWORD + " TEXT)";
        String createTableIntercambioElectronico = "CREATE TABLE IntercambioElectronico (" +
                "estatus  TEXT , " +
                "folio    TEXT , " +
                "terminal  TEXT , " +
                "idUsuario    TEXT , " +
                "tipoOperacion    TEXT , " +
                "tipoMovimiento    TEXT , " +
                "estatusRemolque    TEXT , " +
                "comentario2   TEXT , " +
                "nombreOperador    TEXT , " +
                "idOperador   TEXT , " +
                "idTransportista    TEXT , " +
                "unidad    TEXT , " +
                "idUnidad   TEXT , " +
                "idLinea    TEXT , " +
                "remolque    TEXT , " +
                "idRemolque   TEXT , " +
                "fechaInicio    TEXT , " +

                "tractoDefensa   TEXT , " +
                "tractoCabina   TEXT , " +
                "tractoQuintaRueda   TEXT , " +
                "tractoTuboEscape   TEXT , " +
                "tractoBaseRemolque   TEXT , " +
                "tractoTechos   TEXT , " +
                "tractoLlantas   TEXT , " +
                "tractoTanqueDiesel   TEXT , " +
                "tractoTanqueAire   TEXT , " +
                "tractoEjeTransmision   TEXT , " +
                "tractoMotor   TEXT , " +

                "remolqueInspeccionMecanica   TEXT , " +
                "remolqueLucesIzquierda   TEXT , " +
                "remolqueLucesGaliboIzqFrontalSup   TEXT , " +
                "remolqueManitasIzq   TEXT , " +
                "remolqueManivelaIzq   TEXT , " +
                "remolquePatinIzquierdo   TEXT , " +
                "remolqueCuartoLadoIzq   TEXT , " +
                "LoderaIzq   TEXT , " +
                "remolqueLucesIzqP   TEXT , " +
                "LuzAbsIzq   TEXT , " +
                "luzBarcoIzq   TEXT , " +
                "rompevientosIzq   TEXT , " +

                "remolqueLlantaIzqJumbo   TEXT , " +
                "remolqueLlantaIzqEje1Posicion1Marca   TEXT , " +
                "remolqueLlantaIzqEje1Posicion1Estatus   TEXT , " +
                "remolqueLlantaIzqEje1Posicion2Marca   TEXT , " +
                "remolqueLlantaIzqEje1Posicion2Estatus   TEXT , " +
                "remolqueLlantaIzqEje1BrilosPivote   TEXT , " +
                "remolqueLlantaIzqEje1Posicion1   TEXT , " +
                "remolqueLlantaIzqEje1Posicion2   TEXT , " +
                "remolqueLlantaIzqEje1MesaYoyo   TEXT , " +
                "remolqueLlantaIzqEje1Rin   TEXT , " +
                "remolqueLlantaIzqEje1Lodera   TEXT , " +
                "remolqueLlantaIzqEje2Posicion5Marca   TEXT , " +
                "remolqueLlantaIzqEje2Posicion5Estatus   TEXT , " +
                "remolqueLlantaIzqEje2Posicion6Marca   TEXT , " +
                "remolqueLlantaIzqEje2Posicion6Estatus   TEXT , " +
                "remolqueLlantaIzqEje2BrilosPivote   TEXT , " +
                "remolqueLlantaIzqEje2Posicion5   TEXT , " +
                "remolqueLlantaIzqEje2Posicion6   TEXT , " +
                "remolqueLlantaIzqEje2MesaYoyo   TEXT , " +
                "remolqueLlantaIzqEje2Rin   TEXT , " +
                "remolqueLlantaIzqEje2Lodera   TEXT , " +
                "remolqueChasisFrontalIzqAmortiguador   TEXT , " +
                "remolqueChasisFrontalIzqBolsaAire   TEXT , " +
                "remolqueChasisFrontalIzqGavilan   TEXT , " +
                "remolqueChasisFrontalIzqMuelle   TEXT , " +
                "remolqueChasisFrontalIzqRotachamber   TEXT , " +
                "remolqueChasisTraseroIzqAmortiguador   TEXT , " +
                "remolqueChasisTraseroIzqBolsaAire   TEXT , " +
                "remolqueChasisTraseroIzqGavilan   TEXT , " +
                "remolqueChasisTraseroIzqMuelle   TEXT , " +
                "remolqueChasisTraseroIzqRotachamber   TEXT , " +
                "remolqueIzqObservaciones   TEXT , " +


                "Placas   TEXT , " +
                "Sello1   TEXT , " +
                "Sello2   TEXT , " +
                "Sello3   TEXT , " +
                "remolquePuertasBisagrasTornillos   TEXT , " +
                "remolquePuertasDefensa   TEXT , " +
                "remolquePuertasLuzGaliboSupTraseras   TEXT , " +
                "remolquePuertasPlafonDerecho   TEXT , " +
                "remolquePuertasPlafonIzquierdo   TEXT , " +
                "remolquePlacasLuzPlaca   TEXT , " +
                "remolquePlacasPlaca   TEXT , " +
                "remolqueSello1Sello   TEXT , " +
                "remolqueSello1Seguridad   TEXT , " +
                "remolqueSello2Escotilla   TEXT , " +
                "remolqueSello2Sello   TEXT , " +
                "remolqueSello2Seguridad   TEXT , " +
                "remolqueSello2Vvtt   TEXT , " +
                "remolqueTraseraObservaciones   TEXT , " +


                "remolqueChasisTraseroDerAmortiguador   TEXT , " +
                "remolqueChasisTraseroDerBolsaAire   TEXT , " +
                "remolqueChasisTraseroDerGavilan   TEXT , " +
                "remolqueChasisTraseroDerMuelle   TEXT , " +
                "remolqueChasisTraseroDerRotachamber   TEXT , " +
                "remolqueLlantaDerEje2BrilosPivote   TEXT , " +
                "remolqueLlantaDerEje2Posicion7Marca   TEXT , " +
                "remolqueLlantaDerEje2Posicion7Estatus   TEXT , " +
                "remolqueLlantaDerEje2Posicion8Marca   TEXT , " +
                "remolqueLlantaDerEje2Posicion8Estatus   TEXT , " +
                "remolqueLlantaDerEje2Posicion7   TEXT , " +
                "remolqueLlantaDerEje2Posicion8   TEXT , " +
                "remolqueLlantaDerEje2MasaYoyo   TEXT , " +
                "remolqueLlantaDerEje2Rin   TEXT , " +
                "remolqueLlantaDerEje2Lodera   TEXT , " +
                "remolqueLlantaDerEje1BrilosPivote   TEXT , " +
                "remolqueLlantaDerEje1Posicion3Marca   TEXT , " +
                "remolqueLlantaDerEje1Posicion3Estatus   TEXT , " +
                "remolqueLlantaDerEje1Posicion4Marca   TEXT , " +
                "remolqueLlantaDerEje1Posicion4Estatus   TEXT , " +
                "remolqueLlantaDerEje1Posicion3   TEXT , " +
                "remolqueLlantaDerEje1Posicion4   TEXT , " +
                "remolqueLlantaDerEje1MasaYoyo   TEXT , " +
                "remolqueLlantaDerEje1Rin   TEXT , " +
                "remolqueLlantaDerEje1Lodera   TEXT , " +
                "remolqueChasisFrontalDerAmortiguador   TEXT , " +
                "remolqueChasisFrontalDerBolsaAire   TEXT , " +
                "remolqueChasisFrontalDerGavilan   TEXT , " +
                "remolqueChasisFrontalDerMuelle   TEXT , " +
                "remolqueChasisFrontalDerRotachamber   TEXT , " +


                "remolquePisoPLagas   TEXT , " +
                "remolqueTechoPlagas   TEXT , " +
                "remolqueDerLuces   TEXT , " +
                "remolqueDerGaliboFrontal   TEXT , " +
                "remolqueDerParedPlagas   TEXT , " +
                "remolqueDerIzqParedPlagas   TEXT , " +
                "remolqueDerPatin   TEXT , " +
                "remolqueCuartoLadoDer   TEXT , " +
                "LoderaDer   TEXT , " +
                "remolqueLucesDerP2   TEXT , " +
                "luzBarcoDer   TEXT , " +
                "rompevientosDer   TEXT , " +
                "remolqueLlantaDerJumbo   TEXT , " +
                
                "remolqueDerObservaciones   TEXT , " +



                "licenciaUrl  TEXT , " +

                "tractoIzqUrl  TEXT , " +
                "tractoFrenteUrl  TEXT , " +
                "tractoDerUrl TEXT , " +

                "noEcoUrl TEXT , " +
                "vinUrl TEXT , " +
                "remolqueCostadoTraseroIzqUrl TEXT , " +
                "remolqueCostadoFrenteIzquierdoUrl TEXT , " +

                "remolqueLlantaIzqEje1FotoUrl TEXT , " +
                "remolqueLlantaIzqEje2FotoUrl TEXT , " +
                "remolqueChasisFrontalIzqFotoUrl TEXT , " +
                "remolqueChasisTraseroIzqFotoUrl TEXT , " +
                "remolqueIzqDano1FotoUrl TEXT , " +
                "remolqueIzqDano2FotoUrl TEXT , " +
                "remolqueIzqDano3FotoUrl TEXT , " +
                "remolqueIzqDano4FotoUrl TEXT , " +

                "remolquePuertasFotoUrl TEXT , " +
                "remolquePlacasFotoUrl TEXT , " +
                "remolqueSello1FotoUrl TEXT , " +
                "remolqueSello2FotoUrl TEXT , " +
                "remolqueSello3FotoUrl TEXT , " +
                "remolqueTraseroDano1FotoUrl TEXT , " +
                "remolqueTraseroDano2FotoUrl TEXT , " +
                "remolqueTraseroDano3FotoUrl TEXT , " +
                "remolqueTraseroDano4FotoUrl TEXT , " +

                "remolqueCostadoTraseroDerUrl TEXT , " +
                "remolqueCostadoFrenteDerechoUrl TEXT , " +

                "remolqueLlantaDerEje1FotoUrl TEXT , " +
                "remolqueLlantaDerEje2FotoUrl TEXT , " +
                "remolqueChasisFrontalDerFotoUrl TEXT , " +
                "remolqueChasisTraseroDerFotoUrl TEXT , " +

                "remolqueDerDano1FotoUrl TEXT , " +
                "remolqueDerDano2FotoUrl TEXT , " +
                "remolqueDerDano3FotoUrl TEXT , " +
                "remolqueDerDano4FotoUrl TEXT , " +

                "firmaOperadorUrl TEXT , " +
                "firmaIntercambistaUrl TEXT , " +


                "fechaFin   TEXT )";


        db.execSQL(createTableUnidad);
        db.execSQL(createTableOperador);
        db.execSQL(createTableTransportista);
        db.execSQL(createTableRemolque);
        db.execSQL(createTableLinea);
        db.execSQL(createTableLlanta);
        db.execSQL(createTableUsuario);
        db.execSQL(createTableIntercambioElectronico);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insertUnidad(CUnidad cUnidad){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_UNIDAD,cUnidad.getId());
        cv.put(COLUMN_CLAVE_UNIDAD,cUnidad.getClave());


        long insert = db.insert(TABLE_UNIDAD, null, cv);

        if(insert == -1){
            return false;
            }else{
            return true;
            }
    }

    public boolean insertOperador(COperador cOperador){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_OPERADOR,cOperador.getIdOperador());
        cv.put(COLUMN_NOMBRE_OPERADOR,cOperador.getNombreCompleto());

        long insert = db.insert(TABLE_OPERADOR, null, cv);

        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean insertTranspo(CFlota cFlota){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_TRANSPORTISTA,cFlota.getId());
        cv.put(COLUMN_NOMBRE_TRANSPOSTISTA,cFlota.getNombre());
        cv.put(COLUMN_CLAVE_TRANSPORTISTA,cFlota.getClave());

        long insert = db.insert(TABLE_TRANSPORTISTA, null, cv);

        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean insertRemolque(CRemolque cRemolque){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_REMOLQUE,cRemolque.getId());
        cv.put(COLUMN_CLAVE_REMOLQUE,cRemolque.getRemolques());
        cv.put(COLUMN_ID_LINEA_REMOLQUE,cRemolque.getIdLinea());

        long insert = db.insert(TABLE_REMOLQUE, null, cv);

        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean insertLinea(CLinea cLinea){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_LINEA,cLinea.getId());
        cv.put(COLUMN_NOMBRE_LINEA,cLinea.getNombreLinea());

        long insert = db.insert(TABLE_LINEA, null, cv);

        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean insertLlanta(CLlanta cLlanta){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_LLANTA,cLlanta.getId());
        cv.put(COLUMN_NOMBRE_LLANTA,cLlanta.getNombre());

        long insert = db.insert(TABLE_LLANTA, null, cv);

        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean insertUsuario(CUsuarioRel cUsuarioRel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_USUARIO,cUsuarioRel.getId());
        cv.put(COLUMN_LOGIN,cUsuarioRel.getLogin());
        cv.put(COLUMN_PASSWORD,cUsuarioRel.getPassword());

        long insert = db.insert(TABLE_USUARIO, null, cv);

        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public List<COperador> selectOperador() {

        List<COperador> returnList = new ArrayList<>();

            String queryString = "SELECT * FROM " + TABLE_OPERADOR ;

            SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
             do {
                 String id = cursor.getString(0);
                 String nombre = cursor.getString(1);

                COperador cOperador = new COperador (id,nombre);
                returnList.add(cOperador);

             }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<CFlota> selectTranspo() {

        List<CFlota> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_TRANSPORTISTA ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);
                String clave = cursor.getString(2);

                CFlota cFlota = new CFlota (id,clave,nombre);
                returnList.add(cFlota);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<CLinea> selectLinea() {

        List<CLinea> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_LINEA ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);

                CLinea cLinea = new CLinea (id,nombre);
                returnList.add(cLinea);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<CUnidad> selectUnidad(String idTransportista ) {

        List<CUnidad> returnList = new ArrayList<>();

        String queryString =  null;

        if(idTransportista == ""){
            idTransportista = "4";
            queryString = "SELECT * FROM " + TABLE_UNIDAD + " WHERE "+ COLUMN_CLAVE_UNIDAD + " LIKE  " + "'" + idTransportista + "%'" ;
        }else {
            queryString = "SELECT idUnidad , replace(claveUnidad , '" + idTransportista + "' , ' ' ) FROM " + TABLE_UNIDAD + " WHERE "+ COLUMN_CLAVE_UNIDAD + " LIKE  " + "'" + idTransportista + "%'" ;
        }



        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);

                CUnidad cUnidad = new CUnidad (id,nombre);
                returnList.add(cUnidad);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<CRemolque> selectRemolque(String idLinea) {

        List<CRemolque> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_REMOLQUE + " WHERE idLinea = " + idLinea ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);

                CRemolque cRemolque = new CRemolque (id,nombre,"");
                returnList.add(cRemolque);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<CLlanta> selectLlanta() {

        List<CLlanta> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_LLANTA ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);

                CLlanta cLlanta = new CLlanta (id,nombre);
                returnList.add(cLlanta);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<CUsuarioRel> login(String usuario, String contra) {

        List<CUsuarioRel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_USUARIO + " WHERE " +COLUMN_LOGIN +" = '" + usuario + "' AND " + COLUMN_PASSWORD + " = '" + contra+ "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);

                CUsuarioRel cUsuarioRel = new CUsuarioRel (id,nombre,"");
                returnList.add(cUsuarioRel);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public long insertIntercambioElectronico1( String licenciaUrl, String estatus ,String folio,String terminal,String idUsuario,String tipoOperacion,String tipoMovimiento,String estatusRemolque,
                                               String comentario2,String nombreOperador,String idOperador,String idTransportista,String unidad,String idUnidad,String idLinea,String remolque
                                                ,String idRemolque,String fechaInicio){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("licenciaUrl",licenciaUrl);
        cv.put("estatus",estatus);
        cv.put("folio",folio);
        cv.put("terminal","");
        cv.put("idUsuario",idUsuario);
        cv.put("tipoOperacion",tipoOperacion);
        cv.put("tipoMovimiento",tipoMovimiento);
        cv.put("estatusRemolque",estatusRemolque);
        cv.put("comentario2",comentario2);
        cv.put("nombreOperador",nombreOperador);
        cv.put("idOperador",idOperador);
        cv.put("idTransportista",idTransportista);
        cv.put("unidad",unidad);
        cv.put("idUnidad",idUnidad);
        cv.put("idLinea",idLinea);
        cv.put("remolque",remolque);
        cv.put("idRemolque",idRemolque);
        cv.put("fechaInicio",fechaInicio);



        long insert = db.insert(TABLE_INTERCAMBIO,null,cv);

        if(insert == -1){
            return -1;
        }else{
            return insert;
        }
    }

    public long insertIntercambioElectronico2(  String tractoIzqUrl, String tractoFrenteUrl, String tractoDerUrl,String estatus ,String folio,String tractoDefensa,String tractoCabina,String tractoQuintaRueda,String tractoTuboEscape,String tractoBaseRemolque,
                                               String tractoTechos,String tractoLlantas,String tractoTanqueDiesel,String tractoTanqueAire,String tractoEjeTransmision,String tractoMotor){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("tractoIzqUrl",tractoIzqUrl);
        cv.put("tractoFrenteUrl",tractoFrenteUrl);
        cv.put("tractoDerUrl",tractoDerUrl);
        cv.put("estatus",estatus);
        cv.put("tractoDefensa",tractoDefensa);
        cv.put("tractoCabina",tractoCabina);
        cv.put("tractoQuintaRueda",tractoQuintaRueda);
        cv.put("tractoTuboEscape",tractoTuboEscape);
        cv.put("tractoBaseRemolque",tractoBaseRemolque);
        cv.put("tractoTechos",tractoTechos);
        cv.put("tractoLlantas",tractoLlantas);
        cv.put("tractoTanqueDiesel",tractoTanqueDiesel);
        cv.put("tractoTanqueAire",tractoTanqueAire);
        cv.put("tractoEjeTransmision",tractoEjeTransmision);
        cv.put("tractoMotor",tractoMotor);



        long update = db.update(TABLE_INTERCAMBIO,cv,"folio = ?",new String[]{folio});

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico3( String noEcoUrl,String vinUrl,String remolqueCostadoTraseroIzqUrl,String remolqueCostadoFrenteIzquierdoUrl,String estatus ,String folio,String remolqueInspeccionMecanica,String remolqueLucesIzquierda,String remolqueLucesGaliboIzqFrontalSup,
                                               String remolqueManitasIzq,String remolqueManivelaIzq, String remolquePatinIzquierdo,String remolqueCuartoLadoIzq,
                                               String LoderaIzq,String remolqueLucesIzqP,String LuzAbsIzq,String luzBarcoIzq,String rompevientosIzq){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("noEcoUrl",noEcoUrl);
        cv.put("vinUrl",vinUrl);
        cv.put("remolqueCostadoTraseroIzqUrl",remolqueCostadoTraseroIzqUrl);
        cv.put("remolqueCostadoFrenteIzquierdoUrl",remolqueCostadoFrenteIzquierdoUrl);
        cv.put("estatus",estatus);
        cv.put("remolqueInspeccionMecanica",remolqueInspeccionMecanica);
        cv.put("remolqueLucesIzquierda",remolqueLucesIzquierda);
        cv.put("remolqueLucesGaliboIzqFrontalSup",remolqueLucesGaliboIzqFrontalSup);
        cv.put("remolqueManitasIzq",remolqueManitasIzq);
        cv.put("remolqueManivelaIzq",remolqueManivelaIzq);
        cv.put("remolquePatinIzquierdo",remolquePatinIzquierdo);
        cv.put("remolqueCuartoLadoIzq",remolqueCuartoLadoIzq);
        cv.put("LoderaIzq",LoderaIzq);
        cv.put("remolqueLucesIzqP",remolqueLucesIzqP);
        cv.put("LuzAbsIzq",LuzAbsIzq);
        cv.put("luzBarcoIzq",luzBarcoIzq);
        cv.put("rompevientosIzq",rompevientosIzq);


        long update = db.update(TABLE_INTERCAMBIO,cv,"folio = ?",new String[]{folio});

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico4( String remolqueLlantaIzqEje1FotoUrl, String remolqueLlantaIzqEje2FotoUrl, String remolqueChasisFrontalIzqFotoUrl, String remolqueChasisTraseroIzqFotoUrl, String remolqueIzqDano1FotoUrl, String remolqueIzqDano2FotoUrl, String remolqueIzqDano3FotoUrl, String remolqueIzqDano4FotoUrl, String estatus ,String folio,String remolqueLlantaIzqJumbo,String remolqueLlantaIzqEje1Posicion1Marca,String remolqueLlantaIzqEje1Posicion1Estatus,
                                               String remolqueLlantaIzqEje1Posicion2Marca,String remolqueLlantaIzqEje1Posicion2Estatus, String remolqueLlantaIzqEje1BrilosPivote,String remolqueLlantaIzqEje1Posicion1,
                                               String remolqueLlantaIzqEje1Posicion2,String remolqueLlantaIzqEje1MesaYoyo, String remolqueLlantaIzqEje1Rin,String remolqueLlantaIzqEje1Lodera,
                                               String remolqueLlantaIzqEje2Posicion5Marca,String remolqueLlantaIzqEje2Posicion5Estatus, String remolqueLlantaIzqEje2Posicion6Marca,String remolqueLlantaIzqEje2Posicion6Estatus,
                                               String remolqueLlantaIzqEje2BrilosPivote,String remolqueLlantaIzqEje2Posicion5, String remolqueLlantaIzqEje2Posicion6,String remolqueLlantaIzqEje2MesaYoyo,
                                               String remolqueLlantaIzqEje2Rin,String remolqueLlantaIzqEje2Lodera, String remolqueChasisFrontalIzqAmortiguador,String remolqueChasisFrontalIzqBolsaAire,
                                               String remolqueChasisFrontalIzqGavilan,String remolqueChasisFrontalIzqMuelle, String remolqueChasisFrontalIzqRotachamber,String remolqueChasisTraseroIzqAmortiguador,
                                               String remolqueChasisTraseroIzqBolsaAire,String remolqueChasisTraseroIzqGavilan ,String remolqueChasisTraseroIzqMuelle ,String remolqueChasisTraseroIzqRotachamber ,String remolqueIzqObservaciones ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("remolqueLlantaIzqEje1FotoUrl",remolqueLlantaIzqEje1FotoUrl);
        cv.put("remolqueLlantaIzqEje2FotoUrl",remolqueLlantaIzqEje2FotoUrl);
        cv.put("remolqueChasisFrontalIzqFotoUrl",remolqueChasisFrontalIzqFotoUrl);
        cv.put("remolqueChasisTraseroIzqFotoUrl",remolqueChasisTraseroIzqFotoUrl);
        cv.put("remolqueIzqDano1FotoUrl",remolqueIzqDano1FotoUrl);
        cv.put("remolqueIzqDano2FotoUrl",remolqueIzqDano2FotoUrl);
        cv.put("remolqueIzqDano3FotoUrl",remolqueIzqDano3FotoUrl);
        cv.put("remolqueIzqDano4FotoUrl",remolqueIzqDano4FotoUrl);
        cv.put("estatus",estatus);
        cv.put("remolqueLlantaIzqJumbo",remolqueLlantaIzqJumbo);
        cv.put("remolqueLlantaIzqEje1Posicion1Marca",remolqueLlantaIzqEje1Posicion1Marca);
        cv.put("remolqueLlantaIzqEje1Posicion1Estatus",remolqueLlantaIzqEje1Posicion1Estatus);
        cv.put("remolqueLlantaIzqEje1Posicion2Marca",remolqueLlantaIzqEje1Posicion2Marca);
        cv.put("remolqueLlantaIzqEje1Posicion2Estatus",remolqueLlantaIzqEje1Posicion2Estatus);
        cv.put("remolqueLlantaIzqEje1BrilosPivote",remolqueLlantaIzqEje1BrilosPivote);
        cv.put("remolqueLlantaIzqEje1Posicion1",remolqueLlantaIzqEje1Posicion1);
        cv.put("remolqueLlantaIzqEje1Posicion2",remolqueLlantaIzqEje1Posicion2);
        cv.put("remolqueLlantaIzqEje1MesaYoyo",remolqueLlantaIzqEje1MesaYoyo);
        cv.put("remolqueLlantaIzqEje1Rin",remolqueLlantaIzqEje1Rin);
        cv.put("remolqueLlantaIzqEje1Lodera",remolqueLlantaIzqEje1Lodera);
        cv.put("remolqueLlantaIzqEje2Posicion5Marca",remolqueLlantaIzqEje2Posicion5Marca);
        cv.put("remolqueLlantaIzqEje2Posicion5Estatus",remolqueLlantaIzqEje2Posicion5Estatus);
        cv.put("remolqueLlantaIzqEje2Posicion6Marca",remolqueLlantaIzqEje2Posicion6Marca);
        cv.put("remolqueLlantaIzqEje2Posicion6Estatus",remolqueLlantaIzqEje2Posicion6Estatus);
        cv.put("remolqueLlantaIzqEje2BrilosPivote",remolqueLlantaIzqEje2BrilosPivote);
        cv.put("remolqueLlantaIzqEje2Posicion5",remolqueLlantaIzqEje2Posicion5);
        cv.put("remolqueLlantaIzqEje2Posicion6",remolqueLlantaIzqEje2Posicion6);
        cv.put("remolqueLlantaIzqEje2MesaYoyo",remolqueLlantaIzqEje2MesaYoyo);
        cv.put("remolqueLlantaIzqEje2Rin",remolqueLlantaIzqEje2Rin);
        cv.put("remolqueLlantaIzqEje2Lodera",remolqueLlantaIzqEje2Lodera);
        cv.put("remolqueChasisFrontalIzqAmortiguador",remolqueChasisFrontalIzqAmortiguador);
        cv.put("remolqueChasisFrontalIzqBolsaAire",remolqueChasisFrontalIzqBolsaAire);
        cv.put("remolqueChasisFrontalIzqGavilan",remolqueChasisFrontalIzqGavilan);
        cv.put("remolqueChasisFrontalIzqMuelle",remolqueChasisFrontalIzqMuelle);
        cv.put("remolqueChasisFrontalIzqRotachamber",remolqueChasisFrontalIzqRotachamber);
        cv.put("remolqueChasisTraseroIzqAmortiguador",remolqueChasisTraseroIzqAmortiguador);
        cv.put("remolqueChasisTraseroIzqBolsaAire",remolqueChasisTraseroIzqBolsaAire);
        cv.put("remolqueChasisTraseroIzqGavilan",remolqueChasisTraseroIzqGavilan);
        cv.put("remolqueChasisTraseroIzqMuelle",remolqueChasisTraseroIzqMuelle);
        cv.put("remolqueChasisTraseroIzqRotachamber",remolqueChasisTraseroIzqRotachamber);
        cv.put("remolqueIzqObservaciones",remolqueIzqObservaciones);



        long update = db.update(TABLE_INTERCAMBIO,cv,"folio = ?",new String[]{folio});

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico5( String remolquePuertasFotoUrl,String remolquePlacasFotoUrl,String remolqueSello1FotoUrl,String remolqueSello2FotoUrl,String remolqueSello3FotoUrl,String remolqueTraseroDano1FotoUrl,String remolqueTraseroDano2FotoUrl,String remolqueTraseroDano3FotoUrl,String remolqueTraseroDano4FotoUrl ,String estatus ,String folio,String remolqueTraseraObservaciones,String Placas,String Sello1,String Sello2,
                                               String Sello3,String remolquePuertasBisagrasTornillos, String remolquePuertasDefensa,String remolquePuertasLuzGaliboSupTraseras,
                                               String remolquePuertasPlafonDerecho,String remolquePuertasPlafonIzquierdo, String remolquePlacasLuzPlaca,String remolquePlacasPlaca,
                                               String remolqueSello1Sello,String remolqueSello1Seguridad, String remolqueSello2Escotilla,String remolqueSello2Sello,
                                               String remolqueSello2Seguridad,String remolqueSello2Vvtt ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put("remolquePuertasFotoUrl",remolquePuertasFotoUrl);
        cv.put("remolquePlacasFotoUrl",remolquePlacasFotoUrl);
        cv.put("remolqueSello1FotoUrl",remolqueSello1FotoUrl);
        cv.put("remolqueSello2FotoUrl",remolqueSello2FotoUrl);
        cv.put("remolqueSello3FotoUrl",remolqueSello3FotoUrl);
        cv.put("remolqueTraseroDano1FotoUrl",remolqueTraseroDano1FotoUrl);
        cv.put("remolqueTraseroDano2FotoUrl",remolqueTraseroDano2FotoUrl);
        cv.put("remolqueTraseroDano3FotoUrl",remolqueTraseroDano3FotoUrl);
        cv.put("remolqueTraseroDano4FotoUrl",remolqueTraseroDano4FotoUrl);
        cv.put("remolqueTraseraObservaciones",remolqueTraseraObservaciones);
        cv.put("estatus",estatus);
        cv.put("Placas",Placas);
        cv.put("Sello1",Sello1);
        cv.put("Sello2",Sello2);
        cv.put("Sello3",Sello3);
        cv.put("remolquePuertasBisagrasTornillos",remolquePuertasBisagrasTornillos);
        cv.put("remolquePuertasDefensa",remolquePuertasDefensa);
        cv.put("remolquePuertasLuzGaliboSupTraseras",remolquePuertasLuzGaliboSupTraseras);
        cv.put("remolquePuertasPlafonDerecho",remolquePuertasPlafonDerecho);
        cv.put("remolquePuertasPlafonIzquierdo",remolquePuertasPlafonIzquierdo);
        cv.put("remolquePlacasLuzPlaca",remolquePlacasLuzPlaca);
        cv.put("remolquePlacasPlaca",remolquePlacasPlaca);
        cv.put("remolqueSello1Sello",remolqueSello1Sello);
        cv.put("remolqueSello1Seguridad",remolqueSello1Seguridad);
        cv.put("remolqueSello2Escotilla",remolqueSello2Escotilla);
        cv.put("remolqueSello2Sello",remolqueSello2Sello);
        cv.put("remolqueSello2Seguridad",remolqueSello2Seguridad);
        cv.put("remolqueSello2Vvtt",remolqueSello2Vvtt);



        long update = db.update(TABLE_INTERCAMBIO,cv,"folio = ?",new String[]{folio});

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico6( String remolqueLlantaDerEje1FotoUrl,String remolqueLlantaDerEje2FotoUrl,String remolqueChasisFrontalDerFotoUrl,String remolqueChasisTraseroDerFotoUrl,String estatus ,String folio, String remolqueLlantaDerJumbo,String remolqueChasisTraseroDerAmortiguador,String remolqueChasisTraseroDerBolsaAire,String remolqueChasisTraseroDerGavilan,
                                               String remolqueChasisTraseroDerMuelle,String remolqueChasisTraseroDerRotachamber, String remolqueLlantaDerEje2BrilosPivote,String remolqueLlantaDerEje2Posicion7Marca,
                                               String remolqueLlantaDerEje2Posicion7Estatus,String remolqueLlantaDerEje2Posicion8Marca, String remolqueLlantaDerEje2Posicion8Estatus,String remolqueLlantaDerEje2Posicion7,
                                               String remolqueLlantaDerEje2Posicion8,String remolqueLlantaDerEje2MasaYoyo, String remolqueLlantaDerEje2Rin,String remolqueLlantaDerEje2Lodera,
                                               String remolqueLlantaDerEje1BrilosPivote,String remolqueLlantaDerEje1Posicion3Marca, String remolqueLlantaDerEje1Posicion3Estatus,String remolqueLlantaDerEje1Posicion4Marca,
                                               String remolqueLlantaDerEje1Posicion4Estatus,String remolqueLlantaDerEje1Posicion3, String remolqueLlantaDerEje1Posicion4,String remolqueLlantaDerEje1MasaYoyo,
                                               String remolqueLlantaDerEje1Rin,String remolqueLlantaDerEje1Lodera,String remolqueChasisFrontalDerAmortiguador,String remolqueChasisFrontalDerBolsaAire,String remolqueChasisFrontalDerGavilan,
                                               String remolqueChasisFrontalDerMuelle,String remolqueChasisFrontalDerRotachamber) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("remolqueLlantaDerEje1FotoUrl",remolqueLlantaDerEje1FotoUrl);
        cv.put("remolqueLlantaDerEje2FotoUrl",remolqueLlantaDerEje2FotoUrl);
        cv.put("remolqueChasisFrontalDerFotoUrl",remolqueChasisFrontalDerFotoUrl);
        cv.put("remolqueChasisTraseroDerFotoUrl",remolqueChasisTraseroDerFotoUrl);

        cv.put("remolqueLlantaDerJumbo",remolqueLlantaDerJumbo);
        cv.put("estatus",estatus);
        cv.put("remolqueChasisTraseroDerAmortiguador",remolqueChasisTraseroDerAmortiguador);
        cv.put("remolqueChasisTraseroDerBolsaAire",remolqueChasisTraseroDerBolsaAire);
        cv.put("remolqueChasisTraseroDerGavilan",remolqueChasisTraseroDerGavilan);
        cv.put("remolqueChasisTraseroDerMuelle",remolqueChasisTraseroDerMuelle);
        cv.put("remolqueChasisTraseroDerRotachamber",remolqueChasisTraseroDerRotachamber);
        cv.put("remolqueLlantaDerEje2BrilosPivote",remolqueLlantaDerEje2BrilosPivote);
        cv.put("remolqueLlantaDerEje2Posicion7Marca",remolqueLlantaDerEje2Posicion7Marca);
        cv.put("remolqueLlantaDerEje2Posicion7Estatus",remolqueLlantaDerEje2Posicion7Estatus);
        cv.put("remolqueLlantaDerEje2Posicion8Marca",remolqueLlantaDerEje2Posicion8Marca);
        cv.put("remolqueLlantaDerEje2Posicion8Estatus",remolqueLlantaDerEje2Posicion8Estatus);
        cv.put("remolqueLlantaDerEje2Posicion7",remolqueLlantaDerEje2Posicion7);
        cv.put("remolqueLlantaDerEje2Posicion8",remolqueLlantaDerEje2Posicion8);
        cv.put("remolqueLlantaDerEje2MasaYoyo",remolqueLlantaDerEje2MasaYoyo);
        cv.put("remolqueLlantaDerEje2Rin",remolqueLlantaDerEje2Rin);
        cv.put("remolqueLlantaDerEje2Lodera",remolqueLlantaDerEje2Lodera);
        cv.put("remolqueLlantaDerEje1BrilosPivote",remolqueLlantaDerEje1BrilosPivote);
        cv.put("remolqueLlantaDerEje1Posicion3Marca",remolqueLlantaDerEje1Posicion3Marca);
        cv.put("remolqueLlantaDerEje1Posicion3Estatus",remolqueLlantaDerEje1Posicion3Estatus);
        cv.put("remolqueLlantaDerEje1Posicion4Marca",remolqueLlantaDerEje1Posicion4Marca);
        cv.put("remolqueLlantaDerEje1Posicion4Estatus",remolqueLlantaDerEje1Posicion4Estatus);
        cv.put("remolqueLlantaDerEje1Posicion3",remolqueLlantaDerEje1Posicion3);
        cv.put("remolqueLlantaDerEje1Posicion4",remolqueLlantaDerEje1Posicion4);
        cv.put("remolqueLlantaDerEje1MasaYoyo",remolqueLlantaDerEje1MasaYoyo);
        cv.put("remolqueLlantaDerEje1Rin",remolqueLlantaDerEje1Rin);
        cv.put("remolqueLlantaDerEje1Lodera",remolqueLlantaDerEje1Lodera);
        cv.put("remolqueChasisFrontalDerAmortiguador",remolqueChasisFrontalDerAmortiguador);
        cv.put("remolqueChasisFrontalDerBolsaAire",remolqueChasisFrontalDerBolsaAire);
        cv.put("remolqueChasisFrontalDerGavilan",remolqueChasisFrontalDerGavilan);
        cv.put("remolqueChasisFrontalDerMuelle",remolqueChasisFrontalDerMuelle);
        cv.put("remolqueChasisFrontalDerRotachamber",remolqueChasisFrontalDerRotachamber);


        long update = db.update(TABLE_INTERCAMBIO,cv,"folio = ?",new String[]{folio});

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico7 (String remolqueCostadoTraseroDerUrl,String remolqueCostadoFrenteDerechoUrl,String remolqueDerDano1FotoUrl,String remolqueDerDano2FotoUrl,String remolqueDerDano3FotoUrl,String remolqueDerDano4FotoUrl,String estatus ,String folio,String remolqueDerObservaciones ,String remolquePisoPLagas,String remolqueTechoPlagas,String remolqueDerLuces,
                                               String remolqueDerGaliboFrontal,String remolqueDerParedPlagas, String remolqueDerIzqParedPlagas,String remolqueDerPatin,
                                               String remolqueCuartoLadoDer,String LoderaDer, String remolqueLucesDerP2,String luzBarcoDer,
                                               String rompevientosDer ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("remolqueCostadoTraseroDerUrl",remolqueCostadoTraseroDerUrl);
        cv.put("remolqueCostadoFrenteDerechoUrl",remolqueCostadoFrenteDerechoUrl);
        cv.put("remolqueDerDano1FotoUrl",remolqueDerDano1FotoUrl);
        cv.put("remolqueDerDano2FotoUrl",remolqueDerDano2FotoUrl);
        cv.put("remolqueDerDano3FotoUrl",remolqueDerDano3FotoUrl);
        cv.put("remolqueDerDano4FotoUrl",remolqueDerDano4FotoUrl);
        cv.put("estatus",estatus);
        cv.put("remolqueDerObservaciones",remolqueDerObservaciones);
        cv.put("remolquePisoPLagas",remolquePisoPLagas);
        cv.put("remolqueTechoPlagas",remolqueTechoPlagas);
        cv.put("remolqueDerLuces",remolqueDerLuces);
        cv.put("remolqueDerGaliboFrontal",remolqueDerGaliboFrontal);
        cv.put("remolqueDerParedPlagas",remolqueDerParedPlagas);
        cv.put("remolqueDerIzqParedPlagas",remolqueDerIzqParedPlagas);
        cv.put("remolqueDerPatin",remolqueDerPatin);
        cv.put("remolqueCuartoLadoDer",remolqueCuartoLadoDer);
        cv.put("LoderaDer",LoderaDer);
        cv.put("remolqueLucesDerP2",remolqueLucesDerP2);
        cv.put("luzBarcoDer",luzBarcoDer);
        cv.put("rompevientosDer",rompevientosDer);


        long update = db.update(TABLE_INTERCAMBIO,cv,"folio = ?",new String[]{folio});

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico200( String folio ,String fechaFin, String firmaOperadorUrl,String firmaIntercambistaUrl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("fechaFin",fechaFin);
        cv.put("firmaOperadorUrl",firmaOperadorUrl);
        cv.put("firmaIntercambistaUrl",firmaIntercambistaUrl);

        long update = db.update(TABLE_INTERCAMBIO,cv,"folio = ?",new String[]{folio});

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }



    public List<CPopulateList> selectListIntercambioTerminado() {

        List<CPopulateList> returnList = new ArrayList<>();

        String queryString = "SELECT i.folio,i.fechaFin,u.claveUnidad,us.login,r.claveRemolque,i.estatus,i.fechaFin  " +
                "FROM " + TABLE_INTERCAMBIO + " i LEFT JOIN unidad u ON u.idUnidad = i.idUnidad LEFT JOIN usuario us ON us.idUsuario = i.idUsuario LEFT JOIN remolque r ON r.idRemolque = i.idRemolque "
                + " WHERE i.fechaFin IS NOT null ";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {
                
                String folio  = cursor.getString(0);
                String fechaInicio  = cursor.getString(1);
                String claveUnidad  = cursor.getString(2);
                String login  = cursor.getString(3);
                String claveRemolque  = cursor.getString(4);
                String estatus  = cursor.getString(5);
                String fechaFin  = cursor.getString(6);

                CPopulateList cPopulateList = new CPopulateList ( folio  ,
                        fechaInicio ,
                        claveRemolque  ,
                        login  ,
                        claveUnidad ,
                        estatus,
                        fechaFin
                );

                returnList.add(cPopulateList);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<CintercambioElectronico> selectIntercambio( String Zfolio) {

        List<CintercambioElectronico> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_INTERCAMBIO + " WHERE folio = " + Zfolio;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {

                String estatus  = cursor.getString(0);
                String folio  = cursor.getString(1);
                String terminal  = cursor.getString(2);
                String idUsuario  = cursor.getString(3);
                String tipoOperacion  = cursor.getString(4);
                String tipoMovimiento  = cursor.getString(5);
                String estatusRemolque  = cursor.getString(6);
                String comentario2  = cursor.getString(7);
                String nombreOperador  = cursor.getString(8);
                String idOperador  = cursor.getString(9);
                String idTransportista  = cursor.getString(10);
                String unidad  = cursor.getString(11);
                String idUnidad  = cursor.getString(12);
                String idLinea  = cursor.getString(13);
                String remolque  = cursor.getString(14);
                String idRemolque  = cursor.getString(15);
                String fechaInicio  = cursor.getString(16);
                String tractoDefensa  = cursor.getString(17);
                String tractoCabina  = cursor.getString(18);
                String tractoQuintaRueda  = cursor.getString(19);
                String tractoTuboEscape  = cursor.getString(20);
                String tractoBaseRemolque  = cursor.getString(21);
                String tractoTechos  = cursor.getString(22);
                String tractoLlantas  = cursor.getString(23);
                String tractoTanqueDiesel  = cursor.getString(24);
                String tractoTanqueAire  = cursor.getString(25);
                String tractoEjeTransmision  = cursor.getString(26);
                String tractoMotor  = cursor.getString(27);
                String remolqueInspeccionMecanica  = cursor.getString(28);
                String remolqueLucesIzquierda  = cursor.getString(29);
                String remolqueLucesGaliboIzqFrontalSup  = cursor.getString(30);
                String remolqueManitasIzq  = cursor.getString(31);
                String remolqueManivelaIzq  = cursor.getString(32);
                String remolquePatinIzquierdo  = cursor.getString(33);
                String remolqueCuartoLadoIzq  = cursor.getString(34);
                String LoderaIzq  = cursor.getString(35);
                String remolqueLucesIzqP  = cursor.getString(36);
                String LuzAbsIzq  = cursor.getString(37);
                String luzBarcoIzq  = cursor.getString(38);
                String rompevientosIzq  = cursor.getString(39);
                String remolqueLlantaIzqJumbo  = cursor.getString(40);
                String remolqueLlantaIzqEje1Posicion1Marca  = cursor.getString(41);
                String remolqueLlantaIzqEje1Posicion1Estatus  = cursor.getString(42);
                String remolqueLlantaIzqEje1Posicion2Marca  = cursor.getString(43);
                String remolqueLlantaIzqEje1Posicion2Estatus  = cursor.getString(44);
                String remolqueLlantaIzqEje1BrilosPivote  = cursor.getString(45);
                String remolqueLlantaIzqEje1Posicion1  = cursor.getString(46);
                String remolqueLlantaIzqEje1Posicion2  = cursor.getString(47);
                String remolqueLlantaIzqEje1MesaYoyo  = cursor.getString(48);
                String remolqueLlantaIzqEje1Rin  = cursor.getString(49);
                String remolqueLlantaIzqEje1Lodera  = cursor.getString(50);
                String remolqueLlantaIzqEje2Posicion5Marca  = cursor.getString(51);
                String remolqueLlantaIzqEje2Posicion5Estatus  = cursor.getString(52);
                String remolqueLlantaIzqEje2Posicion6Marca  = cursor.getString(53);
                String remolqueLlantaIzqEje2Posicion6Estatus  = cursor.getString(54);
                String remolqueLlantaIzqEje2BrilosPivote  = cursor.getString(55);
                String remolqueLlantaIzqEje2Posicion5  = cursor.getString(56);
                String remolqueLlantaIzqEje2Posicion6  = cursor.getString(57);
                String remolqueLlantaIzqEje2MesaYoyo  = cursor.getString(58);
                String remolqueLlantaIzqEje2Rin  = cursor.getString(59);
                String remolqueLlantaIzqEje2Lodera  = cursor.getString(60);
                String remolqueChasisFrontalIzqAmortiguador  = cursor.getString(61);
                String remolqueChasisFrontalIzqBolsaAire  = cursor.getString(62);
                String remolqueChasisFrontalIzqGavilan  = cursor.getString(63);
                String remolqueChasisFrontalIzqMuelle  = cursor.getString(64);
                String remolqueChasisFrontalIzqRotachamber  = cursor.getString(65);
                String remolqueChasisTraseroIzqAmortiguador  = cursor.getString(66);
                String remolqueChasisTraseroIzqBolsaAire  = cursor.getString(67);
                String remolqueChasisTraseroIzqGavilan  = cursor.getString(68);
                String remolqueChasisTraseroIzqMuelle  = cursor.getString(69);
                String remolqueChasisTraseroIzqRotachamber  = cursor.getString(70);
                String remolqueIzqObservaciones  = cursor.getString(71);
                String Placas  = cursor.getString(72);
                String Sello1  = cursor.getString(73);
                String Sello2  = cursor.getString(74);
                String Sello3  = cursor.getString(75);
                String remolquePuertasBisagrasTornillos  = cursor.getString(76);
                String remolquePuertasDefensa  = cursor.getString(77);
                String remolquePuertasLuzGaliboSupTraseras  = cursor.getString(78);
                String remolquePuertasPlafonDerecho  = cursor.getString(79);
                String remolquePuertasPlafonIzquierdo  = cursor.getString(80);
                String remolquePlacasLuzPlaca  = cursor.getString(81);
                String remolquePlacasPlaca  = cursor.getString(82);
                String remolqueSello1Sello  = cursor.getString(83);
                String remolqueSello1Seguridad  = cursor.getString(84);
                String remolqueSello2Escotilla  = cursor.getString(85);
                String remolqueSello2Sello  = cursor.getString(86);
                String remolqueSello2Seguridad  = cursor.getString(87);
                String remolqueSello2Vvtt  = cursor.getString(88);
                String remolqueTraseraObservaciones  = cursor.getString(89);
                String remolqueChasisTraseroDerAmortiguador  = cursor.getString(90);
                String remolqueChasisTraseroDerBolsaAire  = cursor.getString(91);
                String remolqueChasisTraseroDerGavilan  = cursor.getString(92);
                String remolqueChasisTraseroDerMuelle  = cursor.getString(93);
                String remolqueChasisTraseroDerRotachamber  = cursor.getString(94);
                String remolqueLlantaDerEje2BrilosPivote  = cursor.getString(95);
                String remolqueLlantaDerEje2Posicion7Marca  = cursor.getString(96);
                String remolqueLlantaDerEje2Posicion7Estatus  = cursor.getString(97);
                String remolqueLlantaDerEje2Posicion8Marca  = cursor.getString(98);
                String remolqueLlantaDerEje2Posicion8Estatus  = cursor.getString(99);
                String remolqueLlantaDerEje2Posicion7  = cursor.getString(100);
                String remolqueLlantaDerEje2Posicion8  = cursor.getString(101);
                String remolqueLlantaDerEje2MasaYoyo  = cursor.getString(102);
                String remolqueLlantaDerEje2Rin  = cursor.getString(103);
                String remolqueLlantaDerEje2Lodera  = cursor.getString(104);
                String remolqueLlantaDerEje1BrilosPivote  = cursor.getString(105);
                String remolqueLlantaDerEje1Posicion3Marca  = cursor.getString(106);
                String remolqueLlantaDerEje1Posicion3Estatus  = cursor.getString(107);
                String remolqueLlantaDerEje1Posicion4Marca  = cursor.getString(108);
                String remolqueLlantaDerEje1Posicion4Estatus  = cursor.getString(109);
                String remolqueLlantaDerEje1Posicion3  = cursor.getString(110);
                String remolqueLlantaDerEje1Posicion4  = cursor.getString(111);
                String remolqueLlantaDerEje1MasaYoyo  = cursor.getString(112);
                String remolqueLlantaDerEje1Rin  = cursor.getString(113);
                String remolqueLlantaDerEje1Lodera  = cursor.getString(114);
                String remolqueChasisFrontalDerAmortiguador  = cursor.getString(115);
                String remolqueChasisFrontalDerBolsaAire  = cursor.getString(116);
                String remolqueChasisFrontalDerGavilan  = cursor.getString(117);
                String remolqueChasisFrontalDerMuelle  = cursor.getString(118);
                String remolqueChasisFrontalDerRotachamber  = cursor.getString(119);
                String remolquePisoPLagas  = cursor.getString(120);
                String remolqueTechoPlagas  = cursor.getString(121);
                String remolqueDerLuces  = cursor.getString(122);
                String remolqueDerGaliboFrontal  = cursor.getString(123);
                String remolqueDerParedPlagas  = cursor.getString(124);
                String remolqueDerIzqParedPlagas  = cursor.getString(125);
                String remolqueDerPatin  = cursor.getString(126);
                String remolqueCuartoLadoDer  = cursor.getString(127);
                String LoderaDer  = cursor.getString(128);
                String remolqueLucesDerP2  = cursor.getString(129);
                String luzBarcoDer  = cursor.getString(130);
                String rompevientosDer  = cursor.getString(131);
                String remolqueLlantaDerJumbo  = cursor.getString(132);
                String remolqueDerObservaciones  = cursor.getString(133);
                String licenciaUrl= cursor.getString(134);
                String tractoIzqUrl = cursor.getString(135);
                String tractoFrenteUrl = cursor.getString(136);
                String tractoDerUrl = cursor.getString(137);
                String noEcoUrl = cursor.getString(138);
                String vinUrl = cursor.getString(139);
                String remolqueCostadoTraseroIzqUrl = cursor.getString(140);
                String remolqueCostadoFrenteIzquierdoUrl = cursor.getString(141);
                String remolqueLlantaIzqEje1FotoUrl = cursor.getString(142);
                String remolqueLlantaIzqEje2FotoUrl = cursor.getString(143);
                String remolqueChasisFrontalIzqFotoUrl = cursor.getString(144);
                String remolqueChasisTraseroIzqFotoUrl = cursor.getString(145);
                String remolqueIzqDano1FotoUrl = cursor.getString(146);
                String remolqueIzqDano2FotoUrl = cursor.getString(147);
                String remolqueIzqDano3FotoUrl = cursor.getString(148);
                String remolqueIzqDano4FotoUrl = cursor.getString(149);
                String remolquePuertasFotoUrl = cursor.getString(150);
                String remolquePlacasFotoUrl = cursor.getString(151);
                String remolqueSello1FotoUrl = cursor.getString(152);
                String remolqueSello2FotoUrl = cursor.getString(153);
                String remolqueSello3FotoUrl = cursor.getString(154);
                String remolqueTraseroDano1FotoUrl = cursor.getString(155);
                String remolqueTraseroDano2FotoUrl = cursor.getString(156);
                String remolqueTraseroDano3FotoUrl = cursor.getString(157);
                String remolqueTraseroDano4FotoUrl = cursor.getString(158);
                String remolqueCostadoTraseroDerUrl = cursor.getString(159);
                String remolqueCostadoFrenteDerechoUrl = cursor.getString(160);
                String remolqueLlantaDerEje1FotoUrl = cursor.getString(161);
                String remolqueLlantaDerEje2FotoUrl = cursor.getString(162);
                String remolqueChasisFrontalDerFotoUrl = cursor.getString(163);
                String remolqueChasisTraseroDerFotoUrl = cursor.getString(164);
                String remolqueDerDano1FotoUrl = cursor.getString(165);
                String remolqueDerDano2FotoUrl = cursor.getString(166);
                String remolqueDerDano3FotoUrl = cursor.getString(167);
                String remolqueDerDano4FotoUrl = cursor.getString(168);
                String firmaOperadorUrl = cursor.getString(169);
                String firmaIntercambistaUrl = cursor.getString(170);
                String fechaFin  = cursor.getString(171);



                CintercambioElectronico cintercambioElectronico = new CintercambioElectronico ( estatus  ,
                        folio  ,
                        terminal  ,
                        idUsuario  ,
                        tipoOperacion  ,
                        tipoMovimiento  ,
                        estatusRemolque  ,
                        comentario2  ,
                        nombreOperador  ,
                        idOperador  ,
                        idTransportista  ,
                        unidad  ,
                        idUnidad  ,
                        idLinea  ,
                        remolque  ,
                        idRemolque  ,
                        fechaInicio  ,
                        tractoDefensa  ,
                        tractoCabina  ,
                        tractoQuintaRueda  ,
                        tractoTuboEscape  ,
                        tractoBaseRemolque  ,
                        tractoTechos  ,
                        tractoLlantas  ,
                        tractoTanqueDiesel  ,
                        tractoTanqueAire  ,
                        tractoEjeTransmision  ,
                        tractoMotor  ,
                        remolqueInspeccionMecanica  ,
                        remolqueLucesIzquierda  ,
                        remolqueLucesGaliboIzqFrontalSup  ,
                        remolqueManitasIzq  ,
                        remolqueManivelaIzq  ,
                        remolquePatinIzquierdo  ,
                        remolqueCuartoLadoIzq  ,
                        LoderaIzq  ,
                        remolqueLucesIzqP  ,
                        LuzAbsIzq  ,
                        luzBarcoIzq  ,
                        rompevientosIzq  ,
                        remolqueLlantaIzqJumbo  ,
                        remolqueLlantaIzqEje1Posicion1Marca  ,
                        remolqueLlantaIzqEje1Posicion1Estatus  ,
                        remolqueLlantaIzqEje1Posicion2Marca  ,
                        remolqueLlantaIzqEje1Posicion2Estatus  ,
                        remolqueLlantaIzqEje1BrilosPivote  ,
                        remolqueLlantaIzqEje1Posicion1  ,
                        remolqueLlantaIzqEje1Posicion2  ,
                        remolqueLlantaIzqEje1MesaYoyo  ,
                        remolqueLlantaIzqEje1Rin  ,
                        remolqueLlantaIzqEje1Lodera  ,
                        remolqueLlantaIzqEje2Posicion5Marca  ,
                        remolqueLlantaIzqEje2Posicion5Estatus  ,
                        remolqueLlantaIzqEje2Posicion6Marca  ,
                        remolqueLlantaIzqEje2Posicion6Estatus  ,
                        remolqueLlantaIzqEje2BrilosPivote  ,
                        remolqueLlantaIzqEje2Posicion5  ,
                        remolqueLlantaIzqEje2Posicion6  ,
                        remolqueLlantaIzqEje2MesaYoyo  ,
                        remolqueLlantaIzqEje2Rin  ,
                        remolqueLlantaIzqEje2Lodera  ,
                        remolqueChasisFrontalIzqAmortiguador  ,
                        remolqueChasisFrontalIzqBolsaAire  ,
                        remolqueChasisFrontalIzqGavilan  ,
                        remolqueChasisFrontalIzqMuelle  ,
                        remolqueChasisFrontalIzqRotachamber  ,
                        remolqueChasisTraseroIzqAmortiguador  ,
                        remolqueChasisTraseroIzqBolsaAire  ,
                        remolqueChasisTraseroIzqGavilan  ,
                        remolqueChasisTraseroIzqMuelle  ,
                        remolqueChasisTraseroIzqRotachamber  ,
                        remolqueIzqObservaciones  ,
                        Placas  ,
                        Sello1  ,
                        Sello2  ,
                        Sello3  ,
                        remolquePuertasBisagrasTornillos  ,
                        remolquePuertasDefensa  ,
                        remolquePuertasLuzGaliboSupTraseras  ,
                        remolquePuertasPlafonDerecho  ,
                        remolquePuertasPlafonIzquierdo  ,
                        remolquePlacasLuzPlaca  ,
                        remolquePlacasPlaca  ,
                        remolqueSello1Sello  ,
                        remolqueSello1Seguridad  ,
                        remolqueSello2Escotilla  ,
                        remolqueSello2Sello  ,
                        remolqueSello2Seguridad  ,
                        remolqueSello2Vvtt  ,
                        remolqueTraseraObservaciones  ,
                        remolqueChasisTraseroDerAmortiguador  ,
                        remolqueChasisTraseroDerBolsaAire  ,
                        remolqueChasisTraseroDerGavilan  ,
                        remolqueChasisTraseroDerMuelle  ,
                        remolqueChasisTraseroDerRotachamber  ,
                        remolqueLlantaDerEje2BrilosPivote  ,
                        remolqueLlantaDerEje2Posicion7Marca  ,
                        remolqueLlantaDerEje2Posicion7Estatus  ,
                        remolqueLlantaDerEje2Posicion8Marca  ,
                        remolqueLlantaDerEje2Posicion8Estatus  ,
                        remolqueLlantaDerEje2Posicion7  ,
                        remolqueLlantaDerEje2Posicion8  ,
                        remolqueLlantaDerEje2MasaYoyo  ,
                        remolqueLlantaDerEje2Rin  ,
                        remolqueLlantaDerEje2Lodera  ,
                        remolqueLlantaDerEje1BrilosPivote  ,
                        remolqueLlantaDerEje1Posicion3Marca  ,
                        remolqueLlantaDerEje1Posicion3Estatus  ,
                        remolqueLlantaDerEje1Posicion4Marca  ,
                        remolqueLlantaDerEje1Posicion4Estatus  ,
                        remolqueLlantaDerEje1Posicion3  ,
                        remolqueLlantaDerEje1Posicion4  ,
                        remolqueLlantaDerEje1MasaYoyo  ,
                        remolqueLlantaDerEje1Rin  ,
                        remolqueLlantaDerEje1Lodera  ,
                        remolqueChasisFrontalDerAmortiguador  ,
                        remolqueChasisFrontalDerBolsaAire  ,
                        remolqueChasisFrontalDerGavilan  ,
                        remolqueChasisFrontalDerMuelle  ,
                        remolqueChasisFrontalDerRotachamber  ,
                        remolquePisoPLagas  ,
                        remolqueTechoPlagas  ,
                        remolqueDerLuces  ,
                        remolqueDerGaliboFrontal  ,
                        remolqueDerParedPlagas  ,
                        remolqueDerIzqParedPlagas  ,
                        remolqueDerPatin  ,
                        remolqueCuartoLadoDer  ,
                        LoderaDer  ,
                        remolqueLucesDerP2  ,
                        luzBarcoDer  ,
                        rompevientosDer  ,
                        remolqueLlantaDerJumbo  ,
                        remolqueDerObservaciones  ,
                        licenciaUrl ,
                      tractoIzqUrl ,
                      tractoFrenteUrl ,
                      tractoDerUrl ,
                      noEcoUrl ,
                      vinUrl ,
                      remolqueCostadoTraseroIzqUrl ,
                      remolqueCostadoFrenteIzquierdoUrl ,
                      remolqueLlantaIzqEje1FotoUrl ,
                      remolqueLlantaIzqEje2FotoUrl ,
                      remolqueChasisFrontalIzqFotoUrl ,
                      remolqueChasisTraseroIzqFotoUrl ,
                      remolqueIzqDano1FotoUrl ,
                      remolqueIzqDano2FotoUrl ,
                      remolqueIzqDano3FotoUrl ,
                      remolqueIzqDano4FotoUrl ,
                      remolquePuertasFotoUrl ,
                      remolquePlacasFotoUrl ,
                      remolqueSello1FotoUrl ,
                      remolqueSello2FotoUrl ,
                      remolqueSello3FotoUrl ,
                      remolqueTraseroDano1FotoUrl ,
                      remolqueTraseroDano2FotoUrl ,
                      remolqueTraseroDano3FotoUrl ,
                      remolqueTraseroDano4FotoUrl ,
                      remolqueCostadoTraseroDerUrl ,
                      remolqueCostadoFrenteDerechoUrl ,
                      remolqueLlantaDerEje1FotoUrl ,
                      remolqueLlantaDerEje2FotoUrl ,
                      remolqueChasisFrontalDerFotoUrl ,
                      remolqueChasisTraseroDerFotoUrl ,
                      remolqueDerDano1FotoUrl ,
                      remolqueDerDano2FotoUrl ,
                      remolqueDerDano3FotoUrl ,
                      remolqueDerDano4FotoUrl ,
                      firmaOperadorUrl ,
                      firmaIntercambistaUrl ,
                        fechaFin   );


                returnList.add(cintercambioElectronico);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<CPopulateList> selectIntercambioAbierto() {

        List<CPopulateList> returnList = new ArrayList<>();

        String queryString = "SELECT i.folio,i.fechaInicio,u.claveUnidad,us.login,r.claveRemolque,i.estatus,i.fechaFin " +
                "FROM " + TABLE_INTERCAMBIO + " i LEFT JOIN unidad u ON u.idUnidad = i.idUnidad LEFT JOIN usuario us ON us.idUsuario = i.idUsuario LEFT JOIN remolque r ON r.idRemolque = i.idRemolque "
                + " WHERE i.fechaFin IS null ";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {

                String folio  = cursor.getString(0);
                String fechaInicio  = cursor.getString(1);
                String claveUnidad  = cursor.getString(2);
                String login  = cursor.getString(3);
                String claveRemolque  = cursor.getString(4);
                String estatus  = cursor.getString(5);
                String fechaFin =  cursor.getString(6);

                CPopulateList cPopulateList = new CPopulateList ( folio  ,
                        fechaInicio ,
                        claveRemolque  ,
                        login  ,
                        claveUnidad ,
                        estatus,
                        fechaFin
                );

                returnList.add(cPopulateList);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public int clearTables() {


        SQLiteDatabase db = this.getReadableDatabase();
        int response ;

        int response0 = db.delete(TABLE_REMOLQUE,null,null);
        int response1 = db.delete(TABLE_LLANTA,null,null);
        int response2 = db.delete(TABLE_USUARIO,null,null);
        int response3 = db.delete(TABLE_UNIDAD,null,null);
        int response4 = db.delete(TABLE_TRANSPORTISTA,null,null);
        int response5 = db.delete(TABLE_LINEA,null,null);
        int response6 = db.delete(TABLE_OPERADOR,null,null);


        if(response0 == -1 || response1 == -1 || response2 == -1 || response3 == -1 || response4 == -1 || response5 == -1 || response6 == -1 ){
            response =  -1;
        }else {
            response =  420;
        }

        db.close();

        return  response;
    }
}
