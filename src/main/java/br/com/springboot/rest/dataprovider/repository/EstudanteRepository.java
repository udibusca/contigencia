package br.com.springboot.rest.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.rest.dataprovider.repository.entity.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

}
