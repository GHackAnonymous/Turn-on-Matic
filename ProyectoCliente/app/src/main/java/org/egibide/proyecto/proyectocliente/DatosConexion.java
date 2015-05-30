package org.egibide.proyecto.proyectocliente;

public class DatosConexion {
    private static String ip;
    private static int puerto;


    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        DatosConexion.ip = ip;
    }

    public static int getPuerto() {
        return puerto;
    }

    public static void setPuerto(int puerto) {
        DatosConexion.puerto = puerto;
    }
}
