package com.example.sintronico.Ui.Presupuesto;

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

import com.example.sintronico.Modelo.Presupuesto;
import com.example.sintronico.R;
import com.example.sintronico.Ui.Bicicletas.BicicletaAdapter;

import java.util.ArrayList;

public class PresupuestoFragment extends Fragment {

    private Context context;
    private RecyclerView rvPresupuesto;
    private PresupuestoAdapter presupuestoAdapter;
    private PresupuestoViewModel presupuestoViewModel;

    public static PresupuestoFragment newInstance() {
        return new PresupuestoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presupuesto, container, false);

        context = view.getContext();

        presupuestoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PresupuestoViewModel.class);

        presupuestoViewModel.getPresupuetosMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Presupuesto>>() {
            @Override
            public void onChanged(ArrayList<Presupuesto> presupuestos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                rvPresupuesto.setLayoutManager(gridLayoutManager);
                presupuestoAdapter = new PresupuestoAdapter(context,presupuestos,getLayoutInflater());
                rvPresupuesto.setAdapter(presupuestoAdapter);
            }
        });

        Inicializar(view);

        return view;

    }

    private void Inicializar(View view)
    {
        rvPresupuesto = view.findViewById(R.id.rvPresupuestos);

        presupuestoViewModel.obtenerPresupuestos();
    }


}