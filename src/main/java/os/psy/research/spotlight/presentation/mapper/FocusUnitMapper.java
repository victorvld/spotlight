package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.presentation.dto.GetFocusUnitResponse;


@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface FocusUnitMapper {

    @Named("toDtoGetResponse")
    @Mapping(target = "userId", source = "userUuid")
    GetFocusUnitResponse toDto(FocusUnit unit);
}
