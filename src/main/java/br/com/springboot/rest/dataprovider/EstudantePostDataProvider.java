package br.com.springboot.rest.dataprovider;

import org.springframework.stereotype.Component;

import br.com.springboot.rest.dataprovider.repository.EstudanteRepository;
import br.com.springboot.rest.dataprovider.repository.entity.Estudante;
import br.com.springboot.rest.usecase.gateway.EstudantePostGateway;

@Component
public class EstudantePostDataProvider implements EstudantePostGateway {

	private EstudanteRepository studentRepository;

	public EstudantePostDataProvider(EstudanteRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Estudante salva(Estudante estudante) {

		Estudante retorno = studentRepository.save(estudante);

		return retorno;
	}

}
