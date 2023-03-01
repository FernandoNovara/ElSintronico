package com.example.sintronico.Ui.Bicicletas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sintronico.Modelo.Bicicleta;
import com.example.sintronico.Request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BicicletasViewModel extends AndroidViewModel
{
    private MutableLiveData<ArrayList<Bicicleta>> bicicletaMutable;
    private Context context;

    public BicicletasViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Bicicleta>> getBicicletaMutable()
    {
        if (bicicletaMutable == null)
        {
            bicicletaMutable = new MutableLiveData<>();
        }
        return bicicletaMutable;
    }

    public void ObtenerBicicletas()
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<ArrayList<Bicicleta>> bicicletas = ApiRetrofit.getServiceSintronico().obtenerBicicletas(sp.getString("token","-1"));
        bicicletas.enqueue(new Callback<ArrayList<Bicicleta>>() {
            @Override
            public void onResponse(Call<ArrayList<Bicicleta>> call, Response<ArrayList<Bicicleta>> response)
            {
                if (response.isSuccessful())
                {
                    ArrayList lista = response.body();
                    bicicletaMutable.postValue(lista);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Bicicleta>> call, Throwable t) {

            }
        });
    }


}