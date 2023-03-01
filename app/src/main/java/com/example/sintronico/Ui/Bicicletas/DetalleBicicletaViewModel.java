package com.example.sintronico.Ui.Bicicletas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sintronico.Modelo.Bicicleta;
import com.example.sintronico.Modelo.Presupuesto;
import com.example.sintronico.Request.ApiRetrofit;

public class DetalleBicicletaViewModel extends AndroidViewModel
{
    private MutableLiveData<Bicicleta> bicicletaMutable;

    private Context context;

    public DetalleBicicletaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Bicicleta> getBicicletaMutable()
    {
        if(bicicletaMutable == null)
        {
            bicicletaMutable = new MutableLiveData<>();
        }
        return bicicletaMutable;
    }

    public void ObtenerBicicleta(Bundle bundle)
    {
        Bicicleta bici = (Bicicleta) bundle.getSerializable("bicicleta");
        bicicletaMutable.postValue(bici);
    }

}