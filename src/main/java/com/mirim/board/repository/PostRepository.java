package com.mirim.board.repository;

import com.mirim.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    //오타 주의
    //findBy = select * from
    //Containing = like
    //select * from posts where title = '';
    //List<Post> findByTitle(String keyword);

    //select * from posts where title like '%%'
    List<Post> findByTitleContaining(String keyword); //서버를 만들기 전에 미리 올라감

    //jpql
    @Query("select p from Post p join fetch p.author")
    List<Post> findAllWithAuthor();
}