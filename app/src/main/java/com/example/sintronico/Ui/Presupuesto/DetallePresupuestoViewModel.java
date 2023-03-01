package com.example.sintronico.Ui.Presupuesto;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sintronico.Modelo.Bicicleta;
import com.example.sintronico.Modelo.DetallePresupuesto;
import com.example.sintronico.Modelo.Presupuesto;
import com.example.sintronico.Request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePresupuestoViewModel extends AndroidViewModel
{
    private Context context;
    private MutableLiveData<Presupuesto> PresupuestoMutable;
    private MutableLiveData<ArrayList<DetallePresupuesto>> detalleMutable;
    private MutableLiveData<String> nombreMutable;

    public DetallePresupuestoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Presupuesto> getPresupuestoMutable()
    {
        if(PresupuestoMutable == null)
        {
            PresupuestoMutable = new MutableLiveData<>();
        }
        return PresupuestoMutable;
    }

    public LiveData<ArrayList<DetallePresupuesto>> getDetalleMutable()
    {
        if(detalleMutable == null)
        {
            detalleMutable = new MutableLiveData<>();
        }
        return detalleMutable;
    }

    public LiveData<String> getNombreMutable()
    {
        if(nombreMutable == null)
        {
            nombreMutable = new MutableLiveData<>();
        }
        return nombreMutable;
    }

    public void ObtenerDetalles(Bundle bundle)
    {
        Presupuesto presupuesto = (Presupuesto) bundle.getSerializable("presupuesto");
        PresupuestoMutable.postValue(presupuesto);
        int id = presupuesto.getIdPresupuesto();
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<ArrayList<DetallePresupuesto>> detallePresupuesto = ApiRetrofit.getServiceSintronico().ObtenerDetalle(sp.getString("token","-1"),id);
        detallePresupuesto.enqueue(new Callback<ArrayList<DetallePresupuesto>>() {
            @Override
            public void onResponse(Call<ArrayList<DetallePresupuesto>> call, Response<ArrayList<DetallePresupuesto>> response) {
                ArrayList lista = response.body();
                detalleMutable.postValue(lista);
            }

            @Override
            public void onFailure(Call<ArrayList<DetallePresupuesto>> call, Throwable t) {

            }
        });
    }

    public void CambiarEstado(Bundle bundle)
    {
        Presupuesto pre = (Presupuesto) bundle.getSerializable("presupuesto");
        int id = pre.getIdPresupuesto();
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<Presupuesto> p= ApiRetrofit.getServiceSintronico().Aprobado(sp.getString("token","-1"), id);
        Log.d( "Salida"," " + p);
        p.enqueue(new Callback<Presupuesto>() {
            @Override
            public void onResponse(Call<Presupuesto> call, Response<Presupuesto> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(context, "El presupuesto fue aceptado de forma correcta", Toast.LENGTH_SHORT).show();
                    nombreMutable.postValue("Aprobado");
                }
                else
                {
                    Toast.makeText(context, "El presupuesto no pudo ser aprobado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Presupuesto> call, Throwable t) {

            }
        });
    }


}