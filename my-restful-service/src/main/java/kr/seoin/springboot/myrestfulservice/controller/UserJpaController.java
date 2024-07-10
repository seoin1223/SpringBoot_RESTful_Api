package kr.seoin.springboot.myrestfulservice.controller;


import kr.seoin.springboot.myrestfulservice.dao.User;
import kr.seoin.springboot.myrestfulservice.exception.UserNotFoundException;
import kr.seoin.springboot.myrestfulservice.repository.UserRepository;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    private final UserRepository userRepository;


    public UserJpaController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new UserNotFoundException("id : "+id);
        }
        EntityModel<User> model = EntityModel.of(user.get());

        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linTo.withRel("all-users")); // all - user -> http://localhost:8088/users
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
