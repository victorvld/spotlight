package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.psy.research.spotlight.domain.entity.BreakTime;
import os.psy.research.spotlight.presentation.dto.BreakTimeDto;

@Mapper(componentModel = "spring")
public interface BreakTimeMapper {
    @Named("breakTimeToDto")
    BreakTimeDto toDto(BreakTime entity);

    @Named("dtoToBreakTime")
    BreakTime toEntity(BreakTimeDto dto);
}
