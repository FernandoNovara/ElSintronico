package com.example.sintronico.Modelo;

import java.util.Objects;

public class DetallePresupuesto {

    private int idDetalle;
    private int cantidad;
    private double precio;
    private Presupuesto presupuesto;
    private Repuesto repuestos;

    public void setIdDetalle(int IdDetalle)
    {
        this.idDetalle = IdDetalle;
    }

    public int getIdDetalle()
    {
        return this.idDetalle;
    }

    public void setCantidad(int Cantidad)
    {
        this.cantidad = Cantidad;
    }

    public int getCantidad()
    {
        return this.cantidad;
    }

    public void setPrecio(double Precio)
    {
        this.precio = Precio;
    }

    public double getPrecio()
    {
        return this.precio;
    }

    public void setPresupuesto(Presupuesto Presupuesto)
    {
        this.presupuesto = Presupuesto;
    }

    public Presupuesto getPresupuesto()
    {
        return this.presupuesto;
    }

    public void setRepuesto(Repuesto Repuesto)
    {
        this.repuestos = Repuesto;
    }

    public Repuesto getRepuesto()
    {
        return this.repuestos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePresupuesto that = (DetallePresupuesto) o;
        return idDetalle == that.idDetalle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalle);
    }
}
