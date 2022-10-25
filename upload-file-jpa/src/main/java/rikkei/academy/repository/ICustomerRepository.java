package rikkei.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import rikkei.academy.model.Customer;

public interface ICustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByNameContaining(String name, Pageable pageable);
    @Query("SELECT c FROM Customer AS c WHERE c.name LIKE CONCAT('%',:name,'%')")
    Page<Customer> findByNameCustomer(@Param("name")String name, Pageable pageable);
}
