

package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import servidor.DTO.CancionDTO;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestorCancionInt extends Remote
{
     public boolean registrarCancion(CancionDTO objCancion)  throws RemoteException;  
     public ArrayList<CancionDTO> listarCanciones() throws RemoteException;
}
