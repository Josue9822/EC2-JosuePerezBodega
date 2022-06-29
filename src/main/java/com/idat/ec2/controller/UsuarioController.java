package com.idat.ec2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idat.ec2.model.Usuarios;
import com.idat.ec2.service.UsuarioService;


@Controller
@RequestMapping("/ec2josueperez/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping("/listar")
	public ResponseEntity<List<Usuarios>> listar(){
		return new ResponseEntity<List<Usuarios>>(service.listarUsuarios(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/listar/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuarios> listarPorId(@PathVariable Integer id){
		Usuarios c = service.obtenerUsuarios(id);
		
		if (c!=null) {
			return new ResponseEntity<Usuarios>(service.obtenerUsuarios(id), HttpStatus.OK);
		}
		
		return new ResponseEntity<Usuarios>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/guardar", method = RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody Usuarios usuario){
		service.guardarUsuarios(usuario);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "actualizar", method = RequestMethod.PUT)
	public ResponseEntity<Void> actualizar(@RequestBody Usuarios usuario){
		Usuarios c = service.obtenerUsuarios(usuario.getIdUsuario());
		if (c!=null) {
			service.actualizarUsuarios(usuario);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		Usuarios c = service.obtenerUsuarios(id);
		if (c!= null) {
			service.eliminarUsuario(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
