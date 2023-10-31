package os.psy.research.spotlight.integration.usecase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import os.psy.research.spotlight.domain.repository.FocusUnitRepository;
import os.psy.research.spotlight.domain.service.FocusUnitService;
import os.psy.research.spotlight.infrastructure.persistence.doubles.InMemoryFocusUnitRepository;
import os.psy.research.spotlight.testDataFactory.EntityObjectMother;

import java.time.OffsetDateTime;
public class FocusUnitIntegrationTests {
    private final FocusUnitRepository repository = new InMemoryFocusUnitRepository();
    private final FocusUnitService service = new FocusUnitService(repository);

    @Nested
    @DisplayName("When user has already registered some focus units.")
    class WhenUserHasRecordedSomeFocusUnit {

        @BeforeEach
        void setUpScenario() {
            var unit1 = EntityObjectMother.complete().build();
            var unit2 = EntityObjectMother.complete().build();
            service.registerFocusUnit(unit1);
            service.registerFocusUnit(unit2);
        }

        @AfterEach
        void cleanUpScenario() {
            repository.deleteAll();
        }

        @Test
        void getFocusUnitsTest() {
            var units = service.getFocusUnits("userId");

            Assertions.assertEquals(2, units.size());
            Assertions.assertEquals("linkedTaskId", units.get(0).getLinkedTaskId());
            Assertions.assertEquals(OffsetDateTime.MIN, units.get(0).getWorkingTime().getStartedAt());
            Assertions.assertEquals(OffsetDateTime.MAX, units.get(0).getWorkingTime().getCompletedAt());
            Assertions.assertEquals(25, units.get(0).getWorkingTime().getPlannedMinutes());
        }

    }

    // TODO: 11/10/2023 Write integration tests for RegisterFocusUnit use case.
    //  When FU has been already registered could not be registered again.
    //  When FU Has not been registered so far it could be registered.
}
