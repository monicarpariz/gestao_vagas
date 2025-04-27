package br.com.monicapariz.gestao_vagas.modules.company.useCases;

import br.com.monicapariz.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.monicapariz.gestao_vagas.modules.company.entities.JobEntity;
import br.com.monicapariz.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.monicapariz.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {

        companyRepository.findById(jobEntity.getCompanyId())
                .orElseThrow(() -> {
                    throw new CompanyNotFoundException();
                });

        return this.jobRepository.save(jobEntity);
    }

}
