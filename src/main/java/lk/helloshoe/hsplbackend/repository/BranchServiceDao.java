package lk.helloshoe.hsplbackend.repository;

import lk.helloshoe.hsplbackend.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchServiceDao extends JpaRepository<BranchEntity,String> {
}
