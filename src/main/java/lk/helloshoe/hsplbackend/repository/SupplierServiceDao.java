package lk.helloshoe.hsplbackend.repository;

import lk.helloshoe.hsplbackend.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierServiceDao extends JpaRepository<SupplierEntity,String> {
    SupplierEntity findFirstByOrderBySupplierCode();
}
