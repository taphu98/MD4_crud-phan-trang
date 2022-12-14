package rikkei.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import rikkei.academy.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    void save(Customer customer);
    Customer findById(Long id);
    void deleteById(Long id);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByNameContaining(String name, Pageable pageable);
    Page<Customer> findByNameCustomer(@Param("name") String name, Pageable pageable);
}
