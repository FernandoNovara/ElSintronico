package com.example.sintronico.Ui.Repuestos;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sintronico.Modelo.Repuesto;

public class DetalleRepuestoViewModel extends AndroidViewModel
{
    private MutableLiveData<Repuesto> repuestoMutable;

    public DetalleRepuestoViewModel(@NonNull Application application) {
        super(application);
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
}