package servicios;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;

import models.Usuario;

public class UsuarioServices {

    private String endPoint;
    private Client objClientePeticiones;

    public UsuarioServices() {
        this.endPoint = "http://localhost:4000/api/usuarios";
        this.objClientePeticiones = ClientBuilder.newClient().register(new JacksonFeature());
    }

    public boolean ValidarToken(String token) {
        boolean isTokenValido;

        WebTarget target = this.objClientePeticiones.target(this.endPoint + "/" + token);
        Builder objPeticion = target.request(MediaType.APPLICATION_JSON_TYPE);

        isTokenValido = objPeticion.get(boolean.class);

        return isTokenValido;
    }
}
