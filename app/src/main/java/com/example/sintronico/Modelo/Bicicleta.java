package com.example.sintronico.Modelo;

import java.io.Serializable;
import java.util.Objects;

public class Bicicleta implements Serializable {

    private int idBicicleta;

    private int idPropietario;
    private Propietario dueño;
    private String marca;
    private String color;
    private String numeroSerie;
    private String tipo;
    private String imagen;


    public int getId() {
        return idBicicleta;
    }

    public void setId(int id) {
        this.idBicicleta = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String Marca) {
        this.marca = Marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String Color) {
        this.color = Color;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String NumeroSerie) {
        this.numeroSerie = NumeroSerie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String Tipo) {
        this.tipo = Tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String Imagen) {
        this.imagen = Imagen;
    }

    public Propietario getdueño() {
        return dueño;
    }

    public void setdueño(Propietario dueño) {
        this.dueño = dueño;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicicleta that = (Bicicleta) o;
        return idBicicleta == that.idBicicleta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBicicleta);
    }

}
