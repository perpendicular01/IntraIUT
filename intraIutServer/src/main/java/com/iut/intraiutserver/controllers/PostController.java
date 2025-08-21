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
import java.security.Principal; // <-- IMPORT THI

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // <-- FIX: SECURE ENDPOINT USING PRINCIPAL
    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostDto postDto,
            Principal principal) { // Get user identity from the token

        PostDto createdPost = postService.createPost(postDto, principal.getName());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
  

    // UPDATE Post
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @RequestBody PostDto postDto,
            @PathVariable Integer postId) {

        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return ResponseEntity.ok(updatedPost);
    }

   
    
    // DELETE Post
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully with id: " + postId);
    }

    // Get all posts
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}


