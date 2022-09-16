package com.example.hellospringbatchexecutioncontext;

import com.example.hellospringbatchexecutioncontext.repositories.Customer;
import com.example.hellospringbatchexecutioncontext.services.CustomerDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamSupport;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerReader extends ItemStreamSupport implements ItemReader<Customer> {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerReader.class);

    @Inject
    private CustomerDataService customerDataService;

    private List<Customer> customerList ;
    private Integer index;
    private String INDEX_KEY = "current.index.customers";

    private ExecutionContext executionContext;

    public CustomerReader(){
        index = 0;
        customerList = new ArrayList<>();
    }

    @Override
    public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        LOG.info("reading a customer index {}", index);
        if(customerList.isEmpty()){
            init();
        }

        Customer customer = null;
        if(index == 7){
            throw new RuntimeException("This will end your job .");
        }
        if(index < customerList.size()){
            customer = customerList.get(index);
            index += 1;
        }

        update(executionContext);
        return customer;
    }

    private boolean init(){
        customerList = customerDataService.getCustomerList();
        return true;
    }

    public void open(ExecutionContext executionContext){
        this.executionContext = executionContext;
        if(executionContext.containsKey(getExecutionContextKey(INDEX_KEY))){
            int savedIndex = executionContext.getInt(getExecutionContextKey(INDEX_KEY));
            if(savedIndex == 7){
                this.index = 8;
            }
            else{
                this.index = savedIndex;
            }
        }
        else{
            this.index = 0;
        }
    }

    public void update(ExecutionContext executionContext){
        executionContext.putInt(getExecutionContextKey(INDEX_KEY), this.index);
    }
}
