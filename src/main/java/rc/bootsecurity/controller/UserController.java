package rc.bootsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rc.bootsecurity.dtos.AuthUser;
import rc.bootsecurity.services.UserService;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/sign-up")
  public ResponseEntity<?> registerUser(@RequestBody AuthUser newUser) {

    long userId = userService.addUser(newUser);
    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{userId}")
            .buildAndExpand(userId)
            .toUri();

    return ResponseEntity.created(location).build();
  }

}
