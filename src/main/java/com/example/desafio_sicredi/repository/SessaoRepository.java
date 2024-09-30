package com.example.desafio_sicredi.repository;

import com.example.desafio_sicredi.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {
}
