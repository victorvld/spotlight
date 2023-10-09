package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.psy.research.spotlight.domain.entity.LinkedResource;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;

@Mapper(componentModel = "spring")
public interface LinkedResourceMapper {
    @Named("linkedResourceToDto")
    LinkedResourceDto toDto(LinkedResource entity);

    @Named("dtoToLinkedResource")
    LinkedResource toEntity(LinkedResourceDto dto);
}
