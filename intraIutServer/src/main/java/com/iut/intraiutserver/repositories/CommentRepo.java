package com.iut.intraiutserver.repositories;

import com.iut.intraiutserver.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository // Marks this as a Spring component for the persistence layer.
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    // By extending JpaRepository, this interface automatically inherits methods like:
    // - save(Comment comment)
    // - findById(Integer commentId)
    // - findAll()
    // - delete(Comment comment)
    // - and many more...

    // You don't need to add any methods here for basic CRUD functionality.
    // You could add custom query methods if needed, for example:
    // List<Comment> findByPost(Post post);
}
