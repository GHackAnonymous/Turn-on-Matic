/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.ArrayList;

public class Aulas {
    
    private ArrayList<Aula> lista = new ArrayList();
    
    public synchronized void crearAula(Aula aula)
    {
        this.lista.add(aula);
    }
    
    public synchronized String borrarAula(String aula)
    {
        String resultado = "AULA NO ENCONTRADA";
        
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getCodigo().equals(aula))
            {
                resultado = "AULA BORRADA "+lista.get(i).getCodigo()+" > "+lista.get(i).getNombre();
                lista.remove(i);
            }
        }
        
        return resultado;
    }
    
    public synchronized Aula buscarAula(String codigo)
    {
        Aula resultado = null;
        
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getCodigo().equals(codigo));
            {
                resultado = lista.get(i);
            }
        }
        
        return resultado;
    }
    
    
}
