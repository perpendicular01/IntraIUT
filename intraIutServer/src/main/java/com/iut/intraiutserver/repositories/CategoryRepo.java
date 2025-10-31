package com.iut.intraiutserver.repositories;

import com.iut.intraiutserver.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // Marks this interface as a Spring Data repository.
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}