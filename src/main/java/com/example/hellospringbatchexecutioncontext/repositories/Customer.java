package com.example.hellospringbatchexecutioncontext.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
    @Id
    private Long id;

    private String name;
    private String address;
    private Date createdAt;

    public Customer(){}

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
