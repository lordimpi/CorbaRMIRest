package servidor.controladores;

import interfaces.ControladorGestorCancionInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import servicios.UsuarioServices;
import servidor.DTO.CancionDTOO;
import servidor.Repositorios.CancionRepositoryInt;

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
    public boolean registrarCancion(CancionDTOO objCancion, String token) throws RemoteException {
        boolean bandera = false;
        UsuarioServices objUsuarioService = new UsuarioServices();
        if (!objUsuarioService.ValidarToken(token)) {
            System.out.println("El token no es valido");
            return false;
        }
        System.out.println("El token es valido");
        
        if (this.objCancionesRepository.registrarCancion(objCancion)) {
            bandera = true;
            ControladorGestionCancionRespaldo objRemotoRespaldo = new ControladorGestionCancionRespaldo();
            objRemotoRespaldo.guardarCopia(objCancion);
            this.objReferenciaRemota.notificarAdministradores(objCancion, objCancionesRepository.listarCanciones().size());
        }
        return bandera;
    }

    @Override
    public ArrayList<CancionDTOO> listarCanciones() throws RemoteException {
        return this.objCancionesRepository.listarCanciones();
    }
}
