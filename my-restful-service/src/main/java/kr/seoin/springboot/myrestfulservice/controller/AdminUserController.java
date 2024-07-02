package kr.seoin.springboot.myrestfulservice.controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import kr.seoin.springboot.myrestfulservice.dao.AdminUser;
import kr.seoin.springboot.myrestfulservice.dao.User;
import kr.seoin.springboot.myrestfulservice.exception.UserNotFoundException;
import kr.seoin.springboot.myrestfulservice.service.UserDaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service;

    public AdminUserController(UserDaoService userDaoService) {
        this.service = userDaoService;
    }


    @GetMapping("/users/{id}")
    public MappingJacksonValue getUser(@PathVariable int id) {

        User user =service.findOne(id);
        AdminUser adminUser = new AdminUser();

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/users")
    public MappingJacksonValue getAllUsers() {
        List<User> users = service.findAll();
        List<AdminUser> adminUsers = new ArrayList<AdminUser>();

        for(User user : users) {
            AdminUser adminUser = new AdminUser();
            BeanUtils.copyProperties(user, adminUser);
            adminUsers.add(adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);
        return mapping;


    }

}
