package com.mirim.board;

import com.mirim.board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity getPosts(@RequestParam(required = false) String keyword){
        if(keyword != null){
            List<Map<String,Object>> posts = postService.searchPosts(keyword);
            return ResponseEntity.ok(posts);
        }
        List<Map<String,Object>> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/count")
    public String getPostsCount(){
        long postCount = postService.getPostCount();
        return "게시글 개수 : " + postCount + "개";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        //게시글 번호가 0보다 작거나 같으면 잘못된 입력임
        if(id <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다");
        }
        Map<String,Object> post = postService.getPost(id);
        if(post == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시물입니다.");
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping()
    public ResponseEntity<?> createPost(@RequestBody Map<String, Object> request){
        String title = (String)request.get("title");
        String content = (String)request.get("content");

        Map<String, Object> response = postService.createPost(title,content);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Map<String, Object> request){
        if(id <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다");
        }
        String title = (String)request.get("title");
        String content = (String)request.get("content");

        Map<String, Object> response = postService.updatePost(id,title,content);

        if(response == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시글입니다");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        if(id <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다");
        }

        // db에서 삭제한다고 가정
        boolean deleted = postService.deletePost(id);
        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시글입니다");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
