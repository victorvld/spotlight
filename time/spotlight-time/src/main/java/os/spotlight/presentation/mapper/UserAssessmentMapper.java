package os.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.spotlight.domain.entity.UserAssessment;
import os.spotlight.presentation.dto.UserAssessmentDto;

@Mapper(componentModel = "spring")
public interface UserAssessmentMapper {

    @Named("userAssessmentToDto")
    UserAssessmentDto toDto(UserAssessment entity);

    @Named("dtoToUserAssessment")
    UserAssessment toEntity(UserAssessmentDto dto);
}
