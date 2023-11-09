package os.spotlight.infrastructure.persistence.doubles;

import org.springframework.stereotype.Repository;
import os.spotlight.domain.entity.FocusUnit;
import os.spotlight.domain.repository.FocusUnitRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryFocusUnitRepository extends InMemoryGenericRepository<FocusUnit> implements FocusUnitRepository {

    public List<FocusUnit> findByUserId(String userUuid) {
        List<FocusUnit> result = new ArrayList<>();
        for (FocusUnit unit : getEntities()) {
            if (unit.getUserId().equals(userUuid)) {
                result.add(unit);
            }
        }
        return result;
    }
}
