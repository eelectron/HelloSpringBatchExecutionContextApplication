package com.example.hellospringbatchexecutioncontext.config;

import com.example.hellospringbatchexecutioncontext.CustomerProcessor;
import com.example.hellospringbatchexecutioncontext.CustomerReader;
import com.example.hellospringbatchexecutioncontext.CustomerWriter;
import com.example.hellospringbatchexecutioncontext.repositories.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class BatchConfig {
    @Inject
    private JobBuilderFactory jobBuilderFactory;

    @Inject
    private StepBuilderFactory stepBuilderFactory;

    /**
     * Fetch customer data from external service and store in database .
     */
    @Bean(name = "saveCustomerJob")
    public Job saveCustomerJob(){
        return this.jobBuilderFactory.get("saveCustomerJob")
                .start(saveCustomerStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step saveCustomerStep(){
        Step step = this.stepBuilderFactory.get("saveCustomerStep")
                .<Customer, Customer>chunk(4)
                .reader(getCustomerReader())
                .processor(getCustomerProcessor())
                .writer(getCustomerWriter())
                .build();
        return step;
    }

    @Bean
    public CustomerWriter getCustomerWriter() {
        CustomerWriter customerWriter = new CustomerWriter();
        return customerWriter;
    }

    @Bean
    public CustomerProcessor getCustomerProcessor() {
        CustomerProcessor customerProcessor = new CustomerProcessor();
        return customerProcessor;
    }

    @Bean
    public CustomerReader getCustomerReader(){
        CustomerReader customerReader = new CustomerReader();
        customerReader.setName("customerReader");
        return customerReader;
    }


}
