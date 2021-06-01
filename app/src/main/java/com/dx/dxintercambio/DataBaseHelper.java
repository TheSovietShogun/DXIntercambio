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
    public static final String TABLE_INTERCAMBIO = "intercambio";
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

    public String insertIntercambioElectronico( String UfechaHora,String UtipoOperacion,String UidUsuario,String UidTransportista
                                                ,String UidOperador,String UidUnidad ,String  UidRemolque ,String UidLinea ,String  Uestatus ,String Ucomentario,
                                                String Ufolio,String Umovimiento ,String Upatio , String UotroUnidad, String UotroRemolque){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("estatus",Uestatus);
        cv.put("folio",Ufolio);
        cv.put("terminal",Upatio);
        cv.put("idUsuario",UidUsuario);
        cv.put("tipoOperacion",UtipoOperacion);
        cv.put("tipoMovimiento",Umovimiento);
        cv.put("estatusRemolque",Uestatus);
        cv.put("comentario2",Ucomentario);
        cv.put("idOperador",UidOperador);
        cv.put("idTransportista",UidTransportista);
        cv.put("unidad",UotroUnidad);
        cv.put("idUnidad",UidUnidad);
        cv.put("idLinea",UidLinea);
        cv.put("remolque",UotroRemolque);
        cv.put("idRemolque",UidRemolque);
        cv.put("fechaInicio",UfechaHora);

        long insert = db.insert(TABLE_INTERCAMBIO, null, cv);

        if(insert == -1){
            return "-1";
        }else{

            String folio = Ufolio;
            return folio;
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

        if(idTransportista == ""){
            idTransportista = "4";
        }

        String queryString = "SELECT * FROM " + TABLE_UNIDAD + " WHERE "+ COLUMN_CLAVE_UNIDAD + " LIKE  " + "'" + idTransportista + "%'" ;

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

        String queryString = "SELECT * FROM " + TABLE_USUARIO + " WHERE " +COLUMN_LOGIN +" = " + usuario + " AND " + COLUMN_PASSWORD + " = " + contra;

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

    public long insertIntercambioElectronico1( String estatus ,String folio,String terminal,String idUsuario,String tipoOperacion,String tipoMovimiento,String estatusRemolque,
                                               String comentario2,String nombreOperador,String idOperador,String idTransportista,String unidad,String idUnidad,String idLinea,String remolque
                                                ,String idRemolque,String fechaInicio){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

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

    public long insertIntercambioElectronico2( String estatus ,String folio,String tractoDefensa,String tractoCabina,String tractoQuintaRueda,String tractoTuboEscape,String tractoBaseRemolque,
                                               String tractoTechos,String tractoLlantas,String tractoTanqueDiesel,String tractoTanqueAire,String tractoEjeTransmision,String tractoMotor){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

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


        String where = " folio = " + folio;

        long update = db.update(TABLE_INTERCAMBIO,cv,where,null);

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico3( String estatus ,String folio,String remolqueInspeccionMecanica,String remolqueLucesIzquierda,String remolqueLucesGaliboIzqFrontalSup,
                                               String remolqueManitasIzq,String remolqueManivelaIzq, String remolquePatinIzquierdo,String remolqueCuartoLadoIzq,
                                               String LoderaIzq,String remolqueLucesIzqP,String LuzAbsIzq,String luzBarcoIzq,String rompevientosIzq){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

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


        String where = " folio = " + folio;

        long update = db.update(TABLE_INTERCAMBIO,cv,where,null);

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico4( String estatus ,String folio,String remolqueLlantaIzqJumbo,String remolqueLlantaIzqEje1Posicion1Marca,String remolqueLlantaIzqEje1Posicion1Estatus,
                                               String remolqueLlantaIzqEje1Posicion2Marca,String remolqueLlantaIzqEje1Posicion2Estatus, String remolqueLlantaIzqEje1BrilosPivote,String remolqueLlantaIzqEje1Posicion1,
                                               String remolqueLlantaIzqEje1Posicion2,String remolqueLlantaIzqEje1MesaYoyo, String remolqueLlantaIzqEje1Rin,String remolqueLlantaIzqEje1Lodera,
                                               String remolqueLlantaIzqEje2Posicion5Marca,String remolqueLlantaIzqEje2Posicion5Estatus, String remolqueLlantaIzqEje2Posicion6Marca,String remolqueLlantaIzqEje2Posicion6Estatus,
                                               String remolqueLlantaIzqEje2BrilosPivote,String remolqueLlantaIzqEje2Posicion5, String remolqueLlantaIzqEje2Posicion6,String remolqueLlantaIzqEje2MesaYoyo,
                                               String remolqueLlantaIzqEje2Rin,String remolqueLlantaIzqEje2Lodera, String remolqueChasisFrontalIzqAmortiguador,String remolqueChasisFrontalIzqBolsaAire,
                                               String remolqueChasisFrontalIzqGavilan,String remolqueChasisFrontalIzqMuelle, String remolqueChasisFrontalIzqRotachamber,String remolqueChasisTraseroIzqAmortiguador,
                                               String remolqueChasisTraseroIzqBolsaAire,String remolqueChasisTraseroIzqGavilan ,String remolqueChasisTraseroIzqMuelle ,String remolqueChasisTraseroIzqRotachamber ,String remolqueIzqObservaciones ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

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

        String where = " folio = " + folio;

        long update = db.update(TABLE_INTERCAMBIO,cv,where,null);

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico5( String estatus ,String folio,String Placas,String Sello1,String Sello2,
                                               String Sello3,String remolquePuertasBisagrasTornillos, String remolquePuertasDefensa,String remolquePuertasLuzGaliboSupTraseras,
                                               String remolquePuertasPlafonDerecho,String remolquePuertasPlafonIzquierdo, String remolquePlacasLuzPlaca,String remolquePlacasPlaca,
                                               String remolqueSello1Sello,String remolqueSello1Seguridad, String remolqueSello2Escotilla,String remolqueSello2Sello,
                                               String remolqueSello2Seguridad,String remolqueSello2Vvtt ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

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


        String where = " folio = " + folio;

        long update = db.update(TABLE_INTERCAMBIO,cv,where,null);

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico6( String folio,String remolqueChasisTraseroDerAmortiguador,String remolqueChasisTraseroDerBolsaAire,String remolqueChasisTraseroDerGavilan,
                                               String remolqueChasisTraseroDerMuelle,String remolqueChasisTraseroDerRotachamber, String remolqueLlantaDerEje2BrilosPivote,String remolqueLlantaDerEje2Posicion7Marca,
                                               String remolqueLlantaDerEje2Posicion7Estatus,String remolqueLlantaDerEje2Posicion8Marca, String remolqueLlantaDerEje2Posicion8Estatus,String remolqueLlantaDerEje2Posicion7,
                                               String remolqueLlantaDerEje2Posicion8,String remolqueLlantaDerEje2MasaYoyo, String remolqueLlantaDerEje2Rin,String remolqueLlantaDerEje2Lodera,
                                               String remolqueLlantaDerEje1BrilosPivote,String remolqueLlantaDerEje1Posicion3Marca, String remolqueLlantaDerEje1Posicion3Estatus,String remolqueLlantaDerEje1Posicion4Marca,
                                               String remolqueLlantaDerEje1Posicion4Estatus,String remolqueLlantaDerEje1Posicion3, String remolqueLlantaDerEje1Posicion4,String remolqueLlantaDerEje1MasaYoyo,
                                               String remolqueLlantaDerEje1Rin,String remolqueLlantaDerEje1Lodera,String remolqueChasisFrontalDerAmortiguador,String remolqueChasisFrontalDerBolsaAire,String remolqueChasisFrontalDerGavilan,
                                               String remolqueChasisFrontalDerMuelle,String remolqueChasisFrontalDerRotachamber ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

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

        String where = " folio = " + folio;

        long update = db.update(TABLE_INTERCAMBIO,cv,where,null);

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico7 (String folio,String remolquePisoPLagas,String remolqueTechoPlagas,String remolqueDerLuces,
                                               String remolqueDerGaliboFrontal,String remolqueDerParedPlagas, String remolqueDerIzqParedPlagas,String remolqueDerPatin,
                                               String remolqueCuartoLadoDer,String LoderaDer, String remolqueLucesDerP2,String luzBarcoDer,
                                               String rompevientosDer,String remolqueLlantaDerJumbo ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

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
        cv.put("remolqueLlantaDerJumbo",remolqueLlantaDerJumbo);


        String where = " folio = " + folio;

        long update = db.update(TABLE_INTERCAMBIO,cv,where,null);

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public long insertIntercambioElectronico200( String folio ,String fechaFin){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("fechaFin",fechaFin);

        String where = " folio = " + folio;

        long update = db.update(TABLE_INTERCAMBIO,cv,where,null);

        if(update == -1){
            return -1;
        }else{
            return update;
        }
    }

    public List<CintercambioElectronico> selectListIntercambio() {

        List<CintercambioElectronico> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_INTERCAMBIO ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {
                
                String estatus  = cursor.getString(0);
                String folio    = cursor.getString(1);
                String terminal  = cursor.getString(2);
                String idUsuario    = cursor.getString(3);
                String tipoOperacion    = cursor.getString(4);
                String tipoMovimiento    = cursor.getString(5);
                String estatusRemolque    = cursor.getString(6);
                String comentario2   = cursor.getString(7);
                String nombreOperador    = cursor.getString(8);
                String idOperador   = cursor.getString(9);
                String idTransportista    = cursor.getString(10);
                String unidad    = cursor.getString(11);
                String idUnidad   = cursor.getString(12);
                String idLinea    = cursor.getString(13);
                String remolque    = cursor.getString(14);
                String idRemolque   = cursor.getString(15);
                String fechaInicio    = cursor.getString(16);
                String tractoDefensa   = cursor.getString(17);
                String tractoCabina   = cursor.getString(18);
                String tractoQuintaRueda   = cursor.getString(19);
                String tractoTuboEscape   = cursor.getString(20);
                String tractoBaseRemolque   = cursor.getString(21);
                String tractoTechos   = cursor.getString(22);
                String tractoLlantas   = cursor.getString(23);
                String tractoTanqueDiesel   = cursor.getString(24);
                String tractoTanqueAire   = cursor.getString(25);
                String tractoEjeTransmision   = cursor.getString(26);
                String tractoMotor   = cursor.getString(27);
                String remolqueInspeccionMecanica   = cursor.getString(28);
                String remolqueLucesIzquierda   = cursor.getString(29);
                String remolqueLucesGaliboIzqFrontalSup   = cursor.getString(30);
                String remolqueManitasIzq   = cursor.getString(31);
                String remolqueManivelaIzq   = cursor.getString(32);
                String remolquePatinIzquierdo   = cursor.getString(33);
                String remolqueCuartoLadoIzq   = cursor.getString(34);
                String LoderaIzq   = cursor.getString(35);
                String remolqueLucesIzqP   = cursor.getString(36);
                String LuzAbsIzq   = cursor.getString(37);
                String luzBarcoIzq   = cursor.getString(38);
                String rompevientosIzq   = cursor.getString(39);
                String remolqueLlantaIzqJumbo   = cursor.getString(40);
                String remolqueLlantaIzqEje1Posicion1Marca   = cursor.getString(41);
                String remolqueLlantaIzqEje1Posicion1Estatus   = cursor.getString(42);
                String remolqueLlantaIzqEje1Posicion2Marca   = cursor.getString(43);
                String remolqueLlantaIzqEje1Posicion2Estatus   = cursor.getString(44);
                String remolqueLlantaIzqEje1BrilosPivote   = cursor.getString(45);
                String remolqueLlantaIzqEje1Posicion1   = cursor.getString(46);
                String remolqueLlantaIzqEje1Posicion2   = cursor.getString(47);
                String remolqueLlantaIzqEje1MesaYoyo   = cursor.getString(48);
                String remolqueLlantaIzqEje1Rin   = cursor.getString(49);
                String remolqueLlantaIzqEje1Lodera   = cursor.getString(50);
                String remolqueLlantaIzqEje2Posicion5Marca   = cursor.getString(51);
                String remolqueLlantaIzqEje2Posicion5Estatus   = cursor.getString(52);
                String remolqueLlantaIzqEje2Posicion6Marca   = cursor.getString(53);
                String remolqueLlantaIzqEje2Posicion6Estatus   = cursor.getString(54);
                String remolqueLlantaIzqEje2BrilosPivote   = cursor.getString(55);
                String remolqueLlantaIzqEje2Posicion5   = cursor.getString(56);
                String remolqueLlantaIzqEje2Posicion6   = cursor.getString(57);
                String remolqueLlantaIzqEje2MesaYoyo   = cursor.getString(58);
                String remolqueLlantaIzqEje2Rin   = cursor.getString(59);
                String remolqueLlantaIzqEje2Lodera   = cursor.getString(60);
                String remolqueChasisFrontalIzqAmortiguador   = cursor.getString(61);
                String remolqueChasisFrontalIzqBolsaAire   = cursor.getString(62);
                String remolqueChasisFrontalIzqGavilan   = cursor.getString(63);
                String remolqueChasisFrontalIzqMuelle   = cursor.getString(64);
                String remolqueChasisFrontalIzqRotachamber   = cursor.getString(65);
                String remolqueChasisTraseroIzqAmortiguador   = cursor.getString(66);
                String remolqueChasisTraseroIzqBolsaAire   = cursor.getString(67);
                String remolqueChasisTraseroIzqGavilan   = cursor.getString(68);
                String remolqueChasisTraseroIzqMuelle   = cursor.getString(69);
                String remolqueChasisTraseroIzqRotachamber   = cursor.getString(70);
                String remolqueIzqObservaciones   = cursor.getString(71);
                String Placas   = cursor.getString(72);
                String Sello1   = cursor.getString(73);
                String Sello2   = cursor.getString(74);
                String Sello3   = cursor.getString(75);
                String remolquePuertasBisagrasTornillos   = cursor.getString(76);
                String remolquePuertasDefensa   = cursor.getString(77);
                String remolquePuertasLuzGaliboSupTraseras   = cursor.getString(78);
                String remolquePuertasPlafonDerecho   = cursor.getString(79);
                String remolquePuertasPlafonIzquierdo   = cursor.getString(80);
                String remolquePlacasLuzPlaca   = cursor.getString(81);
                String remolquePlacasPlaca   = cursor.getString(82);
                String remolqueSello1Sello   = cursor.getString(83);
                String remolqueSello1Seguridad   = cursor.getString(84);
                String remolqueSello2Escotilla   = cursor.getString(85);
                String remolqueSello2Sello   = cursor.getString(86);
                String remolqueSello2Seguridad   = cursor.getString(87);
                String remolqueSello2Vvtt   = cursor.getString(88);
                String remolquePisoPLagas   = cursor.getString(89);
                String remolqueTechoPlagas   = cursor.getString(90);
                String remolqueDerLuces   = cursor.getString(91);
                String remolqueDerGaliboFrontal   = cursor.getString(92);
                String remolqueDerParedPlagas   = cursor.getString(93);
                String remolqueDerIzqParedPlagas   = cursor.getString(94);
                String remolqueDerPatin   = cursor.getString(95);
                String remolqueCuartoLadoDer   = cursor.getString(96);
                String LoderaDer   = cursor.getString(97);
                String remolqueLucesDerP2   = cursor.getString(98);
                String luzBarcoDer   = cursor.getString(99);
                String rompevientosDer   = cursor.getString(100);
                String remolqueLlantaDerJumbo   = cursor.getString(101);
                String remolqueLlantaDerEje2Posicion7Marca   = cursor.getString(102);
                String remolqueLlantaDerEje2Posicion7Estatus   = cursor.getString(103);
                String remolqueLlantaDerEje2Posicion8Marca   = cursor.getString(104);
                String remolqueLlantaDerEje2Posicion8Estatus   = cursor.getString(105);
                String remolqueLlantaDerEje1BrilosPivote   = cursor.getString(106);
                String remolqueLlantaDerEje2Posicion7   = cursor.getString(107);
                String remolqueLlantaDerEje2Posicion8   = cursor.getString(108);
                String remolqueLlantaDerEje1MasaYoyo   = cursor.getString(109);
                String remolqueLlantaDerEje1Rin   = cursor.getString(110);
                String remolqueLlantaDerEje1Lodera   = cursor.getString(111);
                String remolqueLlantaDerEje1Posicion3Marca   = cursor.getString(112);
                String remolqueLlantaDerEje1Posicion3Estatus   = cursor.getString(113);
                String remolqueLlantaDerEje1Posicion4Marca   = cursor.getString(114);
                String remolqueLlantaDerEje1Posicion4Estatus   = cursor.getString(115);
                String remolqueLlantaDerEje2BrilosPivote   = cursor.getString(116);
                String remolqueLlantaDerEje1Posicion3   = cursor.getString(117);
                String remolqueLlantaDerEje1Posicion4   = cursor.getString(118);
                String remolqueLlantaDerEje2MasaYoyo   = cursor.getString(119);
                String remolqueLlantaDerEje2Rin   = cursor.getString(120);
                String remolqueLlantaDerEje2Lodera   = cursor.getString(121);
                String remolqueChasisFrontalDerAmortiguador   = cursor.getString(122);
                String remolqueChasisFrontalDerBolsaAire   = cursor.getString(123);
                String remolqueChasisFrontalDerGavilan   = cursor.getString(124);
                String remolqueChasisFrontalDerMuelle   = cursor.getString(125);
                String remolqueChasisFrontalDerRotachamber   = cursor.getString(126);
                String remolqueChasisTraseroDerAmortiguador   = cursor.getString(127);
                String remolqueChasisTraseroDerBolsaAire   = cursor.getString(128);
                String remolqueChasisTraseroDerGavilan   = cursor.getString(129);
                String remolqueChasisTraseroDerMuelle   = cursor.getString(130);
                String remolqueChasisTraseroDerRotachamber   = cursor.getString(131);
                String remolqueDerObservaciones   = cursor.getString(132);
                String fechaFin   = cursor.getString(133);

                
                CintercambioElectronico cintercambioElectronico = new CintercambioElectronico ( estatus  ,
                        folio    ,
                        terminal  ,
                        idUsuario    ,
                        tipoOperacion    ,
                        tipoMovimiento    ,
                        estatusRemolque    ,
                        comentario2   ,
                        nombreOperador    ,
                        idOperador   ,
                        idTransportista    ,
                        unidad    ,
                        idUnidad   ,
                        idLinea    ,
                        remolque    ,
                        idRemolque   ,
                        fechaInicio    ,
                        tractoDefensa   ,
                        tractoCabina   ,
                        tractoQuintaRueda   ,
                        tractoTuboEscape   ,
                        tractoBaseRemolque   ,
                        tractoTechos   ,
                        tractoLlantas   ,
                        tractoTanqueDiesel   ,
                        tractoTanqueAire   ,
                        tractoEjeTransmision   ,
                        tractoMotor   ,
                        remolqueInspeccionMecanica   ,
                        remolqueLucesIzquierda   ,
                        remolqueLucesGaliboIzqFrontalSup   ,
                        remolqueManitasIzq   ,
                        remolqueManivelaIzq   ,
                        remolquePatinIzquierdo   ,
                        remolqueCuartoLadoIzq   ,
                        LoderaIzq   ,
                        remolqueLucesIzqP   ,
                        LuzAbsIzq   ,
                        luzBarcoIzq   ,
                        rompevientosIzq   ,
                        remolqueLlantaIzqJumbo   ,
                        remolqueLlantaIzqEje1Posicion1Marca   ,
                        remolqueLlantaIzqEje1Posicion1Estatus   ,
                        remolqueLlantaIzqEje1Posicion2Marca   ,
                        remolqueLlantaIzqEje1Posicion2Estatus   ,
                        remolqueLlantaIzqEje1BrilosPivote   ,
                        remolqueLlantaIzqEje1Posicion1   ,
                        remolqueLlantaIzqEje1Posicion2   ,
                        remolqueLlantaIzqEje1MesaYoyo   ,
                        remolqueLlantaIzqEje1Rin   ,
                        remolqueLlantaIzqEje1Lodera   ,
                        remolqueLlantaIzqEje2Posicion5Marca   ,
                        remolqueLlantaIzqEje2Posicion5Estatus   ,
                        remolqueLlantaIzqEje2Posicion6Marca   ,
                        remolqueLlantaIzqEje2Posicion6Estatus   ,
                        remolqueLlantaIzqEje2BrilosPivote   ,
                        remolqueLlantaIzqEje2Posicion5   ,
                        remolqueLlantaIzqEje2Posicion6   ,
                        remolqueLlantaIzqEje2MesaYoyo   ,
                        remolqueLlantaIzqEje2Rin   ,
                        remolqueLlantaIzqEje2Lodera   ,
                        remolqueChasisFrontalIzqAmortiguador   ,
                        remolqueChasisFrontalIzqBolsaAire   ,
                        remolqueChasisFrontalIzqGavilan   ,
                        remolqueChasisFrontalIzqMuelle   ,
                        remolqueChasisFrontalIzqRotachamber   ,
                        remolqueChasisTraseroIzqAmortiguador   ,
                        remolqueChasisTraseroIzqBolsaAire   ,
                        remolqueChasisTraseroIzqGavilan   ,
                        remolqueChasisTraseroIzqMuelle   ,
                        remolqueChasisTraseroIzqRotachamber   ,
                        remolqueIzqObservaciones   ,
                        Placas   ,
                        Sello1   ,
                        Sello2   ,
                        Sello3   ,
                        remolquePuertasBisagrasTornillos   ,
                        remolquePuertasDefensa   ,
                        remolquePuertasLuzGaliboSupTraseras   ,
                        remolquePuertasPlafonDerecho   ,
                        remolquePuertasPlafonIzquierdo   ,
                        remolquePlacasLuzPlaca   ,
                        remolquePlacasPlaca   ,
                        remolqueSello1Sello   ,
                        remolqueSello1Seguridad   ,
                        remolqueSello2Escotilla   ,
                        remolqueSello2Sello   ,
                        remolqueSello2Seguridad   ,
                        remolqueSello2Vvtt   ,
                        remolquePisoPLagas   ,
                        remolqueTechoPlagas   ,
                        remolqueDerLuces   ,
                        remolqueDerGaliboFrontal   ,
                        remolqueDerParedPlagas   ,
                        remolqueDerIzqParedPlagas   ,
                        remolqueDerPatin   ,
                        remolqueCuartoLadoDer   ,
                        LoderaDer   ,
                        remolqueLucesDerP2   ,
                        luzBarcoDer   ,
                        rompevientosDer   ,
                        remolqueLlantaDerJumbo   ,
                        remolqueLlantaDerEje2Posicion7Marca   ,
                        remolqueLlantaDerEje2Posicion7Estatus   ,
                        remolqueLlantaDerEje2Posicion8Marca   ,
                        remolqueLlantaDerEje2Posicion8Estatus   ,
                        remolqueLlantaDerEje1BrilosPivote   ,
                        remolqueLlantaDerEje2Posicion7   ,
                        remolqueLlantaDerEje2Posicion8   ,
                        remolqueLlantaDerEje1MasaYoyo   ,
                        remolqueLlantaDerEje1Rin   ,
                        remolqueLlantaDerEje1Lodera   ,
                        remolqueLlantaDerEje1Posicion3Marca   ,
                        remolqueLlantaDerEje1Posicion3Estatus   ,
                        remolqueLlantaDerEje1Posicion4Marca   ,
                        remolqueLlantaDerEje1Posicion4Estatus   ,
                        remolqueLlantaDerEje2BrilosPivote   ,
                        remolqueLlantaDerEje1Posicion3   ,
                        remolqueLlantaDerEje1Posicion4   ,
                        remolqueLlantaDerEje2MasaYoyo   ,
                        remolqueLlantaDerEje2Rin   ,
                        remolqueLlantaDerEje2Lodera   ,
                        remolqueChasisFrontalDerAmortiguador   ,
                        remolqueChasisFrontalDerBolsaAire   ,
                        remolqueChasisFrontalDerGavilan   ,
                        remolqueChasisFrontalDerMuelle   ,
                        remolqueChasisFrontalDerRotachamber   ,
                        remolqueChasisTraseroDerAmortiguador   ,
                        remolqueChasisTraseroDerBolsaAire   ,
                        remolqueChasisTraseroDerGavilan   ,
                        remolqueChasisTraseroDerMuelle   ,
                        remolqueChasisTraseroDerRotachamber   ,
                        remolqueDerObservaciones   ,
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

    public List<CintercambioElectronico> selectIntercambio( String folio) {

        List<CintercambioElectronico> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_INTERCAMBIO + " WHERE = " + folio;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {

                String estatus  = cursor.getString(0);
                String Ufolio    = cursor.getString(1);
                String terminal  = cursor.getString(2);
                String idUsuario    = cursor.getString(3);
                String tipoOperacion    = cursor.getString(4);
                String tipoMovimiento    = cursor.getString(5);
                String estatusRemolque    = cursor.getString(6);
                String comentario2   = cursor.getString(7);
                String nombreOperador    = cursor.getString(8);
                String idOperador   = cursor.getString(9);
                String idTransportista    = cursor.getString(10);
                String unidad    = cursor.getString(11);
                String idUnidad   = cursor.getString(12);
                String idLinea    = cursor.getString(13);
                String remolque    = cursor.getString(14);
                String idRemolque   = cursor.getString(15);
                String fechaInicio    = cursor.getString(16);
                String tractoDefensa   = cursor.getString(17);
                String tractoCabina   = cursor.getString(18);
                String tractoQuintaRueda   = cursor.getString(19);
                String tractoTuboEscape   = cursor.getString(20);
                String tractoBaseRemolque   = cursor.getString(21);
                String tractoTechos   = cursor.getString(22);
                String tractoLlantas   = cursor.getString(23);
                String tractoTanqueDiesel   = cursor.getString(24);
                String tractoTanqueAire   = cursor.getString(25);
                String tractoEjeTransmision   = cursor.getString(26);
                String tractoMotor   = cursor.getString(27);
                String remolqueInspeccionMecanica   = cursor.getString(28);
                String remolqueLucesIzquierda   = cursor.getString(29);
                String remolqueLucesGaliboIzqFrontalSup   = cursor.getString(30);
                String remolqueManitasIzq   = cursor.getString(31);
                String remolqueManivelaIzq   = cursor.getString(32);
                String remolquePatinIzquierdo   = cursor.getString(33);
                String remolqueCuartoLadoIzq   = cursor.getString(34);
                String LoderaIzq   = cursor.getString(35);
                String remolqueLucesIzqP   = cursor.getString(36);
                String LuzAbsIzq   = cursor.getString(37);
                String luzBarcoIzq   = cursor.getString(38);
                String rompevientosIzq   = cursor.getString(39);
                String remolqueLlantaIzqJumbo   = cursor.getString(40);
                String remolqueLlantaIzqEje1Posicion1Marca   = cursor.getString(41);
                String remolqueLlantaIzqEje1Posicion1Estatus   = cursor.getString(42);
                String remolqueLlantaIzqEje1Posicion2Marca   = cursor.getString(43);
                String remolqueLlantaIzqEje1Posicion2Estatus   = cursor.getString(44);
                String remolqueLlantaIzqEje1BrilosPivote   = cursor.getString(45);
                String remolqueLlantaIzqEje1Posicion1   = cursor.getString(46);
                String remolqueLlantaIzqEje1Posicion2   = cursor.getString(47);
                String remolqueLlantaIzqEje1MesaYoyo   = cursor.getString(48);
                String remolqueLlantaIzqEje1Rin   = cursor.getString(49);
                String remolqueLlantaIzqEje1Lodera   = cursor.getString(50);
                String remolqueLlantaIzqEje2Posicion5Marca   = cursor.getString(51);
                String remolqueLlantaIzqEje2Posicion5Estatus   = cursor.getString(52);
                String remolqueLlantaIzqEje2Posicion6Marca   = cursor.getString(53);
                String remolqueLlantaIzqEje2Posicion6Estatus   = cursor.getString(54);
                String remolqueLlantaIzqEje2BrilosPivote   = cursor.getString(55);
                String remolqueLlantaIzqEje2Posicion5   = cursor.getString(56);
                String remolqueLlantaIzqEje2Posicion6   = cursor.getString(57);
                String remolqueLlantaIzqEje2MesaYoyo   = cursor.getString(58);
                String remolqueLlantaIzqEje2Rin   = cursor.getString(59);
                String remolqueLlantaIzqEje2Lodera   = cursor.getString(60);
                String remolqueChasisFrontalIzqAmortiguador   = cursor.getString(61);
                String remolqueChasisFrontalIzqBolsaAire   = cursor.getString(62);
                String remolqueChasisFrontalIzqGavilan   = cursor.getString(63);
                String remolqueChasisFrontalIzqMuelle   = cursor.getString(64);
                String remolqueChasisFrontalIzqRotachamber   = cursor.getString(65);
                String remolqueChasisTraseroIzqAmortiguador   = cursor.getString(66);
                String remolqueChasisTraseroIzqBolsaAire   = cursor.getString(67);
                String remolqueChasisTraseroIzqGavilan   = cursor.getString(68);
                String remolqueChasisTraseroIzqMuelle   = cursor.getString(69);
                String remolqueChasisTraseroIzqRotachamber   = cursor.getString(70);
                String remolqueIzqObservaciones   = cursor.getString(71);
                String Placas   = cursor.getString(72);
                String Sello1   = cursor.getString(73);
                String Sello2   = cursor.getString(74);
                String Sello3   = cursor.getString(75);
                String remolquePuertasBisagrasTornillos   = cursor.getString(76);
                String remolquePuertasDefensa   = cursor.getString(77);
                String remolquePuertasLuzGaliboSupTraseras   = cursor.getString(78);
                String remolquePuertasPlafonDerecho   = cursor.getString(79);
                String remolquePuertasPlafonIzquierdo   = cursor.getString(80);
                String remolquePlacasLuzPlaca   = cursor.getString(81);
                String remolquePlacasPlaca   = cursor.getString(82);
                String remolqueSello1Sello   = cursor.getString(83);
                String remolqueSello1Seguridad   = cursor.getString(84);
                String remolqueSello2Escotilla   = cursor.getString(85);
                String remolqueSello2Sello   = cursor.getString(86);
                String remolqueSello2Seguridad   = cursor.getString(87);
                String remolqueSello2Vvtt   = cursor.getString(88);
                String remolquePisoPLagas   = cursor.getString(89);
                String remolqueTechoPlagas   = cursor.getString(90);
                String remolqueDerLuces   = cursor.getString(91);
                String remolqueDerGaliboFrontal   = cursor.getString(92);
                String remolqueDerParedPlagas   = cursor.getString(93);
                String remolqueDerIzqParedPlagas   = cursor.getString(94);
                String remolqueDerPatin   = cursor.getString(95);
                String remolqueCuartoLadoDer   = cursor.getString(96);
                String LoderaDer   = cursor.getString(97);
                String remolqueLucesDerP2   = cursor.getString(98);
                String luzBarcoDer   = cursor.getString(99);
                String rompevientosDer   = cursor.getString(100);
                String remolqueLlantaDerJumbo   = cursor.getString(101);
                String remolqueLlantaDerEje2Posicion7Marca   = cursor.getString(102);
                String remolqueLlantaDerEje2Posicion7Estatus   = cursor.getString(103);
                String remolqueLlantaDerEje2Posicion8Marca   = cursor.getString(104);
                String remolqueLlantaDerEje2Posicion8Estatus   = cursor.getString(105);
                String remolqueLlantaDerEje1BrilosPivote   = cursor.getString(106);
                String remolqueLlantaDerEje2Posicion7   = cursor.getString(107);
                String remolqueLlantaDerEje2Posicion8   = cursor.getString(108);
                String remolqueLlantaDerEje1MasaYoyo   = cursor.getString(109);
                String remolqueLlantaDerEje1Rin   = cursor.getString(110);
                String remolqueLlantaDerEje1Lodera   = cursor.getString(111);
                String remolqueLlantaDerEje1Posicion3Marca   = cursor.getString(112);
                String remolqueLlantaDerEje1Posicion3Estatus   = cursor.getString(113);
                String remolqueLlantaDerEje1Posicion4Marca   = cursor.getString(114);
                String remolqueLlantaDerEje1Posicion4Estatus   = cursor.getString(115);
                String remolqueLlantaDerEje2BrilosPivote   = cursor.getString(116);
                String remolqueLlantaDerEje1Posicion3   = cursor.getString(117);
                String remolqueLlantaDerEje1Posicion4   = cursor.getString(118);
                String remolqueLlantaDerEje2MasaYoyo   = cursor.getString(119);
                String remolqueLlantaDerEje2Rin   = cursor.getString(120);
                String remolqueLlantaDerEje2Lodera   = cursor.getString(121);
                String remolqueChasisFrontalDerAmortiguador   = cursor.getString(122);
                String remolqueChasisFrontalDerBolsaAire   = cursor.getString(123);
                String remolqueChasisFrontalDerGavilan   = cursor.getString(124);
                String remolqueChasisFrontalDerMuelle   = cursor.getString(125);
                String remolqueChasisFrontalDerRotachamber   = cursor.getString(126);
                String remolqueChasisTraseroDerAmortiguador   = cursor.getString(127);
                String remolqueChasisTraseroDerBolsaAire   = cursor.getString(128);
                String remolqueChasisTraseroDerGavilan   = cursor.getString(129);
                String remolqueChasisTraseroDerMuelle   = cursor.getString(130);
                String remolqueChasisTraseroDerRotachamber   = cursor.getString(131);
                String remolqueDerObservaciones   = cursor.getString(132);
                String fechaFin   = cursor.getString(133);


                CintercambioElectronico cintercambioElectronico = new CintercambioElectronico ( estatus  ,
                        Ufolio    ,
                        terminal  ,
                        idUsuario    ,
                        tipoOperacion    ,
                        tipoMovimiento    ,
                        estatusRemolque    ,
                        comentario2   ,
                        nombreOperador    ,
                        idOperador   ,
                        idTransportista    ,
                        unidad    ,
                        idUnidad   ,
                        idLinea    ,
                        remolque    ,
                        idRemolque   ,
                        fechaInicio    ,
                        tractoDefensa   ,
                        tractoCabina   ,
                        tractoQuintaRueda   ,
                        tractoTuboEscape   ,
                        tractoBaseRemolque   ,
                        tractoTechos   ,
                        tractoLlantas   ,
                        tractoTanqueDiesel   ,
                        tractoTanqueAire   ,
                        tractoEjeTransmision   ,
                        tractoMotor   ,
                        remolqueInspeccionMecanica   ,
                        remolqueLucesIzquierda   ,
                        remolqueLucesGaliboIzqFrontalSup   ,
                        remolqueManitasIzq   ,
                        remolqueManivelaIzq   ,
                        remolquePatinIzquierdo   ,
                        remolqueCuartoLadoIzq   ,
                        LoderaIzq   ,
                        remolqueLucesIzqP   ,
                        LuzAbsIzq   ,
                        luzBarcoIzq   ,
                        rompevientosIzq   ,
                        remolqueLlantaIzqJumbo   ,
                        remolqueLlantaIzqEje1Posicion1Marca   ,
                        remolqueLlantaIzqEje1Posicion1Estatus   ,
                        remolqueLlantaIzqEje1Posicion2Marca   ,
                        remolqueLlantaIzqEje1Posicion2Estatus   ,
                        remolqueLlantaIzqEje1BrilosPivote   ,
                        remolqueLlantaIzqEje1Posicion1   ,
                        remolqueLlantaIzqEje1Posicion2   ,
                        remolqueLlantaIzqEje1MesaYoyo   ,
                        remolqueLlantaIzqEje1Rin   ,
                        remolqueLlantaIzqEje1Lodera   ,
                        remolqueLlantaIzqEje2Posicion5Marca   ,
                        remolqueLlantaIzqEje2Posicion5Estatus   ,
                        remolqueLlantaIzqEje2Posicion6Marca   ,
                        remolqueLlantaIzqEje2Posicion6Estatus   ,
                        remolqueLlantaIzqEje2BrilosPivote   ,
                        remolqueLlantaIzqEje2Posicion5   ,
                        remolqueLlantaIzqEje2Posicion6   ,
                        remolqueLlantaIzqEje2MesaYoyo   ,
                        remolqueLlantaIzqEje2Rin   ,
                        remolqueLlantaIzqEje2Lodera   ,
                        remolqueChasisFrontalIzqAmortiguador   ,
                        remolqueChasisFrontalIzqBolsaAire   ,
                        remolqueChasisFrontalIzqGavilan   ,
                        remolqueChasisFrontalIzqMuelle   ,
                        remolqueChasisFrontalIzqRotachamber   ,
                        remolqueChasisTraseroIzqAmortiguador   ,
                        remolqueChasisTraseroIzqBolsaAire   ,
                        remolqueChasisTraseroIzqGavilan   ,
                        remolqueChasisTraseroIzqMuelle   ,
                        remolqueChasisTraseroIzqRotachamber   ,
                        remolqueIzqObservaciones   ,
                        Placas   ,
                        Sello1   ,
                        Sello2   ,
                        Sello3   ,
                        remolquePuertasBisagrasTornillos   ,
                        remolquePuertasDefensa   ,
                        remolquePuertasLuzGaliboSupTraseras   ,
                        remolquePuertasPlafonDerecho   ,
                        remolquePuertasPlafonIzquierdo   ,
                        remolquePlacasLuzPlaca   ,
                        remolquePlacasPlaca   ,
                        remolqueSello1Sello   ,
                        remolqueSello1Seguridad   ,
                        remolqueSello2Escotilla   ,
                        remolqueSello2Sello   ,
                        remolqueSello2Seguridad   ,
                        remolqueSello2Vvtt   ,
                        remolquePisoPLagas   ,
                        remolqueTechoPlagas   ,
                        remolqueDerLuces   ,
                        remolqueDerGaliboFrontal   ,
                        remolqueDerParedPlagas   ,
                        remolqueDerIzqParedPlagas   ,
                        remolqueDerPatin   ,
                        remolqueCuartoLadoDer   ,
                        LoderaDer   ,
                        remolqueLucesDerP2   ,
                        luzBarcoDer   ,
                        rompevientosDer   ,
                        remolqueLlantaDerJumbo   ,
                        remolqueLlantaDerEje2Posicion7Marca   ,
                        remolqueLlantaDerEje2Posicion7Estatus   ,
                        remolqueLlantaDerEje2Posicion8Marca   ,
                        remolqueLlantaDerEje2Posicion8Estatus   ,
                        remolqueLlantaDerEje1BrilosPivote   ,
                        remolqueLlantaDerEje2Posicion7   ,
                        remolqueLlantaDerEje2Posicion8   ,
                        remolqueLlantaDerEje1MasaYoyo   ,
                        remolqueLlantaDerEje1Rin   ,
                        remolqueLlantaDerEje1Lodera   ,
                        remolqueLlantaDerEje1Posicion3Marca   ,
                        remolqueLlantaDerEje1Posicion3Estatus   ,
                        remolqueLlantaDerEje1Posicion4Marca   ,
                        remolqueLlantaDerEje1Posicion4Estatus   ,
                        remolqueLlantaDerEje2BrilosPivote   ,
                        remolqueLlantaDerEje1Posicion3   ,
                        remolqueLlantaDerEje1Posicion4   ,
                        remolqueLlantaDerEje2MasaYoyo   ,
                        remolqueLlantaDerEje2Rin   ,
                        remolqueLlantaDerEje2Lodera   ,
                        remolqueChasisFrontalDerAmortiguador   ,
                        remolqueChasisFrontalDerBolsaAire   ,
                        remolqueChasisFrontalDerGavilan   ,
                        remolqueChasisFrontalDerMuelle   ,
                        remolqueChasisFrontalDerRotachamber   ,
                        remolqueChasisTraseroDerAmortiguador   ,
                        remolqueChasisTraseroDerBolsaAire   ,
                        remolqueChasisTraseroDerGavilan   ,
                        remolqueChasisTraseroDerMuelle   ,
                        remolqueChasisTraseroDerRotachamber   ,
                        remolqueDerObservaciones   ,
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

}