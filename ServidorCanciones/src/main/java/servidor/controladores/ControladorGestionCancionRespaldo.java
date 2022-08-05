package servidor.controladores;

import servidor.DTO.CancionDTOO;
import sop_corba.ControladorCancionInt;
import sop_corba.ControladorCancionIntPackage.CancionDTO;
import utilidades.UtilidadesNSC;

/**
 *
 * @author MXGALLEGO
 */
public class ControladorGestionCancionRespaldo {

    private ControladorCancionInt objRemoto;

    public ControladorGestionCancionRespaldo() {
        ObtenerOR();
    }

    public void ObtenerOR() {
        String[] vectorDatosLocalizarNS = new String[4];
        vectorDatosLocalizarNS[0] = "-ORBInitialHost";
        //System.out.println("Ingrese la dirección IP donde escucha el n_s");
        vectorDatosLocalizarNS[1] = "localhost";
        vectorDatosLocalizarNS[2] = "-ORBInitialPort";
        //System.out.println("Ingrese el puerto donde escucha el n_s");
        vectorDatosLocalizarNS[3] = "2021";

        objRemoto = UtilidadesNSC.obtenerObjRemoto(vectorDatosLocalizarNS, "ControladorNotificaciones");

    }

    public void guardarCopia(CancionDTOO objCancion) {
        CancionDTO objCancionCorba = new CancionDTO();
        objCancionCorba.audio = objCancion.getArrayBytes();
        objCancionCorba.artista = objCancion.getArtista();
        objCancionCorba.id = objCancion.getId();
        objCancionCorba.titulo = objCancion.getTitulo();
        objCancionCorba.tamKB = objCancion.getTamKB();
        
        objRemoto.registrarCancion(objCancionCorba);
    }

}
