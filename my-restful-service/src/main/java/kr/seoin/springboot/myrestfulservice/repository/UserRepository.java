package kr.seoin.springboot.myrestfulservice.repository;

import kr.seoin.springboot.myrestfulservice.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
