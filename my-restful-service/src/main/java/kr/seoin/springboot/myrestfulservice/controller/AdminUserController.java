package kr.seoin.springboot.myrestfulservice.controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import kr.seoin.springboot.myrestfulservice.dao.AdminUser;
import kr.seoin.springboot.myrestfulservice.dao.AdminUserV2;
import kr.seoin.springboot.myrestfulservice.dao.User;
import kr.seoin.springboot.myrestfulservice.exception.UserNotFoundException;
import kr.seoin.springboot.myrestfulservice.service.UserDaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service;

    public AdminUserController(UserDaoService userDaoService) {
        this.service = userDaoService;
    }


    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue retrieveUser4AdminV1(@PathVariable int id) {

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

    @GetMapping("/v1/users")
    public MappingJacksonValue retrieveUserAll4AdminV1() {
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



    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue retrieveUser4AdminV2(@PathVariable int id) {

        User user =service.findOne(id);
        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/v2/users")
    public MappingJacksonValue retrieveUserAll4AdminV2() {
        List<User> users = service.findAll();
        List<AdminUserV2> adminUsersV2 = new ArrayList<AdminUserV2>();

        for(User user : users) {
            AdminUserV2 adminUser = new AdminUserV2();
            BeanUtils.copyProperties(user, adminUser);
            System.out.println(user);
            adminUser.setGrade("VIP");
            adminUsersV2.add(adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsersV2);
        mapping.setFilters(filters);
        return mapping;
    }


    @GetMapping(value = "/users/{id}",params = "version=1")
    public MappingJacksonValue retrieveUserAdminP1(@PathVariable int id) {

        User user =service.findOne(id);
        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);

        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(value = "/users/{id}",params = "version=2")
    public MappingJacksonValue retrieveUserAdminP2(@PathVariable int id) {

        User user =service.findOne(id);
        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");

        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(value = "/users/{id}",headers = "X-API-VERSION=1")
    public MappingJacksonValue retrieveUserAdminH1(@PathVariable int id) {

        User user =service.findOne(id);
        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);

        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(value = "/users/{id}",headers = "X-API-VERSION=2")
    public MappingJacksonValue retrieveUserAdminH2(@PathVariable int id) {

        User user =service.findOne(id);
        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");

        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }


    @GetMapping(value = "/users/{id}",produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserAdminPro1(@PathVariable int id) {

        User user =service.findOne(id);
        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);

        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping(value = "/users/{id}",produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserAdminPro2(@PathVariable int id) {

        User user =service.findOne(id);
        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }



}
