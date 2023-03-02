package com.example.sintronico.Ui.Pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sintronico.Modelo.Pago;
import com.example.sintronico.R;

import java.util.ArrayList;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Pago> pagos;
    private LayoutInflater inflater;

    public PagoAdapter(Context context,ArrayList<Pago> lista, LayoutInflater layout)
    {
        this.context = context;
        this.pagos = lista;
        this.inflater = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pagos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.tvCodigoPago.setText("Codigo de pago: "+pagos.get(position).getIdPago()+"");
        holder.tvCodigoPresupuesto.setText("Cosigo de presupuesto: "+pagos.get(position).getPresupuesto().getIdPresupuesto()+"");
        holder.tvMontoPago.setText("Monto abonado: "+pagos.get(position).getMonto()+"");
        holder.tvFechaPago.setText("Fecha de emision: "+pagos.get(position).getFechaEmision());
    }


    @Override
    public int getItemCount()
    {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvCodigoPago,tvCodigoPresupuesto,tvMontoPago,tvFechaPago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoPago = itemView.findViewById(R.id.tvCodigoPago);
            tvCodigoPresupuesto = itemView.findViewById(R.id.tvCodigoPresupuesto);
            tvMontoPago = itemView.findViewById(R.id.tvMontoPago);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);
        }
    }
}
