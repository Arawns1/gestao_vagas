package br.com.gabriel.gestao_vagas.modules.candidate.repository;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {
}
