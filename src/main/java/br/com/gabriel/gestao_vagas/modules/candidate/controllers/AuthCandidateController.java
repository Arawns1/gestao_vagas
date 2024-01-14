package br.com.gabriel.gestao_vagas.modules.candidate.controllers;

import br.com.gabriel.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.services.CandidateAuth;
import br.com.gabriel.gestao_vagas.modules.candidate.services.CandidateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {
    @Autowired
    private CandidateAuth candidateAuth;
    @PostMapping("/auth")
    @Tag(name="Candidato", description = "Informações do candidato")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO){
        try{
            var token = candidateAuth.auth(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
