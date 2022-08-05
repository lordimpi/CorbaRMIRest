
package servidor.Repositorios;

import java.util.List;
import sop_corba.ControladorCancionIntPackage.CancionDTO;


public interface CancionRepositoryInt
{    
    public boolean registrarCancion(CancionDTO objCancion);   
    public List<CancionDTO> listarCanciones();
}


