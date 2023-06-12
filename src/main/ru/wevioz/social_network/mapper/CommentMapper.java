package wevioz.social_network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wevioz.social_network.dto.CommentGetDto;
import wevioz.social_network.dto.UserGetDto;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "content", source = "dto.content")
    @Mapping(target = "owner", source = "dto.owner")
    @Mapping(target = "post", source = "dto.post")
    Comment toEntity(CommentGetDto dto);
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "content", source = "entity.content")
    @Mapping(target = "owner", source = "entity.owner")
    @Mapping(target = "post", source = "entity.post")
    CommentGetDto toGetDto(Comment entity);

    List<CommentGetDto> toGetDtoList(Iterable<Comment> iterable);
}