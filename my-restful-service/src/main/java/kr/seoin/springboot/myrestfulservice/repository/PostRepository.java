package kr.seoin.springboot.myrestfulservice.repository;

import kr.seoin.springboot.myrestfulservice.dao.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
