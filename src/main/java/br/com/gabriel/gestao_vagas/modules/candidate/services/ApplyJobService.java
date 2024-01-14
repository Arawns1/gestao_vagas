package br.com.gabriel.gestao_vagas.modules.candidate.services;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.ApplyJob;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.exceptions.JobNotFoundException;
import br.com.gabriel.gestao_vagas.modules.exceptions.UserNotFoundException;
import br.com.gabriel.gestao_vagas.modules.jobs.repository.JobsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobService {
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    ApplyJobRepository applyJobRepository;

    public ApplyJob apply(UUID idCandidate, UUID idJob){
       candidateRepository.findById(idCandidate)
                          .orElseThrow(UserNotFoundException::new);

       jobsRepository.findById(idJob)
                     .orElseThrow(JobNotFoundException::new);

       var applyjob = ApplyJob.builder().candidateId(idCandidate).jobId(idJob).build();
       return applyJobRepository.save(applyjob);
    }
}
