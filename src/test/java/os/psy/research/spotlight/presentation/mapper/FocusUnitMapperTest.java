package os.psy.research.spotlight.presentation.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;
import os.psy.research.spotlight.presentation.dto.request.RegisterFocusUnitRequest;
import os.psy.research.spotlight.testDataFactory.EntityObjectMother;

import java.time.Duration;
import java.time.OffsetDateTime;

class FocusUnitMapperTest {

    private FocusUnitMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(FocusUnitMapper.class);
        var linkedResourceMapper = Mappers.getMapper(LinkedResourceMapper.class);
        var workingTimeMapper = Mappers.getMapper(WorkingTimeMapper.class);
        ReflectionTestUtils.setField(underTest, "linkedResourceMapper", linkedResourceMapper);
        ReflectionTestUtils.setField(underTest, "workingTimeMapper", workingTimeMapper);
    }

    @Test
    void testToDto() {
        var entity = EntityObjectMother.complete().build();
        var result = underTest.toDto(entity);

        Assertions.assertEquals(entity.getUserId(), result.userId());
        Assertions.assertEquals(entity.getEntityId(), result.id());
        Assertions.assertEquals(entity.getLinkedResource().getProjectId(), result.linkedResourceDto().projectId());
        Assertions.assertEquals(entity.getLinkedResource().getTaskId(), result.linkedResourceDto().taskId());
        Assertions.assertTrue(entity.getWorkingTime().startedAt().isEqual(result.workingTimeDto().startedAt()));
        Assertions.assertTrue(entity.getWorkingTime().completedAt().isEqual(result.workingTimeDto().completedAt()));
        Assertions.assertEquals(entity.getWorkingTime().selectedDuration(), result.workingTimeDto().selectedDuration());
    }

    @Test
    void RegisterFocusUnitRequestToEntity() {
        var request = RegisterFocusUnitRequest.builder()
                .userId("userId")
                .linkedResourceDto(LinkedResourceDto.builder().projectId("projectId").taskId("taskId").build())
                .workingTimeDto(WorkingTimeDto.builder().startedAt(OffsetDateTime.MIN).completedAt(OffsetDateTime.MAX).selectedDuration(Duration.ZERO).build())
                .build();
        var result = underTest.toEntity(request);

        Assertions.assertNull(result.getEntityId());
        Assertions.assertEquals(request.userId(), result.getUserId());
        Assertions.assertEquals(request.linkedResourceDto().projectId(), result.getLinkedResource().getProjectId());
        Assertions.assertEquals(request.linkedResourceDto().taskId(), result.getLinkedResource().getTaskId());
        Assertions.assertTrue(request.workingTimeDto().startedAt().isEqual(result.getWorkingTime().startedAt()));
        Assertions.assertTrue(request.workingTimeDto().completedAt().isEqual(result.getWorkingTime().completedAt()));
        Assertions.assertEquals(request.workingTimeDto().selectedDuration(), result.getWorkingTime().selectedDuration());
    }
}
