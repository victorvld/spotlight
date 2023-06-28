package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.presentation.dto.FocusUnitDto;
import os.psy.research.spotlight.presentation.dto.request.RegisterFocusUnitRequest;

@Mapper(componentModel = "spring", uses = {LinkedResourceMapper.class, WorkingTimeMapper.class})
public interface FocusUnitMapper {

    @Named("focusUnitToDto")
    @Mapping(target = "id", source = "entityId")
    @Mapping(target = "linkedResourceDto", source = "linkedResource", qualifiedByName = "linkedResourceToDto")
    @Mapping(target = "workingTimeDto", source = "workingTime", qualifiedByName = "workingTimeToDto")
    FocusUnitDto toDto(FocusUnit unit);

    @Named("registeredRequestToFocusUnit")
    @Mapping(target = "linkedResource", source = "linkedResourceDto", qualifiedByName = "dtoToLinkedResource")
    @Mapping(target = "workingTime", source = "workingTimeDto", qualifiedByName = "dtoToWorkingTime")
    FocusUnit toEntity(RegisterFocusUnitRequest dto);
}
