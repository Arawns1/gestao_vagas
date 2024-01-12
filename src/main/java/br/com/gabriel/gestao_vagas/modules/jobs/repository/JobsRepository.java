package br.com.gabriel.gestao_vagas.modules.jobs.repository;

import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobsRepository extends JpaRepository<Jobs, UUID> {
}
