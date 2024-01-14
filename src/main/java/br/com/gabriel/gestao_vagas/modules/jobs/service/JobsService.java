package br.com.gabriel.gestao_vagas.modules.jobs.service;

import br.com.gabriel.gestao_vagas.modules.company.repository.CompanyRepository;
import br.com.gabriel.gestao_vagas.modules.exceptions.CompanyNotFoundException;
import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import br.com.gabriel.gestao_vagas.modules.jobs.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsService {
    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Jobs save(Jobs jobs){
        companyRepository.findById(jobs.getCompanyId())
                         .orElseThrow(CompanyNotFoundException::new);

        return jobsRepository.save(jobs);
    }
}
