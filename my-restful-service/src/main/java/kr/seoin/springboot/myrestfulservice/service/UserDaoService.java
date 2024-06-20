package kr.seoin.springboot.myrestfulservice.service;


import kr.seoin.springboot.myrestfulservice.dao.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount;

    static {
        users.add(new User(1,"se1", new Date()));
        users.add(new User(2,"se2", new Date()));
        users.add(new User(3,"se3", new Date()));
    }

    private List<User> findAll(){
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        if (user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }

        users.add(user);

        return user;
    }


    public User findOne(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
}
