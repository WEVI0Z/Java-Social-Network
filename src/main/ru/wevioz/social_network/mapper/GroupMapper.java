package wevioz.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wevioz.social_network.dto.GroupDto;
import wevioz.social_network.entity.Group;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface GroupMapper {
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "participants", source = "dto.participants")
    Group toEntity(GroupDto dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "participants", source = "entity.participants")
    GroupDto toGetDto(Group entity);

    List<GroupDto> toGetDtoList(Iterable<Group> iterable);
}