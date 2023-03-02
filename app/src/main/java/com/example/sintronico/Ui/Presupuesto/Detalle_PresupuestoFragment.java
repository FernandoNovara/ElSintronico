package com.example.sintronico.Ui.Presupuesto;

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
import android.widget.TextView;

import com.example.sintronico.Modelo.DetallePresupuesto;
import com.example.sintronico.Modelo.Presupuesto;
import com.example.sintronico.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Detalle_PresupuestoFragment extends Fragment {

    private EditText edBicicletaDetalle,edFechaInicioDetalle,edDetallesDetalle;
    private TextView tvTotalDetalles;
    private Button btnAprobar;
    private DetallePresupuestoViewModel DetallePresupuestoViewModel;

    public static Detalle_PresupuestoFragment newInstance() {
        return new Detalle_PresupuestoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_presupuesto, container, false);

        DetallePresupuestoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetallePresupuestoViewModel.class);

        DetallePresupuestoViewModel.getDetalleMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<DetallePresupuesto>>() {
            @Override
            public void onChanged(ArrayList<DetallePresupuesto> detallePresupuestos) {
                edDetallesDetalle.setText("Cant.  Repuesto/Arreglo       Precio \n");
                String texto;
                double total = 0;
                for (DetallePresupuesto detalle : detallePresupuestos)
                {
                    texto = edDetallesDetalle.getText().toString();
                    edDetallesDetalle.setText(texto.concat( "    " + detalle.getCantidad() + "     " + detalle.getRepuesto().getNombre() +"      "+ detalle.getRepuesto().getMonto() + "\n" ));
                    total += detalle.getRepuesto().getMonto() * detalle.getCantidad();
                }
                tvTotalDetalles.setText("Total de Presupuesto: $" + total);
            }
        });

        DetallePresupuestoViewModel.getNombreMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btnAprobar.setText(s);
                btnAprobar.setEnabled(false);
            }
        });

        DetallePresupuestoViewModel.getPresupuestoMutable().observe(getViewLifecycleOwner(), new Observer<Presupuesto>() {
            @Override
            public void onChanged(Presupuesto presupuesto) {
                edBicicletaDetalle.setText(presupuesto.getBicicleta().getMarca());
                edFechaInicioDetalle.setText(presupuesto.getFechaInicio());
                if(presupuesto.getEstado().equals("En Cola"))
                {
                    btnAprobar.setText("Aprobado");
                    btnAprobar.setEnabled(false);
                }
            }
        });

        Inicializar(view);

        return view;
    }

    private void Inicializar(View view)
    {
        this.edBicicletaDetalle = view.findViewById(R.id.edBicicletaDetalle);
        this.edFechaInicioDetalle = view.findViewById(R.id.edFechaInicioDetalle);
        this.edDetallesDetalle = view.findViewById(R.id.edDetallesDetalle);
        this.tvTotalDetalles = view.findViewById(R.id.tvTotalDetalles);
        this.btnAprobar = view.findViewById(R.id.btnAprobar);

        btnAprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetallePresupuestoViewModel.CambiarEstado(getArguments());
            }
        });

        DetallePresupuestoViewModel.ObtenerDetalles(getArguments());
    }


}