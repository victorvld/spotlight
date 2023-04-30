package psy.research.spotlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psy.research.spotlight.entity.FocusUnit;

public interface FocusUnitRepository extends JpaRepository<FocusUnit, String> {
}
