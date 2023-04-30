package psy.research.spotlight.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import psy.research.spotlight.entity.FocusUnit;

@SpringBootTest
public class FocusUnitServiceTest {
    @Autowired
    private FocusUnitService service;

    @BeforeEach
    void setUp() {
        var unit = FocusUnit.builder().userUuid("userUuid").build();
        service.saveFocusUnit(unit);
    }

    @Test
    void getFocusUnitsTest() {
        var userId = "uuid";
        var units = service.getFocusUnits(userId);

        Assertions.assertEquals(1, units.size());
        Assertions.assertEquals("userUuid", units.get(0).getUserUuid());
        Assertions.assertNotNull(units.get(0).getUuid());
    }
}
