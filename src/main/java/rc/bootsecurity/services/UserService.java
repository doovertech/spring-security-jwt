package rc.bootsecurity.services;

import org.modelmapper.ModelMapper;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import rc.bootsecurity.dtos.AuthUser;
import rc.bootsecurity.entities.UserEntity;
import rc.bootsecurity.exceptions.PasswordValidationException;
import rc.bootsecurity.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired @Lazy
  private PasswordValidator passwordValidator;

  @Transactional
  public Long addUser(AuthUser newUser) {

    validatePassword(newUser.getPassword());

    UserEntity userEntity = userRepository.saveAndFlush(modelMapper.map(newUser, UserEntity.class));

    return userEntity.getUserId();
  }

  private void validatePassword(String password) {
    RuleResult passValidationResult = passwordValidator.validate(new PasswordData(password));
    if (!passValidationResult.isValid()) {
      String errorMessage = String.join("; ", passwordValidator.getMessages(passValidationResult));
      throw new PasswordValidationException(errorMessage);
    }
  }

}
