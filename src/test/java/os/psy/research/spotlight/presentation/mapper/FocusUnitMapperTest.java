package os.psy.research.spotlight.presentation.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.presentation.dto.RegisterFocusUnitRequest;

class FocusUnitMapperTest {

    private FocusUnitMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(FocusUnitMapper.class);
    }

    @Test
    void testToDto() {
        var entity = FocusUnit.builder().userId("userUuid").build();
        var result = underTest.toDto(entity);

        Assertions.assertEquals(entity.getUserId(), result.getUserId());
        Assertions.assertEquals(entity.getEntityId(), result.getId());
    }

    @Test
    void testToEntity() {
        var dto = RegisterFocusUnitRequest.builder().userId("userUuid").build();
        var result = underTest.toEntity(dto);

        Assertions.assertNull(result.getEntityId());
        Assertions.assertEquals(dto.getUserId(), result.getUserId());
    }
}
