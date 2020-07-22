package rc.bootsecurity.services;

import org.modelmapper.ModelMapper;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rc.bootsecurity.dtos.AuthUser;
import rc.bootsecurity.dtos.Role;
import rc.bootsecurity.dtos.RoleType;
import rc.bootsecurity.dtos.User;
import rc.bootsecurity.entities.RoleEntity;
import rc.bootsecurity.entities.UserEntity;
import rc.bootsecurity.entities.UserRolesEntity;
import rc.bootsecurity.exceptions.PasswordValidationException;
import rc.bootsecurity.repository.RoleRepository;
import rc.bootsecurity.repository.UserRepository;
import rc.bootsecurity.repository.UserRolesRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Lazy
    private PasswordValidator passwordValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Long addUser(AuthUser newUser) {

        validatePassword(newUser.getPassword());

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        UserEntity userEntity = userRepository.save(modelMapper.map(newUser, UserEntity.class));

        if (newUser.getRoles() != null && !newUser.getRoles().isEmpty()) {
            newUser.getRoles().forEach(roleType -> {
                RoleEntity roleEntity = roleRepository.findByRole(roleType);
                UserRolesEntity userRolesEntity = new UserRolesEntity(userEntity.getUserId(), roleEntity.getRoleId());
                userRolesRepository.save(userRolesEntity);
            });
        }

        return userEntity.getUserId();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(userEntity -> users.add(modelMapper.map(userEntity, User.class)));

        users.forEach(this::getUserRoles);

        return users;
    }

    public User getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);

        return getUserRoles(modelMapper.map(userEntity, User.class));
    }

    private User getUserRoles(User user) {
        List<UserRolesEntity> userRolesEntities = userRolesRepository.findAllByUserId(user.getUserId());

        List<RoleType> roles = new ArrayList<>();
        userRolesEntities.forEach(userRolesEntity -> {
            Role role = modelMapper.map(roleRepository.findById(userRolesEntity.getRoleId()).get(), Role.class);

            roles.add(role.getRole());
        });
        user.setRoles(roles);

        return user;
    }

    private void validatePassword(String password) {
        RuleResult passValidationResult = passwordValidator.validate(new PasswordData(password));
        if (!passValidationResult.isValid()) {
            String errorMessage = String.join("; ", passwordValidator.getMessages(passValidationResult));
            throw new PasswordValidationException(errorMessage);
        }
    }

}
