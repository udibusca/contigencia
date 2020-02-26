package br.com.springboot.rest.entrypoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.rest.usecase.EstudanteUsecase;

@RestController
public class EstudanteGetEntrypoint {

	private EstudanteUsecase usecase;

	public EstudanteGetEntrypoint(EstudanteUsecase usecase) {
		this.usecase = usecase;
	}

	@RequestMapping(value = "/estudantes", method = RequestMethod.GET, produces = "application/json")
	public void retornaTodosEstudantes(){
			usecase.execute();	
	}

}
