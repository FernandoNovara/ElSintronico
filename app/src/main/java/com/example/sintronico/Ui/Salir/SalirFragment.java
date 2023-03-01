package com.example.sintronico.Ui.Salir;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sintronico.R;

public class SalirFragment extends Fragment {


    public static SalirFragment newInstance() {
        return new SalirFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salir, container, false);
        mostrarDialog();
        return view;
    }

    public void mostrarDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getContext())
                .setTitle("Cerrar sesion")
                .setMessage("Desea cerrar la app?")
                .setPositiveButton (R.string.Aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                }).setNegativeButton(R.string.Quedarse, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(getActivity(),R.id.nav_host_fragment_content_main).navigate(R.id.inicioFragment);
                    }
                });
        alertDialog.show();
    }

}