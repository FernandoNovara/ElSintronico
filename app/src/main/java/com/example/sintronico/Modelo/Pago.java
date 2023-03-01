package com.example.sintronico.Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Pago {

    private int idPago;
    private int idPresupuesto;
    private Presupuesto presupuesto;
    private double monto;
    private String fechaEmision;


    public void setIdPago(int IdPago)
    {
        this.idPago = IdPago;
    }

    public int getIdPago()
    {
        return this.idPago;
    }

    public void setIdPresupuesto(int idPresupuesto)
    {
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdPresupuesto()
    {
        return this.idPresupuesto;
    }

    public void setMonto(double Monto)
    {
        this.monto = Monto;
    }

    public double getMonto()
    {
        return this.monto;
    }

    public void setFechaEmision(String FechaEmision)
    {
        this.fechaEmision = FechaEmision;
    }

    public String getFechaEmision()
    {
        return this.fechaEmision;
    }

    public void setPresupuesto(Presupuesto Presupuesto)
    {
        this.presupuesto = Presupuesto;
    }

    public Presupuesto getPresupuesto()
    {
        return this.presupuesto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago that = (Pago) o;
        return idPago == that.idPago;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago);
    }
}
