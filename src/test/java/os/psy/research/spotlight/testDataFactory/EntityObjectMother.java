package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.domain.entity.BreakTime;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.domain.entity.LinkedResource;
import os.psy.research.spotlight.domain.entity.WorkingTime;

import java.time.Duration;
import java.time.OffsetDateTime;

public class EntityObjectMother {

    public static FocusUnit.FocusUnitBuilder<?, ?> complete() {
        return FocusUnit.builder()
                .userId("userId")
                .linkedResource(LinkedResource.builder()
                        .projectId("projectId")
                        .taskId("taskId")
                        .build())
                .workingTime(WorkingTime.builder()
                        .startedAt(OffsetDateTime.MIN)
                        .completedAt(OffsetDateTime.MAX)
                        .selectedDuration(Duration.ZERO)
                        .build())
                .breakTime(BreakTime.builder()
                        .startedAt(OffsetDateTime.MIN)
                        .completedAt(OffsetDateTime.MAX)
                        .selectedDuration(Duration.ZERO)
                        .build());
    }
}
