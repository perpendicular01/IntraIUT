package com.iut.intraiutserver.services.impl;


import com.iut.intraiutserver.entities.Category;
import com.iut.intraiutserver.entities.Post;
import com.iut.intraiutserver.entities.PostStatus;
import com.iut.intraiutserver.entities.User;
import com.iut.intraiutserver.exceptions.ResourceNotFoundException;
import com.iut.intraiutserver.payloads.PostDto;
import com.iut.intraiutserver.repositories.CategoryRepo;
import com.iut.intraiutserver.repositories.PostRepo;
import com.iut.intraiutserver.repositories.UserRepo;
import com.iut.intraiutserver.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
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



    @Override
    public PostDto createPost(PostDto postDto, String username, Integer categoryId) {
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", username));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        post.setStatus(PostStatus.PENDING);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }


}

