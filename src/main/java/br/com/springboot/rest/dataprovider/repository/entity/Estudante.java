package br.com.springboot.rest.dataprovider.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estudante {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String numerosPass;

}
