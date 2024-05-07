package com.coconsult.pidevspring.Services.HR.Config;

import com.coconsult.pidevspring.DAO.Entities.ContractEmployment;
import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;
import com.coconsult.pidevspring.DAO.Repository.HR.ContractEmploymentRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.QuizQuestionRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

public class BatchConfig {
    private ContractEmploymentRepository contractEmploymentRepository;
    public FlatFileItemReader<ContractEmployment> itemReader(){
        FlatFileItemReader<ContractEmployment> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("C:\\xampp\\htdocs\\coconsult\\contract.csv"));
        itemReader.setName("csv-reader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<ContractEmployment> lineMapper() {
        DefaultLineMapper<ContractEmployment> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("contract_id","candidateName","contractType","startDate","endDate","employmentType","jobTitle","salary","terms");
        tokenizer.setStrict(false);

        BeanWrapperFieldSetMapper mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(ContractEmployment.class);

        lineMapper.setFieldSetMapper(mapper);
        lineMapper.setLineTokenizer(tokenizer);
        return lineMapper;
    }

    @Bean
    public ContractEmploymentProcessor processor(){
        return new ContractEmploymentProcessor();
    }

    @Bean
    public RepositoryItemWriter<ContractEmployment> itemWriter(){
        RepositoryItemWriter<ContractEmployment> writer = new RepositoryItemWriter<>();
        writer.setRepository(contractEmploymentRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step(JobRepository repository, PlatformTransactionManager transactionManager){
        return new StepBuilder("csv-step",repository)
                .<ContractEmployment,ContractEmployment>chunk(10,transactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    private TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

    @Bean(name="csvJob")
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("csv-job",jobRepository)
                .flow(step(jobRepository,transactionManager)).end().build();
    }
}
