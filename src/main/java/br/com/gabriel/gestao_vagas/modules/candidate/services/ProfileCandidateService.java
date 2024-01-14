package br.com.gabriel.gestao_vagas.modules.candidate.services;

import br.com.gabriel.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO findProfile(UUID idCandidate){
        var candidateFound = candidateRepository
                .findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);
        return new ProfileCandidateResponseDTO(candidateFound);
    }
}
