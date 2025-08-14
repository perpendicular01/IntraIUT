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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;



=======
import org.springframework.stereotype.Service;


>>>>>>> Stashed changes
=======
import org.springframework.stereotype.Service;


>>>>>>> Stashed changes

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
    public PostDto createPost(PostDto postDto, String userEmail) {

        // Find user by email
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(user);

        // Skip category assignment
        // post.setCategory(...);

        Post savedPost = postRepo.save(post);
        return modelMapper.map(savedPost, PostDto.class);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        this.postRepo.delete(post);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }


<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======


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




}
>>>>>>> Stashed changes


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




}
>>>>>>> Stashed changes





}