package com.example.sintronico.Ui.Pagos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sintronico.Modelo.Pago;
import com.example.sintronico.R;

import java.util.ArrayList;

public class PagosFragment extends Fragment {

    private Context context;
    private RecyclerView rvPagos;
    private PagoAdapter pagoAdapter;
    private PagosViewModel pagosViewModel;

    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pagos, container, false);

        this.context = view.getContext();

        pagosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagosViewModel.class);

        pagosViewModel.getMutablePagos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,GridLayoutManager.HORIZONTAL,false);
                rvPagos.setLayoutManager(gridLayoutManager);
                pagoAdapter = new PagoAdapter(context,pagos,getLayoutInflater());
                rvPagos.setAdapter(pagoAdapter);
            }
        });



        Inicializar(view);

        return view;
    }

    private void Inicializar(View view)
    {
        rvPagos = view.findViewById(R.id.rvPagos);

        pagosViewModel.ObtenerPagos();
    }

}