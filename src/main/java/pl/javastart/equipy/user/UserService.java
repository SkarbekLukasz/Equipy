package pl.javastart.equipy.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    List<UserDto> findByLastName(String lastName) {
        return userRepository.findAllByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDto);
    }

    UserDto save(UserDto user) {
        Optional<User> userByPesel = userRepository.findByPesel(user.getPesel());
        userByPesel.ifPresent(u -> {
            throw new DuplicatePeselException();
        });
        User userEntity = UserMapper.toEntity(user);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);
    }

    UserDto update(UserDto user) {
        Optional<User> userByPesel = userRepository.findByPesel(user.getPesel());
        userByPesel.ifPresent(u -> {
            if(u.getId().equals(user.getId()))
                throw new DuplicatePeselException();
        });
        User userEntity = UserMapper.toEntity(user);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);
    }

    List<UserAssignmentDto> getUserAssignments(Long id) {
        return userRepository.findById(id)
                .map(User::getAssignments)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(UserAssignmentMapper::userAssignmentToDto)
                .collect(Collectors.toList());
    }
}
