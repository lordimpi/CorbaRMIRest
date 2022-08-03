package cliente.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.DTO.NotificacionDTO;

public class AdminitradorCallbackImpl extends UnicastRemoteObject implements AdministradorCallbackInt {

    public AdminitradorCallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void notificarNuevaCancion(NotificacionDTO objNotificacion) throws RemoteException {
        System.out.println(objNotificacion.toString());
    }

}
