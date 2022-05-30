package com.kdt.prgrms.board.controller;

import com.kdt.prgrms.board.dto.PostAddRequest;
import com.kdt.prgrms.board.dto.PostResponse;
import com.kdt.prgrms.board.dto.PostUpdateRequest;
import com.kdt.prgrms.board.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {

        this.postService = postService;
    }

    @PostMapping
    public void addPost(@RequestBody @Valid PostAddRequest postAddRequest) {

        postService.addPost(postAddRequest);
    }

    @GetMapping
    public List<PostResponse> getPosts(@PageableDefault(size = 10, sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {

       return postService.getPosts(pageable);
    }

    @GetMapping("{id}")
    public PostResponse getPostById(@PathVariable long id) {

        return postService.getPostById(id);
    }

    @PutMapping("{id}")
    public void updatePostById(@PathVariable long id, @RequestBody @Valid PostUpdateRequest postUpdateRequest) {

        postService.updatePostById(id, postUpdateRequest);
    }
}
