/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import server.Log;

public class Aula implements Serializable{
    
    private String nombre;
    private String codigo;
    private Profesor profesor;
    private ArrayList<Alumno> lista = new ArrayList();

    public Aula(String nombre, String codigo, Profesor profesor)
    {
        this.nombre = nombre;
        this.codigo = codigo;
        this.profesor = profesor;
    }
    
    public synchronized String agregarAlumno(Alumno alumno)
    {
        String resultado = "NO SE HA REALIZADO NINGUNA ACCION";
        
        if(lista.contains(alumno))
        {
            resultado = "EL ALUMNO YA ESTA REGISTRADO EN EL AULA";
            
        }else{
            
            this.lista.add(alumno);
            resultado = "ALUMNO REGISTRADO";
        }
        
        return resultado;
    }
    
    public synchronized int consultarTurno(Alumno alumno)
    {
        int posicion = -1;
        
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getNombre().equals(alumno.getNombre()))
            {
                posicion = i;
            }
        }   
        
        return posicion;
    }
    
    public synchronized Alumno siguienteTurno()
    {
        Alumno alumno;
        
        if(lista.isEmpty() == false)
        {
            alumno = lista.get(0);
            lista.remove(0);
            
        }else{
            
            alumno = new Alumno("LISTA VACIA");
            
        }
        
        return alumno;
        
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ArrayList<Alumno> getLista() {
        return lista;
    }
    
    
}
