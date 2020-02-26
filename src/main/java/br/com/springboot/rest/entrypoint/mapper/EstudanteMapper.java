package br.com.springboot.rest.entrypoint.mapper;

import static java.util.stream.Collectors.toList;

import java.util.List;

import br.com.springboot.rest.dataprovider.repository.entity.Estudante;
import br.com.springboot.rest.entrypoint.model.EstudanteModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EstudanteMapper {

	public static EstudanteModel toModelFromCore(Estudante entity) {
		if (entity == null)
			return null;

		return EstudanteModel.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.numerosPass(entity.getNumerosPass())
				.build();
	}

	public static List<EstudanteModel> entityListFromCore(List<Estudante> entity) {
		return entity.stream().map(EstudanteMapper::toModelFromCore).collect(toList());
	}
	
	
	public static Estudante toCoreFromModel(EstudanteModel model) {
		if (model == null)
			return null;

		return Estudante.builder()
				.id(model.getId())
				.nome(model.getNome())
				.numerosPass(model.getNumerosPass())
				.build();
	}
	
}
