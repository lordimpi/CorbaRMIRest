
package servidor.controladores;

import java.rmi.RemoteException;
import servidor.Repositorios.CancionRepositoryInt;
import sop_corba.ControladorCancionIntPOA;
import sop_corba.ControladorCancionIntPackage.CancionDTO;


public class ControladorGestorCancionesImpl extends ControladorCancionIntPOA{
    
    private final CancionRepositoryInt objCancionesRepository;
    
    public ControladorGestorCancionesImpl(CancionRepositoryInt objCancionesRepository) throws RemoteException
    {        
        this.objCancionesRepository=objCancionesRepository;        
    }

    @Override
    public boolean registrarCancion(CancionDTO objCancion) {
        boolean bandera=false;
        //determinar si la canción existe
        //si existe armar una respuesta con un codigo y un mensaje
        // si no existe
        // registro la camción
        //armo la respuesta con un codigo, un mensaje y con la canción registrada
        if(this.objCancionesRepository.registrarCancion(objCancion))
        {
             bandera=true;
        }
        return bandera;
    }

    @Override
    public CancionDTO[] listarCanciones() {
        CancionDTO[] vector=new CancionDTO[objCancionesRepository.listarCanciones().size()];
        this.objCancionesRepository.listarCanciones().toArray(vector);
        return vector;
    }

   
    
}
