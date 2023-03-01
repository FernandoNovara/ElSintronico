package com.example.sintronico.Ui.Bicicletas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sintronico.Modelo.Bicicleta;
import com.example.sintronico.R;

import java.util.ArrayList;

public class BicicletaAdapter extends RecyclerView.Adapter<BicicletaAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Bicicleta> bicicletas;
    private LayoutInflater inflater;

    public BicicletaAdapter(Context context,ArrayList<Bicicleta> lista, LayoutInflater layout)
    {
        this.context = context;
        this.bicicletas = lista;
        this.inflater = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_bicicleta,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.tvMarca.setText(bicicletas.get(position).getMarca());
        holder.tvNumero.setText(bicicletas.get(position).getNumeroSerie());
        Glide.with(context)
                .load(bicicletas.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivBicicleta);
    }


    @Override
    public int getItemCount()
    {
        return bicicletas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvMarca,tvNumero;
        ImageView ivBicicleta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            ivBicicleta = itemView.findViewById(R.id.ivBicicleta);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Bicicleta bicicleta = bicicletas.get(getAdapterPosition());
                    bundle.putSerializable("bicicleta",bicicleta);
                    Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.detalleBicicletaFragment,bundle);
                }
            });
        }
    }
}
