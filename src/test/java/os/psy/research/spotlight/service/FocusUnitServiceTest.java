package os.psy.research.spotlight.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.domain.repository.FocusUnitRepository;
import os.psy.research.spotlight.domain.service.FocusUnitService;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FocusUnitServiceTest {
    @Mock
    private FocusUnitRepository repository;

    @InjectMocks
    private FocusUnitService service;

    @Test
    void getFocusUnitsTest() {
        var userId = "user";
        var unit1 = FocusUnit.builder().userId(userId).build();
        var unit2 = FocusUnit.builder().userId(userId).build();
        var userUnits = List.of(unit1, unit2);
        when(repository.findByUserUuid(userId)).thenReturn(userUnits);

        var units = service.getFocusUnits(userId);

        Assertions.assertEquals(2, units.size());
        Assertions.assertEquals(2, units.size());

    }

    @Test
    void registerFocusUnitTest() {
        var userId = "user";
        var unit = FocusUnit.builder().userId(userId).build();
        when(repository.save(unit)).thenReturn(unit);

        var result = service.registerFocusUnit(unit);

        Assertions.assertEquals(unit.getUserId(), result.getUserId());
    }
  
}
