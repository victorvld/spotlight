package os.spotlight.domain.repository;

import os.psy.research.spotlight.core.repository.GenericRepository;
import os.spotlight.domain.entity.FocusUnit;

import java.util.List;

public interface FocusUnitRepository extends GenericRepository<FocusUnit> {

    List<FocusUnit> findByUserId(String userId);
}
