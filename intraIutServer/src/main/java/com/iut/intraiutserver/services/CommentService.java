package com.iut.intraiutserver.services;

import com.iut.intraiutserver.payloads.CommentDto;

public interface CommentService {


    CommentDto createComment(CommentDto commentDto, Integer postId, String username);


    void deleteComment(Integer commentId);
}
