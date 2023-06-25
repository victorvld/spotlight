package os.psy.research.spotlight.presentation.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import os.psy.research.spotlight.domain.entity.LinkedResource;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;

public class LinkedResourceMapperTests {

    private LinkedResourceMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(LinkedResourceMapper.class);
    }

    @Test
    void testToDto() {
        var entity = LinkedResource.builder().projectId("projectId").taskId("taskId").build();
        var result = underTest.toDto(entity);

        Assertions.assertEquals(entity.getProjectId(), result.getProjectId());
        Assertions.assertEquals(entity.getTaskId(), result.getTaskId());
    }

    @Test
    void testToEntity() {
        var dto = LinkedResourceDto.builder().projectId("projectId").taskId("taskId").build();
        var result = underTest.toEntity(dto);

        Assertions.assertEquals(dto.getProjectId(), result.getProjectId());
        Assertions.assertEquals(dto.getTaskId(), result.getTaskId());
    }
}
