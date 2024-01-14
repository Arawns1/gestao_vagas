package br.com.gabriel.gestao_vagas.modules.candidate.services;

import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import br.com.gabriel.gestao_vagas.modules.jobs.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListAllJobsByFilterService {
    @Autowired
    private JobsRepository jobsRepository;
    public List<Jobs> search(String filter){
        return jobsRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
