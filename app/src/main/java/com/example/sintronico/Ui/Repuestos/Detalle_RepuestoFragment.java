package com.example.sintronico.Ui.Repuestos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sintronico.Modelo.Repuesto;
import com.example.sintronico.R;

public class Detalle_RepuestoFragment extends Fragment {

    private DetalleRepuestoViewModel detalleRepuesto;
    private EditText edCodigoRepuesto,edNombreRepuesto,edMontoRepuesto,edDetalleRepuesto;
    private Button btnComprar;

    public static Detalle_RepuestoFragment newInstance() {
        return new Detalle_RepuestoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_repuesto, container, false);

        detalleRepuesto = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleRepuestoViewModel.class);

        detalleRepuesto.getRepuestoMutable().observe(getViewLifecycleOwner(), new Observer<Repuesto>() {
            @Override
            public void onChanged(Repuesto repuesto) {
                edCodigoRepuesto.setText(repuesto.getIdRepuesto() + "");
                edNombreRepuesto.setText(repuesto.getNombre());
                edMontoRepuesto.setText(repuesto.getMonto() + "");
                edDetalleRepuesto.setText(repuesto.getDetalle());
            }
        });

        Inicializar(view);

        return view;
    }

    private void Inicializar(View view)
    {
        edCodigoRepuesto = view.findViewById(R.id.edCodigoRepuesto);
        edNombreRepuesto = view.findViewById(R.id.edNombreRepuesto);
        edMontoRepuesto = view.findViewById(R.id.edMontoRepuesto);
        edDetalleRepuesto = view.findViewById(R.id.edDetalleRepuesto);
        btnComprar = view.findViewById(R.id.btnComprar);

        detalleRepuesto.detalles(getArguments());

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detalleRepuesto.Comprar();
            }
        });
    }

}