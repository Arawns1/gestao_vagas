package br.com.gabriel.gestao_vagas.modules.candidate.services;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import br.com.gabriel.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO findProfile(UUID idCandidate){
        var candidateFound = candidateRepository
                .findById(idCandidate)
                .orElseThrow(() -> new UsernameNotFoundException("Id not found"));
        return new ProfileCandidateResponseDTO(candidateFound);
    }
}
