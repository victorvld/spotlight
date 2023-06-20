package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.presentation.dto.FocusUnitDto;
import os.psy.research.spotlight.presentation.dto.RegisterFocusUnitRequest;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface FocusUnitMapper {

    @Mapping(target = "userId", source = "userUuid")
    @Mapping(target = "id", source = "entityId")
    FocusUnitDto toDto(FocusUnit unit);

    @Mapping(target = "userUuid", source = "userId")
    FocusUnit toEntity(RegisterFocusUnitRequest dto);
}
