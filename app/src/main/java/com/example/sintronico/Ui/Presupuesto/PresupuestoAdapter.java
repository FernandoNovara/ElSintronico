package com.example.sintronico.Ui.Presupuesto;

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
import com.example.sintronico.Modelo.Presupuesto;
import com.example.sintronico.R;

import java.util.ArrayList;

public class PresupuestoAdapter extends RecyclerView.Adapter<PresupuestoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Presupuesto> presupuestos;
    private LayoutInflater inflater;

    public PresupuestoAdapter(Context context,ArrayList<Presupuesto> lista, LayoutInflater layout)
    {
        this.context = context;
        this.presupuestos = lista;
        this.inflater = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_presupuestos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.tvMarca.setText(presupuestos.get(position).getBicicleta().getMarca());
        holder.tvTipo.setText(presupuestos.get(position).getBicicleta().getTipo());
        Glide.with(context)
                .load(presupuestos.get(position).getBicicleta().getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivDetallePresupuesto);
    }


    @Override
    public int getItemCount()
    {
        return presupuestos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvMarca,tvTipo;
        ImageView ivDetallePresupuesto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            ivDetallePresupuesto = itemView.findViewById(R.id.ivDetallePresupuesto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Presupuesto presupuesto = presupuestos.get(getAdapterPosition());
                    bundle.putSerializable("presupuesto",presupuesto);
                    Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.detalle_PresupuestoFragment,bundle);
                }
            });
        }
    }
}
