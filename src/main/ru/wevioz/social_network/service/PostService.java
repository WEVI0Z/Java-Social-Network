package wevioz.social_network.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.dto.request.PostCreateRequest;
import wevioz.social_network.dto.PostDto;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.TextLimitException;
import wevioz.social_network.mapper.PostMapper;
import wevioz.social_network.mapper.UserMapper;
import wevioz.social_network.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements EntityService<Post>{
    public final int TEXT_LIMIT = 200;

    private final UserService userService;
    private final PostRepository postRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;

    public List<PostDto> getPosts() {
        return postMapper.toGetDtoList(postRepository.findAll());
    }

    public Post createInstance(String content, User owner) throws TextLimitException {
        if (content.length() > TEXT_LIMIT) {
            throw new TextLimitException("content", TEXT_LIMIT);
        }

        return new Post(content, owner);
    }

    public PostDto create(PostCreateRequest postCreateRequest) {
        UserDto userDto = userService.findById(postCreateRequest.getUserId());

        Post post = createInstance(postCreateRequest.getContent(), userMapper.toEntity(userDto));

        add(post);

        return postMapper.toGetDto(post);
    }

    public PostDto delete(int id) {
        PostDto postDto = findById(id);

        remove(postMapper.toEntity(postDto));

        return postDto;
    }

    public PostDto findById(int id) throws NotFoundException {
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