package com.example.sintronico.Ui.Pagos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sintronico.Modelo.Pago;
import com.example.sintronico.Request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel
{
    private Context context;
    private MutableLiveData<ArrayList<Pago>> mutablePagos;


    public PagosViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Pago>> getMutablePagos()
    {
        if(mutablePagos == null)
        {
            mutablePagos = new MutableLiveData<>();
        }
        return mutablePagos;
    }

    public void ObtenerPagos()
    {
        Log.d("Salida","Entro al ObtenerPagos");

        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<ArrayList<Pago>> pagos = ApiRetrofit.getServiceSintronico().obtenerPago(sp.getString("token","-1"));
        pagos.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response)
            {
                ArrayList lista = response.body();
                mutablePagos.postValue(lista);
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {

            }
        });
    }
}