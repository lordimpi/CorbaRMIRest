package cliente.servicios;

import cliente.controladores.AdminitradorCallbackImpl;
import java.rmi.RemoteException;
import servidor.controladores.ControladorGestionAdministradoresInt;
import utilidades.UtilidadesConsola;
import utilidades.UtilidadesRegistroC;

public class ClienteDeObjetos {

    private static ControladorGestionAdministradoresInt objRemoto;

    public static void main(String[] args) throws RemoteException {

        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();

        objRemoto = (ControladorGestionAdministradoresInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,
                numPuertoRMIRegistry, "objServicioGestionAdministradores");
        AdminitradorCallbackImpl objRemotoAdmin = new AdminitradorCallbackImpl();
        objRemoto.registrarReferenciaRemotaAdministrador(objRemotoAdmin);

        System.out.println("Esperando notificaciones...");
    }

}
