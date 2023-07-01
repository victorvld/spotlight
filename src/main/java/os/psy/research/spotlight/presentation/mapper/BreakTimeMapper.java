package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import os.psy.research.spotlight.domain.entity.BreakTime;
import os.psy.research.spotlight.presentation.dto.BreakTimeDto;

@Mapper(componentModel = "spring", uses = InterruptionMapper.class)
public interface BreakTimeMapper {
    @Named("breakTimeToDto")
    @Mapping(target = "interruptionsDto", source = "interruptions", qualifiedByName = "toInterruptionsDto")
    BreakTimeDto toDto(BreakTime entity);

    @Named("dtoToBreakTime")
    @Mapping(target = "interruptions", source = "interruptionsDto", qualifiedByName = "toInterruptions")
    BreakTime toEntity(BreakTimeDto dto);
}
