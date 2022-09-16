package com.example.hellospringbatchexecutioncontext.services;

import com.example.hellospringbatchexecutioncontext.CustomerReader;
import com.example.hellospringbatchexecutioncontext.repositories.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerDataService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerDataService.class);

    private List<Customer> customerList = new ArrayList<>();

    public void loadCustomerData(){
        int n = 10;
        for(int i = 0; i < n; i++){
            Customer customer = new Customer();
            customer.setId((long)i);
            customer.setName("user " + i);
            customer.setAddress("address " + i);
            customer.setCreatedAt(new Date());

            customerList.add(customer);
        }
    }

    public List<Customer> getCustomerList(){
        loadCustomerData();
        LOG.info("Customr data {}", customerList);
        return customerList;
    }
}
