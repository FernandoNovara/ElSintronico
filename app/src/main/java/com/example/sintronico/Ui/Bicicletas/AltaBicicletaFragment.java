package com.example.sintronico.Ui.Bicicletas;

import static android.Manifest.permission.CAMERA;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.sintronico.Modelo.Bicicleta;
import com.example.sintronico.R;

import java.io.ByteArrayOutputStream;
import java.util.Base64;


public class AltaBicicletaFragment extends Fragment {

    private AltaBicicletaViewModel altaViewModel;
    private EditText edMarcaAlta,edColorAlta,edNumeroSerieAlta,edTipoAlta;
    private ImageView ivFotoBicicleta;
    private Context context;
    private Button btnAgregarBicicleta;
    private int REQUEST_IMAGE_CAPTURE = 1;

    private String encoded;

    public static AltaBicicletaFragment newInstance() {
        return new AltaBicicletaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        altaViewModel = new ViewModelProvider(this).get(AltaBicicletaViewModel.class);
        View view = inflater.inflate(R.layout.fragment_alta_bicicleta, container, false);

        this.context = view.getContext();

        //altaViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AltaBicicletaViewModel.class);

        altaViewModel.getFotoMutable().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                ivFotoBicicleta.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte [] b = baos.toByteArray();

                encoded = Base64.getEncoder().encodeToString(b);
            }
        });

        Inicializar(view);

        return view;
    }

    private void Inicializar(View view)
    {
        this.edMarcaAlta = view.findViewById(R.id.edMarcaAlta);
        this.edColorAlta = view.findViewById(R.id.edColorAlta);
        this.edNumeroSerieAlta = view.findViewById(R.id.edNumeroSerieAlta);
        this.edTipoAlta = view.findViewById(R.id.edTipoAlta);
        this.btnAgregarBicicleta = view.findViewById(R.id.btnAgregarBicicleta);
        this.ivFotoBicicleta = view.findViewById(R.id.ivFotoBicicleta);

        btnAgregarBicicleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bicicleta bicicleta = new Bicicleta();
                bicicleta.setMarca(edMarcaAlta.getText().toString());
                bicicleta.setColor(edColorAlta.getText().toString());
                bicicleta.setTipo(edTipoAlta.getText().toString());
                bicicleta.setNumeroSerie(edNumeroSerieAlta.getText().toString());
                bicicleta.setImagen(encoded);
                altaViewModel.Alta(bicicleta);
                Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.bicicletasFragment);
            }
        });

        ivFotoBicicleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Salida","Entro al click foto");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && checkSelfPermission(context,CAMERA) != PermissionChecker.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},1000);
                }
                else
                {
                    AbrirCamara(v);
                }
            }
        });
    }

    public void AbrirCamara(View view) {
        Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (tomarFoto.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(tomarFoto, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        altaViewModel.SacarFoto(requestCode,resultCode,data,REQUEST_IMAGE_CAPTURE);

    }
}