package interfaces;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface ControladorGestionAdministradoresInt extends Remote {

    public void registrarReferenciaRemotaAdministrador(AdministradorCallbackInt objReferencia) throws RemoteException;
}
