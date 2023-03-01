package com.example.sintronico.Modelo;

import java.io.Serializable;
import java.util.Objects;

public class Repuesto implements Serializable
{
    private int idRepuesto;
    private String nombre;
    private String tipo;
    private double monto;
    private String imagen;
    private String detalle;

    public void setIdRepuesto(int Repuesto)
    {
        this.idRepuesto = Repuesto;
    }

    public int getIdRepuesto()
    {
        return this.idRepuesto;
    }

    public void setNombre(String Nombre)
    {
        this.nombre = Nombre;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setTipo(String Tipo)
    {
        this.tipo = Tipo;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    public void setMonto(double Monto)
    {
        this.monto = Monto;
    }

    public double getMonto()
    {
        return this.monto;
    }

    public void setImagen(String Imagen)
    {
        this.imagen = Imagen;
    }

    public String getImagen()
    {
        return this.imagen;
    }

    public void setDetalle(String Detalle)
    {
        this.detalle = Detalle;
    }

    public String getDetalle()
    {
        return this.detalle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repuesto that = (Repuesto) o;
        return idRepuesto == that.idRepuesto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepuesto);
    }
}
