package br.com.gabriel.gestao_vagas.modules.candidate.controllers;

import br.com.gabriel.gestao_vagas.modules.candidate.dto.CandidateSaveDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public CandidateSaveDTO create(@RequestBody @Valid CandidateSaveDTO candidateSaveDTO) {
        return candidateSaveDTO;
    }
}
