package com.mirim.board.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    //아직 진짜 저장소 없음
    //컨트롤러에서 쓰던 가짜 규칙을 옮긴다
    public boolean existsById(Long id){
        return id <= 10;
    }

    public List<Map<String,Object>> findAll(){
        return new ArrayList<>();
    }

    public long count(){
        return 0;
    }

    public List<Map<String,Object>> findByKeyword(String keyword){
        //아직 진짜 데이터는 없다
        return new ArrayList<>();
    }
}

