package os.psy.research.spotlight.integration.usecase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.domain.repository.FocusUnitRepository;
import os.psy.research.spotlight.domain.service.FocusUnitService;
import os.psy.research.spotlight.infrastructure.persistence.doubles.InMemoryFocusUnitRepository;

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
        private final String userId = "user";

        @BeforeEach
        void setUpScenario() {
            var unit1 = FocusUnit.builder().userId(userId).build();
            var unit2 = FocusUnit.builder().userId(userId).build();
            service.registerFocusUnit(unit1);
            service.registerFocusUnit(unit2);
        }

        @AfterEach
        void cleanUpScenario() {
            repository.deleteAll();
        }

        @Test
        void getFocusUnitsTest() {
            var units = service.getFocusUnits(userId);

            Assertions.assertEquals(2, units.size());
        }

    }

}
