package os.psy.research.spotlight.infrastructure.persistence.doubles;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.domain.repository.FocusUnitRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

// Public class InMemoryCodecastGateway extends GatewayUtilities<Codecast> implements CodeCastGateway {
// Here I can try just to extend a class that implements all the Jpa repository methods. And here just implement the custom ones like findByUserUuid.
@Repository
public class InMemoryFocusUnitRepository extends InMemoryGenericRepository<FocusUnit> implements FocusUnitRepository {

    public List<FocusUnit> findByUserUuid(String userUuid) {
        List<FocusUnit> result = new ArrayList<>();
        for (FocusUnit unit : getEntities()) {
            if (unit.getUserUuid().equals(userUuid)) {
                result.add(unit);
            }
        }
        return result;
    }

    public void deleteByUserUuid(String userUuid) {
        for (FocusUnit unit : getEntities()) {
            if (unit.getUserUuid().equals(userUuid)) {
                getEntities().remove(unit.getId());
            }
        }}
}
