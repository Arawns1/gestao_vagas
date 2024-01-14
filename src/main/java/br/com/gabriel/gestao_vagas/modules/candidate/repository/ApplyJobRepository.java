package br.com.gabriel.gestao_vagas.modules.candidate.repository;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob, UUID> {
}
