package wevioz.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "password", source = "entity.password")
    @Mapping(target = "role", source = "entity.role")
    @Mapping(target = "city", source = "entity.city")
    @Mapping(target = "country", source = "entity.country")
    UserDto toGetDto(User entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "password", source = "dto.password")
    @Mapping(target = "role", source = "dto.role")
    @Mapping(target = "city", source = "dto.city")
    @Mapping(target = "country", source = "dto.country")
    User toEntity(UserDto dto);

    List<UserDto> toGetDtoList(Iterable<User> iterable);
    List<UserDto> toGetDtoList(List<User> list);
}