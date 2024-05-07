//package com.coconsult.pidevspring.Services.HR.Config;
//
//import com.coconsult.pidevspring.DAO.Entities.QuizQuestion;
//import com.coconsult.pidevspring.DAO.Repository.HR.QuizQuestionRepository;
//import lombok.AllArgsConstructor;
////import org.springframework.batch.core.Job;
////import org.springframework.batch.core.Step;
////import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
////import org.springframework.batch.item.data.RepositoryItemWriter;
////import org.springframework.batch.item.file.FlatFileItemReader;
////import org.springframework.batch.item.file.LineMapper;
////import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
////import org.springframework.batch.item.file.mapping.DefaultLineMapper;
////import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
////import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.item.data.RepositoryItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.LineMapper;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.TaskExecutor;
////import org.springframework.batch.core.configuration.annotation.jobBuilders;
////import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//
//
//@Configuration
//@EnableBatchProcessing
//@AllArgsConstructor
//public class SpringBatchConfig {
////    @Autowired
////    private JobBuilderFactory jobBuilderFactory;
////
////    @Autowired
////    private StepBuilderFactory stepBuilderFactory;
//    private QuizQuestionRepository quizRepository;
//
//    @Bean
//    public FlatFileItemReader<QuizQuestion> customerReader() {
//        FlatFileItemReader<QuizQuestion> itemReader = new FlatFileItemReader<>();
//        itemReader.setResource(new FileSystemResource("src/main/resources/quiz.csv"));
//        itemReader.setName("csv-reader");
//        itemReader.setLinesToSkip(1);
//        itemReader.setLineMapper(lineMapper());
//        return itemReader;
//    }
//
//    private LineMapper<QuizQuestion> lineMapper() {
//
//        DefaultLineMapper<QuizQuestion> lineMapper = new DefaultLineMapper<>();
//
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        lineTokenizer.setDelimiter(",");
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames("questionId", "question", "options");
//
//        BeanWrapperFieldSetMapper<QuizQuestion> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(QuizQuestion.class);
//
//        lineMapper.setLineTokenizer(lineTokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//
//        return lineMapper;
//    }
//
//    @Bean
//    public QuizQuestionProcessor QuizQuestionProcessor() {
//        return new QuizQuestionProcessor();
//    }
//
//    @Bean
//    public RepositoryItemWriter<QuizQuestion> customerWriter() {
//
//        RepositoryItemWriter<QuizQuestion> writer = new RepositoryItemWriter<>();
//        writer.setRepository(quizRepository);
//        writer.setMethodName("save");
//
//        return writer;
//    }
//
//
////    @Bean
////    public Step step() {
////        return stepBuilderFactory.get("step-1").<QuizQuestion, QuizQuestion>chunk(10)
////                .reader(customerReader())
////                .processor(QuizQuestionProcessor())
////                .writer(customerWriter())
////                .taskExecutor(taskExecutor())
////                .build();
////    }
////
////    @Bean
////    public Job job() {
////        return jobBuilderFactory.get("quiz-import")
////                .flow(step())
////                .end()
////                .build();
////    }
//
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
//        taskExecutor.setConcurrencyLimit(10);
//        return taskExecutor;
//    }
//}
