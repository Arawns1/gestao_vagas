package br.com.gabriel.gestao_vagas.modules.candidate.services;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.ApplyJob;
import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.exceptions.JobNotFoundException;
import br.com.gabriel.gestao_vagas.modules.exceptions.UserNotFoundException;
import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import br.com.gabriel.gestao_vagas.modules.jobs.repository.JobsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ApplyJobServiceTest {
    @InjectMocks
    private ApplyJobService applyJobService;
    @Mock
    CandidateRepository candidateRepository;
    @Mock
    JobsRepository jobsRepository;

    @Mock
    ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound(){
       try {
           applyJobService.apply(null, null);
       }catch (Exception e){
           assertThat(e).isInstanceOf(UserNotFoundException.class);
       }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void shouldNotBeAbleToApplyJobWithJobNotFound(){
        var idCandidate = UUID.randomUUID();

        var candidate = new Candidate();
        candidate.setId(idCandidate);
        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobService.apply(idCandidate, null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should Be Able To Create A New ApplyJob")
    public void ShouldBeAbleToCreateANewApplyJob(){
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJob.builder().candidateId(idCandidate).jobId(idJob).build();
        var applyJobCreated = ApplyJob.builder()
                                      .id(UUID.randomUUID())
                                      .build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new Candidate()));
        when(jobsRepository.findById(idJob)).thenReturn(Optional.of(new Jobs()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobService.apply(idCandidate,idJob);

        assertThat(result).hasFieldOrProperty("id");
        Assertions.assertNotNull(result.getId());
    }
}
