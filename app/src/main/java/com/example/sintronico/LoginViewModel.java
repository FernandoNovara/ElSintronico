package com.example.sintronico ;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private Context contexto;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.contexto = application.getApplicationContext();
    }

    public void iniciarSesion(String usuario, String contraseña)
    {
        User user = new User("Fernando@mail.com","1234");
        Call<String> tokenPromesa = ApiRetrofit.getServiceSintronico().login(user);
        tokenPromesa.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Log.d("Salida",response.body());
                    SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(contexto);
                    SharedPreferences.Editor editor = sp.edit();
                    String token ="Bearer "+response.body();
                    editor.putString("token",token);
                    editor.commit();

                    Intent i = new Intent(contexto,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    contexto.startActivity(i);


                }
                else
                {
                    Log.d("Salida","sin respuesta");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Log.d("Salida",t.getMessage());
            }
        });

    }
}
