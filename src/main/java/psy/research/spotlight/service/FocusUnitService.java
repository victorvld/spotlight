package psy.research.spotlight.service;

import org.springframework.stereotype.Service;
import psy.research.spotlight.repository.FocusUnitRepository;
import psy.research.spotlight.entity.FocusUnit;

import java.util.List;

@Service
public class FocusUnitService {
    private final FocusUnitRepository repository;

    public FocusUnitService(FocusUnitRepository repository) {
        this.repository = repository;
    }

    public List<FocusUnit> getFocusUnits(String userUuid) {
        return repository.findAll();
    }

    public FocusUnit saveFocusUnit(FocusUnit unit) {
        return repository.save(unit);
    }
}
