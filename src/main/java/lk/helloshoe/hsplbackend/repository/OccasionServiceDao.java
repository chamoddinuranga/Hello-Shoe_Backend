package lk.helloshoe.hsplbackend.repository;

import lk.helloshoe.hsplbackend.entity.OccasionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccasionServiceDao extends JpaRepository<OccasionEntity,String> {
}
