package br.com.springboot.rest.entrypoint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.rest.configuration.infrastructure.JmsSender;
import br.com.springboot.rest.entrypoint.model.EstudanteModel;


@RestController
@RequestMapping(value = "/jms")
@Validated
public class JmsController {

	@Autowired
	JmsSender jmsSender;
	
	@Autowired
	JmsTemplate jmsTemplate;

	@GetMapping(value = "/get/{message}")
	public ResponseEntity<String> jms(@PathVariable String message, HttpServletRequest request) {

		jmsSender.send("DEV.QUEUE.1", message);

		return ResponseEntity.ok().body("JMS enviado : " + message);
	}
	
	@PostMapping
	public ResponseEntity<String> sendMensagem(@RequestBody String message) {

		jmsSender.send("DEV.QUEUE.1", message);

		return ResponseEntity.ok().body("Mensagem enviada com sucesso : " + message);
	}

	/*
	@GetMapping
	public ResponseEntity<String> getMensagem() {

		String retornoMensagem = (String) jmsTemplate.receiveAndConvert("DEV.QUEUE.1");

		return ResponseEntity.ok().body("JMS recebida : " + retornoMensagem);
	}
	*/
	
	@GetMapping
	public ResponseEntity<String> getEstudantes() {

		//Object retornoMensagemFila1 = jmsTemplate.receiveAndConvert("DEV.QUEUE.1");
		Object retornoMensagemFila = jmsTemplate.receiveAndConvert("DEV.QUEUE.2");

		return ResponseEntity.ok().body("JMS recebidaFila" + retornoMensagemFila);
	}

}
