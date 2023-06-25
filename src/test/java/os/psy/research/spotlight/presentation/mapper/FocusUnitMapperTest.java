package os.psy.research.spotlight.presentation.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;
import os.psy.research.spotlight.presentation.dto.request.RegisterFocusUnitRequest;
import os.psy.research.spotlight.testDataFactory.FocusUnitDtoMother;
import os.psy.research.spotlight.testDataFactory.FocusUnitMother;

class FocusUnitMapperTest {

    private FocusUnitMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(FocusUnitMapper.class);
        var linkedResourceMapper = Mappers.getMapper(LinkedResourceMapper.class);
        ReflectionTestUtils.setField(underTest, "linkedResourceMapper", linkedResourceMapper);
    }

    @Test
    void testToDto() {
        var entity = FocusUnitMother.complete().build();
        var result = underTest.toDto(entity);

        Assertions.assertEquals(entity.getUserId(), result.getUserId());
        Assertions.assertEquals(entity.getEntityId(), result.getId());
        Assertions.assertEquals(entity.getLinkedResource().getProjectId(), result.getLinkedResourceDto().getProjectId());
        Assertions.assertEquals(entity.getLinkedResource().getTaskId(), result.getLinkedResourceDto().getTaskId());
    }

    @Test
    void RegisterFocusUnitRequestToEntity() {
        var request = RegisterFocusUnitRequest.builder().userId("userId").linkedResourceDto(LinkedResourceDto.builder().projectId("projectId").taskId("taskId").build()).build();
        var result = underTest.toEntity(request);

        Assertions.assertNull(result.getEntityId());
        Assertions.assertEquals(request.getUserId(), result.getUserId());
        Assertions.assertEquals(request.getLinkedResourceDto().getProjectId(), result.getLinkedResource().getProjectId());
        Assertions.assertEquals(request.getLinkedResourceDto().getTaskId(), result.getLinkedResource().getTaskId());
    }
}
