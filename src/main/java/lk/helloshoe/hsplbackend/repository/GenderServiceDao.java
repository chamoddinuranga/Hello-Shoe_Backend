package lk.helloshoe.hsplbackend.repository;

import lk.helloshoe.hsplbackend.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderServiceDao extends JpaRepository<GenderEntity,String> {
}
