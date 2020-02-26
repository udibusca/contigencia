package br.com.springboot.rest.entrypoint;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.rest.dataprovider.repository.EstudanteRepository;

@RestController
public class EstudanteDeleteByIdEntrypoint {

	private EstudanteRepository studentRepository;

	public EstudanteDeleteByIdEntrypoint(EstudanteRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@DeleteMapping("/estudantes/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentRepository.deleteById(id);
	}

}
