package wevioz.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wevioz.social_network.dto.GroupGetDto;
import wevioz.social_network.dto.UserGetDto;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "participants", source = "dto.participants")
    Group toEntity(GroupGetDto dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "participants", source = "entity.participants")
    GroupGetDto toGetDto(Group entity);

    List<GroupGetDto> toGetDtoList(Iterable<Group> iterable);
}