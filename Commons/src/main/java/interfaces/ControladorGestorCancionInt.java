package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import servidor.DTO.CancionDTOO;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestorCancionInt extends Remote {

    //Definicion del primer método remoto
    public boolean registrarCancion(CancionDTOO objCancion, String token) throws RemoteException;

    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    public ArrayList<CancionDTOO> listarCanciones() throws RemoteException;
}
