package com.example.prueba_clase;

import java.util.Date;

public class ListElement {
    public String ciudad;
    public String pais;
    public String anio;

    public String info;

    public ListElement(String ciudad, String pais, String anio) {
        this.ciudad = ciudad;
        this.pais = pais;
        this.anio=anio;
    }
    public String getAnio() {
        return anio;
    }
    public void setAnio(String fecha){this.anio=fecha;}
    public String getCiudad() {
        return ciudad;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
