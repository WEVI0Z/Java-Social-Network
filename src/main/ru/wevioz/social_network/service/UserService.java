package wevioz.social_network.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.dto.request.UserPostRequest;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.UniqueException;
import wevioz.social_network.mapper.UserMapper;
import wevioz.social_network.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class UserService implements EntityService<User>, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> get() {
        return userMapper.toGetDtoList(userRepository.findAll());
    }

    public User createInstance(String email, String password) {
        Optional<User> sameUser = userRepository.findUserByEmail(email);

        if (sameUser.isPresent()) {
            throw new UniqueException("email");
        }

        return new User(email, password);
    }

    public UserDto findByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if(user.isEmpty()) {
            throw new NotFoundException("user");
        }

        return userMapper.toGetDto(user.get());
    }

    public UserDto create(UserPostRequest userPostRequest) {
        User user = createInstance(userPostRequest.getEmail(), userPostRequest.getPassword());

        add(user);

        return userMapper.toGetDto(user);
    }

    public UserDto findById(int id) {
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

    public UserDto removeById(int id) {
        UserDto user = findById(id);

        remove(userMapper.toEntity(user));

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = findByEmail(email);

        return new org.springframework.security.core.userdetails.User(
                userDto.getEmail(),
                userDto.getPassword(),
                true,
                true,
                true,
                true,
                new HashSet<>()
        );
    }
}