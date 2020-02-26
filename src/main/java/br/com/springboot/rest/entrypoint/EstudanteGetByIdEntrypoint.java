package br.com.springboot.rest.entrypoint;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.rest.configuration.exception.StudentNotFoundException;
import br.com.springboot.rest.dataprovider.repository.EstudanteRepository;
import br.com.springboot.rest.dataprovider.repository.entity.Estudante;

@RestController
public class EstudanteGetByIdEntrypoint {

	private EstudanteRepository studentRepository;

	public EstudanteGetByIdEntrypoint(EstudanteRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@RequestMapping(value = "/estudantes/{id}", method = RequestMethod.GET, produces = "application/json")
	public Estudante retrieveStudent(@PathVariable long id) {
		Optional<Estudante> student = studentRepository.findById(id);

		if (!student.isPresent())
			throw new StudentNotFoundException("id-" + id);

		return student.get();
	}

}
