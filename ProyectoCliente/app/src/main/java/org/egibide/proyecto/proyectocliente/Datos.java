package org.egibide.proyecto.proyectocliente;

import java.io.Serializable;

public class Datos implements Serializable {
    private String ip;
    private int puerto;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

}
