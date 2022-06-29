package com.idat.ec2.service;

import java.util.List;

import com.idat.ec2.DTO.BodegaDTORequest;
import com.idat.ec2.DTO.BodegaDTOResponse;




public interface BodegaService {
	
	void guardarBodega(BodegaDTORequest bodega);
	void actualizarBodega(BodegaDTORequest bodega);
	void eliminarBodega(Integer id);
	BodegaDTOResponse obtenerBodega(Integer id);
	List<BodegaDTOResponse> listaBodega();

}
