package com.iut.intraiutserver.services;

<<<<<<< Updated upstream
public class PostService {
}
=======

import com.iut.intraiutserver.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, String username, Integer categoryId);
    void deletePost(Integer postId);

}
>>>>>>> Stashed changes
