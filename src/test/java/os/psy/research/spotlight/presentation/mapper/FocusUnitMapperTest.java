package os.psy.research.spotlight.presentation.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;
import os.psy.research.spotlight.testDataFactory.EntityObjectMother;
import os.psy.research.spotlight.testDataFactory.RequestObjectMother;

class FocusUnitMapperTest {

    private FocusUnitMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(FocusUnitMapper.class);
        var linkedResourceMapper = Mappers.getMapper(LinkedResourceMapper.class);
        var workingTimeMapper = Mappers.getMapper(WorkingTimeMapper.class);
        var breakTimeMapper = Mappers.getMapper(BreakTimeMapper.class);
        var interruptionMapper = Mappers.getMapper(InterruptionMapper.class);
        ReflectionTestUtils.setField(underTest, "linkedResourceMapper", linkedResourceMapper);
        ReflectionTestUtils.setField(underTest, "workingTimeMapper", workingTimeMapper);
        ReflectionTestUtils.setField(underTest, "breakTimeMapper", breakTimeMapper);
        ReflectionTestUtils.setField(workingTimeMapper, "interruptionMapper", interruptionMapper);
        ReflectionTestUtils.setField(breakTimeMapper, "interruptionMapper", interruptionMapper);
    }

    @Test
    void testToDto() {
        var entity = EntityObjectMother.complete().build();
        var result = underTest.toDto(entity);

        Assertions.assertEquals(entity.getUserId(), result.userId());
        Assertions.assertEquals(entity.getEntityId(), result.id());
        Assertions.assertEquals(entity.getLinkedResource().getProjectId(), result.linkedResourceDto().projectId());
        Assertions.assertEquals(entity.getLinkedResource().getTaskId(), result.linkedResourceDto().taskId());

        Assertions.assertTrue(entity.getWorkingTime().getStartedAt().isEqual(result.workingTimeDto().startedAt()));
        Assertions.assertTrue(entity.getWorkingTime().getCompletedAt().isEqual(result.workingTimeDto().completedAt()));
        Assertions.assertEquals(entity.getWorkingTime().getSelectedDuration(), result.workingTimeDto().selectedDuration());
        Assertions.assertEquals(entity.getWorkingTime().getInterruptions().get(0).getType(), result.workingTimeDto().interruptionsDto().get(0).type());
        Assertions.assertEquals(entity.getWorkingTime().getInterruptions().get(0).getReasonType(), result.workingTimeDto().interruptionsDto().get(0).reasonType());
        Assertions.assertEquals(entity.getWorkingTime().getInterruptions().get(0).getRecordedAt(), result.workingTimeDto().interruptionsDto().get(0).recordedAt());
        Assertions.assertEquals(entity.getWorkingTime().getInterruptions().get(0).getDuration(), result.workingTimeDto().interruptionsDto().get(0).duration());

        Assertions.assertTrue(entity.getBreakTime().getStartedAt().isEqual(result.breakTimeDto().startedAt()));
        Assertions.assertTrue(entity.getBreakTime().getCompletedAt().isEqual(result.breakTimeDto().completedAt()));
        Assertions.assertEquals(entity.getBreakTime().getSelectedDuration(), result.breakTimeDto().selectedDuration());
        Assertions.assertEquals(entity.getBreakTime().getInterruptions().get(0).getType(), result.breakTimeDto().interruptionsDto().get(0).type());
        Assertions.assertEquals(entity.getBreakTime().getInterruptions().get(0).getReasonType(), result.breakTimeDto().interruptionsDto().get(0).reasonType());
        Assertions.assertEquals(entity.getBreakTime().getInterruptions().get(0).getRecordedAt(), result.breakTimeDto().interruptionsDto().get(0).recordedAt());
        Assertions.assertEquals(entity.getBreakTime().getInterruptions().get(0).getDuration(), result.breakTimeDto().interruptionsDto().get(0).duration());
    }

    @Test
    void RegisterFocusUnitRequestToEntity() {
        var request = RequestObjectMother.RegisterFocusUnit.complete().build();

        var result = underTest.toEntity(request);

        Assertions.assertNull(result.getEntityId());
        Assertions.assertEquals(request.userId(), result.getUserId());
        Assertions.assertEquals(request.linkedResourceDto().projectId(), result.getLinkedResource().getProjectId());
        Assertions.assertEquals(request.linkedResourceDto().taskId(), result.getLinkedResource().getTaskId());

        Assertions.assertTrue(request.workingTimeDto().startedAt().isEqual(result.getWorkingTime().getStartedAt()));
        Assertions.assertTrue(request.workingTimeDto().completedAt().isEqual(result.getWorkingTime().getCompletedAt()));
        Assertions.assertEquals(request.workingTimeDto().selectedDuration(), result.getWorkingTime().getSelectedDuration());
        Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).type(), result.getWorkingTime().getInterruptions().get(0).getType());
        Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).reasonType(), result.getWorkingTime().getInterruptions().get(0).getReasonType());
        Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).recordedAt(), result.getWorkingTime().getInterruptions().get(0).getRecordedAt());
        Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).duration(), result.getWorkingTime().getInterruptions().get(0).getDuration());

        Assertions.assertTrue(request.breakTimeDto().startedAt().isEqual(result.getBreakTime().getStartedAt()));
        Assertions.assertTrue(request.breakTimeDto().completedAt().isEqual(result.getBreakTime().getCompletedAt()));
        Assertions.assertEquals(request.breakTimeDto().selectedDuration(), result.getBreakTime().getSelectedDuration());
        Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).type(), result.getBreakTime().getInterruptions().get(0).getType());
        Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).reasonType(), result.getBreakTime().getInterruptions().get(0).getReasonType());
        Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).recordedAt(), result.getBreakTime().getInterruptions().get(0).getRecordedAt());
        Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).duration(), result.getBreakTime().getInterruptions().get(0).getDuration());

    }
}
