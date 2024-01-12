package br.com.gabriel.gestao_vagas.modules.candidate.controllers;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import br.com.gabriel.gestao_vagas.modules.candidate.dto.CandidateSaveDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.candidate.services.CandidateService;
import br.com.gabriel.gestao_vagas.modules.exceptions.UserFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

@Autowired
private CandidateService candidateService;
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
}