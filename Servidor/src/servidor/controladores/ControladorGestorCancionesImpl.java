package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import servidor.DTO.CancionDTO;
import servidor.Repositorios.CancionRepositoryInt;
import servidor.utilidades.UtilidadesRegistroC;

public class ControladorGestorCancionesImpl extends UnicastRemoteObject implements ControladorGestorCancionInt {

    private final CancionRepositoryInt objCancionesRepository;
    private final ControladorGestionAdministradoresImpl objReferenciaRemota;

    public ControladorGestorCancionesImpl(CancionRepositoryInt objCancionesRepository,
            ControladorGestionAdministradoresImpl objReferenciaRemota) throws RemoteException {
        super(); //se asigna un puerto de escucha al OR
        this.objCancionesRepository = objCancionesRepository;
        this.objReferenciaRemota = objReferenciaRemota;
    }

    @Override
    public boolean registrarCancion(CancionDTO objCancion) throws RemoteException {
        boolean bandera = false;
        if (this.objCancionesRepository.registrarCancion(objCancion)) {
            bandera = true;
            this.objReferenciaRemota.notificarAdministradores(objCancion, objCancionesRepository.listarCanciones().size());
        }
        return bandera;
    }

    @Override
    public ArrayList<CancionDTO> listarCanciones() throws RemoteException {
        return this.objCancionesRepository.listarCanciones();
    }
}
