package os.spotlight.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import os.spotlight.domain.repository.FocusUnitRepository;
import os.spotlight.domain.service.FocusUnitService;
import os.spotlight.testDataFactory.EntityObjectMother;

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
        when(repository.findByUserId(userId)).thenReturn(null);

        service.getFocusUnits(userId);

        verify(repository, times(1)).findByUserId(userId);
    }

    @Test
    void registerFocusUnitTest() {
        var unit = EntityObjectMother.complete().build();
        when(repository.save(unit)).thenReturn(null);

        service.registerFocusUnit(unit);

        verify(repository, times(1)).save(unit);
    }
  
}
