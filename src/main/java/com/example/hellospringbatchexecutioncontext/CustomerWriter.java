package com.example.hellospringbatchexecutioncontext;

import com.example.hellospringbatchexecutioncontext.repositories.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemStreamSupport;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerWriter extends ItemStreamSupport implements ItemWriter<Customer> {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerWriter.class);

    @Override
    public void write(List<? extends Customer> items) throws Exception {
        LOG.info("writing  customer data : {}", items);
    }
}
