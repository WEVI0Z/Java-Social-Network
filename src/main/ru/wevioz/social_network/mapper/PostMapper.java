package wevioz.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wevioz.social_network.dto.PostDto;
import wevioz.social_network.entity.Post;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PostMapper {
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "content", source = "dto.content")
    @Mapping(target = "owner", source = "dto.owner")
    @Mapping(target = "creationDate", source = "dto.creationDate")
    Post toEntity(PostDto dto);
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "content", source = "entity.content")
    @Mapping(target = "owner", source = "entity.owner")
    @Mapping(target = "creationDate", source = "entity.creationDate")
    PostDto toGetDto(Post entity);

    List<PostDto> toGetDtoList(Iterable<Post> iterable);
    List<PostDto> toGetDtoList(List<Post> list);
}