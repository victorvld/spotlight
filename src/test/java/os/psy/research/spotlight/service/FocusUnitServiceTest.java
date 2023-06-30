package os.psy.research.spotlight.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import os.psy.research.spotlight.domain.repository.FocusUnitRepository;
import os.psy.research.spotlight.domain.service.FocusUnitService;
import os.psy.research.spotlight.testDataFactory.FocusUnitMother;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        when(repository.findByUserUuid(userId)).thenReturn(null);

        service.getFocusUnits(userId);

        verify(repository, times(1)).findByUserUuid(userId);
    }

    @Test
    void registerFocusUnitTest() {
        var unit = FocusUnitMother.complete().build();
        when(repository.save(unit)).thenReturn(null);

        service.registerFocusUnit(unit);

        verify(repository, times(1)).save(unit);
    }
  
}
