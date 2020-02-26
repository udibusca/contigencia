package br.com.springboot.rest.usecase.gateway;

import java.util.List;

import br.com.springboot.rest.dataprovider.repository.entity.Estudante;

public interface EstudanteGetAllGateway {

	List<Estudante> findAll();
	
}
