package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.presentation.dto.*;
import os.psy.research.spotlight.presentation.dto.request.RegisterFocusUnitRequest;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

public class RequestObjectMother {

    public static class RegisterFocusUnit {
        public static RegisterFocusUnitRequest.RegisterFocusUnitRequestBuilder complete() {
            return RegisterFocusUnitRequest.builder()
                    .userId("userId")
                    .linkedTaskId("taskId")
                    .workingTimeDto(WorkingTimeDto.builder()
                            .startedAt(OffsetDateTime.MIN)
                            .completedAt(OffsetDateTime.MAX)
                            .plannedMinutes(25)
                            .interruptionsDto(List.of(InterruptionDto.builder()
                                    .type("type")
                                    .reason("reason")
                                    .recordedAt(OffsetDateTime.MIN)

                                    .build()))
                            .build())
                    .breakTimeDto(BreakTimeDto.builder()
                            .startedAt(OffsetDateTime.MIN)
                            .completedAt(OffsetDateTime.MAX)
                            .plannedMinutes(25)
                            .interruptionsDto(List.of(InterruptionDto.builder()
                                    .type("type")
                                    .reason("reason")
                                    .recordedAt(OffsetDateTime.MIN)
                                    .build()))
                            .build())
                    .userAssessmentDto(UserAssessmentDto.builder()
                            .feedback("feedback")
                            .mood("mood")
                            .build());
        }
    }

}
