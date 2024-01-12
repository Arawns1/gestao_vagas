package br.com.gabriel.gestao_vagas.modules.jobs.service;

import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import br.com.gabriel.gestao_vagas.modules.jobs.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsService {
    @Autowired
    private JobsRepository jobsRepository;
    public Jobs save(Jobs jobs){
        return jobsRepository.save(jobs);
    }
}
