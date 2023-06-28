package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.psy.research.spotlight.domain.entity.WorkingTime;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;

@Mapper(componentModel = "spring")
public interface WorkingTimeMapper {
    @Named("workingTimeToDto")
    WorkingTimeDto toDto(WorkingTime entity);

    @Named("dtoToWorkingTime")
    WorkingTime toEntity(WorkingTimeDto dto);
}
