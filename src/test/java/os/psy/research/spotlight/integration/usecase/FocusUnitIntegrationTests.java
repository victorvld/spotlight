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

import java.time.Duration;
import java.time.OffsetDateTime;

public class FocusUnitIntegrationTests {

    // TODO: 03/06/2023 Playing around with different approaches.
    /*
    Approach 1: @SpringBootTest - Result - very slow.
    Approach 2: @DataJpaTest - Not mean for use case integration tests.
    Approach 3: In memory database. This should be a custom one independent of Spring H2 in memory Database. It work so far.
     */
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
            Assertions.assertEquals("projectId", units.get(0).getLinkedResource().getProjectId());
            Assertions.assertEquals("taskId", units.get(0).getLinkedResource().getTaskId());
            Assertions.assertEquals(OffsetDateTime.MIN, units.get(0).getWorkingTime().startedAt());
            Assertions.assertEquals(OffsetDateTime.MAX, units.get(0).getWorkingTime().completedAt());
            Assertions.assertEquals(Duration.ZERO, units.get(0).getWorkingTime().selectedDuration());
        }

    }

}
