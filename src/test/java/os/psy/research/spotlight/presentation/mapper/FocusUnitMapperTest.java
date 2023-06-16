package os.psy.research.spotlight.presentation.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.presentation.dto.FocusUnitDto;

class FocusUnitMapperTest {

    private FocusUnitMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(FocusUnitMapper.class);
    }

    @Test
    void testToDto() {
        var entity = FocusUnit.builder().userUuid("userUuid").build();
        var result = underTest.toDto(entity);

        Assertions.assertEquals(entity.getUserUuid(), result.getUserId());
        Assertions.assertEquals(entity.getId(), result.getId());
    }

    @Test
    void testToEntity() {
        var dto = FocusUnitDto.builder().userId("userUuid").build();
        var result = underTest.toEntity(dto);

        Assertions.assertEquals(dto.getId(), result.getId());
        Assertions.assertEquals(dto.getUserId(), result.getUserUuid());
    }
}