package wevioz.social_network.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import wevioz.social_network.dto.UserGetDto;
import wevioz.social_network.dto.UserPostDto;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.UniqueException;
import wevioz.social_network.mapper.UserMapper;
import wevioz.social_network.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Service
@RequiredArgsConstructor
public class UserService implements EntityService<User> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserGetDto> get() {
        List<UserGetDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> System.out.println(user.getEmail()));
        userRepository.findAll().forEach(user -> users.add(userMapper.toGetDto(user)));

        return users;
    }

    public User createInstance(String email) {
        Optional<User> sameUser = userRepository.findUserByEmail(email);

        if (sameUser.isPresent()) {
            throw new UniqueException("email");
        }

        return new User(email);
    }

    public UserGetDto create(UserPostDto userPostDto) {
        User user = createInstance(userPostDto.getEmail());

        add(user);

        return userMapper.toGetDto(user);
    }

    public UserGetDto findById(int id) {
        Optional<User> user = userRepository.findById((long) id);

        if(user.isEmpty()) {
            throw new NotFoundException("user");
        }

        return userMapper.toGetDto(user.get());
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    public UserGetDto removeById(int id) {
        UserGetDto user = findById(id);

        remove(userMapper.toEntity(user));

        return user;
    }
}