package os.spotlight.testDataFactory;

import os.spotlight.domain.entity.BreakTime;
import os.spotlight.domain.entity.FocusUnit;
import os.spotlight.domain.entity.Interruption;
import os.spotlight.domain.entity.UserAssessment;
import os.spotlight.domain.entity.WorkingTime;

import java.time.OffsetDateTime;
import java.util.List;

public class EntityObjectMother {

    public static FocusUnit.FocusUnitBuilder<?, ?> complete() {
        return FocusUnit.builder()
                .userId("userId")
                .linkedTaskId("linkedTaskId")
                .workingTime(WorkingTime.builder()
                        .startedAt(OffsetDateTime.MIN)
                        .completedAt(OffsetDateTime.MAX)
                        .plannedMinutes(25)
                        .interruptions(List.of(Interruption.builder()
                                .type("type")
                                .reason("reason")
                                .recordedAt(OffsetDateTime.MIN)
                                .build()))
                        .build())
                .breakTime(BreakTime.builder()
                        .startedAt(OffsetDateTime.MIN)
                        .completedAt(OffsetDateTime.MAX)
                        .plannedMinutes(25)
                        .interruptions(List.of(Interruption.builder()
                                .type("type")
                                .reason("reason")
                                .recordedAt(OffsetDateTime.MIN)
                                .build()))
                        .build())
                .userAssessment(UserAssessment.builder()
                        .mood("mood")
                        .feedback("feedback")
                        .build());
    }
}
