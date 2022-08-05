
package servidor.utilidades;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import servidor.controladores.ControladorGestorCancionesImpl;
import sop_corba.ControladorCancionInt;
import sop_corba.ControladorCancionIntHelper;
public class UtilidadesRegistroS
{
	public static void registrarObjetoRemoto(String[] vectorDatosNS, ControladorGestorCancionesImpl objRemoto,String idObjetoRemoto) throws ServantNotActive, WrongPolicy, org.omg.CORBA.ORBPackage.InvalidName, AdapterInactive, InvalidName, NotFound, CannotProceed
        {            

            // crea e inicia el ORB
            ORB orb = ORB.init(vectorDatosNS, null);      
            POA rootpoa =  POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            //*** se genera la referencia del servant
            org.omg.CORBA.Object obj = rootpoa.servant_to_reference(objRemoto);
            ControladorCancionInt href = ControladorCancionIntHelper.narrow(obj);

            // se obtiene un link al name service
            org.omg.CORBA.Object objref =orb.resolve_initial_references("NameService");
            NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

            // *** se realiza el binding de la referencia del servant en el N_S ***            
            NameComponent path[] = ncref.to_name( idObjetoRemoto );
            ncref.rebind(path, href);

            System.out.println("El Servidor esta listo y esperando ...");

            // esperan por las invocaciones desde los clientes
            orb.run();
        }
	
}
