package os.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import os.spotlight.domain.entity.FocusUnit;
import os.spotlight.presentation.dto.FocusUnitDto;
import os.spotlight.presentation.dto.request.RegisterFocusUnitRequest;

@Mapper(componentModel = "spring", uses = {WorkingTimeMapper.class, BreakTimeMapper.class, UserAssessmentMapper.class})
public interface FocusUnitMapper {

    @Named("focusUnitToDto")
    @Mapping(target = "id", source = "entityId")
    @Mapping(target = "workingTimeDto", source = "workingTime", qualifiedByName = "workingTimeToDto")
    @Mapping(target = "breakTimeDto", source = "breakTime", qualifiedByName = "breakTimeToDto")
    @Mapping(target = "userAssessmentDto", source = "userAssessment", qualifiedByName = "userAssessmentToDto")
    FocusUnitDto toDto(FocusUnit unit);

    @Named("registeredRequestToFocusUnit")
    @Mapping(target = "workingTime", source = "workingTimeDto", qualifiedByName = "dtoToWorkingTime")
    @Mapping(target = "breakTime", source = "breakTimeDto", qualifiedByName = "dtoToBreakTime")
    @Mapping(target = "userAssessment", source = "userAssessmentDto", qualifiedByName = "dtoToUserAssessment")
    FocusUnit toEntity(RegisterFocusUnitRequest dto);
}
