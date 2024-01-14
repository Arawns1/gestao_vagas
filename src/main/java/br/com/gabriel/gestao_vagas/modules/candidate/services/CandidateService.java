package br.com.gabriel.gestao_vagas.modules.candidate.services;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import br.com.gabriel.gestao_vagas.modules.candidate.dto.CandidateSaveDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Candidate save(CandidateSaveDTO candidate){
        this.candidateRepository
                    .findByUsernameOrEmail(candidate.username(), candidate.email())
                    .ifPresent((user) -> {
                        throw new UserFoundException();
                    });
        var password = passwordEncoder.encode(candidate.password());
        Candidate candidateInstance = Candidate.builder()
                 .name(candidate.name())
                 .username(candidate.username())
                 .email(candidate.email())
                 .password(password)
                 .description(candidate.description())
                 .curriculum(candidate.curriculum())
                 .build();
        return this.candidateRepository.save(candidateInstance);
    }
}
