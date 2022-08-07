
package servidor.servicios;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import servidor.Repositorios.CancionRepository;
import servidor.controladores.ControladorGestorCancionesImpl;
import servidor.utilidades.UtilidadesConsola;
import servidor.utilidades.UtilidadesRegistroS;
import sop_corba.ControladorCancionInt;
import sop_corba.ControladorCancionIntHelper;

public class ServidorDeObjetos {

    
    public static void main(String[] args) {
       
        try {
            String[] vectorDatosLocalizarNS = new String[4];
        vectorDatosLocalizarNS[0] = "-ORBInitialHost";
        //System.out.println("Ingrese la dirección IP donde escucha el n_s");
        vectorDatosLocalizarNS[1] = "localhost";
        vectorDatosLocalizarNS[2] = "-ORBInitialPort";
        //System.out.println("Ingrese el puerto donde escucha el n_s");
        vectorDatosLocalizarNS[3] = "2021";
            
            CancionRepository objRepository = new CancionRepository();
            ControladorGestorCancionesImpl objRemotoGestionCanciones = new ControladorGestorCancionesImpl(objRepository);
      
            UtilidadesRegistroS.registrarObjetoRemoto(vectorDatosLocalizarNS, objRemotoGestionCanciones,"idObjetoRemoto");
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace(System.out);
        }
        
    }
    
  
    
}
