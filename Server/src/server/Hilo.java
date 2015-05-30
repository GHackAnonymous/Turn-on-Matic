/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.*;

public class Hilo extends Thread{
    
    private Socket socket;
    private InputStream in;
    private ObjectInputStream is;
    private OutputStream os;
    private ObjectOutputStream out;
    private Log log;
    
    private Profesor profesor;
    private Aulas aulas;
    private Aula aula;
    private Alumno alumno;
    private GeneradorCodigo generadorCodigo = new GeneradorCodigo();
    
    private boolean resultado;
    private int turno;
    private String codigo;
    private String comando;
    private String respuesta;
    private String nombreProfesor;
    private String nombreAlumno;
    
    public Hilo(Socket socket, Aulas aulas, Log log) throws IOException
    {
        this.socket = socket;
        this.aulas = aulas;
        this.log = log;
        in = this.socket.getInputStream();
        is = new ObjectInputStream(in);
        os = this.socket.getOutputStream();
        out = new ObjectOutputStream(os);
        log.mensaje("NUEVO HILO");
    }
    
    @Override
    public void run()
    {
        try {
        
        do{
        
            log.mensaje("ESPERANDO COMANDO");
            this.comando = is.readUTF();
            log.mensaje("COMANDO RECIBIDO");
            
        switch(comando)
        {
            case "crearAula":
                
                log.mensaje("CREAR AULA");
                
                String nombre = is.readUTF();
                codigo = generadorCodigo.Generar();
                nombreProfesor = (String) is.readObject();
                profesor = new Profesor(nombreProfesor);
                
                aula = new Aula(nombre, codigo, profesor);
                aulas.crearAula(aula);
                
                out.writeObject(codigo);
                
                log.mensaje("AULA CREADA "+aula.getCodigo()+" > "+aula.getNombre());
            
            break;
            
            case "borrarAula":
                
                codigo = (String) is.readObject();
            
                respuesta = aulas.borrarAula(codigo);
                log.mensaje("AULA BORRADA "+codigo);
            
                out.writeObject(respuesta);
            
            break;
                
            case "agregarAlumno":
                
                log.mensaje("AGREGAR ALUMNO");
                
                codigo = is.readUTF();
                
                nombreAlumno = (String) is.readObject();
                alumno = new Alumno(nombreAlumno);
                
                aula = aulas.buscarAula(codigo);
                
                if(aula != null)
                {
                    
                respuesta = aula.agregarAlumno(alumno);
                log.mensaje("ALUMNO AGREGADO "+alumno.getNombre());
                }else{
                
                respuesta = "AULA NO ENCONTRADA";
                    
                }
                
                out.writeObject(respuesta);
                
                
                
            break;
            
            case "consultarTurno":
                
                log.mensaje("CONSULTAR TURNO");
                codigo = is.readUTF();
                
                aula = aulas.buscarAula(codigo);
                
                nombreAlumno = (String) is.readObject();
                alumno = new Alumno(nombreAlumno);
                
                turno = aula.consultarTurno(alumno);
                
                out.writeObject(turno);
                
                
            break;
            case "siguienteAlumno":
                
                log.mensaje("SIGUIENTE ALUMNO");
                codigo = (String) is.readObject();
 
                aula = aulas.buscarAula(codigo);
                
                alumno = aula.siguienteTurno();
                nombreAlumno = alumno.getNombre();
                
                out.writeObject(nombreAlumno);
                
            break;      
                
            default:
                log.mensaje("COMANDO NO RECONOCIDO");
            break;
             
        }
        }while(!comando.equals("salir"));
        
        
        } catch(SocketException socketE){
            
            log.setForeground(Color.red);
            log.mensaje("CONEXION PERDIDA");
            log.setForeground(Color.black);
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
