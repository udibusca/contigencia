package br.com.springboot.rest.entrypoint;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.springboot.rest.dataprovider.repository.EstudanteRepository;
import br.com.springboot.rest.dataprovider.repository.entity.Estudante;

@RestController
public class EstudantePostEntrypoint {

	private EstudanteRepository studentRepository;

	public EstudantePostEntrypoint(EstudanteRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@PostMapping("/estudantes")
	public ResponseEntity<Estudante> createStudent(@RequestBody Estudante student) {
		Estudante savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

}
