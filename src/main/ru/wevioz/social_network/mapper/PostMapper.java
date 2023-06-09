package wevioz.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wevioz.social_network.dto.PostGetDto;
import wevioz.social_network.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "content", source = "dto.content")
    @Mapping(target = "owner", source = "dto.owner")
    @Mapping(target = "creationDate", source = "dto.creationDate")
    Post toEntity(PostGetDto dto);
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "content", source = "entity.content")
    @Mapping(target = "owner", source = "entity.owner")
    @Mapping(target = "creationDate", source = "entity.creationDate")
    PostGetDto toGetDto(Post entity);
}