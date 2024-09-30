package com.example.desafio_sicredi.repository;

import com.example.desafio_sicredi.entity.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface VotoRepository extends JpaRepository<VotoEntity, Long> {
}
