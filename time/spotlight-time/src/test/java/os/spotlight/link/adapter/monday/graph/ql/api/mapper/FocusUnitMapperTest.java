package os.spotlight.link.adapter.monday.graph.ql.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;
import os.spotlight.testDataFactory.EntityObjectMother;
import os.spotlight.testDataFactory.RequestObjectMother;
import os.spotlight.presentation.mapper.BreakTimeMapper;
import os.spotlight.presentation.mapper.FocusUnitMapper;
import os.spotlight.presentation.mapper.InterruptionMapper;
import os.spotlight.presentation.mapper.UserAssessmentMapper;
import os.spotlight.presentation.mapper.WorkingTimeMapper;

class FocusUnitMapperTest {

    private FocusUnitMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(FocusUnitMapper.class);
        var workingTimeMapper = Mappers.getMapper(WorkingTimeMapper.class);
        var breakTimeMapper = Mappers.getMapper(BreakTimeMapper.class);
        var userAssessmentMapper = Mappers.getMapper(UserAssessmentMapper.class);
        var interruptionMapper = Mappers.getMapper(InterruptionMapper.class);
        ReflectionTestUtils.setField(underTest, "workingTimeMapper", workingTimeMapper);
        ReflectionTestUtils.setField(underTest, "breakTimeMapper", breakTimeMapper);
        ReflectionTestUtils.setField(workingTimeMapper, "interruptionMapper", interruptionMapper);
        ReflectionTestUtils.setField(breakTimeMapper, "interruptionMapper", interruptionMapper);
        ReflectionTestUtils.setField(underTest, "userAssessmentMapper", userAssessmentMapper);
    }

    @Test
    void testToDto() {
        var entity = EntityObjectMother.complete().build();
        var result = underTest.toDto(entity);

        Assertions.assertEquals(entity.getUserId(), result.userId());
        Assertions.assertEquals(entity.getEntityId(), result.id());
        Assertions.assertEquals(entity.getLinkedTaskId(), result.linkedTaskId());

        Assertions.assertTrue(entity.getWorkingTime().getStartedAt().isEqual(result.workingTimeDto().startedAt()));
        Assertions.assertTrue(entity.getWorkingTime().getCompletedAt().isEqual(result.workingTimeDto().completedAt()));
        Assertions.assertEquals(entity.getWorkingTime().getPlannedMinutes(), result.workingTimeDto().plannedMinutes());
        Assertions.assertEquals(entity.getWorkingTime().getInterruptions().get(0).getType(), result.workingTimeDto().interruptionsDto().get(0).type());
        Assertions.assertEquals(entity.getWorkingTime().getInterruptions().get(0).getReason(), result.workingTimeDto().interruptionsDto().get(0).reason());
        Assertions.assertEquals(entity.getWorkingTime().getInterruptions().get(0).getRecordedAt(), result.workingTimeDto().interruptionsDto().get(0).recordedAt());

        Assertions.assertTrue(entity.getBreakTime().getStartedAt().isEqual(result.breakTimeDto().startedAt()));
        Assertions.assertTrue(entity.getBreakTime().getCompletedAt().isEqual(result.breakTimeDto().completedAt()));
        Assertions.assertEquals(entity.getBreakTime().getPlannedMinutes(), result.breakTimeDto().plannedMinutes());
        Assertions.assertEquals(entity.getBreakTime().getInterruptions().get(0).getType(), result.breakTimeDto().interruptionsDto().get(0).type());
        Assertions.assertEquals(entity.getBreakTime().getInterruptions().get(0).getReason(), result.breakTimeDto().interruptionsDto().get(0).reason());
        Assertions.assertEquals(entity.getBreakTime().getInterruptions().get(0).getRecordedAt(), result.breakTimeDto().interruptionsDto().get(0).recordedAt());

        Assertions.assertEquals(entity.getUserAssessment().getMood(), result.userAssessmentDto().mood());
        Assertions.assertEquals(entity.getUserAssessment().getFeedback(), result.userAssessmentDto().feedback());
    }

    @Test
    void RegisterFocusUnitRequestToEntity() {
        var request = RequestObjectMother.RegisterFocusUnit.complete().build();

        var result = underTest.toEntity(request);

        Assertions.assertNull(result.getEntityId());
        Assertions.assertEquals(request.userId(), result.getUserId());
        Assertions.assertEquals(request.linkedTaskId(), result.getLinkedTaskId());

        Assertions.assertTrue(request.workingTimeDto().startedAt().isEqual(result.getWorkingTime().getStartedAt()));
        Assertions.assertTrue(request.workingTimeDto().completedAt().isEqual(result.getWorkingTime().getCompletedAt()));
        Assertions.assertEquals(request.workingTimeDto().plannedMinutes(), result.getWorkingTime().getPlannedMinutes());
        Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).type(), result.getWorkingTime().getInterruptions().get(0).getType());
        Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).reason(), result.getWorkingTime().getInterruptions().get(0).getReason());
        Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).recordedAt(), result.getWorkingTime().getInterruptions().get(0).getRecordedAt());

        Assertions.assertTrue(request.breakTimeDto().startedAt().isEqual(result.getBreakTime().getStartedAt()));
        Assertions.assertTrue(request.breakTimeDto().completedAt().isEqual(result.getBreakTime().getCompletedAt()));
        Assertions.assertEquals(request.breakTimeDto().plannedMinutes(), result.getBreakTime().getPlannedMinutes());
        Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).type(), result.getBreakTime().getInterruptions().get(0).getType());
        Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).reason(), result.getBreakTime().getInterruptions().get(0).getReason());
        Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).recordedAt(), result.getBreakTime().getInterruptions().get(0).getRecordedAt());

        Assertions.assertEquals(request.userAssessmentDto().mood(), result.getUserAssessment().getMood());
        Assertions.assertEquals(request.userAssessmentDto().feedback(), result.getUserAssessment().getFeedback());
    }
}
