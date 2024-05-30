package lk.helloshoe.hsplbackend.repository;

import lk.helloshoe.hsplbackend.entity.VarietyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarietyServiceDao extends JpaRepository<VarietyEntity,String> {
}
