package com.idat.ec2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.ec2.DTO.BodegaDTORequest;
import com.idat.ec2.DTO.BodegaDTOResponse;
import com.idat.ec2.model.Bodega;
import com.idat.ec2.repository.BodegaRepository;


@Service
public class BodegaServiceImpl implements BodegaService{
	
	@Autowired
	BodegaRepository repo;

	@Override
	public void guardarBodega(BodegaDTORequest bodega) {
		
		Bodega b = new Bodega();
		b.setNombre(bodega.getNombre());
		b.setDireccion(bodega.getDireccion());
		
		repo.save(b);
		
	}

	@Override
	public void actualizarBodega(BodegaDTORequest bodega) {
		
		Bodega b = new Bodega();
		b.setIdBodega(bodega.getIdBodega());
		b.setNombre(bodega.getNombre());
		b.setDireccion(bodega.getDireccion());
		
		repo.saveAndFlush(b);
		
	}

	@Override
	public void eliminarBodega(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public BodegaDTOResponse obtenerBodega(Integer id) {
		Bodega bodega = repo.findById(id).orElse(null);
		BodegaDTOResponse dto = new BodegaDTOResponse();
		
		dto.setIdBodega(bodega.getIdBodega());
		dto.setNombre(bodega.getNombre());
		dto.setDireccion(bodega.getDireccion());
		
		
		return dto;
	}

	@Override
	public List<BodegaDTOResponse> listaBodega() {
		List<BodegaDTOResponse> listar = new ArrayList<>();
		BodegaDTOResponse dto = null;
		List<Bodega> b = repo.findAll();
		
		for(Bodega bodega: b) {
			dto = new BodegaDTOResponse();
			dto.setIdBodega(bodega.getIdBodega());
			dto.setNombre(bodega.getNombre());
			dto.setDireccion(bodega.getDireccion());
			
			listar.add(dto);
		}
		return listar;
	}

}
