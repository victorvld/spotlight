package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.domain.entity.*;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

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
                        .interruptions(List.of(Interruption.builder()
                                .duration(Duration.ZERO)
                                .type("type")
                                .reasonType("reasonType")
                                .recordedAt(OffsetDateTime.MIN)
                                .build()))
                        .build())
                .breakTime(BreakTime.builder()
                        .startedAt(OffsetDateTime.MIN)
                        .completedAt(OffsetDateTime.MAX)
                        .selectedDuration(Duration.ZERO)
                        .interruptions(List.of(Interruption.builder()
                                .duration(Duration.ZERO)
                                .type("type")
                                .reasonType("reasonType")
                                .recordedAt(OffsetDateTime.MIN)
                                .build()))
                        .build())
                .userAssessment(UserAssessment.builder()
                        .mood("mood")
                        .feedback("feedback")
                        .build());
    }
}
