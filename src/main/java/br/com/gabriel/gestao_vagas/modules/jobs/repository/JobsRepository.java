package br.com.gabriel.gestao_vagas.modules.jobs.repository;

import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface JobsRepository extends JpaRepository<Jobs, UUID> {
    List<Jobs> findByDescriptionContainingIgnoreCase(String filter);
}
