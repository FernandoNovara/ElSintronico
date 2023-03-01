package com.example.sintronico.Ui.Repuestos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sintronico.Modelo.Repuesto;
import com.example.sintronico.R;

import java.util.ArrayList;

public class RepuestosFragment extends Fragment {

    private RepuestosViewModel repuestosViewModel;
    private Context context;
    private RepuestoAdapter repuestoAdapter;
    private RecyclerView rvRepuesto;

    public static RepuestosFragment newInstance() {
        return new RepuestosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repuestos, container, false);

        this.context = view.getContext();

        repuestosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(RepuestosViewModel.class);

        repuestosViewModel.getRepuestosMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Repuesto>>() {
            @Override
            public void onChanged(ArrayList<Repuesto> repuestos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                rvRepuesto.setLayoutManager(gridLayoutManager);
                repuestoAdapter = new RepuestoAdapter(context,repuestos,getLayoutInflater());
                rvRepuesto.setAdapter(repuestoAdapter);
            }
        });

        Inicializar(view);

        return view;
    }

    private void Inicializar(View view)
    {
        this.rvRepuesto = view.findViewById(R.id.rvRepuesto);

        repuestosViewModel.ObtenerRepuestos();
    }


}