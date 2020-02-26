package br.com.springboot.rest.dataprovider;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.springboot.rest.dataprovider.repository.EstudanteRepository;
import br.com.springboot.rest.dataprovider.repository.entity.Estudante;
import br.com.springboot.rest.usecase.gateway.EstudanteGetAllGateway;

@Component
public class EstudanteGetDataProvider implements EstudanteGetAllGateway {

	private EstudanteRepository studentRepository;

	public EstudanteGetDataProvider(EstudanteRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Estudante> findAll() {
		List<Estudante> retorno = studentRepository.findAll();

		return retorno;
	}

}
