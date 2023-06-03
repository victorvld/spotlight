package os.psy.research.spotlight.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import os.psy.research.spotlight.domain.entity.FocusUnit;

import java.util.List;

public interface FocusUnitRepository extends JpaRepository<FocusUnit, String> {

    List<FocusUnit> findByUserUuid(String userUuid);

    void deleteByUserUuid(String userUuid);
}
