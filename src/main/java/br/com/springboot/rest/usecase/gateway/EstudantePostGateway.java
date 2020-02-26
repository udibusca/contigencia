package br.com.springboot.rest.usecase.gateway;

import br.com.springboot.rest.dataprovider.repository.entity.Estudante;

public interface EstudantePostGateway {

	Estudante salva(Estudante estudante);

}
