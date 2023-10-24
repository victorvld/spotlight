package os.psy.research.spotlight.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.domain.repository.FocusUnitRepository;

import java.util.List;

@Service
@Slf4j
public class FocusUnitService {
    private final FocusUnitRepository repository;

    public FocusUnitService(FocusUnitRepository repository) {
        this.repository = repository;
    }

    public List<FocusUnit> getFocusUnits(String userId) {
        log.info("Retrieving all units for user: {}", userId);
        return repository.findByUserId(userId);
    }

    public FocusUnit registerFocusUnit(FocusUnit unit) {
        log.info("Registering unit for user: {}", unit.getUserId());
        return repository.save(unit);
    }
}
