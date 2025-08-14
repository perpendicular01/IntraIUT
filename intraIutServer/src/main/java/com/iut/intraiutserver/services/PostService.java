package com.iut.intraiutserver.services;


import com.iut.intraiutserver.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, String userEmail);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
}


