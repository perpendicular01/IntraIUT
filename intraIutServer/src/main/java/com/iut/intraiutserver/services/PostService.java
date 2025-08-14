package com.iut.intraiutserver.services;


import com.iut.intraiutserver.payloads.PostDto;

import java.util.List;

public interface PostService {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    PostDto createPost(PostDto postDto, String username, Integer categoryId);

=======
=======
>>>>>>> Stashed changes
    PostDto createPost(PostDto postDto, String userEmail);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    List<PostDto> getAllPosts();
=======
    PostDto createPost(PostDto postDto, String userEmail);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
>>>>>>> Stashed changes
=======
    PostDto createPost(PostDto postDto, String userEmail);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
>>>>>>> Stashed changes
}
>>>>>>> Stashed changes

}