package lk.helloshoe.hsplbackend.repository;

import lk.helloshoe.hsplbackend.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeServiceDao extends JpaRepository<EmployeeEntity,String> {
    EmployeeEntity findFirstByOrderByEmployeeCodeDesc();
}
