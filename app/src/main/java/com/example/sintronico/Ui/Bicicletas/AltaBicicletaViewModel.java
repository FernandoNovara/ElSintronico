package com.example.sintronico.Ui.Bicicletas;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.sintronico.Modelo.Bicicleta;
import com.example.sintronico.R;
import com.example.sintronico.Request.ApiRetrofit;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AltaBicicletaViewModel extends AndroidViewModel
{
    private Context context;
    private MutableLiveData<Bitmap> fotoMutable;

    public AltaBicicletaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Bitmap> getFotoMutable()
    {
        if(fotoMutable == null)
        {
            fotoMutable = new MutableLiveData<>();
        }
        return fotoMutable;
    }

    public void Alta(Bicicleta bicicleta)
    {
        SharedPreferences sp = ApiRetrofit.obtenerSharedPreferences(context);
        Call<Bicicleta> bici = ApiRetrofit.getServiceSintronico().AltaBicicleta(sp.getString("token","-1"),bicicleta);
        bici.enqueue(new Callback<Bicicleta>() {
            @Override
            public void onResponse(Call<Bicicleta> call, Response<Bicicleta> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(context, "Se agregó correctamente", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "Se pudo agregar la bicicleta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Bicicleta> call, Throwable t) {

            }
        });
    }

    public void SacarFoto(int requestCode, int resultCode, Intent data, int REQUEST_IMAGE_CAPTURE){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            if(data.getExtras().get("data") != null)
            {
                try
                {
                    Bundle extra = data.getExtras();
                    Bitmap foto = (Bitmap) extra.get("data");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    foto.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] b = baos.toByteArray();

                    this.fotoMutable.postValue(foto);
                }
                catch (Exception ex)
                {
                    Toast.makeText(context,"No hay foto",Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}