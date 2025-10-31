package com.iut.intraiutserver.services.impl;

import com.iut.intraiutserver.entities.Comment;
import com.iut.intraiutserver.entities.Post;
import com.iut.intraiutserver.entities.User;
import com.iut.intraiutserver.exceptions.ResourceNotFoundException;
import com.iut.intraiutserver.payloads.CommentDto;
import com.iut.intraiutserver.repositories.CommentRepo;
import com.iut.intraiutserver.repositories.PostRepo;
import com.iut.intraiutserver.repositories.UserRepo;
import com.iut.intraiutserver.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, String username) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", username));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user); // Link the comment to the user

        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        // NOTE: Add authorization check here (is user the author or an admin?)
        Comment com = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        this.commentRepo.delete(com);
    }
}
