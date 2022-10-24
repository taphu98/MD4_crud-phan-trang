package rikkei.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import rikkei.academy.model.Customer;

public interface ICustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    Page<Customer> findAll(Pageable pageable);
}
