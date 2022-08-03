package servidor.servicios;

import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.Repositorios.CancionRepository;
import servidor.controladores.ControladorGestionAdministradoresImpl;
import servidor.controladores.ControladorGestorCancionesImpl;

public class ServidorDeObjetos {

    public static void main(String args[]) throws RemoteException {

        int numPuertoRMIRegistryServidorCanciones;
        String direccionIpRMIRegistryServidorCanciones;

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry canciones");
        direccionIpRMIRegistryServidorCanciones = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry canciones");
        numPuertoRMIRegistryServidorCanciones = UtilidadesConsola.leerEntero();


        CancionRepository objRepository = new CancionRepository();
        ControladorGestionAdministradoresImpl objRemotoGestionAdministradores = new ControladorGestionAdministradoresImpl();
        ControladorGestorCancionesImpl objRemotoGestionCanciones = new ControladorGestorCancionesImpl(
                objRepository, objRemotoGestionAdministradores);
        
        
        try {
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistryServidorCanciones);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionCanciones, direccionIpRMIRegistryServidorCanciones,
                    numPuertoRMIRegistryServidorCanciones, "objServicioGestionCanciones");
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoGestionAdministradores, direccionIpRMIRegistryServidorCanciones,
                    numPuertoRMIRegistryServidorCanciones, "objServicioGestionAdministradores");
        } catch (Exception e) {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        }

    }
}
