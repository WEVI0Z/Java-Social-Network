package wevioz.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wevioz.social_network.dto.StatDto;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.entity.User;

import java.time.LocalDate;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface StatMapper {
    @Mapping(target = "name", source = "dto.email")
    @Mapping(target = "city", source = "dto.city")
    @Mapping(target = "country", source = "dto.country")
    StatDto toDto(UserDto dto);

    @Mapping(target = "name", source = "entity.email")
    @Mapping(target = "city", source = "entity.city")
    @Mapping(target = "country", source = "entity.country")
    StatDto toDto(User entity);
}
