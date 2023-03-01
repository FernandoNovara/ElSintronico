package com.example.sintronico.Modelo;

import java.util.Objects;

public class User
{
    private String Usuario;
    private String Clave;

    public User(String Usuario, String Clave) {
        this.Usuario = Usuario;
        this.Clave = Clave;
    }

    public void setUsuario(String Usuario)
    {
        this.Usuario = Usuario;
    }

    public String getUsuario()
    {
        return this.Usuario;
    }

    public void setClave(String Clave)
    {
        this.Clave = Clave;
    }

    public String getClave()
    {
        return this.Clave;
    }

}
