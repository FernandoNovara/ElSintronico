package com.example.sintronico.Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.function.ObjIntConsumer;

public class Presupuesto implements Serializable
{
    private int idPresupuesto;
    private int idBicicleta;
    private Bicicleta bicicleta;
    private String fechaInicio;
    private String fechaEntrega;
    private Double monto;
    private String estado;

    public void setIdPresupuesto(int IdPresupuesto)
    {
        this.idPresupuesto = IdPresupuesto;
    }

    public int getIdPresupuesto()
    {
        return this.idPresupuesto;
    }

    public void setIdBicicleta(int IdBicicleta)
    {
        this.idBicicleta = IdBicicleta;
    }

    public int getIIdBicicleta()
    {
        return this.idBicicleta;
    }

    public void setFechaInicio(String FechaInicio)
    {
        this.fechaInicio = FechaInicio;
    }

    public String getFechaInicio()
    {
        return this.fechaInicio;
    }

    public void setFechaEntrega(String FechaEntrega)
    {
        this.fechaEntrega = FechaEntrega;
    }

    public String getFechaEntrega()
    {
        return this.fechaEntrega;
    }

    public void setMonto(double Monto)
    {
        this.monto = Monto;
    }

    public double getMonto()
    {
        return this.monto;
    }

    public void setEstado(String Estado)
    {
        this.estado = Estado;
    }

    public String getEstado()
    {
        return this.estado;
    }

    public void setBicicleta(Bicicleta Bicicleta)
    {
        this.bicicleta = Bicicleta;
    }

    public Bicicleta getBicicleta()
    {
        return this.bicicleta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Presupuesto that = (Presupuesto) o;
        return idPresupuesto == that.idPresupuesto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPresupuesto);
    }

}
