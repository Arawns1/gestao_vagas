package br.com.gabriel.gestao_vagas.modules.candidate.services;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;


    public Candidate save(Candidate candidate){
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(candidate);
    }
}
