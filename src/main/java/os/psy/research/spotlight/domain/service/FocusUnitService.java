package os.psy.research.spotlight.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import os.psy.research.spotlight.domain.repository.FocusUnitRepository;
import os.psy.research.spotlight.domain.entity.FocusUnit;

import java.util.List;

@Service
@Log4j2
public class FocusUnitService {
    private final FocusUnitRepository repository;

    public FocusUnitService(FocusUnitRepository repository) {
        this.repository = repository;
    }

    public List<FocusUnit> getFocusUnits(String userUuid) {
        log.info("Retrieving all units for user: {}", userUuid);
        return repository.findByUserUuid(userUuid);
    }

    public FocusUnit saveFocusUnit(FocusUnit unit) {
        return repository.save(unit);
    }

    public void deleteFocusUnits(String userId) {
        repository.deleteByUserUuid(userId);
    }
}
