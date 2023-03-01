package com.example.sintronico.Ui.Perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sintronico.Modelo.Propietario;
import com.example.sintronico.Request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> NombreBotonMutable;
    private MutableLiveData<Boolean> EditarMutable;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getPropietarioMutable()
    {
        if(propietarioMutable == null)
        {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public LiveData<String> getNombreBotonMutable()
    {
        if(NombreBotonMutable == null)
        {
            NombreBotonMutable = new MutableLiveData<>();
        }
        return NombreBotonMutable;
    }

    public LiveData<Boolean> getEditarMutable()
    {
        if(EditarMutable == null)
        {
            EditarMutable = new MutableLiveData<>();
        }
        return EditarMutable;
    }

    public void ObtenerPerfil()
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<Propietario> propietario = ApiRetrofit.getServiceSintronico().obtenerPerfil(sp.getString("token","-1"));
        propietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful())
                {
                    propietarioMutable.postValue(response.body());
                }
                else
                {
                    Log.d("Salida", "no hay nada");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

            }
        });
    }

    public void cambio(String nombre,Propietario p)
    {
        if (nombre.equals("Editar"))
        {
            NombreBotonMutable.postValue("Actualizar Datos");
            EditarMutable.postValue(true);
        }
        else
        {
            NombreBotonMutable.postValue("Editar");
            EditarMutable.postValue(false);

            SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
            Call<Propietario> actualizar = ApiRetrofit.getServiceSintronico().Actualizar(sp.getString("token","-1"),p);
            actualizar.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(context,"Usuario actualizado correctamente",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(context,"El Usuario no pudo ser actualizado",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {

                }
            });
        }
    }
}