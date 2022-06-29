package com.idat.ec2.service;

import java.util.List;

import com.idat.ec2.DTO.ClienteDTORequest;
import com.idat.ec2.DTO.ClienteDTOResponse;



public interface ClienteService {
	
	void guardarCliente(ClienteDTORequest cliente);
	void actualizarCliente(ClienteDTORequest cliente);
	void eliminarCliente(Integer id);
	List<ClienteDTOResponse> listarClientes();
	ClienteDTOResponse obtenerCliente(Integer id);

}
