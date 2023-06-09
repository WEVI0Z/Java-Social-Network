package wevioz.social_network.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.CommentCreateDto;
import wevioz.social_network.dto.CommentGetDto;
import wevioz.social_network.dto.PostGetDto;
import wevioz.social_network.dto.UserGetDto;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.TextLimitException;
import wevioz.social_network.mapper.CommentMapper;
import wevioz.social_network.mapper.PostMapper;
import wevioz.social_network.mapper.UserMapper;
import wevioz.social_network.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CommentService implements EntityService<Comment> {
    public final static int TEXT_LIMIT = 100;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<Comment> comments = new ArrayList<>();

    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public List<CommentGetDto> getComments() {
        List<CommentGetDto> comments = new ArrayList<>();

        commentRepository.findAll().forEach(comment -> comments.add(commentMapper.toGetDto(comment)));

        return comments;
    }

    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(Comment comment) {
        commentRepository.delete(comment);
    }

    public CommentGetDto findById(int id) throws NotFoundException {
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

    public CommentGetDto create(CommentCreateDto commentCreateDto) {
        PostGetDto postGetDto = postService.findById(commentCreateDto.getPostId());
        UserGetDto userGetDto = userService.findById(commentCreateDto.getUserId());

        Comment comment = createInstance(
                commentCreateDto.getContent(),
                postMapper.toEntity(postGetDto),
                userMapper.toEntity(userGetDto)
        );

        add(comment);

        return commentMapper.toGetDto(comment);
    }

    public CommentGetDto delete(int id) {
        CommentGetDto commentGetDto = findById(id);

        remove(commentMapper.toEntity(commentGetDto));

        return commentGetDto;
    }
}