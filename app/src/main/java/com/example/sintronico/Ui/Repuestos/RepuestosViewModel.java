package com.example.sintronico.Ui.Repuestos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sintronico.Modelo.Repuesto;
import com.example.sintronico.Request.ApiRetrofit;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepuestosViewModel extends AndroidViewModel
{

    private Context context;
    private MutableLiveData<ArrayList<Repuesto>> repuestosMutable;

    public RepuestosViewModel(@NonNull Application application)
    {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Repuesto>> getRepuestosMutable()
    {
        if(repuestosMutable == null)
        {
            repuestosMutable = new MutableLiveData<>();
        }
        return repuestosMutable;
    }

    public void ObtenerRepuestos()
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<ArrayList<Repuesto>> repuestos = ApiRetrofit.getServiceSintronico().obtenerRepuesto(sp.getString("token","-1"));
        repuestos.enqueue(new Callback<ArrayList<Repuesto>>() {
            @Override
            public void onResponse(Call<ArrayList<Repuesto>> call, Response<ArrayList<Repuesto>> response)
            {
                ArrayList lista = response.body();
                repuestosMutable.postValue(lista);
            }

            @Override
            public void onFailure(Call<ArrayList<Repuesto>> call, Throwable t) {

            }
        });
    }

}