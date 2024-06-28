package kr.seoin.springboot.myrestfulservice.controller;


import kr.seoin.springboot.myrestfulservice.dao.User;
import kr.seoin.springboot.myrestfulservice.exception.UserNotFoundException;
import kr.seoin.springboot.myrestfulservice.service.UserDaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user){
        user.setName(user.getName().replaceAll("\\s+",""));
        boolean b = user.getName().length() >= 2;
        User saveUser;
        if(b){
            saveUser=  service.save(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saveUser.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return ResponseEntity.noContent().build();
    }
}
