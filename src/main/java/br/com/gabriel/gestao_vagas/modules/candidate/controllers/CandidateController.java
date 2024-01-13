package br.com.gabriel.gestao_vagas.modules.candidate.controllers;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import br.com.gabriel.gestao_vagas.modules.candidate.dto.CandidateSaveDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.candidate.services.CandidateService;
import br.com.gabriel.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import br.com.gabriel.gestao_vagas.modules.exceptions.UserFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

@Autowired
private CandidateService candidateService;

@Autowired
private ProfileCandidateService profileCandidateService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> create(@RequestBody @Valid Candidate candidate,UriComponentsBuilder uriBuilder){
        try{
            Candidate response = candidateService.save(candidate);

            var uri = uriBuilder.path("/candidate/{id}")
                                .buildAndExpand(response.getId())
                                .toUri();

            return ResponseEntity.created(uri).body(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('candidate')")
    public ResponseEntity<Object> findUser(HttpServletRequest request){
        var idCandidate = request.getAttribute("candidate_id");
        try{
            var result = profileCandidateService.findProfile(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(result);
        }
        catch(Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
