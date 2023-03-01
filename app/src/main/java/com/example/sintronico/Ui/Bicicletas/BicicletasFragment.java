package com.example.sintronico.Ui.Bicicletas;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sintronico.Modelo.Bicicleta;
import com.example.sintronico.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BicicletasFragment extends Fragment {

    private Context context;
    private RecyclerView rvBicicletas;

    private FloatingActionButton fbAgregar;
    private BicicletaAdapter bicicletaAdapter;
    private BicicletasViewModel BicicletaViewModel;

    public static BicicletasFragment newInstance() {
        return new BicicletasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bicicletas, container, false);
        context = view.getContext();

        BicicletaViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(BicicletasViewModel.class);

        BicicletaViewModel.getBicicletaMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Bicicleta>>() {
            @Override
            public void onChanged(ArrayList<Bicicleta> bicicletas) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                rvBicicletas.setLayoutManager(gridLayoutManager);
                bicicletaAdapter = new BicicletaAdapter(context,bicicletas,getLayoutInflater());
                rvBicicletas.setAdapter(bicicletaAdapter);
            }
        });

        Inicializar(view);

        return view;
    }

    private void Inicializar(View view)
    {
        this.rvBicicletas = view.findViewById(R.id.rvBicicletas);
        this.fbAgregar = view.findViewById(R.id.fbAgregar);

        BicicletaViewModel.ObtenerBicicletas();

        fbAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.altaBicicletaFragment);
            }
        });
    }


}