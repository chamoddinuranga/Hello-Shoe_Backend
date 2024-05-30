package lk.helloshoe.hsplbackend.repository;

import lk.helloshoe.hsplbackend.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceDao extends JpaRepository <CustomerEntity,String> {
    CustomerEntity findFirstByOrderByCustomerIdDesc();
}
