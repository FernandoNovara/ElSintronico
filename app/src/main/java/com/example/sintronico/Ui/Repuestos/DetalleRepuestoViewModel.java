package com.example.sintronico.Ui.Repuestos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sintronico.Modelo.Propietario;
import com.example.sintronico.Modelo.Repuesto;
import com.example.sintronico.Request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleRepuestoViewModel extends AndroidViewModel
{
    private Context context;
    private MutableLiveData<Repuesto> repuestoMutable;

    public DetalleRepuestoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Repuesto> getRepuestoMutable()
    {
        if(repuestoMutable == null)
        {
            repuestoMutable = new MutableLiveData<>();
        }
        return repuestoMutable;
    }

    public void detalles(Bundle bundle)
    {
        Repuesto repuesto = (Repuesto) bundle.getSerializable("repuesto");
        repuestoMutable.postValue(repuesto);
    }

    public void Comprar()
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<Propietario> aviso = ApiRetrofit.getServiceSintronico().AvisarCompra(sp.getString("token","-1"));
        aviso.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(context, "Tu compra se realizo con exito! revisa tu email para confirmar.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "No se pudo realizar la compra.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

            }
        });
    }
}