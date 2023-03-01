package com.example.sintronico.Request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sintronico.Modelo.Bicicleta;
import com.example.sintronico.Modelo.DetallePresupuesto;
import com.example.sintronico.Modelo.Pago;
import com.example.sintronico.Modelo.Presupuesto;
import com.example.sintronico.Modelo.Propietario;
import com.example.sintronico.Modelo.Repuesto;
import com.example.sintronico.Modelo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class ApiRetrofit
{

    private static final String PATH="http://192.168.1.115:5000/api/";
    private static serviceSintronico serviceSintronico;
    private static SharedPreferences sp;

    //con este metodo se obtiene el sharedpreferences de forma staticas y todo manejada por una clase

    public static SharedPreferences obtenerSharedPreferences(Context context)
    {
        if (sp == null)
        {
            sp = context.getSharedPreferences("Configuracion",0);
        }
        return sp;
    }


    public static serviceSintronico getServiceSintronico()
    {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        serviceSintronico = retrofit.create(serviceSintronico.class);
        return serviceSintronico;
    }

    public interface serviceSintronico
    {
        //Url base:"http://192.168.1.115:5000/api/"

        @POST("Propietario/Login")
        Call<String> login(@Body User user);

        @GET("Propietario")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @POST("Propietario/Actualizar")
        Call<Propietario> Actualizar(@Header("Authorization") String token, @Body Propietario propietario );

        @GET("Bicicleta")
        Call<ArrayList<Bicicleta>> obtenerBicicletas(@Header("Authorization") String token);

        @GET("Presupuesto")
        Call<ArrayList<Presupuesto>> obtenerPresupuesto(@Header("Authorization") String token);

        @POST("Presupuesto/Aprobado")
        Call<Presupuesto> Aprobado (@Header("Authorization") String token,@Body int id);

        @FormUrlEncoded
        @POST("DetallePresupuesto/ObtenerDetalle")
        Call<ArrayList<DetallePresupuesto>> ObtenerDetalle(@Header("Authorization") String token,@Field("id") int id);

        @GET("Repuesto")
        Call<ArrayList<Repuesto>> obtenerRepuesto(@Header("Authorization") String token);

        @GET("Pago")
        Call<ArrayList<Pago>> obtenerPago(@Header("Authorization") String token);

        @GET("Bicicleta/ObtenerBicicletaPorId")
        Call<Bicicleta> obtenerBicicletaPorId(@Header("Authorization") String token,@Body int id);

        @POST("Bicicleta/AltaBicicleta")
        Call<Bicicleta> AltaBicicleta(@Header("Authorization") String token,@Body Bicicleta bicicleta);

        @FormUrlEncoded
        @POST("Propietario/ObtenerEmail")
        Call<Propietario> ObtenerEmail(@Field("Email") String email);

        @FormUrlEncoded
        @POST("Propietario/ResetearPass")
        Call<Propietario> ResetearPass(@Header("Authorization") String token, @Field("contraseña") String contraseña);
    }
}
