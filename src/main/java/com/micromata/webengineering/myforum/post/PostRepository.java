package com.micromata.webengineering.myforum.post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM Post p ORDER BY p.timestamp DESC")
    Iterable<Post> findAllOrderByTimestamp();
}
