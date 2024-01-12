package br.com.gabriel.gestao_vagas.modules.candidate.repository;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    Optional<Candidate> findByUsernameOrEmail(String username, String email);
}
