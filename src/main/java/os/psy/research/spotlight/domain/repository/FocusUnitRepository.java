package os.psy.research.spotlight.domain.repository;

import os.psy.research.spotlight.domain.entity.FocusUnit;

import java.util.List;

public interface FocusUnitRepository extends GenericRepository<FocusUnit> {

    List<FocusUnit> findByUserUuid(String userUuid);

    void deleteByUserUuid(String userUuid);
}
