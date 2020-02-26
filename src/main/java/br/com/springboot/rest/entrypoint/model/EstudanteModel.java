package br.com.springboot.rest.entrypoint.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudanteModel {

	private Long id;
	private String nome;
	private String numerosPass;

}
