package com.idat.ec2.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.ec2.DTO.ClienteDTORequest;
import com.idat.ec2.DTO.ClienteDTOResponse;
import com.idat.ec2.model.Cliente;
import com.idat.ec2.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	ClienteRepository repository;

	@Override
	public void guardarCliente(ClienteDTORequest cliente) {
		Cliente c = new Cliente();
		c.setNombre(cliente.getNombre());
		c.setDireccion(cliente.getDireccion());
		c.setDni(cliente.getDni());
		repository.save(c);
		
	}

	@Override
	public void actualizarCliente(ClienteDTORequest cliente) {
		
		Cliente c = new Cliente();
		c.setIdCliente(cliente.getIdCliente());
		c.setNombre(cliente.getNombre());
		c.setDireccion(cliente.getDireccion());
		c.setDni(cliente.getDni());
		
		repository.saveAndFlush(c);
		
	}

	@Override
	public void eliminarCliente(Integer id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<ClienteDTOResponse> listarClientes() {
		
		List<ClienteDTOResponse> listar = new ArrayList<>();
		ClienteDTOResponse dto = null;
		List<Cliente> c = repository.findAll();
		
		for(Cliente cliente : c) {
			dto = new ClienteDTOResponse();
			dto.setIdCliente(cliente.getIdCliente());
			dto.setNombre(cliente.getNombre());
			dto.setDireccion(cliente.getDireccion());
			dto.setDni(cliente.getDni());
			
			listar.add(dto);
		}
		
		return listar;
	}

	@Override
	public ClienteDTOResponse obtenerCliente(Integer id) {
		
		Cliente cliente = repository.findById(id).orElse(null);
		ClienteDTOResponse dto = new ClienteDTOResponse();
		
		dto.setIdCliente(cliente.getIdCliente());
		dto.setNombre(cliente.getNombre());
		dto.setDireccion(cliente.getDireccion());
		dto.setDni(cliente.getDni());
		
		return dto;
	}

}
