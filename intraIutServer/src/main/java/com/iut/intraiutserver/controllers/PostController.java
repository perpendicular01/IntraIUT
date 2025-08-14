package com.iut.intraiutserver.controllers;

public class PostController {
<<<<<<< Updated upstream
}
=======

    @Autowired
    private PostService postService;

    // CREATE Post
    @PostMapping("/user/{email}")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable("email") String email) {

        PostDto createdPost = postService.createPost(postDto, email);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // Get all posts
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
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








<<<<<<< Updated upstream
}
>>>>>>> Stashed changes
=======
}
>>>>>>> Stashed changes
