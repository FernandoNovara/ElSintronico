package com.example.sintronico.Ui.Perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.sintronico.Modelo.Propietario;
import com.example.sintronico.Request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetearPassViewModel extends AndroidViewModel {

    private Context context;
    public ResetearPassViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public void CambiarContraseña(String pass)
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<Propietario> contraseña = ApiRetrofit.getServiceSintronico().ResetearPass(sp.getString("token","-1"), pass);
        contraseña.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(context,"Sea cambiado correctamente",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context,"No se pudo cambiar.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

            }
        });
    }

}