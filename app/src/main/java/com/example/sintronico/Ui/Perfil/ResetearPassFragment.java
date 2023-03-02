package com.example.sintronico.Ui.Perfil;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sintronico.R;

public class ResetearPassFragment extends Fragment {

    private Context context;
    private ResetearPassViewModel resetearViewModel;
    private EditText edContraseñaNueva,edConfirmarContraseña;
    private Button btnCambiar;

    public static ResetearPassFragment newInstance() {
        return new ResetearPassFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resetear_pass, container, false);
        this.context = view.getContext();
        resetearViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ResetearPassViewModel.class);
        Inicializar(view);
        return view;
    }

    private void Inicializar(View view)
    {
        edContraseñaNueva = view.findViewById(R.id.edContraseñaNueva);
        edConfirmarContraseña = view.findViewById(R.id.edConfirmarContraseña);
        btnCambiar = view.findViewById(R.id.btnCambiar);

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    resetearViewModel.CambiarContraseña(edContraseñaNueva.getText().toString());
                Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.perfilFragment);
            }
        });


    }


}