package co.edu.unicauca.distribuidos.core.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.models.Usuario;
import co.edu.unicauca.distribuidos.core.services.IUsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping("/usuarios")
	public boolean registrar(@RequestBody Usuario nuevoUsuario) {
		if (!usuarioService.Registrar(nuevoUsuario)) {
			System.out.println("No se pudo registrar el usuario.");
			return false;
		}
		System.out.println("Usuario registrado con éxito.");
		return true;
	}


	@GetMapping("/clientes")
	public List<Usuario> index() {
		return usuarioService.findAll();
	}

	@GetMapping("/clientes/{id}")
	public Usuario show(@PathVariable Integer id) {
		Usuario objCliente = null;
		objCliente = usuarioService.findById(id);
		return objCliente;
	}

	@GetMapping("clientes2/{name}/{age}")
	public String getMessage(@PathVariable("name") String name, @PathVariable("age") String edad) {
		var msg = String.format("%s es %s años viejo", name, edad);
		System.out.println(msg);
		return msg;
	}

	@GetMapping("consultarClientes")
	public String getMessageParametros(@RequestParam String nombres,
			@RequestParam String apellidos,
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
		var msg = String.format("buscando un cliente por nombre: %s, apellidos: %s fecha %s", nombres, apellidos,
				fecha);
		System.out.println(msg);
		return msg;
	}

	@PostMapping("/clientes")
	public Usuario create(@RequestBody Usuario cliente) {
		Usuario objCliente = null;
		objCliente = usuarioService.save(cliente);
		return objCliente;
	}

	@PutMapping("/clientes/{id}")
	public Usuario update(@RequestBody Usuario cliente, @PathVariable Integer id) {
		Usuario objCliente = null;
		Usuario clienteActual = usuarioService.findById(id);
		if (clienteActual != null) {
			objCliente = usuarioService.update(id, cliente);
		}
		return objCliente;
	}

	@DeleteMapping("/clientes/{id}")
	public Boolean delete(@PathVariable Integer id) {
		Boolean bandera = false;
		Usuario clienteActual = usuarioService.findById(id);
		if (clienteActual != null) {
			bandera = usuarioService.delete(id);
		}
		return bandera;

	}

	@GetMapping("/clientes/listarCabeceras")
	public void listarCabeceras(@RequestHeader Map<String, String> headers) {
		System.out.println("cabeceras");
		headers.forEach((key, value) -> {
			System.out.println(String.format("Cabecera '%s' = %s", key, value));
		});
	}

}
