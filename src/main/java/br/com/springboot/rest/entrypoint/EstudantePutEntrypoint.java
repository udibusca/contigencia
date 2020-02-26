package br.com.springboot.rest.entrypoint;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.rest.dataprovider.repository.EstudanteRepository;
import br.com.springboot.rest.dataprovider.repository.entity.Estudante;

@RestController
public class EstudantePutEntrypoint {

	private EstudanteRepository studentRepository;

	public EstudantePutEntrypoint(EstudanteRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@PutMapping("/estudantes/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Estudante student, @PathVariable long id) {

		Optional<Estudante> studentOptional = studentRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);
		
		studentRepository.save(student);

		return ResponseEntity.noContent().build();
	}

}
