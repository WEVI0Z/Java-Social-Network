package wevioz.social_network.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.PostCreateDto;
import wevioz.social_network.dto.PostGetDto;
import wevioz.social_network.dto.UserGetDto;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.TextLimitException;
import wevioz.social_network.mapper.PostMapper;
import wevioz.social_network.mapper.UserMapper;
import wevioz.social_network.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PostService implements EntityService<Post>{
    public final int TEXT_LIMIT = 200;

    private final UserService userService;
    private final PostRepository postRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;

    public List<PostGetDto> getPosts() {
        List<PostGetDto> posts = new ArrayList<>();

        postRepository.findAll().forEach(post -> posts.add(postMapper.toGetDto(post)));

        return posts;
    }

    public Post createInstance(String content, User owner) throws TextLimitException {
        if (content.length() > TEXT_LIMIT) {
            throw new TextLimitException("content", TEXT_LIMIT);
        }

        return new Post(content, owner);
    }

    public PostGetDto create(PostCreateDto postCreateDto) {
        UserGetDto userGetDto = userService.findById(postCreateDto.getUserId());

        Post post = createInstance(postCreateDto.getContent(), userMapper.toEntity(userGetDto));

        add(post);

        return postMapper.toGetDto(post);
    }

    public PostGetDto delete(int id) {
        PostGetDto postGetDto = findById(id);

        remove(postMapper.toEntity(postGetDto));

        return postGetDto;
    }

    public PostGetDto findById(int id) throws NotFoundException {
        Optional<Post> post = postRepository.findById((long) id);

        if(post.isEmpty()) {
            throw new NotFoundException("post");
        }

        return postMapper.toGetDto(post.get());
    }

    @Override
    public void add(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(Post post) {
        postRepository.delete(post);
    }
}