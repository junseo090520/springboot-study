package com.mirim.board.repository;

import com.mirim.board.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query(value = "select p from Post p join fetch p.author", countQuery = "select count(p) from Post p")
    Page<Post> findAllWithAuthor(Pageable pageable);
}