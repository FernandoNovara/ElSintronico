package com.example.sintronico.Ui.Presupuesto;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sintronico.Modelo.Presupuesto;
import com.example.sintronico.Request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresupuestoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Presupuesto>> presupuestosMutable;


    public PresupuestoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Presupuesto>> getPresupuetosMutable()
    {
        if(presupuestosMutable == null)
        {
            presupuestosMutable = new MutableLiveData<>();
        }
        return presupuestosMutable;
    }


    public void obtenerPresupuestos()
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<ArrayList<Presupuesto>> presupuestos = ApiRetrofit.getServiceSintronico().obtenerPresupuesto(sp.getString("token","-1"));
        presupuestos.enqueue(new Callback<ArrayList<Presupuesto>>() {
            @Override
            public void onResponse(Call<ArrayList<Presupuesto>> call, Response<ArrayList<Presupuesto>> response)
            {
                if (response.isSuccessful())
                {
                    ArrayList<Presupuesto> lista = response.body();
                    presupuestosMutable.postValue(lista);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Presupuesto>> call, Throwable t) {

            }
        });
    }
}