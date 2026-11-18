package com.mirim.board.controller;

import com.mirim.board.Post;
import com.mirim.board.dto.PostRequest;
import com.mirim.board.dto.PostResponse;
import com.mirim.board.login.SessionConst;
import com.mirim.board.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
//        System.out.println("[확인] postService = " + postService.getClass());
    }

    @GetMapping()
    public ResponseEntity getPosts(@RequestParam(required = false) String keyword,
                                   @PageableDefault(size=5,sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        if(keyword != null){
            List<PostResponse> posts = postService.searchPosts(keyword);
            return ResponseEntity.ok(posts);
        }
        Page<PostResponse> posts = postService.getAllPosts(pageable);
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
        PostResponse post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping()
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest request,
                                        @SessionAttribute(name =SessionConst.LOGIN_MEMBER_ID) Long loginMemberId){
        String title = request.getTitle();
        String content = request.getContent();

        PostResponse response = postService.createPost(title,content,loginMemberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequest request){
        if(id <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다");
        }
        String title = request.getTitle();
        String content = request.getContent();

        Post response = postService.updatePost(id,title,content);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER_ID) Long loginMemberId) {
        postService.deletePost(id, loginMemberId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/proxy-test")
//    public int proxyTest(){
//        return postService.proxyTest();
//    }
}
