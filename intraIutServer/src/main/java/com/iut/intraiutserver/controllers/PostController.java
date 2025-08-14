package com.iut.intraiutserver.controllers;

import com.iut.intraiutserver.payloads.PostDto;
import com.iut.intraiutserver.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // CREATE Post
    @PostMapping("/user/{username}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable String username,
            @PathVariable Integer categoryId) {

        PostDto createdPost = this.postService.createPost(postDto, username, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    
    // DELETE Post
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully with id: " + postId);
    }




}