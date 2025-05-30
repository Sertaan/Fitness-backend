package com.gymbooking.fitness.User;

import com.gymbooking.fitness.DTO.UserGetDTO;
import com.gymbooking.fitness.DTO.UserPostDTO;
import com.gymbooking.fitness.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserGetDTO> getAllUsers() {

        return userRepository.findAll().stream()
        .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public Optional<UserGetDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::toDTO);
    }
    // Skapa en ny användare
    public User createUser(UserPostDTO userPostDTO) {
        userPostDTO.setPassword(passwordEncoder.encode(userPostDTO.getPassword()));
        User userToEntity = toEntity(userPostDTO);
        return userRepository.save(userToEntity);
    }

    // Registrera en ny användare
    public String registerUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "Användarnamn redan taget";
        }
        // Här kan du lägga till lösenordshashning innan sparande (rekommenderas)
        userRepository.save(user);
        return "Registrering lyckades";
    }

    // Uppdatera en användare
    public UserGetDTO updateUser(Long id, UserPostDTO updatedUserDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUserDTO.getFirstName());
                    user.setLastName(updatedUserDTO.getLastName());
                    user.setEmail(updatedUserDTO.getEmail());
                    user.setPassword(passwordEncoder.encode(updatedUserDTO.getPassword()));
                    userRepository.save(user);
                    return toDTO(user);
                })
                .orElseThrow(() -> new RuntimeException("com.gymbooking.fitness.User not found"));
    }

    // Radera en användare
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("com.gymbooking.fitness.User not found");
        }
        userRepository.deleteById(id);
    }

    public void createOrUpdateUser(User user){

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

    }

    private UserGetDTO toDTO(User user) {
        return new UserGetDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
    }

    private User toEntity(UserPostDTO userPostDTO) {
        return new User(
                userPostDTO.getUsername(),
                userPostDTO.getFirstName(),
                userPostDTO.getLastName(),
                userPostDTO.getPassword(),
                userPostDTO.getEmail(),
                userPostDTO.getRole()
        );
    }




    // Autentisera användare vid inloggning
    public boolean authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            // Jämför lösenord, här jämför vi direkt men helst ska du jämföra hash
            return user.get().getPassword().equals(password);
        }
        return false;
    }
}
