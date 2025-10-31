package com.iut.intraiutserver.services.impl;


import com.iut.intraiutserver.entities.Category;
import com.iut.intraiutserver.entities.Post;
import com.iut.intraiutserver.entities.PostStatus;
import com.iut.intraiutserver.entities.User;
import com.iut.intraiutserver.exceptions.ApiException;
import com.iut.intraiutserver.exceptions.ResourceNotFoundException;
import com.iut.intraiutserver.payloads.PostDto;
import com.iut.intraiutserver.payloads.PostResponse;
import com.iut.intraiutserver.repositories.CategoryRepo;
import com.iut.intraiutserver.repositories.PostRepo;
import com.iut.intraiutserver.repositories.UserRepo;
import com.iut.intraiutserver.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;



    // --- ADD THIS FULL METHOD IMPLEMENTATION ---
    @Override
    public PostDto createPost(PostDto postDto, String username, Integer categoryId) {
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", username));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());

    public PostDto createPost(PostDto postDto, String userEmail) {
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        // <-- FIX: FETCH AND ASSIGN THE CATEGORY
        Category category = this.categoryRepo.findById(postDto.getCategory().getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", postDto.getCategory().getCategoryId()));

        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setCategory(category); // <-- SET THE CATEGORY ON THE POST

        Post savedPost = postRepo.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }
  
  

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        // update fields manually
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        // if imageName is coming from frontend, update it, else keep old one
        if (postDto.getImageName() != null && !postDto.getImageName().isEmpty()) {
            post.setImageName(postDto.getImageName());
        }

        Post updatedPost = postRepo.save(post);

        // map back to PostDto manually
        PostDto responseDto = new PostDto();
        responseDto.setPostId(updatedPost.getPostId());
        responseDto.setTitle(updatedPost.getTitle());
        responseDto.setContent(updatedPost.getContent());
        responseDto.setImageName(updatedPost.getImageName());
        responseDto.setAddedDate(updatedPost.getAddedDate());

        return responseDto;
    }

    @Override
    public PostResponse getPendingPosts(Integer pageNumber, Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePost = this.postRepo.findByStatus(PostStatus.PENDING, p);
        return pageToPostResponse(pagePost);
    }

    

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        this.postRepo.delete(post);
    }


    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }






    @Override
    public PostDto approvePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        post.setStatus(PostStatus.APPROVED);
        Post savedPost = this.postRepo.save(post);
        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto rejectPost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        post.setStatus(PostStatus.REJECTED);
        Post savedPost = this.postRepo.save(post);
        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByUsername(String username) {
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", username));
        List<Post> posts = this.postRepo.findByUserOrderByAddedDateDesc(user);
        return posts.stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}


