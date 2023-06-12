package wevioz.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wevioz.social_network.dto.UserGetDto;
import wevioz.social_network.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "email", source = "entity.email")
    UserGetDto toGetDto(User entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "email", source = "dto.email")
    User toEntity(UserGetDto dto);

    List<UserGetDto> toGetDtoList(List<User> list);
}