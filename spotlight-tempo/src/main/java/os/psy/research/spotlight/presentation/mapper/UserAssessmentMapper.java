package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.psy.research.spotlight.domain.entity.UserAssessment;
import os.psy.research.spotlight.presentation.dto.UserAssessmentDto;

@Mapper(componentModel = "spring")
public interface UserAssessmentMapper {

    @Named("userAssessmentToDto")
    UserAssessmentDto toDto(UserAssessment entity);

    @Named("dtoToUserAssessment")
    UserAssessment toEntity(UserAssessmentDto dto);
}
