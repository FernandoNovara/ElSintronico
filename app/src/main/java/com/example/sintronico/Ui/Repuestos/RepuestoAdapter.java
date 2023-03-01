package com.example.sintronico.Ui.Repuestos;

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
import com.example.sintronico.Modelo.Repuesto;
import com.example.sintronico.R;

import java.util.ArrayList;

public class RepuestoAdapter extends RecyclerView.Adapter<RepuestoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Repuesto> repuestos;
    private LayoutInflater inflater;

    public RepuestoAdapter(Context context,ArrayList<Repuesto> lista, LayoutInflater layout)
    {
        this.context = context;
        this.repuestos = lista;
        this.inflater = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_repuesto,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.tvNombre.setText(repuestos.get(position).getNombre());
        holder.tvMonto.setText(repuestos.get(position).getMonto() + " ");
        Glide.with(context)
                .load(repuestos.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivRepuesto);
    }


    @Override
    public int getItemCount()
    {
        return repuestos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNombre,tvMonto;
        ImageView ivRepuesto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvMonto = itemView.findViewById(R.id.tvMonto);
            ivRepuesto = itemView.findViewById(R.id.ivRepuesto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Repuesto repuesto = repuestos.get(getAdapterPosition());
                    bundle.putSerializable("repuesto",repuesto);
                    Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.detalle_RepuestoFragment,bundle);
                }
            });
        }
    }
}
