package com.example.hellospringbatchexecutioncontext;

import com.example.hellospringbatchexecutioncontext.repositories.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamSupport;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessor extends ItemStreamSupport implements  ItemProcessor<Customer, Customer> {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerProcessor.class);

    @Override
    public Customer process(Customer item) throws Exception {
        LOG.info("processing a customer data .");
        Customer processedCustomer = new Customer();
        processedCustomer.setId(item.getId());
        processedCustomer.setName(item.getName().toUpperCase());
        processedCustomer.setAddress(item.getAddress().toUpperCase());
        processedCustomer.setCreatedAt(item.getCreatedAt());
        return processedCustomer;
    }
}
