package com.example.sintronico.Ui.Inicio;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sintronico.R;
import com.example.sintronico.databinding.FragmentInicioBinding;
import com.google.android.gms.maps.SupportMapFragment;

public class InicioFragment extends Fragment {

    private InicioViewModel inicioViewModel;
    private FragmentInicioBinding binding;

    public static InicioFragment newInstance() {
        return new InicioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);
        binding = FragmentInicioBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        validarPermisos();



        inicioViewModel.getLeerMapaMutable().observe(getViewLifecycleOwner(), new Observer<LeerMapa>() {
            @Override
            public void onChanged(LeerMapa leerMapa) {

                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.mvMapa);
                mapFragment.getMapAsync(leerMapa);
            }
        });

        inicioViewModel.ObtenerMapa();

        return view;
    }

    public void validarPermisos()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
        {
            if (checkSelfPermission(getContext(),ACCESS_FINE_LOCATION) != PermissionChecker.PERMISSION_GRANTED )
            {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION},1000);
            }
            if (checkSelfPermission(getContext(),ACCESS_COARSE_LOCATION) != PermissionChecker.PERMISSION_GRANTED )
            {
                requestPermissions(new String[]{ACCESS_COARSE_LOCATION},1000);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}