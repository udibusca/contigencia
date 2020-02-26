package br.com.springboot.rest.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.springboot.rest.configuration.infrastructure.JmsSender;
import br.com.springboot.rest.dataprovider.repository.entity.Estudante;
import br.com.springboot.rest.entrypoint.mapper.EstudanteMapper;
import br.com.springboot.rest.entrypoint.model.EstudanteModel;
import br.com.springboot.rest.usecase.gateway.EstudanteGetAllGateway;
import br.com.springboot.rest.usecase.gateway.EstudantePostGateway;

@Component
@Transactional
public class EstudanteUsecase {

	@Autowired
	private EstudanteGetAllGateway gatewayProvider;

	@Autowired
	private EstudantePostGateway gatWayPost;

	@Autowired
	JmsSender jmsSender;

	public void execute() {

		List<EstudanteModel> listEstudante = EstudanteMapper.entityListFromCore(gatewayProvider.findAll());

		jmsSender.send("DEV.QUEUE.1", listEstudante);

		for (EstudanteModel estudanteModel : listEstudante) {

			Estudante retornoCore = EstudanteMapper.toCoreFromModel(estudanteModel);

			System.out.println("Antes do save > " + retornoCore);

			retornoCore.setNome(retornoCore.getNome() + " ALTERADO");
			Estudante mensagemFila = gatWayPost.salva(retornoCore);

			EstudanteModel estudanteModelFila = EstudanteMapper.toModelFromCore(mensagemFila);

			System.out.println("Depois do save e antes de chamar a fila > " + retornoCore);

			jmsSender.send("DEV.QUEUE.99", estudanteModelFila);

		}

	}
}