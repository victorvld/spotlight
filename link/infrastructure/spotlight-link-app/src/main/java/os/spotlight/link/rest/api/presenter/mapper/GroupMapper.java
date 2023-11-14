package os.spotlight.link.rest.api.presenter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.spotlight.link.rest.api.presenter.dto.GroupDto;
import os.spotlight.service.Group;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Named("toGroupDto")
    GroupDto toDto(Group group);

    @Named("toGroupsDto")
    List<GroupDto> toDto(List<Group> groups);
}
