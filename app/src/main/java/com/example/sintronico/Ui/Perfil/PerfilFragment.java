package com.example.sintronico.Ui.Perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sintronico.Modelo.Propietario;
import com.example.sintronico.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText edNombre,edApellido,edDni,edTelefono,edEmail,edDireccion;
    private Button btnEditar;


    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        perfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                edNombre.setText(propietario.getNombre());
                edApellido.setText(propietario.getApellido());
                edDni.setText(propietario.getDni());
                edDireccion.setText(propietario.getDireccion());
                edTelefono.setText(propietario.getTelefono());
                edEmail.setText(propietario.getEmail());
            }
        });

        perfilViewModel.getNombreBotonMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btnEditar.setText(s);
            }
        });

        perfilViewModel.getEditarMutable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                edNombre.setEnabled(aBoolean);
                edApellido.setEnabled(aBoolean);
                edDni.setEnabled(aBoolean);
                edDireccion.setEnabled(aBoolean);
                edTelefono.setEnabled(aBoolean);
                edEmail.setEnabled(aBoolean);
            }
        });

        Inicializar(view);

        return view;

    }

    private void Inicializar(View view)
    {
        edNombre = view.findViewById(R.id.edNombre);
        edApellido = view.findViewById(R.id.edApellido);
        edDni = view.findViewById(R.id.edDni);
        edDireccion = view.findViewById(R.id.edDireccion);
        edTelefono = view.findViewById(R.id.edTelefono);
        edEmail = view.findViewById(R.id.edEmail);
        btnEditar = view.findViewById(R.id.btnEditar);

        perfilViewModel.ObtenerPerfil();

        btnEditar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Propietario p = new Propietario();
            p.setNombre(edNombre.getText().toString());
            p.setApellido(edApellido.getText().toString());
            p.setDni(edDni.getText().toString());
            p.setDireccion(edDireccion.getText().toString());
            p.setTelefono(edTelefono.getText().toString());
            p.setEmail(edEmail.getText().toString());

            perfilViewModel.cambio(btnEditar.getText().toString(),p);
        }
    });

    }

}