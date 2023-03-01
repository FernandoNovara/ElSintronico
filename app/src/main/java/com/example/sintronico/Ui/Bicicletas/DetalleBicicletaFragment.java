package com.example.sintronico.Ui.Bicicletas;

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

import com.example.sintronico.Modelo.Bicicleta;
import com.example.sintronico.R;

public class DetalleBicicletaFragment extends Fragment {

    private DetalleBicicletaViewModel detalleViewModel;
    private EditText edDueñoBicicleta,edMarcaBicicleta,edColorBicicleta,edNumeroSerieBicicleta,edTipoBicicleta;

    private Button btnAprobar;

    public static DetalleBicicletaFragment newInstance() {
        return new DetalleBicicletaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_bicicleta, container, false);

        detalleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleBicicletaViewModel.class);

        detalleViewModel.getBicicletaMutable().observe(getViewLifecycleOwner(), new Observer<Bicicleta>() {
            @Override
            public void onChanged(Bicicleta bicicleta) {
                edDueñoBicicleta.setText(bicicleta.getdueño().getApellido() + " " + bicicleta.getdueño().getNombre());
                edMarcaBicicleta.setText(bicicleta.getMarca());
                edColorBicicleta.setText(bicicleta.getColor());
                edNumeroSerieBicicleta.setText(bicicleta.getNumeroSerie());
                edTipoBicicleta.setText(bicicleta.getTipo());
            }
        });

        Inicializar(view);

        return view;
    }

    private void Inicializar(View view)
    {
        this.edDueñoBicicleta = view.findViewById(R.id.edDueñoBicicleta);
        this.edMarcaBicicleta = view.findViewById(R.id.edMarcaBicicleta);
        this.edColorBicicleta = view.findViewById(R.id.edColorBicicleta);
        this.edNumeroSerieBicicleta = view.findViewById(R.id.edNumeroSerieBicicleta);
        this.edTipoBicicleta = view.findViewById(R.id.edTipoBicicleta);




        detalleViewModel.ObtenerBicicleta(getArguments());
    }

}