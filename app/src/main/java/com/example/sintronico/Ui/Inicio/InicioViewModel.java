package com.example.sintronico.Ui.Inicio;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class InicioViewModel extends AndroidViewModel
{
    private MutableLiveData<LeerMapa> leerMapaMutable;
    private Context context;

    public InicioViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<LeerMapa> getLeerMapaMutable()
    {
        if (leerMapaMutable == null)
        {
            leerMapaMutable = new MutableLiveData<>();
        }
        return leerMapaMutable;
    }

    public void ObtenerMapa()
    {
        leerMapaMutable.setValue(new LeerMapa(context));
    }
}