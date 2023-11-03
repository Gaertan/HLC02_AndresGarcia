package com.example.hlc02_andresgarcia.modelo.negocio.dominio;

import android.content.Context;

import java.io.Serializable;

public enum Servicios implements Serializable {

    WIFI("Wifi"),
    TELEVISION("Televisión"),
    AIRE_ACONDICIONADO("Aire Acondicionado"),
    TERRAZA("Terraza"),
    MUSICA_EN_VIVO("Música en Vivo"),
    COMIDA_VEGETARIANA("Comida Vegetariana");

    private final String descripcion;

    Servicios(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String obtenerDescripcionDeServicio(Context context, Servicios servicio) {
        int resourceId = context.getResources().getIdentifier("servicio_" + servicio.name().toLowerCase().replace(" ", "_"), "string", context.getPackageName());
        return context.getString(resourceId).replace("_", " ");
    }
}