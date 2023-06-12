package wevioz.social_network.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.CommentDto;
import wevioz.social_network.dto.PostDto;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.dto.request.CommentCreateRequest;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.TextLimitException;
import wevioz.social_network.mapper.CommentMapper;
import wevioz.social_network.mapper.PostMapper;
import wevioz.social_network.mapper.UserMapper;
import wevioz.social_network.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements EntityService<Comment> {
    public final static int TEXT_LIMIT = 100;

    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public List<CommentDto> getComments() {
        return commentMapper.toGetDtoList(commentRepository.findAll());
    }

    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(Comment comment) {
        commentRepository.delete(comment);
    }

    public CommentDto findById(int id) throws NotFoundException {
        Optional<Comment> comment = commentRepository.findById((long) id);

        if(comment.isEmpty()) {
            throw new NotFoundException("comment");
        }

        return commentMapper.toGetDto(comment.get());
    }

    public Comment createInstance(String content, Post post, User owner) {
        if (content.length() > TEXT_LIMIT) {
            throw new TextLimitException("content", TEXT_LIMIT);
        }

        return new Comment(content, owner, post);
    }

    public CommentDto create(CommentCreateRequest commentCreateRequest) {
        PostDto postDto = postService.findById(commentCreateRequest.getPostId());
        UserDto userDto = userService.findById(commentCreateRequest.getUserId());

        Comment comment = createInstance(
                commentCreateRequest.getContent(),
                postMapper.toEntity(postDto),
                userMapper.toEntity(userDto)
        );

        add(comment);

        return commentMapper.toGetDto(comment);
    }

    public CommentDto delete(int id) {
        CommentDto commentDto = findById(id);

        remove(commentMapper.toEntity(commentDto));

        return commentDto;
    }
}