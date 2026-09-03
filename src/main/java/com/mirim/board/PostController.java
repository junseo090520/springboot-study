package com.mirim.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    @GetMapping()
    public String getPosts(@RequestParam(required = false) String keyword){
        if(keyword != null){
            return keyword + "(으)로 검색한 결과입니다.";
        }
        return "게시글의 목록입니다.";
    }

    @GetMapping("/count")
    public String getPostsCount(){
        return "게시글 개수 : 0개";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        //게시글 번호가 10번보다 크면 게시글이 없는 거임
        if(id > 10) {
            // 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시글입니다");
        }else if(id <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다");
        }
        return ResponseEntity.status(HttpStatus.OK).body(id + "번 게시글입니다.");
    }

    @PostMapping()
    public ResponseEntity<?> createPost(@RequestBody Map<String, Object> request){
        String title = (String)request.get("title");
        String content = (String)request.get("content");

        //db에 저장했다고 가정
        Map<String,Object> response = new HashMap<>();
        response.put("title",title);
        response.put("content",content);
        response.put("message","게시글이 등록되었습니다");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
