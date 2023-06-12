package wevioz.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wevioz.social_network.dto.CommentDto;
import wevioz.social_network.entity.Comment;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PostMapper.class, UserMapper.class})
public interface CommentMapper {
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "content", source = "dto.content")
    @Mapping(target = "owner", source = "dto.owner")
    @Mapping(target = "post", source = "dto.post")
    Comment toEntity(CommentDto dto);
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "content", source = "entity.content")
    @Mapping(target = "owner", source = "entity.owner")
    @Mapping(target = "post", source = "entity.post")
    CommentDto toGetDto(Comment entity);

    List<CommentDto> toGetDtoList(Iterable<Comment> iterable);
    List<CommentDto> toGetDtoList(List<Comment> list);
}