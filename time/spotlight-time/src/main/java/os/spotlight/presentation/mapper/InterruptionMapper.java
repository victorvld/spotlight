package os.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.spotlight.domain.entity.Interruption;
import os.spotlight.presentation.dto.InterruptionDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InterruptionMapper {

    @Named("toInterruptionDto")
    InterruptionDto toDto(Interruption entity);

    @Named("toInterruptionsDto")
    List<InterruptionDto> toDto(List<Interruption> entity);

    @Named("dtoToInterruption")
    Interruption toEntity(InterruptionDto dto);

    @Named("toInterruptions")
    List<Interruption> toEntity(List<InterruptionDto> dto);
}
