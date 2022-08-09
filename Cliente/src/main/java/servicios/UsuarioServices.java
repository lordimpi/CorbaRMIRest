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

    public Usuario registrarUsuario(Usuario objUsuarioRegistar) {
        Usuario objUsuario = null;
        WebTarget target = this.objClientePeticiones.target(this.endPoint);
        Entity<Usuario> data = Entity.entity(objUsuarioRegistar, MediaType.APPLICATION_JSON_TYPE);
        Builder objPeticion = target.request(MediaType.APPLICATION_JSON_TYPE);
        objUsuario = objPeticion.post(data, Usuario.class);
        return objUsuario;
    }

    public Usuario Login(Usuario objUsuarioLogin) {
        Usuario objUsuario = null;

        WebTarget target = this.objClientePeticiones.target(
                this.endPoint + "/" + objUsuarioLogin.getEmail() + "/" + objUsuarioLogin.getContraseña());

        Builder objPeticion = target.request(MediaType.APPLICATION_JSON_TYPE);

        objUsuario = objPeticion.get(Usuario.class);

        return objUsuario;
    }

    /*
    public Cliente actualizarCliente(Cliente objClienteActualizar, Integer id) {
        Cliente objCliente = null;

        WebTarget target = this.objClientePeticiones.target(this.endPoint + "/" + id);

        Entity<Cliente> data = Entity.entity(objClienteActualizar, MediaType.APPLICATION_JSON_TYPE);

        Builder objPeticion = target.request(MediaType.APPLICATION_JSON_TYPE);

        objCliente = objPeticion.put(data, Cliente.class);

        return objCliente;
    }

    public Boolean eliminarCliente(Integer id) {
        Boolean bandera = false;

        WebTarget target = this.objClientePeticiones.target(this.endPoint + "/" + id);

        Builder objPeticion = target.request(MediaType.APPLICATION_JSON_TYPE);

        bandera = objPeticion.delete(Boolean.class);

        return bandera;
    }
    
    public Cliente consultarCliente(Integer id) {
        Cliente objCliente = null;

        WebTarget target = this.objClientePeticiones.target(this.endPoint + "/" + id);

        Builder objPeticion = target.request(MediaType.APPLICATION_JSON_TYPE);

        objCliente = objPeticion.get(Cliente.class);

        return objCliente;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> listaClientes = null;

        WebTarget target = this.objClientePeticiones.target(this.endPoint);

        Builder objPeticion = target.request(MediaType.APPLICATION_JSON);

        listaClientes = objPeticion.get(new GenericType<List<Cliente>>() {
        });

        return listaClientes;
    }
     */
}
