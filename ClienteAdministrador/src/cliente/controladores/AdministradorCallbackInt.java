package cliente.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.DTO.NotificacionDTO;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface AdministradorCallbackInt extends Remote {

    public void notificarNuevaCancion(NotificacionDTO objNotificacion) throws RemoteException;
}
