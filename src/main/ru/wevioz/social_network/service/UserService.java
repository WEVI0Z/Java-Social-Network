package wevioz.social_network.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.StatDto;
import wevioz.social_network.dto.TokenDto;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.dto.request.UserPostRequest;
import wevioz.social_network.entity.Role;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.UniqueException;
import wevioz.social_network.mapper.StatMapper;
import wevioz.social_network.mapper.UserMapper;
import wevioz.social_network.publisher.StatPublisher;
import wevioz.social_network.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class UserService implements EntityService<User> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final StatMapper statMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StatPublisher statPublisher;

    public List<UserDto> get() {
        return userMapper.toGetDtoList(userRepository.findAll());
    }

    public User createInstance(String email, String password, String city, String country) {
        Optional<User> sameUser = userRepository.findUserByEmail(email);

        if (sameUser.isPresent()) {
            throw new UniqueException("email");
        }

        return new User(email, passwordEncoder.encode(password), city, country);
    }

    public UserDto findByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if(user.isEmpty()) {
            throw new NotFoundException("user");
        }

        return userMapper.toGetDto(user.get());
    }

    public UserDto create(UserPostRequest userPostRequest) {
        User user = createInstance(
                userPostRequest.getEmail(),
                userPostRequest.getPassword(),
                userPostRequest.getCity(),
                userPostRequest.getCountry()
        );

        StatDto statDto = statMapper.toDto(user);
        statDto.setCreationDate(LocalDateTime.now());

        statPublisher.send(statDto);

        add(user);

        return userMapper.toGetDto(user);
    }

    public TokenDto authenticate(UserPostRequest userPostRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userPostRequest.getEmail(),
                        userPostRequest.getPassword()
                )
        );

        UserDto userDto = findByEmail(userPostRequest.getEmail());
        String token = jwtService.generateToken(userMapper.toEntity(userDto));

        return new TokenDto(token);
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
}