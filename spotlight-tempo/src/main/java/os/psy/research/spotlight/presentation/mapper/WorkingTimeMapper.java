package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import os.psy.research.spotlight.domain.entity.WorkingTime;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;

@Mapper(componentModel = "spring", uses = InterruptionMapper.class)
public interface WorkingTimeMapper {
    @Named("workingTimeToDto")
    @Mapping(target = "interruptionsDto", source = "interruptions", qualifiedByName = "toInterruptionsDto")
    WorkingTimeDto toDto(WorkingTime entity);

    @Named("dtoToWorkingTime")
    @Mapping(target = "interruptions", source = "interruptionsDto", qualifiedByName = "toInterruptions")
    WorkingTime toEntity(WorkingTimeDto dto);
}
