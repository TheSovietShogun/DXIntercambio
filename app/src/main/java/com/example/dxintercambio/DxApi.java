package com.example.dxintercambio;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DxApi {

   @POST("login")
    Call<ResponseBody> createPost(@Body Post post);

   @POST("linea")
    Call<List<CLinea>> getLinea(@Body Post post);

   @POST("operador")
    Call<List<COperador>> getOperador(@Body Post post);

    @POST("flota")
    Call<List<CFlota>> getFlota(@Body Post post);

}
