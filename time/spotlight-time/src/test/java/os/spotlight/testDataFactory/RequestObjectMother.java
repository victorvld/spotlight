package os.spotlight.testDataFactory;

import os.spotlight.presentation.dto.BreakTimeDto;
import os.spotlight.presentation.dto.InterruptionDto;
import os.spotlight.presentation.dto.UserAssessmentDto;
import os.spotlight.presentation.dto.WorkingTimeDto;
import os.spotlight.presentation.dto.request.RegisterFocusUnitRequest;

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
